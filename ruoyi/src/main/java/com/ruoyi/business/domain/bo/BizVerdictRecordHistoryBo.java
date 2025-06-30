package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决记录历史复盘业务对象 biz_verdict_record_history
 *
 * //@author ruoyi
 * //@date 2024-02-20
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizVerdictRecordHistoryBo extends BaseEntity {

    /**
     * 主键id
     */
    //@NotNull(message = "主键id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 裁决记录表id
     */
    //@NotNull(message = "裁决记录表id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long verdictRecordId;

    /**
     * 用户id
     */
    //@NotNull(message = "用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    private Long roundId;

    /**
     * 棋子阵营id
     */
    //@NotNull(message = "棋子阵营id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long campId;

    /**
     * 棋子编号
     */
    //@NotBlank(message = "棋子编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String chessPiecesNumber;

    /**
     * 当前坐标
     */
    //@NotBlank(message = "当前坐标不能为空", groups = { AddGroup.class, EditGroup.class })
    private String selfCoordinate;

    /**
     * 目标坐标
     */
    //@NotBlank(message = "目标坐标不能为空", groups = { AddGroup.class, EditGroup.class })
    private String targetCoordinate;

    /**
     * 行动模式,10:移动 20:攻击
     */
    //@NotNull(message = "行动模式,10:移动 20:攻击不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer actionMode;

    /**
     * 是否结束回合
     */
    //@NotNull(message = "是否结束回合不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer isEndRound;

    /**
     * 回合数
     */
    //@NotNull(message = "回合数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long chessRound;

    /**
     * 回合所在阶段,10:甲方直瞄 20:乙方直瞄 30:甲方机动 40:乙方机动
     */
    //@NotNull(message = "回合所在阶段,10:甲方直瞄 20:乙方直瞄 30:甲方机动 40:乙方机动不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer roundPeriod;

    /**
     * 备注
     */
    //@NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;

    /**
     * 扩展字段
     */
    //@NotBlank(message = "扩展字段不能为空", groups = { AddGroup.class, EditGroup.class })
    private String ext;

    /**
     * 描述
     */
    //@NotBlank(message = "描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String actionDesc;

    /**
     * 所有棋子位置及状态信息
     */
    //@NotBlank(message = "所有棋子位置及状态信息不能为空", groups = { AddGroup.class, EditGroup.class })
    private String chessPiecesInfo;

    private String  attackResult;

    private String targetChessPiecesNumber;

    private Integer attackScore;

    private Integer status;

    private String targetOffset;

    private String selfOffset;


    private String actionInfoArray;

    private String moveInfo;
}
