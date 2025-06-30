package com.ruoyi.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 裁决维度定义视图对象 biz_verdict_dimension
 *
 * @author ruoyi
 * @date 2024-09-09
 */
@Data
@ExcelIgnoreUnannotated
public class BizVerdictDimensionVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * product表id
     */
    @ExcelProperty(value = "product表id")
    private Long productId;

    /**
     * 产品名称
     */
    @ExcelProperty(value = "产品名称")
    private String productName;

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
     * 维度名称
     */
    @ExcelProperty(value = "维度名称")
    private String name;

    /**
     * 维度编码
     */
    @ExcelProperty(value = "维度编码")
    private String code;

    /**
     * 数据来源（01人工填写 02业务表）
     */
    @ExcelProperty(value = "数据来源", converter = ExcelDictConvert.class)
    private String resource;

    /**
     * 字典编码
     */
    @ExcelProperty(value = "字典编码")
    private String dictcode;

    /**
     * 业务表|字段
     */
    @ExcelProperty(value = "业务表|字段")
    private String columnName;

    /**
     * 维度限长
     */
    @ExcelProperty(value = "维度限长")
    private Integer length;

    /**
     * 维度取值类型（01 文本 02 数值）
     */
    @ExcelProperty(value = "维度取值类型", converter = ExcelDictConvert.class)
    private String valueType;

    /**
     * 是否分类（0 否 1 是）
     */
    @ExcelProperty(value = "是否分类", converter = ExcelDictConvert.class)
    private String isClass;

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
