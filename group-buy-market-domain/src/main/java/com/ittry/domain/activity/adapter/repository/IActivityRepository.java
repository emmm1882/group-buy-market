package com.ittry.domain.activity.adapter.repository;


import com.ittry.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.ittry.domain.activity.model.valobj.SCSkuActivityVO;
import com.ittry.domain.activity.model.valobj.SkuVO;

/**
 * @description 活动仓储
 */
public interface IActivityRepository {

    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId);

    SkuVO querySkuByGoodsId(String goodsId);

    SCSkuActivityVO querySCSkuActivityBySCGoodsId(String source, String channel, String goodsId);
}
