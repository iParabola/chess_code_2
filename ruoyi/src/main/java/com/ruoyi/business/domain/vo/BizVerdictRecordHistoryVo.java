package com.ruoyi.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 裁决记录历史复盘视图对象 biz_verdict_record_history
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Data
@ExcelIgnoreUnannotated
public class BizVerdictRecordHistoryVo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
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

    private Long roundId;

    /**
     * 棋子编号
     */
    @ExcelProperty(value = "棋子编号")
    private String chessPiecesNumber;

    /**
     * 当前坐标
     */
    @ExcelProperty(value = "当前坐标")
    private String selfCoordinate;

    /**
     * 目标坐标
     */
    @ExcelProperty(value = "目标坐标")
    private String targetCoordinate;

    /**
     * 行动模式,10:移动 20:攻击
     */
    @ExcelProperty(value = "行动模式,10:移动 20:攻击")
    private Integer actionMode;

    /**
     * 是否结束回合
     */
    @ExcelProperty(value = "是否结束回合")
    private Integer isEndRound;

    /**
     * 回合数
     */
    @ExcelProperty(value = "回合数")
    private Long chessRound;

    /**
     * 回合所在阶段,10:甲方直瞄 20:乙方直瞄 30:甲方机动 40:乙方机动
     */
    @ExcelProperty(value = "回合所在阶段,10:甲方直瞄 20:乙方直瞄 30:甲方机动 40:乙方机动")
    private Integer roundPeriod;

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

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String actionDesc;

    /**
     * 所有棋子位置及状态信息
     */
    @ExcelProperty(value = "所有棋子位置及状态信息")
    private String chessPiecesInfo;


    private String chessPiecesCover;


    private String  attackResult;

    private String targetChessPiecesNumber;

    private Integer attackScore;

    private Integer status;
    private String targetOffset;

    private String selfOffset;


    private String actionInfoArray;

    private String moveInfo;

}
