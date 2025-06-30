package com.ruoyi.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 间瞄打击效果视图对象 biz_indirect_blow_effect
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Data
@ExcelIgnoreUnannotated
public class BizIndirectBlowEffectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 攻击分数
     */
    @ExcelProperty(value = "攻击分数")
    private Long attackScore;

    /**
     * 骰子分数
     */
    @ExcelProperty(value = "骰子分数")
    private Long diceScore;

    /**
     * 打击效果
     */
    @ExcelProperty(value = "打击效果")
    private String blowEffect;

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
