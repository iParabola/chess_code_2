package com.ruoyi.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.business.domain.BizArbiterMapCoordinate;
import com.ruoyi.business.domain.BizArbiterMapLegend;
import com.ruoyi.business.domain.BizArbiterMapRoad;
import com.ruoyi.business.domain.BizVerdictRecordHistory;
import com.ruoyi.business.domain.bo.BizArbiterMapCoordinateBo;
import com.ruoyi.business.domain.dto.MapImportDto;
import com.ruoyi.business.domain.vo.BizArbiterMapCoordinateVo;
import com.ruoyi.business.domain.vo.BizArbiterMapLegendVo;
import com.ruoyi.business.domain.vo.BizArbiterMapRoadVo;
import com.ruoyi.business.mapper.BizArbiterMapCoordinateMapper;
import com.ruoyi.business.mapper.BizArbiterMapLegendMapper;
import com.ruoyi.business.mapper.BizArbiterMapRoadMapper;
import com.ruoyi.business.service.IBizArbiterMapCoordinateService;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 地图坐标Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@RequiredArgsConstructor
@Service
public class BizArbiterMapCoordinateServiceImpl implements IBizArbiterMapCoordinateService {

    private final BizArbiterMapCoordinateMapper baseMapper;

    private final BizArbiterMapLegendMapper legendMapper;

    private final BizArbiterMapRoadMapper roadMapper;

    /**
     * 查询地图坐标
     */
    @Override
    public BizArbiterMapCoordinateVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询地图坐标列表
     */
    @Override
    public TableDataInfo<BizArbiterMapCoordinateVo> queryPageList(BizArbiterMapCoordinateBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizArbiterMapCoordinate> lqw = buildQueryWrapper(bo);
        Page<BizArbiterMapCoordinateVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询地图坐标列表
     */
    @Override
    public List<BizArbiterMapCoordinateVo> queryList(BizArbiterMapCoordinateBo bo) {
        LambdaQueryWrapper<BizArbiterMapCoordinate> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizArbiterMapCoordinate> buildQueryWrapper(BizArbiterMapCoordinateBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizArbiterMapCoordinate> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getArbiterMapId() != null, BizArbiterMapCoordinate::getArbiterMapId, bo.getArbiterMapId());
        lqw.like(StringUtils.isNotBlank(bo.getArbiterMapName()), BizArbiterMapCoordinate::getArbiterMapName, bo.getArbiterMapName());
        lqw.eq(StringUtils.isNotBlank(bo.getCoordinate()), BizArbiterMapCoordinate::getCoordinate, bo.getCoordinate());
        lqw.eq(StringUtils.isNotBlank(bo.getAbscissa()), BizArbiterMapCoordinate::getAbscissa, bo.getAbscissa());
        lqw.eq(StringUtils.isNotBlank(bo.getOrdinate()), BizArbiterMapCoordinate::getOrdinate, bo.getOrdinate());
        lqw.eq(bo.getElevation() != null, BizArbiterMapCoordinate::getElevation, bo.getElevation());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizArbiterMapCoordinate::getExt, bo.getExt());
        return lqw;
    }

