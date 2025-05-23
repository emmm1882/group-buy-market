package com.ittry.domain.activity.service.trial;

import com.ittry.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.ittry.types.design.framework.tree.AbstractStrategyRouter;

/**
 * @description 抽象的拼团营销支撑类
 */
public abstract class AbstractGroupBuyMarketSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> extends AbstractStrategyRouter<com.ittry.domain.activity.model.entity.MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, com.ittry.domain.activity.model.entity.TrialBalanceEntity> {



}
