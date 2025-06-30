package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 武器管理业务对象 biz_weapon
 *
 * @author ruoyi
 * @date 2024-02-19
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizWeaponBo extends BaseEntity {

    /**
     * 
     */
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


}
