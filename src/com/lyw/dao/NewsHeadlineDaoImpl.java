package com.lyw.dao;

import com.lyw.dao.interfaces.NewsHeadlineDao;
import com.lyw.pojo.NewsHeadline;
import com.lyw.pojo.vo.HeadlineDetailVo;
import com.lyw.pojo.vo.HeadlinePageVo;
import com.lyw.pojo.vo.HeadlineQueryVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: liuyaowen
 * @poject: TopNews
 * @create: 2024-03-28 22:54
 * @Description:
 */
public class NewsHeadlineDaoImpl extends BaseDao implements NewsHeadlineDao {

    public int queryHeadlineTotalCount(HeadlineQueryVo headlineQueryVo) {
        String sql = """
                SELECT\s
                    COUNT(1)\s
                FROM\s
                    news_headline\s
                WHERE\s
                    news_headline.is_deleted = 0
                """;
        StringBuilder sqlBuffer = new StringBuilder(sql);
        if (headlineQueryVo.getKeyWords() != null && !headlineQueryVo.getKeyWords().equals("")) {
            sqlBuffer.append(" and news_headline.title like '%" + headlineQueryVo.getKeyWords() + "%' ");
        }
        if (headlineQueryVo.getType() != null && headlineQueryVo.getType() != 0) {
            sqlBuffer.append(" and news_headline.type = " + headlineQueryVo.getType());
        }
        System.out.println(sqlBuffer);
        return selectCount(sqlBuffer.toString());

    }


    @Override
    public List<HeadlinePageVo> queryHeadlineByPage(HeadlineQueryVo headlineQueryVo) {
        Map<String, Object> map = new HashMap<>();
        String sql = """
                SELECT\s
                    news_headline.hid,\s
                    news_headline.title,\s
                    news_headline.`type`,\s
                    news_headline.page_views pageViews,\s
                    CONCAT('', TIMESTAMPDIFF(HOUR, news_headline.create_time, NOW())) pastHours,\s
                    news_headline.publisher\s
                FROM\s
                    news_headline\s
                WHERE\s
                    news_headline.is_deleted = 0
                """;

        StringBuilder sqlBuffer = new StringBuilder(sql);
        if (headlineQueryVo.getKeyWords() != null && !headlineQueryVo.getKeyWords().equals("")) {
            sqlBuffer.append(" and news_headline.title like '%" + headlineQueryVo.getKeyWords() + "%' ");
        }
        if (headlineQueryVo.getType() != null && headlineQueryVo.getType() != 0) {
            sqlBuffer.append(" and news_headline.type = " + headlineQueryVo.getType());
        }
        sqlBuffer.append(" limit " + (headlineQueryVo.getPageNum() - 1) * headlineQueryVo.getPageSize() + "," + headlineQueryVo.getPageSize());
        System.out.println(sqlBuffer);

        return baseMultiObjectQuery(HeadlinePageVo.class, sqlBuffer.toString());
    }

    @Override
    public HeadlineDetailVo queryHeadlineDetail(int hid) {
        String sql = """
                    SELECT 
                        news_headline.hid, 
                        news_headline.title, 
                        news_headline.article, 
                        news_headline.`type`,
                        news_type.tname typeName, 
                        news_headline.page_views pageViews, 
                        TIMESTAMPDIFF(HOUR, news_headline.create_time, NOW()) pastHours, 
                        news_headline.publisher, 
                        news_user.nick_name author
                    FROM 
                        news_headline
                    INNER JOIN 
                        news_type ON news_headline.type = news_type.tid
                    INNER JOIN 
                        news_user ON news_headline.publisher = news_user.uid
                    WHERE  
                        news_headline.is_deleted  = 0
                    AND 
                        news_headline.hid = ?
                """;
        return baseObjectQuery(HeadlineDetailVo.class, sql, hid);
    }

    public int insertHeadline(NewsHeadline newsHeadline) {
        String sql = """
                INSERT INTO\s
                    news_headline (title, article, type, publisher, page_views, create_time, update_time, is_deleted)
                VALUES (?, ?, ?, ?, 0,NOW(), NOW(), 0)
                """;
        return baseUpdate(sql, newsHeadline.getTitle(), newsHeadline.getArticle(), newsHeadline.getType(), newsHeadline.getPublisher());
    }

    public NewsHeadline findHeadlineByHid(int hid) {
        String sql = """
                SELECT\s
                    hid,\s
                    title,\s
                    article,\s
                    `type`,\s
                    publisher,\s
                    page_views pageViews,\s
                    create_time createTime,\s
                    update_time updateTime,\s
                    is_deleted isDeleted
                FROM\s
                    news_headline\s
                WHERE\s
                    hid = ?
                """;
        return baseObjectQuery(NewsHeadline.class, sql, hid);
    }

    @Override
    public boolean update(NewsHeadline newsHeadline) {
        String sql = """
                UPDATE\s
                    news_headline\s
                SET\s
                    title = ?,\s
                    article = ?,\s
                    `type` = ?,\s
                    update_time = NOW()\s
                WHERE\s
                    hid = ?
                """;
        return baseUpdate(sql, newsHeadline.getTitle(), newsHeadline.getArticle(), newsHeadline.getType(), newsHeadline.getHid()) > 0;
    }

    @Override
    public boolean removeByHid(int hid) {
        String sql = """
                UPDATE\s
                    news_headline\s
                SET\s
                    is_deleted = 1\s
                WHERE\s
                    hid = ?
                """;
        return baseUpdate(sql, hid) > 0;
    }
}
