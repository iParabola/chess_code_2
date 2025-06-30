package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizArbiterMapRiver;
import com.ruoyi.business.domain.vo.BizArbiterMapRiverVo;
import com.ruoyi.business.domain.bo.BizArbiterMapRiverBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 地图河Service接口
 *
 * @author ruoyi
 * @date 2024-03-08
 */
public interface IBizArbiterMapRiverService {

    /**
     * 查询地图河
     */
    BizArbiterMapRiverVo queryById(Long id);

    /**
     * 查询地图河列表
     */
    TableDataInfo<BizArbiterMapRiverVo> queryPageList(BizArbiterMapRiverBo bo, PageQuery pageQuery);

    /**
     * 查询地图河列表
     */
    List<BizArbiterMapRiverVo> queryList(BizArbiterMapRiverBo bo);

    /**
     * 新增地图河
     */
    Boolean insertByBo(BizArbiterMapRiverBo bo);

    /**
     * 修改地图河
     */
    Boolean updateByBo(BizArbiterMapRiverBo bo);

    /**
     * 校验并批量删除地图河信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
