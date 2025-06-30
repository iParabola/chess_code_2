package com.ruoyi.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 想定管理视图对象 biz_scenario
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@Data
@ExcelIgnoreUnannotated
public class BizScenarioVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 编号
     */
    @ExcelProperty(value = "编号")
    private String scenarioNumber;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称")
    private String scenarioName;

    /**
     * 内容图片
     */
    @ExcelProperty(value = "内容图片")
    private String cover;

    /**
     * 内容
     */
    @ExcelProperty(value = "内容")
    private String scenarioContent;

    /**
     * arbiter_map table id
     */
    @ExcelProperty(value = "arbiter_map table id")
    private Long arbiterMapId;

    /**
     * 地图名称
     */
    @ExcelProperty(value = "地图名称")
    private String arbiterMapName;

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
     * 比赛回合数限制
     */
    @ExcelProperty(value = "比赛回合数限制")
    private Long chessRoundLimit;

    private String stageConfig;

}
