package com.lyw.dao;

import com.lyw.dao.interfaces.NewsTypeDao;
import com.lyw.pojo.NewsType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liuyaowen
 * @poject: TopNews
 * @create: 2024-03-28 22:54
 * @Description:
 */
public class NewsTypeDaoImpl extends BaseDao implements NewsTypeDao {

    public List<NewsType> getAllNewsType() {
        String sql = "SELECT tid, tname FROM top_news.news_type";
        List<NewsType> allNewsType = baseMultiObjectQuery(NewsType.class, sql);
        // 如果查询为空，返回一个空的NewsType对象
        if (allNewsType == null) {
            List<NewsType> newsTypes = new ArrayList<>();
            NewsType newsType = new NewsType(0, "查询为空");
            newsTypes.add(newsType);
            // 打印日志
            System.out.println("getAllNewsType: 查询为空");
            return newsTypes;
        }
        return allNewsType;

    }
}
