package com.lyw.dao;

import com.lyw.dao.interfaces.NewsUserDao;
import com.lyw.pojo.NewsUser;

/**
 * @author: liuyaowen
 * @poject: TopNews
 * @create: 2024-03-28 22:54
 * @Description:
 */
public class NewsUserDaoImpl extends BaseDao implements NewsUserDao {
    @Override
    public NewsUser findUserByUsername(NewsUser newsUser) {
        String sql = "SELECT uid, username, user_pwd userPwd, nick_name nickName FROM top_news.news_user WHERE username = ?;";
        return baseObjectQuery(NewsUser.class, sql, newsUser.getUsername());
    }

    @Override
    public NewsUser findUserByUid(int userId) {
        String sql = "SELECT uid, username, user_pwd userPwd, nick_name nickName FROM top_news.news_user WHERE uid = ?;";
        return baseObjectQuery(NewsUser.class, sql, userId);
    }

    @Override
    public int insertUser(NewsUser newsUser) {
        String sql = "INSERT INTO top_news.news_user (username, user_pwd, nick_name) VALUES (?, ?, ?);";
        return baseUpdate(sql, newsUser.getUsername(), newsUser.getUserPwd(), newsUser.getNickName());
    }
}
