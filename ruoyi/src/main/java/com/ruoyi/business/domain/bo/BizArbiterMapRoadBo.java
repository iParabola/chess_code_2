package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地图路业务对象 biz_arbiter_map_road
 *
 * @author ruoyi
 * @date 2024-03-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizArbiterMapRoadBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 地图id
     */
    @NotNull(message = "地图id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long arbiterMapId;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 背景色
     */
    @NotBlank(message = "背景色不能为空", groups = { AddGroup.class, EditGroup.class })
    private String color;

    /**
     * 贴图
     */
    @NotBlank(message = "贴图不能为空", groups = { AddGroup.class, EditGroup.class })
    private String cover;

    /**
     * 地形影响行动值(+影响值，河流行动值+1，则行动值-（1+1），公里-0.5，则行动值-（1+ -0.5）)
     */
    @NotNull(message = "地形影响行动值(+影响值，河流行动值+1，则行动值-（1+1），公里-0.5，则行动值-（1+ -0.5）)不能为空", groups = { AddGroup.class, EditGroup.class })
    private Double terrainActionValue;

    /**
     * 地形影响视野范围(乘系数)
     */
    @NotNull(message = "地形影响视野范围(乘系数)不能为空", groups = { AddGroup.class, EditGroup.class })
    private Double terrainVisibleRange;

    /**
     * 中心点坐标
     */
    @NotBlank(message = "中心点坐标不能为空", groups = { AddGroup.class, EditGroup.class })
    private String centersCoordinat;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;

    /**
     * 扩展字段
     */
    @NotBlank(message = "扩展字段不能为空", groups = { AddGroup.class, EditGroup.class })
    private String ext;
    private Integer roadWidth;

}
