package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 阶段管理对象 biz_scenario_stage
 *
 * @author ruoyi
 * @date 2024-03-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_scenario_stage")
public class BizScenarioStage extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 阶段名称
     */
    private String stageName;
    /**
     * 排序
     */
    private Long sort;
    /**
     * 所属想定
     */
    private Long scenarioId;
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;

}
