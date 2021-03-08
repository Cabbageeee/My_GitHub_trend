package com.example.demo.controller;

import com.example.demo.entity.Project;
import com.example.demo.service.imp.ProjectServiceimp;
import com.example.demo.util.MultithreadingReptile;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class Home {

    @Autowired
    ProjectServiceimp projectServiceimp;
    @RequestMapping("Message")
    @ResponseBody
    public String message(@RequestParam("date") String strdata, HttpServletResponse response) throws ParseException, IOException {
        Gson gson = new Gson();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date javadate = sdf.parse(strdata);
        java.sql.Date date = new java.sql.Date(javadate.getTime());
        List<Project> projects = projectServiceimp.selectbydate(date);
        if (projects == null) {
            List<Project> saveprojects = MultithreadingReptile.multiThreadingParse();
            for (Project project : saveprojects) {
                projectServiceimp.insert(project);
            }
            projects = projectServiceimp.selectbydate(date);
        }
        String json = gson.toJson(projects);
        response.getWriter().write(json);
        return json;
    }

    @RequestMapping("test")
    public String test(){
        System.out.println();


        return "success";
    }
}
