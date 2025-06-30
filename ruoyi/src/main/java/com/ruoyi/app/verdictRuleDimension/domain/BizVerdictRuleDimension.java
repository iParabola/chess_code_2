package com.ruoyi.app.verdictRuleDimension.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决规则维度定义表对象 biz_verdict_rule_dimension
 *
 * @author ccc
 * @date 2024-09-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_verdict_rule_dimension")
public class BizVerdictRuleDimension extends BaseEntity {

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

    /***
     * 维度ID
     */
    private String dimensionId;
    /**
     * 维度名称
     */
    private String dimensionName;
    /**
     * 维度编码
     */
    private String dimensionCode;
    /**
     * 维度方向（01 横向 02 纵向 用于前端展现使用）
     */
    private String dimensionDirect;
    /**
     * 维度取值（数组）
     */
    private String dimensionValue;
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
