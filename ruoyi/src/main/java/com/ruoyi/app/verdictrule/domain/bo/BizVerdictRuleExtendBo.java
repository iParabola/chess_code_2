package com.ruoyi.app.verdictrule.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 裁决表管理业务对象 biz_verdict_rule
 *
 * @author ruoyi
 * @date 2024-08-18
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizVerdictRuleExtendBo extends TreeEntity<BizVerdictRuleExtendBo> {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
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
    @NotBlank(message = "参数名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String paramName;

    /**
     * 取值来源：01  字典选项 02 人工填写 03 设备属性 04 裁决规则 05 计算公式 06 算子
     */
    private String resource;

    /**
     * 是否目标棋子
     * */
    private String isTargetChessPieces;


    /**
     * 字典编码
     */
    private String dictcode;

    /**
     * 裁决规则信息
     * */
    private String verdictRuleInfo;
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
    /**
     * 是否显示
     * */
    private String isShow;
    /**
     * 备注
     */
    private String remark;

    /**
     * 扩展字段
     */
    private String ext;


}
