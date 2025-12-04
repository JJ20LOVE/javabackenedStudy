package com.example.demo.controller;

import com.example.demo.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.pojo.Emp;
import com.example.demo.pojo.EmpQueryParam;
import com.example.demo.pojo.PageResult;
import com.example.demo.pojo.Result;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(EmpQueryParam empQueryParam){

        log.info("分页查询：{}",empQueryParam);
        PageResult<Emp> pageResult=empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    //新增员工
    @PostMapping
    public Result add(@RequestBody Emp emp){
        log.info("新增员工：{}",emp);
        empService.add(emp);
        return Result.success();
    }

    //批量删除员工
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("批量删除员工：{}",ids);
        empService.delete(ids);
        return Result.success();
    }


}
