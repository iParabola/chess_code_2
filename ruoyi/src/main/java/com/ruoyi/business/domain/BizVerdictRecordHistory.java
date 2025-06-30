package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决记录历史复盘对象 biz_verdict_record_history
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_verdict_record_history")
public class BizVerdictRecordHistory extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 裁决记录表id
     */
    private Long verdictRecordId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 棋子阵营id
     */
    private Long campId;

    private Long roundId;
    /**
     * 棋子编号
     */
    private String chessPiecesNumber;
    /**
     * 当前坐标
     */
    private String selfCoordinate;
    /**
     * 目标坐标
     */
    private String targetCoordinate;
    /**
     * 行动模式,10:移动 20:攻击
     */
    private Integer actionMode;
    /**
     * 是否结束回合
     */
    private Integer isEndRound;
    /**
     * 回合数
     */
    private Long chessRound;
    /**
     * 回合所在阶段,10:甲方直瞄 20:乙方直瞄 30:甲方机动 40:乙方机动
     */
    private Integer roundPeriod;
    /**
     * 备注
     */
    private String remark;
    /**
     * 扩展字段
     */
    private String ext;
    /**
     * 描述
     */
    private String actionDesc;
    /**
     * 所有棋子位置及状态信息
     */
    private String chessPiecesInfo;
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;


    private String  attackResult;

    private String targetChessPiecesNumber;

    private Integer attackScore;

    private Integer status;

    private String targetOffset;

    private String selfOffset;


    private String actionInfoArray;


    private String moveInfo;

    @TableField(exist = false)
    private Long maneuverValue;

}
