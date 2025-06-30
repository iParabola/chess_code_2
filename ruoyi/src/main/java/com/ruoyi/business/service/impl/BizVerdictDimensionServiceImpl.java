package com.ruoyi.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.business.domain.bo.BizVerdictDimensionBo;
import com.ruoyi.business.domain.vo.BizVerdictDimensionVo;
import com.ruoyi.business.domain.BizVerdictDimension;
import com.ruoyi.business.mapper.BizVerdictDimensionMapper;
import com.ruoyi.business.service.IBizVerdictDimensionService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 裁决维度定义Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-09
 */
@RequiredArgsConstructor
@Service
public class BizVerdictDimensionServiceImpl implements IBizVerdictDimensionService {

    private final BizVerdictDimensionMapper baseMapper;

    /**
     * 查询裁决维度定义
     */
    @Override
    public BizVerdictDimensionVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询裁决维度定义列表
     */
    @Override
    public TableDataInfo<BizVerdictDimensionVo> queryPageList(BizVerdictDimensionBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizVerdictDimension> lqw = buildQueryWrapper(bo);
        Page<BizVerdictDimensionVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询裁决维度定义列表
     */
    @Override
    public List<BizVerdictDimensionVo> queryList(BizVerdictDimensionBo bo) {
        LambdaQueryWrapper<BizVerdictDimension> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizVerdictDimension> buildQueryWrapper(BizVerdictDimensionBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizVerdictDimension> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), BizVerdictDimension::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getCode()), BizVerdictDimension::getCode, bo.getCode());
        lqw.eq(ObjectUtils.isNotNull(bo.getProductId()), BizVerdictDimension::getProductId, bo.getProductId());
        return lqw;
    }

    /**
     * 新增裁决维度定义
     */
    @Override
    public Boolean insertByBo(BizVerdictDimensionBo bo) {
        BizVerdictDimension add = BeanUtil.toBean(bo, BizVerdictDimension.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改裁决维度定义
     */
    @Override
    public Boolean updateByBo(BizVerdictDimensionBo bo) {
        BizVerdictDimension update = BeanUtil.toBean(bo, BizVerdictDimension.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizVerdictDimension entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除裁决维度定义
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
