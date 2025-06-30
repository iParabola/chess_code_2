package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决维度定义值域业务对象 biz_verdict_dimension_value
 *
 * @author ruoyi
 * @date 2024-09-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizVerdictDimensionValueBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * product表id
     */
    @NotNull(message = "verdict_dimension表id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long dimensionId;

    /**
     * 产品名称
     */
    @NotBlank(message = "维度名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String dimensionName;

    /**
     * 维度值
     */
    @NotBlank(message = "维度值不能为空", groups = { AddGroup.class, EditGroup.class })
    private String dimensionValue;

    /**
     * 值数组坐标
     */
    @NotNull(message = "值数组坐标不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer dimensionIndex;

    /**
     * 字典名称（type）
     */
    private String className;

    /**
     * 字典选项编码
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


}
