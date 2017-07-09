package com.spring.cache.service;

import com.spring.cache.model.Account;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by zhangyubo02 on 2017/7/9.
 * 使用了spring cache的service
 */

public class AccountService {

    private final String cacheName = "myCache";

    /**
     * 可以实现先从缓存取数据，如果缓存未命中则查询DB并且会写缓存的操作，不指定默认查询的key即为传入的参数acctName
     * @param acctName
     * @return
     */
    @Cacheable(value = cacheName)
    public Account getAccountByName(String acctName){
        return getFromDB(acctName);
    }

    @Cacheable(value = cacheName,key = "#acctName")
    public Account getAccountByNameAndPassword(String acctName, String password){
        return getFromDB(acctName);
    }

    /**
     * 只缓存name长度小于4的数据
     * @param acctName
     * @return
     */
    @Cacheable(value = cacheName, condition = "#acctName.length() < 4")
    public Account getShortAccountName(String acctName){
        return getFromDB(acctName);
    }

    /**
     * 清空缓存
     * @param acctName
     */
    @CacheEvict(cacheName)
    public void update(String acctName){
        System.out.println("update : " + acctName);
    }

    @CacheEvict(value = cacheName,allEntries = true)
    public void reload(){
        System.out.println("reload ...");
    }

    @CachePut(value = cacheName)
    public Account updateByName(String acctName){
        System.out.println("update ... " + acctName);
        return getFromDB(acctName);
    }

    private Account getFromDB(String acctName) {
        System.out.println("querying from db..."+acctName);
        return new Account(acctName);
    }
}
