package com.ruoyi.app.verdictRuleDimension.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.app.verdictRuleDimension.domain.bo.BizVerdictRuleDimensionBo;
import com.ruoyi.app.verdictRuleDimension.domain.vo.BizVerdictRuleDimensionVo;
import com.ruoyi.app.verdictRuleDimension.domain.BizVerdictRuleDimension;
import com.ruoyi.app.verdictRuleDimension.mapper.BizVerdictRuleDimensionMapper;
import com.ruoyi.app.verdictRuleDimension.service.IBizVerdictRuleDimensionService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 裁决规则维度定义表Service业务层处理
 *
 * @author ccc
 * @date 2024-09-13
 */
@RequiredArgsConstructor
@Service
public class BizVerdictRuleDimensionServiceImpl implements IBizVerdictRuleDimensionService {

    private final BizVerdictRuleDimensionMapper baseMapper;

    /**
     * 查询裁决规则维度定义表
     */
    @Override
    public BizVerdictRuleDimensionVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询裁决规则维度定义表列表
     */
    @Override
    public TableDataInfo<BizVerdictRuleDimensionVo> queryPageList(BizVerdictRuleDimensionBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizVerdictRuleDimension> lqw = buildQueryWrapper(bo);
        Page<BizVerdictRuleDimensionVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询裁决规则维度定义表列表
     */
    @Override
    public List<BizVerdictRuleDimensionVo> queryList(BizVerdictRuleDimensionBo bo) {
        LambdaQueryWrapper<BizVerdictRuleDimension> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizVerdictRuleDimension> buildQueryWrapper(BizVerdictRuleDimensionBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizVerdictRuleDimension> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getVerdictTypeId() != null, BizVerdictRuleDimension::getVerdictTypeId, bo.getVerdictTypeId());
        lqw.like(StringUtils.isNotBlank(bo.getVerdictTypeName()), BizVerdictRuleDimension::getVerdictTypeName, bo.getVerdictTypeName());
        lqw.eq(bo.getVerdictRuleId() != null, BizVerdictRuleDimension::getVerdictRuleId, bo.getVerdictRuleId());
        lqw.like(StringUtils.isNotBlank(bo.getVerdictParamName()), BizVerdictRuleDimension::getVerdictParamName, bo.getVerdictParamName());
        lqw.like(StringUtils.isNotBlank(bo.getDimensionName()), BizVerdictRuleDimension::getDimensionName, bo.getDimensionName());
        lqw.eq(StringUtils.isNotBlank(bo.getDimensionCode()), BizVerdictRuleDimension::getDimensionCode, bo.getDimensionCode());
        lqw.eq(StringUtils.isNotBlank(bo.getDimensionDirect()), BizVerdictRuleDimension::getDimensionDirect, bo.getDimensionDirect());
        lqw.eq(StringUtils.isNotBlank(bo.getDimensionValue()), BizVerdictRuleDimension::getDimensionValue, bo.getDimensionValue());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizVerdictRuleDimension::getExt, bo.getExt());
        return lqw;
    }

    /**
     * 新增裁决规则维度定义表
     */
    @Override
    public Boolean insertByBo(BizVerdictRuleDimensionBo bo) {
        BizVerdictRuleDimension add = BeanUtil.toBean(bo, BizVerdictRuleDimension.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改裁决规则维度定义表
     */
    @Override
    public Boolean updateByBo(BizVerdictRuleDimensionBo bo) {
        BizVerdictRuleDimension update = BeanUtil.toBean(bo, BizVerdictRuleDimension.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizVerdictRuleDimension entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除裁决规则维度定义表
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
