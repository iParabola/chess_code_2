package com.ruoyi.app.verdictRuleValue.service;

import com.ruoyi.app.verdictRuleValue.domain.BizVerdictRuleValue;
import com.ruoyi.app.verdictRuleValue.domain.vo.BizVerdictRuleValueVo;
import com.ruoyi.app.verdictRuleValue.domain.bo.BizVerdictRuleValueBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 裁决规则标准值表Service接口
 *
 * @author ccc
 * @date 2024-09-13
 */
public interface IBizVerdictRuleValueService {

    /**
     * 查询裁决规则标准值表
     */
    BizVerdictRuleValueVo queryById(Long id);

    /**
     * 查询裁决规则标准值表列表
     */
    TableDataInfo<BizVerdictRuleValueVo> queryPageList(BizVerdictRuleValueBo bo, PageQuery pageQuery);

    /**
     * 查询裁决规则标准值表列表
     */
    List<BizVerdictRuleValueVo> queryList(BizVerdictRuleValueBo bo);

    /**
     * 新增裁决规则标准值表
     */
    Boolean insertByBo(BizVerdictRuleValueBo bo);

    /**
     * 修改裁决规则标准值表
     */
    Boolean updateByBo(BizVerdictRuleValueBo bo);

    /**
     * 校验并批量删除裁决规则标准值表信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
