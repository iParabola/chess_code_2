package com.ruoyi.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.business.domain.*;
import com.ruoyi.business.domain.bo.*;
import com.ruoyi.business.domain.dto.*;
import com.ruoyi.business.domain.vo.*;
import com.ruoyi.business.mapper.*;
import com.ruoyi.business.service.*;
import com.ruoyi.common.constant.ArbiterConstant;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.*;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 裁决记录历史复盘Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class BizVerdictRecordHistoryServiceImpl implements IBizVerdictRecordHistoryService {

    private final BizVerdictRecordHistoryMapper baseMapper;

    private final BizVerdictRecordMapper verdictRecordMapper;
    @Autowired
    private IBizVerdictRecordChessPiecesService verdictRecordChessPiecesService;
    @Autowired
    private IBizChessPiecesService chessPiecesService;

    @Autowired
    private IBizScenarioCreateService scenarioCreateService;

    private final BizVerdictRecordChessPiecesMapper verdictRecordChessPiecesMapper;
    @Autowired
    private IBizVerdictRuleService verdictRuleService;
    @Autowired
    private IBizVerdictBlowEffectService verdictBlowEffectService;

    private final BizVerdictBlowEffectMapper verdictBlowEffectMapper;

    @Autowired
    private IBizIndirectVerdictRuleService indirectVerdictRuleService;

    private final BizIndirectRecordMapper indirectRecordMapper;

    private final BizIndirectVerdictRuleMapper indirectVerdictRuleMapper;

    @Autowired
    private IBizIndirectBlowEffectService indirectBlowEffectService;

    private final BizUserMapper userMapper;

    private final BizChessPiecesCampMapper chessPiecesCampMapper;

    private final BizVerdictRecordHistoryMapper verdictRecordHistoryMapper;

    private final BizVerdictRecordScoreMapper verdictRecordScoreMapper;

    private final BizVerdictRecordRoundMapper verdictRecordRoundMapper;

    private final BizScenarioMapper scenarioMapper;

    private final BizScenarioCreateMapper scenarioCreateMapper;

    @Autowired
    private IBizVerdictRecordRoundService verdictRecordRoundService;

    /**
     * 查询裁决记录历史复盘
     */
    @Override
    public BizVerdictRecordHistoryVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询裁决记录历史复盘列表
     */
    @Override
    public TableDataInfo<BizVerdictRecordHistoryVo> queryPageList(BizVerdictRecordHistoryBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizVerdictRecordHistory> lqw = buildQueryWrapper(bo);
        Page<BizVerdictRecordHistoryVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询裁决记录历史复盘列表
     */
    @Override
    public List<BizVerdictRecordHistoryVo> queryList(BizVerdictRecordHistoryBo bo) {
        LambdaQueryWrapper<BizVerdictRecordHistory> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizVerdictRecordHistory> buildQueryWrapper(BizVerdictRecordHistoryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizVerdictRecordHistory> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getVerdictRecordId() != null, BizVerdictRecordHistory::getVerdictRecordId, bo.getVerdictRecordId());
        lqw.eq(bo.getUserId() != null, BizVerdictRecordHistory::getUserId, bo.getUserId());
        lqw.eq(bo.getCampId() != null, BizVerdictRecordHistory::getCampId, bo.getCampId());
        lqw.eq(StringUtils.isNotBlank(bo.getChessPiecesNumber()), BizVerdictRecordHistory::getChessPiecesNumber, bo.getChessPiecesNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getSelfCoordinate()), BizVerdictRecordHistory::getSelfCoordinate, bo.getSelfCoordinate());
        lqw.eq(StringUtils.isNotBlank(bo.getTargetCoordinate()), BizVerdictRecordHistory::getTargetCoordinate, bo.getTargetCoordinate());
        lqw.eq(bo.getActionMode() != null, BizVerdictRecordHistory::getActionMode, bo.getActionMode());
        lqw.eq(bo.getIsEndRound() != null, BizVerdictRecordHistory::getIsEndRound, bo.getIsEndRound());
        lqw.eq(bo.getChessRound() != null, BizVerdictRecordHistory::getChessRound, bo.getChessRound());
        lqw.eq(bo.getRoundPeriod() != null, BizVerdictRecordHistory::getRoundPeriod, bo.getRoundPeriod());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizVerdictRecordHistory::getExt, bo.getExt());
        lqw.eq(StringUtils.isNotBlank(bo.getActionDesc()), BizVerdictRecordHistory::getActionDesc, bo.getActionDesc());
        lqw.eq(StringUtils.isNotBlank(bo.getChessPiecesInfo()), BizVerdictRecordHistory::getChessPiecesInfo, bo.getChessPiecesInfo());
        return lqw;
    }

    /**
     * 新增裁决记录历史复盘
     */
    @Override
    public Boolean insertByBo(BizVerdictRecordHistoryBo bo) {
        BizVerdictRecordHistory add = BeanUtil.toBean(bo, BizVerdictRecordHistory.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改裁决记录历史复盘
     */
    @Override
    public Boolean updateByBo(BizVerdictRecordHistoryBo bo) {
        BizVerdictRecordHistory update = BeanUtil.toBean(bo, BizVerdictRecordHistory.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizVerdictRecordHistory entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除裁决记录历史复盘
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }


    @Override
    public String deployChessPieces(BizVerdictRecordHistoryBo dto) {
        log.info("deployChessPieces req:{}", JSON.toJSONString(dto));
        BizVerdictRecordVo verdictRecord = verdictRecordMapper.selectVoById(dto.getVerdictRecordId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("裁决记录不存在,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("裁决记录不存在");
        }
        if (VerdictRecordStatusEnum.INIT.getCode().equals(verdictRecord.getStatus().intValue())) {
            log.error("比赛还未开始,无法部署,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("比赛还未开始,无法部署");
        }
        if (VerdictRecordStatusEnum.ONE_DEPLOY_DONE.getCode().equals(verdictRecord.getStatus().intValue()) && String.valueOf(dto.getUserId()).equals(verdictRecord.getExt())) {
            log.error("您已部署完成,请勿重复提交,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("您已部署完成,请勿重复提交");
        }
        if (VerdictRecordStatusEnum.TWO_DEPLOY_DONE.getCode().equals(verdictRecord.getStatus().intValue())) {
            log.error("您已部署完成,请勿重复提交,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("您已部署完成,请勿重复提交");
        }
        BizVerdictRecordChessPiecesVo verdictRecordChessPieces = verdictRecordChessPiecesService.queryByVerdictRecordIdAndChessPiecesNumber(dto.getVerdictRecordId(), dto.getChessPiecesNumber());
        //save or update verdictRecordChessPieces
        if (ObjectUtils.isEmpty(verdictRecordChessPieces)) {
            BizChessPieces chessPieces = chessPiecesService.queryByChessPiecesNumber(dto.getChessPiecesNumber());
            if (ObjectUtils.isEmpty(chessPieces)) {
                throw new ServiceException("棋子不存在");
            }
            verdictRecordChessPieces = new BizVerdictRecordChessPiecesVo();
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
            verdictRecordChessPieces.setCoordinate(dto.getTargetCoordinate());
            verdictRecordChessPieces.setOffset(dto.getTargetOffset());
            verdictRecordChessPieces.setFromCoordinate(dto.getTargetCoordinate());
            verdictRecordChessPieces.setFromOffset(dto.getTargetOffset());
            verdictRecordChessPiecesMapper.insert(BeanUtil.toBean(verdictRecordChessPieces, BizVerdictRecordChessPieces.class));

        } else if (ActionModeEnum.NORMAL.getCode().equals(dto.getActionMode())) {
            verdictRecordChessPieces.setCoordinate(dto.getTargetCoordinate());
            verdictRecordChessPieces.setOffset(dto.getTargetOffset());
            verdictRecordChessPiecesMapper.updateById(BeanUtil.toBean(verdictRecordChessPieces, BizVerdictRecordChessPieces.class));
        }
        return "";
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public String endDeploy(BizVerdictRecordHistoryBo dto) {
        log.info("endDeploy req:{}", JSON.toJSONString(dto));
        BizVerdictRecordVo verdictRecord = verdictRecordMapper.selectVoById(dto.getVerdictRecordId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("裁决记录不存在,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("裁决记录不存在");
        }
        if (!Long.valueOf(VerdictRecordStatusEnum.DEPLOYING.getCode()).equals(verdictRecord.getStatus()) && !Long.valueOf(VerdictRecordStatusEnum.ONE_DEPLOY_DONE.getCode()).equals(verdictRecord.getStatus())) {
            log.error("状态不正确,无法结束部署,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("状态不正确,无法结束部署");
        }
        if (VerdictRecordStatusEnum.DEPLOYING.getCode().equals(verdictRecord.getStatus().intValue())) {
            //一方部署完成
            verdictRecord.setStatus(Long.valueOf(VerdictRecordStatusEnum.ONE_DEPLOY_DONE.getCode()));
            verdictRecord.setExt(String.valueOf(dto.getUserId()));

        } else {
            //双方部署完成
            if (String.valueOf(dto.getUserId()).equals(verdictRecord.getExt())) {
                log.error("您已结束部署,请勿重复提交,dto:{}", JSON.toJSONString(dto));
                throw new ServiceException("您已结束部署,请勿重复提交");
            }
            verdictRecord.setStatus(Long.valueOf(VerdictRecordStatusEnum.TWO_DEPLOY_DONE.getCode()));
            verdictRecord.setChessRound(1L);
            //双方部署完成后添加回合记录
            addRecordChessRound(verdictRecord);
        }
        verdictRecordMapper.updateById(BeanUtil.toBean(verdictRecord, BizVerdictRecord.class));

        //如果双方部署完成,将0000坐标的棋子删掉
        if (VerdictRecordStatusEnum.TWO_DEPLOY_DONE.getCode().equals(verdictRecord.getStatus().intValue())) {
            List<BizVerdictRecordChessPiecesVo> initCoordinateRecords = verdictRecordChessPiecesMapper.selectVoList(new LambdaQueryWrapper<BizVerdictRecordChessPieces>().eq(BizVerdictRecordChessPieces::getVerdictRecordId, verdictRecord.getId()).eq(BizVerdictRecordChessPieces::getCoordinate, ArbiterConstant.INIT_COORDINATE));
            if (!CollectionUtils.isEmpty(initCoordinateRecords)) {
                List<Long> ids = initCoordinateRecords.stream().map(BizVerdictRecordChessPiecesVo::getId).collect(Collectors.toList());
                verdictRecordChessPiecesMapper.deleteBatchIds(ids);
            }
            //新增一条部署记录存初始棋子的状态
            BizVerdictRecordHistory history = new BizVerdictRecordHistory();
            history.setVerdictRecordId(verdictRecord.getId());
            history.setChessRound(0L);
            history.setRoundPeriod(0);
            history.setStatus(0);
            QueryAllChessPiecesInfoDto queryAllChessPiecesInfoDto = new QueryAllChessPiecesInfoDto();
            queryAllChessPiecesInfoDto.setVerdictRecordId(String.valueOf(verdictRecord.getId()));
            queryAllChessPiecesInfoDto.setChessRound(verdictRecord.getChessRound());
            List<BizVerdictRecordChessPieces> vos = verdictRecordChessPiecesService.queryAllChessPiecesInfo(queryAllChessPiecesInfoDto);
            history.setChessPiecesInfo(JSON.toJSONString(vos));
            baseMapper.insert(history);

            //数据库中增加记录使导演可以打分 设置蓝军
            BizVerdictRecordHistory history1 = new BizVerdictRecordHistory();
            history1.setUserId(dto.getUserId());
            history1.setCampId(1689661085015457793L);
            history1.setVerdictRecordId(dto.getVerdictRecordId());
            history1.setChessRound(0L);
            history1.setRoundPeriod(0);
            history1.setRoundId(verdictRecordRoundService.getIdByRecordIdAndCampId(verdictRecord.getId(), verdictRecord.getSecondCampId()));
            history1.setStatus(0);
            history1.setChessPiecesNumber("蓝军");
            history1.setAttackResult("准备阶段结束");
            history1.setActionDesc("蓝军已准备就绪");
            baseMapper.insert(history1);

            BizVerdictRecordHistory history2 = new BizVerdictRecordHistory();
            history2.setUserId(dto.getUserId());
            history2.setCampId(1689661085015457792L);
            history2.setVerdictRecordId(dto.getVerdictRecordId());
            history2.setChessRound(0L);
            history2.setRoundPeriod(0);
            history2.setRoundId(verdictRecordRoundService.getIdByRecordIdAndCampId(verdictRecord.getId(), verdictRecord.getFirstCampId()));
            history2.setStatus(0);
            history2.setChessPiecesNumber("红军");
            history2.setAttackResult("准备阶段结束");
            history2.setActionDesc("红军已准备就绪");
            baseMapper.insert(history2);
        }



        return "";
    }

    private void addRecordChessRound(BizVerdictRecordVo vo) {
        List<BizVerdictRecordRound> recordRounds = new ArrayList<>();
        int round = vo.getChessRoundLimit().intValue();
        for (int i = 1; i <= round; i++) {
            for (int j = 1; j <= 4; j++) {
                BizVerdictRecordRound firstRound = new BizVerdictRecordRound();
                firstRound.setVerdictRecordId(vo.getId());
                firstRound.setCampId(vo.getFirstCampId());
                firstRound.setUserId(vo.getFirstUserId());
                firstRound.setRoundPeriod(j);
                firstRound.setChessRound(Long.valueOf(i));
                firstRound.setStatus(0);
                BizVerdictRecordRound secondRound = new BizVerdictRecordRound();
                secondRound.setVerdictRecordId(vo.getId());
                secondRound.setCampId(vo.getSecondCampId());
                secondRound.setUserId(vo.getSecondUserId());
                secondRound.setRoundPeriod(j);
                secondRound.setChessRound(Long.valueOf(i));
                secondRound.setStatus(0);
                recordRounds.add(firstRound);
                recordRounds.add(secondRound);
            }
        }
        verdictRecordRoundMapper.insertBatch(recordRounds);
    }

    /**
     * 方法已弃用
     *
     * @param dto
     * @return
     */
    @Override
    public Boolean isOwnRound(BizVerdictRecordBo dto) {
        log.info("isOwnRound req:{}", JSON.toJSONString(dto));
        BizVerdictRecordVo verdictRecord = verdictRecordMapper.selectVoById(dto.getId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("裁决记录不存在,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("裁决记录不存在");
        }
        if (verdictRecord.getFirstCampId() == null || verdictRecord.getSecondCampId() == null) {
            log.error("双方还未准备就绪,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("双方还未准备就绪");
        }
        if (VerdictRecordStatusEnum.END.getCode().equals(verdictRecord.getStatus().intValue())) {
            log.error("比赛已结束,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("比赛已结束");
        }
        Boolean isOwnRound = Boolean.FALSE;
        BizVerdictRecordHistoryVo latestVerdictRecordHistory = this.queryLatestRecord(dto.getId());
        if (ObjectUtils.isEmpty(latestVerdictRecordHistory)) {
            if (verdictRecord.getFirstUserId().equals(Long.valueOf(dto.getUserId()))) {
                isOwnRound = Boolean.TRUE;
            }
        } else {
            if (verdictRecord.getFirstUserId().equals(dto.getUserId())) {
//                if (RoundPeriodEnum.FIRST_AIM.getCode().equals(verdictRecord.getRoundPeriod()) || RoundPeriodEnum.FIRST_MOVE.getCode().equals(verdictRecord.getRoundPeriod()) || RoundPeriodEnum.FIRST_INDIRECT_FIRE.getCode().equals(verdictRecord.getRoundPeriod())) {
                isOwnRound = Boolean.TRUE;
//                }
            } else {
//                if (RoundPeriodEnum.SECOND_AIM.getCode().equals(verdictRecord.getRoundPeriod()) || RoundPeriodEnum.SECOND_MOVE.getCode().equals(verdictRecord.getRoundPeriod()) || RoundPeriodEnum.SECOND_INDIRECT_FIRE.getCode().equals(verdictRecord.getRoundPeriod())) {
                isOwnRound = Boolean.TRUE;
//                }
            }
        }
        return isOwnRound;
    }

    public BizVerdictRecordHistoryVo queryLatestRecord(Long verdictRecordId) {
        return baseMapper.selectVoOne(new LambdaQueryWrapper<BizVerdictRecordHistory>().eq(BizVerdictRecordHistory::getVerdictRecordId, verdictRecordId).orderByDesc(BizVerdictRecordHistory::getCreateTime).last(ArbiterConstant.LIMIT_ONE));
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public BizIndirectBlowEffectVo chessPiecesAction(BizVerdictRecordDetailBo dto) {
        log.info("chessPiecesAction req:{}", JSON.toJSONString(dto));
        BizVerdictRecordVo verdictRecord = verdictRecordMapper.selectVoById(dto.getVerdictRecordId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("裁决记录不存在,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("裁决记录不存在");
        }
        if (VerdictRecordStatusEnum.END.getCode().equals(verdictRecord.getStatus().intValue())) {
            log.error("游戏已结束,回合数:{}", verdictRecord.getChessRound());
            throw new ServiceException("已进行" + verdictRecord.getChessRound() + "回合,游戏已结束");
        }
        if (dto.getActionMode().equals(ActionModeEnum.DESTROYED.getCode()) && StringUtils.isBlank(dto.getTargetChessPiecesNumber())) {
            log.error("请输入目标棋子编号,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("请输入目标棋子编号");
        }


        //查询回合状态
        LambdaQueryWrapper<BizVerdictRecordRound> lqw = Wrappers.lambdaQuery();
        lqw.eq(BizVerdictRecordRound::getVerdictRecordId, verdictRecord.getId());
        lqw.eq(BizVerdictRecordRound::getRoundPeriod, dto.getRoundPeriod());
        lqw.eq(BizVerdictRecordRound::getChessRound, dto.getChessRound());
        lqw.eq(BizVerdictRecordRound::getCampId, dto.getCampId());
        lqw.last("limit 1");
        BizVerdictRecordRound recordRound = verdictRecordRoundMapper.selectById(lqw);
        if (ObjectUtil.isNull(recordRound)) {
            log.error("回合数据不存在");
            throw new ServiceException("回合数据不存在");
        }
        if (recordRound.getStatus() != 0) {
            log.error("本次回合已提交,无法再次提交");
            throw new ServiceException("本次回合已提交,无法再次提交");
        }

//        BizVerdictRecordHistoryVo latestVerdictRecordHistory = this.queryLatestRecord(dto.getVerdictRecordId());
//        if (!ObjectUtils.isEmpty(latestVerdictRecordHistory)) {
//            if (latestVerdictRecordHistory.getIsEndRound().equals(1) && latestVerdictRecordHistory.getUserId().equals(Long.valueOf(dto.getUserId()))) {
//                log.error("本次回合已结束,无法再次提交");
//                throw new ServiceException("本次回合已结束,无法再次提交");
//            }
//            if (!latestVerdictRecordHistory.getIsEndRound().equals(1) && !latestVerdictRecordHistory.getUserId().equals(Long.valueOf(dto.getUserId()))) {
//                log.error("对方回合未结束,您无法提交");
//                throw new ServiceException("对方回合未结束,您无法提交");
//            }
//        }

        BizVerdictRecordChessPiecesVo verdictRecordChessPieces = verdictRecordChessPiecesService.queryByVerdictRecordIdAndChessPiecesNumber(dto.getVerdictRecordId(), dto.getChessPiecesNumber());
        if (ObjectUtils.isEmpty(verdictRecordChessPieces)) {
            log.error("您未部署该棋子,req:{}", JSON.toJSONString(dto));
            throw new ServiceException("您未部署该棋子");
        }

        //保存棋子历史记录
        BizVerdictRecordHistory history = new BizVerdictRecordHistory();
        history.setVerdictRecordId(dto.getVerdictRecordId());
        history.setUserId(dto.getUserId());
        history.setCampId(dto.getCampId());
        history.setChessPiecesNumber(dto.getChessPiecesNumber());
        history.setSelfCoordinate(dto.getSelfCoordinate());
        history.setSelfOffset(dto.getSelfOffset());
        history.setTargetCoordinate(dto.getTargetCoordinate());
        history.setTargetOffset(dto.getTargetOffset());
        history.setActionMode(dto.getActionMode());
        history.setChessRound(verdictRecord.getChessRound());
        history.setRoundPeriod(verdictRecord.getRoundPeriod());
        history.setTargetChessPiecesNumber(dto.getTargetChessPiecesNumber());
        history.setRoundId(recordRound.getId());
        history.setStatus(0);
        //打击效果
        String blowEffect = "";
        BizIndirectBlowEffectVo vo = new BizIndirectBlowEffectVo();
        if (ActionModeEnum.NORMAL.getCode().equals(dto.getActionMode())) {
            verdictRecordChessPieces.setCoordinate(dto.getTargetCoordinate());
            verdictRecordChessPieces.setOffset(dto.getTargetOffset());
            verdictRecordChessPiecesMapper.updateById(BeanUtil.toBean(verdictRecordChessPieces, BizVerdictRecordChessPieces.class));

            String actionDesc = verdictRecordChessPieces.getChessPiecesCampName() + "棋子" + dto.getChessPiecesNumber() + "初始位置:" + dto.getSelfCoordinate() + ", 终点位置:" + dto.getTargetCoordinate() + ", 状态:" + verdictRecordChessPieces.getStatus() + ", 剩余机动值:" + verdictRecordChessPieces.getManeuverValue();
            history.setActionDesc(actionDesc);

        } else if (ActionModeEnum.DESTROYED.getCode().equals(dto.getActionMode())) {
            if (RoundPeriodEnum.first_stage.getCode().equals(verdictRecord.getRoundPeriod())) {
                blowEffect = directFire(dto, vo, verdictRecord, verdictRecordChessPieces, history, blowEffect);

            } else if (RoundPeriodEnum.fourth_stage.getCode().equals(verdictRecord.getRoundPeriod())) {
                indirectFire(dto, verdictRecord, history, verdictRecordChessPieces);
            }
            history.setAttackResult(blowEffect);
        } else if (ActionModeEnum.HIDE.getCode().equals(dto.getActionMode())) {
            BizVerdictRecordChessPieces piecesRecord = new BizVerdictRecordChessPieces();
            //TODO 01是否的问题
            piecesRecord.setIsHide(1);
            verdictRecordChessPiecesMapper.update(piecesRecord, new LambdaQueryWrapper<BizVerdictRecordChessPieces>()
                .eq(BizVerdictRecordChessPieces::getVerdictRecordId, dto.getVerdictRecordId())
                .eq(BizVerdictRecordChessPieces::getChessPiecesNumber, dto.getChessPiecesNumber()));

            String actionDesc = dto.getChessPiecesNumber() + ActionModeEnum.HIDE.getDesc() + "了";
            history.setActionDesc(actionDesc);
        }
        baseMapper.insert(history);

        //更新棋子信息
        QueryAllChessPiecesInfoDto queryAllChessPiecesInfoDto = new QueryAllChessPiecesInfoDto();
        queryAllChessPiecesInfoDto.setVerdictRecordId(String.valueOf(verdictRecord.getId()));
        queryAllChessPiecesInfoDto.setChessRound(verdictRecord.getChessRound());
        List<BizVerdictRecordChessPieces> vos = verdictRecordChessPiecesService.queryAllChessPiecesInfo(queryAllChessPiecesInfoDto);
        history.setChessPiecesInfo(JSON.toJSONString(vos));
        baseMapper.updateById(history);
        return vo;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void chessPiecesActionNew(BizVerdictRecordDetailBo dto) {
        log.info("chessPiecesAction req:{}", JSON.toJSONString(dto));
        BizVerdictRecordVo verdictRecord = verdictRecordMapper.selectVoById(dto.getVerdictRecordId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("裁决记录不存在,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("裁决记录不存在");
        }
        if (VerdictRecordStatusEnum.END.getCode().equals(verdictRecord.getStatus().intValue())) {
            log.error("游戏已结束,回合数:{}", verdictRecord.getChessRound());
            throw new ServiceException("已进行" + verdictRecord.getChessRound() + "回合,游戏已结束");
        }
//        if (dto.getActionMode().equals(ActionModeEnum.DESTROYED.getCode()) && StringUtils.isBlank(dto.getTargetChessPiecesNumber())) {
//            log.error("请输入目标棋子编号,dto:{}", JSON.toJSONString(dto));
//            throw new ServiceException("请输入目标棋子编号");
//        }
//        BizVerdictRecordHistoryVo latestVerdictRecordHistory = this.queryLatestRecord(dto.getVerdictRecordId());
//        if (!ObjectUtils.isEmpty(latestVerdictRecordHistory)) {
//            if (latestVerdictRecordHistory.getIsEndRound().equals(1) && latestVerdictRecordHistory.getUserId().equals(Long.valueOf(dto.getUserId()))) {
//                log.error("本次回合已结束,无法再次提交");
//                throw new ServiceException("本次回合已结束,无法再次提交");
//            }
//            if (!latestVerdictRecordHistory.getIsEndRound().equals(1) && !latestVerdictRecordHistory.getUserId().equals(Long.valueOf(dto.getUserId()))) {
//                log.error("对方回合未结束,您无法提交");
//                throw new ServiceException("对方回合未结束,您无法提交");
//            }
//        }
        //查询回合状态
        LambdaQueryWrapper<BizVerdictRecordRound> lqw = Wrappers.lambdaQuery();
        lqw.eq(BizVerdictRecordRound::getVerdictRecordId, verdictRecord.getId());
        lqw.eq(BizVerdictRecordRound::getRoundPeriod, dto.getRoundPeriod());
        lqw.eq(BizVerdictRecordRound::getChessRound, dto.getChessRound());
        lqw.eq(BizVerdictRecordRound::getCampId, dto.getCampId());
        lqw.last("limit 1");
        BizVerdictRecordRound recordRound = verdictRecordRoundMapper.selectOne(lqw);
        if (ObjectUtil.isNull(recordRound)) {
            log.error("回合数据不存在");
            throw new ServiceException("回合数据不存在");
        }
//        if (recordRound.getStatus() != 0)
        if (recordRound.getStatus() == 1 || recordRound.getStatus() == 3) {
            log.error("本次回合已提交,无法再次提交");
            throw new ServiceException("本次回合已提交,无法再次提交");
        }

        BizVerdictRecordChessPiecesVo verdictRecordChessPieces = verdictRecordChessPiecesService.queryByVerdictRecordIdAndChessPiecesNumber(dto.getVerdictRecordId(), dto.getChessPiecesNumber());
        if (ObjectUtils.isEmpty(verdictRecordChessPieces)) {
            log.error("您未部署该棋子,req:{}", JSON.toJSONString(dto));
            throw new ServiceException("您未部署该棋子");
        }


        //保存棋子历史记录
        BizVerdictRecordHistory history = new BizVerdictRecordHistory();
        history.setVerdictRecordId(dto.getVerdictRecordId());
        history.setUserId(dto.getUserId());
        history.setCampId(dto.getCampId());
        history.setChessPiecesNumber(dto.getChessPiecesNumber());
        history.setSelfCoordinate(dto.getSelfCoordinate());
        history.setSelfOffset(dto.getSelfOffset());
        history.setTargetCoordinate(dto.getTargetCoordinate());
        history.setTargetOffset(dto.getTargetOffset());
        history.setActionMode(dto.getActionMode());
        history.setChessRound(verdictRecord.getChessRound());
        history.setRoundPeriod(Math.toIntExact(dto.getRoundPeriod()));
        history.setTargetChessPiecesNumber(dto.getTargetChessPiecesNumber());
        history.setRoundId(recordRound.getId());
        history.setAttackResult(dto.getAttackResult());
        // 查询是否已有记录
        BizVerdictRecordHistory existingHistory = baseMapper.selectOne(new LambdaQueryWrapper<BizVerdictRecordHistory>()
            .eq(BizVerdictRecordHistory::getVerdictRecordId, dto.getVerdictRecordId())
            .eq(BizVerdictRecordHistory::getUserId, dto.getUserId())
            .eq(BizVerdictRecordHistory::getRoundPeriod, Math.toIntExact(dto.getRoundPeriod()))
            .eq(BizVerdictRecordHistory::getChessPiecesNumber, dto.getChessPiecesNumber()));
        if (recordRound.getStatus() == 0) {
            if (ActionModeEnum.NORMAL.getCode().equals(history.getActionMode())) {
                String actionDesc = "棋子" + dto.getChessPiecesNumber() + "初始位置:" + dto.getSelfCoordinate() + ", 移动到:" + dto.getTargetCoordinate();
                history.setActionDesc(actionDesc);
                history.setMoveInfo(dto.getMoveInfo());
            } else if (ActionModeEnum.DESTROYED.getCode().equals(history.getActionMode())) {
                String actionDesc = verdictRecordChessPieces.getChessPiecesCampName() + "棋子" + history.getChessPiecesNumber() + ", 位置:" + history.getSelfCoordinate() + "攻击了：" + "棋子" + history.getTargetChessPiecesNumber() + ", 位置:" + history.getTargetCoordinate();
                history.setActionDesc(actionDesc);
            } else if (ActionModeEnum.HIDE.getCode().equals(history.getActionMode())) {
                BizVerdictRecordChessPieces piecesRecord = new BizVerdictRecordChessPieces();
                piecesRecord.setIsHide(1);
                verdictRecordChessPiecesMapper.update(piecesRecord, new LambdaQueryWrapper<BizVerdictRecordChessPieces>()
                    .eq(BizVerdictRecordChessPieces::getVerdictRecordId, verdictRecordChessPieces.getVerdictRecordId())
                    .eq(BizVerdictRecordChessPieces::getChessPiecesNumber, verdictRecordChessPieces.getChessPiecesNumber()));

                String actionDesc = dto.getChessPiecesNumber() + ActionModeEnum.HIDE.getDesc() + "了";
                history.setActionDesc(actionDesc);
            } else if (ActionModeEnum.SCOUT.getCode().equals(history.getActionMode())) {
                String actionDesc = "棋子" + dto.getChessPiecesNumber() + "位置:" + dto.getSelfCoordinate() + ", 侦察位置:" + dto.getTargetCoordinate();
                history.setActionDesc(actionDesc);
            } else if (ActionModeEnum.COMMUNICATION.getCode().equals(history.getActionMode())) {
                String actionDesc = "棋子" + dto.getChessPiecesNumber() + "位置:" + dto.getSelfCoordinate() + ", 通信位置:" + dto.getTargetCoordinate();
                history.setActionDesc(actionDesc);
                //添加一条指控裁决记录

            } else if (ActionModeEnum.REACTANCE.getCode().equals(history.getActionMode())) {
                String actionDesc = "棋子" + dto.getChessPiecesNumber() + "位置:" + dto.getSelfCoordinate() + ", 干扰位置:" + dto.getTargetCoordinate();
//                String actionDesc = "棋子" + dto.getChessPiecesNumber() + "位置:" + dto.getSelfCoordinate() + ", 解除压制位置:" + dto.getTargetCoordinate();
                history.setActionDesc(actionDesc);
            } else if (ActionModeEnum.RELEASE_PRESS.getCode().equals(history.getActionMode())) {
//                String actionDesc = "棋子" + dto.getChessPiecesNumber() + "位置:" + dto.getSelfCoordinate() + ", 干扰位置:" + dto.getTargetCoordinate();
                String actionDesc = "棋子" + dto.getChessPiecesNumber() + "位置:" + dto.getSelfCoordinate() + ", 解除压制";
                history.setActionDesc(actionDesc);
            }
            history.setStatus(0);
        }

        if (recordRound.getStatus() == 2) {
            if (ActionModeEnum.NORMAL.getCode().equals(history.getActionMode())) {
                //修改棋子信息
                BizVerdictRecordChessPiecesVo verdictRecordChessPieces2 = verdictRecordChessPiecesService.queryByVerdictRecordIdAndChessPiecesNumber(history.getVerdictRecordId(), history.getChessPiecesNumber());
                verdictRecordChessPieces2.setFromCoordinate(verdictRecordChessPieces.getCoordinate());
                verdictRecordChessPieces2.setFromOffset(verdictRecordChessPieces.getOffset());
                verdictRecordChessPieces2.setCoordinate(history.getTargetCoordinate());
                verdictRecordChessPieces2.setOffset(history.getTargetOffset());
                BizVerdictRecordChessPieces update = BeanUtil.toBean(verdictRecordChessPieces2, BizVerdictRecordChessPieces.class);
                verdictRecordChessPiecesMapper.updateById(update);
            }
//            if (ActionModeEnum.RELEASE_PRESS.getCode().equals(history.getActionMode())) {
//                //修改棋子信息
//                BizVerdictRecordChessPiecesVo verdictRecordChessPieces2 = verdictRecordChessPiecesService.queryByVerdictRecordIdAndChessPiecesNumber(history.getVerdictRecordId(), history.getChessPiecesNumber());
////                verdictRecordChessPieces2.setFromCoordinate(verdictRecordChessPieces.getCoordinate());
////                verdictRecordChessPieces2.setFromOffset(verdictRecordChessPieces.getOffset());
////                verdictRecordChessPieces2.setCoordinate(history.getTargetCoordinate());
////                verdictRecordChessPieces2.setOffset(history.getTargetOffset());
//                verdictRecordChessPieces2.setStatus();
//                BizVerdictRecordChessPieces update = BeanUtil.toBean(verdictRecordChessPieces2, BizVerdictRecordChessPieces.class);
//                verdictRecordChessPiecesMapper.updateById(update);
//            }
        }

        if (existingHistory != null) {
            history.setId(existingHistory.getId());
            if (recordRound.getStatus() == 2) {
                history.setStatus(3);
            }
            baseMapper.updateById(history);
        } else {
            baseMapper.insert(history);
        }
        //通信新增一条指控裁决记录
        if (ActionModeEnum.COMMUNICATION.getCode().equals(history.getActionMode())) {
            history.setActionMode(ActionModeEnum.COMMAND_CONTROL.getCode());
            history.setId(null);
            baseMapper.insert(history);
        }
//        else if (ActionModeEnum.DESTROYED.getCode().equals(history.getActionMode())) {
//            //todo 判断数据库内是否有该棋子行动记录
//            int delNum=baseMapper.deleteByRecord(history);
//            baseMapper.insert(history);
//        }
//        else{
//            baseMapper.insert(history);
//        }
    }

    @Override
    public List<JudgeVo> judge(BizVerdictRecordRoundBo bo) {
        //查询待裁决历史
        LambdaQueryWrapper<BizVerdictRecordHistory> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getVerdictRecordId() != null, BizVerdictRecordHistory::getVerdictRecordId, bo.getVerdictRecordId());
        lqw.eq(bo.getChessRound() != null, BizVerdictRecordHistory::getChessRound, bo.getChessRound());
        lqw.eq(bo.getRoundPeriod() != null, BizVerdictRecordHistory::getRoundPeriod, bo.getRoundPeriod());
        List<BizVerdictRecordHistory> historyList = baseMapper.selectList(lqw);
        List<JudgeVo> judgeVoList = new ArrayList<>();
        for (BizVerdictRecordHistory history : historyList) {
            //打击效果 延时到 提交后裁决
            String blowEffect = "";
            BizIndirectBlowEffectVo vo = new BizIndirectBlowEffectVo();
            BizVerdictRecordChessPiecesVo verdictRecordChessPieces = verdictRecordChessPiecesService.queryByVerdictRecordIdAndChessPiecesNumber(history.getVerdictRecordId(), history.getChessPiecesNumber());


            if (ActionModeEnum.NORMAL.getCode().equals(history.getActionMode())) {
                String actionDesc = verdictRecordChessPieces.getChessPiecesCampName() + "棋子" + history.getChessPiecesNumber() + "初始位置:" + history.getSelfCoordinate() + ", 终点位置:" + history.getTargetCoordinate() + ", 状态:" + verdictRecordChessPieces.getStatus() + ", 剩余机动值:" + verdictRecordChessPieces.getManeuverValue();
                history.setActionDesc(actionDesc);
                history.setStatus(2);
                baseMapper.updateById(history);
            } else if (ActionModeEnum.DESTROYED.getCode().equals(history.getActionMode())) {
                if (history.getActionMode().equals(ActionModeEnum.DESTROYED.getCode())) {
                    JudgeVo judgeVo = directFireJudge(history, bo, vo, blowEffect);
                    insertOrUpdateScore(history);
                    judgeVoList.add(judgeVo);
                }
//                else if (RoundPeriodEnum.FIRST_INDIRECT_FIRE.getCode().equals(verdictRecord.getRoundPeriod()) || RoundPeriodEnum.SECOND_INDIRECT_FIRE.getCode().equals(verdictRecord.getRoundPeriod())) {
//                    indirectFire(dto, verdictRecord, history, verdictRecordChessPieces);
//                }
            } else if (ActionModeEnum.HIDE.getCode().equals(history.getActionMode())) {

                String actionDesc = verdictRecordChessPieces.getChessPiecesNumber() + ActionModeEnum.HIDE.getDesc() + "了";
                history.setActionDesc(actionDesc);
                history.setStatus(2);
                baseMapper.updateById(history);
            }

            QueryAllChessPiecesInfoDto queryAllChessPiecesInfoDto = new QueryAllChessPiecesInfoDto();
            queryAllChessPiecesInfoDto.setVerdictRecordId(String.valueOf(history.getVerdictRecordId()));
            queryAllChessPiecesInfoDto.setChessRound(history.getChessRound());
            List<BizVerdictRecordChessPieces> vos = verdictRecordChessPiecesService.queryAllChessPiecesInfo(queryAllChessPiecesInfoDto);
            history.setChessPiecesInfo(JSON.toJSONString(vos));
            baseMapper.updateById(history);
        }
        //修改recordround的状态
        LambdaQueryWrapper<BizVerdictRecordRound> lqwRound = Wrappers.lambdaQuery();
        lqwRound.eq(bo.getVerdictRecordId() != null, BizVerdictRecordRound::getVerdictRecordId, bo.getVerdictRecordId());
        lqwRound.eq(bo.getChessRound() != null, BizVerdictRecordRound::getChessRound, bo.getChessRound());
        lqwRound.eq(bo.getRoundPeriod() != null, BizVerdictRecordRound::getRoundPeriod, bo.getRoundPeriod());
        BizVerdictRecordRound recordRound = new BizVerdictRecordRound();
        recordRound.setStatus(2);
        verdictRecordRoundMapper.update(recordRound, lqwRound);
        //更改推演局数
        BizVerdictRecord verdictRecord = verdictRecordMapper.selectById(bo.getVerdictRecordId());
        Pair pair = getRoundInfo(verdictRecord);
        //已到最大回合数,比赛结束
        int nextChessRound = Integer.parseInt(String.valueOf(pair.getKey()));
        int nextRoundPeriod = Integer.parseInt(String.valueOf(pair.getValue()));
        if (nextChessRound > verdictRecord.getChessRoundLimit()) {
            verdictRecord.setStatus(Long.valueOf(VerdictRecordStatusEnum.END.getCode()));
        } else {
            verdictRecord.setChessRound((long) nextChessRound);
            verdictRecord.setRoundPeriod(nextRoundPeriod);
        }
        verdictRecordMapper.updateById(verdictRecord);


        return judgeVoList;
    }

    @Override
    public List<BizVerdictRecordHistoryVo> getAwaitJudge(BizVerdictRecordRoundBo bo) {
        LambdaQueryWrapper<BizVerdictRecordHistory> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getVerdictRecordId() != null, BizVerdictRecordHistory::getVerdictRecordId, bo.getVerdictRecordId());
        lqw.eq(bo.getChessRound() != null, BizVerdictRecordHistory::getChessRound, bo.getChessRound());
        lqw.eq(bo.getRoundPeriod() != null, BizVerdictRecordHistory::getRoundPeriod, bo.getRoundPeriod());
        lqw.eq(bo.getCampId() != null, BizVerdictRecordHistory::getCampId, bo.getCampId());
        List<BizVerdictRecordHistoryVo> historyList = baseMapper.selectVoList(lqw);
        return historyList;
    }

    private JudgeVo directFireJudge(BizVerdictRecordHistory history, BizVerdictRecordRoundBo bo, BizIndirectBlowEffectVo vo, String blowEffect) {
        JudgeVo judgeVo = new JudgeVo();
        BizVerdictRecord verdictRecord = verdictRecordMapper.selectById(history.getVerdictRecordId());
        //初始化
        vo.setAttackScore(Long.valueOf(NumberUtils.INTEGER_ZERO));
        int[] dices = this.getDice(2);
        vo.setDiceScore((long) Arrays.stream(dices).sum());
        vo.setBlowEffect(blowEffect);

        //求两个坐标距离
//        int distance = getDistance(history);
        int distance = 0;
        //查询两方棋子
        BizChessPieces selfChessPieces = chessPiecesService.queryByChessPiecesNumber(history.getChessPiecesNumber());
        BizChessPieces targetChessPieces = chessPiecesService.queryByChessPiecesNumber(history.getTargetChessPiecesNumber());
        BizVerdictRule verdictRule = null;
        //查询规则
        if (StringUtils.isNotBlank(selfChessPieces.getEquipment())) {
            String[] selfEquipments = selfChessPieces.getEquipment().split(ArbiterConstant.ENGLISH_COMMA);
            String targetChessPiecesTypeName = targetChessPieces.getChessPiecesTypeName();
            for (String selfEquipment : selfEquipments) {
                List<BizVerdictRule> recordList = verdictRuleService.queryRecord(selfEquipment, targetChessPiecesTypeName, distance);
                if (!CollectionUtils.isEmpty(recordList)) {
                    verdictRule = recordList.get(0);
                    break;
                }
            }
            BizVerdictBlowEffect effectRecord = new BizVerdictBlowEffect();
            if (ObjectUtil.isNotNull(verdictRule)) {
                int verdictType = verdictRule.getVerdictType().intValue();
                int attackScore = verdictRule.getAttackScore().intValue();

                effectRecord = verdictBlowEffectService.queryRecord(verdictType, attackScore, Math.toIntExact(vo.getDiceScore()));
                if (!ObjectUtils.isEmpty(effectRecord)) {
                    history.setExt(String.valueOf(effectRecord.getId()));

                    BeanUtils.copyProperties(effectRecord, vo);

                    blowEffect = effectRecord.getBlowEffect();
                }
            }
            judgeVo.setRule(verdictRule);
            judgeVo.setDice(dices);
            judgeVo.setBlowEffect(effectRecord);
        }

        //击毁
        if (BlowEffectEnum.KF.getCode().equals(blowEffect) || BlowEffectEnum.K.getCode().equals(blowEffect)) {
            //更新棋子状态为被击毁
            BizVerdictRecordChessPieces record = verdictRecordChessPiecesMapper.selectOne(new LambdaQueryWrapper<BizVerdictRecordChessPieces>().eq(BizVerdictRecordChessPieces::getVerdictRecordId, Long.valueOf(history.getVerdictRecordId())).eq(BizVerdictRecordChessPieces::getChessPiecesNumber, history.getTargetChessPiecesNumber()));
//            record.setStatus(ChessPiecesStatusEnum.DESTROYED.getCode());
            record.setStatus("");
            verdictRecordChessPiecesMapper.updateById(record);

            //如果该方所有棋子都被击毁,游戏结束
            List<BizVerdictRecordChessPieces> campRecords = verdictRecordChessPiecesMapper.selectList(new LambdaQueryWrapper<BizVerdictRecordChessPieces>().eq(BizVerdictRecordChessPieces::getVerdictRecordId, history.getVerdictRecordId()).eq(BizVerdictRecordChessPieces::getChessPiecesCampId, record.getChessPiecesCampId()));
            //如果一方棋子都被毁,游戏结束
            List<BizVerdictRecordChessPieces> normalRecords = campRecords.stream().filter(e -> !e.getStatus().equals(ChessPiecesStatusEnum.DESTROYED.getCode())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(normalRecords)) {
                verdictRecord.setStatus(Long.valueOf(VerdictRecordStatusEnum.END.getCode()));
                verdictRecordMapper.updateById(BeanUtil.toBean(verdictRecord, BizVerdictRecord.class));
            }

        } else if (BlowEffectEnum.S.getCode().equals(blowEffect)) {
            //更新棋子状态为被压制
            BizVerdictRecordChessPieces record = verdictRecordChessPiecesMapper.selectOne(new LambdaQueryWrapper<BizVerdictRecordChessPieces>().eq(BizVerdictRecordChessPieces::getVerdictRecordId, history.getVerdictRecordId()).eq(BizVerdictRecordChessPieces::getChessPiecesNumber, history.getTargetChessPiecesNumber()));
//            record.setStatus(ChessPiecesStatusEnum.REPRESSED.getCode());
            record.setStatus("");
            verdictRecordChessPiecesMapper.updateById(record);
        }
        BizVerdictRecordChessPiecesVo verdictRecordChessPieces = verdictRecordChessPiecesService.queryByVerdictRecordIdAndChessPiecesNumber(history.getVerdictRecordId(), history.getChessPiecesNumber());

        //目标棋子
        BizVerdictRecordChessPiecesVo targetVerdictRecordChessPieces = verdictRecordChessPiecesService.queryByVerdictRecordIdAndChessPiecesNumber(history.getVerdictRecordId(), history.getTargetChessPiecesNumber());

        String actionDesc = verdictRecordChessPieces.getChessPiecesCampName() + "棋子" + history.getChessPiecesNumber() + ", 类型:" + selfChessPieces.getChessPiecesTypeName() + ", 武器:" + selfChessPieces.getEquipment() + ", 位置:" + history.getSelfCoordinate() + ", 状态:" + verdictRecordChessPieces.getStatus()
            + ";\n目标编号:" + history.getTargetChessPiecesNumber() + ", 目标类型:" + targetChessPieces.getChessPiecesTypeName() + ", 目标位置:" + targetVerdictRecordChessPieces.getCoordinate() + ", 目标状态" + targetVerdictRecordChessPieces.getStatus()
            + ";\n距离:" + distance + ", 攻击等级:" + blowEffect + ", 骰子数1:" + dices[0] + ", 骰子数2:" + dices[1] + ", 裁决结果:" + BlowEffectEnum.getDescByCode(blowEffect);
        history.setActionDesc(actionDesc);
        history.setAttackResult(blowEffect);
        history.setAttackScore(BlowEffectEnum.getScoreByCode(blowEffect));
        history.setStatus(2);
        //更新棋子信息json
        QueryAllChessPiecesInfoDto queryAllChessPiecesInfoDto = new QueryAllChessPiecesInfoDto();
        queryAllChessPiecesInfoDto.setVerdictRecordId(String.valueOf(verdictRecord.getId()));
        queryAllChessPiecesInfoDto.setChessRound(verdictRecord.getChessRound());
        List<BizVerdictRecordChessPieces> vos = verdictRecordChessPiecesService.queryAllChessPiecesInfo(queryAllChessPiecesInfoDto);
        history.setChessPiecesInfo(JSON.toJSONString(vos));
        baseMapper.updateById(history);
        return judgeVo;
    }

    public int[] getDice(Integer diceCount) {
        if (ObjectUtil.isEmpty(diceCount)) {
            throw new ServiceException("获取骰子数量失败");
        }
        int[] diceArr = new int[diceCount];
        for (int i = 0; i < diceCount; i++) {
            diceArr[i] = RandomUtil.randomInt(6) + 1;
        }
        return diceArr;
    }

    private int getDistance(BizVerdictRecordHistory dto) {
        //求两个坐标距离
        int selfX = Integer.parseInt(dto.getSelfCoordinate().substring(0, 2));
        int selfY = Integer.parseInt(dto.getSelfCoordinate().substring(2));

        int targetX = Integer.parseInt(dto.getTargetCoordinate().substring(0, 2));
        int targetY = Integer.parseInt(dto.getTargetCoordinate().substring(2));

        double sum = Math.pow(selfX - targetX, 2) + Math.pow(selfY - targetY, 2);
        return (int) Math.sqrt(sum) * 2;
    }

    public void insertOrUpdateScore(BizVerdictRecordHistory history) {
        //查询是否有作战效果的打分记录
        LambdaQueryWrapper<BizVerdictRecordScore> lqw = Wrappers.lambdaQuery();
        lqw.eq(BizVerdictRecordScore::getVerdictRecordId, history.getVerdictRecordId());
        lqw.eq(BizVerdictRecordScore::getCampId, history.getCampId());
        lqw.eq(BizVerdictRecordScore::getChessRound, history.getChessRound());
        lqw.eq(BizVerdictRecordScore::getType, 3);
        lqw.last("limit 1");
        BizVerdictRecordScore bizVerdictRecordScore = verdictRecordScoreMapper.selectOne(lqw);
        if (ObjectUtil.isNotNull(bizVerdictRecordScore)) {
            //拼接打分标准和分数
            bizVerdictRecordScore.setStandard(bizVerdictRecordScore.getStandard() + "<br/>" + history.getActionDesc());
            bizVerdictRecordScore.setScore(bizVerdictRecordScore.getScore() + history.getAttackScore());
            verdictRecordScoreMapper.updateById(bizVerdictRecordScore);
        } else {
            //添加一条类型为3 的打分记录
            bizVerdictRecordScore = new BizVerdictRecordScore();
            bizVerdictRecordScore.setVerdictRecordId(history.getVerdictRecordId());
            bizVerdictRecordScore.setUserId(history.getUserId());
            bizVerdictRecordScore.setCampId(history.getCampId());
            bizVerdictRecordScore.setChessRound(history.getChessRound());
            bizVerdictRecordScore.setType(3);
            bizVerdictRecordScore.setContent("打分效果");
            bizVerdictRecordScore.setStandard(history.getActionDesc());
            bizVerdictRecordScore.setScore(history.getAttackScore());
            verdictRecordScoreMapper.insert(bizVerdictRecordScore);
        }

    }

    @Override
    public void chessPiecesActionContinue(BizVerdictRuleBo dto) {
        int verdictType = dto.getVerdictType().intValue();
        int attackScore = dto.getAttackScore().intValue();
        int diceScore = Arrays.stream(dto.getDices()).sum();
        String blowEffect = "";
        BizIndirectBlowEffectVo vo = new BizIndirectBlowEffectVo();
        BizVerdictRecordHistory history = verdictRecordHistoryMapper.selectById(dto.getHistoryId());
        BizVerdictRecord verdictRecord = verdictRecordMapper.selectById(history.getVerdictRecordId());
        BizVerdictRecordChessPiecesVo verdictRecordChessPieces = verdictRecordChessPiecesService.queryByVerdictRecordIdAndChessPiecesNumber(history.getVerdictRecordId(), history.getChessPiecesNumber());
        BizChessPieces selfChessPieces = chessPiecesService.queryByChessPiecesNumber(history.getChessPiecesNumber());
        BizChessPieces targetChessPieces = chessPiecesService.queryByChessPiecesNumber(history.getTargetChessPiecesNumber());

        BizVerdictBlowEffect effectRecord = verdictBlowEffectService.queryRecord(verdictType, attackScore, diceScore);
        if (!ObjectUtils.isEmpty(effectRecord)) {
            history.setExt(String.valueOf(effectRecord.getId()));

            BeanUtils.copyProperties(effectRecord, vo);

            blowEffect = effectRecord.getBlowEffect();
        }
        //击毁
        if (BlowEffectEnum.KF.getCode().equals(blowEffect) || BlowEffectEnum.K.getCode().equals(blowEffect)) {
            //更新棋子状态为被击毁
            BizVerdictRecordChessPieces record = verdictRecordChessPiecesMapper.selectOne(new LambdaQueryWrapper<BizVerdictRecordChessPieces>().eq(BizVerdictRecordChessPieces::getVerdictRecordId, Long.valueOf(history.getVerdictRecordId())).eq(BizVerdictRecordChessPieces::getChessPiecesNumber, history.getTargetChessPiecesNumber()));
//            record.setStatus(ChessPiecesStatusEnum.DESTROYED.getCode());
            record.setStatus("");
            verdictRecordChessPiecesMapper.updateById(record);

            //如果该方所有棋子都被击毁,游戏结束
            List<BizVerdictRecordChessPieces> campRecords = verdictRecordChessPiecesMapper.selectList(new LambdaQueryWrapper<BizVerdictRecordChessPieces>().eq(BizVerdictRecordChessPieces::getVerdictRecordId, history.getVerdictRecordId()).eq(BizVerdictRecordChessPieces::getChessPiecesCampId, record.getChessPiecesCampId()));
            //如果一方棋子都被毁,游戏结束
            List<BizVerdictRecordChessPieces> normalRecords = campRecords.stream().filter(e -> !e.getStatus().equals(ChessPiecesStatusEnum.DESTROYED.getCode())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(normalRecords)) {
                verdictRecord.setStatus(Long.valueOf(VerdictRecordStatusEnum.END.getCode()));
                verdictRecordMapper.updateById(BeanUtil.toBean(verdictRecord, BizVerdictRecord.class));
            }

        } else if (BlowEffectEnum.S.getCode().equals(blowEffect)) {
            //更新棋子状态为被压制
            BizVerdictRecordChessPieces record = verdictRecordChessPiecesMapper.selectOne(new LambdaQueryWrapper<BizVerdictRecordChessPieces>().eq(BizVerdictRecordChessPieces::getVerdictRecordId, history.getVerdictRecordId()).eq(BizVerdictRecordChessPieces::getChessPiecesNumber, history.getTargetChessPiecesNumber()));
//            record.setStatus(ChessPiecesStatusEnum.REPRESSED.getCode());
            record.setStatus("");
            verdictRecordChessPiecesMapper.updateById(record);
        }

        //目标棋子
        BizVerdictRecordChessPiecesVo targetVerdictRecordChessPieces = verdictRecordChessPiecesService.queryByVerdictRecordIdAndChessPiecesNumber(history.getVerdictRecordId(), history.getTargetChessPiecesNumber());

        String actionDesc = verdictRecordChessPieces.getChessPiecesCampName() + "棋子" + history.getChessPiecesNumber() + ", 类型:" + selfChessPieces.getChessPiecesTypeName() + ", 武器:" + selfChessPieces.getEquipment() + ", 位置:" + history.getSelfCoordinate() + ", 状态:" + verdictRecordChessPieces.getStatus()
            + ";\n目标编号:" + history.getTargetChessPiecesNumber() + ", 目标类型:" + targetChessPieces.getChessPiecesTypeName() + ", 目标位置:" + targetVerdictRecordChessPieces.getCoordinate() + ", 目标状态" + targetVerdictRecordChessPieces.getStatus()
            + ";\n距离:" + dto.getDistance() + ", 攻击等级:" + blowEffect + ", 骰子数1:" + dto.getDices()[0] + ", 骰子数2:" + dto.getDices()[1] + ", 裁决结果:" + BlowEffectEnum.getDescByCode(blowEffect);
        history.setActionDesc(actionDesc);
        history.setAttackResult(blowEffect);
//        baseMapper.updateById(history);
        //更新棋子信息
        QueryAllChessPiecesInfoDto queryAllChessPiecesInfoDto = new QueryAllChessPiecesInfoDto();
        queryAllChessPiecesInfoDto.setVerdictRecordId(String.valueOf(verdictRecord.getId()));
        queryAllChessPiecesInfoDto.setChessRound(verdictRecord.getChessRound());
        List<BizVerdictRecordChessPieces> vos = verdictRecordChessPiecesService.queryAllChessPiecesInfo(queryAllChessPiecesInfoDto);
        history.setChessPiecesInfo(JSON.toJSONString(vos));
        baseMapper.updateById(history);
    }

    private BizVerdictRule directFireNew(BizVerdictRecordDetailBo dto, BizIndirectBlowEffectVo vo, BizVerdictRecordVo verdictRecord, BizVerdictRecordChessPiecesVo verdictRecordChessPieces, BizVerdictRecordHistory history, String blowEffect) {
        //初始化
        vo.setAttackScore(Long.valueOf(NumberUtils.INTEGER_ZERO));
//        vo.setDiceScore((long) diceScore);
        vo.setBlowEffect(blowEffect);

        //求两个坐标距离
        int distance = getDistance(dto);
        //查询两方棋子
        BizChessPieces selfChessPieces = chessPiecesService.queryByChessPiecesNumber(dto.getChessPiecesNumber());
        BizChessPieces targetChessPieces = chessPiecesService.queryByChessPiecesNumber(dto.getTargetChessPiecesNumber());
        BizVerdictRule verdictRule = new BizVerdictRule();
        //查询规则
        if (StringUtils.isNotBlank(selfChessPieces.getEquipment())) {
            String[] selfEquipments = selfChessPieces.getEquipment().split(ArbiterConstant.ENGLISH_COMMA);
            String targetChessPiecesTypeName = targetChessPieces.getChessPiecesTypeName();
            for (String selfEquipment : selfEquipments) {
                List<BizVerdictRule> recordList = verdictRuleService.queryRecord(selfEquipment, targetChessPiecesTypeName, distance);
                if (!CollectionUtils.isEmpty(recordList)) {
                    verdictRule = recordList.get(0);
                    break;
                }
            }
        }
        return verdictRule;
    }

    /**
     * direct fire
     *
     * @param dto
     * @param vo
     * @param verdictRecord
     * @param verdictRecordChessPieces
     * @param history
     * @param blowEffect
     */
    private String directFire(BizVerdictRecordDetailBo dto, BizIndirectBlowEffectVo vo, BizVerdictRecordVo verdictRecord, BizVerdictRecordChessPiecesVo verdictRecordChessPieces, BizVerdictRecordHistory history, String blowEffect) {
        Random random = new Random();
        int diceOne = random.nextInt(6) + 1;
        int diceTwo = random.nextInt(6) + 1;
        int diceScore = diceOne + diceTwo;

        //初始化
        vo.setAttackScore(Long.valueOf(NumberUtils.INTEGER_ZERO));
        vo.setDiceScore((long) diceScore);
        vo.setBlowEffect(blowEffect);

        //求两个坐标距离
        int distance = getDistance(dto);
        //查询两方棋子
        BizChessPieces selfChessPieces = chessPiecesService.queryByChessPiecesNumber(dto.getChessPiecesNumber());
        BizChessPieces targetChessPieces = chessPiecesService.queryByChessPiecesNumber(dto.getTargetChessPiecesNumber());

        //查询规则
        if (StringUtils.isNotBlank(selfChessPieces.getEquipment())) {
            BizVerdictRule verdictRule = null;
            String[] selfEquipments = selfChessPieces.getEquipment().split(ArbiterConstant.ENGLISH_COMMA);
            String targetChessPiecesTypeName = targetChessPieces.getChessPiecesTypeName();
            for (String selfEquipment : selfEquipments) {
                List<BizVerdictRule> recordList = verdictRuleService.queryRecord(selfEquipment, targetChessPiecesTypeName, distance);
                if (!CollectionUtils.isEmpty(recordList)) {
                    verdictRule = recordList.get(0);
                    break;
                }
            }
            if (!ObjectUtils.isEmpty(verdictRule)) {
                int verdictType = verdictRule.getVerdictType().intValue();
                int attackScore = verdictRule.getAttackScore().intValue();

                BizVerdictBlowEffect effectRecord = verdictBlowEffectService.queryRecord(verdictType, attackScore, diceScore);
                if (!ObjectUtils.isEmpty(effectRecord)) {
                    history.setExt(String.valueOf(effectRecord.getId()));

                    BeanUtils.copyProperties(effectRecord, vo);

                    blowEffect = effectRecord.getBlowEffect();
                }
            }
        }

        //击毁
        if (BlowEffectEnum.KF.getCode().equals(blowEffect) || BlowEffectEnum.K.getCode().equals(blowEffect)) {
            //更新棋子状态为被击毁
            BizVerdictRecordChessPieces record = verdictRecordChessPiecesMapper.selectOne(new LambdaQueryWrapper<BizVerdictRecordChessPieces>().eq(BizVerdictRecordChessPieces::getVerdictRecordId, Long.valueOf(dto.getVerdictRecordId())).eq(BizVerdictRecordChessPieces::getChessPiecesNumber, dto.getTargetChessPiecesNumber()));
//            record.setStatus(ChessPiecesStatusEnum.DESTROYED.getCode());
            verdictRecordChessPiecesMapper.updateById(record);

            //如果该方所有棋子都被击毁,游戏结束
            List<BizVerdictRecordChessPieces> campRecords = verdictRecordChessPiecesMapper.selectList(new LambdaQueryWrapper<BizVerdictRecordChessPieces>().eq(BizVerdictRecordChessPieces::getVerdictRecordId, dto.getVerdictRecordId()).eq(BizVerdictRecordChessPieces::getChessPiecesCampId, record.getChessPiecesCampId()));
            //如果一方棋子都被毁,游戏结束
            List<BizVerdictRecordChessPieces> normalRecords = campRecords.stream().filter(e -> !e.getStatus().equals(ChessPiecesStatusEnum.DESTROYED.getCode())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(normalRecords)) {
                verdictRecord.setStatus(Long.valueOf(VerdictRecordStatusEnum.END.getCode()));
                verdictRecordMapper.updateById(BeanUtil.toBean(verdictRecord, BizVerdictRecord.class));
            }

        } else if (BlowEffectEnum.S.getCode().equals(blowEffect)) {
            //更新棋子状态为被压制
            BizVerdictRecordChessPieces record = verdictRecordChessPiecesMapper.selectOne(new LambdaQueryWrapper<BizVerdictRecordChessPieces>().eq(BizVerdictRecordChessPieces::getVerdictRecordId, dto.getVerdictRecordId()).eq(BizVerdictRecordChessPieces::getChessPiecesNumber, dto.getTargetChessPiecesNumber()));
//            record.setStatus(ChessPiecesStatusEnum.REPRESSED.getCode());
            verdictRecordChessPiecesMapper.updateById(record);
        }

        //目标棋子
        BizVerdictRecordChessPiecesVo targetVerdictRecordChessPieces = verdictRecordChessPiecesService.queryByVerdictRecordIdAndChessPiecesNumber(dto.getVerdictRecordId(), dto.getTargetChessPiecesNumber());

        String actionDesc = verdictRecordChessPieces.getChessPiecesCampName() + "棋子" + dto.getChessPiecesNumber() + ", 类型:" + selfChessPieces.getChessPiecesTypeName() + ", 武器:" + selfChessPieces.getEquipment() + ", 位置:" + dto.getSelfCoordinate() + ", 状态:" + verdictRecordChessPieces.getStatus()
            + ";\n目标编号:" + dto.getTargetChessPiecesNumber() + ", 目标类型:" + targetChessPieces.getChessPiecesTypeName() + ", 目标位置:" + targetVerdictRecordChessPieces.getCoordinate() + ", 目标状态" + targetVerdictRecordChessPieces.getStatus()
            + ";\n距离:" + distance + ", 攻击等级:" + blowEffect + ", 骰子数1:" + diceOne + ", 骰子数2:" + diceTwo + ", 裁决结果:" + BlowEffectEnum.getDescByCode(blowEffect);
        history.setActionDesc(actionDesc);

        return blowEffect;
    }

    /**
     * 间瞄
     *
     * @param dto
     * @param verdictRecord
     * @param history
     */
    private void indirectFire(BizVerdictRecordDetailBo dto, BizVerdictRecordVo verdictRecord, BizVerdictRecordHistory history, BizVerdictRecordChessPiecesVo verdictRecordChessPieces) {
        //间瞄规则里没有此棋子
        int count = indirectVerdictRuleService.countByChessPiecesNumber(dto.getChessPiecesNumber());
        if (count == 0) {
            return;
        }
        //求两个坐标距离
        int distance = getDistance(dto);
        //查询目标阵营
        Long targetCampId = dto.getUserId().equals(verdictRecord.getFirstUserId()) ? verdictRecord.getSecondCampId() : verdictRecord.getFirstCampId();
        //查询射击类型
        Integer fireType = indirectVerdictRuleService.queryFireType(verdictRecord.getId(), targetCampId, dto.getTargetCoordinate(), distance);
        //查询间瞄规则
        BizIndirectVerdictRule indirectRule = indirectVerdictRuleService.queryIndirectRuleRecord(dto.getChessPiecesNumber(), fireType);
        if (ObjectUtils.isEmpty(indirectRule)) {
            return;
        }
        //保存间瞄记录
        BizIndirectRecord indirectRecord = new BizIndirectRecord();
        indirectRecord.setVerdictRecordId(verdictRecord.getId());
        indirectRecord.setTargetCampId(targetCampId);
        indirectRecord.setTargetCoordinate(dto.getTargetCoordinate());
        indirectRecord.setVerdictRecordHistoryId(history.getId());
        indirectRecord.setIndirectVerdictRuleId(indirectRule.getId());
        //生效回合
        Long effectChessRound = verdictRecord.getChessRound() + indirectRule.getDelayRound();
        indirectRecord.setEffectChessRound(effectChessRound);
        indirectRecordMapper.insert(indirectRecord);

        //查询两方棋子
        BizChessPieces selfChessPieces = chessPiecesService.queryByChessPiecesNumber(dto.getChessPiecesNumber());
        BizChessPieces targetChessPieces = chessPiecesService.queryByChessPiecesNumber(dto.getTargetChessPiecesNumber());

        //目标棋子
        BizVerdictRecordChessPiecesVo targetVerdictRecordChessPieces = verdictRecordChessPiecesService.queryByVerdictRecordIdAndChessPiecesNumber(Long.valueOf(dto.getVerdictRecordId()), dto.getTargetChessPiecesNumber());
        String actionDesc = "间瞄射击：" + verdictRecordChessPieces.getChessPiecesCampName() + "棋子" + dto.getChessPiecesNumber() + ", 类型:" + selfChessPieces.getChessPiecesTypeName() + ", 武器:" + selfChessPieces.getEquipment() + ", 位置:" + dto.getSelfCoordinate() + ", 状态:" + verdictRecordChessPieces.getStatus()
            + ";\n目标编号:" + dto.getTargetChessPiecesNumber() + ", 目标类型:" + targetChessPieces.getChessPiecesTypeName() + ", 目标位置:" + targetVerdictRecordChessPieces.getCoordinate() + ", 目标状态" + targetVerdictRecordChessPieces.getStatus()
            + ";\n距离:" + distance;
        history.setActionDesc(actionDesc);

        //延迟回合为0
        if (indirectRule.getDelayRound() == 0) {
            this.calIndirectAttack(verdictRecord.getId(), verdictRecord.getChessRound());
        }
    }


    /**
     * 查询攻击坐标距离
     *
     * @param dto
     * @return
     */
    private int getDistance(BizVerdictRecordDetailBo dto) {
        //求两个坐标距离
        int selfX = Integer.parseInt(dto.getSelfCoordinate().substring(0, 2));
        int selfY = Integer.parseInt(dto.getSelfCoordinate().substring(2));

        int targetX = Integer.parseInt(dto.getTargetCoordinate().substring(0, 2));
        int targetY = Integer.parseInt(dto.getTargetCoordinate().substring(2));

        double sum = Math.pow(selfX - targetX, 2) + Math.pow(selfY - targetY, 2);
        return (int) Math.sqrt(sum) * 2;
    }


    /**
     * 间瞄效果
     *
     * @param verdictRecordId
     * @param currentRound
     */
    private void calIndirectAttack(Long verdictRecordId, Long currentRound) {
        List<BizIndirectRecord> indirectRecordList = indirectRecordMapper.selectList(new LambdaQueryWrapper<BizIndirectRecord>()
            .eq(BizIndirectRecord::getVerdictRecordId, verdictRecordId)
            .eq(BizIndirectRecord::getEffectChessRound, currentRound)
            .eq(BizIndirectRecord::getStatus, IndirectRecordStatusEnum.INIT.getCode()));
        if (CollectionUtils.isEmpty(indirectRecordList)) {
            log.info("VerdictRecordHistoryServiceImpl indirectEffect record is empty,verdictRecordId:{} currentRound:{}", verdictRecordId, currentRound);
            return;
        }
        indirectRecordList.forEach(indirectRecord -> {
            try {
                //间瞄裁决规则
                BizIndirectVerdictRule rule = indirectVerdictRuleMapper.selectById(indirectRecord.getIndirectVerdictRuleId());
                //间瞄效果
                this.indirectEffect(indirectRecord, rule, verdictRecordId);

                indirectRecord.setStatus(IndirectRecordStatusEnum.SUCCESS.getCode());
            } catch (Exception e) {
                indirectRecord.setStatus(IndirectRecordStatusEnum.FAILURE.getCode());
            } finally {
                indirectRecordMapper.updateById(indirectRecord);
            }
        });
    }


    /**
     * 间瞄效果
     *
     * @param indirectRecord
     * @param rule
     * @param verdictRecordId
     */
    private void indirectEffect(BizIndirectRecord indirectRecord, BizIndirectVerdictRule rule, Long verdictRecordId) {
        Random random = new Random();
        int diceOne = random.nextInt(6) + 1;
        int diceTwo = random.nextInt(6) + 1;
        int diceScore = diceOne + diceTwo;

        //弹着六宫格
        Long fallAttackScore = rule.getFallAttackScore();
        List<BizVerdictRecordChessPieces> fallChessPiecesList = queryEffectChessPiecesList(verdictRecordId, Arrays.asList(indirectRecord.getTargetCoordinate()), indirectRecord.getTargetCampId());
        if (!CollectionUtils.isEmpty(fallChessPiecesList)) {
            BizIndirectBlowEffect fallEffect = indirectBlowEffectService.getByAttackScoreAndDiceScore(fallAttackScore, diceScore);
            if (!ObjectUtils.isEmpty(fallEffect)) {
                if (BlowEffectEnum.K.getCode().equals(fallEffect.getBlowEffect())) {
//                    verdictRecordChessPiecesService.updateStatusByIds(ChessPiecesStatusEnum.DESTROYED.getCode(), fallChessPiecesList.stream().map(BizVerdictRecordChessPieces::getId).collect(Collectors.toList()));
                } else if (BlowEffectEnum.S.getCode().equals(fallEffect.getBlowEffect())) {
//                    verdictRecordChessPiecesService.updateStatusByIds(ChessPiecesStatusEnum.REPRESSED.getCode(), fallChessPiecesList.stream().map(BizVerdictRecordChessPieces::getId).collect(Collectors.toList()));
                }
            }
        }

        //相邻六宫格
        Long neighborAttackScore = rule.getNeighborAttackScore();
        List<String> neighborCoordinateList = queryNeighbourCoordinate(indirectRecord.getTargetCoordinate());
        if (CollectionUtils.isEmpty(neighborCoordinateList)) {
            return;
        }
        List<BizVerdictRecordChessPieces> neighborChessPiecesList = queryEffectChessPiecesList(verdictRecordId, neighborCoordinateList, indirectRecord.getTargetCampId());
        if (!CollectionUtils.isEmpty(neighborChessPiecesList)) {
            BizIndirectBlowEffect neighborEffect = indirectBlowEffectService.getByAttackScoreAndDiceScore(neighborAttackScore, diceScore);
            if (!ObjectUtils.isEmpty(neighborEffect)) {
                if (BlowEffectEnum.K.getCode().equals(neighborEffect.getBlowEffect())) {
//                    verdictRecordChessPiecesService.updateStatusByIds(ChessPiecesStatusEnum.DESTROYED.getCode(), neighborChessPiecesList.stream().map(BizVerdictRecordChessPieces::getId).collect(Collectors.toList()));
                } else if (BlowEffectEnum.S.getCode().equals(neighborEffect.getBlowEffect())) {
//                    verdictRecordChessPiecesService.updateStatusByIds(ChessPiecesStatusEnum.REPRESSED.getCode(), neighborChessPiecesList.stream().map(BizVerdictRecordChessPieces::getId).collect(Collectors.toList()));
                }
            }
        }

        //外围六宫格
        Long peripheryAttackScore = rule.getPeripheryAttackScore();
        List<String> peripheryCoordinates = new ArrayList<>();
        neighborCoordinateList.forEach(neighborCoordinate -> {
            List<String> coordinates = queryNeighbourCoordinate(neighborCoordinate);
            if (!CollectionUtils.isEmpty(coordinates)) {
                peripheryCoordinates.addAll(coordinates);
            }
        });
        if (CollectionUtils.isEmpty(peripheryCoordinates)) {
            return;
        }
        List<BizVerdictRecordChessPieces> peripheryChessPiecesList = queryEffectChessPiecesList(verdictRecordId, peripheryCoordinates, indirectRecord.getTargetCampId());
        if (!CollectionUtils.isEmpty(peripheryChessPiecesList)) {
            BizIndirectBlowEffect peripheryEffect = indirectBlowEffectService.getByAttackScoreAndDiceScore(peripheryAttackScore, diceScore);
            if (!ObjectUtils.isEmpty(peripheryEffect)) {
                if (BlowEffectEnum.K.getCode().equals(peripheryEffect.getBlowEffect())) {
//                    verdictRecordChessPiecesService.updateStatusByIds(ChessPiecesStatusEnum.DESTROYED.getCode(), peripheryChessPiecesList.stream().map(BizVerdictRecordChessPieces::getId).collect(Collectors.toList()));
                } else if (BlowEffectEnum.S.getCode().equals(peripheryEffect.getBlowEffect())) {
//                    verdictRecordChessPiecesService.updateStatusByIds(ChessPiecesStatusEnum.REPRESSED.getCode(), peripheryChessPiecesList.stream().map(BizVerdictRecordChessPieces::getId).collect(Collectors.toList()));
                }
            }
        }
    }


    private List<BizVerdictRecordChessPieces> queryEffectChessPiecesList(Long verdictRecordId, List<String> coordinateList, Long targetCampId) {
        return verdictRecordChessPiecesMapper.selectList(new LambdaQueryWrapper<BizVerdictRecordChessPieces>()
            .eq(BizVerdictRecordChessPieces::getVerdictRecordId, verdictRecordId)
            .eq(BizVerdictRecordChessPieces::getChessPiecesCampId, targetCampId)
            .in(BizVerdictRecordChessPieces::getCoordinate, coordinateList)
            .in(BizVerdictRecordChessPieces::getStatus, Arrays.asList(ChessPiecesStatusEnum.NORMAL.getCode(), ChessPiecesStatusEnum.REPRESSED.getCode())));
    }


    /**
     * 查询相邻坐标
     *
     * @param coordinate
     * @return
     */
    private List<String> queryNeighbourCoordinate(String coordinate) {
        List<String> resultList = new LinkedList<>();
        //横坐标
        int abscissa = Integer.parseInt(coordinate.substring(0, 2));
        //纵坐标
        int ordinate = Integer.parseInt(coordinate.substring(2));

        String coordinateOne = build(abscissa - 1, ordinate);
        String coordinateTwo = build(abscissa + 1, ordinate);
        String coordinateThree = build(abscissa, ordinate - 1);
        String coordinateFour = build(abscissa, ordinate + 1);
        if (StringUtils.isNotBlank(coordinateOne)) {
            resultList.add(coordinateOne);
        }
        if (StringUtils.isNotBlank(coordinateTwo)) {
            resultList.add(coordinateTwo);
        }
        if (StringUtils.isNotBlank(coordinateThree)) {
            resultList.add(coordinateThree);
        }
        if (StringUtils.isNotBlank(coordinateFour)) {
            resultList.add(coordinateFour);
        }
        return resultList;
    }

    private String build(int abscissa, int ordinate) {
        if (abscissa < 0) {
            return null;
        }
        if (ordinate < 0) {
            return null;
        }
        if (abscissa == 0 && ordinate == 0) {
            return null;
        }
        return lessThanTwoAddZero(abscissa) + lessThanTwoAddZero(ordinate);
    }


    /**
     * 小于两位,第一位补0
     *
     * @param number
     * @return
     */
    private String lessThanTwoAddZero(int number) {
        String str = String.valueOf(number);
        if (str.length() == 1) {
            str = "0" + str;
        }
        return str;
    }


    @Override
    public List<BizVerdictRecordHistory> queryByVerdictRecordIdAndChessRound(Long verdictRecordId, Long chessRound) {
        return baseMapper.selectList(new LambdaQueryWrapper<BizVerdictRecordHistory>().eq(BizVerdictRecordHistory::getVerdictRecordId, verdictRecordId).eq(BizVerdictRecordHistory::getChessRound, chessRound));

    }


    @Override
    public QueryEachActionVo queryEachAction(BizVerdictRecordBo dto) {
        List<BizVerdictRecordHistory> historyList = baseMapper.selectList(
            new LambdaQueryWrapper<BizVerdictRecordHistory>().eq(BizVerdictRecordHistory::getVerdictRecordId, dto.getId())
                .eq(BizVerdictRecordHistory::getChessRound, dto.getChessRound())
        );

        List<QueryEachActionDetail> actionList = new ArrayList<>();
        historyList.forEach(history -> {
            QueryEachActionDetail detail = new QueryEachActionDetail();
            List<BizVerdictRecordChessPieces> pieceList = JSON.parseArray(history.getChessPiecesInfo(), BizVerdictRecordChessPieces.class);
            detail.setPieceList(pieceList);
            detail.setTips(history.getActionDesc());
            actionList.add(detail);
        });
        //返回
        QueryEachActionVo vo = new QueryEachActionVo();
        vo.setActionList(actionList);
        return vo;
    }


    @Override
    public ChessRoundActionEffectVo queryActionEffect(BizVerdictRecordBo dto) {
        ChessRoundActionEffectVo vo = new ChessRoundActionEffectVo();
        vo.setSelfAttackScore(0L);
        vo.setSelfDiceScore(0L);
        vo.setTargetAttackScore(0L);
        vo.setTargetDiceScore(0L);

        List<BizVerdictRecordHistory> historyList = baseMapper.selectList(new LambdaQueryWrapper<BizVerdictRecordHistory>().eq(BizVerdictRecordHistory::getVerdictRecordId, dto.getId()).eq(dto.getChessRound() != null, BizVerdictRecordHistory::getChessRound, dto.getChessRound()));
        List<BizVerdictRecordHistory> selfHistoryList = historyList.stream().filter(e -> StringUtils.isNotBlank(e.getExt()) && e.getUserId().equals(dto.getUserId())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(selfHistoryList)) {
            List<Long> selfBlowEffectIds = selfHistoryList.stream().map(e -> Long.valueOf(e.getExt())).collect(Collectors.toList());
            List<BizVerdictBlowEffect> selfBlowEffects = verdictBlowEffectMapper.selectBatchIds(selfBlowEffectIds);
            if (!CollectionUtils.isEmpty(selfBlowEffects)) {
                Long selfAttackScore = selfBlowEffects.stream().mapToLong(BizVerdictBlowEffect::getAttackScore).sum();
                Long selfDiceScore = selfBlowEffects.stream().mapToLong(BizVerdictBlowEffect::getDiceScore).sum();
                vo.setSelfAttackScore(selfAttackScore);
                vo.setSelfDiceScore(selfDiceScore);
            }
        }
        List<BizVerdictRecordHistory> targetHistoryList = historyList.stream().filter(e -> StringUtils.isNotBlank(e.getExt()) && !e.getUserId().equals(dto.getUserId())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(targetHistoryList)) {
            List<Long> targetBlowEffectIds = targetHistoryList.stream().map(e -> Long.valueOf(e.getExt())).collect(Collectors.toList());
            List<BizVerdictBlowEffect> targetBlowEffects = verdictBlowEffectMapper.selectBatchIds(targetBlowEffectIds);
            if (!CollectionUtils.isEmpty(targetBlowEffects)) {
                Long targetAttackScore = targetBlowEffects.stream().mapToLong(BizVerdictBlowEffect::getAttackScore).sum();
                Long targetDiceScore = targetBlowEffects.stream().mapToLong(BizVerdictBlowEffect::getDiceScore).sum();
                vo.setTargetAttackScore(targetAttackScore);
                vo.setTargetDiceScore(targetDiceScore);
            }
        }
        return vo;
    }


    @Override
    public QueryVerdictResultVo queryVerdictResult(BizVerdictRecordBo dto) {
        log.info("queryVerdictResult req:{}", JSON.toJSONString(dto));
        BizVerdictRecordVo verdictRecord = verdictRecordMapper.selectVoById(dto.getId());
        QueryVerdictResultVo vo = new QueryVerdictResultVo();
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("裁决记录不存在,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("裁决记录不存在");
        }
        if (VerdictRecordStatusEnum.END.getCode().equals(verdictRecord.getStatus().intValue())) {
            //查询裁决结果
            Long verdictResult = queryVerdictResult(verdictRecord);
            vo.setVerdictResult(verdictResult);
        }
        BeanUtils.copyProperties(verdictRecord, vo);
        vo.setFirstUserId(verdictRecord.getFirstUserId());
        vo.setFirstUserName(userMapper.selectVoById(verdictRecord.getFirstUserId()).getUserName());
        vo.setFirstJudgeUserName(userMapper.selectVoById(verdictRecord.getFirstJudgeUserId()).getUserName());
        vo.setFirstCommanderUserName(userMapper.selectVoById(verdictRecord.getFirstCommanderUserId()).getUserName());
        vo.setFirstCampId(verdictRecord.getFirstCampId());
        vo.setFirstCampName(chessPiecesCampMapper.selectVoById(verdictRecord.getFirstCampId()).getName());
        vo.setSecondUserId(verdictRecord.getSecondUserId());
        vo.setSecondUserName(userMapper.selectVoById(verdictRecord.getSecondUserId()).getUserName());
        vo.setSecondCampId(verdictRecord.getSecondCampId());
        vo.setSecondCampName(chessPiecesCampMapper.selectVoById(verdictRecord.getSecondCampId()).getName());
        vo.setStatus(verdictRecord.getStatus());
        vo.setChessRound(verdictRecord.getChessRound());
        vo.setFirstScore(verdictRecord.getFirstScore());
        vo.setSecondScore(verdictRecord.getSecondScore());

        Long ArbiterMapId = scenarioCreateService.getArbiterMapIdById(vo.getScenarioId());
        String StageConfig = scenarioCreateService.getStageConfigById(vo.getScenarioId());

        vo.setStageStr(StageConfig);
        vo.setMapId(ArbiterMapId);
        return vo;
    }


    private Long queryVerdictResult(BizVerdictRecordVo verdictRecord) {
        if (verdictRecord.getVerdictResult() != null) {
            return verdictRecord.getVerdictResult();
        }
        BizVerdictRecordBo req = new BizVerdictRecordBo();
        req.setId(verdictRecord.getId());
        req.setUserId(verdictRecord.getFirstUserId());
        ChessRoundActionEffectVo resp = this.queryActionEffect(req);
        Long firstTotalScore = resp.getSelfAttackScore() + resp.getSelfDiceScore();
        Long secondTotalScore = resp.getTargetAttackScore() + resp.getTargetDiceScore();
        //设置裁决结果
        Long verdictResult = null;
        if (firstTotalScore > secondTotalScore) {
            verdictResult = Long.valueOf(VerdictResultEnum.FIRST_WIN.getCode());
        } else if (firstTotalScore.equals(secondTotalScore)) {
            verdictResult = Long.valueOf(VerdictResultEnum.DEAD_HEAT.getCode());
        } else {
            verdictResult = Long.valueOf(VerdictResultEnum.SECOND_WIN.getCode());
        }
        verdictRecord.setVerdictResult(verdictResult);
        verdictRecordMapper.updateById(BeanUtil.toBean(verdictResult, BizVerdictRecord.class));
        return verdictResult;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public String endRound(BizVerdictRecordBo dto) {
        log.info("endRound req:{}", JSON.toJSONString(dto));
        LambdaQueryWrapper<BizVerdictRecordRound> lqw = Wrappers.lambdaQuery();
        lqw.eq(BizVerdictRecordRound::getVerdictRecordId, dto.getId());
        lqw.eq(BizVerdictRecordRound::getRoundPeriod, dto.getRoundPeriod());
        lqw.eq(BizVerdictRecordRound::getChessRound, dto.getChessRound());
        lqw.eq(BizVerdictRecordRound::getUserId, dto.getUserId());
        lqw.last("limit 1");
        BizVerdictRecordRound recordRound = verdictRecordRoundMapper.selectOne(lqw);
//        BizVerdictRecordHistoryVo verdictRecordHistory = this.queryLatestRecord(dto.getId());
//        if (ObjectUtils.isEmpty(verdictRecordHistory)) {
//            log.error("游戏还没开始,req:{}", JSON.toJSONString(dto));
//            throw new ServiceException("游戏还没开始");
//        }
//        if (!dto.getUserId().equals(verdictRecordHistory.getUserId())) {
//            log.error("您本回合还没开始行动,无法结束,req:{}", JSON.toJSONString(dto));
//            throw new ServiceException("您本回合还没开始行动,无法结束");
//        }
        if (recordRound.getStatus() == 0) {
            recordRound.setStatus(1);
            verdictRecordRoundMapper.updateById(recordRound);
            BizVerdictRecordHistoryVo verdictRecordHistory = this.queryLatestRecord(dto.getId());
            verdictRecordHistory.setRoundPeriod(verdictRecordHistory.getRoundPeriod());
            verdictRecordHistory.setIsEndRound(1);
            baseMapper.updateById(BeanUtil.toBean(verdictRecordHistory, BizVerdictRecordHistory.class));
        } else if (recordRound.getStatus() == 1) {
            log.error("已结束回合,请勿重复提交,req:{}", JSON.toJSONString(dto));
            throw new ServiceException("已结束回合,请勿重复提交");
        } else if (recordRound.getStatus() == 2) {
            recordRound.setStatus(3);
            verdictRecordRoundMapper.updateById(recordRound);
//            BizVerdictRecordHistoryVo verdictRecordHistory = this.queryLatestRecord(dto.getId());
//            verdictRecordHistory.setRoundPeriod(verdictRecordHistory.getRoundPeriod());
//            verdictRecordHistory.setIsEndRound(1);
//            baseMapper.updateById(BeanUtil.toBean(verdictRecordHistory, BizVerdictRecordHistory.class));
        }


        //更改推演局数延时到裁决结束
        //        BizVerdictRecord verdictRecord = verdictRecordMapper.selectById(dto.getId());
        //        Pair pair = getRoundInfo(verdictRecord);
        //        //已到最大回合数,比赛结束
        //        int nextChessRound = Integer.parseInt(String.valueOf(pair.getKey()));
        //        int nextRoundPeriod = Integer.parseInt(String.valueOf(pair.getValue()));
        //        if (nextChessRound > verdictRecord.getChessRoundLimit()) {
        //            verdictRecord.setStatus(Long.valueOf(VerdictRecordStatusEnum.END.getCode()));
        //        } else {
        //            verdictRecord.setChessRound((long) nextChessRound);
        //            verdictRecord.setRoundPeriod(nextRoundPeriod);
        //        }
        //        verdictRecordMapper.updateById(verdictRecord);
        return "";
    }

    private Pair getRoundInfo(BizVerdictRecord verdictRecord) {
        if (verdictRecord.getRoundPeriod().equals(RoundPeriodEnum.second_stage.getCode())) {
            //标记清除,将被压制状态恢复为正常状态
            verdictRecordChessPiecesService.markRemove(verdictRecord.getId());
            return Pair.of(verdictRecord.getChessRound(), RoundPeriodEnum.fourth_stage.getCode());

        } else if (verdictRecord.getRoundPeriod().equals(RoundPeriodEnum.fourth_stage.getCode())) {
            //间瞄效果
            this.calIndirectAttack(verdictRecord.getId(), verdictRecord.getChessRound() + 1);
            return Pair.of(verdictRecord.getChessRound() + 1, RoundPeriodEnum.first_stage.getCode());

        } else {
            return Pair.of(verdictRecord.getChessRound(), verdictRecord.getRoundPeriod() + ArbiterConstant.ONE);
        }
    }


    @Override
    public QueryPromptVo queryPrompt(BizVerdictRecordBo dto) {
        LambdaQueryWrapper<BizVerdictRecordHistory> lqw = Wrappers.lambdaQuery();
        lqw.eq(BizVerdictRecordHistory::getVerdictRecordId, dto.getId())
            .eq(com.baomidou.mybatisplus.core.toolkit.ObjectUtils.isNotNull(dto.getChessRound()), BizVerdictRecordHistory::getChessRound, dto.getChessRound());

        lqw.eq(ObjectUtil.isNotNull(dto.getCampId()), BizVerdictRecordHistory::getCampId, dto.getCampId());
        lqw.in(ObjectUtil.isNotNull(dto.getRoundPeriod()), BizVerdictRecordHistory::getRoundPeriod, dto.getRoundPeriod());
        lqw.orderByAsc(BizVerdictRecordHistory::getCreateTime);
        List<BizVerdictRecordHistory> historyList = baseMapper.selectList(lqw);
        Map<Long, List<BizVerdictRecordHistory>> historyMap = historyList.stream().collect(Collectors.groupingBy(BizVerdictRecordHistory::getChessRound));
        List<String> actionDescList = new ArrayList<>();
        QueryPromptVo vo = new QueryPromptVo();
        String chessRoundDesc = "";
        if ("record".equals(dto.getType())) {
            chessRoundDesc = getChessRoundDescRecord(historyList, dto.getUserId(), dto.getChessRound());
        } else {
            chessRoundDesc = getChessRoundDesc(historyList, dto.getUserId(), dto.getChessRound());
        }
        vo.setVerdictRecordHistoryList(historyList);
        actionDescList.add(chessRoundDesc);
        vo.setActionDescList(actionDescList);
        return vo;
    }

    private String getChessRoundDesc(List<BizVerdictRecordHistory> historyList, Long userId, Long chessRound) {
        Long selfAttackScore = Long.valueOf(org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO);
        Long selfDiceScore = Long.valueOf(org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO);
        Long targetAttackScore = Long.valueOf(org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO);
        Long targetDiceScore = Long.valueOf(org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO);
        List<BizVerdictRecordHistory> selfHistoryList = historyList.stream().filter(e -> org.apache.commons.lang.StringUtils.isNotBlank(e.getExt()) && e.getUserId().equals(userId)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(selfHistoryList)) {
            List<Long> selfBlowEffectIds = selfHistoryList.stream().map(e -> Long.valueOf(e.getExt())).collect(Collectors.toList());
            List<BizVerdictBlowEffect> selfBlowEffects = verdictBlowEffectMapper.selectBatchIds(selfBlowEffectIds);
            if (!CollectionUtils.isEmpty(selfBlowEffects)) {
                selfAttackScore = selfBlowEffects.stream().mapToLong(BizVerdictBlowEffect::getAttackScore).sum();
                selfDiceScore = selfBlowEffects.stream().mapToLong(BizVerdictBlowEffect::getDiceScore).sum();
            }
        }

        List<BizVerdictRecordHistory> targetHistoryList = historyList.stream().filter(e -> org.apache.commons.lang.StringUtils.isNotBlank(e.getExt()) && !e.getUserId().equals(userId)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(targetHistoryList)) {
            List<Long> targetBlowEffectIds = targetHistoryList.stream().map(e -> Long.valueOf(e.getExt())).collect(Collectors.toList());
            List<BizVerdictBlowEffect> targetBlowEffects = verdictBlowEffectMapper.selectBatchIds(targetBlowEffectIds);
            if (!CollectionUtils.isEmpty(targetBlowEffects)) {
                targetAttackScore = targetBlowEffects.stream().mapToLong(BizVerdictBlowEffect::getAttackScore).sum();
                targetDiceScore = targetBlowEffects.stream().mapToLong(BizVerdictBlowEffect::getDiceScore).sum();
            }
        }
        return "第" + chessRound + "回合,我方攻击得分：" + selfAttackScore + ",骰子得分：" + selfDiceScore + ";对方攻击得分：" + targetAttackScore + ",骰子得分：" + targetDiceScore;
    }


    private String getChessRoundDescRecord(List<BizVerdictRecordHistory> historyList, Long userId, Long chessRound) {
        String selfName = "";
        Long selfAttackScore = Long.valueOf(org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO);
        Long selfDiceScore = Long.valueOf(org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO);
        String targetName = "";
        Long targetAttackScore = Long.valueOf(org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO);
        Long targetDiceScore = Long.valueOf(org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO);
        Map<Long, List<BizVerdictRecordHistory>> groupedMap = historyList.stream()
            .collect(Collectors.groupingBy(BizVerdictRecordHistory::getCampId));

        List<Long> campIds = historyList.stream().map(BizVerdictRecordHistory::getCampId).distinct().collect(Collectors.toList());
        List<BizChessPiecesCamp> chessPiecesCamps = chessPiecesCampMapper.selectBatchIds(campIds);
        for (int i = 0; i < campIds.size(); i++) {
            if (i == 0) {
                List<BizVerdictRecordHistory> list = groupedMap.get(campIds.get(0)).stream().filter(e -> org.apache.commons.lang.StringUtils.isNotBlank(e.getExt())).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(list)) {
                    List<Long> selfBlowEffectIds = list.stream().map(e -> Long.valueOf(e.getExt())).collect(Collectors.toList());
                    List<BizVerdictBlowEffect> selfBlowEffects = verdictBlowEffectMapper.selectBatchIds(selfBlowEffectIds);
                    if (!CollectionUtils.isEmpty(selfBlowEffects)) {
                        selfAttackScore = selfBlowEffects.stream().mapToLong(BizVerdictBlowEffect::getAttackScore).sum();
                        selfDiceScore = selfBlowEffects.stream().mapToLong(BizVerdictBlowEffect::getDiceScore).sum();
                    }
                }

                for (BizChessPiecesCamp chessPiecesCamp : chessPiecesCamps) {
                    if (chessPiecesCamp.getId().equals(campIds.get(0))) {
                        selfName = chessPiecesCamp.getName();
                    }
                }
                ;
            } else {
                List<BizVerdictRecordHistory> list = groupedMap.get(campIds.get(1)).stream().filter(e -> org.apache.commons.lang.StringUtils.isNotBlank(e.getExt())).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(list)) {
                    List<Long> targetBlowEffectIds = list.stream().map(e -> Long.valueOf(e.getExt())).collect(Collectors.toList());
                    List<BizVerdictBlowEffect> targetBlowEffects = verdictBlowEffectMapper.selectBatchIds(targetBlowEffectIds);
                    if (!CollectionUtils.isEmpty(targetBlowEffects)) {
                        targetAttackScore = targetBlowEffects.stream().mapToLong(BizVerdictBlowEffect::getAttackScore).sum();
                        targetDiceScore = targetBlowEffects.stream().mapToLong(BizVerdictBlowEffect::getDiceScore).sum();
                    }
                }
                for (BizChessPiecesCamp chessPiecesCamp : chessPiecesCamps) {
                    if (chessPiecesCamp.getId().equals(campIds.get(1))) {
                        targetName = chessPiecesCamp.getName();
                    }
                }
                ;
            }
        }


//        groupedMap.forEach((campId, list) -> {
//          BizChessPiecesCampVo chessPiecesCampVo =  chessPiecesCampMapper.selectVoById(campId);
//        });
//        List<BizVerdictRecordHistory> selfHistoryList = historyList.stream().filter(e -> org.apache.commons.lang.StringUtils.isNotBlank(e.getExt()) && e.getUserId().equals(userId)).collect(Collectors.toList());
//        if (!CollectionUtils.isEmpty(selfHistoryList)) {
//            List<Long> selfBlowEffectIds = selfHistoryList.stream().map(e -> Long.valueOf(e.getExt())).collect(Collectors.toList());
//            List<BizVerdictBlowEffect> selfBlowEffects = verdictBlowEffectMapper.selectBatchIds(selfBlowEffectIds);
//            if (!CollectionUtils.isEmpty(selfBlowEffects)) {
//                selfAttackScore = selfBlowEffects.stream().mapToLong(BizVerdictBlowEffect::getAttackScore).sum();
//                selfDiceScore = selfBlowEffects.stream().mapToLong(BizVerdictBlowEffect::getDiceScore).sum();
//            }
//        }
//
//        List<BizVerdictRecordHistory> targetHistoryList = historyList.stream().filter(e -> org.apache.commons.lang.StringUtils.isNotBlank(e.getExt()) && !e.getUserId().equals(userId)).collect(Collectors.toList());
//        if (!CollectionUtils.isEmpty(targetHistoryList)) {
//            List<Long> targetBlowEffectIds = targetHistoryList.stream().map(e -> Long.valueOf(e.getExt())).collect(Collectors.toList());
//            List<BizVerdictBlowEffect> targetBlowEffects = verdictBlowEffectMapper.selectBatchIds(targetBlowEffectIds);
//            if (!CollectionUtils.isEmpty(targetBlowEffects)) {
//                targetAttackScore = targetBlowEffects.stream().mapToLong(BizVerdictBlowEffect::getAttackScore).sum();
//                targetDiceScore = targetBlowEffects.stream().mapToLong(BizVerdictBlowEffect::getDiceScore).sum();
//            }
//        }
        return "第" + chessRound + "回合," + selfName + "攻击得分：" + selfAttackScore + ",骰子得分：" + selfDiceScore + ";" + targetName + "攻击得分：" + targetAttackScore + ",骰子得分：" + targetDiceScore;
    }


    @Override
    public String endGame(BizVerdictRecordBo dto) {
        log.info("endGame req:{}", JSON.toJSONString(dto));
        BizVerdictRecord verdictRecord = verdictRecordMapper.selectById(dto.getId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("结束游戏,裁决记录不存在,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("结束游戏,裁决记录不存在");
        }
        verdictRecord.setStatus(Long.valueOf(VerdictRecordStatusEnum.END.getCode()));
        verdictRecordMapper.updateById(verdictRecord);
        return "";
    }

    @Override
    public Map<Long, List<BizVerdictRecordHistory>> chessPiecesActionHistory(BizVerdictRecordBo dto) {
        log.info("chessPiecesActionHistory req:{}", JSON.toJSONString(dto));
        List<BizVerdictRecordHistory> historyList = baseMapper.selectList(new LambdaQueryWrapper<BizVerdictRecordHistory>().eq(BizVerdictRecordHistory::getVerdictRecordId, dto.getId()).orderByAsc(BizVerdictRecordHistory::getCreateTime));

        if (CollectionUtils.isEmpty(historyList)) {
            log.info("chessPiecesActionHistory is empty");
            return new HashMap<>();
        }
        Map<Long, List<BizVerdictRecordHistory>> chessRoundMap = historyList.stream().collect(Collectors.groupingBy(BizVerdictRecordHistory::getChessRound));
        return chessRoundMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Long confirmJudge(BizVerdictRecordHistoryBo bo) {
        if (ObjectUtil.isNull(bo.getAttackResult())) {
            throw new ServiceException("裁决失败！请确认裁决结果！");
        }
        bo.setStatus(2);

        //修改打分表
        //updateScore(bo);
        //修改记录表信息和记录表棋子信息
        LambdaQueryWrapper<BizVerdictRecordChessPieces> clqw = Wrappers.lambdaQuery();
        clqw.eq(BizVerdictRecordChessPieces::getVerdictRecordId, bo.getVerdictRecordId());
        List<BizVerdictRecordChessPiecesVo> vos = verdictRecordChessPiecesMapper.selectVoList(clqw);
        bo.setChessPiecesInfo(JSON.toJSONString(vos));
        BizVerdictRecordHistory update = BeanUtil.toBean(bo, BizVerdictRecordHistory.class);
        baseMapper.updateById(update);


        LambdaQueryWrapper<BizVerdictRecordHistory> lqw = Wrappers.lambdaQuery();
        lqw.eq(BizVerdictRecordHistory::getVerdictRecordId, bo.getVerdictRecordId());
        lqw.eq(BizVerdictRecordHistory::getCampId, bo.getCampId());
        lqw.eq(BizVerdictRecordHistory::getChessRound, bo.getChessRound());
        lqw.eq(BizVerdictRecordHistory::getRoundPeriod, bo.getRoundPeriod());
        lqw.ne(BizVerdictRecordHistory::getStatus, 2);
        long notJudge = baseMapper.selectCount(lqw);
        if (notJudge == 0) {
            //回合表修改状态
            LambdaUpdateWrapper<BizVerdictRecordRound> roundLqw = Wrappers.lambdaUpdate();
            roundLqw.eq(BizVerdictRecordRound::getVerdictRecordId, bo.getVerdictRecordId());
            roundLqw.eq(BizVerdictRecordRound::getRoundPeriod, bo.getRoundPeriod());
            roundLqw.eq(BizVerdictRecordRound::getChessRound, bo.getChessRound());
            roundLqw.eq(BizVerdictRecordRound::getCampId, bo.getCampId());
            roundLqw.set(BizVerdictRecordRound::getStatus, 2);
            verdictRecordRoundMapper.update(roundLqw);

        }
        return notJudge;
    }

    private void updateChessStatus(String coordinate, String status, Long verdictRecordId) {
        LambdaQueryWrapper<BizVerdictRecordChessPieces> lqw = Wrappers.lambdaQuery();
        lqw.eq(BizVerdictRecordChessPieces::getVerdictRecordId, verdictRecordId);
        lqw.eq(BizVerdictRecordChessPieces::getFromCoordinate, coordinate);
        List<BizVerdictRecordChessPieces> chessPiecesList = verdictRecordChessPiecesMapper.selectList(lqw);
        for (BizVerdictRecordChessPieces verdictRecordChessPieces : chessPiecesList) {
            if (StringUtils.isEmpty(verdictRecordChessPieces.getStatus())) {
                verdictRecordChessPieces.setStatus(status);
            } else {
                if (!verdictRecordChessPieces.getStatus().contains(status)) {
                    verdictRecordChessPieces.setStatus("," + status);
                }
            }
            verdictRecordChessPiecesMapper.updateById(verdictRecordChessPieces);
        }
    }


    private void updateScore(BizVerdictRecordHistoryBo history) {
        //查询是否有作战效果的打分记录
        LambdaQueryWrapper<BizVerdictRecordScore> lqw = Wrappers.lambdaQuery();
        lqw.eq(BizVerdictRecordScore::getVerdictRecordId, history.getVerdictRecordId());
        lqw.eq(BizVerdictRecordScore::getCampId, history.getCampId());
        lqw.eq(BizVerdictRecordScore::getChessRound, history.getChessRound());
        lqw.eq(BizVerdictRecordScore::getType, 3);
        lqw.last("limit 1");
        BizVerdictRecordScore bizVerdictRecordScore = verdictRecordScoreMapper.selectOne(lqw);
        if (ObjectUtil.isNotNull(bizVerdictRecordScore)) {
            //拼接打分标准和分数
            bizVerdictRecordScore.setStandard(bizVerdictRecordScore.getStandard() + "<br/>" + history.getActionDesc());
            bizVerdictRecordScore.setScore(bizVerdictRecordScore.getScore() + history.getAttackScore());
            verdictRecordScoreMapper.updateById(bizVerdictRecordScore);
        } else {
            //添加一条类型为3 的打分记录
            bizVerdictRecordScore = new BizVerdictRecordScore();
            bizVerdictRecordScore.setVerdictRecordId(history.getVerdictRecordId());
            bizVerdictRecordScore.setUserId(history.getUserId());
            bizVerdictRecordScore.setCampId(history.getCampId());
            bizVerdictRecordScore.setChessRound(history.getChessRound());
            bizVerdictRecordScore.setType(3);
            bizVerdictRecordScore.setContent("打分效果");
            bizVerdictRecordScore.setStandard(history.getActionDesc());
            bizVerdictRecordScore.setScore(history.getAttackScore());
            verdictRecordScoreMapper.insert(bizVerdictRecordScore);
        }
    }

    public List<BizVerdictRecordHistoryBo> selectChessPieceByVerdictRecordId(BizVerdictRecordHistoryBo dto) {
        return baseMapper.selectChessPieceByVerdictRecordId(dto);
    }

    public String calculateRoundScore(BizVerdictRecordHistoryBo bo) {
        //计算准备回合分数 即第0回合的分数
        BizVerdictRecordHistoryBo preBo = new BizVerdictRecordHistoryBo();
        preBo.setVerdictRecordId(bo.getVerdictRecordId());
        preBo.setChessRound(0L);
        List<BizVerdictRecordHistoryBo> prepareList = baseMapper.selectByChessRoundAndVerdictRecordId(BeanUtil.toBean(preBo, BizVerdictRecordHistoryBo.class));
        if (CollectionUtils.isEmpty(prepareList)) {
            return "false";
        }
        Long firstScore = 0L;
        Long secondScore = 0L;
        for (BizVerdictRecordHistoryBo history : prepareList) {
            //如果getCampId 为null，则不计算
            if (history.getCampId() == null) {
                continue;
            }
            if ("1689661085015457792".equals(history.getCampId().toString())) {
                if (history.getAttackScore() != null) {
                    firstScore += history.getAttackScore();
                }
            } else if ("1689661085015457793".equals(history.getCampId().toString())) {
                if (history.getAttackScore() != null) {
                    secondScore += history.getAttackScore();
                }
            }
        }
        LambdaQueryWrapper<BizVerdictRecordScore> scoreLqw1 = Wrappers.lambdaQuery();
        scoreLqw1.eq(BizVerdictRecordScore::getVerdictRecordId, bo.getVerdictRecordId());
        scoreLqw1.eq(BizVerdictRecordScore::getChessRound, 0);
        List<BizVerdictRecordScore> scoreList1 = verdictRecordScoreMapper.selectList(scoreLqw1);
        if (!CollectionUtils.isEmpty(scoreList1)) {
            for (BizVerdictRecordScore score : scoreList1) {
                if ("1689661085015457792".equals(score.getCampId().toString())) {
                    score.setScore(firstScore.intValue());
                    verdictRecordScoreMapper.updateById(score);
                } else if ("1689661085015457793".equals(score.getCampId().toString())) {
                    score.setScore(secondScore.intValue());
                    verdictRecordScoreMapper.updateById(score);
                }
            }
        } else {
            BizVerdictRecordScore firstScoreRecord1 = new BizVerdictRecordScore();
            firstScoreRecord1.setVerdictRecordId(bo.getVerdictRecordId());
            firstScoreRecord1.setCampId(1689661085015457792L);
            firstScoreRecord1.setChessRound(0L);
            firstScoreRecord1.setScore(firstScore.intValue());
            firstScoreRecord1.setContent("红军得分");
            firstScoreRecord1.setType(7);
            verdictRecordScoreMapper.insert(firstScoreRecord1);
            BizVerdictRecordScore secondScoreRecord1 = new BizVerdictRecordScore();
            secondScoreRecord1.setVerdictRecordId(bo.getVerdictRecordId());
            secondScoreRecord1.setCampId(1689661085015457793L);
            secondScoreRecord1.setChessRound(0L);
            secondScoreRecord1.setScore(secondScore.intValue());
            secondScoreRecord1.setContent("蓝军得分");
            secondScoreRecord1.setType(6);
            verdictRecordScoreMapper.insert(secondScoreRecord1);
        }
        //根据裁决记录id和回合数标识查询裁决记录 随后根据阵营id计算分数
        LambdaQueryWrapper<BizVerdictRecordHistory> lqw = Wrappers.lambdaQuery();
        lqw.eq(BizVerdictRecordHistory::getVerdictRecordId, bo.getVerdictRecordId());
        lqw.eq(BizVerdictRecordHistory::getChessRound, bo.getChessRound());
        List<BizVerdictRecordHistoryBo> historyList = baseMapper.selectByChessRoundAndVerdictRecordId(BeanUtil.toBean(bo, BizVerdictRecordHistoryBo.class));
        if (CollectionUtils.isEmpty(historyList)) {
            log.error("calculateRoundScore,裁决记录不存在,dto:{}", JSON.toJSONString(bo));
            return "false";
        }
        //计算分数
        firstScore = 0L;
        secondScore = 0L;
        int firstCount = 0;
        int secondCount = 0;
        for (BizVerdictRecordHistoryBo history : historyList) {
            if (history.getCampId().toString().equals("1689661085015457792")) {
                if (history.getAttackScore() != null) {
                    firstCount += 1;
                    firstScore += history.getAttackScore();
                }
                continue;
            }
            if (history.getCampId().toString().equals("1689661085015457793")) {
                if (history.getAttackScore() != null) {
                    secondCount += 1;
                    secondScore += history.getAttackScore();
                }
            }
        }
        //计算平均分
        if (firstCount != 0) {
            firstScore = firstScore / firstCount;
        }
        if (secondCount != 0) {
            secondScore = secondScore / secondCount;
        }

        // 如果数据库中已经存在该回合的分数，则更新，否则插入
        LambdaQueryWrapper<BizVerdictRecordScore> scoreLqw = Wrappers.lambdaQuery();
        scoreLqw.eq(BizVerdictRecordScore::getVerdictRecordId, bo.getVerdictRecordId());
        scoreLqw.eq(BizVerdictRecordScore::getChessRound, bo.getChessRound());
        List<BizVerdictRecordScore> scoreList = verdictRecordScoreMapper.selectList(scoreLqw);
        if (!CollectionUtils.isEmpty(scoreList)) {
            for (BizVerdictRecordScore score : scoreList) {
                if (score.getCampId().toString().equals("1689661085015457792")) {
                    score.setScore(firstScore.intValue());
                    score.setContent("红军得分");
                    score.setType(6);
                    verdictRecordScoreMapper.updateById(score);
                }
                if (score.getCampId().toString().equals("1689661085015457793")) {
                    score.setScore(secondScore.intValue());
                    score.setContent("蓝军得分");
                    score.setType(7);
                    verdictRecordScoreMapper.updateById(score);
                }
            }
        }else {
            //生成两个信息，分别向biz_verdict_record_score表中插入数据
            BizVerdictRecordScore firstScoreRecord = new BizVerdictRecordScore();
            firstScoreRecord.setVerdictRecordId(bo.getVerdictRecordId());
            firstScoreRecord.setCampId(1689661085015457792L);
            firstScoreRecord.setChessRound(bo.getChessRound());
            firstScoreRecord.setScore(firstScore.intValue());
            firstScoreRecord.setContent("红军得分");
            firstScoreRecord.setType(7);
            verdictRecordScoreMapper.insert(firstScoreRecord);

            BizVerdictRecordScore secondScoreRecord = new BizVerdictRecordScore();
            secondScoreRecord.setVerdictRecordId(bo.getVerdictRecordId());
            secondScoreRecord.setCampId(1689661085015457793L);
            secondScoreRecord.setChessRound(bo.getChessRound());
            secondScoreRecord.setScore(secondScore.intValue());
            secondScoreRecord.setContent("蓝军得分");
            secondScoreRecord.setType(6);
            verdictRecordScoreMapper.insert(secondScoreRecord);
        }
        return "success";
    }
}


