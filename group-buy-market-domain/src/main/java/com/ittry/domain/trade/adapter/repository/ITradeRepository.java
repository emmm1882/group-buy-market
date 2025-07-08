package com.ittry.domain.trade.adapter.repository;

import com.ittry.domain.trade.model.aggregate.GroupBuyOrderAggregate;
import com.ittry.domain.trade.model.entity.GroupBuyActivityEntity;
import com.ittry.domain.trade.model.entity.MarketPayOrderEntity;
import com.ittry.domain.trade.model.valobj.GroupBuyProgressVO;

public interface ITradeRepository {
    MarketPayOrderEntity queryNoPayMarketPayOrderByOutTradeNo(String userId, String outTradeNo);

    GroupBuyProgressVO queryGroupBuyProgress(String teamId);

    MarketPayOrderEntity lockMarketPayOrder(GroupBuyOrderAggregate groupBuyOrderAggregate);

    GroupBuyActivityEntity queryGroupBuyActivityEntityByActivityId(Long activityId);

    Integer queryOrderCountByActivityId(Long activityId, String userId);
}
