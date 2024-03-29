package com.lyw.pojo.vo;

/**
 * @author: liuyaowen
 * @poject: TopNews
 * @create: 2024-03-28 22:50
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HeadlineQueryVo implements Serializable {
    private String keyWords;
    private Integer type;
    private Integer pageNum;
    private Integer pageSize;
}
