package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 间瞄记录对象 biz_indirect_record
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_indirect_record")
public class BizIndirectRecord extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 裁决记录表id
     */
    private Long verdictRecordId;
    /**
     * 目标阵营id
     *
     */
    private Long targetCampId;
    /**
     * 坐标
     */
    private String targetCoordinate;
    /**
     * 裁决记录历史表id
     */
    private Long verdictRecordHistoryId;
    /**
     * 间瞄裁决规则表id
     */
    private Long indirectVerdictRuleId;
    /**
     * 生效回合
     */
    private Long effectChessRound;
    /**
     * 状态,0:初始化 10:处理中 20:处理失败 30:处理成功
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 扩展字段
     */
    private String ext;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;

}
