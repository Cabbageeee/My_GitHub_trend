package com.example.demo;

import com.example.demo.entity.Project;
import com.example.demo.service.imp.ProjectServiceimp;

import com.example.demo.util.MultithreadingReptile;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@SpringBootApplication()
@MapperScan("com.example.demo.dao")
public class DemoApplication {

    public static void main(String[] args) throws IOException, SQLException {
        SpringApplication.run(DemoApplication.class);


    }


}


