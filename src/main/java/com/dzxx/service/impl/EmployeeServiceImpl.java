package com.dzxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzxx.common.R;
import com.dzxx.entity.Employee;
import com.dzxx.mapper.EmployeeMapper;
import com.dzxx.service.EmployeeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService{
    @Override
    public R<Employee> login(HttpServletRequest request, Employee employee) {

        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = getOne(queryWrapper);

        if (emp==null){
            return R.error("登陆失败");
        }

        if (!emp.getPassword().equals(password)){
            return R.error("登陆失败");
        }
        if (emp.getStatus()== 0){
            return R.error("用户已被禁用");
        }
        request.getSession().setAttribute("employee",emp.getId());

        return R.success(emp);
    }

    @Override
    public R<Page> pages(int page, int pageSize, String name) {
        Page pageInfo = new Page(page, pageSize);

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<Employee>();
        queryWrapper.like(StringUtils.isNotBlank(name),Employee::getName,name)
                    .orderByDesc(Employee::getUpdateTime);

        page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }

    @Override
    public R<Employee> addEmployee(HttpServletRequest request , Employee employee) {
        String password = DigestUtils.md5DigestAsHex("123456".getBytes());
        employee.setPassword(password);
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//        Long empId = (Long) request.getSession().getAttribute("employee");
//
//        employee.setUpdateUser(empId);
//        employee.setCreateUser(empId);

        save(employee);
        return R.success(employee);
    }

    @Override
    public R<Employee> getByid(Long id) {
        Employee employee = getById(id);
        if(employee != null){
            return R.success(employee);
        }
        return R.error("没有查询到对应员工信息");
    }

    @Override
    public R<String> updateEmployee(HttpServletRequest request, Employee employee) {
//        Long emyId = (Long) request.getSession().getAttribute("employee");
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(emyId);
        updateById(employee);
        return R.success("修改信息成功");
    }
}
