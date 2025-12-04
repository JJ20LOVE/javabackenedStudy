package com.example.demo.service;

import com.example.demo.pojo.Dept;

import java.util.List;

public interface DeptService {
    public List<Dept> findAll();

    void delete(Integer id);

    void add(Dept dept);

    Dept get(Integer id);

    void update(Dept dept);
}
