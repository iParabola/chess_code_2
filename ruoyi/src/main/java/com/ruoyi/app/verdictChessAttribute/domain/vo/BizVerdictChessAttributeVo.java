package com.ruoyi.app.verdictChessAttribute.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 裁决棋子属性表视图对象 biz_verdict_chess_attribute
 *
 * @author ruoyi
 * @date 2024-08-21
 */
@Data
@ExcelIgnoreUnannotated
public class BizVerdictChessAttributeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * chess_pieces_type表id
     */
    @ExcelProperty(value = "chess_pieces_type表id")
    private Long chessTypeId;

    /**
     * chess_pieces_attribute表id
     */
    @ExcelProperty(value = "chess_pieces_attribute表id")
    private Long chessAttributeId;

    /**
     * 属性名称
     */
    @ExcelProperty(value = "属性名称")
    private String attrName;

    /**
     * 属性编码
     */
    @ExcelProperty(value = "属性编码")
    private String attrCode;

    /**
     * 属性类型：01 引用裁决规则维度（选项，单选或多选）、02 关联属性、03 常规
     */
    @ExcelProperty(value = "属性类型：01 引用裁决规则维度", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "选=项，单选或多选")
    private String attrType;

    /**
     * 裁决规则维度编码
     */
    @ExcelProperty(value = "裁决规则维度编码")
    private String ruleDimensionCode;

    /**
     * 关联属性id
     */
    @ExcelProperty(value = "关联属性id")
    private Long attributeId;

    /**
     * 属性值
     */
    @ExcelProperty(value = "属性值")
    private String attrValue;

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
