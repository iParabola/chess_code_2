package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 地图坐标对象 biz_arbiter_map_coordinate
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_arbiter_map_coordinate")
public class BizArbiterMapCoordinate extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 地图id
     */
    private Long arbiterMapId;
    /**
     * 地图名称
     */
    private String arbiterMapName;
    /**
     * 坐标
     */
    private String coordinate;
    /**
     * 横坐标
     */
    private String abscissa;
    /**
     * 纵坐标
     */
    private String ordinate;
    /**
     * 地图高程
     */
    private Long elevation;
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

    /**
     * 高程颜色
     */
    private String elevationColor;
    /**
     * 地形颜色
     */
    private String terrainColor;
    /**
     * 地形贴图
     */
    private String terrainCover;
    private BigDecimal terrainActionValue;

    private BigDecimal terrainVisibleRange;


    private String offset;

    private String  pointsCoordinate;


    private String  centerCoordinate;

    private long terrainId;

    private String bridgeFlag;

    private Long roadTypeId;

    private String roadName;

    private String  toRoadNumber;


    private Integer roadWidth;

    private String roadColor;

}
