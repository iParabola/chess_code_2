package com.ruoyi.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.business.domain.BizChessPieces;
import com.ruoyi.business.domain.BizScenarioCreate;
import com.ruoyi.business.domain.BizScenarioRecordChessPieces;
import com.ruoyi.business.domain.BizVerdictRecordChessPieces;
import com.ruoyi.business.domain.bo.BizChessPiecesBo;
import com.ruoyi.business.domain.bo.BizScenarioCreateBo;
import com.ruoyi.business.domain.bo.BizScenarioRecordHistoryBo;
import com.ruoyi.business.domain.dto.CreatVerdictRoomInfoDto;
import com.ruoyi.business.domain.dto.QueryAllScenarioChessPiecesInfoDto;
import com.ruoyi.business.domain.vo.BizScenarioCreateVo;
import com.ruoyi.business.domain.vo.BizScenarioVo;
import com.ruoyi.business.domain.vo.BizVerdictRecordChessPiecesVo;
import com.ruoyi.business.mapper.*;
import com.ruoyi.business.service.IBizChessPiecesService;
import com.ruoyi.business.service.IBizScenarioCreateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class BizScenarioCreateServiceImpl implements IBizScenarioCreateService {

    @Autowired
    private BizScenarioCreateMapper scenarioCreateMapper;

    @Autowired
    private BizScenarioRecordChessPiecesMapper scenarioRecordChessPiecesMapper;

    @Autowired
    private IBizChessPiecesService chessPiecesService;

    @Autowired
    private BizChessPiecesMapper chessPiecesMapper;

    @Autowired
    private BizScenarioCreateMapper baseMapper;



    @Autowired
    private  BizVerdictRecordChessPiecesMapper verdictRecordChessPiecesMapper;
    @Override
    public BizScenarioCreate createScenario(BizScenarioCreateBo bo) {
        // 创建新的 BizScenarioCreate 对象
        BizScenarioCreate scenarioCreate = new BizScenarioCreate();
        scenarioCreate.setChessPiecesIds(bo.getChessPiecesIds());
        scenarioCreate.setScenarioName(bo.getScenarioName());
        scenarioCreate.setArbiterMapId(bo.getArbiterMapId());
        scenarioCreate.setProductId(bo.getProductId());
        scenarioCreate.setChessRoundLimit(bo.getChessRoundLimit());
        // 将新的 BizScenarioCreate 对象插入到 scenario_create 表中
        scenarioCreateMapper.insert(scenarioCreate);
        Long arbiterMapId = scenarioCreateMapper.selectArbiterMapIdById(scenarioCreate.getId());
        scenarioCreate.setArbiterMapId(arbiterMapId);
        // 返回插入后的 BizScenarioCreate 对象，其中包含自动生成的 ID
        return scenarioCreate;
    }

    @Override
    public BizScenarioCreate deleteScenario(BizScenarioCreateBo bo) {
        Long scenarioId = Long.valueOf(bo.getScenarioId());
        scenarioCreateMapper.deleteById(scenarioId);
        return null;
    }

    @Override
    public String saveScenarioChessPieces(BizChessPiecesBo saveScenarioChessPiecesDto) {
        // 创建新的 BizScenarioCreate 对象
        BizScenarioCreate newScenarioCreate = new BizScenarioCreate();
        newScenarioCreate.setChessPiecesIds(saveScenarioChessPiecesDto.getChessPiecesIds());

        // 将新的 BizScenarioCreate 对象插入到 scenario_create 表中
        scenarioCreateMapper.insert(newScenarioCreate);

        return "Scenario 和棋子保存成功";
    }


    @Override
    public String CreatVerdictRoomInfoDto(CreatVerdictRoomInfoDto dto) {
        // 创建新的 BizVerdictRecord 对象
        List<BizScenarioRecordChessPieces> res = new ArrayList<>();
        res = scenarioRecordChessPiecesMapper.queryByScenarioRecordId(Long.valueOf(dto.getScenarioRecordId()));
        for(BizScenarioRecordChessPieces item : res){
            BizChessPieces chessPieces = chessPiecesService.queryByChessPiecesNumber(item.getChessPiecesNumber());
            BizVerdictRecordChessPiecesVo verdictRecordChessPieces = new BizVerdictRecordChessPiecesVo();
            verdictRecordChessPieces.setVerdictRecordId(Long.valueOf(dto.getVerdictRecordId()));
            verdictRecordChessPieces.setChessPiecesNumber(chessPieces.getChessPiecesNumber());
            verdictRecordChessPieces.setChessPiecesTypeId(chessPieces.getChessPiecesTypeId());
            verdictRecordChessPieces.setChessPiecesTypeName(chessPieces.getChessPiecesTypeName());
            verdictRecordChessPieces.setChessPiecesCampId(chessPieces.getChessPiecesCampId());
            verdictRecordChessPieces.setChessPiecesCampName(chessPieces.getChessPiecesCampName());
            verdictRecordChessPieces.setChessPiecesName(chessPieces.getChessPiecesName());
            verdictRecordChessPieces.setVitaValue(chessPieces.getVitaValue());
            verdictRecordChessPieces.setManeuverValue(chessPieces.getManeuverValue());
            verdictRecordChessPieces.setAttackValue(chessPieces.getAttackValue());
            verdictRecordChessPieces.setDefenseValue(chessPieces.getDefenseValue());
            verdictRecordChessPieces.setStatus("");
            verdictRecordChessPieces.setCoordinate(item.getCoordinate());
            verdictRecordChessPieces.setOffset(item.getOffset());
            verdictRecordChessPieces.setFromCoordinate(item.getFromCoordinate());
            verdictRecordChessPieces.setFromOffset(item.getFromOffset());
            verdictRecordChessPiecesMapper.insert(BeanUtil.toBean(verdictRecordChessPieces, BizVerdictRecordChessPieces.class));
        }
        return "裁决房间信息创建成功";
    }



    //想定部署棋子实现（对新表的添加数据和修改数据）
    @Override
    public String deployScenarioChessPieces(BizScenarioRecordHistoryBo dto) {
        // 根据 scenarioRecordId 和 chessPiecesNumber 查询棋子
        BizScenarioRecordChessPieces scenarioRecordChessPieces = scenarioRecordChessPiecesMapper.queryByScenarioRecordIdAndChessPiecesNumber(dto.getScenarioRecordId(), dto.getChessPiecesNumber());
        // 根据棋子编号查询棋子所有信息（到表存储所有棋子信息的表中，将相关棋子信息找到，后续修改，新增使用）
        BizChessPieces chessPieces = chessPiecesService.queryByChessPiecesNumber(dto.getChessPiecesNumber());
        // 如果棋子不存在，新增 BizScenarioRecordChessPieces
        if (ObjectUtils.isEmpty(scenarioRecordChessPieces)) {
            BizScenarioRecordChessPieces newScenarioRecordChessPieces = new BizScenarioRecordChessPieces();

            newScenarioRecordChessPieces.setScenarioRecordId(Long.valueOf(dto.getScenarioRecordId()));
            newScenarioRecordChessPieces.setChessPiecesNumber(chessPieces.getChessPiecesNumber());
            newScenarioRecordChessPieces.setChessPiecesTypeId(chessPieces.getChessPiecesTypeId());
            newScenarioRecordChessPieces.setChessPiecesTypeName(chessPieces.getChessPiecesTypeName());
            newScenarioRecordChessPieces.setChessPiecesCampId(chessPieces.getChessPiecesCampId());
            newScenarioRecordChessPieces.setChessPiecesCampName(chessPieces.getChessPiecesCampName());
            newScenarioRecordChessPieces.setChessPiecesName(chessPieces.getChessPiecesName());

            newScenarioRecordChessPieces.setVitaValue(chessPieces.getVitaValue().intValue());
            newScenarioRecordChessPieces.setManeuverValue(chessPieces.getManeuverValue().intValue());
            newScenarioRecordChessPieces.setAttackValue(chessPieces.getAttackValue().intValue());
            newScenarioRecordChessPieces.setDefenseValue(chessPieces.getDefenseValue().intValue());
            newScenarioRecordChessPieces.setStatus("");
            newScenarioRecordChessPieces.setCoordinate(dto.getTargetCoordinate());
            newScenarioRecordChessPieces.setOffset(dto.getTargetOffset());
            //保留原有的坐标和偏移量到fromCoordinate和fromOffset
            newScenarioRecordChessPieces.setFromCoordinate(dto.getSelfCoordinate());
            newScenarioRecordChessPieces.setFromOffset(dto.getSelfOffset());

            scenarioRecordChessPiecesMapper.insert(newScenarioRecordChessPieces);
            return "创建成功";
        }
        // 如果棋子存在，更新 Coordinate 和 Offset (坐标和偏移量)和fromCoordinate和fromOffset
        else {
            scenarioRecordChessPieces.setCoordinate(dto.getTargetCoordinate());
            scenarioRecordChessPieces.setOffset(dto.getTargetOffset());
        //保留原有的坐标和偏移量到fromCoordinate和fromOffset
            scenarioRecordChessPieces.setFromCoordinate(dto.getSelfCoordinate());
            scenarioRecordChessPieces.setFromOffset(dto.getSelfOffset());
            scenarioRecordChessPiecesMapper.updateById(BeanUtil.toBean(scenarioRecordChessPieces, BizScenarioRecordChessPieces.class));
            return "修改成功";
        }

    }


    @Override
    public List<BizScenarioRecordChessPieces> queryAllChessPiecesInfo(QueryAllScenarioChessPiecesInfoDto dto) {
        List<BizChessPieces> chessPiecesList;
        String chessPiecesIds = scenarioCreateMapper.selectById(Long.valueOf(dto.getScenarioRecordId())).getChessPiecesIds();
        chessPiecesList = chessPiecesMapper.selectBatchIds(Arrays.asList(chessPiecesIds.split(",")));
        List<BizScenarioRecordChessPieces> tmp = queryChessPiecesInfo(chessPiecesList, dto.getScenarioRecordId());
        return tmp;
    }

    private List<BizScenarioRecordChessPieces> queryChessPiecesInfo(List<BizChessPieces> chessPiecesList, String scenarioRecordId) {
        List<BizScenarioRecordChessPieces> res = new ArrayList<>();
        // 将chessPieces与scenarioRecordId组合成BizScenarioRecordChessPieces
        for (BizChessPieces chessPieces : chessPiecesList) {
            BizScenarioRecordChessPieces scenarioRecordChessPieces = new BizScenarioRecordChessPieces();

            if(scenarioRecordChessPiecesMapper.queryByScenarioRecordIdAndChessPiecesNumber(Long.valueOf(scenarioRecordId), chessPieces.getChessPiecesNumber()) != null){
                scenarioRecordChessPieces = scenarioRecordChessPiecesMapper.queryByScenarioRecordIdAndChessPiecesNumber(Long.valueOf(scenarioRecordId), chessPieces.getChessPiecesNumber());
                scenarioRecordChessPieces.setChessPiecesName(chessPieces.getChessPiecesName());
                scenarioRecordChessPieces.setChessPiecesCover(chessPieces.getChessPiecesCover());
                res.add(scenarioRecordChessPieces);
                continue;
            }

            scenarioRecordChessPieces.setScenarioRecordId(Long.valueOf(scenarioRecordId));
            scenarioRecordChessPieces.setChessPiecesNumber(chessPieces.getChessPiecesNumber());
            scenarioRecordChessPieces.setChessPiecesTypeId(chessPieces.getChessPiecesTypeId());
            scenarioRecordChessPieces.setChessPiecesTypeName(chessPieces.getChessPiecesTypeName());
            scenarioRecordChessPieces.setChessPiecesCampId(chessPieces.getChessPiecesCampId());
            scenarioRecordChessPieces.setChessPiecesCampName(chessPieces.getChessPiecesCampName());
            scenarioRecordChessPieces.setChessPiecesName(chessPieces.getChessPiecesName());
            scenarioRecordChessPieces.setChessPiecesCover(chessPieces.getChessPiecesCover());
            scenarioRecordChessPieces.setCoordinate("0000");
            scenarioRecordChessPieces.setStatus("");
            scenarioRecordChessPieces.setChessPiecesCampId(Long.valueOf(String.valueOf(chessPieces.getChessPiecesCampId())));
            scenarioRecordChessPieces.setIsHide(true);
            res.add(scenarioRecordChessPieces);
        }

        return res;
    }

    @Override
    public List<BizScenarioCreateVo> queryScenarios() {
        LambdaQueryWrapper<BizScenarioCreate> lqw = Wrappers.lambdaQuery();
        lqw.select(BizScenarioCreate::getScenarioName, BizScenarioCreate::getId);

        return baseMapper.selectVoList(lqw);
    }

    @Override
    public Long getChessRoundLimitById(Long id) {
        return baseMapper.selectChessRoundLimitById(id);
    }
    @Override
    public Long getArbiterMapIdById(Long id)
    {
        return baseMapper.selectArbiterMapIdById(id);
    }
    @Override
    public String getStageConfigById(Long id)
    {
        return baseMapper.selectStageConfigById(id);
    }

}
