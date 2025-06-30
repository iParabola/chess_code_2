package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决打击效果业务对象 biz_verdict_blow_effect
 *
 * @author ruoyi
 * @date 2024-02-20
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizVerdictBlowEffectBo extends BaseEntity {

    /**
     * 
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 类型,10:苏军反装甲 20:苏军软目标 30:美军反装甲 40:美军反软目标
     */
    @NotNull(message = "类型,10:苏军反装甲 20:苏军软目标 30:美军反装甲 40:美军反软目标不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long verdictType;

    /**
     * 攻击分数
     */
    @NotNull(message = "攻击分数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long attackScore;

    /**
     * 骰子分数
     */
    @NotNull(message = "骰子分数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long diceScore;

    /**
     * 打击效果
     */
    @NotBlank(message = "打击效果不能为空", groups = { AddGroup.class, EditGroup.class })
    private String blowEffect;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;

    /**
     * 扩展字段
     */
    @NotBlank(message = "扩展字段不能为空", groups = { AddGroup.class, EditGroup.class })
    private String ext;


}
