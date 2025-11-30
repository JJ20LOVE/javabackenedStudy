package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pojo.Emp;

import java.util.List;

@Mapper
public interface EmpMapper {


    //原始分页查询
   /* @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    public Long count();

    @Select("select e.* ,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
            "order by e.update_time desc limit #{start},#{pageSize}")
    public List<Emp>list(Integer start,Integer pageSize)*/;

    //基于PageHelper的分页查询
    @Select("select e.* ,d.name deptName from emp e left join dept d on e.dept_id=d.id order by e.update_time desc" )
    List<Emp>list();
}
