package com.ruoyi.business.domain.vo;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 裁决记录分数管理视图对象 biz_verdict_record_score
 *
 * @author ruoyi
 * @date 2024-03-04
 */
@Data
@ExcelIgnoreUnannotated
public class BizVerdictRecordScoreVo implements Serializable {

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
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;

    /**
     * 棋子阵营id
     */
    @ExcelProperty(value = "棋子阵营id")
    private Long campId;

    /**
     * 用户名
     */
    @ExcelProperty(value = "用户名")
    private String userName;

    /**
     * 回合
     */
    @ExcelProperty(value = "回合")
    private Long chessRound;

    /**
     * 打分项
     */
    @ExcelProperty(value = "打分项")
    private String content;

    /**
     * 分数
     */
    @ExcelProperty(value = "分数")
    private Integer score;

    private String standard;

    private String campName;
    private Integer type;

    private String scores;
}
