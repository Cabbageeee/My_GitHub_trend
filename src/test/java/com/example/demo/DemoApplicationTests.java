package com.example.demo;

//import com.example.demo.dao.ProjectDao;
//import com.example.demo.entity.Project;
//import com.example.demo.service.ProjectService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.Project;
import com.example.demo.service.imp.ProjectServiceimp;
import com.example.demo.util.MultithreadingReptile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    ProjectServiceimp projectServiceimp;
    @Test
    void contextLoads() throws IOException {
        List<Project> projects = MultithreadingReptile.multiThreadingParse();
        for (Project project:projects) {
            projectServiceimp.insert(project);
        }
    }

    public static void main(String[] args) {
        String a = "aa";
        a.hashCode();

    }

}
