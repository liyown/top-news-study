package com.lyw.service;

import com.lyw.dao.NewsUserDaoImpl;
import com.lyw.pojo.NewsUser;
import com.lyw.utils.JwtHelper;
import com.lyw.utils.MD5Util;

/**
 * @author: liuyaowen
 * @poject: TopNews
 * @create: 2024-03-28 22:58
 * @Description:
 */
public class NewsUserService {
    private final NewsUserDaoImpl newsUserDao = new NewsUserDaoImpl();

    /**
     * Check if user exist
     *
     * @param newsUser NewsUser
     * @return true: user exist, false: user not exist
     */
    public boolean isUserExist(NewsUser newsUser) {
        NewsUser user = newsUserDao.findUserByUsername(newsUser);
        return user != null;
    }

    /**
     * login
     *
     * @param newsUser NewsUser
     * @return 1: login success, -1: password error, 0: user not exist
     */
    public String login(NewsUser newsUser) {
        boolean userExist = isUserExist(newsUser);
        if (userExist) {
            NewsUser newsUserFind = newsUserDao.findUserByUsername(newsUser);
            String token = JwtHelper.createToken(Long.valueOf(newsUserFind.getUid()));
            if (newsUserFind.getUserPwd().equals(MD5Util.encrypt(newsUser.getUserPwd()))) {
                // login success
                return token;
            } else {
                // password error
                return "-1";
            }
        } else {
            // user not exist
            return "0";
        }


    }

    public NewsUser findUserByUid(int userId) {
        return newsUserDao.findUserByUid(userId);
    }

    /**
     * register
     *
     * @param newsUser NewsUser
     * @return 1: register success, -1: user exist, 0: register fail
     */
    public int register(NewsUser newsUser) {
        boolean userExist = isUserExist(newsUser);
        if (userExist) {
            return -1;
        } else {
            newsUser.setUserPwd(MD5Util.encrypt(newsUser.getUserPwd()));
            return newsUserDao.insertUser(newsUser);
        }
    }
}
