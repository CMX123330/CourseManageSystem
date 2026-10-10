package com.cmx.cms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import com.cmx.cms.dao.MajorCourseDao;
import com.cmx.cms.dao.OfferingDao;
import com.cmx.cms.dao.ScheduleDao;
import com.cmx.cms.dao.StudentDao;
import com.cmx.cms.dao.StudentOfferingDao;
import com.cmx.cms.dao.TeacherDao;
import com.cmx.cms.model.Schedule;
import com.cmx.cms.model.MajorCourse;
import com.cmx.cms.model.Offering;
import com.cmx.cms.model.SimState;
import com.cmx.cms.model.Student;
import com.cmx.cms.model.StudentOffering;
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

    public SimState tick() {
        if (!state.isStarted()) {
            state.setStarted(true);
            state.setWeek(1);
            state.getEvents().add("学期开始");
        } else {
            state.setWeek(state.getWeek() + 1);
            state.getEvents().add("第 " + state.getWeek() + " 周推进");
        }
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
                state.getEvents().add("排课失败："+o.getOfferingId()+"无空闲时段");
            }
        }
        return scheduled;
    }
    public int autoSelectCourses() throws SQLException {
        List<Offering> elective = new OfferingDao().getElectives(state.getSemesterId());
        if (elective.isEmpty()) {
            return 0;   // 没有选修课可选
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
        int offerings = autoCreateOfferings();
        int schedules = autoSchedule();
        int selections = autoSelectCourses();
        state.getEvents().add("学期开始：自动开课 " + offerings + " 门、排课 " + schedules
                + " 条、学生选课 " + selections + " 人次");
        return state;
    }
}
