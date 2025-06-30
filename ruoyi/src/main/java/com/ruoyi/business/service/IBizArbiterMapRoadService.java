package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizArbiterMapRoad;
import com.ruoyi.business.domain.vo.BizArbiterMapRoadVo;
import com.ruoyi.business.domain.bo.BizArbiterMapRoadBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 地图路Service接口
 *
 * @author ruoyi
 * @date 2024-03-08
 */
public interface IBizArbiterMapRoadService {

    /**
     * 查询地图路
     */
    BizArbiterMapRoadVo queryById(Long id);

    /**
     * 查询地图路列表
     */
    TableDataInfo<BizArbiterMapRoadVo> queryPageList(BizArbiterMapRoadBo bo, PageQuery pageQuery);

    /**
     * 查询地图路列表
     */
    List<BizArbiterMapRoadVo> queryList(BizArbiterMapRoadBo bo);

    /**
     * 新增地图路
     */
    Boolean insertByBo(BizArbiterMapRoadBo bo);

    /**
     * 修改地图路
     */
    Boolean updateByBo(BizArbiterMapRoadBo bo);

    /**
     * 校验并批量删除地图路信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
