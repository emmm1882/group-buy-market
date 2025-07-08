package com.ittry.domain.trade.service.filter;

import com.ittry.domain.trade.adapter.repository.ITradeRepository;
import com.ittry.domain.trade.model.entity.GroupBuyActivityEntity;
import com.ittry.domain.trade.model.entity.TradeRuleCommandEntity;
import com.ittry.domain.trade.model.entity.TradeRuleFilterBackEntity;
import com.ittry.domain.trade.service.factory.TradeRuleFilterFactory;
import com.ittry.types.design.framework.link.model2.handler.ILogicHandler;
import com.ittry.types.enums.ResponseCode;
import com.ittry.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserTakeLimitRuleFilter implements ILogicHandler<TradeRuleCommandEntity, TradeRuleFilterFactory.DynamicContext, TradeRuleFilterBackEntity> {

    @Resource
    private ITradeRepository repository;

    @Override
    public TradeRuleFilterBackEntity apply(TradeRuleCommandEntity requestParameter, TradeRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("交易规则过滤-用户参与次数校验{} activityId:{}", requestParameter.getUserId(), requestParameter.getActivityId());

        GroupBuyActivityEntity groupBuyActivity = dynamicContext.getGroupBuyActivity();

        // 查询用户在一个拼团活动上参与的次数
        Integer count = repository.queryOrderCountByActivityId(requestParameter.getActivityId(), requestParameter.getUserId());

        if (null != groupBuyActivity.getTakeLimitCount() && count >= groupBuyActivity.getTakeLimitCount()) {
            log.info("用户参与次数校验，已达可参与上限 activityId:{}", requestParameter.getActivityId());
            throw new AppException(ResponseCode.E0103);
        }

        return TradeRuleFilterBackEntity.builder()
                .userTakeOrderCount(count)
                .build();

    }
}
