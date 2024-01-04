package com.nnutc.mapper;


import com.nnutc.bean.Student;
import com.nnutc.dao.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StudentDAOTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void insertStu() {
        for (int i = 21; i <= 60; i++) {
            Student student = new Student();
            student.setStuName("汪大东" + i);
            student.setStuGender('男');
            studentMapper.insertStu(student);
        }
    }

    @Test
    public void queryAllStu() {
        List<Student> students = studentMapper.queryAllStu();
        for (Student stu : students) {
            System.out.println(stu);
        }
    }
    @Test
    public void queryStuByPage() {
        List<Student> students = studentMapper.queryStuByPage(0,4);
        for (Student stu : students) {
            System.out.println(stu);
        }
    }
    @Test
    public void stuCount() {
        int i = studentMapper.stuCount();
        System.out.println(i);
    }
    @Test
    public void queryFuzz() {
        List<Student> students = studentMapper.queryFuzz("2",1,4);
        System.out.println(students);
    }
    @Test
    public void updateStu() {
        Student student = new Student(1,"刘德华",'男');
        int i = studentMapper.updateStu(student);
        System.out.println(i);
    }
    @Test
    public void deleteStu() {

        int i = studentMapper.deleteStu(75);
        System.out.println(i);
    }


}
