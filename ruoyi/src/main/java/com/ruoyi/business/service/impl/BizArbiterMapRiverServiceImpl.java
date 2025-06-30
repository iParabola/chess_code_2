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
import com.ruoyi.business.domain.bo.BizArbiterMapRiverBo;
import com.ruoyi.business.domain.vo.BizArbiterMapRiverVo;
import com.ruoyi.business.domain.BizArbiterMapRiver;
import com.ruoyi.business.mapper.BizArbiterMapRiverMapper;
import com.ruoyi.business.service.IBizArbiterMapRiverService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 地图河Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@RequiredArgsConstructor
@Service
public class BizArbiterMapRiverServiceImpl implements IBizArbiterMapRiverService {

    private final BizArbiterMapRiverMapper baseMapper;

    /**
     * 查询地图河
     */
    @Override
    public BizArbiterMapRiverVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询地图河列表
     */
    @Override
    public TableDataInfo<BizArbiterMapRiverVo> queryPageList(BizArbiterMapRiverBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizArbiterMapRiver> lqw = buildQueryWrapper(bo);
        Page<BizArbiterMapRiverVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询地图河列表
     */
    @Override
    public List<BizArbiterMapRiverVo> queryList(BizArbiterMapRiverBo bo) {
        LambdaQueryWrapper<BizArbiterMapRiver> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizArbiterMapRiver> buildQueryWrapper(BizArbiterMapRiverBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizArbiterMapRiver> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getArbiterMapId() != null, BizArbiterMapRiver::getArbiterMapId, bo.getArbiterMapId());
        lqw.like(StringUtils.isNotBlank(bo.getName()), BizArbiterMapRiver::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getColor()), BizArbiterMapRiver::getColor, bo.getColor());
        lqw.eq(StringUtils.isNotBlank(bo.getCover()), BizArbiterMapRiver::getCover, bo.getCover());
        lqw.eq(StringUtils.isNotBlank(bo.getHexsCoordinat()), BizArbiterMapRiver::getHexsCoordinat, bo.getHexsCoordinat());
        lqw.eq(StringUtils.isNotBlank(bo.getPointsCoordinate()), BizArbiterMapRiver::getPointsCoordinate, bo.getPointsCoordinate());
        lqw.eq(StringUtils.isNotBlank(bo.getBridgeFlag()), BizArbiterMapRiver::getBridgeFlag, bo.getBridgeFlag());
        lqw.eq(StringUtils.isNotBlank(bo.getRoadFlag()), BizArbiterMapRiver::getRoadFlag, bo.getRoadFlag());
        lqw.eq(bo.getTerrainActionValue() != null, BizArbiterMapRiver::getTerrainActionValue, bo.getTerrainActionValue());
        lqw.eq(bo.getTerrainVisibleRange() != null, BizArbiterMapRiver::getTerrainVisibleRange, bo.getTerrainVisibleRange());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizArbiterMapRiver::getExt, bo.getExt());
        return lqw;
    }

    /**
     * 新增地图河
     */
    @Override
    public Boolean insertByBo(BizArbiterMapRiverBo bo) {
        BizArbiterMapRiver add = BeanUtil.toBean(bo, BizArbiterMapRiver.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改地图河
     */
    @Override
    public Boolean updateByBo(BizArbiterMapRiverBo bo) {
        BizArbiterMapRiver update = BeanUtil.toBean(bo, BizArbiterMapRiver.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizArbiterMapRiver entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除地图河
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
