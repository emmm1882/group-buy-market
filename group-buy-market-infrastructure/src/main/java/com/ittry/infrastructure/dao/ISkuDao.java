package com.ittry.infrastructure.dao;
import com.ittry.infrastructure.dao.po.Sku;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 商品查询
 */
@Mapper
public interface ISkuDao {

    Sku querySkuByGoodsId(String goodsId);

}
