package com.ruoyi.app.verdictrule.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 裁决表管理对象 biz_verdict_rule
 *
 * @author ruoyi
 * @date 2024-08-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_verdict_rule")
public class BizVerdictRuleExtend extends TreeEntity<BizVerdictRuleExtend> {

    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * biz_verdict_type表id
     */
    private Long verdictTypeId;
    /**
     * 裁决类型名称
     */
    private String verdictTypeName;
    /**
     * product表id
     */
    private Long productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 裁决阶段：01~04  定、判、裁、定
     */
    private String stage;
    /**
     * 参数名称
     */
    private String paramName;
    /**
     * 取值来源：01  字典选项 02 人工填写 03 设备属性 04 裁决规则 05 计算公式 06 算子
     */
    private String resource;
    /**
     * 字典编码
     */
    private String dictcode;
    /**
     * 设备属性编码
     */
    private String attrCode;
    /**
     * 计算公式
     */
    private String formula;
    /**
     * 参数取值
     */
    private String paramValue;

    /***
     * 是否目标单位
     */
    private String isTargetChessPieces;
    /**
     * 备注
     */
    private String remark;
    /**
     * 扩展字段
     */
    private String ext;

    /**
     * 是否显示
     * */
    private String isShow;
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;

}
