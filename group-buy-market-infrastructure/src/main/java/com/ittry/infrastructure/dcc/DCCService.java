package com.ittry.infrastructure.dcc;


import com.ittry.types.annotations.DCCValue;
import org.springframework.stereotype.Service;

@Service
public class DCCService {

    @DCCValue("downgradeSwitch:0")
    private String downgradeSwitch ; // DCC降级开关

    @DCCValue("cutRange:100")
    private String cutRange ; // DCC切换范围

    public boolean isDowngradeSwitch() {
        return "1".equals(downgradeSwitch);
    }

    public boolean isCutRange(String userId) {
        int hashCode = Math.abs(userId.hashCode());

        int lastTwoDigits = hashCode % 100; // 获取用户ID的哈希值的最后两位数字

        return lastTwoDigits <= Integer.parseInt(cutRange);
    }
}
