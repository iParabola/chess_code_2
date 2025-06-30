package com.ruoyi.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.business.domain.bo.BizVerdictRecordScoreBo;
import com.ruoyi.business.domain.vo.BizVerdictRecordScoreVo;
import com.ruoyi.business.domain.BizVerdictRecordScore;
import com.ruoyi.business.mapper.BizVerdictRecordScoreMapper;
import com.ruoyi.business.service.IBizVerdictRecordScoreService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 裁决记录分数管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-04
 */
@RequiredArgsConstructor
@Service
public class BizVerdictRecordScoreServiceImpl implements IBizVerdictRecordScoreService {

    private final BizVerdictRecordScoreMapper baseMapper;

    /**
     * 查询裁决记录分数管理
     */
    @Override
    public BizVerdictRecordScoreVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询裁决记录分数管理列表
     */
    @Override
    public TableDataInfo<BizVerdictRecordScoreVo> queryPageList(BizVerdictRecordScoreBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizVerdictRecordScore> lqw = buildQueryWrapper(bo);
        Page<BizVerdictRecordScoreVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询裁决记录分数管理列表
     */
    @Override
    public List<BizVerdictRecordScoreVo> queryList(BizVerdictRecordScoreBo bo) {
        LambdaQueryWrapper<BizVerdictRecordScore> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizVerdictRecordScore> buildQueryWrapper(BizVerdictRecordScoreBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizVerdictRecordScore> lqw = Wrappers.lambdaQuery();
        return lqw;
    }

    /**
     * 新增裁决记录分数管理
     */
    @Override
    public Boolean insertByBo(BizVerdictRecordScoreBo bo) {
        BizVerdictRecordScore add = BeanUtil.toBean(bo, BizVerdictRecordScore.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改裁决记录分数管理
     */
    @Override
    public Boolean updateByBo(BizVerdictRecordScoreBo bo) {
        BizVerdictRecordScore update = BeanUtil.toBean(bo, BizVerdictRecordScore.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizVerdictRecordScore entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除裁决记录分数管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }


    @Override
    public void submitScore(List<BizVerdictRecordScore> bos) {
        baseMapper.insertBatch(bos);
    }

    @Override
    public List<BizVerdictRecordScoreVo> queryScoreList(BizVerdictRecordScoreBo bo) {
        LambdaQueryWrapper<BizVerdictRecordScore> lqw = Wrappers.lambdaQuery();
        lqw.eq(ObjectUtil.isNotNull(bo.getVerdictRecordId()), BizVerdictRecordScore::getVerdictRecordId, bo.getVerdictRecordId());
        lqw.eq(ObjectUtil.isNotNull(bo.getCampId()), BizVerdictRecordScore::getCampId, bo.getCampId());
        lqw.eq(ObjectUtil.isNotNull(bo.getChessRound()), BizVerdictRecordScore::getChessRound, bo.getChessRound());
        if (StringUtils.isNotBlank(bo.getTypeString())) {
            lqw.in( BizVerdictRecordScore::getType, Arrays.asList(bo.getTypeString().split(",")));
        }
         lqw.orderByAsc(BizVerdictRecordScore::getId);
        return baseMapper.selectVoList(lqw);
    }


    @Override
    public List<BizVerdictRecordScoreVo> getRealTimeScore(BizVerdictRecordScoreBo bo) {
        return baseMapper.getRealTimeScore(bo.getVerdictRecordId());
    }

    @Override
    public List<BizVerdictRecordScoreVo> getSummaryScore(BizVerdictRecordScoreBo bo) {
        return baseMapper.getSummaryScore(bo.getVerdictRecordId());
    }

    @Override
    public List<BizVerdictRecordScoreVo> getSummaryScoreNew(BizVerdictRecordScoreBo bo) {
        return baseMapper.getSummaryScoreNew(bo.getVerdictRecordId());
    }


    @Override
    public List<BizVerdictRecordScoreVo> getSummaryTotal(BizVerdictRecordScoreBo bo) {
        return baseMapper.getSummaryTotal(bo.getVerdictRecordId());
    }
}
