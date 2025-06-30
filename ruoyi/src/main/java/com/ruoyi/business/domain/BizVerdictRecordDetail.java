package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决记录详情对象 biz_verdict_record_detail
 *
 * @author ruoyi
 * @date 2024-02-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_verdict_record_detail")
public class BizVerdictRecordDetail extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     *
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
    /**
     * 棋子编号
     */
    private String selfChessPiecesNumber;
    /**
     * 棋子名称
     */
    private String selfChessPiecesName;
    /**
     * chess_pieces_type表id
     */
    private Long selfChessPiecesTypeId;
    /**
     * 棋子类型名称
     */
    private String selfChessPiecesTypeName;
    /**
     * 坐标
     */
    private String selfCoordinate;
    /**
     * 10:正常 20:击毁
     */
    private Integer selfStatus;
    /**
     * 棋子编号
     */
    private String targetChessPiecesNumber;
    /**
     * 棋子名称
     */
    private String targetChessPiecesName;
    /**
     * chess_pieces_type表id
     */
    private Long targetChessPiecesTypeId;
    /**
     * 棋子类型名称
     */
    private String targetChessPiecesTypeName;
    /**
     * 坐标
     */
    private String targetCoordinate;
    /**
     * 10:正常 20:击毁
     */
    private Integer targetStatus;
    /**
     * 骰子数量
     */
    private Long diceAmount;
    /**
     * 是否通视,0:否 1:是
     */
    private Integer isVisible;
    /**
     * 骰子值,多个用英文逗号分隔
     */
    private String diceValue;
    /**
     * 攻击等级
     */
    private String attackLevel;
    /**
     * 裁决结果
     */
    private String verdictResult;
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

    private String targetOffset;

    private String selfOffset;

}
