package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决记录详情业务对象 biz_verdict_record_detail
 *
 * @author ruoyi
 * @date 2024-02-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizVerdictRecordDetailBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 裁决记录表id
     */
    @NotNull(message = "裁决记录表id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long verdictRecordId;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     * 棋子阵营id
     */
    @NotNull(message = "棋子阵营id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long campId;

    /**
     * 棋子编号
     */
    @NotBlank(message = "棋子编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String selfChessPiecesNumber;

    /**
     * 棋子名称
     */
    @NotBlank(message = "棋子名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String selfChessPiecesName;

    /**
     * chess_pieces_type表id
     */
    @NotNull(message = "chess_pieces_type表id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long selfChessPiecesTypeId;

    /**
     * 棋子类型名称
     */
    @NotBlank(message = "棋子类型名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String selfChessPiecesTypeName;

    /**
     * 坐标
     */
    @NotBlank(message = "坐标不能为空", groups = { AddGroup.class, EditGroup.class })
    private String selfCoordinate;

    /**
     * 10:正常 20:击毁
     */
    @NotNull(message = "10:正常 20:击毁不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer selfStatus;

    /**
     * 棋子编号
     */
    @NotBlank(message = "棋子编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String targetChessPiecesNumber;

    /**
     * 棋子名称
     */
    @NotBlank(message = "棋子名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String targetChessPiecesName;

    /**
     * chess_pieces_type表id
     */
    @NotNull(message = "chess_pieces_type表id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long targetChessPiecesTypeId;

    /**
     * 棋子类型名称
     */
    @NotBlank(message = "棋子类型名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String targetChessPiecesTypeName;

    /**
     * 坐标
     */
    @NotBlank(message = "坐标不能为空", groups = { AddGroup.class, EditGroup.class })
    private String targetCoordinate;

    /**
     * 10:正常 20:击毁
     */
    @NotNull(message = "10:正常 20:击毁不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer targetStatus;

    /**
     * 骰子数量
     */
    @NotNull(message = "骰子数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long diceAmount;

    /**
     * 是否通视,0:否 1:是
     */
    @NotNull(message = "是否通视,0:否 1:是不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer isVisible;

    /**
     * 骰子值,多个用英文逗号分隔
     */
    @NotBlank(message = "骰子值,多个用英文逗号分隔不能为空", groups = { AddGroup.class, EditGroup.class })
    private String diceValue;

    /**
     * 攻击等级
     */
    @NotBlank(message = "攻击等级不能为空", groups = { AddGroup.class, EditGroup.class })
    private String attackLevel;

    /**
     * 裁决结果
     */
    @NotBlank(message = "裁决结果不能为空", groups = { AddGroup.class, EditGroup.class })
    private String verdictResult;

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


    private Integer actionMode;

    private String chessPiecesNumber;


    private Long chessRound;

    private Long roundPeriod;

    private String targetOffset;

    private String selfOffset;

    private String actionInfoArray;

    private String attackResult;

    private String moveInfo;

}
