package com.ruoyi.app.verdictRuleJudgeCriteria.service;

import com.ruoyi.app.verdictRuleJudgeCriteria.domain.BizVerdictRuleJudgeCriteria;
import com.ruoyi.app.verdictRuleJudgeCriteria.domain.vo.BizVerdictRuleJudgeCriteriaVo;
import com.ruoyi.app.verdictRuleJudgeCriteria.domain.bo.BizVerdictRuleJudgeCriteriaBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 裁决判断前置条件Service接口
 *
 * @author ccc
 * @date 2024-09-18
 */
public interface IBizVerdictRuleJudgeCriteriaService {

    /**
     * 查询裁决判断前置条件
     */
    BizVerdictRuleJudgeCriteriaVo queryById(Long id);

    /***
     * 根据裁决类型获取前置条件
     */
    BizVerdictRuleJudgeCriteriaVo queryByVerdictTypeId(Long verdictTypeId);

    /**
     * 查询裁决判断前置条件列表
     */
    TableDataInfo<BizVerdictRuleJudgeCriteriaVo> queryPageList(BizVerdictRuleJudgeCriteriaBo bo, PageQuery pageQuery);

    /**
     * 查询裁决判断前置条件列表
     */
    List<BizVerdictRuleJudgeCriteriaVo> queryList(BizVerdictRuleJudgeCriteriaBo bo);

    /**
     * 新增裁决判断前置条件
     */
    Boolean insertByBo(BizVerdictRuleJudgeCriteriaBo bo);

    /**
     * 修改裁决判断前置条件
     */
    Boolean updateByBo(BizVerdictRuleJudgeCriteriaBo bo);

    /**
     * 校验并批量删除裁决判断前置条件信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
