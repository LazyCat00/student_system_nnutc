package com.nnutc.service;


import com.nnutc.bean.Student;
import com.nnutc.common.vo.ResultVO;



public interface StudentService {
    public ResultVO queryAllStudent();

    public ResultVO queryStuByPage(int offset, int pageSize);

    public ResultVO queryFuzz(String fuzz,int pageNo,int pageSize);
    public ResultVO insertStu(Student student);
    public ResultVO updateStu(Student student);
    public ResultVO deleteStu(Integer stuId);
}
