package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决记录回合管理对象 biz_verdict_record_round
 *
 * @author ruoyi
 * @date 2024-03-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_verdict_record_round")
public class BizVerdictRecordRound extends BaseEntity {

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
     * 用户id
     */
    private Long userId;
    /**
     * 棋子阵营id
     */
    private Long campId;
    /**
     * 回合数
     */
    private Long chessRound;
    /**
     * 回合所在阶段,1直瞄射击2机动阶段3解除压制阶段4间瞄射击阶段
     */
    private Integer roundPeriod;
    /**
     * 0未提交1已提交待裁决2已裁决
     */
    private Integer status;
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;


    private String coverUrl;

    private String textIns;
}
