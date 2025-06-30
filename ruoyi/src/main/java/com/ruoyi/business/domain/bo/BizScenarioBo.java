package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 想定管理业务对象 biz_scenario
 *
 * @author ruoyi
 * @date 2024-02-19
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizScenarioBo extends BaseEntity {

    /**
     *
     */
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

    private Long userId;


    private String stageConfig;


}
