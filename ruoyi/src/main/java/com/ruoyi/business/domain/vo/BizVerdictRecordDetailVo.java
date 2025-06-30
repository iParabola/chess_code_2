package com.ruoyi.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 裁决记录详情视图对象 biz_verdict_record_detail
 *
 * @author ruoyi
 * @date 2024-02-21
 */
@Data
@ExcelIgnoreUnannotated
public class BizVerdictRecordDetailVo implements Serializable {

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
     * 棋子编号
     */
    @ExcelProperty(value = "棋子编号")
    private String selfChessPiecesNumber;

    /**
     * 棋子名称
     */
    @ExcelProperty(value = "棋子名称")
    private String selfChessPiecesName;

    /**
     * chess_pieces_type表id
     */
    @ExcelProperty(value = "chess_pieces_type表id")
    private Long selfChessPiecesTypeId;

    /**
     * 棋子类型名称
     */
    @ExcelProperty(value = "棋子类型名称")
    private String selfChessPiecesTypeName;

    /**
     * 坐标
     */
    @ExcelProperty(value = "坐标")
    private String selfCoordinate;

    /**
     * 10:正常 20:击毁
     */
    @ExcelProperty(value = "10:正常 20:击毁")
    private Integer selfStatus;

    /**
     * 棋子编号
     */
    @ExcelProperty(value = "棋子编号")
    private String targetChessPiecesNumber;

    /**
     * 棋子名称
     */
    @ExcelProperty(value = "棋子名称")
    private String targetChessPiecesName;

    /**
     * chess_pieces_type表id
     */
    @ExcelProperty(value = "chess_pieces_type表id")
    private Long targetChessPiecesTypeId;

    /**
     * 棋子类型名称
     */
    @ExcelProperty(value = "棋子类型名称")
    private String targetChessPiecesTypeName;

    /**
     * 坐标
     */
    @ExcelProperty(value = "坐标")
    private String targetCoordinate;

    /**
     * 10:正常 20:击毁
     */
    @ExcelProperty(value = "10:正常 20:击毁")
    private Integer targetStatus;

    /**
     * 骰子数量
     */
    @ExcelProperty(value = "骰子数量")
    private Long diceAmount;

    /**
     * 是否通视,0:否 1:是
     */
    @ExcelProperty(value = "是否通视,0:否 1:是")
    private Integer isVisible;

    /**
     * 骰子值,多个用英文逗号分隔
     */
    @ExcelProperty(value = "骰子值,多个用英文逗号分隔")
    private String diceValue;

    /**
     * 攻击等级
     */
    @ExcelProperty(value = "攻击等级")
    private String attackLevel;

    /**
     * 裁决结果
     */
    @ExcelProperty(value = "裁决结果")
    private String verdictResult;

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

    private String targetOffset;

    private String selfOffset;
}
