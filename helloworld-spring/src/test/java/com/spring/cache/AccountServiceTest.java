package com.spring.cache;

import com.spring.cache.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhangyubo02 on 2017/7/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AccountServiceTest {

    @Autowired
    AccountService service;

    @Test
    public void testGetByName(){
        //第一次查询，走DB
        System.out.println("first query");
        service.getAccountByName("name");
        //第二次查询，走cache
        System.out.println("second query");
        service.getAccountByName("name");
    }

    @Test
    public void testGetByNameAndPassword(){
        System.out.println("first query");
        service.getAccountByNameAndPassword("name","123456");
        System.out.println("second query");
        service.getAccountByNameAndPassword("name","654321");
    }

    @Test
    public void testUpdate(){
        //第一次查询，走DB
        System.out.println("first query");
        service.getAccountByName("name");
        //第二次查询，走cache
        System.out.println("second query");
        service.getAccountByName("name");
        service.update("name");
        service.getAccountByName("name");
    }

    @Test
    public void testGetShortAccountName(){
        //第一次查询，走DB
        System.out.println("first query");
        service.getShortAccountName("name");
        //第二次查询，走cache
        System.out.println("second query");
        service.getShortAccountName("name");

        System.out.println("first query ... abc");
        service.getShortAccountName("abc");
        System.out.println("first query ... abc");
        service.getShortAccountName("abc");
    }

    @Test
    public void testUpdateByName(){
        System.out.println("first query");
        service.getAccountByName("name");
        System.out.println("second query");
        service.getAccountByName("name");

        service.updateByName("name");
        service.getAccountByName("name");
    }
}
