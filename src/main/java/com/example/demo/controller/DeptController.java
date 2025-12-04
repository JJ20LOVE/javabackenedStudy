package com.example.demo.controller;

import com.example.demo.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.pojo.Dept;
import com.example.demo.pojo.Result;

import java.util.List;

@RestController
@RequestMapping("/depts")
@Slf4j
public class DeptController {

    //private static final Logger log= LoggerFactory.getLogger(DeptController.class); 等效于@slf4j
    @Autowired
    private DeptService deptService;


    @GetMapping
    public Result list(){
        log.info("查询全部部门");
        List<Dept> deptlist=deptService.findAll();
        return Result.success(deptlist);
    }

    @DeleteMapping
    public Result delete(Integer id){
        log.info("删除部门:{}",id);
        deptService.delete(id);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("添加部门:{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id){
        log.info("查询部门:{}",id);
        Dept dept=deptService.get(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("更新部门:{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}