package com.lyw.controller;

import com.lyw.common.ResultCodeEnum;
import com.lyw.pojo.NewsHeadline;
import com.lyw.pojo.vo.HeadlineDetailVo;
import com.lyw.service.NewsHeadlineService;
import com.lyw.utils.JwtHelper;
import com.lyw.utils.WebUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: liuyaowen
 * @poject: TopNews
 * @create: 2024-03-28 23:00
 * @Description:
 */
@WebServlet("/headline/*")
public class NewsHeadlineController extends BaseController {
    private final NewsHeadlineService newsHeadlineService = new NewsHeadlineService();

    /**
     * 分页查询新闻头条
     * 根据请求中的pageNum和pageSize分页查询新闻头条
     *
     * @param req  请求
     * @param resp 响应
     */
    protected void publish(HttpServletRequest req, HttpServletResponse resp) {
        // 获取请求头中的token
        String token = req.getHeader("token");
        // 从请求中读取json数据
        NewsHeadline newsHeadline = WebUtil.readJsonToObject(req, NewsHeadline.class);
        boolean expiration = JwtHelper.isExpiration(token);
        boolean publish = newsHeadlineService.publish(token, newsHeadline);// 发布新闻头条
        if (publish) {// 发布成功
            WebUtil.writeObjectToJson(resp, ResultCodeEnum.SUCCESS, "发布成功");
        } else {// 发布失败
            WebUtil.writeObjectToJson(resp, ResultCodeEnum.FAIL, "发布失败");
        }

    }

    /**
     * 更具hid查询新闻头条
     *
     * @param req  请求
     * @param resp 响应
     */
    protected void findHeadlineByHid(HttpServletRequest req, HttpServletResponse resp) {
        // 获取请求中的hid
        String hid = req.getParameter("hid");
        Map<String, Object> map = new HashMap<>();
        HeadlineDetailVo headlineDetailVo = newsHeadlineService.queryHeadlineDetail(Integer.parseInt(hid));
        map.put("hid", hid);
        map.put("title", headlineDetailVo.getTitle());
        map.put("article", headlineDetailVo.getArticle());
        map.put("type", headlineDetailVo.getType());

        Map<String, Object> data = Map.of("headline", map);
        // 查询新闻头条详情
        WebUtil.writeObjectToJson(resp, ResultCodeEnum.SUCCESS, data);
    }

    /**
     * 更新新闻头条
     *
     * @param req  请求
     * @param resp 响应
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) {
        NewsHeadline newsHeadline = WebUtil.readJsonToObject(req, NewsHeadline.class);
        // 更新新闻头条
        boolean update = newsHeadlineService.update(newsHeadline);
        if (update) {// 更新成功
            WebUtil.writeObjectToJson(resp, ResultCodeEnum.SUCCESS, "更新成功");
        } else {// 更新失败
            WebUtil.writeObjectToJson(resp, ResultCodeEnum.NOTLOGIN, "更新失败");
        }

    }

    /**
     * 根据hid删除新闻头条
     *
     * @param req  请求
     * @param resp 响应
     */
    protected void removeByHid(HttpServletRequest req, HttpServletResponse resp) {
        // 获取请求中的hid
        String hid = req.getParameter("hid");
        String token = req.getHeader("token");
        boolean expiration = JwtHelper.isExpiration(token);
        // 删除新闻头条
        boolean remove = newsHeadlineService.removeByHid(Integer.parseInt(hid));
        if (remove) {// 删除成功
            WebUtil.writeObjectToJson(resp, ResultCodeEnum.SUCCESS, "删除成功");
        } else {// 删除失败
            WebUtil.writeObjectToJson(resp, ResultCodeEnum.FAIL, "删除失败");
        }
    }
}
