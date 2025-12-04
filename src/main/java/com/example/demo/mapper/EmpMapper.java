package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import com.example.demo.pojo.Emp;
import com.example.demo.pojo.EmpQueryParam;

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

    List<Emp>list(EmpQueryParam empQueryParam);
    //添加员工信息以及工作经历
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    @Insert("insert into emp (username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) VALUES " +
            "(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void delete(List<Integer> ids);

    Emp get(Integer id);

    void update(Emp emp);
}
