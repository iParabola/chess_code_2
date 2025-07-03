package com.ruoyi.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.business.domain.bo.BizVerdictRecordRoundBo;
import com.ruoyi.business.domain.vo.BizVerdictRecordRoundVo;
import com.ruoyi.business.domain.BizVerdictRecordRound;
import com.ruoyi.business.mapper.BizVerdictRecordRoundMapper;
import com.ruoyi.business.service.IBizVerdictRecordRoundService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 裁决记录回合管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-07
 */
@RequiredArgsConstructor
@Service
public class BizVerdictRecordRoundServiceImpl implements IBizVerdictRecordRoundService {

    private final BizVerdictRecordRoundMapper baseMapper;

    public Long getIdByRecordIdAndCampId(Long id, Long CampId) {
        LambdaQueryWrapper<BizVerdictRecordRound> lqw = Wrappers.lambdaQuery();
        lqw.eq(BizVerdictRecordRound::getVerdictRecordId, id);
        lqw.eq(BizVerdictRecordRound::getCampId, CampId);
        lqw.eq(BizVerdictRecordRound::getStatus, 2);
        lqw.eq(BizVerdictRecordRound::getRoundPeriod, 0);
        lqw.eq(BizVerdictRecordRound::getRoundPeriod, 0);
        BizVerdictRecordRound bizVerdictRecordRound = baseMapper.selectOne(lqw);
        if (bizVerdictRecordRound != null) {
            return bizVerdictRecordRound.getId();
        }
        return null;
    }

    /**
     * 查询裁决记录回合管理
     */
    @Override
    public BizVerdictRecordRoundVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询裁决记录回合管理列表
     */
    @Override
    public TableDataInfo<BizVerdictRecordRoundVo> queryPageList(BizVerdictRecordRoundBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizVerdictRecordRound> lqw = buildQueryWrapper(bo);
        Page<BizVerdictRecordRoundVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询裁决记录回合管理列表
     */
    @Override
    public List<BizVerdictRecordRoundVo> queryList(BizVerdictRecordRoundBo bo) {
        LambdaQueryWrapper<BizVerdictRecordRound> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizVerdictRecordRound> buildQueryWrapper(BizVerdictRecordRoundBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizVerdictRecordRound> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getVerdictRecordId() != null, BizVerdictRecordRound::getVerdictRecordId, bo.getVerdictRecordId());
        lqw.eq(bo.getUserId() != null, BizVerdictRecordRound::getUserId, bo.getUserId());
        lqw.eq(bo.getCampId() != null, BizVerdictRecordRound::getCampId, bo.getCampId());
        lqw.eq(bo.getChessRound() != null, BizVerdictRecordRound::getChessRound, bo.getChessRound());
        lqw.eq(bo.getRoundPeriod() != null, BizVerdictRecordRound::getRoundPeriod, bo.getRoundPeriod());
        lqw.eq(bo.getStatus() != null, BizVerdictRecordRound::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增裁决记录回合管理
     */
    @Override
    public Boolean insertByBo(BizVerdictRecordRoundBo bo) {
        BizVerdictRecordRound add = BeanUtil.toBean(bo, BizVerdictRecordRound.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改裁决记录回合管理
     */
    @Override
    public Boolean updateByBo(BizVerdictRecordRoundBo bo) {
        BizVerdictRecordRound update = BeanUtil.toBean(bo, BizVerdictRecordRound.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizVerdictRecordRound entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除裁决记录回合管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public List<BizVerdictRecordRoundVo> getRoundStatus(BizVerdictRecordRoundBo bo) {
        LambdaQueryWrapper<BizVerdictRecordRound> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getVerdictRecordId() != null, BizVerdictRecordRound::getVerdictRecordId, bo.getVerdictRecordId());
        lqw.eq(bo.getChessRound() != null, BizVerdictRecordRound::getChessRound, bo.getChessRound());
        lqw.eq(bo.getRoundPeriod() != null, BizVerdictRecordRound::getRoundPeriod, bo.getRoundPeriod());
        return baseMapper.selectVoList(lqw);
    }

    @Override
    public Boolean saveRoundCover(BizVerdictRecordRoundBo bo) {
        LambdaQueryWrapper<BizVerdictRecordRound> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getVerdictRecordId() != null, BizVerdictRecordRound::getVerdictRecordId, bo.getVerdictRecordId());
        lqw.eq(bo.getChessRound() != null, BizVerdictRecordRound::getChessRound, bo.getChessRound());
        lqw.eq(bo.getRoundPeriod() != null, BizVerdictRecordRound::getRoundPeriod, bo.getRoundPeriod());
        lqw.eq(bo.getCampId() != null, BizVerdictRecordRound::getCampId, bo.getCampId());
        lqw.last("limit 1");
        BizVerdictRecordRound recordRound = baseMapper.selectOne(lqw);
        recordRound.setCoverUrl(bo.getCoverUrl());
        return baseMapper.updateById(recordRound)>0;
    }

    @Override
    public List<BizVerdictRecordRoundVo> getHistoryTreeByRound(BizVerdictRecordRoundBo bo) {
        LambdaQueryWrapper<BizVerdictRecordRound> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getVerdictRecordId() != null, BizVerdictRecordRound::getVerdictRecordId, bo.getVerdictRecordId());
        lqw.eq(bo.getChessRound() != null, BizVerdictRecordRound::getChessRound, bo.getChessRound());
        lqw.eq(bo.getRoundPeriod() != null, BizVerdictRecordRound::getRoundPeriod, bo.getRoundPeriod());
        lqw.eq(bo.getCampId() != null, BizVerdictRecordRound::getCampId, bo.getCampId());
        return baseMapper.getHistoryTreeByRound(lqw);
    }

    @Override
    public String stepJudge(BizVerdictRecordRoundBo bo){
        LambdaQueryWrapper<BizVerdictRecordRound> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getVerdictRecordId() != null, BizVerdictRecordRound::getVerdictRecordId, bo.getVerdictRecordId());
        lqw.eq(bo.getChessRound() != null, BizVerdictRecordRound::getChessRound, bo.getChessRound());
        lqw.eq(bo.getRoundPeriod() != null, BizVerdictRecordRound::getRoundPeriod, bo.getRoundPeriod());
        lqw.eq(bo.getCampId() != null, BizVerdictRecordRound::getCampId, bo.getCampId());
        BizVerdictRecordRound recordRound = baseMapper.selectOne(lqw);
        recordRound.setStatus(2);
        baseMapper.updateById(recordRound);
        return "success";
    }

    @Override
    public Boolean saveTextInstruction(BizVerdictRecordRoundBo bo) {
        LambdaQueryWrapper<BizVerdictRecordRound> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getVerdictRecordId() != null, BizVerdictRecordRound::getVerdictRecordId, bo.getVerdictRecordId());
        lqw.eq(bo.getChessRound() != null, BizVerdictRecordRound::getChessRound, bo.getChessRound());
        lqw.eq(bo.getRoundPeriod() != null, BizVerdictRecordRound::getRoundPeriod, bo.getRoundPeriod());
        lqw.eq(bo.getCampId() != null, BizVerdictRecordRound::getCampId, bo.getCampId());
        lqw.last("limit 1");
        BizVerdictRecordRound recordRound = baseMapper.selectOne(lqw);
        recordRound.setTextIns(bo.getTextIns());
        return baseMapper.updateById(recordRound)>0;
    }




}
