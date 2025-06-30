package com.ruoyi.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.business.domain.BizArbiterMap;
import com.ruoyi.business.domain.BizArbiterMapCoordinate;
import com.ruoyi.business.domain.bo.BizArbiterMapBo;
import com.ruoyi.business.domain.bo.BizArbiterMapCoordinateBo;
import com.ruoyi.business.domain.dto.BatchSaveArbiterMapCoordinateDto;
import com.ruoyi.business.domain.vo.BizArbiterMapCoordinateVo;
import com.ruoyi.business.domain.vo.BizArbiterMapVo;
import com.ruoyi.business.mapper.BizArbiterMapCoordinateMapper;
import com.ruoyi.business.mapper.BizArbiterMapLegendMapper;
import com.ruoyi.business.mapper.BizArbiterMapMapper;
import com.ruoyi.business.service.IBizArbiterMapService;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 地图管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class BizArbiterMapServiceImpl implements IBizArbiterMapService {

    private final BizArbiterMapMapper baseMapper;

    private final BizArbiterMapLegendMapper arbiterMapLegendMapper;
    private final BizArbiterMapCoordinateMapper arbiterMapCoordinateMapper;

    /**
     * 查询地图管理
     */
    @Override
    public BizArbiterMapVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询地图管理列表
     */
    @Override
    public TableDataInfo<BizArbiterMapVo> queryPageList(BizArbiterMapBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizArbiterMap> lqw = buildQueryWrapper(bo);
        Page<BizArbiterMapVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询地图管理列表
     */
    @Override
    public List<BizArbiterMapVo> queryList(BizArbiterMapBo bo) {
        LambdaQueryWrapper<BizArbiterMap> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizArbiterMap> buildQueryWrapper(BizArbiterMapBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizArbiterMap> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getMapName()), BizArbiterMap::getMapName, bo.getMapName());
        return lqw;
    }

    /**
     * 新增地图管理
     */
    @Override
    public Boolean insertByBo(BizArbiterMapBo bo) {
        BizArbiterMap add = BeanUtil.toBean(bo, BizArbiterMap.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改地图管理
     */
    @Override
    public Boolean updateByBo(BizArbiterMapBo bo) {
        BizArbiterMap update = BeanUtil.toBean(bo, BizArbiterMap.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizArbiterMap entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除地图管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public String batchSaveArbiterMapCoordinate(BatchSaveArbiterMapCoordinateDto batchSaveArbiterMapCoordinateDto) {
        log.info("batchSaveArbiterMapCoordinate req:{}", JSON.toJSONString(batchSaveArbiterMapCoordinateDto));
        if (CollectionUtils.isEmpty(batchSaveArbiterMapCoordinateDto.getDetailList())) {
            log.error("地图坐标为空");
            throw new ServiceException("地图坐标为空");
        }
        BizArbiterMap arbiterMap = baseMapper.selectById(Long.valueOf(batchSaveArbiterMapCoordinateDto.getMapId()));
        if (ObjectUtils.isEmpty(arbiterMap)) {
            log.error("批量新增地图坐标,地图不存在,req:{}", JSON.toJSONString(batchSaveArbiterMapCoordinateDto));
            throw new ServiceException("地图不存在");
        }
        log.info("1");
        //校验坐标地形
//        List<String> terrainIdStrList = new ArrayList<>();
//        for (int i = 0; i < batchSaveArbiterMapCoordinateDto.getDetailList().size(); i++) {
//
//            BizArbiterMapCoordinateBo detail = batchSaveArbiterMapCoordinateDto.getDetailList().get(i);
//            List<String> terrainIds = Arrays.asList(detail.getArbiterMapLegendIds().split(ArbiterConstant.ENGLISH_COMMA));
//            if (CollectionUtils.isEmpty(terrainIds)) {
//                log.error("坐标地形不能为空,detail:{}", JSON.toJSONString(detail));
//                throw new ServiceException("坐标地形不能为空");
//            }
//            terrainIdStrList.addAll(terrainIds);
//        }
//        log.info("2");
//        terrainIdStrList = terrainIdStrList.stream().distinct().collect(Collectors.toList());
//        List<Long> terrainIdList = terrainIdStrList.stream().map(Long::valueOf).collect(Collectors.toList());
//        log.info("3");
//        List<BizArbiterMapLegend> terrainList = arbiterMapLegendMapper.selectBatchIds(terrainIdList);
//        if (terrainIdList.size() != terrainList.size()) {
//            log.error("地形不存在");
//            throw new ServiceException("地形不存在");
//        }
        //校验横坐标最大值、纵坐标最大值
        //横坐标最大值
        long abscissaMax = arbiterMap.getTransverseDimension() + Integer.parseInt(arbiterMap.getInitialAbscissa()) - 1;
        log.info(abscissaMax + "");
        //纵坐标最大值
        long ordinateMax = arbiterMap.getLongitudinalDimension() + Integer.parseInt(arbiterMap.getInitialOrdinate()) - 1;
        log.info(ordinateMax + "");
        List<BizArbiterMapCoordinateBo> validDetailList = batchSaveArbiterMapCoordinateDto.getDetailList().stream().filter(detail -> Integer.parseInt(detail.getCoordinate().substring(0, 2)) <= ordinateMax && Integer.parseInt(detail.getCoordinate().substring(2)) <= abscissaMax).collect(Collectors.toList());
        arbiterMapCoordinateMapper.delete(new QueryWrapper<BizArbiterMapCoordinate>().lambda().eq(BizArbiterMapCoordinate::getArbiterMapId, batchSaveArbiterMapCoordinateDto.getMapId()));
        //已存在坐标
//        List<BizArbiterMapCoordinate> existCoordinateList = arbiterMapCoordinateMapper.selectList(new QueryWrapper<BizArbiterMapCoordinate>().lambda().eq(BizArbiterMapCoordinate::getArbiterMapId, Long.valueOf(batchSaveArbiterMapCoordinateDto.getMapId())).in(BizArbiterMapCoordinate::getCoordinate, validDetailList.stream().map(BizArbiterMapCoordinateBo::getCoordinate).collect(Collectors.toList())));
//        List<String> existCoordinates = existCoordinateList.stream().map(BizArbiterMapCoordinate::getCoordinate).collect(Collectors.toList());

        List<BizArbiterMapCoordinate> coordinateList = new ArrayList<>();
        for (int i = 0; i < validDetailList.size(); i++) {
            BizArbiterMapCoordinateBo detail = validDetailList.get(i);
            BizArbiterMapCoordinate mapCoordinate = new BizArbiterMapCoordinate();
//            if (existCoordinates.contains(detail.getCoordinate())) {
//                BizArbiterMapCoordinate arbiterMapCoordinate = existCoordinateList.stream().filter(e -> e.getCoordinate().equals(detail.getCoordinate())).collect(Collectors.toList()).get(0);
//                mapCoordinate.setId(arbiterMapCoordinate.getId());
//            }
//            } else {
//                mapCoordinate.setId(idWorker.nextId());
//            }
            mapCoordinate.setArbiterMapId(Long.valueOf(batchSaveArbiterMapCoordinateDto.getMapId()));
            mapCoordinate.setArbiterMapName(arbiterMap.getMapName());
            mapCoordinate.setCoordinate(detail.getCoordinate());
            mapCoordinate.setAbscissa(detail.getCoordinate().substring(0, 2));
            mapCoordinate.setOrdinate(detail.getCoordinate().substring(2));
            mapCoordinate.setElevation(detail.getElevation());
            mapCoordinate.setElevationColor(detail.getElevationColor());
            mapCoordinate.setTerrainColor(detail.getTerrainColor());
            coordinateList.add(mapCoordinate);
        }
        arbiterMapCoordinateMapper.insertBatch(coordinateList);
        return "";
    }


    @Override
    public void exportOffsetAndCoordinate(HttpServletResponse response) {
        List<BizArbiterMapCoordinate> arbiterMapCoordinates = new ArrayList<>();
        for(int i = 49;i>=-49;i--){
            for(int j = -65;j<=64;j++){
                BizArbiterMapCoordinate arbiterMapCoordinate = new BizArbiterMapCoordinate();
                int rowNumber = 0;
                String rowNumberStr = "";
                rowNumber = 49 - i + 25;
                if (rowNumber < 10) {
                    rowNumberStr = "00" + rowNumber;
                } else if (rowNumber < 100) {
                    rowNumberStr = "0" + rowNumber;
                } else {
                    rowNumberStr = rowNumber+"";
                }
                int colNumber = 0;
                String colNumberStr = "";
                if (i % 2 == 0) {
                    colNumber = j + 65;
                } else {
                    colNumber = j + 66;
                }

                if (colNumber < 10) {
                    colNumberStr = "00" + colNumber;
                } else if (colNumber < 100) {
                    colNumberStr = "0" + colNumber;
                } else {
                    colNumberStr = colNumber+"";
                }
                System.out.println(rowNumberStr+colNumberStr);

                arbiterMapCoordinate.setOffset(j+","+i);
                arbiterMapCoordinate.setCoordinate(rowNumberStr+colNumberStr);
                arbiterMapCoordinates.add(arbiterMapCoordinate);
            }
        }
        arbiterMapCoordinateMapper.insertBatch(arbiterMapCoordinates);
    }


    @Override
    public Map<String, BizArbiterMapCoordinateVo> getCoordinateMap(Long mapId) {


        List<BizArbiterMapCoordinateVo> coordinateVos = arbiterMapCoordinateMapper.getMapCoordinate(mapId);
        Map<String, BizArbiterMapCoordinateVo> map = coordinateVos.stream()
            .filter(entity -> entity.getOffset() != null)
            .collect(Collectors.toMap(BizArbiterMapCoordinateVo::getOffset, Function.identity(), (existing, replacement) -> existing));
        return map;
    }

    @Override
    public List<BizArbiterMapVo> queryMapByProductId(Long productId) {
        LambdaQueryWrapper<BizArbiterMap> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BizArbiterMap::getProductId, productId);
        List<BizArbiterMap> maps = baseMapper.selectList(queryWrapper);
        return maps.stream().map(map -> {
            BizArbiterMapVo dto = new BizArbiterMapVo();
            dto.setId(map.getId());
            dto.setMapName(map.getMapName());
            return dto;
        }).collect(Collectors.toList());
    }
}
