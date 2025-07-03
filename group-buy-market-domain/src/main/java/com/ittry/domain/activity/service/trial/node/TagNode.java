package com.ittry.domain.activity.service.trial.node;

import com.ittry.domain.activity.model.entity.MarketProductEntity;
import com.ittry.domain.activity.model.entity.TrialBalanceEntity;
import com.ittry.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.ittry.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.ittry.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.ittry.types.design.framework.tree.StrategyHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class TagNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Resource
    private EndNode endNode;

    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {

        GroupBuyActivityDiscountVO groupBuyActivityDiscountVO = dynamicContext.getGroupBuyActivityDiscountVO();

        String tagId = groupBuyActivityDiscountVO.getTagId();
        boolean limitVisible = groupBuyActivityDiscountVO.isVisible();
        boolean limitEnable = groupBuyActivityDiscountVO.isEnable();

        if (StringUtils.isBlank(tagId)) {
            dynamicContext.setVisible(true);
            dynamicContext.setEnable(true);
            return router(requestParameter, dynamicContext);
        }

        boolean isWithin = repository.isTagCrowRange(tagId, requestParameter.getUserId());
        dynamicContext.setVisible(limitVisible ? isWithin : true);
        dynamicContext.setEnable(limitEnable ? isWithin : true);
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return endNode;
    }
}
