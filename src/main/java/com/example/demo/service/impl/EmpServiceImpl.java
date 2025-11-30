package com.example.demo.service.impl;

import com.example.demo.mapper.EmpMapper;
import com.example.demo.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Emp;
import pojo.PageResult;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    //分页查询
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Emp> empList = empMapper.list();
        Page<Emp> empPage = (Page<Emp>) empList;
        return new PageResult<Emp>(empPage.getTotal(), empPage.getResult());
    }

    //原始分页方式
    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        Long total = empMapper.count();
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);
        return new PageResult<>(total, rows);
    }*/
}
