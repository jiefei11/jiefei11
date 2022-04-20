package com.dzxx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dzxx.common.R;
import com.dzxx.entity.Employee;
import com.dzxx.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 登陆
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request,@RequestBody Employee employee){
        return employeeService.login(request,employee);
    }


    /**
     * 员工退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //清理Session中保存的当前登录员工的id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }


    /**
     * 分页功能
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page , int pageSize , String name){
        log.info("page = {},pageSize = {},name = {}" ,page,pageSize,name);
        return employeeService.pages(page,pageSize,name);
    }

    /**
     * 添加员工
     * @param request
     * @param employee
     * @return
     */
    @PostMapping
    public R<Employee> addEmployee(HttpServletRequest request,@RequestBody Employee employee){
        try {
            return employeeService.addEmployee(request,employee);
        }catch (Exception e){
            return R.error("添加失败");
        }
    }

    @GetMapping("{id}")
    public R<Employee> getByid(@PathVariable Long id){
        return employeeService.getByid(id);
    }

    @PutMapping()
    public R<String> updateEmployee(HttpServletRequest request,@RequestBody Employee employee){
        return employeeService.updateEmployee(request,employee);
    }



}   