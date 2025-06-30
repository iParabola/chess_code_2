package com.ruoyi.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 棋子属性定义视图对象 biz_chess_pieces_attribute
 *
 * @author ruoyi
 * @date 2024-09-02
 */
@Data
@ExcelIgnoreUnannotated
public class BizChessPiecesAttributeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 棋子属性名称
     */
    @ExcelProperty(value = "棋子属性名称")
    private String name;

    /**
     * 棋子属性编码
     */
    @ExcelProperty(value = "棋子属性编码")
    private String code;

    /**
     * 产品名称
     */
    @ExcelProperty(value = "产品名称")
    private String productName;

    /**
     * 类型
     */
    @ExcelProperty(value = "类型")
    private String type;


}
