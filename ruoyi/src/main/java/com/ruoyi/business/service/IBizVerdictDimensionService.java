package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizVerdictDimension;
import com.ruoyi.business.domain.vo.BizVerdictDimensionVo;
import com.ruoyi.business.domain.bo.BizVerdictDimensionBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 裁决维度定义Service接口
 *
 * @author ruoyi
 * @date 2024-09-09
 */
public interface IBizVerdictDimensionService {

    /**
     * 查询裁决维度定义
     */
    BizVerdictDimensionVo queryById(Long id);

    /**
     * 查询裁决维度定义列表
     */
    TableDataInfo<BizVerdictDimensionVo> queryPageList(BizVerdictDimensionBo bo, PageQuery pageQuery);

    /**
     * 查询裁决维度定义列表
     */
    List<BizVerdictDimensionVo> queryList(BizVerdictDimensionBo bo);

    /**
     * 新增裁决维度定义
     */
    Boolean insertByBo(BizVerdictDimensionBo bo);

    /**
     * 修改裁决维度定义
     */
    Boolean updateByBo(BizVerdictDimensionBo bo);

    /**
     * 校验并批量删除裁决维度定义信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
