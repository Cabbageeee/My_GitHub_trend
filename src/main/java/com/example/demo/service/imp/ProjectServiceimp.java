package com.example.demo.service.imp;
import com.example.demo.dao.ProjectDao;
import com.example.demo.entity.Project;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ProjectServiceimp implements ProjectService {

    @Autowired
    private ProjectDao projectDao;
    @Override
    public List<Project> selectTop10() {
        return projectDao.selectTop10();
    }

    @Override
    public void insert(Project project) {
        projectDao.insert(project);
    }

    @Override
    public void deletebyid(int id) {
        projectDao.deletebyid(id);
    }

    @Override
    public void deleteAll() {
        projectDao.deleteAll();
    }

    @Override
    public List<Project> selectbydate(Date date) {
        return projectDao.selectbydate(date);
    }

}
