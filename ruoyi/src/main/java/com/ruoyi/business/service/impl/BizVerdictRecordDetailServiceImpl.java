package com.ruoyi.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.business.domain.BizChessPieces;
import com.ruoyi.business.domain.bo.BizChessPiecesBo;
import com.ruoyi.business.domain.bo.BizVerdictRecordBo;
import com.ruoyi.business.domain.vo.BizChessPiecesVo;
import com.ruoyi.business.domain.vo.BizVerdictRecordVo;
import com.ruoyi.business.mapper.BizChessPiecesMapper;
import com.ruoyi.business.mapper.BizVerdictRecordMapper;
import com.ruoyi.common.constant.ArbiterConstant;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.ruoyi.business.domain.bo.BizVerdictRecordDetailBo;
import com.ruoyi.business.domain.vo.BizVerdictRecordDetailVo;
import com.ruoyi.business.domain.BizVerdictRecordDetail;
import com.ruoyi.business.mapper.BizVerdictRecordDetailMapper;
import com.ruoyi.business.service.IBizVerdictRecordDetailService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 裁决记录详情Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-21
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class BizVerdictRecordDetailServiceImpl implements IBizVerdictRecordDetailService {

    private final BizVerdictRecordDetailMapper baseMapper;

    private final BizVerdictRecordMapper verdictRecordMapper;

    private final BizChessPiecesMapper chessPiecesMapper;

    private final BizVerdictRecordDetailMapper verdictRecordDetailMapper;

    /**
     * 查询裁决记录详情
     */
    @Override
    public BizVerdictRecordDetailVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询裁决记录详情列表
     */
    @Override
    public TableDataInfo<BizVerdictRecordDetailVo> queryPageList(BizVerdictRecordDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizVerdictRecordDetail> lqw = buildQueryWrapper(bo);
        Page<BizVerdictRecordDetailVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询裁决记录详情列表
     */
    @Override
    public List<BizVerdictRecordDetailVo> queryList(BizVerdictRecordDetailBo bo) {
        LambdaQueryWrapper<BizVerdictRecordDetail> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizVerdictRecordDetail> buildQueryWrapper(BizVerdictRecordDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizVerdictRecordDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getVerdictRecordId() != null, BizVerdictRecordDetail::getVerdictRecordId, bo.getVerdictRecordId());
        lqw.eq(bo.getUserId() != null, BizVerdictRecordDetail::getUserId, bo.getUserId());
        lqw.eq(bo.getCampId() != null, BizVerdictRecordDetail::getCampId, bo.getCampId());
        lqw.eq(StringUtils.isNotBlank(bo.getSelfChessPiecesNumber()), BizVerdictRecordDetail::getSelfChessPiecesNumber, bo.getSelfChessPiecesNumber());
        lqw.like(StringUtils.isNotBlank(bo.getSelfChessPiecesName()), BizVerdictRecordDetail::getSelfChessPiecesName, bo.getSelfChessPiecesName());
        lqw.eq(bo.getSelfChessPiecesTypeId() != null, BizVerdictRecordDetail::getSelfChessPiecesTypeId, bo.getSelfChessPiecesTypeId());
        lqw.like(StringUtils.isNotBlank(bo.getSelfChessPiecesTypeName()), BizVerdictRecordDetail::getSelfChessPiecesTypeName, bo.getSelfChessPiecesTypeName());
        lqw.eq(StringUtils.isNotBlank(bo.getSelfCoordinate()), BizVerdictRecordDetail::getSelfCoordinate, bo.getSelfCoordinate());
        lqw.eq(bo.getSelfStatus() != null, BizVerdictRecordDetail::getSelfStatus, bo.getSelfStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getTargetChessPiecesNumber()), BizVerdictRecordDetail::getTargetChessPiecesNumber, bo.getTargetChessPiecesNumber());
        lqw.like(StringUtils.isNotBlank(bo.getTargetChessPiecesName()), BizVerdictRecordDetail::getTargetChessPiecesName, bo.getTargetChessPiecesName());
        lqw.eq(bo.getTargetChessPiecesTypeId() != null, BizVerdictRecordDetail::getTargetChessPiecesTypeId, bo.getTargetChessPiecesTypeId());
        lqw.like(StringUtils.isNotBlank(bo.getTargetChessPiecesTypeName()), BizVerdictRecordDetail::getTargetChessPiecesTypeName, bo.getTargetChessPiecesTypeName());
        lqw.eq(StringUtils.isNotBlank(bo.getTargetCoordinate()), BizVerdictRecordDetail::getTargetCoordinate, bo.getTargetCoordinate());
        lqw.eq(bo.getTargetStatus() != null, BizVerdictRecordDetail::getTargetStatus, bo.getTargetStatus());
        lqw.eq(bo.getDiceAmount() != null, BizVerdictRecordDetail::getDiceAmount, bo.getDiceAmount());
        lqw.eq(bo.getIsVisible() != null, BizVerdictRecordDetail::getIsVisible, bo.getIsVisible());
        lqw.eq(StringUtils.isNotBlank(bo.getDiceValue()), BizVerdictRecordDetail::getDiceValue, bo.getDiceValue());
        lqw.eq(StringUtils.isNotBlank(bo.getAttackLevel()), BizVerdictRecordDetail::getAttackLevel, bo.getAttackLevel());
        lqw.eq(StringUtils.isNotBlank(bo.getVerdictResult()), BizVerdictRecordDetail::getVerdictResult, bo.getVerdictResult());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizVerdictRecordDetail::getExt, bo.getExt());
        return lqw;
    }

    /**
     * 新增裁决记录详情
     */
    @Override
    public Boolean insertByBo(BizVerdictRecordDetailBo bo) {
        BizVerdictRecordDetail add = BeanUtil.toBean(bo, BizVerdictRecordDetail.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改裁决记录详情
     */
    @Override
    public Boolean updateByBo(BizVerdictRecordDetailBo bo) {
        BizVerdictRecordDetail update = BeanUtil.toBean(bo, BizVerdictRecordDetail.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizVerdictRecordDetail entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除裁决记录详情
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }


    @Override
    public List<BizVerdictRecordDetailVo> goVerdictRecordDetail(BizVerdictRecordDetailBo dto) {
        log.info("goVerdictRecordDetail req:{}", JSON.toJSONString(dto));
        List<BizVerdictRecordDetailVo> detailList = baseMapper.selectVoList(new QueryWrapper<BizVerdictRecordDetail>().lambda()
            .eq(BizVerdictRecordDetail::getDelFlag,"0")
            .eq(BizVerdictRecordDetail::getVerdictRecordId, dto.getVerdictRecordId())
            .eq(BizVerdictRecordDetail::getUserId, dto.getUserId()));
        if (CollectionUtils.isEmpty(detailList)) {
            log.error("goVerdictRecordDetail detailList is empty,req:{}", JSON.toJSONString(dto));
            throw new ServiceException("请稍等,对方阵营还未选择");
        }
//        List<BizVerdictRecordDetailVo> voList = new ArrayList<>();
//        detailList.stream().forEach(detail -> {
//            GoVerdictRecordDetailVo vo = new GoVerdictRecordDetailVo();
//            BeanUtils.copyProperties(detail, vo);
//            vo.setVerdictRecordDetailId(String.valueOf(detail.getId()));
//            vo.setSelfChessPiecesTypeId(String.valueOf(detail.getSelfChessPiecesTypeId()));
//            vo.setTargetChessPiecesTypeId(String.valueOf(detail.getTargetChessPiecesTypeId()));
//            voList.add(vo);
//        });
        return detailList;
    }


    @Override
    public List<String> targetChessPiecesNumberList(BizVerdictRecordBo dto) {
        log.info("targetChessPiecesNumberList req:{}", JSON.toJSONString(dto));
        BizVerdictRecordVo verdictRecord = verdictRecordMapper.selectVoById(dto.getId());
        if (ObjectUtils.isEmpty(verdictRecord)) {
            log.error("targetChessPiecesNumberList verdictRecord is empty,req:{}", JSON.toJSONString(dto));
            throw new ServiceException("裁决记录不存在");
        }
        Long targetUserId;
        if (dto.getUserId().equals(verdictRecord.getFirstUserId())) {
            targetUserId = verdictRecord.getSecondUserId();

        } else if (dto.getUserId().equals(verdictRecord.getSecondUserId())) {
            targetUserId = verdictRecord.getFirstUserId();

        } else {
            log.error("targetChessPiecesNumberList user not in verdict,req:{}", JSON.toJSONString(dto));
            throw new ServiceException("您不在裁决对决里");
        }

        List<BizVerdictRecordDetailVo> detailList = baseMapper.selectVoList(new QueryWrapper<BizVerdictRecordDetail>().lambda()
            .eq(BizVerdictRecordDetail::getDelFlag, "0")
            .eq(BizVerdictRecordDetail::getVerdictRecordId, dto.getId())
            .eq(BizVerdictRecordDetail::getUserId, targetUserId));
        List<String> targetChessPiecesNumberList = detailList.stream().map(BizVerdictRecordDetailVo::getSelfChessPiecesNumber).collect(Collectors.toList());
        log.info("targetChessPiecesNumberList resp:{}", JSON.toJSONString(targetChessPiecesNumberList));
        return targetChessPiecesNumberList;
    }


    @Override
    public BizChessPiecesVo targetChessPiecesDetail(BizChessPiecesBo dto) {
        log.info("targetChessPiecesDetail req:{}", JSON.toJSONString(dto));
        BizChessPiecesVo chessPieces = chessPiecesMapper.selectVoOne(new QueryWrapper<BizChessPieces>().lambda().eq(BizChessPieces::getChessPiecesNumber, dto.getChessPiecesNumber()));
        if (ObjectUtils.isEmpty(chessPieces)) {
            log.error("targetChessPiecesDetail chessPieces is not exist,dto:{}", JSON.toJSONString(dto));
            throw new ServiceException("targetChessPiecesDetail chessPieces is not exist");
        }
//        TargetChessPiecesDetailVo vo = new TargetChessPiecesDetailVo();
//        BeanUtils.copyProperties(chessPieces, vo);
//        vo.setChessPiecesTypeId(String.valueOf(chessPieces.getChessPiecesTypeId()));
//        log.info("targetChessPiecesDetail resp:{}", JSON.toJSONString(vo));
        return chessPieces;
    }


    @Override
    public BizVerdictRecordDetailVo verdict(BizVerdictRecordDetailBo dto) {
        log.info("verdict req:{}", JSON.toJSONString(dto));
        BizVerdictRecordDetailVo verdictRecordDetail = verdictRecordDetailMapper.selectVoById(dto.getId());
        if (ObjectUtils.isEmpty(verdictRecordDetail)) {
            log.error("verdict record detail not exist,req:{}", JSON.toJSONString(dto));
            throw new ServiceException("裁决记录详情不存在");
        }
        BizChessPiecesVo chessPieces = chessPiecesMapper.selectVoOne(new LambdaQueryWrapper<BizChessPieces>().eq(BizChessPieces::getChessPiecesNumber, dto.getTargetChessPiecesNumber()).last("limit 1"));
        if (ObjectUtils.isEmpty(chessPieces)) {
            log.error("chessPieces not exist,req:{}", JSON.toJSONString(dto));
            throw new ServiceException("目标棋子不存在");
        }
        verdictRecordDetail.setSelfCoordinate(dto.getSelfCoordinate());
        verdictRecordDetail.setTargetChessPiecesNumber(dto.getTargetChessPiecesNumber());
        verdictRecordDetail.setTargetChessPiecesName(chessPieces.getChessPiecesName());
        verdictRecordDetail.setTargetChessPiecesTypeId(chessPieces.getChessPiecesTypeId());
        verdictRecordDetail.setTargetChessPiecesTypeName(chessPieces.getChessPiecesTypeName());
        verdictRecordDetail.setIsVisible(1);

        Random random = new Random();
        String a = String.valueOf(random.nextInt(7));
        String b = String.valueOf(random.nextInt(7));
        String diceValue = a + ArbiterConstant.ENGLISH_COMMA + b;
        verdictRecordDetail.setDiceValue(diceValue);

        String attackLevel = String.valueOf(random.nextInt(10));
        verdictRecordDetail.setAttackLevel(attackLevel);

        List<String> list = Arrays.asList("KF", "K", "Km", "K", "S");
        String verdictResult = list.get(random.nextInt(5));
        verdictRecordDetail.setVerdictResult(verdictResult);
        baseMapper.updateById(BeanUtil.toBean(verdictRecordDetail, BizVerdictRecordDetail.class));
//
//        VerdictVo vo = new VerdictVo();
//        BeanUtils.copyProperties(verdictRecordDetail, vo);
        return verdictRecordDetail;
    }
}
