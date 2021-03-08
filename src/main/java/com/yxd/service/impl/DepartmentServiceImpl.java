package com.yxd.service.impl;

import com.yxd.dao.DepartmentMapper;
import com.yxd.entity.Department;
import com.yxd.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author yangxiaodong
 * @Date 2021-03-08
 **/
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * @Description 获取部门信息
     * @Author yangxiaodong
     * @Date 2021-03-08
     **/
    @Override
    public List<Department> getDepts() {
        List<Department> list = departmentMapper.selectByExample(null);
        return list;
    }
}
