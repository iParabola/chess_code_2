package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决记录分数管理对象 biz_verdict_record_score
 *
 * @author ruoyi
 * @date 2024-03-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_verdict_record_score")
public class BizVerdictRecordScore extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 裁决记录表id
     */
    private Long verdictRecordId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 棋子阵营id
     */
    private Long campId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 回合
     */
    private Long chessRound;
    /**
     * 打分项
     */
    private String content;
    /**
     * 分数
     */
    private Integer score;
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;

    private String standard;


    private String campName;

    private Integer type;
}
