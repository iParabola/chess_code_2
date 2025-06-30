package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决维度定义对象 biz_verdict_dimension
 *
 * @author ruoyi
 * @date 2024-09-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_verdict_dimension")
public class BizVerdictDimension extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * product表id
     */
    private Long productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * biz_verdict_type表id
     */
    private Long verdictTypeId;
    /**
     * 裁决类型名称
     */
    private String verdictTypeName;
    /**
     * 维度名称
     */
    private String name;
    /**
     * 维度编码
     */
    private String code;
    /**
     * 数据来源（01人工填写  02业务表 ）
     */
    private String resource;
    /**
     * 字典编码
     */
    private String dictcode;
    /**
     * 业务表及字段
     */
    private String columnName;
    /**
     * 维度限长
     */
    private Integer length;
    /**
     * 维度取值类型（01 文本 02 数值）
     */
    private String valueType;
    /**
     * 是否分类（0 否 1 是）
     */
    private String isClass;
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
