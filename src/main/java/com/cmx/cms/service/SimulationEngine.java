package com.cmx.cms.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.cmx.cms.dao.MajorCourseDao;
import com.cmx.cms.dao.OfferingDao;
import com.cmx.cms.dao.ScheduleDao;
import com.cmx.cms.dao.StudentDao;
import com.cmx.cms.dao.StudentOfferingDao;
import com.cmx.cms.dao.StudentStateDao;
import com.cmx.cms.dao.TeacherDao;
import com.cmx.cms.model.Schedule;
import com.cmx.cms.model.MajorCourse;
import com.cmx.cms.model.Offering;
import com.cmx.cms.model.SimState;
import com.cmx.cms.model.Student;
import com.cmx.cms.model.StudentOffering;
import com.cmx.cms.model.StudentState;
import com.cmx.cms.model.Teacher;

public class SimulationEngine {
    private static SimulationEngine instance = new SimulationEngine();

    public static SimulationEngine getInstance() {
        return instance;
    }

    public SimulationEngine() {
    }

    public SimState state = new SimState();

    public SimState getState() {
        return state;
    }

    private void runBehaviorRules() throws SQLException {
        List<StudentState> states = new StudentStateDao().getAll();
        Random rand = new Random();
        int skipped = 0;

        for (StudentState st : states) {

            double skipProb = (100 - st.getEnergy()) / 200.0
                    + (100 - st.getDiligence()) / 400.0;
            if (rand.nextDouble() < skipProb) {
                st.setEnergy(Math.min(0, st.getEnergy() + 5));
                st.setMood(Math.min(100, st.getMood() + 2));
                st.setAttendanceRate(st.getAttendanceRate().subtract(new BigDecimal("0.02")));
                skipped++;
            } else {
                st.setEnergy(Math.max(0, st.getEnergy() - 5));
                st.setMood(Math.max(0, st.getMood() - 3));
            }
            if (state.getWeek() % 4 == 0) {
                st.setMood(Math.min(100, st.getMood() + 30));
                st.setEnergy(Math.min(100, st.getEnergy() + 15));
            }
            new StudentStateDao().update(st);
        }
        state.getEvents().add("第 " + state.getWeek() + " 周：" + skipped + "/" + states.size() + " 人翘课");
    }

    public SimState tick() throws SQLException {
        if (!state.isStarted()) {
            state.setStarted(true);
            state.setWeek(1);
            state.getEvents().add("学期开始");
        } else {
            state.setWeek(state.getWeek() + 1);
            state.getEvents().add("第 " + state.getWeek() + " 周推进");
        }
        runBehaviorRules();
        return state;
    }

    public int autoCreateOfferings() throws SQLException {
        List<MajorCourse> plans = new MajorCourseDao().getAll();
        List<Teacher> teachers = new TeacherDao().getAll();
        Random rand = new Random();
        int seq = 1;
        for (MajorCourse mc : plans) {
            Offering o = new Offering();
            o.setOfferingId("AK" + String.format("%03d", seq++)); // AK001 AK002...
            o.setSemesterId(state.getSemesterId());
            o.setCourseId(mc.getCourseId());
            o.setTeacherId(teachers.get(rand.nextInt(teachers.size())).getTeacherId()); // 随机教师
            o.setWeeklyHours(4);
            new OfferingDao().add(o);
        }
        return seq - 1;
    }

    public int autoSchedule() throws SQLException {
        int[] slots = { 1, 3, 5, 7, 9 };
        String[] rooms = { "R201", "R101", "L501", "L502", "R305", "R401", "G001", "G002" };
        List<Offering> offerings = new OfferingDao().getAll();
        ScheduleService service = new ScheduleService();
        int scheduled = 0;
        int roomIdx = 0;
        for (Offering o : offerings) {
            if (!o.getOfferingId().startsWith("AK")) {
                continue;
            }
            boolean placed = false;
            outer: for (int slot : slots) {
                for (int day = 1; day < 7; day++) {
                    Schedule s = new Schedule();
                    s.setScheduleId("AS" + String.format("%03d", scheduled + 1));
                    s.setOfferingId(o.getOfferingId());
                    s.setWeekday(day);
                    s.setStartSlot(slot);
                    s.setSlotCount(2);
                    s.setClassroomId(rooms[roomIdx % rooms.length]); // ② 教室轮询分配
                    s.setStartWeek(1);
                    s.setEndWeek(16);
                    s.setWeekType("全周");
                    if (service.checkConflicts(s, null).isEmpty()) {
                        new ScheduleDao().add(s);
                        scheduled++;
                        roomIdx++;
                        placed = true;
                        break outer;
                    }
                }
            }
            if (!placed) {
                state.getEvents().add("排课失败：" + o.getOfferingId() + "无空闲时段");
            }
        }
        return scheduled;
    }

