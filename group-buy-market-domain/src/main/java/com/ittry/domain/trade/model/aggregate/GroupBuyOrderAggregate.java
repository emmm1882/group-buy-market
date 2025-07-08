package com.ittry.domain.trade.model.aggregate;

import com.ittry.domain.trade.model.entity.PayActivityEntity;
import com.ittry.domain.trade.model.entity.PayDiscountEntity;
import com.ittry.domain.trade.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupBuyOrderAggregate {

    /** 用户实体对象 */
    private UserEntity userEntity;
    /** 支付活动实体对象 */
    private PayActivityEntity payActivityEntity;
    /** 支付优惠实体对象 */
    private PayDiscountEntity payDiscountEntity;
    /** 用户已参与拼团量 - 用于构建数据库唯一索引使用，确保用户只能在一个活动上参与固定的次数 */
    private Integer userTakeOrderCount;
}
