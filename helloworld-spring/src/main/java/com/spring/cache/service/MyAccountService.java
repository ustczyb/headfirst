package com.spring.cache.service;

import com.spring.cache.model.Account;
import com.spring.cache.model.MyCacheManager;

/**
 * Created by zhangyubo02 on 2017/7/6.
 * 不使用spring cache的service
 */
public class MyAccountService {

    private MyCacheManager<Account> cacheManager;

    public Account getAccountByName(String acctName) {
        Account result = cacheManager.getValue(acctName);// 首先查询缓存
        if(result!=null) {
            System.out.println("get from cache..."+acctName);
            return result;// 如果在缓存中，则直接返回缓存的结果
        }
        result = getFromDB(acctName);// 否则到数据库中查询
        if(result!=null) {// 将数据库查询的结果更新到缓存中
            cacheManager.addOrUpdateCache(acctName, result);
        }
        return result;
    }

    private Account getFromDB(String acctName) {
        System.out.println("real querying db..."+acctName);
        return new Account(acctName);
    }

    public void reload() {
        cacheManager.evictCache();
    }

    public MyAccountService() {
        cacheManager = new MyCacheManager<Account>();// 构造一个缓存管理器
    }


}
