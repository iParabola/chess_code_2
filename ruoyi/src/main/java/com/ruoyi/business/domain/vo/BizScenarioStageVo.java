package com.ruoyi.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 阶段管理视图对象 biz_scenario_stage
 *
 * @author ruoyi
 * @date 2024-03-01
 */
@Data
@ExcelIgnoreUnannotated
public class BizScenarioStageVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 阶段名称
     */
    @ExcelProperty(value = "阶段名称")
    private String stageName;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    private Long sort;

    /**
     * 所属想定
     */
    @ExcelProperty(value = "所属想定")
    private Long scenarioId;


}
