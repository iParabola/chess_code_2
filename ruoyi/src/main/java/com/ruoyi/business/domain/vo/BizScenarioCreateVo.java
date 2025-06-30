package com.ruoyi.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ExcelIgnoreUnannotated
public class BizScenarioCreateVo implements Serializable {
    private static final long serialVersionUID=1L;

    private Long id;

    private String chessPiecesIds;

    private String scenarioName;

    private Long arbiterMapId;

    @ExcelProperty(value = "比赛回合数限制")
    private Long chessRoundLimit;

    private String stageConfig;

}
