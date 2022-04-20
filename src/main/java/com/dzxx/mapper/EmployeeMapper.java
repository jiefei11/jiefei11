package com.dzxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dzxx.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}