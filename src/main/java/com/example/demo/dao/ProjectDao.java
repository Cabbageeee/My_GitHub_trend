package com.example.demo.dao;

import com.example.demo.entity.Project;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


@Mapper
@Repository
public interface ProjectDao {
    @Results(id = "ProjectMap",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "url",column = "url"),
            @Result(property = "description",column = "description"),
            @Result(property = "stargazers_count",column = "stargazers_count"),
            @Result(property = "forks",column = "forks"),
            @Result(property = "subscribers_count",column = "subscribers_count"),
            @Result(property = "open_issues",column = "open_issues"),
            @Result(property = "date",column = "date")

    })
    @Select("select * from projects order by fame desc limit 10")
    List<Project> selectTop10();

    @Options(useGeneratedKeys = true,keyColumn = "id")
    @Insert("insert into projects(name,url,description,stargazers_count,forks,subscribers_count,open_issues,date) values(#{name},#{url},#{description},#{stargazers_count},#{forks}," +
            "#{subscribers_count},#{open_issues},#{date})")
    void insert(Project project);

    @Delete("delete from projects where id=#{id}")
    void deletebyid(int id);

    @Delete("delete from projects")
    void deleteAll();

    @Select("select * from projects where date =#{date} order by stargazers_count desc")
    List<Project> selectbydate(Date date);
}
