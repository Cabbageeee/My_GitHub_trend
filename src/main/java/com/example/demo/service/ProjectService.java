package com.example.demo.service;


import com.example.demo.entity.Project;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface ProjectService {
    List<Project> selectTop10();
    void insert(Project project);
    void deletebyid(int id);
    void deleteAll();
    List<Project> selectbydate(Date date);
}
