package com.ittry.domain.trade.service;

import com.ittry.domain.trade.adapter.repository.ITradeRepository;
import com.ittry.domain.trade.model.aggregate.GroupBuyOrderAggregate;
import com.ittry.domain.trade.model.entity.MarketPayOrderEntity;
import com.ittry.domain.trade.model.entity.PayActivityEntity;
import com.ittry.domain.trade.model.entity.PayDiscountEntity;
import com.ittry.domain.trade.model.entity.UserEntity;
import com.ittry.domain.trade.model.valobj.GroupBuyProgressVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class TradeOrderService implements ITradeOrderService {

    @Resource
    private ITradeRepository repository;

    @Override
    public MarketPayOrderEntity queryNoPayMarketPayOrderByOutTradeNo(String userId, String outTradeNo) {
        return repository.queryNoPayMarketPayOrderByOutTradeNo(userId, outTradeNo);
    }

    @Override
    public GroupBuyProgressVO queryGroupBuyProgress(String teamId) {
        return repository.queryGroupBuyProgress(teamId);
    }

    @Override
    public MarketPayOrderEntity lockMarketPayOrder(UserEntity userEntity, PayActivityEntity payActivityEntity, PayDiscountEntity payDiscountEntity) {

        GroupBuyOrderAggregate groupBuyOrderAggregate = GroupBuyOrderAggregate.builder()
                .userEntity(userEntity)
                .payActivityEntity(payActivityEntity)
                .payDiscountEntity(payDiscountEntity)
                .build();


        return repository.lockMarketPayOrder(groupBuyOrderAggregate);
    }
}
