package com.ruoyi.app.verdicttype.domain.bo;

import com.ruoyi.common.core.domain.TreeEntity;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 裁决类型业务对象 biz_verdict_type
 *
 * @author ruoyi
 * @date 2024-08-10
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizVerdictTypeBo extends TreeEntity<BizVerdictTypeBo> {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 名称，按每套产品的裁决规则分类，如：通信、干扰、直瞄、间瞄等
     */
    @NotBlank(message = "名称", groups = {AddGroup.class, EditGroup.class})
    private String name;

    /**
     * 编码
     */
    @NotBlank(message = "编码不能为空", groups = {AddGroup.class, EditGroup.class})
    private String code;

    /**
     * product表id
     */
    private Long productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 扩展字段
     */
    private String ext;

    /**
     * 扩展字段
     */
    private String delFlag;

}
