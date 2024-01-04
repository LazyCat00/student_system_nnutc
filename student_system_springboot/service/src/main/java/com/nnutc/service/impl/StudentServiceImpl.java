package com.nnutc.service.impl;

import com.nnutc.bean.Student;
import com.nnutc.common.vo.Page;
import com.nnutc.common.vo.ResStatus;
import com.nnutc.common.vo.ResultVO;
import com.nnutc.dao.StudentMapper;
import com.nnutc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public ResultVO queryAllStudent() {
        List<Student> students = studentMapper.queryAllStu();
        return new ResultVO(ResStatus.OK, "查询所有学生", students);
    }

    @Override
    public ResultVO queryStuByPage(int pageNo, int pageSize) {
        Page<Student> studentPage = new Page<>();

        int i = studentMapper.stuCount();
        int pageTotal = i / pageSize + (i % pageSize > 0 ? 1 : 0);
        studentPage.setPageTotal(pageTotal);

        int offset = pageSize * (pageNo - 1);


        studentPage.setPageNo(pageNo);
        studentPage.setPageSize(pageSize);
        studentPage.setPageTotalCount(i);
        studentPage.setUrl("queryByPage");

        List<Student> students = studentMapper.queryStuByPage(offset, pageSize);
        studentPage.setItems(students);
//        当前页码
        return new ResultVO(ResStatus.OK, "分页查询学生", studentPage);
    }

    @Override
    public ResultVO queryFuzz(String fuzz, int pageNo, int pageSize) {
        List<Student> students = studentMapper.queryFuzz(fuzz, pageNo, pageSize);
        Page<Student> studentPage = new Page<>();

        int i = students.size();
        int pageTotal = i / pageSize + (i % pageSize > 0 ? 1 : 0);
        studentPage.setPageTotal(pageTotal);

        int offset = pageNo * (pageSize - 1);


        studentPage.setPageNo(pageNo);
        studentPage.setPageSize(pageSize);
        studentPage.setPageTotalCount(i);
        studentPage.setItems(students);
        studentPage.setUrl("queryFuzz");


        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResStatus.OK);
        resultVO.setMsg("查询成功！");
        resultVO.setData(studentPage);
        return resultVO;
    }

    @Override
    public ResultVO insertStu(Student student) {
        int i = studentMapper.insertStu(student);
        if (i == 1) {
            ResultVO resultVO = new ResultVO(ResStatus.OK, "新增成功！", student);
            return resultVO;
        } else {
            ResultVO resultVO = new ResultVO(ResStatus.NO, "新增失败！", student);
            return resultVO;
        }
    }

    @Override
    public ResultVO updateStu(Student student) {
        int i = studentMapper.updateStu(student);
        if (i == 1) {
            return new ResultVO(ResStatus.OK, "修改成功！", student);
        } else {
            return new ResultVO(ResStatus.NO, "修改失败！", student);
        }
    }

    @Override
    public ResultVO deleteStu(Integer stuId) {
        int i = studentMapper.deleteStu(stuId);
        if (i==1){
            return new ResultVO(ResStatus.OK,"删除成功！",stuId);
        }else {
            return new ResultVO(ResStatus.NO,"删除失败！",stuId);
        }

    }
}
