package com.ruoyi.app.verdictChessAttribute.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决棋子属性表对象 biz_verdict_chess_attribute
 *
 * @author ruoyi
 * @date 2024-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_verdict_chess_attribute")
public class BizVerdictChessAttribute extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * chess_pieces_type表id
     */
    private Long chessTypeId;

    /**
     * chess_pieces_attribute表id(属性引用)
     */
    private Long chessAttributeId;
    /**
     * 属性名称
     */
    private String attrName;
    /**
     * 属性编码
     */
    private String attrCode;
    /**
     * 属性类型：01 引用裁决规则维度（选项，单选或多选）、02 关联属性、03 常规
     */
    private String attrType;
    /**
     * 裁决规则维度编码
     */
    private String ruleDimensionCode;
    /**
     * 关联属性id
     */
    private Long attributeId;
    /**
     * 属性值
     */
    private String attrValue;
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
