package com.ittry.domain.tag.adapter.repository;

import com.ittry.domain.tag.model.entity.CrowdTagsJobEntity;

/**
 * @description 人群标签仓储接口
 */
public interface ITagRepository {

    CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId);

    void addCrowdTagsUserId(String tagId, String userId);

    void updateCrowdTagsStatistics(String tagId, int count);

    /**
     * 全量同步数据库人群标签明细到Redis BitSet
     */
    void syncAllCrowdTagsToRedis();

}
