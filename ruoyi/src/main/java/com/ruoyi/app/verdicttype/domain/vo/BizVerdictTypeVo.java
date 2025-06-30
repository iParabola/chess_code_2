package com.ruoyi.app.verdicttype.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 裁决类型视图对象 biz_verdict_type
 *
 * @author ruoyi
 * @date 2024-08-10
 */
@Data
@ExcelIgnoreUnannotated
public class BizVerdictTypeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 名称，按每套产品的裁决规则分类，如：通信、干扰、直瞄、间瞄等
     */
    @ExcelProperty(value = "名称，按每套产品的裁决规则分类，如：通信、干扰、直瞄、间瞄等")
    private String name;

    /**
     * 编码
     */
    @ExcelProperty(value = "编码")
    private String code;

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
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 扩展字段
     */
    @ExcelProperty(value = "扩展字段")
    private String ext;

    /**
     * 父类别ID
     */
    @ExcelProperty(value = "父类别ID")
    private Long parentId;

    /**
     * 扩展字段
     */
    private String delFlag;

}
