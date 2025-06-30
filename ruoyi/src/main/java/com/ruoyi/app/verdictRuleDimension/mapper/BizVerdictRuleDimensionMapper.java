package com.ruoyi.app.verdictRuleDimension.mapper;

import com.ruoyi.app.verdictRuleDimension.domain.BizVerdictRuleDimension;
import com.ruoyi.app.verdictRuleDimension.domain.vo.BizVerdictRuleDimensionVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;

/**
 * 裁决规则维度定义表Mapper接口
 *
 * @author ccc
 * @date 2024-09-13
 */
public interface BizVerdictRuleDimensionMapper extends BaseMapperPlus<BizVerdictRuleDimensionMapper, BizVerdictRuleDimension, BizVerdictRuleDimensionVo> {
    public void physicalDeleteById(long verdictRuleId);
}
