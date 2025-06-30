package com.ruoyi.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 地图河视图对象 biz_arbiter_map_river
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@Data
@ExcelIgnoreUnannotated
public class BizArbiterMapRiverVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 地图id
     */
    @ExcelProperty(value = "地图id")
    private Long arbiterMapId;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称")
    private String name;

    /**
     * 背景色
     */
    @ExcelProperty(value = "背景色")
    private String color;

    /**
     * 贴图
     */
    @ExcelProperty(value = "贴图")
    private String cover;

    /**
     * 相邻六角格坐标，从小到大排序
     */
    @ExcelProperty(value = "相邻六角格坐标，从小到大排序")
    private String hexsCoordinat;

    /**
     * 相邻六角格重合点坐标，从小到大排序
     */
    @ExcelProperty(value = "相邻六角格重合点坐标，从小到大排序")
    private String pointsCoordinate;

    /**
     * 是不是桥 0否 1是
     */
    @ExcelProperty(value = "是不是桥 0否 1是")
    private String bridgeFlag;

    /**
     * 是不是路 0否 1是
     */
    @ExcelProperty(value = "是不是路 0否 1是")
    private String roadFlag;

    /**
     * 地形影响行动值(+影响值，河流行动值+1，则行动值-（1+1），公里-0.5，则行动值-（1+ -0.5）)
     */
    @ExcelProperty(value = "地形影响行动值(+影响值，河流行动值+1，则行动值-", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1=+1")
    private Double terrainActionValue;

    /**
     * 地形影响视野范围(乘系数)
     */
    @ExcelProperty(value = "地形影响视野范围(乘系数)")
    private Double terrainVisibleRange;

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
