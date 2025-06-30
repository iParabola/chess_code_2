package com.ruoyi.app.verdictRuleJudgeCriteria.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 裁决判断前置条件视图对象 biz_verdict_rule_judge_criteria
 *
 * @author ccc
 * @date 2024-09-18
 */
@Data
@ExcelIgnoreUnannotated
public class BizVerdictRuleJudgeCriteriaVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * biz_verdict_type表id
     */
    @ExcelProperty(value = "biz_verdict_type表id")
    private Long verdictTypeId;

    /**
     * 裁决阶段：01~04  定、判、裁、定
     */
    @ExcelProperty(value = "裁决阶段：01~04  定、判、裁、定")
    private String stage;

    /**
     * 计算公式
     */
    @ExcelProperty(value = "计算公式")
    private String formula;


}
