package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizVerdictDimensionValue;
import com.ruoyi.business.domain.vo.BizVerdictDimensionValueVo;
import com.ruoyi.business.domain.bo.BizVerdictDimensionValueBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 裁决维度定义值域Service接口
 *
 * @author ruoyi
 * @date 2024-09-09
 */
public interface IBizVerdictDimensionValueService {

    /**
     * 查询裁决维度定义值域
     */
    BizVerdictDimensionValueVo queryById(Long id);

    /**
     * 查询裁决维度定义值域列表
     */
    TableDataInfo<BizVerdictDimensionValueVo> queryPageList(BizVerdictDimensionValueBo bo, PageQuery pageQuery);

    /**
     * 查询裁决维度定义值域列表
     */
    List<BizVerdictDimensionValueVo> queryList(BizVerdictDimensionValueBo bo);

    /**
     * 新增裁决维度定义值域
     */
    Boolean insertByBo(BizVerdictDimensionValueBo bo);

    /**
     * 修改裁决维度定义值域
     */
    Boolean updateByBo(BizVerdictDimensionValueBo bo);

    /**
     * 校验并批量删除裁决维度定义值域信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
