package com.ruoyi.app.deployChess.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.app.deployChess.mapper.MapChessMapper;
import com.ruoyi.app.deployChess.service.IMapChessService;
import com.ruoyi.business.domain.bo.BizScenarioRecordHistoryBo;
import com.ruoyi.business.domain.bo.BizVerdictRecordHistoryBo;
import com.ruoyi.business.domain.vo.BizVerdictRecordVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class MapChessServiceImpl implements IMapChessService {
    private final MapChessMapper mapChessMapper;
    @Override
    public String delByDto(BizVerdictRecordHistoryBo dto){
        log.info("deployChessPieces req:{}", JSON.toJSONString(dto));
        mapChessMapper.delVoById(dto);
        return "";
    }

    @Override
    public String delByDtoC(BizScenarioRecordHistoryBo dto){
        log.info("deployChessPieces req:{}", JSON.toJSONString(dto));
        mapChessMapper.delVoByIdC(dto);
        return "";
    }
}
