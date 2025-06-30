package com.ruoyi.app.verdicttype.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 裁决类型对象 biz_verdict_type
 * @date 2024-08-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_verdict_type")
public class BizVerdictType extends TreeEntity<BizVerdictType> {

    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 名称，按每套产品的裁决规则分类，如：通信、干扰、直瞄、间瞄等
     */
    private String name;
    /**
     * 编码
     */
    private String code;
    /**
     * product表id
     */
    private Long productId;
    /**
     * 产品名称
     */
    private String productName;
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
