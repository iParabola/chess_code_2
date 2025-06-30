package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 裁决记录业务对象 biz_verdict_record
 *
 * @author ruoyi
 * @date 2024-02-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizVerdictRecordBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 邀请人用户id
     */
    @NotNull(message = "邀请人用户id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long inviterUserId;

    /**
     * 甲方用户id
     */
    @NotNull(message = "甲方用户id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long firstUserId;

    /**
     * 甲方棋子阵营id
     */
    @NotNull(message = "甲方棋子阵营id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long firstCampId;

    /**
     * 乙方用户id
     */
    @NotNull(message = "乙方用户id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long secondUserId;

    /**
     * 乙方棋子阵营id
     */
    @NotNull(message = "乙方棋子阵营id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long secondCampId;

    /**
     * 想定id
     */
    @NotNull(message = "想定id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long scenarioId;

    /**
     * chess_pieces表关联id
     */
    @NotBlank(message = "chess_pieces表关联id不能为空", groups = {AddGroup.class, EditGroup.class})
    private String chessPiecesIds;

    /**
     * 邀请码
     */
    @NotBlank(message = "邀请码不能为空", groups = {AddGroup.class, EditGroup.class})
    private String inviteCode;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = {AddGroup.class, EditGroup.class})
    private String remark;

    /**
     * 扩展字段
     */
    @NotBlank(message = "扩展字段不能为空", groups = {AddGroup.class, EditGroup.class})
    private String ext;

    /**
     * 观战模式,10:观战 20:不观战
     */
    @NotNull(message = "观战模式,10:观战 20:不观战不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long watchMode;

    /**
     * 比赛状态,10:初始化 20:部署中 25:一方部署完成 30:双方部署完成 40:比赛进行中 50:比赛完成
     */
    @NotNull(message = "比赛状态,10:初始化 20:部署中 25:一方部署完成 30:双方部署完成 40:比赛进行中 50:比赛完成不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long status;

    /**
     * 回合数
     */
    @NotNull(message = "回合数不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long chessRound;

    /**
     * 回合所在阶段,10:甲方直瞄 20:乙方直瞄 30:甲方机动 40:乙方机动
     */
    @NotNull(message = "回合所在阶段,10:甲方直瞄 20:乙方直瞄 30:甲方机动 40:乙方机动不能为空", groups = {AddGroup.class, EditGroup.class})
    private Integer roundPeriod;

    /**
     * 比赛回合数限制
     */
    @NotNull(message = "比赛回合数限制不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long chessRoundLimit;

    /**
     * 比赛结果,10:甲方胜 20:平 30:乙方胜利
     */
    @NotNull(message = "比赛结果,10:甲方胜 20:平 30:乙方胜利不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long verdictResult;


    private Long userId;


    private String chessPiecesNumber;


    private String type;

    private String stageParam;

    private Long campId;

    private Long firstJudgeUserId;
    private Long secondJudgeUserId;
    private Long productId;

    private Long firstCommanderUserId;
    private Long secondCommanderUserId;

}
