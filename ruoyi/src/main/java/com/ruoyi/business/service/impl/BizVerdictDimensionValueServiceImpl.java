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
import com.ruoyi.business.domain.bo.BizVerdictDimensionValueBo;
import com.ruoyi.business.domain.vo.BizVerdictDimensionValueVo;
import com.ruoyi.business.domain.BizVerdictDimensionValue;
import com.ruoyi.business.mapper.BizVerdictDimensionValueMapper;
import com.ruoyi.business.service.IBizVerdictDimensionValueService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 裁决维度定义值域Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-09
 */
@RequiredArgsConstructor
@Service
public class BizVerdictDimensionValueServiceImpl implements IBizVerdictDimensionValueService {

    private final BizVerdictDimensionValueMapper baseMapper;

    /**
     * 查询裁决维度定义值域
     */
    @Override
    public BizVerdictDimensionValueVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询裁决维度定义值域列表
     */
    @Override
    public TableDataInfo<BizVerdictDimensionValueVo> queryPageList(BizVerdictDimensionValueBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizVerdictDimensionValue> lqw = buildQueryWrapper(bo);
        Page<BizVerdictDimensionValueVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询裁决维度定义值域列表
     */
    @Override
    public List<BizVerdictDimensionValueVo> queryList(BizVerdictDimensionValueBo bo) {
        LambdaQueryWrapper<BizVerdictDimensionValue> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizVerdictDimensionValue> buildQueryWrapper(BizVerdictDimensionValueBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizVerdictDimensionValue> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getDimensionId().toString()),BizVerdictDimensionValue::getDimensionId,bo.getDimensionId());
//        lqw.like(StringUtils.isNotBlank(bo.getProductName()), BizChessPiecesAttribute::getProductName, bo.getProductName())
        return lqw;
    }

    /**
     * 新增裁决维度定义值域
     */
    @Override
    public Boolean insertByBo(BizVerdictDimensionValueBo bo) {
        BizVerdictDimensionValue add = BeanUtil.toBean(bo, BizVerdictDimensionValue.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改裁决维度定义值域
     */
    @Override
    public Boolean updateByBo(BizVerdictDimensionValueBo bo) {
        BizVerdictDimensionValue update = BeanUtil.toBean(bo, BizVerdictDimensionValue.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizVerdictDimensionValue entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除裁决维度定义值域
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
