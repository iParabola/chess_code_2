package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 地图坐标业务对象 biz_arbiter_map_coordinate
 *
 * @author ruoyi
 * @date 2024-02-20
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizArbiterMapCoordinateBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 地图id
     */
    @NotNull(message = "地图id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long arbiterMapId;

    /**
     * 地图名称
     */
    @NotBlank(message = "地图名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String arbiterMapName;

    /**
     * 坐标
     */
    @NotBlank(message = "坐标不能为空", groups = {AddGroup.class, EditGroup.class})
    private String coordinate;

    /**
     * 横坐标
     */
    @NotBlank(message = "横坐标不能为空", groups = {AddGroup.class, EditGroup.class})
    private String abscissa;

    /**
     * 纵坐标
     */
    @NotBlank(message = "纵坐标不能为空", groups = {AddGroup.class, EditGroup.class})
    private String ordinate;

    /**
     * 地图高程
     */
    @NotNull(message = "地图高程不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long elevation;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = {AddGroup.class, EditGroup.class})
    private String remark;

    /**
     * 扩展字段
     */
    @NotBlank(message = "扩展字段不能为空", groups = {AddGroup.class, EditGroup.class})
    private String ext;
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
    /**
     * 地形影响行动值
     */
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
