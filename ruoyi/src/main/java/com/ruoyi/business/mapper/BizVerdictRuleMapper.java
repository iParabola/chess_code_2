package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.BizVerdictRule;
import com.ruoyi.business.domain.vo.BizVerdictRuleVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;

import java.util.List;
import java.util.Map;

/**
 * 裁决表管理Mapper接口
 *
 * @author ruoyi
 * @date 2024-02-19
 */
public interface BizVerdictRuleMapper extends BaseMapperPlus<BizVerdictRuleMapper, BizVerdictRule, BizVerdictRuleVo> {
   List<BizVerdictRuleVo> getRuleList(Long type);
}
