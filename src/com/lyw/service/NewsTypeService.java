package com.lyw.service;

import com.lyw.dao.NewsTypeDaoImpl;
import com.lyw.dao.interfaces.NewsTypeDao;
import com.lyw.pojo.NewsType;

import java.util.List;

/**
 * @author: liuyaowen
 * @poject: TopNews
 * @create: 2024-03-28 22:58
 * @Description:
 */
public class NewsTypeService {
    private final NewsTypeDao newsTypeDao;

    public NewsTypeService() {
        this.newsTypeDao = new NewsTypeDaoImpl();
    }

    /**
     * 查询所有新闻类型
     *
     * @return 返回查询到的所有新闻类型
     */
    public List<NewsType> getAllNewsType() {
        return newsTypeDao.getAllNewsType();
    }
}
