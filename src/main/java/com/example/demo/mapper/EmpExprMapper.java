package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.pojo.EmpExpr;

import java.util.List;

@Mapper
public interface EmpExprMapper {


    void insertEmpExpr(List<EmpExpr> empExprs);

    void delete(List<Integer> ids);
}
