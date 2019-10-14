package com.hrb.duyi.test;

import com.hrb.duyi.util.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        String result = HttpClientUtil.sendGet("https://open.duyiedu.com/api/student/findAll?appkey=demo13_1545210570249");
//        System.out.println(result);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("appkey","XXXXXX");
        param.put("account","demmmlll");
        param.put("username","白痴");
        param.put("password","qweqweqwe");
        param.put("rePassword","qweqweqwe");
        String result2 =  HttpClientUtil.sendPost("https://open.duyiedu.com/api/student/stuRegister",param);
        System.out.println(result2);
    }
}
