package com.ittry.infrastructure.dao;


import com.ittry.infrastructure.dao.po.GroupBuyDiscount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description 折扣配置Dao
 */
@Mapper
public interface IGroupBuyDiscountDao {

    List<GroupBuyDiscount> queryGroupBuyDiscountList();

    GroupBuyDiscount queryGroupBuyActivityDiscountByDiscountId(String discountId);

}
