package com.example.admindemo.common;

import java.util.Arrays;
import java.util.HashMap;

public class UrlSecurityUtil {

    /**
     * 检查requestUri是否包含在urls里
     */
    public static boolean checkUrl(String requestUri, String[] urls){
        //对/进行特殊处理
        if ("/".equals(requestUri) && !Arrays.asList(urls).contains(requestUri)) {
            return false;
        }
        String[] requestUris = requestUri.split("/");
        for (String url : urls) {
            if (check(requestUris, url.split("/"))) {
                return true;
            }
        }
        return false;
    }

    private static boolean check(String[] requestUris, String[] urls){
        for(int i1 = 0; i1 < requestUris.length; i1++){
            //判断长度
            if(i1 >= urls.length){
                return false;
            }
            //处理/*、/**情况
            if("**".equals(urls[i1])){
                return true;
            }
            if("*".equals(urls[i1])){
                continue;
            }
            //处理带后缀
            if(requestUris[i1].contains(".") && urls[i1].contains(".")){
                String[] split = requestUris[i1].split("\\.");
                String[] split1 = urls[i1].split("\\.");
                //*.后缀的情况
                if("*".equals(split1[0]) && split[1].equals(split1[1])){
                    return true;
                }
            }
            //不相等
            if(!requestUris[i1].equals(urls[i1])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //无需权限即可访问的url
        String[] urls = {"/a/js/*.js","/a/img/**"};
        //给用户配置的url访问权限
        HashMap<Object, Object> user = new HashMap<>();
        user.put("username", "张三");
        user.put("urls", new String[]{"/","/a/css/*/common.css","/b/b1/","/c/*","/d/**"});

        //满足其中一种情况，就允许访问
        String[] urlz = {
                "/",
                "/a/css/a/common.css",
                "/a/css/a/a1/common.css",
                "/a/js/layui.js",
                "/a/js/layui.css",
                "/a/img/a/a.jpg",
                "/a/img/a1.png",
                "/b/b1",
                "/b/b2",
                "/c/c1",
                "/c/c1/c2",
                "/d/d1/d2",
        };
        for(String url: urlz){
            boolean flag = UrlSecurityUtil.checkUrl(url, urls) ||
                    UrlSecurityUtil.checkUrl(url, (String[])user.get("urls"));
            System.out.println(url + ", " + (flag ? "允许访问" : "无权访问"));
        }
    }
}
