package com.example.demo.util;

import com.example.demo.entity.Project;
import com.google.gson.Gson;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class MyTask implements Runnable {
    private Project project;
    private int i=0;
    public MyTask(Project project) {
        this.project = project;
    }

    @Override
    public void run() {
        Gson gson = new Gson();
        String body = null;
        body = Reptile.getResponInfo(Reptile.getRepoName(project.getUrl()));
        //若获取失败，则一直获取直到获取成功，或十次后获取失败
        while (body==null&&i<10){
            body = Reptile.getResponInfo(Reptile.getRepoName(project.getUrl()));
            if (i>8){
                System.out.println(project.getUrl());
            }
            i++;
        }
        Project jsonProject = gson.fromJson(body, Project.class);
        project.setForks(jsonProject.getForks());
        project.setOpen_issues(jsonProject.getOpen_issues());
        project.setStargazers_count(jsonProject.getStargazers_count());
        project.setSubscribers_count(jsonProject.getSubscribers_count());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date date = new Date(System.currentTimeMillis());
        project.setDate(date);
        System.out.println(1);
    }
}
