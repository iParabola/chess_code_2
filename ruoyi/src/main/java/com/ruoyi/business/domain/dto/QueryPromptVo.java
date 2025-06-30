package com.ruoyi.business.domain.dto;

import com.ruoyi.business.domain.BizVerdictRecordHistory;
import lombok.Data;

import java.util.List;

@Data
public class QueryPromptVo {

    private List<String> actionDescList;

    private Integer selfAttackScore;

    private Integer selfDiceScore;

    private Integer targetAttackScore;

    private Integer targetDiceScore;

    private List<BizVerdictRecordHistory> verdictRecordHistoryList;
//    private List<Map<String,>>

}
