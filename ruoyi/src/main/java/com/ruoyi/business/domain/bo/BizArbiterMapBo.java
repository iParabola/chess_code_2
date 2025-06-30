package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地图管理业务对象 biz_arbiter_map
 *
 * @author ruoyi
 * @date 2024-02-19
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizArbiterMapBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
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
