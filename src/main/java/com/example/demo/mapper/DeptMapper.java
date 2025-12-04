package com.example.demo.mapper;

import org.apache.ibatis.annotations.*;
import com.example.demo.pojo.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from dept order by id ")
    List<Dept> findAll();

    @Delete("delete from dept where id=#{id}")
    void delete(Integer id);

    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void add(Dept dept);

    @Select("select * from dept where id=#{id}")
    Dept get(Integer id);

    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}