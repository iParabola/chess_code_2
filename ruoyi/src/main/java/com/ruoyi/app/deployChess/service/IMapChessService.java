package com.ruoyi.app.deployChess.service;
import com.ruoyi.business.domain.bo.BizScenarioRecordHistoryBo;
import com.ruoyi.business.domain.bo.BizVerdictRecordHistoryBo;
public interface IMapChessService {
    String delByDto(BizVerdictRecordHistoryBo dto);

    String delByDtoC(BizScenarioRecordHistoryBo dto);
}
