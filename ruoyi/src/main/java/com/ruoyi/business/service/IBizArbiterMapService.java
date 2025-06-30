package com.ruoyi.business.service;

import com.ruoyi.business.domain.dto.BatchSaveArbiterMapCoordinateDto;
import com.ruoyi.business.domain.vo.BizArbiterMapCoordinateVo;
import com.ruoyi.business.domain.vo.BizArbiterMapVo;
import com.ruoyi.business.domain.bo.BizArbiterMapBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 地图管理Service接口
 *
 * @author ruoyi
 * @date 2024-02-19
 */
public interface IBizArbiterMapService {

    /**
     * 查询地图管理
     */
    BizArbiterMapVo queryById(Long id);

    /**
     * 查询地图管理列表
     */
    TableDataInfo<BizArbiterMapVo> queryPageList(BizArbiterMapBo bo, PageQuery pageQuery);

    /**
     * 查询地图管理列表
     */
    List<BizArbiterMapVo> queryList(BizArbiterMapBo bo);

    /**
     * 新增地图管理
     */
    Boolean insertByBo(BizArbiterMapBo bo);

    /**
     * 修改地图管理
     */
    Boolean updateByBo(BizArbiterMapBo bo);

    /**
     * 校验并批量删除地图管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);


    String batchSaveArbiterMapCoordinate(  BatchSaveArbiterMapCoordinateDto batchSaveArbiterMapCoordinateDto);


    void exportOffsetAndCoordinate( HttpServletResponse response);


    Map<String, BizArbiterMapCoordinateVo> getCoordinateMap(Long mapId);

    List<BizArbiterMapVo> queryMapByProductId(Long productId);
}
