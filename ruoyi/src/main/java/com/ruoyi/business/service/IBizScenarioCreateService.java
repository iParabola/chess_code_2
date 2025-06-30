package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizScenarioCreate;
import com.ruoyi.business.domain.BizScenarioRecordChessPieces;
import com.ruoyi.business.domain.bo.*;
import com.ruoyi.business.domain.dto.CreatVerdictRoomInfoDto;
import com.ruoyi.business.domain.dto.QueryAllScenarioChessPiecesInfoDto;
import com.ruoyi.business.domain.vo.BizScenarioCreateVo;
import com.ruoyi.business.domain.vo.BizScenarioVo;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface IBizScenarioCreateService {


    BizScenarioCreate createScenario(BizScenarioCreateBo bo);

    BizScenarioCreate deleteScenario(BizScenarioCreateBo bo);

    String saveScenarioChessPieces(BizChessPiecesBo saveScenarioChessPiecesDto);

    String deployScenarioChessPieces (BizScenarioRecordHistoryBo dto);

    List<BizScenarioRecordChessPieces> queryAllChessPiecesInfo(QueryAllScenarioChessPiecesInfoDto dto);

    String CreatVerdictRoomInfoDto(CreatVerdictRoomInfoDto dto);

    List<BizScenarioCreateVo> queryScenarios();

    Long getChessRoundLimitById(Long id);

    Long getArbiterMapIdById(Long scenarioId);

    String getStageConfigById(Long scenarioId);
}
