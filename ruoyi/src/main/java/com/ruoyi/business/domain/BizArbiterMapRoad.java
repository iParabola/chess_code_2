package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地图路对象 biz_arbiter_map_road
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_arbiter_map_road")
public class BizArbiterMapRoad extends BaseEntity {

    private static final long serialVersionUID=1L;

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
     * 名称
     */
    private String name;
    /**
     * 背景色
     */
    private String color;
    /**
     * 贴图
     */
    private String cover;
    /**
     * 地形影响行动值(+影响值，河流行动值+1，则行动值-（1+1），公里-0.5，则行动值-（1+ -0.5）)
     */
    private Double terrainActionValue;
    /**
     * 地形影响视野范围(乘系数)
     */
    private Double terrainVisibleRange;
    /**
     * 中心点坐标
     */
    private String centersCoordinat;
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
    @TableLogic
    private String delFlag;


    private Integer roadWidth;

}
