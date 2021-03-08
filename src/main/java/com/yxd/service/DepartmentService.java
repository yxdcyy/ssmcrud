package com.yxd.service;

import com.yxd.entity.Department;

import java.util.List;

/**
 * @Description 部门 Service 接口
 * @Author yangxiaodong
 * @Date 2021-03-08
 **/
public interface DepartmentService {
    /**
     * @Description 获取部门信息
     * @Author yangxiaodong
     * @Date 2021-03-08
     **/
    List<Department> getDepts();
}
