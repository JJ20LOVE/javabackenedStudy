package com.example.demo.service.impl;

import com.example.demo.mapper.EmpExprMapper;
import com.example.demo.mapper.EmpLogMapper;
import com.example.demo.mapper.EmpMapper;
import com.example.demo.pojo.*;
import com.example.demo.service.EmpService;
import com.example.demo.utils.JWTUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogMapper empLogMapper;
    
    // 员工登录
    @Override
    public LoginInfo login(Emp  emp) {
       Emp e= empMapper.login(emp);
       if(e==null)
           return null;
       
       // 使用JWT工具类生成token
       String token = JWTUtils.generateToken(e);
       return new LoginInfo(e.getId(), e.getUsername(), e.getName(), token);
    }

    //分页查询
    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        PageHelper.startPage(page,pageSize);
        List<Emp> empList = empMapper.list(name,gender,begin,end);
        Page<Emp> empPage = (Page<Emp>) empList;
        return new PageResult<Emp>(empPage.getTotal(), empPage.getResult());
    }*/



    //原始分页方式
    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        Long total = empMapper.count();
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);
        return new PageResult<>(total, rows);
    }*/

    //分页查询
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        List<Emp> empList = empMapper.list(empQueryParam);
        Page<Emp> empPage = (Page<Emp>) empList;
        return new PageResult<Emp>(empPage.getTotal(), empPage.getResult());
    }

    //新增员工
    @Transactional//事务管理
    @Override
    public void add(Emp emp) {
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
            List<EmpExpr> empExprs = emp.getEmpExprs();

            if (!CollectionUtils.isEmpty(empExprs)) {
                empExprs.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
                empExprMapper.insertEmpExpr(empExprs);
            }
        } finally {
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            empLogMapper.insert(empLog);
        }

    }

    //批量删除员工
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
        empExprMapper.delete(ids);
    }

    @Override
    public Emp get(Integer id) {
        return empMapper.get(id);
    }

    @Override
    public void update(@RequestBody Emp emp) {
        //设置更新时间
        emp.setUpdateTime(LocalDateTime.now());
        //更新员工信息
        empMapper.update(emp);
        //删除工作经历
        empExprMapper.delete(Collections.singletonList(emp.getId()));

        //判断是否有工作经历
        if (!CollectionUtils.isEmpty(emp.getEmpExprs())) {
            emp.getEmpExprs().forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertEmpExpr(emp.getEmpExprs());
        }
    }

}