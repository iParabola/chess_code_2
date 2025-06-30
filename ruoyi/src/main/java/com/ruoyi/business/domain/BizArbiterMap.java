package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地图管理对象 biz_arbiter_map
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_arbiter_map")
public class BizArbiterMap extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 地图名称
     */
    private String mapName;
    /**
     * 横规格
     */
    private Long transverseDimension;
    /**
     * 纵规格
     */
    private Long longitudinalDimension;
    /**
     * 起始坐标
     */
    private String initialCoordinate;
    /**
     * 起始横坐标
     */
    private String initialAbscissa;
    /**
     * 起始纵坐标
     */
    private String initialOrdinate;
    /**
     * 备注
     */
    private String remark;
    /**
     * 扩展字段
     */
    private String ext;
    /**
     * product表id
     */
    private Long productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 地图图片
     */
    private String mapCover;
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;

    /**
     * 图片宽度
     */
    private BigDecimal mapWidth;
    /**
     * 图片高度
     */
    private BigDecimal mapHeight;
    /**
     * 六边形半径
     */
    private BigDecimal mapRadius;
    /**
     * top偏移量
     */
    private BigDecimal mapTop;
    /**
     * left偏移量
     */
    private BigDecimal mapLeft;

}
