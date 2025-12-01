package com.example.demo.service;

import pojo.Emp;
import pojo.EmpQueryParam;
import pojo.PageResult;

import java.time.LocalDate;


public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void add(Emp emp);
}
