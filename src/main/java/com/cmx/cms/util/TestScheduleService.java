package com.cmx.cms.util;

import java.util.List;

import com.cmx.cms.model.Schedule;
import com.cmx.cms.service.ScheduleService;

/**
 * checkConflicts 六场景测试。
 * 基于种子数据：
 *   PK001 = KK001(教师T002) 周一1-2节 R201 全周1-16（挂CS2401+CS2402）
 *   PK003 = KK001 周五1-2节 R102 单周1-16
 *   PK004 = KK005(教师T006) 周一5-6节 L501 全周
 * 在 IDE 中右键 Run As Java Application 运行。
 */
public class TestScheduleService {

    public static void main(String[] args) throws Exception {
        ScheduleService service = new ScheduleService();
        int pass = 0;
        int total = 6;

        // 场景1：与 PK001 同时间同教室（R201）→ 应报教室冲突
        List<String> r1 = service.checkConflicts(mk("KK003", 1, 1, 2, "R201", 1, 16, "全周"));
        pass += assertContains("教室冲突", r1, "场景1 教室冲突");

        // 场景2：与 PK001 同时间同教师（T002）但教室不同 → 应报教师冲突
        List<String> r2 = service.checkConflicts(mk("KK001", 1, 1, 2, "R102", 1, 16, "全周"));
        pass += assertContains("教师冲突", r2, "场景2 教师冲突");

        // 场景3：PK003 是单周，新排双周 → 单双周井水不犯河水 → 应无冲突
        List<String> r3 = service.checkConflicts(mk("KK001", 5, 1, 2, "R102", 1, 16, "双周"));
        pass += assertEmpty(r3, "场景3 单周vs双周不撞");

        // 场景4：PK003 是单周，新排也单周 → 应冲突
        List<String> r4 = service.checkConflicts(mk("KK001", 5, 1, 2, "R102", 1, 16, "单周"));
        pass += assertContains("教室冲突", r4, "场景4 单周vs单周撞");

        // 场景5：PK003 是周五1-2节(R102)，新排周五3-4节同教室 → 首尾相接不重叠 → 应无冲突
        List<String> r5 = service.checkConflicts(mk("KK001", 5, 3, 2, "R102", 1, 16, "单周"));
        pass += assertEmpty(r5, "场景5 节次首尾不撞");

        // 场景6：周日（weekday=7）无人排课 → 应无冲突
        List<String> r6 = service.checkConflicts(mk("KK001", 7, 1, 2, "R201", 1, 16, "全周"));
        pass += assertEmpty(r6, "场景6 周日全不撞");

        System.out.println("==========");
        System.out.println(pass + "/" + total + " 通过");
    }

    /** 快速造一条待检测的排课 */
    private static Schedule mk(String offeringId, int weekday, int startSlot, int slotCount,
            String classroomId, int startWeek, int endWeek, String weekType) {
        Schedule s = new Schedule();
        s.setOfferingId(offeringId);
        s.setWeekday(weekday);
        s.setStartSlot(startSlot);
        s.setSlotCount(slotCount);
        s.setClassroomId(classroomId);
        s.setStartWeek(startWeek);
        s.setEndWeek(endWeek);
        s.setWeekType(weekType);
        return s;
    }

    private static int assertContains(String keyword, List<String> conflicts, String name) {
        boolean ok = false;
        for (String m : conflicts) {
            if (m.contains(keyword)) {
                ok = true;
                break;
            }
        }
        System.out.println(name + ": " + (ok ? "PASS" : "FAIL") + " " + conflicts);
        return ok ? 1 : 0;
    }

    private static int assertEmpty(List<String> conflicts, String name) {
        boolean ok = conflicts.isEmpty();
        System.out.println(name + ": " + (ok ? "PASS" : "FAIL") + " " + conflicts);
        return ok ? 1 : 0;
    }
}
