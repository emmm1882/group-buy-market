package com.ittry.infrastructure.dao;

import com.ittry.infrastructure.dao.po.GroupBuyOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IGroupBuyOrderDao {

    void insert(GroupBuyOrder groupBuyOrder);

    int updateAddLockCount(String teamId);

    GroupBuyOrder queryGroupBuyProgress(String teamId);
}
