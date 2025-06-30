package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 裁决表管理对象 biz_verdict_rule
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_verdict_rule")
public class BizVerdictRule extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "id")
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
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;

    @TableField(exist = false)
    private Long historyId;

}