    public int autoSelectCourses() throws SQLException {
        List<Offering> elective = new OfferingDao().getElectives(state.getSemesterId());
        if (elective.isEmpty()) {
            return 0; // 没有选修课可选
        }
        List<Student> students = new StudentDao().getAll();
        Random rand = new Random();
        int total = 0;
        for (Student st : students) {
            int pick = rand.nextInt(2) + 1;
            for (int i = 0; i < pick; i++) {
                Offering o = elective.get(rand.nextInt(elective.size()));
                StudentOffering so = new StudentOffering();
                so.setStudentId(st.getStudentId());
                so.setOfferingId(o.getOfferingId());
                try {
                    new StudentOfferingDao().add(so);
                    total++;
                } catch (SQLException e) {
                    // 重复选同一门（联合主键冲突）就跳过，模拟里很正常
                }
            }
        }
        return total;
    }

    public SimState startSemester() throws SQLException {
        state.setWeek(1);
        state.setStarted(true);
        initStudentStates(); // 先初始化学生个体状态（勤奋随机=个体差异）
        int offerings = autoCreateOfferings();
        int schedules = autoSchedule();
        int selections = autoSelectCourses();
        state.getEvents().add("学期开始：自动开课 " + offerings + " 门、排课 " + schedules
                + " 条、学生选课 " + selections + " 人次");
        return state;
    }

    private void initStudentStates() throws SQLException {
        List<Student> students = new StudentDao().getAll();
        Random rand = new Random();
        for (Student st : students) {
            StudentState state = new StudentState();
            state.setStudentId(st.getStudentId());
            state.setEnergy(100);
            state.setMood(100);
            state.setAttendanceRate(new BigDecimal("1.00"));
            state.setDiligence(30 + rand.nextInt(51));
            state.setSemesterId(this.state.getSemesterId());
            new StudentStateDao().add(state);
        }
    }

    public Map<String, Object> getStudentState(String studentId) throws SQLException {
        List<StudentState> states = new StudentStateDao().getAll();
        if (states.isEmpty()) {
            return Map.of("avgEnergy", 0, "avgMood", 0, "avgDiligence", 0, "avgAttendanceRate", 0);
        }
        double avgeEnergy = states.stream().mapToInt(StudentState::getEnergy).average().orElse(0);
        double avgeMood = states.stream().mapToInt(StudentState::getMood).average().orElse(0);
        double avgeDiligence = states.stream().mapToInt(StudentState::getDiligence).average().orElse(0);
        BigDecimal avgeAttendanceRate = states.stream().map(StudentState::getAttendanceRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(states.size()), 2, BigDecimal.ROUND_HALF_UP);

        return Map.of("avgEnergy", avgeEnergy, "avgMood", avgeMood, "avgDiligence", avgeDiligence, "avgAttendanceRate",
                avgeAttendanceRate);
    }

    /** 统计面板数据：从 student_state 现算，不存新表 */
    public Map<String, Object> getStats() throws SQLException {
        Map<String, Object> stats = new LinkedHashMap<>();
        List<StudentState> states = new StudentStateDao().getAll();
        if (states.isEmpty()) {
            stats.put("avgEnergy", 0);
            stats.put("avgAttendance", 0);
            stats.put("slackers", 0);
            return stats;
        }
        int energySum = 0;
        BigDecimal attendanceSum = BigDecimal.ZERO;
        int slackers = 0;
        for (StudentState st : states) {
            energySum += st.getEnergy();
            attendanceSum = attendanceSum.add(st.getAttendanceRate());
            if (st.getAttendanceRate().compareTo(new BigDecimal("0.9")) < 0) {
                slackers++; 
            }
        }
        stats.put("avgEnergy", energySum / states.size());
        stats.put("avgAttendance",
                attendanceSum.divide(BigDecimal.valueOf(states.size()), 2, RoundingMode.HALF_UP));
        stats.put("slackers", slackers);
        return stats;
    }
}
