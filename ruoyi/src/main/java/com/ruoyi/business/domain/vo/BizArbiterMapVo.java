package com.ruoyi.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.business.domain.BizArbiterMapCoordinate;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

import java.io.Serializable;
import java.util.List;

/**
 * 地图管理视图对象 biz_arbiter_map
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@Data
@ExcelIgnoreUnannotated
public class BizArbiterMapVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 地图名称
     */
    @ExcelProperty(value = "地图名称")
    private String mapName;

    /**
     * 横规格
     */
    @ExcelProperty(value = "横规格")
    private Long transverseDimension;

    /**
     * 纵规格
     */
    @ExcelProperty(value = "纵规格")
    private Long longitudinalDimension;

    /**
     * 起始坐标
     */
    @ExcelProperty(value = "起始坐标")
    private String initialCoordinate;

    /**
     * 起始横坐标
     */
    @ExcelProperty(value = "起始横坐标")
    private String initialAbscissa;

    /**
     * 起始纵坐标
     */
    @ExcelProperty(value = "起始纵坐标")
    private String initialOrdinate;

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
     * 地图图片
     */
    @ExcelProperty(value = "地图图片")
    private String mapCover;


    private List<BizArbiterMapCoordinate> coordinateList;

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
