package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 裁决记录棋子业务对象 biz_verdict_record_chess_pieces
 *
 * @author ruoyi
 * @date 2024-02-20
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizVerdictRecordChessPiecesBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 裁决记录表id
     */
    @NotNull(message = "裁决记录表id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long verdictRecordId;

    /**
     * 棋子编号
     */
    @NotBlank(message = "棋子编号不能为空", groups = {AddGroup.class, EditGroup.class})
    private String chessPiecesNumber;

    /**
     * chess_pieces_type表id
     */
    @NotNull(message = "chess_pieces_type表id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long chessPiecesTypeId;

    /**
     * 棋子类型名称
     */
    @NotBlank(message = "棋子类型名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String chessPiecesTypeName;

    /**
     * chess_pieces_camp表id
     */
    @NotNull(message = "chess_pieces_camp表id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long chessPiecesCampId;

    /**
     * 棋子阵营名称
     */
    @NotBlank(message = "棋子阵营名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String chessPiecesCampName;

    /**
     * 棋子名称
     */
    @NotBlank(message = "棋子名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String chessPiecesName;

    /**
     * 棋子生命值
     */
    @NotNull(message = "棋子生命值不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long vitaValue;

    /**
     * 棋子机动值
     */
    @NotNull(message = "棋子机动值不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long maneuverValue;

    /**
     * 棋子攻击值
     */
    @NotNull(message = "棋子攻击值不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long attackValue;

    /**
     * 棋子防御值
     */
    @NotNull(message = "棋子防御值不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long defenseValue;

    /**
     * 10:正常 20:击毁
     */
    @NotNull(message = "10:正常 20:击毁不能为空", groups = {AddGroup.class, EditGroup.class})
    private String status;

    /**
     * 坐标
     */
    @NotBlank(message = "坐标不能为空", groups = {AddGroup.class, EditGroup.class})
    private String coordinate;

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
     * 是否隐藏
     */
    @NotNull(message = "是否隐藏不能为空", groups = {AddGroup.class, EditGroup.class})
    private Integer isHide;

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
}
