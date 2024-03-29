package com.lyw.test.daotest;

import com.lyw.dao.NewsHeadlineDaoImpl;
import com.lyw.dao.interfaces.NewsHeadlineDao;
import com.lyw.pojo.vo.HeadlinePageVo;
import com.lyw.pojo.vo.HeadlineQueryVo;
import org.junit.Test;

import java.util.List;

/**
 * @author: liuyaowen
 * @poject: TopNews
 * @create: 2024-03-29 16:11
 * @Description:
 */
public class NewsHeadlineDaoTest {
    private final NewsHeadlineDao newsHeadlineDao = new NewsHeadlineDaoImpl();

    @Test
    public void testQueryHeadlineByPage() {
/*    *
    * SELECT news_headline.hid , news_headline.title, news_headline.`type`, news_headline.page_views pageViews, TIMEDIFF(NOW(), news_headline.create_time) pageHours, news_headline.publisher
	    FROM news_headline
		WHERE  news_headline.is_deleted  = 0
			AND news_headline.`type`  = 4
			ORDER BY pageHours ASC, pageViews DESC
				LIMIT 0, 10
    *
    * */

        HeadlineQueryVo headlineQueryVo = new HeadlineQueryVo("", 4, 1, 10);
        List<HeadlinePageVo> headlinePageVos = newsHeadlineDao.queryHeadlineByPage(headlineQueryVo);
        System.out.println(headlinePageVos);
    }

    @Test
    public void showHeadlineDetailTest() {
        System.out.println(newsHeadlineDao.queryHeadlineDetail(1));
    }

    @Test
    public void queryHeadlineTotalCountTest() {
        HeadlineQueryVo headlineQueryVo = new HeadlineQueryVo("", 0, 1, 10);
        System.out.println(newsHeadlineDao.queryHeadlineTotalCount(headlineQueryVo));
    }
}
