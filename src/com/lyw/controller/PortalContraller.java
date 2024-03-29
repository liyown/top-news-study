package com.lyw.controller;

import com.lyw.common.ResultCodeEnum;
import com.lyw.pojo.NewsType;
import com.lyw.pojo.vo.HeadlineDetailVo;
import com.lyw.pojo.vo.HeadlineQueryVo;
import com.lyw.service.NewsHeadlineService;
import com.lyw.service.NewsTypeService;
import com.lyw.utils.WebUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

/**
 * @author: liuyaowen
 * @poject: TopNews
 * @create: 2024-03-28 23:07
 * @Description:
 */
@WebServlet("/portal/*")
public class PortalContraller extends BaseController {
    private final NewsTypeService newsTypeService = new NewsTypeService();
    private final NewsHeadlineService newsHeadlineService = new NewsHeadlineService();

    /**
     * 查询所有新闻类型
     *
     * @param request  请求
     * @param response 响应
     */
    protected void findAllTypes(HttpServletRequest request, HttpServletResponse response) {
        List<NewsType> allNewsType = newsTypeService.getAllNewsType();
        WebUtil.writeObjectToJson(response, ResultCodeEnum.SUCCESS, allNewsType);
        System.out.println("findAllType success!");
    }

    /**
     * 分页查询新闻头条
     *
     * @param request  请求
     * @param response 响应
     */
    protected void findNewsPage(HttpServletRequest request, HttpServletResponse response) {
        HeadlineQueryVo headlineQueryVo = WebUtil.readJsonToObject(request, HeadlineQueryVo.class);
        Map<String, Object> headlinePageVos = newsHeadlineService.queryHeadlineByPage(headlineQueryVo);
        Map<String, Object> map = Map.of("pageInfo", headlinePageVos);
        WebUtil.writeObjectToJson(response, ResultCodeEnum.SUCCESS, map);

    }

    /**
     * 查询新闻头条详情
     *
     * @param request  请求
     * @param response 响应
     */
    protected void showHeadlineDetail(HttpServletRequest request, HttpServletResponse response) {
        String hid = request.getParameter("hid");
        HeadlineDetailVo headlineDetailVo = newsHeadlineService.queryHeadlineDetail(Integer.parseInt(hid));
        Map<String, Object> map = Map.of("headline", headlineDetailVo);
        WebUtil.writeObjectToJson(response, ResultCodeEnum.SUCCESS, map);
    }


}
