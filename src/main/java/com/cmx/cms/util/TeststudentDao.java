package com.cmx.cms.util;

import java.util.List;

import com.cmx.cms.dao.StudentDao;
import com.cmx.cms.model.Student;

public class TeststudentDao{
    public static void main(String[] args) {
        try {
            StudentDao stuDao =new StudentDao();
            List<Student> stu= stuDao.getAll();
            for (Student s : stu) {
                System.err.println(s.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}