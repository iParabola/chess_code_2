package com.ruoyi.app.verdictrule.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 裁决表管理视图对象 biz_verdict_rule
 *
 * @author ruoyi
 * @date 2024-08-18
 */
@Data
@ExcelIgnoreUnannotated
public class BizVerdictRuleExtendVo implements Serializable {

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
     * 裁决阶段：01~04  定、判、裁、定
     */
    @ExcelProperty(value = "裁决阶段：01~04  定、判、裁、定")
    private String stage;

    /**
     * 参数名称
     */
    @ExcelProperty(value = "参数名称")
    private String paramName;

    /**
     * 取值来源：01  字典选项 02 人工填写 03 设备属性 04 裁决规则 05 计算公式
     */
    @ExcelProperty(value = "取值来源：01  字典选项 02 人工填写 03 设备属性 04 裁决规则 05 计算公式")
    private String resource;

    /**
     * 字典编码
     */
    @ExcelProperty(value = "字典编码")
    private String dictcode;

    /**
     * 设备属性编码
     */
    @ExcelProperty(value = "设备属性编码")
    private String attrCode;

    /**
     * 计算公式
     */
    @ExcelProperty(value = "计算公式")
    private String formula;

    /**
     * 参数取值
     */
    @ExcelProperty(value = "参数取值")
    private String paramValue;

    /***
        * 是否目标单位
        */
    private String isTargetChessPieces;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 父参数
     */
    @ExcelProperty(value = "父参数")
    private Long parentId;
    /**
        * 是否显示
        * */
    private String isShow;
    /**
     * 扩展字段
     */
    @ExcelProperty(value = "扩展字段")
    private String ext;


}
