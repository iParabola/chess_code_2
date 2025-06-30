package com.ruoyi.business.domain.vo;

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
 * @date 2024-02-19
 */
@Data
@ExcelIgnoreUnannotated
public class BizVerdictRuleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 类型,10:苏军反装甲 20:苏军软目标 30:美军反装甲 40:美军反软目标
     */
    @ExcelProperty(value = "类型,10:苏军反装甲 20:苏军软目标 30:美军反装甲 40:美军反软目标", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "verdict_type")
    private Long verdictType;

    /**
     * 我方武器名称
     */
    @ExcelProperty(value = "我方武器名称")
    private String selfWeaponName;

    /**
     * 对方武器名称
     */
    @ExcelProperty(value = "对方武器名称")
    private String targetWeaponName;

    /**
     * 距离
     */
    @ExcelProperty(value = "距离")
    private Long distance;

    /**
     * 攻击分数
     */
    @ExcelProperty(value = "攻击分数")
    private Long attackScore;

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
     * product表id
     */
    @ExcelProperty(value = "product表id")
    private Long productId;

    /**
     * 产品名称
     */
    @ExcelProperty(value = "产品名称")
    private String productName;


    private String attackScores;


}
