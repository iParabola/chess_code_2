package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 间瞄记录业务对象 biz_indirect_record
 *
 * @author ruoyi
 * @date 2024-02-20
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizIndirectRecordBo extends BaseEntity {

    /**
     * 
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 裁决记录表id
     */
    @NotNull(message = "裁决记录表id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long verdictRecordId;

    /**
     * 目标阵营id
     */
    @NotNull(message = "目标阵营id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long targetCampId;

    /**
     * 坐标
     */
    @NotBlank(message = "坐标不能为空", groups = { AddGroup.class, EditGroup.class })
    private String targetCoordinate;

    /**
     * 裁决记录历史表id
     */
    @NotNull(message = "裁决记录历史表id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long verdictRecordHistoryId;

    /**
     * 间瞄裁决规则表id
     */
    @NotNull(message = "间瞄裁决规则表id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long indirectVerdictRuleId;

    /**
     * 生效回合
     */
    @NotNull(message = "生效回合不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long effectChessRound;

    /**
     * 状态,0:初始化 10:处理中 20:处理失败 30:处理成功
     */
    @NotNull(message = "状态,0:初始化 10:处理中 20:处理失败 30:处理成功不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer status;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;

    /**
     * 扩展字段
     */
    @NotBlank(message = "扩展字段不能为空", groups = { AddGroup.class, EditGroup.class })
    private String ext;

    /**
     * 创建人
     */
    @NotBlank(message = "创建人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String createdBy;


}
