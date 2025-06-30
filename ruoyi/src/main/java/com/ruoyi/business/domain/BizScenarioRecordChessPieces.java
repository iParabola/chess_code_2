package com.ruoyi.business.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 场景记录棋子对象 biz_scenario_record_chess_pieces
 */
@Data
@TableName("biz_scenario_record_chess_pieces")
public class BizScenarioRecordChessPieces extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long scenarioRecordId;
    private String chessPiecesNumber;
    private Long chessPiecesTypeId;
    private String chessPiecesTypeName;
    private Long chessPiecesCampId;
    private String chessPiecesCampName;
    private String chessPiecesName;
    private Integer vitaValue;
    private Integer maneuverValue;
    private Integer maneuverType;
    private Integer attackValue;
    private Integer closeAttackValue;
    private Integer defenseValue;
    private Integer trafficCapacityLevel;
    private Integer trafficCapacityDistance;
    private Integer closeReconCapacityLevel;
    private Integer closeReconCapacityDistance;
    private Integer photoelectricReconCapacityLevel;
    private Integer photoelectricReconCapacityDistance;
    private Integer groundReconCapacityLevel;
    private Integer groundReconCapacityDistance;
    private Integer weapon1AttackValue;
    private Integer weapon1AttackDistance;
    private Integer weapon2AttackValue;
    private Integer weapon2AttackDistance;
    private Integer antiAirAttackValue;
    private Integer antiAirAttackDistance;
    private Integer communicationCapabilityLevel;
    private Integer commandControlCapabilityLevel;
    private Integer visibleRange;
    private Integer disturbValue;
    private Integer disturbDistance;
    private String status;
    private String offset;
    private String coordinate;
    private String fromOffset;
    private String fromCoordinate;
    private String remark;
    private String ext;
    private Boolean isHide;
    private String delFlag;
    private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;
    private Integer roundActionPoint;
    @TableField(exist = false)
    private String chessPiecesCover;
}
