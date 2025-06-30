package com.ruoyi.app.verdictRuleValue.mapper;

import com.ruoyi.app.verdictRuleValue.domain.BizVerdictRuleValue;
import com.ruoyi.app.verdictRuleValue.domain.vo.BizVerdictRuleValueVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;

/**
 * 裁决规则标准值表Mapper接口
 *
 * @author ccc
 * @date 2024-09-13
 */
public interface BizVerdictRuleValueMapper extends BaseMapperPlus<BizVerdictRuleValueMapper, BizVerdictRuleValue, BizVerdictRuleValueVo> {
    public void physicalDeleteById(long verdictRuleId);
}
