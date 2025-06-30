package com.ruoyi.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.business.domain.*;
import com.ruoyi.business.domain.bo.BizVerdictRecordBo;
import com.ruoyi.business.domain.bo.BizVerdictRecordChessPiecesBo;
import com.ruoyi.business.domain.bo.BizVerdictRecordDetailBo;
import com.ruoyi.business.domain.dto.QueryAllChessPiecesInfoDto;
import com.ruoyi.business.domain.vo.BizArbiterMapVo;
import com.ruoyi.business.domain.vo.BizVerdictRecordChessPiecesVo;
import com.ruoyi.business.domain.vo.BizVerdictRecordHistoryVo;
import com.ruoyi.business.domain.vo.BizVerdictRecordVo;
import com.ruoyi.business.mapper.*;
import com.ruoyi.business.service.IBizArbiterMapCoordinateService;
import com.ruoyi.business.service.IBizChessPiecesService;
import com.ruoyi.business.service.IBizVerdictRecordChessPiecesService;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.ActionModeEnum;
import com.ruoyi.common.enums.ChessPiecesStatusEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 裁决记录棋子Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class BizVerdictRecordChessPiecesServiceImpl implements IBizVerdictRecordChessPiecesService {

    private final BizVerdictRecordChessPiecesMapper baseMapper;

    private final BizVerdictRecordMapper verdictRecordMapper;
    private final BizScenarioMapper scenarioMapper;
    private final BizScenarioCreateMapper scenarioCreateMapper;
    private final BizChessPiecesMapper chessPiecesMapper;
    private final BizVerdictRecordHistoryMapper verdictRecordHistoryMapper;
    @Autowired
    private IBizChessPiecesService chessPiecesService;
//    @Autowired
//    private IBizVerdictRecordHistoryService verdictRecordHistoryService;

    private final BizArbiterMapMapper arbiterMapMapper;

    @Autowired
    private IBizArbiterMapCoordinateService arbiterMapCoordinateService;

    /**
     * 查询裁决记录棋子
     */
    @Override
    public BizVerdictRecordChessPiecesVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询裁决记录棋子列表
     */
    @Override
    public TableDataInfo<BizVerdictRecordChessPiecesVo> queryPageList(BizVerdictRecordChessPiecesBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizVerdictRecordChessPieces> lqw = buildQueryWrapper(bo);
        Page<BizVerdictRecordChessPiecesVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询裁决记录棋子列表
     */
    @Override
    public List<BizVerdictRecordChessPiecesVo> queryList(BizVerdictRecordChessPiecesBo bo) {
        LambdaQueryWrapper<BizVerdictRecordChessPieces> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizVerdictRecordChessPieces> buildQueryWrapper(BizVerdictRecordChessPiecesBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizVerdictRecordChessPieces> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getVerdictRecordId() != null, BizVerdictRecordChessPieces::getVerdictRecordId, bo.getVerdictRecordId());
        lqw.eq(StringUtils.isNotBlank(bo.getChessPiecesNumber()), BizVerdictRecordChessPieces::getChessPiecesNumber, bo.getChessPiecesNumber());
        lqw.eq(bo.getChessPiecesTypeId() != null, BizVerdictRecordChessPieces::getChessPiecesTypeId, bo.getChessPiecesTypeId());
        lqw.like(StringUtils.isNotBlank(bo.getChessPiecesTypeName()), BizVerdictRecordChessPieces::getChessPiecesTypeName, bo.getChessPiecesTypeName());
        lqw.eq(bo.getChessPiecesCampId() != null, BizVerdictRecordChessPieces::getChessPiecesCampId, bo.getChessPiecesCampId());
        lqw.like(StringUtils.isNotBlank(bo.getChessPiecesCampName()), BizVerdictRecordChessPieces::getChessPiecesCampName, bo.getChessPiecesCampName());
        lqw.like(StringUtils.isNotBlank(bo.getChessPiecesName()), BizVerdictRecordChessPieces::getChessPiecesName, bo.getChessPiecesName());
        lqw.eq(bo.getVitaValue() != null, BizVerdictRecordChessPieces::getVitaValue, bo.getVitaValue());
        lqw.eq(bo.getManeuverValue() != null, BizVerdictRecordChessPieces::getManeuverValue, bo.getManeuverValue());
        lqw.eq(bo.getAttackValue() != null, BizVerdictRecordChessPieces::getAttackValue, bo.getAttackValue());
        lqw.eq(bo.getDefenseValue() != null, BizVerdictRecordChessPieces::getDefenseValue, bo.getDefenseValue());
        lqw.eq(bo.getStatus() != null, BizVerdictRecordChessPieces::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getCoordinate()), BizVerdictRecordChessPieces::getCoordinate, bo.getCoordinate());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizVerdictRecordChessPieces::getExt, bo.getExt());
        lqw.eq(bo.getIsHide() != null, BizVerdictRecordChessPieces::getIsHide, bo.getIsHide());
        return lqw;
    }

    /**
     * 新增裁决记录棋子
     */
    @Override
    public Boolean insertByBo(BizVerdictRecordChessPiecesBo bo) {
        BizVerdictRecordChessPieces add = BeanUtil.toBean(bo, BizVerdictRecordChessPieces.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改裁决记录棋子
     */
    @Override
    public Boolean updateByBo(BizVerdictRecordChessPiecesBo bo) {
        BizVerdictRecordChessPieces update = BeanUtil.toBean(bo, BizVerdictRecordChessPieces.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizVerdictRecordChessPieces entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除裁决记录棋子
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }


    @Override
    public BizVerdictRecordChessPiecesVo queryByVerdictRecordIdAndChessPiecesNumber(Long verdictRecordId, String chessPiecesNumber) {
        return baseMapper.selectVoOne(new LambdaQueryWrapper<BizVerdictRecordChessPieces>().eq(BizVerdictRecordChessPieces::getVerdictRecordId, verdictRecordId).eq(BizVerdictRecordChessPieces::getChessPiecesNumber, chessPiecesNumber).last("limit 1"));
    }


    @Override
    public void updateStatusByIds(String status, List<Long> ids) {
        BizVerdictRecordChessPieces verdictRecordChessPieces = new BizVerdictRecordChessPieces();
        verdictRecordChessPieces.setStatus(status);
        baseMapper.update(verdictRecordChessPieces, new LambdaQueryWrapper<BizVerdictRecordChessPieces>().in(BizVerdictRecordChessPieces::getId, ids));

    }


    @Override
    public List<BizVerdictRecordChessPieces> queryAllChessPiecesInfo(QueryAllChessPiecesInfoDto dto) {
        log.info("queryAllChessPiecesInfo req:{}", JSON.toJSONString(dto));
        BizVerdictRecordVo verdictRecord = verdictRecordMapper.selectVoById(dto.getVerdictRecordId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("裁决记录不存在,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("裁决记录不存在");
        }
        BizScenario scenario = scenarioMapper.selectById(verdictRecord.getScenarioId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("查询所有棋子信息,想定不存在,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("想定不存在");
        }
        List<BizChessPieces> chessPiecesList;
//        if (Long.valueOf(VerdictRecordStatusEnum.TWO_DEPLOY_DONE.getCode()).equals(verdictRecord.getStatus()) || Long.valueOf(VerdictRecordStatusEnum.PROCESSING.getCode()).equals(verdictRecord.getStatus()) || Long.valueOf(VerdictRecordStatusEnum.END.getCode()).equals(verdictRecord.getStatus())) {
//            List<BizVerdictRecordChessPieces> records = baseMapper.selectList(new LambdaQueryWrapper<BizVerdictRecordChessPieces>().eq(BizVerdictRecordChessPieces::getVerdictRecordId, verdictRecord.getId()).eq(BizVerdictRecordChessPieces::getDelFlag, "0"));
//            List<String> chessNumberList = records.stream().map(BizVerdictRecordChessPieces::getChessPiecesNumber).collect(Collectors.toList());
//            chessPiecesList = chessPiecesMapper.selectList(new LambdaQueryWrapper<BizChessPieces>().in(BizChessPieces::getChessPiecesNumber, chessNumberList));
//        } else {
//            chessPiecesList = chessPiecesService.queryByProductId(scenario.getProductId());
//        }
        chessPiecesList = chessPiecesMapper.selectBatchIds(Arrays.asList(verdictRecord.getChessPiecesIds().split(",")));
        List<BizVerdictRecordChessPieces> tmp = queryChessPiecesInfo(chessPiecesList, dto.getVerdictRecordId(), dto.getChessRound(), dto.getCampId(), dto.getRoundPeriod());
        return tmp;
    }

    @Override
    public Map<String, String> getMapChessImage(Long verdictRecordId) {
        BizVerdictRecordVo verdictRecord = verdictRecordMapper.selectVoById(verdictRecordId);
        List<BizChessPieces> chessPiecesList;
        chessPiecesList = chessPiecesMapper.selectBatchIds(Arrays.asList(verdictRecord.getChessPiecesIds().split(",")));
//        Map<String, BizVerdictRecordChessPieces> numberMap = verdictRecordChessPiecesList.stream().collect(Collectors.toMap(BizVerdictRecordChessPieces::getChessPiecesNumber, Function.identity()));
        Map<String,String> map = new HashMap<>();
        for(BizChessPieces chessPieces: chessPiecesList){
            map.put(chessPieces.getChessPiecesNumber(),chessPieces.getChessPiecesCover());
        }
        return map;
    }

    @Override
    public Map<String, String> getMapChessImageC(Long scenarioId) {

        List<BizChessPieces> chessPiecesList;

        String chessPiecesIds = scenarioCreateMapper.selectById(scenarioId).getChessPiecesIds();
        chessPiecesList = chessPiecesMapper.selectBatchIds(Arrays.asList(chessPiecesIds.split(",")));
//        Map<String, BizVerdictRecordChessPieces> numberMap = verdictRecordChessPiecesList.stream().collect(Collectors.toMap(BizVerdictRecordChessPieces::getChessPiecesNumber, Function.identity()));
        Map<String,String> map = new HashMap<>();
        for(BizChessPieces chessPieces: chessPiecesList){
            map.put(chessPieces.getChessPiecesNumber(),chessPieces.getChessPiecesCover());
        }
        return map;
    }


    private List<BizVerdictRecordChessPieces> queryChessPiecesInfo(List<BizChessPieces> chessPiecesList, String verdictRecordId, Long chessRound, String campId, Long roundPeriod) {
        //裁决记录棋子信息
        List<BizVerdictRecordChessPieces> verdictRecordChessPiecesList = baseMapper.selectList(new LambdaQueryWrapper<BizVerdictRecordChessPieces>().eq(BizVerdictRecordChessPieces::getVerdictRecordId, Long.valueOf(verdictRecordId)));
        Map<String, BizVerdictRecordChessPieces> numberMap = verdictRecordChessPiecesList.stream().collect(Collectors.toMap(BizVerdictRecordChessPieces::getChessPiecesNumber, Function.identity()));
        //查询历史记录

        List<BizVerdictRecordHistory> historyList = verdictRecordHistoryMapper.selectList(new LambdaQueryWrapper<BizVerdictRecordHistory>().eq(BizVerdictRecordHistory::getVerdictRecordId, verdictRecordId).eq(BizVerdictRecordHistory::getChessRound, chessRound).in(ObjectUtil.isNotNull(roundPeriod), BizVerdictRecordHistory::getRoundPeriod, roundPeriod));
        Map<String, List<BizVerdictRecordHistory>> historyMap = historyList.stream().collect(Collectors.groupingBy(BizVerdictRecordHistory::getChessPiecesNumber));
        List<BizVerdictRecordChessPieces> voList = new ArrayList<>();
        chessPiecesList.forEach(e -> {
            BizVerdictRecordChessPieces vo = new BizVerdictRecordChessPieces();
            vo.setVerdictRecordId(Long.valueOf(verdictRecordId));
            vo.setChessPiecesNumber(e.getChessPiecesNumber());
            vo.setChessPiecesTypeName(e.getChessPiecesTypeName());
            vo.setChessPiecesName(e.getChessPiecesName());
            vo.setChessPiecesCover(e.getChessPiecesCover());
            vo.setCoordinate("0000");
            vo.setStatus("");
            vo.setStatusDesc(ChessPiecesStatusEnum.NORMAL.getDesc());
            vo.setIsCanBeRemoved(ChessPiecesStatusEnum.NORMAL.getIsCanBeRemoved());
            vo.setChessPiecesCampId(Long.valueOf(String.valueOf(e.getChessPiecesCampId())));
            vo.setIsHide(1);
            vo.setManeuverValue(e.getManeuverValue());


            BizVerdictRecordChessPieces verdictRecordChessPieces = numberMap.get(e.getChessPiecesNumber());
            if (!ObjectUtils.isEmpty(verdictRecordChessPieces)) {
                vo.setCoordinate(verdictRecordChessPieces.getCoordinate());
                vo.setOffset(verdictRecordChessPieces.getOffset());
                vo.setStatus(verdictRecordChessPieces.getStatus());
                vo.setStatusDesc(verdictRecordChessPieces.getStatus());
                vo.setIsCanBeRemoved(false);
                vo.setIsHide(verdictRecordChessPieces.getIsHide());
                vo.setRoundActionPoint(verdictRecordChessPieces.getRoundActionPoint());
                vo.setId(verdictRecordChessPieces.getId());
            }
            List<BizVerdictRecordHistory> chessPiecesHistoryList = historyMap.get(e.getChessPiecesNumber());
            if (CollectionUtils.isEmpty(chessPiecesHistoryList)) {
                vo.setIsAction(Boolean.FALSE);
            } else {
                vo.setIsAction(Boolean.TRUE);
            }
            voList.add(vo);
        });
        //比赛两方会传入阵营ID
        if (StringUtils.isNotBlank(campId)) {
            //我方棋子
            List<BizVerdictRecordChessPieces> usList = voList.stream().filter(e -> e.getChessPiecesCampId().equals(Long.valueOf(campId))).collect(Collectors.toList());
            List<BizVerdictRecordChessPieces> filterVoList = new ArrayList<>(usList);
            /*//对方棋子
            List<ArbiterMapCoordinate> mapCoordinates = arbiterMapCoordinateService.queryByMapId(arbiterMapId);
            //查询所有地形
            List<String> terrainIdList = new ArrayList<>();
            mapCoordinates.forEach(mapCoordinate -> {
                if (StringUtils.isNotBlank(mapCoordinate.getArbiterMapLegendIds())) {
                    List<String> eachLegendIdList = Arrays.asList(mapCoordinate.getArbiterMapLegendIds().split(ArbiterConstant.ENGLISH_COMMA));
                    terrainIdList.addAll(eachLegendIdList);
                }
            });
            List<ArbiterMapLegend> mapLegendList = arbiterMapLegendService.listByIds(terrainIdList.stream().distinct().map(Long::parseLong).collect(Collectors.toList()));

            Map<String, String> coordinateMap = mapCoordinates.stream().collect(Collectors.toMap(ArbiterMapCoordinate::getCoordinate, ArbiterMapCoordinate::getArbiterMapLegendIds));*/
            //TODO  getIsHide判断
            List<BizVerdictRecordChessPieces> oppositeList = voList.stream().filter(e -> !e.getChessPiecesCampId().equals(Long.valueOf(campId)) && e.getIsHide() == 0).collect(Collectors.toList());
            for (BizVerdictRecordChessPieces oppositePieces : oppositeList) {
                if (isVisible(usList, oppositePieces, 120)) {
                    filterVoList.add(oppositePieces);
                }
            }
            return filterVoList;
        }
        return voList;
    }

    private boolean isVisible(List<BizVerdictRecordChessPieces> usList, BizVerdictRecordChessPieces oppositePieces, int visibleDistance) {
        for (BizVerdictRecordChessPieces usPieces : usList) {
//            int coordinateInstance = calculateCoordinateInstance(usPieces.getCoordinate(), oppositePieces.getCoordinate());
            int coordinateInstance = 10;
            if (coordinateInstance <= visibleDistance) {
                return true;
            }
        }
        return false;
    }


    /**
     * 查询两个坐标间距离
     *
     * @param selfCoordinate
     * @param targetCoordinate
     * @return
     */
    private int calculateCoordinateInstance(String selfCoordinate, String targetCoordinate) {
        //求两个坐标距离
        int selfX = Integer.parseInt(selfCoordinate.substring(0, 2));
        int selfY = Integer.parseInt(selfCoordinate.substring(2));

        int targetX = Integer.parseInt(targetCoordinate.substring(0, 2));
        int targetY = Integer.parseInt(targetCoordinate.substring(2));

        double sum = Math.pow(selfX - targetX, 2) + Math.pow(selfY - targetY, 2);
        return (int) Math.sqrt(sum) * 2;
    }


    @Override
    public boolean markRemove(Long verdictRecordId) {
        BizVerdictRecordChessPieces record = new BizVerdictRecordChessPieces();
//        record.setStatus(ChessPiecesStatusEnum.NORMAL.getCode());
        record.setStatus("");
        return baseMapper.update(record, new LambdaQueryWrapper<BizVerdictRecordChessPieces>().eq(BizVerdictRecordChessPieces::getVerdictRecordId, verdictRecordId)
            .eq(BizVerdictRecordChessPieces::getStatus, ChessPiecesStatusEnum.REPRESSED.getCode())) > 0;
    }


    @Override
    public BizVerdictRecordChessPieces queryChessPiecesInfo(BizVerdictRecordBo dto) {
        log.info("queryChessPiecesInfo req:{}", JSON.toJSONString(dto));
        BizVerdictRecordChessPiecesVo verdictRecordChessPieces = this.queryByVerdictRecordIdAndChessPiecesNumber(dto.getId(), dto.getChessPiecesNumber());
        if (ObjectUtils.isEmpty(verdictRecordChessPieces)) {
            log.error("本裁决中当前棋子信息不存在");
            throw new ServiceException("本裁决中当前棋子信息不存在");
        }
        return BeanUtil.toBean(verdictRecordChessPieces, BizVerdictRecordChessPieces.class);
    }

    @Override
    public List<BizVerdictRecordChessPieces> queryUsChessPiecesInfo(QueryAllChessPiecesInfoDto dto) {
        log.info("queryUsChessPiecesInfo req:{}", JSON.toJSONString(dto));
        BizVerdictRecordVo verdictRecord = verdictRecordMapper.selectVoById(dto.getVerdictRecordId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("裁决记录不存在,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("裁决记录不存在");
        }
        BizScenario scenario = scenarioMapper.selectById(verdictRecord.getScenarioId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("查询所有棋子信息,想定不存在,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("想定不存在");
        }
        List<BizChessPieces> chessPiecesList = chessPiecesService.queryByProductId(scenario.getProductId());
        chessPiecesList = chessPiecesList.stream().filter(e -> e.getChessPiecesCampId().equals(Long.valueOf(dto.getCampId()))).collect(Collectors.toList());
        return queryChessPiecesInfo(chessPiecesList, dto.getVerdictRecordId(), dto.getChessRound(), dto.getCampId(), dto.getRoundPeriod());
    }

    @Override
    public List<BizVerdictRecordHistoryVo> queryChessRecordByRound(QueryAllChessPiecesInfoDto dto) {
        log.info("queryUsChessPiecesInfo req:{}", JSON.toJSONString(dto));
        BizVerdictRecordVo verdictRecord = verdictRecordMapper.selectVoById(dto.getVerdictRecordId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("裁决记录不存在,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("裁决记录不存在");
        }
        QueryWrapper<BizVerdictRecordHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("vrh.del_flag", "0");
        queryWrapper.eq("vrh.verdict_record_id", dto.getVerdictRecordId());
        queryWrapper.eq("vrh.chess_round", dto.getChessRound());
        queryWrapper.in("vrh.round_period", dto.getRoundPeriod());
        queryWrapper.orderByDesc("vrh.update_time");

        return baseMapper.queryChessRecordByRound(queryWrapper);
    }

    @Override
    public BizArbiterMapVo queryMapInfo(BizVerdictRecordBo dto) {
        BizVerdictRecord verdictRecord = verdictRecordMapper.selectById(dto.getId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("查询地图信息,裁决记录不存在,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("裁决记录不存在");
        }
        //查询想定记录
        BizScenario scenario = scenarioMapper.selectById(verdictRecord.getScenarioId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("查询所有棋子信息,想定不存在,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("想定不存在");
        }
        //查询地图
        BizArbiterMap arbiterMap = arbiterMapMapper.selectById(scenario.getArbiterMapId());
        //查询地图坐标
        List<BizArbiterMapCoordinate> arbiterMapCoordinates = arbiterMapCoordinateService.queryByMapId(scenario.getArbiterMapId());
        //返回
        BizArbiterMapVo vo = BeanUtil.toBean(arbiterMap, BizArbiterMapVo.class);
        vo.setCoordinateList(arbiterMapCoordinates);
        return vo;
    }


    @Override
    public List<BizVerdictRecordChessPieces> globalVision(BizVerdictRecordBo dto) {
        BizVerdictRecord verdictRecord = verdictRecordMapper.selectById(dto.getId());
        //查询我方阵营
        Pair pair = queryCampIdByUserId(verdictRecord, Long.valueOf(dto.getUserId()));
        //查询本次裁决所有棋子
        List<BizVerdictRecordChessPieces> records = baseMapper.selectList(new LambdaQueryWrapper<BizVerdictRecordChessPieces>().eq(BizVerdictRecordChessPieces::getVerdictRecordId, dto.getId()));
        //我方棋子
        List<BizVerdictRecordChessPieces> usPieces = records.stream().filter(e -> e.getChessPiecesCampId().equals(pair.getKey())).collect(Collectors.toList());
        //敌方棋子
        List<BizVerdictRecordChessPieces> oppositePieces = records.stream().filter(e -> !e.getChessPiecesCampId().equals(pair.getKey())).collect(Collectors.toList());
        //查询棋子
        List<String> chessNumberList = records.stream().map(BizVerdictRecordChessPieces::getChessPiecesNumber).collect(Collectors.toList());
        List<BizChessPieces> chessPiecesList = chessPiecesMapper.selectList(new LambdaQueryWrapper<BizChessPieces>().in(BizChessPieces::getChessPiecesNumber, chessNumberList));
        Map<String, BizChessPieces> piecesMap = chessPiecesList.stream().collect(Collectors.toMap(BizChessPieces::getChessPiecesNumber, Function.identity()));

        List<BizVerdictRecordChessPieces> voList = new ArrayList<>();
        usPieces.forEach(e -> {
            BizVerdictRecordChessPieces usVo = new BizVerdictRecordChessPieces();
            usVo.setUserId(dto.getUserId());
            usVo.setChessPiecesNumber(e.getChessPiecesNumber());
            usVo.setChessPiecesName(e.getChessPiecesName());
            usVo.setChessPiecesCover(piecesMap.get(e.getChessPiecesNumber()).getChessPiecesCover());
            usVo.setCoordinate(e.getCoordinate());
            usVo.setStatus(e.getStatus());
            voList.add(usVo);
        });
        oppositePieces.forEach(e -> {
            BizVerdictRecordChessPieces oppositeVo = new BizVerdictRecordChessPieces();
            oppositeVo.setUserId((Long) pair.getValue());
            oppositeVo.setChessPiecesNumber(e.getChessPiecesNumber());
            oppositeVo.setChessPiecesName(e.getChessPiecesName());
            oppositeVo.setChessPiecesCover(piecesMap.get(e.getChessPiecesNumber()).getChessPiecesCover());
            oppositeVo.setCoordinate(e.getCoordinate());
            oppositeVo.setStatus(e.getStatus());
            voList.add(oppositeVo);
        });
        return voList;
    }

    private Pair queryCampIdByUserId(BizVerdictRecord verdictRecord, Long userId) {
        if (userId.equals(verdictRecord.getFirstUserId())) {
            return Pair.of(verdictRecord.getFirstCampId(), verdictRecord.getSecondUserId());
        } else {
            return Pair.of(verdictRecord.getSecondCampId(), verdictRecord.getFirstUserId());
        }
    }

    @Override
    public void actionPointChange(BizVerdictRecordChessPiecesBo dto){
//        boolean ifAct=false;
//        if (ActionModeEnum.NORMAL.getCode().equals(dto.getActionMode())) {
//            ifAct=true;
//        }else if (ActionModeEnum.DESTROYED.getCode().equals(dto.getActionMode())) {
//            ifAct=true;
//        }
//        if(ifAct){
            baseMapper.setActionPointFalse(dto);
//        }
        return ;
//        if(dto.
    }
    @Override
    public void actionPointReset(BizVerdictRecordChessPiecesBo dto){
        baseMapper.resetActionPointFalse(dto);
    }


    @Override
    public String changeChessStatus(BizVerdictRecordChessPiecesBo dto){
        LambdaQueryWrapper<BizVerdictRecordChessPieces> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BizVerdictRecordChessPieces::getChessPiecesNumber, dto.getChessPiecesNumber());
        queryWrapper.eq(BizVerdictRecordChessPieces::getVerdictRecordId, dto.getVerdictRecordId());
//        queryWrapper.eq(BizVerdictRecordChessPieces::getChessPiecesCampId, dto.getChessPiecesCampId());
        BizVerdictRecordChessPieces bizVerdictRecordChessPieces = baseMapper.selectOne(queryWrapper);
        if (Objects.equals(dto.getStatus(), "RELEASE_PRESS")){
            bizVerdictRecordChessPieces.setStatus("");
        }
        else {
            bizVerdictRecordChessPieces.setStatus(dto.getStatus());
        }
        baseMapper.update(bizVerdictRecordChessPieces, queryWrapper);
        return "success";
    }
}
