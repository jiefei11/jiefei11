package com.dzxx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dzxx.common.R;
import com.dzxx.entity.Employee;

import javax.servlet.http.HttpServletRequest;

public interface EmployeeService extends IService<Employee> {
    R<Employee> login(HttpServletRequest request, Employee employee);

    R<Page> pages(int page, int pageSize, String name);

    R<Employee> addEmployee(HttpServletRequest request,Employee employee);

    R<Employee> getByid(Long id);

    R<String> updateEmployee(HttpServletRequest request, Employee employee);
}
