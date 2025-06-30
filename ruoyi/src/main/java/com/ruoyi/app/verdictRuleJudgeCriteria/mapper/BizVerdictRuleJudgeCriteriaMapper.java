package com.ruoyi.app.verdictRuleJudgeCriteria.mapper;

import com.ruoyi.app.verdictRuleJudgeCriteria.domain.BizVerdictRuleJudgeCriteria;
import com.ruoyi.app.verdictRuleJudgeCriteria.domain.vo.BizVerdictRuleJudgeCriteriaVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;

/**
 * 裁决判断前置条件Mapper接口
 *
 * @author ccc
 * @date 2024-09-18
 */
public interface BizVerdictRuleJudgeCriteriaMapper extends BaseMapperPlus<BizVerdictRuleJudgeCriteriaMapper, BizVerdictRuleJudgeCriteria, BizVerdictRuleJudgeCriteriaVo> {
    public void physicalDeleteById(long verdictTypeId);
}
