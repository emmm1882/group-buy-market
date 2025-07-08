package com.ittry.domain.activity.service.trial.node;


import com.ittry.domain.activity.model.entity.MarketProductEntity;
import com.ittry.domain.activity.model.entity.TrialBalanceEntity;
import com.ittry.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.ittry.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.ittry.types.design.framework.tree.StrategyHandler;
import com.ittry.types.enums.ResponseCode;
import com.ittry.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description 开关节点
 */
@Slf4j
@Service
public class SwitchNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Resource
    private MarketNode marketNode;

    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {

        String userId = requestParameter.getUserId();

        if(repository.downgradeSwitch()) {
            String msg = String.format("拼团活动降级拦截, userId:%s, activityId:%s, goodsId:%s, 原因:DCC开关已打开", 
                requestParameter.getUserId(), requestParameter.getActivityId(), requestParameter.getGoodsId());
            log.error(msg);
            throw new AppException(ResponseCode.E0003.getCode(), ResponseCode.E0003.getInfo());
        }

        if (!repository.cutRange(userId)) {
            throw new AppException(ResponseCode.E0004.getCode(), ResponseCode.E0004.getInfo());
        }
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return marketNode;
    }

}

