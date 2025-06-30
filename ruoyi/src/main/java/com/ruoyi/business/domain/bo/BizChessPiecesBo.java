package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 棋子管理业务对象 biz_chess_pieces
 *
 * @author ruoyi
 * @date 2024-02-19
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizChessPiecesBo extends BaseEntity {

    /**
     *
     */
    private Long id;

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
     * 棋子图片
     */
    private String chessPiecesCover;

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
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 扩展字段
     */
    private String ext;

    /**
     * product表id
     */
    private Long productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 武器装备
     */
    private String equipment;


    private String chessPiecesIds;


    private Long verdictRecordId;

    /**
     * 视野范围
     */
    private Integer visibleRange;

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

}
