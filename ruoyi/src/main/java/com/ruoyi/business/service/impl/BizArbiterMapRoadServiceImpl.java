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
import com.ruoyi.business.domain.bo.BizArbiterMapRoadBo;
import com.ruoyi.business.domain.vo.BizArbiterMapRoadVo;
import com.ruoyi.business.domain.BizArbiterMapRoad;
import com.ruoyi.business.mapper.BizArbiterMapRoadMapper;
import com.ruoyi.business.service.IBizArbiterMapRoadService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 地图路Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@RequiredArgsConstructor
@Service
public class BizArbiterMapRoadServiceImpl implements IBizArbiterMapRoadService {

    private final BizArbiterMapRoadMapper baseMapper;

    /**
     * 查询地图路
     */
    @Override
    public BizArbiterMapRoadVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询地图路列表
     */
    @Override
    public TableDataInfo<BizArbiterMapRoadVo> queryPageList(BizArbiterMapRoadBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizArbiterMapRoad> lqw = buildQueryWrapper(bo);
        Page<BizArbiterMapRoadVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询地图路列表
     */
    @Override
    public List<BizArbiterMapRoadVo> queryList(BizArbiterMapRoadBo bo) {
        LambdaQueryWrapper<BizArbiterMapRoad> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizArbiterMapRoad> buildQueryWrapper(BizArbiterMapRoadBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizArbiterMapRoad> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getArbiterMapId() != null, BizArbiterMapRoad::getArbiterMapId, bo.getArbiterMapId());
        lqw.like(StringUtils.isNotBlank(bo.getName()), BizArbiterMapRoad::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getColor()), BizArbiterMapRoad::getColor, bo.getColor());
        lqw.eq(StringUtils.isNotBlank(bo.getCover()), BizArbiterMapRoad::getCover, bo.getCover());
        lqw.eq(bo.getTerrainActionValue() != null, BizArbiterMapRoad::getTerrainActionValue, bo.getTerrainActionValue());
        lqw.eq(bo.getTerrainVisibleRange() != null, BizArbiterMapRoad::getTerrainVisibleRange, bo.getTerrainVisibleRange());
        lqw.eq(StringUtils.isNotBlank(bo.getCentersCoordinat()), BizArbiterMapRoad::getCentersCoordinat, bo.getCentersCoordinat());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizArbiterMapRoad::getExt, bo.getExt());
        return lqw;
    }

    /**
     * 新增地图路
     */
    @Override
    public Boolean insertByBo(BizArbiterMapRoadBo bo) {
        BizArbiterMapRoad add = BeanUtil.toBean(bo, BizArbiterMapRoad.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改地图路
     */
    @Override
    public Boolean updateByBo(BizArbiterMapRoadBo bo) {
        BizArbiterMapRoad update = BeanUtil.toBean(bo, BizArbiterMapRoad.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizArbiterMapRoad entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除地图路
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
