package com.lyw.service;

import com.lyw.dao.NewsHeadlineDaoImpl;
import com.lyw.dao.interfaces.NewsHeadlineDao;
import com.lyw.pojo.NewsHeadline;
import com.lyw.pojo.vo.HeadlineDetailVo;
import com.lyw.pojo.vo.HeadlinePageVo;
import com.lyw.pojo.vo.HeadlineQueryVo;
import com.lyw.utils.JwtHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author: liuyaowen
 * @poject: TopNews
 * @create: 2024-03-28 22:58
 * @Description:
 */
public class NewsHeadlineService {
    private final NewsHeadlineDao newsHeadlineDao = new NewsHeadlineDaoImpl();

    /**
     * 分页查询新闻头条
     *
     * @param headlineQueryVo 查询条件
     * @return 返回查询到的新闻头条
     */
    public Map<String, Object> queryHeadlineByPage(HeadlineQueryVo headlineQueryVo) {
        List<HeadlinePageVo> headlinePageVos = newsHeadlineDao.queryHeadlineByPage(headlineQueryVo);

        int totalSize = newsHeadlineDao.queryHeadlineTotalCount(headlineQueryVo);
        Map<String, Object> map = new HashMap<>();

        //          "pageNum":1,    //页码数
        //         "pageSize":10,  // 页大小
        //         "totalPage":20, // 总页数
        //         "totalSize":200 // 总记录数

        map.put("pageData", headlinePageVos);
        map.put("pageNum", headlineQueryVo.getPageNum());
        map.put("pageSize", headlineQueryVo.getPageSize());
        map.put("totalSize", totalSize);
        if (headlineQueryVo.getPageSize() != 0) {
            map.put("totalPage", totalSize % headlineQueryVo.getPageSize() == 0 ? totalSize / headlineQueryVo.getPageSize() : totalSize / headlineQueryVo.getPageSize() + 1);
        } else {
            map.put("totalPage", 0);
        }

        return map;
    }

    /**
     * 查询新闻头条详情
     *
     * @param hid 新闻头条id
     * @return 返回查询到的新闻头条详情
     */
    public HeadlineDetailVo queryHeadlineDetail(int hid) {
        return newsHeadlineDao.queryHeadlineDetail(hid);
    }

    /**
     * 插入新闻头条
     *
     * @param token        token
     * @param newsHeadline 新闻头条
     * @return 返回插入新闻头条的结果
     */
    public boolean publish(String token, NewsHeadline newsHeadline) {
        // Object 空指针异常
        Objects.requireNonNull(token);
        Objects.requireNonNull(newsHeadline);

        // 从token字符串获取userid
        Long userId = JwtHelper.getUserId(token);
        if (userId == null) {
            return false;
        }
        newsHeadline.setPublisher(Math.toIntExact(userId));
        return newsHeadlineDao.insertHeadline(newsHeadline) > 0;

    }

    /**
     * 根据新闻头条id查询新闻头条
     *
     * @param hid 新闻头条id
     * @return 返回查询到的新闻头条
     */
    public NewsHeadline findHeadlineByHid(int hid) {
        return newsHeadlineDao.findHeadlineByHid(hid);
    }

    /**
     * 更新新闻头条
     *
     * @param newsHeadline 新闻头条
     * @return 返回更新新闻头条的结果  true:更新成功  false:更新失败
     */
    public boolean update(NewsHeadline newsHeadline) {
        return newsHeadlineDao.update(newsHeadline);
    }

    /**
     * 根据新闻头条id删除新闻头条
     *
     * @param Hid 新闻头条id
     * @return 返回删除新闻头条的结果  true:删除成功  false:删除失败
     */
    public boolean removeByHid(int Hid) {
        return newsHeadlineDao.removeByHid(Hid);
    }
}
