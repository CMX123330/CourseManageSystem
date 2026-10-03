-- Create a new database called 'coursemanagersystem'
CREATE DATABASE IF NOT EXISTS coursemanagersystem DEFAULT CHARACTER SET utf8mb4;
USE coursemanagersystem;
DROP TABLE IF EXISTS schedule, offering_class, offering, course, student, teacher, clazz, major, slot, classroom, department, semester;
-- 学期表
CREATE TABLE semester(
    semester_id VARCHAR(20) PRIMARY KEY comment '学期编号，如 2025-2026-1',
    name        VARCHAR(50) NOT NULL comment '学期名称',
    start_date  DATE        NOT NULL comment '开始日期',
    end_date    DATE        NOT NULL comment '结束日期',
    total_weeks TINYINT     NOT NULL comment '总周数'
)ENGINE=InnoDB DEFAULT charset=utf8mb4;
-- 院系表
CREATE Table department(
    department_id VARCHAR(10) PRIMARY KEY COMMENT '院系编号',
    name VARCHAR(50) NOT NULL COMMENT '院系名字',
    dean VARCHAR(20) NOT NULL COMMENT '院长姓名',
    office_phone VARCHAR(20) NOT NULL COMMENT '院系电话'
)ENGINE=InnoDB DEFAULT charset=utf8mb4;
-- 教室表
create table classroom(
    classroom_id VARCHAR(10) PRIMARY KEY COMMENT '教室编号',
    building VARCHAR(20) not NULL COMMENT '教室楼栋',
    capacity SMALLINT not NULL COMMENT '教室容量（人数）'
)ENGINE=InnoDB DEFAULT charset=utf8mb4;
-- 节次时间段表
create table slot(
    slot_id TINYINT PRIMARY KEY COMMENT '节次序号（1~10，对应第N节）',
    start_time TIME not null COMMENT '开始时间',
    end_time TIME not null COMMENT '结束时间'
)ENGINE=InnoDB DEFAULT charset=utf8mb4;
-- 专业表
create table major(
    major_id VARCHAR(10) PRIMARY KEY COMMENT '专业编号，如 CS01',
    name VARCHAR(50) not NULL COMMENT '专业名称',
    department_id VARCHAR(10) not NULL COMMENT '所属院系编号',
    constraint fk_major_department FOREIGN key (department_id)
    REFERENCES department (department_id)
)ENGINE=InnoDB DEFAULT charset=utf8mb4;
-- 班级表
create table clazz(
    class_id VARCHAR(10) PRIMARY KEY COMMENT '班级编号，如 CS2401',
    name VARCHAR(50) NOT NULL COMMENT '班级名称',
    major_id VARCHAR(10) NOT NULL COMMENT '所属专业编号',
    grade SMALLINT NOT NULL COMMENT '年级',
    student_count SMALLINT NOT NULL COMMENT '人数',
    constraint fk_clazz_major FOREIGN key (major_id)
    REFERENCES major (major_id)
)ENGINE=InnoDB DEFAULT charset=utf8mb4;
-- 学生表
create table student(
    student_id VARCHAR(10) PRIMARY KEY COMMENT '学号（10 位）',
    name VARCHAR(50) not null COMMENT '姓名',
    gender CHAR(1) NOT NULL COMMENT '性别（男/女）',
    class_id VARCHAR(10) not NULL COMMENT '所属班级编号',
    phone VARCHAR(11) not NULL COMMENT '联系电话',
    constraint fk_student_clazz FOREIGN KEY (class_id)
    REFERENCES clazz (class_id)
)ENGINE=InnoDB DEFAULT charset=utf8mb4;
-- 教师表
create table teacher(
    teacher_id VARCHAR(20) PRIMARY KEY COMMENT '工号，如 T001',
    name VARCHAR(20) NOT NULL COMMENT '姓名',
    phone VARCHAR(11) not NULL COMMENT '电话',
    gender char(1) not null COMMENT '性别',
    department_id VARCHAR(10) NOT NULL COMMENT '所属院系编号',
    title VARCHAR(10) not NULL COMMENT '职称（教授/副教授/讲师）',
    constraint fk_teacher_department FOREIGN KEY(department_id)
    REFERENCES department (department_id)
)ENGINE=InnoDB DEFAULT charset=utf8mb4;
-- 课程表
create table course(
    course_id VARCHAR(10) PRIMARY KEY COMMENT '课程编号，如 C001',
    name VARCHAR(50) NOT NULL COMMENT '课程名称',
    hours SMALLINT not null COMMENT '学时',
    exam_type VARCHAR(4) not null COMMENT '考核方式（考试/考查）',
    credit DECIMAL(3,1) not NULL COMMENT '学分',
    department_id VARCHAR(10) NOT NULL COMMENT '开课院系编号',
    nature VARCHAR(4) not null COMMENT '课程性质（必修/选修）',
    constraint fk_course_department FOREIGN KEY(department_id) REFERENCES department (department_id)
) ENGINE=InnoDB DEFAULT charset=utf8mb4;
--开课表
CREATE Table offering(
    offering_id VARCHAR(10) PRIMARY KEY COMMENT '开课编号，如 KK001',
    semester_id VARCHAR(20) NOT NULL COMMENT '学期编号',
    course_id   VARCHAR(10) NOT NULL COMMENT '课程编号',
    teacher_id  VARCHAR(20) NOT NULL COMMENT '授课教师工号',
    weekly_hours TINYINT    NOT NULL COMMENT '周学时',
    constraint fk_offering_semster FOREIGN KEY (semester_id) REFERENCES semester (semester_id),
    constraint fk_offering_teacher FOREIGN KEY (teacher_id)  REFERENCES teacher  (teacher_id),
    constraint fk_offering_course FOREIGN KEY (course_id)  REFERENCES course  (course_id)
)engine =InnoDB DEFAULT charset=utf8mb4;
-- 开课-班级中间表
CREATE table offering_class(
    offering_id VARCHAR(10) not NULL COMMENT '开课编号',
    class_id VARCHAR(10) not NULL COMMENT '班级编号',
    PRIMARY KEY (offering_id,class_id),
    constraint fk_oc_offering FOREIGN KEY (offering_id) REFERENCES offering (offering_id),
    constraint fk_oc_class FOREIGN KEY (class_id) REFERENCES clazz (class_id)
) ENGINE=InnoDB DEFAULT charset=utf8mb4;
--排课表
create table schedule(
    schedule_id VARCHAR(20) PRIMARY KEY COMMENT '排课编号，如 PK001',
    offering_id VARCHAR(10) not NULL COMMENT '开课编号',
    weekday     TINYINT     not NULL COMMENT '星期（1~7，1=星期一）',
    start_slot  TINYINT     not NULL COMMENT '起始节次',
    slot_count  TINYINT     not NULL COMMENT '连续节数',
    classroom_id VARCHAR(10) NOT NULL COMMENT '教室编号',
    start_week  TINYINT     not NULL COMMENT '起始周',
    end_week    tinyint     not NULL COMMENT '结束周',
    week_type   VARCHAR(4)  not NULL COMMENT '单双周（全周/单周/双周）',
    constraint fk_schedule_classroom FOREIGN KEY (classroom_id) REFERENCES classroom(classroom_id),
    constraint fk_schedule_offering  FOREIGN KEY (offering_id)  REFERENCES offering(offering_id)
)engine =InnoDB DEFAULT charset=utf8mb4;
-- 用户表
create table user(
    user_id  VARCHAR(20) PRIMARY KEY COMMENT '登录账号（管理员=admin，教师=工号，学生=学号）',
    password VARCHAR(64) NOT NULL COMMENT '密码（先明文，后续哈希）',
    role     VARCHAR(10) NOT NULL COMMENT '角色：admin/teacher/student',
    name     VARCHAR(50) NOT NULL COMMENT '显示姓名'
)ENGINE=InnoDB DEFAULT charset=utf8mb4;
