package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决表管理业务对象 biz_verdict_rule
 *
 * @author ruoyi
 * @date 2024-02-19
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizVerdictRuleBo extends BaseEntity {

    /**
     *
     */
    private Long id;

    /**
     * 类型,10:苏军反装甲 20:苏军软目标 30:美军反装甲 40:美军反软目标
     */
    private Long verdictType;

    /**
     * 我方武器名称
     */
    private String selfWeaponName;

    /**
     * 对方武器名称
     */
    private String targetWeaponName;

    /**
     * 距离
     */
    private Long distance;

    /**
     * 攻击分数
     */
    private Long attackScore;

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

    private Long historyId;

    private int[] dices;


}
