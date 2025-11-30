package com.example.demo.service;

import org.springframework.stereotype.Service;
import pojo.Emp;
import pojo.PageResult;


public interface EmpService {
    PageResult<Emp> page(Integer page, Integer pageSize);
}
