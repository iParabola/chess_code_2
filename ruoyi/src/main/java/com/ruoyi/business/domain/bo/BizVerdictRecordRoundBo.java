package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决记录回合管理业务对象 biz_verdict_record_round
 *
 * @author ruoyi
 * @date 2024-03-07
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizVerdictRecordRoundBo extends BaseEntity {

    /**
     *
     */
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


    private String coverUrl;


    private String textIns;
}
