package com.ruoyi.app.verdictRuleValue.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决规则标准值表对象 biz_verdict_rule_value
 *
 * @author ccc
 * @date 2024-09-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_verdict_rule_value")
public class BizVerdictRuleValue extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**              biz_verdict_rule_judge_criteria
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * biz_verdict_type表id
     */
    private Long verdictTypeId;
    /**
     * 裁决类型名称
     */
    private String verdictTypeName;
    /**
     * biz_verdict_rule表id
     */
    private Long verdictRuleId;
    /**
     * 裁决参数名称
     */
    private String verdictParamName;
    /**
     * 维度值（数组）(表格体内容)
     */
    private String verdicRuleDimensionValue;
    /**
     * 备注
     */
    private String remark;
    /**
     * 扩展字段
     */
    private String ext;
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;

}
