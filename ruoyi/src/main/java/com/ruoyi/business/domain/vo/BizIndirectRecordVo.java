package com.ruoyi.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 间瞄记录视图对象 biz_indirect_record
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Data
@ExcelIgnoreUnannotated
public class BizIndirectRecordVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 裁决记录表id
     */
    @ExcelProperty(value = "裁决记录表id")
    private Long verdictRecordId;

    /**
     * 目标阵营id
     */
    @ExcelProperty(value = "目标阵营id")
    private Long targetCampId;

    /**
     * 坐标
     */
    @ExcelProperty(value = "坐标")
    private String targetCoordinate;

    /**
     * 裁决记录历史表id
     */
    @ExcelProperty(value = "裁决记录历史表id")
    private Long verdictRecordHistoryId;

    /**
     * 间瞄裁决规则表id
     */
    @ExcelProperty(value = "间瞄裁决规则表id")
    private Long indirectVerdictRuleId;

    /**
     * 生效回合
     */
    @ExcelProperty(value = "生效回合")
    private Long effectChessRound;

    /**
     * 状态,0:初始化 10:处理中 20:处理失败 30:处理成功
     */
    @ExcelProperty(value = "状态,0:初始化 10:处理中 20:处理失败 30:处理成功")
    private Integer status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 扩展字段
     */
    @ExcelProperty(value = "扩展字段")
    private String ext;

    /**
     * 创建人
     */
    @ExcelProperty(value = "创建人")
    private String createdBy;


}
