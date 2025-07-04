package com.ittry.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * @description 营销商品实体信息，通过这样一个信息获取商品优惠信息
 */
public class MarketProductEntity {

    private Long activityId;
    private String userId;
    private String goodsId;
    private String source;
    private String channel;
}
