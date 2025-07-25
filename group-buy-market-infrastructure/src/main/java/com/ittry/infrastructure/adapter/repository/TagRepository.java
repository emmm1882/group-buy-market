package com.ittry.infrastructure.adapter.repository;

import com.ittry.domain.tag.adapter.repository.ITagRepository;
import com.ittry.domain.tag.model.entity.CrowdTagsJobEntity;
import com.ittry.infrastructure.dao.ICrowdTagsDao;
import com.ittry.infrastructure.dao.ICrowdTagsDetailDao;
import com.ittry.infrastructure.dao.ICrowdTagsJobDao;
import com.ittry.infrastructure.dao.po.CrowdTags;
import com.ittry.infrastructure.dao.po.CrowdTagsDetail;
import com.ittry.infrastructure.dao.po.CrowdTagsJob;
import com.ittry.infrastructure.redis.IRedisService;
import org.redisson.api.RBitSet;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description 人群标签仓储
 */
@Repository
public class TagRepository implements ITagRepository {

    @Resource
    private ICrowdTagsDao crowdTagsDao;
    @Resource
    private ICrowdTagsDetailDao crowdTagsDetailDao;
    @Resource
    private ICrowdTagsJobDao crowdTagsJobDao;

    @Resource
    private IRedisService redisService;

    @Override
    public CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId) {
        CrowdTagsJob crowdTagsJobReq = new CrowdTagsJob();
        crowdTagsJobReq.setTagId(tagId);
        crowdTagsJobReq.setBatchId(batchId);

        CrowdTagsJob crowdTagsJobRes = crowdTagsJobDao.queryCrowdTagsJob(crowdTagsJobReq);
        if (null == crowdTagsJobRes) return null;

        return CrowdTagsJobEntity.builder()
                .tagType(crowdTagsJobRes.getTagType())
                .tagRule(crowdTagsJobRes.getTagRule())
                .statStartTime(crowdTagsJobRes.getStatStartTime())
                .statEndTime(crowdTagsJobRes.getStatEndTime())
                .build();
    }

    @Override
    public void addCrowdTagsUserId(String tagId, String userId) {
        CrowdTagsDetail crowdTagsDetailReq = new CrowdTagsDetail();
        crowdTagsDetailReq.setTagId(tagId);
        crowdTagsDetailReq.setUserId(userId);

        try {
            // 1. 写入数据库
            crowdTagsDetailDao.addCrowdTagsUserId(crowdTagsDetailReq);

            // 2. 同步设置Redis BitSet，保证Redis与数据库一致
            RBitSet bitSet = redisService.getBitSet(tagId);
            bitSet.set(redisService.getIndexFromUserId(userId), true);
        } catch (DuplicateKeyException ignore) {
            // 忽略唯一索引冲突
        }
    }

    @Override
    public void updateCrowdTagsStatistics(String tagId, int count) {
        CrowdTags crowdTagsReq = new CrowdTags();
        crowdTagsReq.setTagId(tagId);
        crowdTagsReq.setStatistics(count);

        crowdTagsDao.updateCrowdTagsStatistics(crowdTagsReq);
    }

    /**
     * 全量同步数据库人群标签明细到Redis BitSet
     */
    public void syncAllCrowdTagsToRedis() {
        List<CrowdTagsDetail> all = crowdTagsDetailDao.selectAll();
        int count = 0;
        for (CrowdTagsDetail detail : all) {
            try {
                RBitSet bitSet = redisService.getBitSet(detail.getTagId());
                bitSet.set(redisService.getIndexFromUserId(detail.getUserId()), true);
                count++;
                if (count % 1000 == 0) {
                    System.out.println("[syncAllCrowdTagsToRedis] 进度：" + count + " 条");
                }
            } catch (Exception e) {
                System.err.println("[syncAllCrowdTagsToRedis] 同步失败，tagId=" + detail.getTagId() + ", userId=" + detail.getUserId() + ", 错误：" + e.getMessage());
            }
        }
        System.out.println("[syncAllCrowdTagsToRedis] 同步完成，总计同步 " + count + " 条人群标签明细到Redis");
    }

}

