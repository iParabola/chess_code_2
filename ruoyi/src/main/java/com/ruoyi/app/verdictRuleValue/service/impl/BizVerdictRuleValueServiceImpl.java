package com.ruoyi.app.verdictRuleValue.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.app.verdictRuleValue.domain.bo.BizVerdictRuleValueBo;
import com.ruoyi.app.verdictRuleValue.domain.vo.BizVerdictRuleValueVo;
import com.ruoyi.app.verdictRuleValue.domain.BizVerdictRuleValue;
import com.ruoyi.app.verdictRuleValue.mapper.BizVerdictRuleValueMapper;
import com.ruoyi.app.verdictRuleValue.service.IBizVerdictRuleValueService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 裁决规则标准值表Service业务层处理
 *
 * @author ccc
 * @date 2024-09-13
 */
@RequiredArgsConstructor
@Service
public class BizVerdictRuleValueServiceImpl implements IBizVerdictRuleValueService {

    private final BizVerdictRuleValueMapper baseMapper;

    /**
     * 查询裁决规则标准值表
     */
    @Override
    public BizVerdictRuleValueVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询裁决规则标准值表列表
     */
    @Override
    public TableDataInfo<BizVerdictRuleValueVo> queryPageList(BizVerdictRuleValueBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizVerdictRuleValue> lqw = buildQueryWrapper(bo);
        Page<BizVerdictRuleValueVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询裁决规则标准值表列表
     */
    @Override
    public List<BizVerdictRuleValueVo> queryList(BizVerdictRuleValueBo bo) {
        LambdaQueryWrapper<BizVerdictRuleValue> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizVerdictRuleValue> buildQueryWrapper(BizVerdictRuleValueBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizVerdictRuleValue> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getVerdictTypeId() != null, BizVerdictRuleValue::getVerdictTypeId, bo.getVerdictTypeId());
        lqw.like(StringUtils.isNotBlank(bo.getVerdictTypeName()), BizVerdictRuleValue::getVerdictTypeName, bo.getVerdictTypeName());
        lqw.eq(bo.getVerdictRuleId() != null, BizVerdictRuleValue::getVerdictRuleId, bo.getVerdictRuleId());
        lqw.like(StringUtils.isNotBlank(bo.getVerdictParamName()), BizVerdictRuleValue::getVerdictParamName, bo.getVerdictParamName());
        lqw.eq(StringUtils.isNotBlank(bo.getVerdicRuleDimensionValue()), BizVerdictRuleValue::getVerdicRuleDimensionValue, bo.getVerdicRuleDimensionValue());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizVerdictRuleValue::getExt, bo.getExt());
        return lqw;
    }

    /**
     * 新增裁决规则标准值表
     */
    @Override
    public Boolean insertByBo(BizVerdictRuleValueBo bo) {
        BizVerdictRuleValue add = BeanUtil.toBean(bo, BizVerdictRuleValue.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改裁决规则标准值表
     */
    @Override
    public Boolean updateByBo(BizVerdictRuleValueBo bo) {
        BizVerdictRuleValue update = BeanUtil.toBean(bo, BizVerdictRuleValue.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizVerdictRuleValue entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除裁决规则标准值表
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
