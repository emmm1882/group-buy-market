package com.ittry.infrastructure.dcc;


import com.ittry.types.annotations.DCCValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DCCService {

    @Value("${groupbuy.downgradeSwitch:0}")
    private String downgradeSwitch ; // Spring配置降级开关

    @Value("${groupbuy.cutRange:100}")
    private String cutRange ; // Spring配置切换范围

    public boolean isDowngradeSwitch() {
        log.info("[DCC开关] downgradeSwitch 当前值: {}", downgradeSwitch);
        return "1".equals(downgradeSwitch);
    }

    public boolean isCutRange(String userId) {
        int hashCode = Math.abs(userId.hashCode());

        int lastTwoDigits = hashCode % 100; // 获取用户ID的哈希值的最后两位数字

        boolean result = lastTwoDigits <= Integer.parseInt(cutRange);
        log.info("[DCC开关] cutRange 当前值: {}, userId: {}, hash后两位: {}, 命中结果: {}", cutRange, userId, lastTwoDigits, result);
        return result;
    }
}
