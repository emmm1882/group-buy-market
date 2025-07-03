package com.ittry.infrastructure.dao;


import com.ittry.infrastructure.dao.po.CrowdTagsDetail;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @description 人群标签明细
 */
@Mapper
public interface ICrowdTagsDetailDao {

    void addCrowdTagsUserId(CrowdTagsDetail crowdTagsDetailReq);

    List<CrowdTagsDetail> selectAll();

}
