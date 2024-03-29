package com.lyw.dao.interfaces;

import com.lyw.pojo.NewsUser;

/**
 * @author: liuyaowen
 * @poject: TopNews
 * @create: 2024-03-28 22:53
 * @Description:
 */
public interface NewsUserDao {
    /**
     * Find user by username and password
     *
     * @param newsUser NewsUser
     * @return NewsUser
     */
    NewsUser findUserByUsername(NewsUser newsUser);

    /**
     * Find user by user id
     *
     * @param userId user id
     * @return NewsUser
     */
    NewsUser findUserByUid(int userId);

    /**
     * Insert user
     *
     * @param newsUser NewsUser
     * @return int
     */
    int insertUser(NewsUser newsUser);
}
