package com.ruoyi.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.business.domain.bo.BizVerdictBlowEffectBo;
import com.ruoyi.business.domain.vo.BizVerdictBlowEffectVo;
import com.ruoyi.business.domain.BizVerdictBlowEffect;
import com.ruoyi.business.mapper.BizVerdictBlowEffectMapper;
import com.ruoyi.business.service.IBizVerdictBlowEffectService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 裁决打击效果Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@RequiredArgsConstructor
@Service
public class BizVerdictBlowEffectServiceImpl implements IBizVerdictBlowEffectService {

    private final BizVerdictBlowEffectMapper baseMapper;

    /**
     * 查询裁决打击效果
     */
    @Override
    public BizVerdictBlowEffectVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询裁决打击效果列表
     */
    @Override
    public TableDataInfo<BizVerdictBlowEffectVo> queryPageList(BizVerdictBlowEffectBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizVerdictBlowEffect> lqw = buildQueryWrapper(bo);
        Page<BizVerdictBlowEffectVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询裁决打击效果列表
     */
    @Override
    public List<BizVerdictBlowEffectVo> queryList(BizVerdictBlowEffectBo bo) {
        LambdaQueryWrapper<BizVerdictBlowEffect> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizVerdictBlowEffect> buildQueryWrapper(BizVerdictBlowEffectBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizVerdictBlowEffect> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getVerdictType() != null, BizVerdictBlowEffect::getVerdictType, bo.getVerdictType());
        lqw.eq(bo.getAttackScore() != null, BizVerdictBlowEffect::getAttackScore, bo.getAttackScore());
        lqw.eq(bo.getDiceScore() != null, BizVerdictBlowEffect::getDiceScore, bo.getDiceScore());
        lqw.eq(StringUtils.isNotBlank(bo.getBlowEffect()), BizVerdictBlowEffect::getBlowEffect, bo.getBlowEffect());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizVerdictBlowEffect::getExt, bo.getExt());
        return lqw;
    }

    /**
     * 新增裁决打击效果
     */
    @Override
    public Boolean insertByBo(BizVerdictBlowEffectBo bo) {
        BizVerdictBlowEffect add = BeanUtil.toBean(bo, BizVerdictBlowEffect.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改裁决打击效果
     */
    @Override
    public Boolean updateByBo(BizVerdictBlowEffectBo bo) {
        BizVerdictBlowEffect update = BeanUtil.toBean(bo, BizVerdictBlowEffect.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizVerdictBlowEffect entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除裁决打击效果
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }


    @Override
    public BizVerdictBlowEffect queryRecord(Integer verdictType, Integer attackScore, Integer diceScore) {
        return baseMapper.selectOne(new LambdaQueryWrapper<BizVerdictBlowEffect>().eq(BizVerdictBlowEffect::getVerdictType, verdictType)
            .eq(BizVerdictBlowEffect::getAttackScore, attackScore)
            .eq(BizVerdictBlowEffect::getDiceScore, diceScore));
    }

    @Override
    public List<BizVerdictBlowEffectVo> getBlowEffectList(BizVerdictBlowEffectBo dto) {
        return baseMapper.getBlowEffectList(dto.getVerdictType());
    }
}
