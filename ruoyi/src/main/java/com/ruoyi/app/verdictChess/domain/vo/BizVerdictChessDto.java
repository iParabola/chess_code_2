package com.ruoyi.app.verdictChess.domain.vo;

import lombok.Data;
import java.io.Serializable;

/**
 * 裁决棋子表视图对象 biz_verdict_chess
 *
 * @author ruoyi
 * @date 2024-08-21
 */
@Data
public class BizVerdictChessDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long verdictChessId;

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
     * 裁决类型name
     */
    private String verdictTypeName;

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


}
