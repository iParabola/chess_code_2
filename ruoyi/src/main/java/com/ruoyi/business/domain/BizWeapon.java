package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 武器管理对象 biz_weapon
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_weapon")
public class BizWeapon extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 武器名称
     */
    private String weaponName;
    /**
     * 武器射程
     */
    private Long weaponRange;
    /**
     * 弹药量
     */
    private Long weaponQuantity;
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
     * 删除标记
     */
    @TableLogic
    private String delFlag;

}
