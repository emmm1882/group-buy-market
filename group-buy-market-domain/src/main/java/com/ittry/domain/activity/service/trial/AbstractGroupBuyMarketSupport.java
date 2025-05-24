package com.ittry.domain.activity.service.trial;

import com.ittry.domain.activity.adapter.repository.IActivityRepository;
import com.ittry.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;

import com.ittry.types.design.framework.tree.AbstractMultiThreadStrategyRouter;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @description 抽象的拼团营销支撑类
 */
public abstract class AbstractGroupBuyMarketSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> extends AbstractMultiThreadStrategyRouter<com.ittry.domain.activity.model.entity.MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, com.ittry.domain.activity.model.entity.TrialBalanceEntity> {

    protected long timeout = 500;
    @Resource
    protected IActivityRepository repository;

    @Override
    protected void multiThread(com.ittry.domain.activity.model.entity.MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        // 缺省的方法
    }


}
