package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 裁决记录棋子对象 biz_verdict_record_chess_pieces
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_verdict_record_chess_pieces")
public class BizVerdictRecordChessPieces extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
     * 棋子编号
     */
    private String chessPiecesNumber;
    /**
     * chess_pieces_type表id
     */
    private Long chessPiecesTypeId;
    /**
     * 棋子类型名称
     */
    private String chessPiecesTypeName;
    /**
     * chess_pieces_camp表id
     */
    private Long chessPiecesCampId;
    /**
     * 棋子阵营名称
     */
    private String chessPiecesCampName;
    /**
     * 棋子名称
     */
    private String chessPiecesName;
    /**
     * 棋子生命值
     */
    private Long vitaValue;
    /**
     * 棋子机动值
     */
    private Long maneuverValue;
    /**
     * 棋子攻击值
     */
    private Long attackValue;
    /**
     * 棋子防御值
     */
    private Long defenseValue;
    /**
     * 10:正常 20:击毁
     */
    private String status;
    /**
     * 坐标
     */
    private String coordinate;
    /**
     * 备注
     */
    private String remark;
    /**
     * 扩展字段
     */
    private String ext;
    /**
     * 是否隐藏
     */
    private Integer isHide;
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;
    @TableField(exist = false)
    private String chessPiecesCover;
    @TableField(exist = false)
    private String statusDesc;
    @TableField(exist = false)
    private Boolean isCanBeRemoved;
    @TableField(exist = false)
    private Boolean isAction;
    @TableField(exist = false)
    private Long userId;

    /**
     * 视野范围
     */
    private Integer visibleRange;

    private String offset;


    /**
     * 棋子机动类型 轮子 履带
     */
    private Integer maneuverType;
    /**
     * 棋子近战攻击值
     */
    private Long closeAttackValue;
    /**
     * 棋子通行能力等级
     */
    private Long trafficCapacityLevel;
    /**
     * 棋子通行能力距离
     */
    private Long trafficCapacityDistance;
    /**
     * 棋子抵近侦察能力等级
     */
    private Long closeReconCapacityLevel;
    /**
     * 棋子抵近侦察能力距离
     */
    private Long closeReconCapacityDistance;
    /**
     * 棋子光电侦察能力等级
     */
    private Long photoelectricReconCapacityLevel;
    /**
     * 棋子光电侦察能力距离
     */
    private Long photoelectricReconCapacityDistance;
    /**
     * 棋子地面监视侦察能力等级
     */
    private Long groundReconCapacityLevel;
    /**
     * 棋子地面监视侦察能力距离
     */
    private Long groundReconCapacityDistance;
    /**
     * 棋子武器1攻击值
     */
    private Long weapon1AttackValue;
    /**
     * 棋子武器1距离
     */
    private Long weapon1AttackDistance;
    /**
     * 棋子武器2攻击值
     */
    private Long weapon2AttackValue;
    /**
     * 棋子武器2距离
     */
    private Long weapon2AttackDistance;
    /**
     * 棋子对空攻击值
     */
    private Long antiAirAttackValue;
    /**
     * 棋子对空距离
     */
    private Long antiAirAttackDistance;
    /**
     * 棋子通信能力等级
     */
    private Double communicationCapabilityLevel;
    /**
     * 棋子指控能力等级
     */
    private Long commandControlCapabilityLevel;

    /**
     * 干扰能力
     */
    private Long disturbValue;
    private Long disturbDistance;


    private String fromOffset;

    private String fromCoordinate;
    private String roundActionPoint;

}
