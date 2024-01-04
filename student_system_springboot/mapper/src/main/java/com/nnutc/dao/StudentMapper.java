package com.nnutc.dao;

import com.nnutc.bean.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    public Student findStuByName(String stuName);

    public int insertStu(Student student);

    public List<Student> queryAllStu();

    public List<Student> queryStuByPage(@Param("offset") int offset, @Param("limit") int limit);

    public int stuCount();
    public List<Student> queryFuzz(@Param("fuzz")String fuzz,@Param("offset") int offset, @Param("limit") int limit);
    public int updateStu(Student student);
    public int deleteStu(Integer stuId);
}
