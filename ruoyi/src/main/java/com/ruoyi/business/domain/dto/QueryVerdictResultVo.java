package com.ruoyi.business.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.business.domain.vo.BizVerdictRecordVo;
import lombok.Data;

@Data
public class QueryVerdictResultVo extends BizVerdictRecordVo {


    private String firstCampName;

//    private String firstJudgeUserName;
//
//    private String firstCommanderUserName;
//
//    private String secondCommanderUserName;


    private Long secondCampId;

    private String secondCampName;

//    private String  secondJudgeUserName;

    private ChessRoundActionEffectVo settlement;

    private String stageStr;

    private Long mapId;

}
