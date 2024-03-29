package com.lyw.dao.interfaces;

import com.lyw.pojo.NewsHeadline;
import com.lyw.pojo.vo.HeadlineDetailVo;
import com.lyw.pojo.vo.HeadlinePageVo;
import com.lyw.pojo.vo.HeadlineQueryVo;

import java.util.List;

/**
 * @author: liuyaowen
 * @poject: TopNews
 * @create: 2024-03-28 22:53
 * @Description:
 */
public interface NewsHeadlineDao {

    /**
     * 分页查询新闻头条
     *
     * @param headlineQueryVo 查询条件
     * @return 返回查询到的新闻头条
     */
    List<HeadlinePageVo> queryHeadlineByPage(HeadlineQueryVo headlineQueryVo);

    /**
     * 查询新闻头条详情
     *
     * @param hid 新闻头条id
     * @return 返回查询到的新闻头条详情
     */
    HeadlineDetailVo queryHeadlineDetail(int hid);

    /**
     * 查询新闻头条总数
     *
     * @param headlineQueryVo 查询条件
     * @return 返回查询到的新闻头条总数
     */
    int queryHeadlineTotalCount(HeadlineQueryVo headlineQueryVo);

    /**
     * 插入新闻头条
     *
     * @param newsHeadline 新闻头条
     * @return 返回插入新闻头条的结果
     */
    int insertHeadline(NewsHeadline newsHeadline);

    /**
     * 根据新闻头条id查询新闻头条
     *
     * @param hid 新闻头条id
     * @return 返回查询到的新闻头条
     */
    NewsHeadline findHeadlineByHid(int hid);

    /**
     * 更新新闻头条
     *
     * @param newsHeadline 新闻头条
     * @return 返回更新新闻头条的结果 true:更新成功 false:更新失败
     */
    boolean update(NewsHeadline newsHeadline);

    /**
     * 根据新闻头条id删除新闻头条
     *
     * @param hid 新闻头条id
     * @return 返回删除新闻头条的结果 true:删除成功 false:删除失败
     */
    boolean removeByHid(int hid);
}
