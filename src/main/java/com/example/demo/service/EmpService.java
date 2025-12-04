package com.example.demo.service;

import com.example.demo.pojo.Emp;
import com.example.demo.pojo.EmpQueryParam;
import com.example.demo.pojo.PageResult;

import java.util.List;


public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void add(Emp emp);

    void delete(List<Integer> ids);

    Emp get(Integer id);

    void update(Emp emp);
}
