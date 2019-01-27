//package com.eastorm.core.cache;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;
//
//public class Main {
//
//  public static void main(String[] args) {
//    ApplicationContext context = new ClassPathXmlApplicationContext(
//            "classpath:spring/spring-cache.xml");// 加载 spring 配置文件
//
//    final AccountService s = (AccountService) context.getBean("accountServiceBean");
//    // 第一次查询，应该走数据库
////    Account account = s.getAccountByName("someone");
////    System.out.println("passwd="+account.getPassword());
////    account = s.getAccountByName("someone");
////    System.out.println("passwd="+account.getPassword());
//////
//    Executor pool= Executors.newFixedThreadPool(10);
//
//    for(int i=0;i<10;i++){
//      final int n=i;
//      pool.execute(new Runnable() {
//        public void run() {
////          try {
////            Thread.sleep(new Random().nextInt(10000));
////          } catch (InterruptedException e) {
////            e.printStackTrace();
////          }
//          Account account = s.getAccountByName("someone");
//          System.out.println(n+"passwd="+account.getPassword());
//          account=s.getAccountByName("someone");
//          System.out.println(n+"passwd="+account.getPassword());
//        }
//      });
//    }
//
//
//
////    s.reload();// 重置缓存
////    System.out.println("after reload...");
////
////    s.getAccountByName("somebody");// 应该是数据库查询
////    s.getAccountByName("somebody");// 第二次查询，应该直接从缓存返回
//
//  }
//
//}