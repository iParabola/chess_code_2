package com.ruoyi.app.verdictChess.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 裁决棋子表视图对象 biz_verdict_chess
 *
 * @author ruoyi
 * @date 2024-08-21
 */
@Data
@ExcelIgnoreUnannotated
public class BizVerdictChessVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * product表id
     */
    @ExcelProperty(value = "product表id")
    private Long productId;

    /**
     * 产品名称
     */
    @ExcelProperty(value = "产品名称")
    private String productName;

    /**
     * 裁决类型id
     */
    @ExcelProperty(value = "裁决类型id")
    private Long verdictTypeId;

    /**
     * 裁决类型编码
     */
    @ExcelProperty(value = "裁决类型编码")
    private String verdictTypeCode;

    /**
     * 棋子类型id
     */
    @ExcelProperty(value = "棋子类型id")
    private Long chessTypeId;

    /**
     * 棋子类型名称
     */
    @ExcelProperty(value = "棋子类型名称")
    private String chessTypeName;

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
