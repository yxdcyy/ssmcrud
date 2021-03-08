package com.yxd.service.impl;

import com.yxd.dao.EmployeeMapper;
import com.yxd.entity.Employee;
import com.yxd.entity.EmployeeExample;
import com.yxd.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author yangxiaodong
 * @Date 2021-03-08
 **/
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * @Description 查询所有员工
     * @Author yangxiaodong
     * @Date 2021-03-08
     **/
    @Override
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    /**
     * @param employee
     * @Description 员工保存
     * @Author yangxiaodong
     * @Date 2021-03-08
     */
    @Override
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /**
     * @param empName
     * @Description 检验用户名是否可用
     * @Author yangxiaodong
     * @Date 2021-03-08
     * @Return true：代表当前姓名可用; fasle：不可用
     */
    @Override
    public boolean checkUser(String empName) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(example);
        return count == 0;
    }

    /**
     * @param id
     * @Description 根据id查询员工
     * @Author yangxiaodong
     * @Date 2021-03-08
     */
    @Override
    public Employee getEmp(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    /**
     * @param employee
     * @Description 员工更新
     * @Author yangxiaodong
     * @Date 2021-03-08
     */
    @Override
    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * @param id
     * @Description 员工删除
     * @Author yangxiaodong
     * @Date 2021-03-08
     */
    @Override
    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * @param ids
     * @Description 员工批量删除
     * @Author yangxiaodong
     * @Date 2021-03-08
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        // delete from xxx where emp_id in(1,2,3)
        criteria.andEmpIdIn(ids);
        employeeMapper.deleteByExample(example);
    }
}
