package com.eastorm.core;

import com.eastorm.api.member.service.SysMemberService;
import com.eastorm.impl.member.service.SysMemberServiceImpl;
import sun.misc.Unsafe;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

    static Map<String, SysMemberServiceImpl> cache = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        cache.computeIfAbsent("SysMemberServiceImpl",key->new SysMemberServiceImpl());


        System.out.println(cache.get("SysMemberServiceImpl"));
    }

}
