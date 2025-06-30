package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.BizScenarioRecordChessPieces;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.business.domain.dto.QueryAllChessPiecesInfoDto;
import com.ruoyi.business.domain.dto.QueryAllScenarioChessPiecesInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BizScenarioRecordChessPiecesMapper extends BaseMapper<BizScenarioRecordChessPieces> {
    BizScenarioRecordChessPieces queryByScenarioRecordIdAndChessPiecesNumber(@Param("scenarioRecordId")Long scenarioRecordId, @Param("chessPiecesNumber") String chessPiecesNumber);

    List<BizScenarioRecordChessPieces> queryByScenarioRecordId(@Param("scenarioRecordId")Long scenarioRecordId);
}
