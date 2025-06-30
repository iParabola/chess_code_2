package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 想定管理对象 biz_scenario
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_scenario")
public class BizScenario extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 编号
     */
    private String scenarioNumber;
    /**
     * 名称
     */
    private String scenarioName;
    /**
     * 内容图片
     */
    private String cover;
    /**
     * 内容
     */
    private String scenarioContent;
    /**
     * arbiter_map table id
     */
    private Long arbiterMapId;
    /**
     * 地图名称
     */
    private String arbiterMapName;
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
     * 比赛回合数限制
     */
    private Long chessRoundLimit;
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;

    private String stageConfig;


}
