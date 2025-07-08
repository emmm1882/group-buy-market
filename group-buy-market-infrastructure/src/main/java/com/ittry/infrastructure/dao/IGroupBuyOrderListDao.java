package com.ittry.infrastructure.dao;

import com.ittry.infrastructure.dao.po.GroupBuyOrderList;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IGroupBuyOrderListDao {

    void insert(GroupBuyOrderList groupBuyOrderReq);

    GroupBuyOrderList queryGroupBuyOrderRecordByOutTradeNo(GroupBuyOrderList groupBuyOrderReq);

    Integer queryOrderCountByActivityId(GroupBuyOrderList groupBuyOrderListReq);
}
