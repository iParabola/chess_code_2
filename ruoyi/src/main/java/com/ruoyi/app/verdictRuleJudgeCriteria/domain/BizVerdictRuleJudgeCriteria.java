package com.ruoyi.app.verdictRuleJudgeCriteria.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决判断前置条件对象 biz_verdict_rule_judge_criteria
 *
 * @author ccc
 * @date 2024-09-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_verdict_rule_judge_criteria")
public class BizVerdictRuleJudgeCriteria extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 
     */
    @TableId(value = "id")
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
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;

}
