package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 间瞄裁决规则对象 biz_indirect_verdict_rule
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_indirect_verdict_rule")
public class BizIndirectVerdictRule extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 棋子编号
     */
    private String chessPiecesNumber;
    /**
     * 棋子名称
     */
    private String chessPiecesName;
    /**
     * 支援状态,10:建制内 20:直接支援 30:专设
     */
    private Integer supportStatus;
    /**
     * 射击类型,10:待命 20:临机目标 30:优先目标
     */
    private Integer fireType;
    /**
     * 延迟回合
     */
    private Long delayRound;
    /**
     * 弹着六宫格攻击分数
     */
    private Long fallAttackScore;
    /**
     * 相邻六宫格攻击分数
     */
    private Long neighborAttackScore;
    /**
     * 外围六宫格攻击分数
     */
    private Long peripheryAttackScore;
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
