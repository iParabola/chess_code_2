package com.ruoyi.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.constant.ArbiterConstant;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.business.domain.bo.BizIndirectBlowEffectBo;
import com.ruoyi.business.domain.vo.BizIndirectBlowEffectVo;
import com.ruoyi.business.domain.BizIndirectBlowEffect;
import com.ruoyi.business.mapper.BizIndirectBlowEffectMapper;
import com.ruoyi.business.service.IBizIndirectBlowEffectService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 间瞄打击效果Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@RequiredArgsConstructor
@Service
public class BizIndirectBlowEffectServiceImpl implements IBizIndirectBlowEffectService {

    private final BizIndirectBlowEffectMapper baseMapper;

    /**
     * 查询间瞄打击效果
     */
    @Override
    public BizIndirectBlowEffectVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询间瞄打击效果列表
     */
    @Override
    public TableDataInfo<BizIndirectBlowEffectVo> queryPageList(BizIndirectBlowEffectBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizIndirectBlowEffect> lqw = buildQueryWrapper(bo);
        Page<BizIndirectBlowEffectVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询间瞄打击效果列表
     */
    @Override
    public List<BizIndirectBlowEffectVo> queryList(BizIndirectBlowEffectBo bo) {
        LambdaQueryWrapper<BizIndirectBlowEffect> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizIndirectBlowEffect> buildQueryWrapper(BizIndirectBlowEffectBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizIndirectBlowEffect> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAttackScore() != null, BizIndirectBlowEffect::getAttackScore, bo.getAttackScore());
        lqw.eq(bo.getDiceScore() != null, BizIndirectBlowEffect::getDiceScore, bo.getDiceScore());
        lqw.eq(StringUtils.isNotBlank(bo.getBlowEffect()), BizIndirectBlowEffect::getBlowEffect, bo.getBlowEffect());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizIndirectBlowEffect::getExt, bo.getExt());
        return lqw;
    }

    /**
     * 新增间瞄打击效果
     */
    @Override
    public Boolean insertByBo(BizIndirectBlowEffectBo bo) {
        BizIndirectBlowEffect add = BeanUtil.toBean(bo, BizIndirectBlowEffect.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改间瞄打击效果
     */
    @Override
    public Boolean updateByBo(BizIndirectBlowEffectBo bo) {
        BizIndirectBlowEffect update = BeanUtil.toBean(bo, BizIndirectBlowEffect.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizIndirectBlowEffect entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除间瞄打击效果
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }


    @Override
    public BizIndirectBlowEffect getByAttackScoreAndDiceScore(Long attackScore, Integer diceScore) {
        return baseMapper.selectOne(new LambdaQueryWrapper<BizIndirectBlowEffect>()
            .eq(BizIndirectBlowEffect::getAttackScore, attackScore)
            .eq(BizIndirectBlowEffect::getDiceScore, diceScore)
            .orderByDesc(BizIndirectBlowEffect::getCreateTime)
            .last(ArbiterConstant.LIMIT_ONE));
    }
}
