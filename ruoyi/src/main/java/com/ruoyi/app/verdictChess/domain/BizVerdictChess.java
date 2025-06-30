package com.ruoyi.app.verdictChess.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决棋子表对象 biz_verdict_chess
 *
 * @author ruoyi
 * @date 2024-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_verdict_chess")
public class BizVerdictChess extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 
     */
    @TableId(value = "id")
    private Long id;
    /**
     * product表id
     */
    private Long productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 裁决类型id
     */
    private Long verdictTypeId;
    /**
     * 裁决类型编码
     */
    private String verdictTypeCode;
    /**
     * 棋子类型id
     */
    private Long chessTypeId;
    /**
     * 棋子类型名称
     */
    private String chessTypeName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 扩展字段
     */
    private String ext;
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;

}
