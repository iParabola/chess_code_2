package com.ruoyi.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;
import java.util.List;

/**
 * 裁决记录回合管理视图对象 biz_verdict_record_round
 *
 * @author ruoyi
 * @date 2024-03-07
 */
@Data
@ExcelIgnoreUnannotated
public class BizVerdictRecordRoundVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 裁决记录表id
     */
    @ExcelProperty(value = "裁决记录表id")
    private Long verdictRecordId;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long userId;

    /**
     * 棋子阵营id
     */
    @ExcelProperty(value = "棋子阵营id")
    private Long campId;

    /**
     * 回合数
     */
    @ExcelProperty(value = "回合数")
    private Long chessRound;

    /**
     * 回合所在阶段,1直瞄射击2机动阶段3解除压制阶段4间瞄射击阶段
     */
    @ExcelProperty(value = "回合所在阶段,1直瞄射击2机动阶段3解除压制阶段4间瞄射击阶段")
    private Integer roundPeriod;

    /**
     * 0未提交1已提交待裁决2已裁决
     */
    @ExcelProperty(value = "0未提交1已提交待裁决2已裁决")
    private Integer status;


    private String coverUrl;

    private String delFlag;

    private String textIns;

    private List<BizVerdictRecordHistoryVo> historyVoList;


}
