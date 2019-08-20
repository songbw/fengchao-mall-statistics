package com.fengchao.statistics.bean.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author tom
 * @Date 19-7-27 上午11:10
 */
@Setter
@Getter
@Deprecated
public class PromotionOverviewResVo {

    private String date; // '2019/6/1',

    /**
     * 秒杀
     */
    private Integer secKill = 0;

    /**
     * 优选
     */
    private Integer premium = 0;

    /**
     * 普通
     */
    private Integer normal = 0;

    /**
     * 其他
     */
    private Integer others = 0;

}
