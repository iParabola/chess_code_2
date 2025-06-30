package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 间瞄打击效果对象 biz_indirect_blow_effect
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_indirect_blow_effect")
public class BizIndirectBlowEffect extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 攻击分数
     */
    private Long attackScore;
    /**
     * 骰子分数
     */
    private Long diceScore;
    /**
     * 打击效果
     */
    private String blowEffect;
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

}
