package com.ruoyi.app.verdictRuleJudgeCriteria.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决判断前置条件业务对象 biz_verdict_rule_judge_criteria
 *
 * @author ccc
 * @date 2024-09-18
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizVerdictRuleJudgeCriteriaBo extends BaseEntity {

    /**
     * 
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * biz_verdict_type表id
     */
    private Long verdictTypeId;

    /**
     * 裁决阶段：01~04  定、判、裁、定
     */
    private String stage;

    /**
     * 计算公式
     */
    private String formula;


}
