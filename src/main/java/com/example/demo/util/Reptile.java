package com.example.demo.util;

import com.example.demo.entity.Project;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Reptile {

    static ArrayList<String> blacklist = new ArrayList<>();

    static {
        blacklist.add("https://github.com/codenameone/CodenameOne/tree/master/vm");
        blacklist.add("https://github.com/networknt/light-4j/");
        blacklist.add("https://github.com/OrienteerBAP/Orienteer/");
        blacklist.add("https://github.com/s4kibs4mi/java-developer-roadmap");
        blacklist.add("https://github.com/zxing/zxing");
        blacklist.add("https://github.com/palantir/Sysmon");
        blacklist.add("https://github.com/springdoc/springdoc-openapi");
    }
    /**
     * 获取指定url的html
     * @param url
     * @return
     * @throws IOException
     */
    public static String getPage(String url) throws IOException {


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /**
     * 根据url获取github的项目信息
     * @param
     * @return
     * @throws IOException
     */
    public static List<Project> parsePage() throws IOException {
        ArrayList<Project> arrayList = new ArrayList<Project>();

        //通过Jsoup解析html
        Document document = Jsoup.parse(getPage("https://github.com/akullpp/awesome-java/blob/master/README.md"));
        Elements Allli = document.getElementsByTag("li");
        for (Element li:Allli) {
            Project project = new Project();
            boolean iserror=false;
            Elements Alla = li.getElementsByTag("a");
            //如果这个li标签中没有a标签则直接跳过
            if (Alla.size()==0) {
                continue;
            }
            //如果不是以https://github.com或者data-ga-click开头则跳过
            if (!Alla.toString().startsWith("<a href=\"https://github.com")||Alla.toString().contains("data-ga-click")) {
                continue;
            }
            for (String blackurl : blacklist) {
                if (blackurl.equals(Alla.attr("href"))) {
                    iserror=true;
                }
            }
            if (iserror) {
                continue;
            }
            //获取a标签内容并设置为项目名
            project.setName(Alla.text());
            //获取li标签的内容名设置为项目描述
            int deletelengh = Alla.text().length();
            if (deletelengh<li.text().length()) {
                project.setDescription(li.text().substring(deletelengh + 1));
            }
            //获取a标签链接并设为项目链接
            project.setUrl(Alla.attr("href"));
            arrayList.add(project);
        }
        return  arrayList;
    }



    /**
     * 调用Github API获取指定的Json响应格式
     * @param responName 仓库用户名
     * @return
     * @throws IOException
     */
    public static String getResponInfo(String responName) {
        String url = "https://api.github.com/repos/"+responName;

        //通过token验证
        String token = "token 55feba9c1a42bae36b87c79626c289142884bcab";
        Request request = new Request.Builder().url(url).header("Authorization",token).build();
        Call call = new OkHttpClient().newCall(request);
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            System.out.print("API爬取失败");
            System.out.println(url);
            return null;
        }
        if(!response.isSuccessful()){
            System.out.println("访问github API失败"+url);
            return "failed";
        }
        try {
            return response.body().string();
        } catch (IOException e) {
            System.out.println("通过API获取Json数据失败");
            return "failed";
        }
    }

    /**
     * 通过url获取Github的仓库名和作者名
     * @param url
     * @return
     */
    public static String getRepoName(String url){
        int lastone = url.lastIndexOf("/");
        int lasttwo = url.lastIndexOf("/",lastone-1);
        if(lastone == -1 || lasttwo == -1){
            System.out.println("获取仓库名失败，访问不合法");
            return null;
        }
        return url.substring(lasttwo+1);
    }


}

