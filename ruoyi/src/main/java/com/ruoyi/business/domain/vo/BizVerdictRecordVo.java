package com.ruoyi.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 裁决记录视图对象 biz_verdict_record
 *
 * @author ruoyi
 * @date 2024-02-21
 */
@Data
@ExcelIgnoreUnannotated
public class BizVerdictRecordVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 邀请人用户id
     */
    @ExcelProperty(value = "邀请人用户id")
    private Long inviterUserId;

    /**
     * 甲方用户id
     */
    @ExcelProperty(value = "甲方用户id")
    private Long firstUserId;

    /**
     * 甲方棋子阵营id
     */
    @ExcelProperty(value = "甲方棋子阵营id")
    private Long firstCampId;
    /**
     * 甲方最终得分
     */
    private Long firstScore;
    /**
     * 甲方指挥员id
     */
    private Long firstCommanderUserId;
    /**
     * 乙方指挥员id
     */
    private Long secondCommanderUserId;
    /**
     * 乙方用户id
     */
    @ExcelProperty(value = "乙方用户id")
    private Long secondUserId;

    /**
     * 乙方棋子阵营id
     */
    @ExcelProperty(value = "乙方棋子阵营id")
    private Long secondCampId;
    /**
     * 乙方最终得分
     */
    private Long secondScore;
    /**
     * 想定id
     */
    @ExcelProperty(value = "想定id")
    private Long scenarioId;

    /**
     * chess_pieces表关联id
     */
    @ExcelProperty(value = "chess_pieces表关联id")
    private String chessPiecesIds;

    /**
     * 邀请码
     */
    @ExcelProperty(value = "邀请码")
    private String inviteCode;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 扩展字段
     */
    @ExcelProperty(value = "扩展字段")
    private String ext;

    /**
     * 观战模式,10:观战 20:不观战
     */
    @ExcelProperty(value = "观战模式,10:观战 20:不观战")
    private Long watchMode;

    /**
     * 比赛状态,10:初始化 20:部署中 25:一方部署完成 30:双方部署完成 40:比赛进行中 50:比赛完成
     */
    @ExcelProperty(value = "比赛状态,10:初始化 20:部署中 25:一方部署完成 30:双方部署完成 40:比赛进行中 50:比赛完成")
    private Long status;

    /**
     * 回合数
     */
    @ExcelProperty(value = "回合数")
    private Long chessRound;

    /**
     * 回合所在阶段,10:甲方直瞄 20:乙方直瞄 30:甲方机动 40:乙方机动
     */
    @ExcelProperty(value = "回合所在阶段,10:甲方直瞄 20:乙方直瞄 30:甲方机动 40:乙方机动")
    private Integer roundPeriod;

    /**
     * 比赛回合数限制
     */
    @ExcelProperty(value = "比赛回合数限制")
    private Long chessRoundLimit;

    /**
     * 比赛结果,10:甲方胜 20:平 30:乙方胜利
     */
    @ExcelProperty(value = "比赛结果,10:甲方胜 20:平 30:乙方胜利")
    private Long verdictResult;

    private String firstUserName;

    private String secondUserName;
    private Long firstJudgeUserId;
    private Long secondJudgeUserId;


}
