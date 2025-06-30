package com.ruoyi.app.verdictRuleValue.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 裁决规则标准值表视图对象 biz_verdict_rule_value
 *
 * @author ccc
 * @date 2024-09-13
 */
@Data
@ExcelIgnoreUnannotated
public class BizVerdictRuleValueVo implements Serializable {

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
     * 裁决类型名称
     */
    @ExcelProperty(value = "裁决类型名称")
    private String verdictTypeName;

    /**
     * biz_verdict_rule表id
     */
    @ExcelProperty(value = "biz_verdict_rule表id")
    private Long verdictRuleId;

    /**
     * 裁决参数名称
     */
    @ExcelProperty(value = "裁决参数名称")
    private String verdictParamName;

    /**
     * 维度值（数组）(表格体内容)
     */
    @ExcelProperty(value = "维度值", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "数=组")
    private String verdicRuleDimensionValue;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 扩展字段
     */
    @ExcelProperty(value = "扩展字段")
    private String ext;


}
