package com.ruoyi.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 间瞄裁决规则视图对象 biz_indirect_verdict_rule
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Data
@ExcelIgnoreUnannotated
public class BizIndirectVerdictRuleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 棋子编号
     */
    @ExcelProperty(value = "棋子编号")
    private String chessPiecesNumber;

    /**
     * 棋子名称
     */
    @ExcelProperty(value = "棋子名称")
    private String chessPiecesName;

    /**
     * 支援状态,10:建制内 20:直接支援 30:专设
     */
    @ExcelProperty(value = "支援状态,10:建制内 20:直接支援 30:专设")
    private Integer supportStatus;

    /**
     * 射击类型,10:待命 20:临机目标 30:优先目标
     */
    @ExcelProperty(value = "射击类型,10:待命 20:临机目标 30:优先目标")
    private Integer fireType;

    /**
     * 延迟回合
     */
    @ExcelProperty(value = "延迟回合")
    private Long delayRound;

    /**
     * 弹着六宫格攻击分数
     */
    @ExcelProperty(value = "弹着六宫格攻击分数")
    private Long fallAttackScore;

    /**
     * 相邻六宫格攻击分数
     */
    @ExcelProperty(value = "相邻六宫格攻击分数")
    private Long neighborAttackScore;

    /**
     * 外围六宫格攻击分数
     */
    @ExcelProperty(value = "外围六宫格攻击分数")
    private Long peripheryAttackScore;

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
