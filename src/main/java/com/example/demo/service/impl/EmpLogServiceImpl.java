package com.example.demo.service.impl;

import com.example.demo.mapper.EmpLogMapper;
import com.example.demo.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.pojo.EmpLog;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}