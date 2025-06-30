package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 阶段管理业务对象 biz_scenario_stage
 *
 * @author ruoyi
 * @date 2024-03-01
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizScenarioStageBo extends BaseEntity {

    /**
     *
     */
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


}
