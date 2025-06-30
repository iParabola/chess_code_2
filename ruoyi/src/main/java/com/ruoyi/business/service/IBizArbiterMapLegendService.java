package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizArbiterMapLegend;
import com.ruoyi.business.domain.vo.BizArbiterMapLegendVo;
import com.ruoyi.business.domain.bo.BizArbiterMapLegendBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 地图图例Service接口
 *
 * @author ruoyi
 * @date 2024-03-08
 */
public interface IBizArbiterMapLegendService {

    /**
     * 查询地图图例
     */
    BizArbiterMapLegendVo queryById(Long id);

    /**
     * 查询地图图例列表
     */
    TableDataInfo<BizArbiterMapLegendVo> queryPageList(BizArbiterMapLegendBo bo, PageQuery pageQuery);

    /**
     * 查询地图图例列表
     */
    List<BizArbiterMapLegendVo> queryList(BizArbiterMapLegendBo bo);

    /**
     * 新增地图图例
     */
    Boolean insertByBo(BizArbiterMapLegendBo bo);

    /**
     * 修改地图图例
     */
    Boolean updateByBo(BizArbiterMapLegendBo bo);

    /**
     * 校验并批量删除地图图例信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
