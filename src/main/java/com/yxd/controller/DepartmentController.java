package com.yxd.controller;

import com.yxd.entity.Department;
import com.yxd.entity.Msg;
import com.yxd.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description 处理和部门有关的请求
 * @Author yangxiaodong
 * @Date 2021-03-08
 **/
@Controller
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    /**
     * @Description 返回所有的部门信息
     * @Author yangxiaodong
     * @Date 2021-03-08
     **/
    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts() {
        // 查出所有部门信息
        List<Department> depts = departmentService.getDepts();
        return Msg.success().add("depts", depts);
    }
    /**
     * @Description 测试方法
     * @Author yangxiaodong
     * @Date 2021-03-09
     **/
    public void test() {
        System.out.println("哈哈哈哈");
    }
}
