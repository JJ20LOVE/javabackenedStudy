package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import pojo.EmpExpr;

import java.util.List;

@Mapper
public interface EmpExprMapper {


    void insertEmpExpr(List<EmpExpr> empExprs);
}
