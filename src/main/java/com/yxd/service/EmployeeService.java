package com.yxd.service;

import com.yxd.entity.Employee;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description 员工 Service 接口类
 * @Author yangxiaodong
 * @Date 2021-03-08
 **/
public interface EmployeeService {
    /**
     * @Description 查询所有员工
     * @Author yangxiaodong
     * @Date 2021-03-08
     **/
    public List<Employee> getAll();

    /**
     * @Description 员工保存
     * @Author yangxiaodong
     * @Date 2021-03-08
     **/
    void saveEmp(Employee employee);

    /**
     * @Description 检验用户名是否可用
     * @Author yangxiaodong
     * @Date 2021-03-08
     **/
    boolean checkUser(String empName);

    /**
     * @Description 根据id查询员工
     * @Author yangxiaodong
     * @Date 2021-03-08
     **/
    Employee getEmp(Integer id);

    /**
     * @Description 员工更新
     * @Author yangxiaodong
     * @Date 2021-03-08
     **/
    void updateEmp(Employee employee);

    /**
     * @Description 员工删除
     * @Author yangxiaodong
     * @Date 2021-03-08
     **/
    void deleteEmp(Integer id);

    /**
     * @Description 员工批量删除
     * @Author yangxiaodong
     * @Date 2021-03-08
     **/
    void deleteBatch(List<Integer> ids);
}
