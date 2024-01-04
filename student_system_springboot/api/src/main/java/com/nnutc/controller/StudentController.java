package com.nnutc.controller;


import com.nnutc.bean.Student;
import com.nnutc.common.vo.ResultVO;
import com.nnutc.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
//@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用；
@RequestMapping("/stu")
@CrossOrigin//跨域访问
public class StudentController {
    @Resource
    StudentService studentService;

    @GetMapping("/queryAll")
    public ResultVO queryAll() {
        System.out.println("stu/queryAll-----start");
        return studentService.queryAllStudent();
    }

    @PostMapping("/queryByPage")
    public ResultVO queryByPage(@RequestParam("pageNo") int pageNo,
                                @RequestParam(value = "pageSize") int pageSize) {
        System.out.println("stu/queryByPage-----start");
        System.out.println(pageNo);
        return studentService.queryStuByPage(pageNo, pageSize);
    }

    @PostMapping("/queryFuzz")
    public ResultVO queryFuzz(@RequestParam("fuzz") String fuzz,
                              @RequestParam("pageNo") int pageNo,
                              @RequestParam("pageSize") int pageSize) {
        System.out.println("stu/queryFuzz-----start");
        return studentService.queryFuzz(fuzz, pageNo, pageSize);
    }

    @PostMapping("/addStu")
    public ResultVO addStu(@RequestBody Student student) {
        System.out.println("stu/addStu-----start");
        System.out.println(student);
        return studentService.insertStu(student);
    }
    @PostMapping("/updateStu")
    public ResultVO updateStu(@RequestBody Student student) {
        System.out.println("stu/updateStu-----start");
        System.out.println(student);
        return studentService.updateStu(student);
    }
    @PostMapping("/deleteStu")
    public ResultVO deleteStu(@RequestParam("stuId") Integer stuId) {
        System.out.println("stu/deleteStu-----start");
        return studentService.deleteStu(stuId);
    }
}
