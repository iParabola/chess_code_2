package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 场景创建对象 biz_scenario_create
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_scenario_create")
public class BizScenarioCreate extends BaseEntity {

    private static final long serialVersionUID=1L;

    @TableId(value = "scenario_id")
    private Long id;
    private Date createTime;
    private Date updateTime;
    private String chessPiecesIds;
    private Long arbiterMapId;
    private String scenarioName;
    private String stageConfig;
    private Long chessRoundLimit;
    private Long productId;
}
