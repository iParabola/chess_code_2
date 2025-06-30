package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决维度定义业务对象 biz_verdict_dimension
 *
 * @author ruoyi
 * @date 2024-09-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizVerdictDimensionBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * product表id
     */
    @NotNull(message = "product表id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 产品名称
     */
    @NotBlank(message = "产品名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String productName;

    /**
     * biz_verdict_type表id
     */
    @NotNull(message = "biz_verdict_type表id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long verdictTypeId;

    /**
     * 裁决类型名称
     */
    @NotBlank(message = "裁决类型名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String verdictTypeName;

    /**
     * 维度名称
     */
    @NotBlank(message = "维度名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 维度编码
     */
    @NotBlank(message = "维度编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String code;

    /**
     * 数据来源（01人工填写 02业务表）
     */
    @NotBlank(message = "数据来源（01人工填写 02业务表）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String resource;

    /**
     * 字典编码
     */
    private String dictcode;

    /**
     * 设备属性编码
     */
    private String columnName;

    /**
     * 维度限长
     */
    @NotNull(message = "维度限长不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer length;

    /**
     * 维度取值类型（01 文本 02 数值）
     */
    @NotBlank(message = "维度取值类型（01 文本 02 数值）不能为空", groups = { AddGroup.class, EditGroup.class })
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


}
