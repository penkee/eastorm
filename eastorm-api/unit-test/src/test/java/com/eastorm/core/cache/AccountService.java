//package com.eastorm.core.cache;
//
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//
//import java.util.Random;
//
//public class AccountService {
//    @Cacheable(value = "accountCache")// 使用了一个缓存名叫 accountCache
//    public Account getAccountByName(String userName) {
//        // 方法内部实现不考虑缓存逻辑，直接实现业务
//        return getFromDB(userName);
//    }
//
//    @CacheEvict(value = "accountCache", key = "#account.getName()")// 清空 accountCache 缓存
//    public void updateAccount(Account account) {
//        updateDB(account);
//    }
//
//    @CacheEvict(value = "accountCache", allEntries = true)// 清空 accountCache 缓存
//    public void reload() {
//    }
//
//    private Account getFromDB(String acctName) {
//                  try {
//            Thread.sleep(new Random().nextInt(1000));
//          } catch (InterruptedException e) {
//            e.printStackTrace();
//          }
//        System.out.println("real querying db..." + acctName);
//        return new Account(acctName);
//    }
//
//    private void updateDB(Account account) {
//        System.out.println("real update db..." + account.getName());
//    }
//}