package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品管理对象 biz_product
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_product")
public class BizProduct extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 名称
     */
    private String productName;
    /**
     * 作者
     */
    private String productAuthor;
    /**
     * 版本号
     */
    private String productVersion;
    /**
     * 出版单位
     */
    private String publicInstitution;
    /**
     * 出版日期
     */
    private String publicDate;
    /**
     * 上架日期
     */
    private String shelfDate;
    /**
     * 是否上架
     */
    private Integer isShelf;
    /**
     * 封面
     */
    private String cover;
    /**
     * 简介
     */
    private String instruction;
    /**
     * 规则
     */
    private String rule;
    /**
     * chess_pieces表关联id
     */
    private String chessPiecesIds;
    /**
     * 裁决类型
     */
    private Long verdictType;
    /**
     * scenario表关联id
     */
    private String scenarioIds;
    /**
     * 备注
     */
    private String remark;
    /**
     * 扩展字段
     */
    private String ext;
    /**
     * 模式选择
     */
    private Long mode;
    /**
     * 级别确定
     */
    private Long level;
    /**
     * 训练科目
     */
    private String trainSubject;
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;

}
