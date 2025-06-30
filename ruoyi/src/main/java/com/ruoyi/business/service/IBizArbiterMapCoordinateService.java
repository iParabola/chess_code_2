package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizArbiterMapCoordinate;
import com.ruoyi.business.domain.dto.MapImportDto;
import com.ruoyi.business.domain.vo.BizArbiterMapCoordinateVo;
import com.ruoyi.business.domain.bo.BizArbiterMapCoordinateBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 地图坐标Service接口
 *
 * @author ruoyi
 * @date 2024-02-20
 */
public interface IBizArbiterMapCoordinateService {

    /**
     * 查询地图坐标
     */
    BizArbiterMapCoordinateVo queryById(Long id);

    /**
     * 查询地图坐标列表
     */
    TableDataInfo<BizArbiterMapCoordinateVo> queryPageList(BizArbiterMapCoordinateBo bo, PageQuery pageQuery);

    /**
     * 查询地图坐标列表
     */
    List<BizArbiterMapCoordinateVo> queryList(BizArbiterMapCoordinateBo bo);

    /**
     * 新增地图坐标
     */
    Boolean insertByBo(BizArbiterMapCoordinateBo bo);

    /**
     * 修改地图坐标
     */
    Boolean updateByBo(BizArbiterMapCoordinateBo bo);

    /**
     * 校验并批量删除地图坐标信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);


    List<BizArbiterMapCoordinate> queryByMapId(Long arbiterMapId);

    void importData(List<MapImportDto> result,Long mapId);
}
