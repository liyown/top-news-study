package com.lyw.test.daotest;

import com.lyw.dao.NewsTypeDaoImpl;
import com.lyw.pojo.NewsType;
import org.junit.Test;

import java.util.List;

/**
 * @author: liuyaowen
 * @poject: TopNews
 * @create: 2024-03-28 23:33
 * @Description:
 */
public class NewsTypeDaoTest {
    private final NewsTypeDaoImpl newsTypeDao = new NewsTypeDaoImpl();

    @Test
    public void testFindAllType() {

        List<NewsType> allNewsType = newsTypeDao.getAllNewsType();
        System.out.println(allNewsType);

    }

}
