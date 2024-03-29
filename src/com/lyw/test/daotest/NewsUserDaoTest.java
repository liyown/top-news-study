package com.lyw.test.daotest;

import com.lyw.dao.NewsUserDaoImpl;
import com.lyw.pojo.NewsUser;
import com.lyw.utils.JwtHelper;
import org.junit.Test;

/**
 * @author: liuyaowen
 * @poject: TopNews
 * @create: 2024-03-29 12:55
 * @Description:
 */
public class NewsUserDaoTest {
    private final NewsUserDaoImpl newsUserDao = new NewsUserDaoImpl();

    @Test
    public void testFindUser() {
        NewsUser newsUser = new NewsUser(0, "zhangsan", "123456", "张三");

        NewsUser user = newsUserDao.findUserByUsername(newsUser);
        String token = JwtHelper.createToken(Long.valueOf(user.getUid()));
        System.out.println(token);
        System.out.println(user);

    }
}
