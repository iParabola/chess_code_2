package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 间瞄裁决规则业务对象 biz_indirect_verdict_rule
 *
 * @author ruoyi
 * @date 2024-02-20
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizIndirectVerdictRuleBo extends BaseEntity {

    /**
     * 
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 棋子编号
     */
    @NotBlank(message = "棋子编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String chessPiecesNumber;

    /**
     * 棋子名称
     */
    @NotBlank(message = "棋子名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String chessPiecesName;

    /**
     * 支援状态,10:建制内 20:直接支援 30:专设
     */
    @NotNull(message = "支援状态,10:建制内 20:直接支援 30:专设不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer supportStatus;

    /**
     * 射击类型,10:待命 20:临机目标 30:优先目标
     */
    @NotNull(message = "射击类型,10:待命 20:临机目标 30:优先目标不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer fireType;

    /**
     * 延迟回合
     */
    @NotNull(message = "延迟回合不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long delayRound;

    /**
     * 弹着六宫格攻击分数
     */
    @NotNull(message = "弹着六宫格攻击分数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long fallAttackScore;

    /**
     * 相邻六宫格攻击分数
     */
    @NotNull(message = "相邻六宫格攻击分数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long neighborAttackScore;

    /**
     * 外围六宫格攻击分数
     */
    @NotNull(message = "外围六宫格攻击分数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long peripheryAttackScore;

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
