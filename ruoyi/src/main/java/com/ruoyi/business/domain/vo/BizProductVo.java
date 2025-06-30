package com.ruoyi.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 产品管理视图对象 biz_product
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@Data
@ExcelIgnoreUnannotated
public class BizProductVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称")
    private String productName;

    /**
     * 作者
     */
    @ExcelProperty(value = "作者")
    private String productAuthor;

    /**
     * 版本号
     */
    @ExcelProperty(value = "版本号")
    private String productVersion;

    /**
     * 出版单位
     */
    @ExcelProperty(value = "出版单位")
    private String publicInstitution;

    /**
     * 出版日期
     */
    @ExcelProperty(value = "出版日期")
    private String publicDate;

    /**
     * 上架日期
     */
    @ExcelProperty(value = "上架日期")
    private String shelfDate;

    /**
     * 是否上架
     */
    @ExcelProperty(value = "是否上架", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "shelf_flag")
    private Integer isShelf;

    /**
     * 封面
     */
    @ExcelProperty(value = "封面")
    private String cover;

    /**
     * 简介
     */
    @ExcelProperty(value = "简介")
    private String instruction;

    /**
     * 规则
     */
    @ExcelProperty(value = "规则")
    private String rule;

    /**
     * chess_pieces表关联id
     */
    @ExcelProperty(value = "chess_pieces表关联id")
    private String chessPiecesIds;

    /**
     * 裁决类型
     */
    @ExcelProperty(value = "裁决类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "verdict_type")
    private Long verdictType;

    /**
     * scenario表关联id
     */
    @ExcelProperty(value = "scenario表关联id")
    private String scenarioIds;

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
     * 模式选择
     */
    @ExcelProperty(value = "模式选择", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "mode_type")
    private Long mode;

    /**
     * 级别确定
     */
    @ExcelProperty(value = "级别确定", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "level_type")
    private Long level;

    /**
     * 训练科目
     */
    @ExcelProperty(value = "训练科目", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "subject_type")
    private String trainSubject;


}
