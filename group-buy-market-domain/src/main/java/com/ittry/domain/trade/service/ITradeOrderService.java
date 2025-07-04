package com.ittry.domain.trade.service;

import com.ittry.domain.trade.model.entity.MarketPayOrderEntity;
import com.ittry.domain.trade.model.entity.MarketPayOrderEntity;
import com.ittry.domain.trade.model.entity.PayActivityEntity;
import com.ittry.domain.trade.model.entity.PayDiscountEntity;
import com.ittry.domain.trade.model.entity.UserEntity;
import com.ittry.domain.trade.model.valobj.GroupBuyProgressVO;

public interface ITradeOrderService {

    MarketPayOrderEntity queryNoPayMarketPayOrderByOutTradeNo(String userId, String outTradeNo);

    GroupBuyProgressVO queryGroupBuyProgress(String teamId);

    MarketPayOrderEntity lockMarketPayOrder(UserEntity userEntity, PayActivityEntity payActivityEntity, PayDiscountEntity payDiscountEntity);


}
