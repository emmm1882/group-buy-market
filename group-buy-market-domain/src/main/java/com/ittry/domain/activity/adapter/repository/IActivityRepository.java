package com.ittry.domain.activity.adapter.repository;


import com.ittry.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.ittry.domain.activity.model.valobj.SkuVO;

/**
 * @description 活动仓储
 */
public interface IActivityRepository {

    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(String source, String channel);

    SkuVO querySkuByGoodsId(String goodsId);

}
