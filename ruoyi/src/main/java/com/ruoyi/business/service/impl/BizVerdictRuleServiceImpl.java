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
import com.ruoyi.business.domain.bo.BizVerdictRuleBo;
import com.ruoyi.business.domain.vo.BizVerdictRuleVo;
import com.ruoyi.business.domain.BizVerdictRule;
import com.ruoyi.business.mapper.BizVerdictRuleMapper;
import com.ruoyi.business.service.IBizVerdictRuleService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 裁决表管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@RequiredArgsConstructor
@Service
public class BizVerdictRuleServiceImpl implements IBizVerdictRuleService {

    private final BizVerdictRuleMapper baseMapper;

    /**
     * 查询裁决表管理
     */
    @Override
    public BizVerdictRuleVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询裁决表管理列表
     */
    @Override
    public TableDataInfo<BizVerdictRuleVo> queryPageList(BizVerdictRuleBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizVerdictRule> lqw = buildQueryWrapper(bo);
        Page<BizVerdictRuleVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询裁决表管理列表
     */
    @Override
    public List<BizVerdictRuleVo> queryList(BizVerdictRuleBo bo) {
        LambdaQueryWrapper<BizVerdictRule> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizVerdictRule> buildQueryWrapper(BizVerdictRuleBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizVerdictRule> lqw = Wrappers.lambdaQuery();
        return lqw;
    }

    /**
     * 新增裁决表管理
     */
    @Override
    public Boolean insertByBo(BizVerdictRuleBo bo) {
        BizVerdictRule add = BeanUtil.toBean(bo, BizVerdictRule.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改裁决表管理
     */
    @Override
    public Boolean updateByBo(BizVerdictRuleBo bo) {
        BizVerdictRule update = BeanUtil.toBean(bo, BizVerdictRule.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizVerdictRule entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除裁决表管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public List<BizVerdictRule> queryRecord(String selfWeaponName, String targetWeaponName, Integer distance) {
        return baseMapper.selectList(new LambdaQueryWrapper<BizVerdictRule>().eq(BizVerdictRule::getSelfWeaponName, selfWeaponName)
            .eq(BizVerdictRule::getTargetWeaponName, targetWeaponName)
            .eq(BizVerdictRule::getDistance, distance)
            .orderByDesc(BizVerdictRule::getCreateTime));
    }


    @Override
    public List<BizVerdictRuleVo> getRuleList(BizVerdictRuleBo dto) {
        return baseMapper.getRuleList(dto.getVerdictType());
    }
}
