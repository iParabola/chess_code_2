package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决记录分数管理业务对象 biz_verdict_record_score
 *
 * @author ruoyi
 * @date 2024-03-04
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizVerdictRecordScoreBo extends BaseEntity {

    /**
     *
     */
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


    private String standard;

    private String campName;

    private Integer type;

    private String typeString;
}
