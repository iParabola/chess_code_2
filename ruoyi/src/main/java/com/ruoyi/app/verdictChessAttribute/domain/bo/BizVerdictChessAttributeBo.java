package com.ruoyi.app.verdictChessAttribute.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决棋子属性表业务对象 biz_verdict_chess_attribute
 *
 * @author ruoyi
 * @date 2024-08-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizVerdictChessAttributeBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * verdict_chess表id
     */
    @NotNull(message = "chess_pieces_type表id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long chessTypeId;

    /**
     * chess_pieces_attribute表id
     */
    @NotNull(message = "chess_pieces_attribute表id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long chessAttributeId;

    /**
     * 属性名称
     */
    @NotBlank(message = "属性名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String attrName;

    /**
     * 属性编码
     */
    @NotBlank(message = "属性编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String attrCode;

    /**
     * 属性类型：01 引用裁决规则维度（选项，单选或多选）、02 关联属性、03 常规
     */
//    @NotBlank(message = "属性类型：01 引用裁决规则维度（选项，单选或多选）、02 关联属性、03 常规不能为空", groups = { AddGroup.class, EditGroup.class })
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
//    @NotBlank(message = "属性值不能为空", groups = { AddGroup.class, EditGroup.class })
    private String attrValue;

    /**
     * 备注
     */
    private String remark;

    /**
     * 扩展字段
     */
    private String ext;


}
