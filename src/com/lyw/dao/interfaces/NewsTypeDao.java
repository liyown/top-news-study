package com.lyw.dao.interfaces;

import com.lyw.pojo.NewsType;

import java.util.List;

/**
 * @author: liuyaowen
 * @poject: TopNews
 * @create: 2024-03-28 22:53
 * @Description:
 */
public interface NewsTypeDao {
    /**
     * 查询所有新闻类型
     *
     * @return 返回查询到的所有新闻类型
     */
    List<NewsType> getAllNewsType();
}
