package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决维度定义值域对象 biz_verdict_dimension_value
 *
 * @author ruoyi
 * @date 2024-09-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_verdict_dimension_value")
public class BizVerdictDimensionValue extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * verdict_dimension表id
     */
    private Long dimensionId;
    /**
     * 维度名称
     */
    private String dimensionName;
    /**
     * 维度值
     */
    private String dimensionValue;
    /**
     * 维度数组坐标
     */
    private Integer dimensionIndex;
    /**
     * 分类名称（引用字典名称 type）
     */
    private String className;
    /**
     * 分类编码（字典编码）
     */
    private String classCode;
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
