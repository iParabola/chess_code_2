package com.ruoyi.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.business.domain.dto.RoadVo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 地图坐标视图对象 biz_arbiter_map_coordinate
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Data
@ExcelIgnoreUnannotated
public class BizArbiterMapCoordinateVo implements Serializable {

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
     * 地图名称
     */
    @ExcelProperty(value = "地图名称")
    private String arbiterMapName;

    /**
     * 坐标
     */
    @ExcelProperty(value = "坐标")
    private String coordinate;

    /**
     * 横坐标
     */
    @ExcelProperty(value = "横坐标")
    private String abscissa;

    /**
     * 纵坐标
     */
    @ExcelProperty(value = "纵坐标")
    private String ordinate;

    /**
     * 地图高程
     */
    @ExcelProperty(value = "地图高程")
    private Long elevation;

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
     * 高程颜色
     */
    @ExcelProperty(value = "高程颜色")
    private String elevationColor;
    /**
     * 地形颜色
     */
    @ExcelProperty(value = "地形颜色")
    private String terrainColor;
    /**
     * 地形贴图
     */
    @ExcelProperty(value = "地形贴图")
    private String terrainCover;
    /**
     * 地形影响行动值
     */
    private BigDecimal terrainActionValue;

    private BigDecimal terrainVisibleRange;

    @ExcelProperty(value = "偏移坐标")
    private String offset;

    private String  pointsCoordinate;


    private String  centerCoordinate;

    private long terrainId;

    private String bridgeFlag;

    private Long roadTypeId;

    private String roadTypeName;

    private String roadName;


    private String  toRoadNumber;


    private Integer roadWidth;

    private String roadColor;


    private List<RoadVo> roadVoList;

    private String terrainName;
}
