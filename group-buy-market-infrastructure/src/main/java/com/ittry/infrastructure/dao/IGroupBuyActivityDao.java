package com.ittry.infrastructure.dao;


import com.ittry.domain.trade.model.entity.GroupBuyActivityEntity;
import com.ittry.infrastructure.dao.po.GroupBuyActivity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description 拼团活动Dao
 */
@Mapper
public interface IGroupBuyActivityDao {

    List<GroupBuyActivity> queryGroupBuyActivityList();

    GroupBuyActivity queryValidGroupBuyActivity(GroupBuyActivity groupBuyActivityReq);

    GroupBuyActivity queryValidGroupBuyActivityId(Long activityId);

    GroupBuyActivity queryGroupBuyActivityByActivityId(Long activityId);
}
