package com.yxd.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yxd.entity.Employee;
import com.yxd.entity.Msg;
import com.yxd.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 员工控制器类, 处理员工 CRUD 请求
 * @Author yangxiaodong
 * @Date 2021-03-08
 **/
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     * @Description 单个批量二合一
     * 批量删除：1-2-3
     * 单个删除：1
     * @Author yangxiaodong
     * @Date 2021-03-08
     **/
    @RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteEmp(@PathVariable("ids") String ids) {
        // 批量删除
        if(ids.contains("-")) {
            List<Integer> del_ids = new ArrayList<>();
            String[] str_ids = ids.split("-");
            // 组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            employeeService.deleteBatch(del_ids);
        }else {
            Integer id = Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        }

        return Msg.success();
    }

    /**
     * @Description 员工更新
     *  我们要能支持直接发送 PUT 之类的请求还要封装请求体中的数据
     *  1. 配置上 HttpPutFormContentFilter
     *  2. HttpPutFormContentFilter 作用 : 将请求体中的数据解析包装成一个map
     *  3. request被重新包装，request.getParameter()被重写，就会从自己封装的map中取数据
     * @Author yangxiaodong
     * @Date 2021-03-08
     **/
    @RequestMapping(value = "/emp/{empId}", method = RequestMethod.PUT)
    @ResponseBody
    public Msg saveEmp(Employee employee, HttpServletRequest request) {
        System.out.println("请求体中的值：" + request.getParameter("gender"));
        System.out.println("将要更新的员工数据：" + employee);
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    /**
     * @Description 根据id查询员工
     * @Author yangxiaodong
     * @Date 2021-03-08
     **/
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp", employee);
    }

    /**
     * @Description 检查用户名是否可用
     * @Author yangxiaodong
     * @Date 2021-03-08
     **/
    @RequestMapping("/checkuser")
    @ResponseBody
    public Msg checkuser(String empName) {
        // 先判断用户名是否是合法的表达式
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
        if (!empName.matches(regx)) {
            return Msg.fail().add("va_msg", "用户名必须是6-16位数字和字母的组合或者2-5位中文");
        }

        // 数据库用户名重复校验
        boolean b = employeeService.checkUser(empName);
        if (b) {
            return Msg.success();
        } else {
            return Msg.fail().add("va_msg", "用户名不可用");
        }
    }

    /**
     * @Description 员工保存
     * 1. 支持JSR303校验
     * 2. 需要导入Hibernate-Validator
     * @Author yangxiaodong
     * @Date 2021-03-08
     **/
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            // 校验失败，应该返回失败，在模态框中显示校验失败的错误信息
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                System.out.println("错误的字段名：" + fieldError.getField());
                System.out.println("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        } else {
            employeeService.saveEmp(employee);
            return Msg.success();
        }
    }

    /**
     * @Description 查询员工数据(分页查询)
     * @Author yangxiaodong
     * @Date 2021-03-08
     **/
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 5);
        List<Employee> emps = employeeService.getAll();
        PageInfo<Employee> page = new PageInfo<>(emps, 5);
        return Msg.success().add("pageInfo", page);
    }
}
