package com.ruoyi.business.domain.dto;

import com.ruoyi.business.domain.BizVerdictBlowEffect;
import com.ruoyi.business.domain.BizVerdictRule;
import lombok.Data;

/**
 * @author kyc
 * @date 2024/3/8 14:04
 */
@Data
public class JudgeVo {
    private BizVerdictRule rule;
    private BizVerdictBlowEffect blowEffect;
    private int[] dice;
}