    /**
     * 新增地图坐标
     */
    @Override
    public Boolean insertByBo(BizArbiterMapCoordinateBo bo) {
        BizArbiterMapCoordinate add = BeanUtil.toBean(bo, BizArbiterMapCoordinate.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改地图坐标
     */
    @Override
    public Boolean updateByBo(BizArbiterMapCoordinateBo bo) {
        BizArbiterMapCoordinate update = BeanUtil.toBean(bo, BizArbiterMapCoordinate.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizArbiterMapCoordinate entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除地图坐标
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public List<BizArbiterMapCoordinate> queryByMapId(Long arbiterMapId) {
        return baseMapper.selectList(new LambdaQueryWrapper<BizArbiterMapCoordinate>().eq(BizArbiterMapCoordinate::getArbiterMapId, arbiterMapId));

    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void importData(List<MapImportDto> result,Long mapId) {
        List<BizArbiterMapCoordinate> coordinateVos = new ArrayList<>();

        //获取map下所有的地形配置
        LambdaQueryWrapper<BizArbiterMapLegend> lqw = Wrappers.lambdaQuery();
        lqw.eq(BizArbiterMapLegend::getMapId,mapId);
        List<BizArbiterMapLegendVo> legendVos = legendMapper.selectVoList(lqw);
        Map<String, List<BizArbiterMapLegendVo>> legendMap = legendVos.stream().collect(Collectors.groupingBy(BizArbiterMapLegendVo::getName));
        //获取map下的道路配置
        LambdaQueryWrapper<BizArbiterMapRoad> roadLqw = Wrappers.lambdaQuery();
        roadLqw.eq(BizArbiterMapRoad::getArbiterMapId,mapId);
        List<BizArbiterMapRoadVo> roadVos = roadMapper.selectVoList(roadLqw);
        Map<String, List<BizArbiterMapRoadVo>> roadMap = roadVos.stream().collect(Collectors.groupingBy(BizArbiterMapRoadVo::getName));

        for(MapImportDto importDto : result){
            BizArbiterMapCoordinate coordinate = new BizArbiterMapCoordinate();
            coordinate.setArbiterMapId(mapId);
            coordinate.setCoordinate(importDto.getCoordinate());
            coordinate.setOffset(importDto.getOffset());
            coordinate.setElevation(importDto.getElevation());
            coordinate.setElevationColor(formatterElevationColor(importDto.getElevation()));
            coordinate.setTerrainActionValue(BigDecimal.ONE);
            coordinate.setTerrainVisibleRange(BigDecimal.ONE);
            List<BizArbiterMapLegendVo> legends = legendMap.get(importDto.getTerrainName());
            if(ObjectUtil.isNotEmpty(legends)){
                coordinate.setTerrainId(legends.get(0).getId());
                coordinate.setTerrainColor(legends.get(0).getColor());
                coordinate.setTerrainCover(legends.get(0).getCover());
                coordinate.setTerrainActionValue(coordinate.getTerrainActionValue().add(legends.get(0).getTerrainActionValue()));
                coordinate.setTerrainVisibleRange(coordinate.getTerrainVisibleRange().multiply(legends.get(0).getTerrainVisibleRange()));
            }
            if(ObjectUtil.isNull(importDto.getBridgeFlag())){
                coordinate.setBridgeFlag("0");
            }else{
                coordinate.setBridgeFlag(importDto.getBridgeFlag().equals("是")?"1":"0");
            }

            List<BizArbiterMapRoadVo> roads = roadMap.get(importDto.getRoadTypeName());
            if(ObjectUtil.isNotEmpty(roads)){
                coordinate.setRoadTypeId(roads.get(0).getId());
                coordinate.setRoadColor(roads.get(0).getColor());
                coordinate.setRoadWidth(roads.get(0).getRoadWidth());
            }
            coordinate.setToRoadNumber(importDto.getToRoadNumber());
            coordinate.setRoadName(importDto.getRoadName());
            coordinateVos.add(coordinate);

        }
        LambdaQueryWrapper<BizArbiterMapCoordinate> coordinateLambdaQueryWrapper = Wrappers.lambdaQuery();
        coordinateLambdaQueryWrapper.eq(BizArbiterMapCoordinate::getArbiterMapId,mapId);
        baseMapper.delete(coordinateLambdaQueryWrapper);
        baseMapper.insertBatch(coordinateVos);
    }


    private String formatterElevationColor(Long elevation){
        if(ObjectUtil.isNull(elevation)){
            return "";
        }
        if(elevation <= 60){
            return "#F5DEB3";
        }else if(elevation <= 90){
            return "#F4A460";
        }else if(elevation <= 140){
            return "#E9967A";
        }else if(elevation <= 170){
            return "#CD853F";
        }else if(elevation <= 210){
            return "#D2691E";
        }else if(elevation <= 250){
            return "#B8860B";
        }else if(elevation <= 290){
            return "#A0522D";
        }else if(elevation <= 340){
            return "#8B4513";
        }else if(elevation <= 400){
            return "#8B0000";
        }
        return "";
    }
}
