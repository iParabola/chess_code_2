package com.ruoyi.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 裁决维度定义值域视图对象 biz_verdict_dimension_value
 *
 * @author ruoyi
 * @date 2024-09-09
 */
@Data
@ExcelIgnoreUnannotated
public class BizVerdictDimensionValueVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * product表id
     */
    @ExcelProperty(value = "verdict_dimension表id")
    private Long dimensionId;

    /**
     * 产品名称
     */
    @ExcelProperty(value = "维度名称")
    private String dimensionName;

    /**
     * 维度值
     */
    @ExcelProperty(value = "维度值")
    private String dimensionValue;

    /**
     * 值数组坐标
     */
    @ExcelProperty(value = "值数组坐标")
    private Integer dimensionIndex;

    /**
     * 分类名称
     */
    @ExcelProperty(value = "分类名称（字典type）", converter = ExcelDictConvert.class)
    private String className;

    /**
     * 字典编码
     */
    @ExcelProperty(value = "分类编码（字典编码）")
    private String classCode;

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
