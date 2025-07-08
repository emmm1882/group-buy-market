package com.ittry.domain.trade.service.factory;

import com.ittry.domain.trade.model.entity.GroupBuyActivityEntity;
import com.ittry.domain.trade.model.entity.TradeRuleCommandEntity;
import com.ittry.domain.trade.model.entity.TradeRuleFilterBackEntity;
import com.ittry.domain.trade.service.filter.ActivityUsabilityRuleFilter;
import com.ittry.domain.trade.service.filter.UserTakeLimitRuleFilter;
import com.ittry.types.design.framework.link.model2.LinkArmory;
import com.ittry.types.design.framework.link.model2.chain.BusinessLinkedList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import sun.plugin.javascript.navig.LinkArray;

@Slf4j
@Service
public class TradeRuleFilterFactory {

    @Bean
    public BusinessLinkedList tradeRuleFilter(ActivityUsabilityRuleFilter activityUsabilityRuleFilter,
                                              UserTakeLimitRuleFilter userTakeLimitRuleFilter) {
        LinkArmory<TradeRuleCommandEntity, TradeRuleFilterFactory.DynamicContext, TradeRuleFilterBackEntity> linkArmory =
                new  LinkArmory<>("交易规则过滤链", activityUsabilityRuleFilter, userTakeLimitRuleFilter);

        return linkArmory.getLogicLink();

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext{

        private GroupBuyActivityEntity groupBuyActivity;

    }
}
