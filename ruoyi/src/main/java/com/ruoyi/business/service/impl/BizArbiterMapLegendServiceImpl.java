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
import com.ruoyi.business.domain.bo.BizArbiterMapLegendBo;
import com.ruoyi.business.domain.vo.BizArbiterMapLegendVo;
import com.ruoyi.business.domain.BizArbiterMapLegend;
import com.ruoyi.business.mapper.BizArbiterMapLegendMapper;
import com.ruoyi.business.service.IBizArbiterMapLegendService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 地图图例Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@RequiredArgsConstructor
@Service
public class BizArbiterMapLegendServiceImpl implements IBizArbiterMapLegendService {

    private final BizArbiterMapLegendMapper baseMapper;

    /**
     * 查询地图图例
     */
    @Override
    public BizArbiterMapLegendVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询地图图例列表
     */
    @Override
    public TableDataInfo<BizArbiterMapLegendVo> queryPageList(BizArbiterMapLegendBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizArbiterMapLegend> lqw = buildQueryWrapper(bo);
        Page<BizArbiterMapLegendVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询地图图例列表
     */
    @Override
    public List<BizArbiterMapLegendVo> queryList(BizArbiterMapLegendBo bo) {
        LambdaQueryWrapper<BizArbiterMapLegend> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizArbiterMapLegend> buildQueryWrapper(BizArbiterMapLegendBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizArbiterMapLegend> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getMapId() != null, BizArbiterMapLegend::getMapId, bo.getMapId());
        lqw.like(StringUtils.isNotBlank(bo.getName()), BizArbiterMapLegend::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getColor()), BizArbiterMapLegend::getColor, bo.getColor());
        lqw.eq(StringUtils.isNotBlank(bo.getCover()), BizArbiterMapLegend::getCover, bo.getCover());
        lqw.eq(bo.getTerrainActionValue() != null, BizArbiterMapLegend::getTerrainActionValue, bo.getTerrainActionValue());
        lqw.eq(bo.getTerrainVisibleRange() != null, BizArbiterMapLegend::getTerrainVisibleRange, bo.getTerrainVisibleRange());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizArbiterMapLegend::getExt, bo.getExt());
        return lqw;
    }

    /**
     * 新增地图图例
     */
    @Override
    public Boolean insertByBo(BizArbiterMapLegendBo bo) {
        BizArbiterMapLegend add = BeanUtil.toBean(bo, BizArbiterMapLegend.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改地图图例
     */
    @Override
    public Boolean updateByBo(BizArbiterMapLegendBo bo) {
        BizArbiterMapLegend update = BeanUtil.toBean(bo, BizArbiterMapLegend.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizArbiterMapLegend entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除地图图例
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
