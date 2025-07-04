package com.ruoyi.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.business.domain.*;
import com.ruoyi.business.domain.bo.*;
import com.ruoyi.business.domain.dto.QueryVerdictRecordDetail;
import com.ruoyi.business.domain.vo.*;
import com.ruoyi.business.mapper.*;
import com.ruoyi.business.service.*;
import com.ruoyi.common.constant.ArbiterConstant;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.RoundPeriodEnum;
import com.ruoyi.common.enums.VerdictRecordStatusEnum;
import com.ruoyi.common.enums.WatchModeEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.common.websocket.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static com.ruoyi.common.websocket.constant.WebSocketConstants.ROOM_KEY;

/**
 * 裁决记录Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-21
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class BizVerdictRecordServiceImpl implements IBizVerdictRecordService {
    @Autowired
    private IBizScenarioService scenarioService;
    @Autowired
    private IBizScenarioCreateService scenarioCreateService;


    private final BizVerdictRecordMapper baseMapper;
    @Autowired
    private final IBizUserService userService;

    private final BizScenarioCreateMapper BizScenarioCreateMapper;

    private final BizChessPiecesMapper chessPiecesMapper;

    private final BizUserMapper userMapper;

    private final BizChessPiecesCampMapper chessPiecesCampMapper;

    private final BizScenarioMapper scenarioMapper;

    private final BizProductMapper productMapper;

    private final BizScenarioCreateMapper scenarioCreateMapper;

    private final BizVerdictRecordChessPiecesMapper verdictRecordChessPiecesMapper;

    private final IBizVerdictRecordHistoryService verdictRecordHistoryService;

    @Resource
    private final BizArbiterMapMapper arbiterMapMapper;
    @Autowired
    private BizVerdictRecordRoundServiceImpl bizVerdictRecordRoundServiceImpl;

    @Override
    public Long getMapId(Long verdictRecordId) {
        BizVerdictRecordVo recordVo = baseMapper.selectVoById(verdictRecordId);
        return scenarioCreateService.getArbiterMapIdById(recordVo.getScenarioId());
    }

    @Override
    public BizArbiterMap getMap(Long verdictRecordId) {
        BizVerdictRecordVo recordVo = baseMapper.selectVoById(verdictRecordId);
//        BizScenarioVo scenarioVo = scenarioMapper.selectVoById(recordVo.getScenarioId());
//        System.out.println(scenarioVo.getArbiterMapId());
//        return arbiterMapMapper.selectById(scenarioVo.getArbiterMapId());
        return arbiterMapMapper.selectById(scenarioCreateService.getArbiterMapIdById(recordVo.getScenarioId()));
    }

    @Override
    public BizArbiterMap getMapC(String ScenarioId) {
//        Long id = BizScenarioCreateMapper.selectArbiterMapIdById(ScenarioId);
//        if (ScenarioId == null) {
//            throw new IllegalArgumentException("ScenarioId cannot be null");
//        }
        return arbiterMapMapper.selectById(Long.valueOf(ScenarioId));
    }

    @Override
    public Long queryScenarioIdByVerdictRecordId(Long verdictRecordId)
    {
        BizVerdictRecordVo recordVo = baseMapper.selectVoById(verdictRecordId);
        return recordVo.getScenarioId();
    }

    /**
     * 查询裁决记录
     */
    @Override
    public BizVerdictRecordVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询裁决记录列表
     */
    @Override
    public TableDataInfo<BizVerdictRecordVo> queryPageList(BizVerdictRecordBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizVerdictRecord> lqw = buildQueryWrapper(bo);
        Page<BizVerdictRecordVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询裁决记录列表
     */
    @Override
    public List<BizVerdictRecordVo> queryList(BizVerdictRecordBo bo) {
        LambdaQueryWrapper<BizVerdictRecord> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizVerdictRecord> buildQueryWrapper(BizVerdictRecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizVerdictRecord> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getInviterUserId() != null, BizVerdictRecord::getInviterUserId, bo.getInviterUserId());
        lqw.eq(bo.getFirstUserId() != null, BizVerdictRecord::getFirstUserId, bo.getFirstUserId());
        lqw.eq(bo.getFirstCampId() != null, BizVerdictRecord::getFirstCampId, bo.getFirstCampId());
        lqw.eq(bo.getSecondUserId() != null, BizVerdictRecord::getSecondUserId, bo.getSecondUserId());
        lqw.eq(bo.getSecondCampId() != null, BizVerdictRecord::getSecondCampId, bo.getSecondCampId());
        lqw.eq(bo.getScenarioId() != null, BizVerdictRecord::getScenarioId, bo.getScenarioId());
        lqw.eq(StringUtils.isNotBlank(bo.getChessPiecesIds()), BizVerdictRecord::getChessPiecesIds, bo.getChessPiecesIds());
        lqw.eq(StringUtils.isNotBlank(bo.getInviteCode()), BizVerdictRecord::getInviteCode, bo.getInviteCode());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizVerdictRecord::getExt, bo.getExt());
        lqw.eq(bo.getWatchMode() != null, BizVerdictRecord::getWatchMode, bo.getWatchMode());
        lqw.eq(bo.getStatus() != null, BizVerdictRecord::getStatus, bo.getStatus());
        lqw.eq(bo.getChessRound() != null, BizVerdictRecord::getChessRound, bo.getChessRound());
        lqw.eq(bo.getRoundPeriod() != null, BizVerdictRecord::getRoundPeriod, bo.getRoundPeriod());
        lqw.eq(bo.getChessRoundLimit() != null, BizVerdictRecord::getChessRoundLimit, bo.getChessRoundLimit());
        lqw.eq(bo.getVerdictResult() != null, BizVerdictRecord::getVerdictResult, bo.getVerdictResult());
        return lqw;
    }

    /**
     * 新增裁决记录
     */
    @Override
    public Boolean insertByBo(BizVerdictRecordBo bo) {
        BizVerdictRecord add = BeanUtil.toBean(bo, BizVerdictRecord.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改裁决记录
     */
    @Override
    public Boolean updateByBo(BizVerdictRecordBo bo) {
        BizVerdictRecord update = BeanUtil.toBean(bo, BizVerdictRecord.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizVerdictRecord entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除裁决记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public BizVerdictRecordVo saveVerdictScenario(BizVerdictRecordBo saveVerdictScenarioDto) {

        BizUserVo user = userService.queryById(saveVerdictScenarioDto.getUserId());
        if (ObjectUtils.isEmpty(user)) {
            log.error("用户不存在, req:{}", JSON.toJSONString(saveVerdictScenarioDto));
            throw new ServiceException("用户不存在");
        }

        if (scenarioCreateService == null) {
            throw new ServiceException("scenarioCreateService 未初始化");
        }

        if (baseMapper == null) {
            throw new ServiceException("baseMapper 未初始化");
        }

        Long chessRoundLimit = scenarioCreateService.getChessRoundLimitById(saveVerdictScenarioDto.getScenarioId());

        BizVerdictRecord verdictRecord = new BizVerdictRecord();
        verdictRecord.setInviterUserId(saveVerdictScenarioDto.getUserId());
        verdictRecord.setScenarioId(saveVerdictScenarioDto.getScenarioId());
        verdictRecord.setChessRoundLimit(chessRoundLimit);
        baseMapper.insert(verdictRecord);
        return BeanUtil.toBean(verdictRecord, BizVerdictRecordVo.class);
    }

    @Override
    public String saveVerdictChessPieces(BizChessPiecesBo saveVerdictChessPiecesDto) {
        BizVerdictRecordVo verdictRecord = baseMapper.selectVoById(saveVerdictChessPiecesDto.getVerdictRecordId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("裁定记录不存在,req:{}", JSON.toJSONString(saveVerdictChessPiecesDto));
            throw new ServiceException("裁定记录不存在");
        }

        String[] chessPiecesIds = saveVerdictChessPiecesDto.getChessPiecesIds().split(",");

        for (String chessPiecesId : chessPiecesIds) {
            if (ObjectUtils.isEmpty(chessPiecesMapper.selectVoById(Long.valueOf(chessPiecesId)))) {
                log.error("所选棋子不存在,请重新选择,req:{}", JSON.toJSONString(saveVerdictChessPiecesDto));
                throw new ServiceException("所选棋子不存在,请重新选择!");
            }
        }
        LambdaQueryWrapper<BizChessPieces> lqw = Wrappers.lambdaQuery();
        lqw.in(BizChessPieces::getId, Arrays.asList(chessPiecesIds));
        List<BizChessPiecesVo> chessPiecesList = chessPiecesMapper.selectVoList(lqw);
        List<BizChessPiecesVo> distinctChessPiecesList = chessPiecesList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(BizChessPiecesVo::getChessPiecesCampId))), ArrayList::new));
        if (distinctChessPiecesList.size() != 2) {
            throw new ServiceException("所选棋子阵营数量不对,请重新选择!");
        }
        verdictRecord.setChessPiecesIds(saveVerdictChessPiecesDto.getChessPiecesIds());
        baseMapper.updateById(BeanUtil.toBean(verdictRecord, BizVerdictRecord.class));
        return "";
    }


    @Override
    public String inviteFriend(BizVerdictRecordBo inviteFriendDto) {
        log.info("saveInviteCode req:{}", JSON.toJSONString(inviteFriendDto));
        BizVerdictRecordVo verdictRecord = baseMapper.selectVoById(inviteFriendDto.getId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("裁定记录不存在,req:{}", JSON.toJSONString(inviteFriendDto));
            throw new ServiceException("裁定记录不存在");
        }
        if (!inviteFriendDto.getUserId().equals(verdictRecord.getInviterUserId())) {
            log.error("您不是邀请人,req:{}", JSON.toJSONString(inviteFriendDto));
            throw new ServiceException("您不是邀请人");
        }
        verdictRecord.setInviteCode(inviteFriendDto.getInviteCode());
        if (inviteFriendDto.getWatchMode() != null) {
            verdictRecord.setWatchMode(inviteFriendDto.getWatchMode());
        }
        baseMapper.updateById(BeanUtil.toBean(verdictRecord, BizVerdictRecord.class));
        return "";
    }


    @Override
    public BizVerdictRecordVo responseInvite(BizVerdictRecordBo responseInviteDto) {
        LambdaQueryWrapper<BizVerdictRecord> queryWrapper = new QueryWrapper<BizVerdictRecord>().lambda().eq(BizVerdictRecord::getInviteCode, responseInviteDto.getInviteCode()).orderByDesc(BizVerdictRecord::getCreateTime).last("limit 1");
        BizVerdictRecord verdictRecord = baseMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("裁定记录不存在,req:{}", JSON.toJSONString(responseInviteDto));
            throw new ServiceException("裁定记录不存在");
        }
        if (!VerdictRecordStatusEnum.INIT.getCode().equals(verdictRecord.getStatus().intValue())) {
            log.error("比赛状态不在初始化,req:{}", JSON.toJSONString(responseInviteDto));
            throw new ServiceException("比赛状态不在初始化");
        }
        if (verdictRecord.getFirstUserId() != null && verdictRecord.getSecondUserId() != null) {
            log.error("参赛人员已满,req:{}", JSON.toJSONString(responseInviteDto));
            throw new ServiceException("参赛人员已满");
        }
        if (responseInviteDto.getUserId().equals(verdictRecord.getFirstUserId()) || responseInviteDto.getUserId().equals(verdictRecord.getSecondUserId())) {
            log.error("您已参赛,请勿重复提交,req:{}", JSON.toJSONString(responseInviteDto));
            throw new ServiceException("您已参赛,请勿重复提交");
        }
        if (verdictRecord.getFirstUserId() == null) {
            verdictRecord.setFirstUserId(responseInviteDto.getUserId());
        } else {
            verdictRecord.setSecondUserId(responseInviteDto.getUserId());
        }
        baseMapper.updateById(verdictRecord);
        return BeanUtil.toBean(verdictRecord, BizVerdictRecordVo.class);
    }


    @Override
    public List<BizChessPiecesCampVo> queryCampList(BizChessPiecesCampBo queryCampDto) {
        BizVerdictRecordVo verdictRecord = baseMapper.selectVoById(queryCampDto.getVerdictRecordId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("裁定记录不存在,req:{}", JSON.toJSONString(queryCampDto));
            throw new ServiceException("裁定记录不存在");
        }
        if (StringUtils.isBlank(verdictRecord.getChessPiecesIds())) {
            throw new ServiceException("阵营还未选择");
        }
        List<String> chessPiecesIds = Arrays.asList(verdictRecord.getChessPiecesIds().split(","));
        LambdaQueryWrapper<BizChessPieces> lqw = Wrappers.lambdaQuery();
        lqw.in(BizChessPieces::getId, chessPiecesIds);
        List<BizChessPiecesVo> chessPiecesList = chessPiecesMapper.selectVoList(lqw);
        List<BizChessPiecesVo> distinctChessPiecesList = chessPiecesList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(BizChessPiecesVo::getChessPiecesCampId))), ArrayList::new));
        List<BizChessPiecesCampVo> vos = new ArrayList<>();
        distinctChessPiecesList.stream().forEach(e -> {
            BizChessPiecesCampVo vo = new BizChessPiecesCampVo();
            vo.setId(e.getChessPiecesCampId());
            vo.setName(e.getChessPiecesCampName());
            vos.add(vo);
        });
        return vos;
    }


    @Override
    public synchronized BizChessPiecesCampVo chooseCamp(BizChessPiecesCampBo chooseCampDto) {
        log.info("chooseCamp req:{}", JSON.toJSONString(chooseCampDto));
        BizVerdictRecordVo verdictRecord = baseMapper.selectVoById(chooseCampDto.getVerdictRecordId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("裁定记录不存在,req:{}", JSON.toJSONString(chooseCampDto));
            throw new ServiceException("裁定记录不存在");
        }
        Long userId = LoginHelper.getLoginUser().getUserId();
        if (ObjectUtil.isNotNull(verdictRecord.getFirstUserId()) && userId.equals(verdictRecord.getFirstUserId()) ||
            ObjectUtil.isNotNull(verdictRecord.getSecondUserId()) && userId.equals(verdictRecord.getSecondUserId())
            // 注释掉指挥员和裁决员相关判断
            // ObjectUtil.isNotNull(verdictRecord.getFirstJudgeUserId()) && userId.equals(verdictRecord.getFirstJudgeUserId()) ||
            // ObjectUtil.isNotNull(verdictRecord.getSecondJudgeUserId()) && userId.equals(verdictRecord.getSecondJudgeUserId()) ||
            // ObjectUtil.isNotNull(verdictRecord.getFirstCommanderUserId()) && userId.equals(verdictRecord.getFirstCommanderUserId()) ||
            // ObjectUtil.isNotNull(verdictRecord.getSecondCommanderUserId()) && userId.equals(verdictRecord.getSecondCommanderUserId())
        ) {
            throw new ServiceException("您已选择了阵营，无法再次选择");
        }
        if (ObjectUtil.isNull(verdictRecord.getFirstCampId())) {
            // 只保留操作员角色，删除指挥员和裁决员判断
            // if (chooseCampDto.getUserType() == 1) {
            verdictRecord.setFirstUserId(userId);
            // } else if(chooseCampDto.getUserType() == 2) {
            //     verdictRecord.setFirstJudgeUserId(userId);
            // } else {
            //     verdictRecord.setFirstCommanderUserId(userId);
            // }
            verdictRecord.setFirstCampId(chooseCampDto.getId());
        } else {
            if (chooseCampDto.getId().equals(verdictRecord.getFirstCampId())) {
                // 只保留操作员角色判断
                // if (chooseCampDto.getUserType() == 1) {
                if (ObjectUtil.isNotNull(verdictRecord.getFirstUserId())) {
                    throw new ServiceException("该阵营控制员已被选择");
                } else {
                    verdictRecord.setFirstUserId(userId);
                }
                // } else if (chooseCampDto.getUserType() == 2)
                // {
                //     if (ObjectUtil.isNotNull(verdictRecord.getFirstJudgeUserId())) {
                //         throw new ServiceException("该阵营裁决员已被选择");
                //     } else {
                //         verdictRecord.setFirstJudgeUserId(userId);
                //     }
                // }
                // else {
                //     if (ObjectUtil.isNotNull(verdictRecord.getFirstCommanderUserId())) {
                //         throw new ServiceException("该阵营指挥员已被选择");
                //     } else {
                //         verdictRecord.setFirstCommanderUserId(userId);
                //     }
                // }
            } else {
                if (ObjectUtil.isNull(verdictRecord.getSecondCampId())) {
                    // 只保留操作员角色判断
                    // if (chooseCampDto.getUserType() == 1) {
                    verdictRecord.setSecondUserId(userId);
                    // } else if(chooseCampDto.getUserType() == 2) {
                    //     verdictRecord.setSecondJudgeUserId(userId);
                    // } else
                    // {
                    //     verdictRecord.setSecondCommanderUserId(userId);
                    // }
                    verdictRecord.setSecondCampId(chooseCampDto.getId());
                } else {
                    if (chooseCampDto.getId().equals(verdictRecord.getSecondCampId())) {
                        // 只保留操作员角色判断
                        // if (chooseCampDto.getUserType() == 1) {
                        if (ObjectUtil.isNotNull(verdictRecord.getSecondUserId())) {
                            throw new ServiceException("该阵营控制员已被选择");
                        } else {
                            verdictRecord.setSecondUserId(userId);
                        }
                        // } else if(chooseCampDto.getUserType() == 2) {
                        //     if (ObjectUtil.isNotNull(verdictRecord.getSecondJudgeUserId())) {
                        //         throw new ServiceException("该阵营裁决员已被选择");
                        //     } else {
                        //         verdictRecord.setSecondJudgeUserId(userId);
                        //     }
                        // }
                        // else {
                        //     if (ObjectUtil.isNotNull(verdictRecord.getSecondCommanderUserId())) {
                        //         throw new ServiceException("该阵营指挥员已被选择");
                        //     } else {
                        //         verdictRecord.setSecondCommanderUserId(userId);
                        //     }
                        // }
                    } else {
                        throw new ServiceException("阵营已被选择");
                    }
                }
            }

        }

        baseMapper.updateById(BeanUtil.toBean(verdictRecord, BizVerdictRecord.class));
        // 保存到redis
        saveToCache(verdictRecord.getId());
        BizChessPiecesCampVo vo = new BizChessPiecesCampVo();
        vo.setVerdictRecordId(String.valueOf(verdictRecord.getId()));
        Boolean isChooseCampDone = Boolean.FALSE;
        if (verdictRecord.getFirstCampId() != null && verdictRecord.getSecondCampId() != null) {
            isChooseCampDone = Boolean.TRUE;
        }
        vo.setIsChooseCampDone(isChooseCampDone);
        return vo;
    }

    @Override
    public String startGame(BizVerdictRecordBo startGameDto) {
        log.info("startGame req:{}", JSON.toJSONString(startGameDto));
        BizVerdictRecordVo verdictRecord = baseMapper.selectVoById(startGameDto.getId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("裁定记录不存在,req:{}", JSON.toJSONString(startGameDto));
            throw new ServiceException("裁定记录不存在");
        }
        if (verdictRecord.getFirstCampId() == null || verdictRecord.getSecondCampId() == null) {
            log.error("双方还未准备就绪,req:{}", JSON.toJSONString(startGameDto));
            throw new ServiceException("双方还未准备就绪");
        }
        if (!VerdictRecordStatusEnum.INIT.getCode().equals(verdictRecord.getStatus().intValue())) {
            log.error("比赛状态不在初始化,req:{}", JSON.toJSONString(startGameDto));
            throw new ServiceException("比赛状态不在初始化");
        }
        // 观战模式
        verdictRecord.setWatchMode(startGameDto.getWatchMode());
        verdictRecord.setStatus(Long.valueOf(VerdictRecordStatusEnum.DEPLOYING.getCode()));
        baseMapper.updateById(BeanUtil.toBean(verdictRecord, BizVerdictRecord.class));


        //增加准备阶段
        BizVerdictRecordRoundBo round1 = new BizVerdictRecordRoundBo();
        round1.setVerdictRecordId(verdictRecord.getId());
        round1.setUserId(verdictRecord.getFirstUserId());
        round1.setCampId(verdictRecord.getFirstCampId());
        round1.setStatus(2);
        round1.setChessRound(0L);
        round1.setRoundPeriod(0);
        bizVerdictRecordRoundServiceImpl.insertByBo(round1);

        BizVerdictRecordRoundBo round = new BizVerdictRecordRoundBo();
        round.setVerdictRecordId(verdictRecord.getId());
        round.setUserId(verdictRecord.getSecondUserId());
        round.setCampId(verdictRecord.getSecondCampId());
        round.setStatus(2);
        round.setChessRound(0L);
        round.setRoundPeriod(0);
        bizVerdictRecordRoundServiceImpl.insertByBo(round);

        return "比赛开始,请部署棋子";
    }


    @Override
    public BizVerdictRecordVo queryGameStatus(BizVerdictRecordBo dto) {
        log.info("queryGameStatus req:{}", JSON.toJSONString(dto));
        BizVerdictRecordVo verdictRecord = baseMapper.selectVoById(dto.getId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("裁定记录不存在,req:{}", JSON.toJSONString(dto));
            throw new ServiceException("裁定记录不存在");
        }
        return verdictRecord;
    }


    @Override
    public BizVerdictRecordVo queryRoomUser(BizVerdictRecordBo dto) {
        log.info("queryRoomUser req:{}", JSON.toJSONString(dto));
        BizVerdictRecordVo verdictRecord = baseMapper.selectVoById(dto.getId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("查询房间用户,裁决记录不存在,req:{}", JSON.toJSONString(dto));
            throw new ServiceException("裁定记录不存在");
        }
        BizVerdictRecordVo vo = new BizVerdictRecordVo();
        BeanUtils.copyProperties(verdictRecord, vo);
        if (verdictRecord.getFirstUserId() != null) {
            BizUserVo firstUser = userMapper.selectVoById(verdictRecord.getFirstUserId());
            vo.setFirstUserName(firstUser.getUserName());
        }
        if (verdictRecord.getSecondUserId() != null) {
            BizUserVo secondUser = userMapper.selectVoById(verdictRecord.getSecondUserId());
            vo.setSecondUserName(secondUser.getUserName());
        }
        return vo;
    }


    @Override
    public BizVerdictRecordVo queryVerdictId(BizVerdictRecordBo dto) {
        log.info("queryVerdictId req:{}", JSON.toJSONString(dto));
        BizVerdictRecordVo verdictRecord = baseMapper.selectVoById(dto.getId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("裁决记录不存在,req:{}", JSON.toJSONString(dto));
            throw new ServiceException("裁定记录不存在");
        }
        if (WatchModeEnum.FORBID_WATCH.getCode().equals(verdictRecord.getWatchMode().intValue())) {
            log.error("观战模式为禁止观战,req:{}", JSON.toJSONString(dto));
            throw new ServiceException("观战模式为禁止观战");
        }
//        QueryVerdictIdVo vo = new QueryVerdictIdVo();
//        vo.setVerdictRecordId(String.valueOf(verdictRecord.getId()));
        return verdictRecord;
    }


    @Override
    public BizVerdictRecordVo queryChessRound(BizVerdictRecordBo dto) {
        log.info("queryChessRound req:{}", JSON.toJSONString(dto));
        BizVerdictRecordVo verdictRecord = baseMapper.selectVoById(dto.getId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("裁决记录不存在,req:{}", JSON.toJSONString(dto));
            throw new ServiceException("裁定记录不存在");
        }
        return verdictRecord;
    }


    @Override
    public TableDataInfo<QueryVerdictRecordDetail> queryVerdictRecordList(BizVerdictRecordBo search, PageQuery pageQuery) {
        log.info("queryVerdictRecordList req:{}", JSON.toJSONString(search));
        QueryWrapper<BizVerdictRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != search.getProductId(), "p.id", search.getProductId());
        queryWrapper.eq("bvr.del_flag", "0");
        queryWrapper.eq("bvr.status", "50");
        queryWrapper.isNotNull("bvr.first_user_id");
        queryWrapper.isNotNull("bvr.second_user_id");
        queryWrapper.orderByDesc("bvr.create_time");
        Page<QueryVerdictRecordDetail> result = baseMapper.myQueryPageList(pageQuery.build(), queryWrapper);
        return TableDataInfo.build(result);
