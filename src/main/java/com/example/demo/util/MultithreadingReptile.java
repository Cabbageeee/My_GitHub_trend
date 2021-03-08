package com.example.demo.util;


import com.example.demo.entity.Project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultithreadingReptile extends Reptile {
    public static List<Project> multiThreadingParse() throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        List<Project> projects = parsePage();
        List<Future> futures = new ArrayList<>();
        for (Project project:projects) {
            MyTask task = new MyTask(project);
            Future taskresult = executorService.submit(task);
            futures.add(taskresult);
        }
        //遍历Future调用get，主线程就会阻塞，直到所有线程执行完毕
        for (Future future : futures) {
            if (!(future == null)) {
                try {
                    future.get();
                } catch (InterruptedException e) {
                    System.out.println("线程中断异常");
                } catch (ExecutionException e) {
                    System.out.println("线程执行异常");
                }
            }
        }
        for (Project project : projects){
            System.out.println(project.toString());
            System.out.println("=============");
        }
        executorService.shutdownNow();
        return projects;
    }
}
