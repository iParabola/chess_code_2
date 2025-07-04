package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决记录对象 biz_verdict_record
 *
 * @author ruoyi
 * @date 2024-02-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_verdict_record")
public class BizVerdictRecord extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 邀请人用户id
     */
    private Long inviterUserId;
    /**
     * 甲方用户id
     */
    private Long firstUserId;
    /**
     * 甲方棋子阵营id
     */
    private Long firstCampId;
    /**
     * 甲方指挥员id
     */

//    private Long firstCommanderUserId;
    /**
     * 乙方指挥员id
     */

//    private Long secondCommanderUserId;
    /**
     * 甲方最终得分
     */
    private Long firstScore;
    /**
     * 乙方用户id
     */
    private Long secondUserId;
    /**
     * 乙方棋子阵营id
     */
    private Long secondCampId;
    /**
     * 乙方最终得分
     */
    private Long secondScore;
    /**
     * 想定id
     */
    private Long scenarioId;
    /**
     * chess_pieces表关联id
     */
    private String chessPiecesIds;
    /**
     * 邀请码
     */
    private String inviteCode;
    /**
     * 备注
     */
    private String remark;
    /**
     * 扩展字段
     */
    private String ext;
    /**
     * 观战模式,10:观战 20:不观战
     */
    private Long watchMode;
    /**
     * 比赛状态,10:初始化 20:部署中 25:一方部署完成 30:双方部署完成 40:比赛进行中 50:比赛完成
     */
    private Long status;
    /**
     * 回合数
     */
    private Long chessRound;
    /**
     * 回合所在阶段,10:甲方直瞄 20:乙方直瞄 30:甲方机动 40:乙方机动
     */
    private Integer roundPeriod;
    /**
     * 比赛回合数限制
     */
    private Long chessRoundLimit;
    /**
     * 比赛结果,10:甲方胜 20:平 30:乙方胜利
     */
    private Long verdictResult;
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;

//    private Long firstJudgeUserId;
//    private Long secondJudgeUserId;
}
