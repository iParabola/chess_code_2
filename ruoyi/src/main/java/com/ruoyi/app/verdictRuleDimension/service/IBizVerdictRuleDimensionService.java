package com.ruoyi.app.verdictRuleDimension.service;

import com.ruoyi.app.verdictRuleDimension.domain.BizVerdictRuleDimension;
import com.ruoyi.app.verdictRuleDimension.domain.vo.BizVerdictRuleDimensionVo;
import com.ruoyi.app.verdictRuleDimension.domain.bo.BizVerdictRuleDimensionBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 裁决规则维度定义表Service接口
 *
 * @author ccc
 * @date 2024-09-13
 */
public interface IBizVerdictRuleDimensionService {

    /**
     * 查询裁决规则维度定义表
     */
    BizVerdictRuleDimensionVo queryById(Long id);

    /**
     * 查询裁决规则维度定义表列表
     */
    TableDataInfo<BizVerdictRuleDimensionVo> queryPageList(BizVerdictRuleDimensionBo bo, PageQuery pageQuery);

    /**
     * 查询裁决规则维度定义表列表
     */
    List<BizVerdictRuleDimensionVo> queryList(BizVerdictRuleDimensionBo bo);

    /**
     * 新增裁决规则维度定义表
     */
    Boolean insertByBo(BizVerdictRuleDimensionBo bo);

    /**
     * 修改裁决规则维度定义表
     */
    Boolean updateByBo(BizVerdictRuleDimensionBo bo);

    /**
     * 校验并批量删除裁决规则维度定义表信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