//        Page<BizVerdictRecord> p = new Page<>(search.getCurrent(), search.getSize());
//        QueryWrapper<BizVerdictRecord> queryWrapper = new QueryWrapper<>();
//        queryWrapper.lambda().eq(BizVerdictRecord::getDelFlag, "0")
//            .orderByDesc(BizVerdictRecord::getCreateTime);
//        Page<BizVerdictRecordVo> page = baseMapper.selectVoPage(pageQuery.build(), queryWrapper);
//        List<QueryVerdictRecordDetail> detailList = new ArrayList<>();
//        List<BizVerdictRecordVo> verdictRecordList = page.getRecords();
//        //查询用户信息
//        List<Long> allUserIds = new ArrayList<>();
//        List<Long> inviterUserIds = verdictRecordList.stream().map(BizVerdictRecordVo::getInviterUserId).distinct().collect(Collectors.toList());
//        List<Long> firstUserIds = verdictRecordList.stream().map(BizVerdictRecordVo::getFirstUserId).distinct().collect(Collectors.toList());
//        List<Long> secondUserIds = verdictRecordList.stream().map(BizVerdictRecordVo::getSecondUserId).distinct().collect(Collectors.toList());
//        allUserIds.addAll(inviterUserIds);
//        allUserIds.addAll(firstUserIds);
//        allUserIds.addAll(secondUserIds);
//        List<BizUserVo> userList = userMapper.selectVoBatchIds(allUserIds);
//        Map<Long, BizUserVo> userMap = userList.stream().collect(Collectors.toMap(BizUserVo::getId, Function.identity()));
//
//        //查询阵营信息
//        List<Long> allCampIds = new ArrayList<>();
//        List<Long> firstCampIds = verdictRecordList.stream().map(BizVerdictRecordVo::getFirstCampId).distinct().collect(Collectors.toList());
//        List<Long> secondCampIds = verdictRecordList.stream().map(BizVerdictRecordVo::getSecondCampId).distinct().collect(Collectors.toList());
//        allCampIds.addAll(firstCampIds);
//        allCampIds.addAll(secondCampIds);
//        List<BizChessPiecesCampVo> campList = chessPiecesCampMapper.selectVoBatchIds(allCampIds);
//        Map<Long, BizChessPiecesCampVo> campMap = campList.stream().collect(Collectors.toMap(BizChessPiecesCampVo::getId, Function.identity()));
//
//        //查询想定信息
//        List<Long> allScenarioIds = verdictRecordList.stream().map(BizVerdictRecordVo::getScenarioId).distinct().collect(Collectors.toList());
//        List<BizScenarioVo> scenarioList = scenarioMapper.selectVoBatchIds(allScenarioIds);
//        Map<Long, BizScenarioVo> scenarioMap = scenarioList.stream().collect(Collectors.toMap(BizScenarioVo::getId, Function.identity()));
//
//
//        List<Long> productIds = scenarioList.stream().map(BizScenarioVo::getProductId).distinct().collect(Collectors.toList());
//        List<BizProductVo> productVoList = productMapper.selectVoBatchIds(productIds);
//        Map<Long, BizProductVo> productMap = productVoList.stream().collect(Collectors.toMap(BizProductVo::getId, Function.identity()));
//
//
//        page.getRecords().forEach(e -> detailList.add(transferToQueryVerdictRecordDetail(e, userMap, campMap, scenarioMap,productMap)));
//        Page<QueryVerdictRecordDetail> vo = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
//        vo.setRecords(detailList);
//        return TableDataInfo.build(vo);
    }

    @Override
    public TableDataInfo<QueryVerdictRecordDetail> queryUnfinishedList(BizVerdictRecordBo search, PageQuery pageQuery) {
        log.info("queryUnfinishedList req:{}", JSON.toJSONString(search));
        QueryWrapper<BizVerdictRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != search.getProductId(), "p.id", search.getProductId());
        queryWrapper.eq("bvr.del_flag", "0");
        queryWrapper.notInSql("bvr.status","(50)");
        queryWrapper.orderByDesc("bvr.create_time");
        Page<QueryVerdictRecordDetail> result = baseMapper.myQueryPageList(pageQuery.build(), queryWrapper);
        return TableDataInfo.build(result);
    }

    /**
     * 生成房间号
     *
     * @param verdictRecordId
     * @return
     */
    @Override
    public String generateInviteCode(Long verdictRecordId) {
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;
        Object obj = RedisUtils.getCacheObject(ROOM_KEY + randomNumber);
        while (ObjectUtil.isNotNull(obj)) {
            // 重复重新生成
            randomNumber = random.nextInt(9000) + 1000;
        }
        BizVerdictRecordVo bizVerdictRecordVo = baseMapper.selectVoById(verdictRecordId);
        bizVerdictRecordVo.setInviteCode(String.valueOf(randomNumber));
        baseMapper.updateById(BeanUtil.toBean(bizVerdictRecordVo, BizVerdictRecord.class));
        RedisUtils.setCacheObject(ROOM_KEY + randomNumber, bizVerdictRecordVo, Duration.ofSeconds(86400));
        return String.valueOf(randomNumber);
    }

    @Override
    public String joinRoom(String inviteCode, String type) {
        Object room = RedisUtils.getCacheObject(ROOM_KEY + inviteCode);
        if (ObjectUtil.isNull(room)) {
            throw new ServiceException("加入的房间不存在");
        }
        BizVerdictRecordVo bizVerdictRecordVo = BeanUtil.toBean(room, BizVerdictRecordVo.class);
        bizVerdictRecordVo = baseMapper.selectVoById(bizVerdictRecordVo.getId());
        if (ObjectUtil.isNull(bizVerdictRecordVo)) {
            throw new ServiceException("加入的房间不存在");
        }

        if (type.equals("watch")) {
            if (VerdictRecordStatusEnum.INIT.getCode().longValue() == bizVerdictRecordVo.getStatus()) {
                throw new ServiceException("观战的房间正在初始化");
            }
            if (VerdictRecordStatusEnum.END.getCode().longValue() == bizVerdictRecordVo.getStatus()) {
                throw new ServiceException("观战的房间已结束");
            }
            if (20 == bizVerdictRecordVo.getWatchMode()) {
                throw new ServiceException("观战的房间不允许观战");
            }

            return String.valueOf(bizVerdictRecordVo.getId());
        }

        if (VerdictRecordStatusEnum.INIT.getCode().longValue() != bizVerdictRecordVo.getStatus()) {
            throw new ServiceException("加入的房间已开始");
        }

        LoginUser loginUser = LoginHelper.getLoginUser();
        Long userId = loginUser.getUserId();
        boolean firstFlag = ObjectUtil.isNotNull(bizVerdictRecordVo.getFirstUserId());
        // 注释掉指挥员和裁决员相关变量
        // boolean firstJudgeFlag = ObjectUtil.isNotNull(bizVerdictRecordVo.getFirstJudgeUserId());
        boolean secondFlag = ObjectUtil.isNotNull(bizVerdictRecordVo.getSecondUserId());
        // boolean secondJudgeFlag = ObjectUtil.isNotNull(bizVerdictRecordVo.getSecondJudgeUserId());
        // boolean firstCommanderFlag = ObjectUtil.isNotNull(bizVerdictRecordVo.getFirstCommanderUserId());
        // boolean secondCommanderFlag = ObjectUtil.isNotNull(bizVerdictRecordVo.getSecondCommanderUserId());

        // 只保留操作员身份判断
        if (firstFlag && secondFlag && !userId.equals(bizVerdictRecordVo.getFirstUserId()) && !userId.equals(bizVerdictRecordVo.getSecondUserId())) {
            throw new ServiceException("加入的房间已满");
        }
        return String.valueOf(bizVerdictRecordVo.getId());
        // TODO: 2024/3/2 同时加入房间，第二个人可能会覆盖第一人数据，加锁
    }

    @Override
    public BizVerdictRecord queryVerdictRecordByInviteCode(String inviteCode){
        LambdaQueryWrapper<BizVerdictRecord> lqw = Wrappers.lambdaQuery();
        lqw.eq(BizVerdictRecord::getInviteCode, inviteCode);
        return baseMapper.selectOne(lqw);
    }

    /**
     * 获取骰子
     *
     * @param diceCount
     * @return
     */
    @Override
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

    /**
     * 读取到Redis缓存
     *
     * @param verdictRecordId
     */
    @Override
    public void saveToCache(Long verdictRecordId) {
        BizVerdictRecordVo bizVerdictRecordVO = baseMapper.selectVoById(verdictRecordId);
        if (Objects.isNull(bizVerdictRecordVO)) {
            throw new ServiceException("裁决不存在");
        }
        RoomDTO<Object> roomDto = new RoomDTO<>();
        Map<String, Long> userMap = new HashMap<>();
        userMap.put("inviterUserId", bizVerdictRecordVO.getInviterUserId());
        userMap.put("firstUserId", bizVerdictRecordVO.getFirstUserId());
        // 注释掉指挥员和裁决员相关缓存
        // userMap.put("firstCommanderUserId", bizVerdictRecordVO.getFirstCommanderUserId());
        // userMap.put("firstJudgeUserId", bizVerdictRecordVO.getFirstJudgeUserId());
        userMap.put("secondUserId", bizVerdictRecordVO.getSecondUserId());
        // userMap.put("secondJudgeUserId", bizVerdictRecordVO.getSecondJudgeUserId());
        // userMap.put("secondCommanderUserId", bizVerdictRecordVO.getSecondCommanderUserId());
        roomDto.setRoomUserMap(userMap);
        RedisUtils.setCacheObject(ROOM_KEY + verdictRecordId, roomDto, Duration.ofSeconds(86400));
    }

    private QueryVerdictRecordDetail transferToQueryVerdictRecordDetail(BizVerdictRecordVo verdictRecord, Map<Long, BizUserVo> userMap, Map<Long, BizChessPiecesCampVo> campMap, Map<Long, BizScenarioVo> scenarioMap, Map<Long, BizProductVo> productMap) {
        QueryVerdictRecordDetail detail = new QueryVerdictRecordDetail();
        BeanUtils.copyProperties(verdictRecord, detail);
        detail.setVerdictRecordId(verdictRecord.getId());
        detail.setInviterUserId(String.valueOf(verdictRecord.getInviterUserId()));
        detail.setInviterUserName(ObjectUtils.isEmpty(userMap.get(verdictRecord.getInviterUserId())) ? ArbiterConstant.BLANK : userMap.get(verdictRecord.getInviterUserId()).getUserName());
        detail.setFirstUserId(String.valueOf(verdictRecord.getFirstUserId()));
        detail.setFirstUserName(ObjectUtils.isEmpty(userMap.get(verdictRecord.getFirstUserId())) ? ArbiterConstant.BLANK : userMap.get(verdictRecord.getFirstUserId()).getUserName());
        detail.setFirstCampId(String.valueOf(verdictRecord.getFirstCampId()));
        detail.setFirstCampName(ObjectUtils.isEmpty(campMap.get(verdictRecord.getFirstCampId())) ? ArbiterConstant.BLANK : campMap.get(verdictRecord.getFirstCampId()).getName());
        detail.setSecondUserId(String.valueOf(verdictRecord.getSecondUserId()));
        detail.setSecondUserName(ObjectUtils.isEmpty(userMap.get(verdictRecord.getSecondUserId())) ? ArbiterConstant.BLANK : userMap.get(verdictRecord.getSecondUserId()).getUserName());
        detail.setSecondCampId(String.valueOf(verdictRecord.getSecondCampId()));
        detail.setSecondCampName(ObjectUtils.isEmpty(campMap.get(verdictRecord.getSecondCampId())) ? ArbiterConstant.BLANK : campMap.get(verdictRecord.getSecondCampId()).getName());
        detail.setScenarioId(String.valueOf(verdictRecord.getScenarioId()));
        detail.setScenarioName(scenarioMap.get(verdictRecord.getScenarioId()).getScenarioName());
//        detail.setCover(productMap.get(verdictRecord.getp));
        return detail;
    }


    @Override
    public void nextStage(Long id) {
        BizVerdictRecord verdictRecord = baseMapper.selectById(id);
        Pair pair = getRoundInfo(verdictRecord, id);
        //已到最大回合数,比赛结束
        int nextChessRound = Integer.parseInt(String.valueOf(pair.getKey()));
        int nextRoundPeriod = Integer.parseInt(String.valueOf(pair.getValue()));
        if (nextChessRound > verdictRecord.getChessRoundLimit()) {
            verdictRecord.setStatus(Long.valueOf(VerdictRecordStatusEnum.END.getCode()));
        } else {
            verdictRecord.setChessRound((long) nextChessRound);
            verdictRecord.setRoundPeriod(nextRoundPeriod);
        }
        baseMapper.updateById(verdictRecord);
        //修改棋子的位置信息，将from 和coordinate 统一
        verdictRecordChessPiecesMapper.updateFromLocation(id);
    }


    private Pair getRoundInfo(BizVerdictRecord verdictRecord, Long verdictRecordId) {
        if (verdictRecord.getRoundPeriod().equals(RoundPeriodEnum.second_stage.getCode())) {

            return Pair.of(verdictRecord.getChessRound(), RoundPeriodEnum.third_stage.getCode());

        } else if (verdictRecord.getRoundPeriod().equals(RoundPeriodEnum.fourth_stage.getCode())) {
            //计算上一轮的分数
            Long chessRound = verdictRecord.getChessRound();
            //这里为了能够调用接口，所以将参数封装成bo 传入
            BizVerdictRecordHistoryBo historyBo = new BizVerdictRecordHistoryBo();
            historyBo.setVerdictRecordId(verdictRecordId);
            historyBo.setChessRound(chessRound);
            verdictRecordHistoryService.calculateRoundScore(historyBo);
//
//            if(verdictRecord.getChessRound() == 1)
//            {
//                BizVerdictRecordHistoryBo historyBo2 = new BizVerdictRecordHistoryBo();
//                historyBo2.setVerdictRecordId(verdictRecordId);
//                historyBo2.setChessRound(0L);
//                verdictRecordHistoryService.calculateRoundScore(historyBo2);
//            }

            return Pair.of(verdictRecord.getChessRound() + 1, RoundPeriodEnum.first_stage.getCode());

        } else {
            return Pair.of(verdictRecord.getChessRound(), verdictRecord.getRoundPeriod() + ArbiterConstant.ONE);
        }
    }

    @Override
    public String setScore(Long id, Long firstScore, Long secondScore){
        BizVerdictRecord verdictRecord = baseMapper.selectById(id);
        verdictRecord.setFirstScore(firstScore);
        verdictRecord.setSecondScore(secondScore);
        baseMapper.updateById(verdictRecord);
        return "success";
    }

    @Override
    public Long getScoreByCampId(Long id, Long campId){
        BizVerdictRecord verdictRecord = baseMapper.selectById(id);
        if(campId.equals(verdictRecord.getFirstCampId())){
            return verdictRecord.getFirstScore();
        }
        else if(campId.equals(verdictRecord.getSecondCampId())){
            return verdictRecord.getSecondScore();
        }
        return 0L;
    }
}
