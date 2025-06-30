package com.ruoyi.app.deployChess.mapper;
import com.ruoyi.business.domain.bo.BizScenarioRecordHistoryBo;
import com.ruoyi.business.domain.bo.BizVerdictRecordHistoryBo;
public interface MapChessMapper {
    void delVoById(BizVerdictRecordHistoryBo dto);

    void delVoByIdC(BizScenarioRecordHistoryBo dto);
}
