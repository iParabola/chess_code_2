package com.ruoyi.app.verdictRuleDimension.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决规则维度定义表业务对象 biz_verdict_rule_dimension
 *
 * @author ccc
 * @date 2024-09-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizVerdictRuleDimensionBo extends BaseEntity {

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


}
