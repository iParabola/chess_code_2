package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizVerdictRule;
import com.ruoyi.business.domain.vo.BizVerdictRuleVo;
import com.ruoyi.business.domain.bo.BizVerdictRuleBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

/**
 * 裁决表管理Service接口
 *
 * @author ruoyi
 * @date 2024-02-19
 */
public interface IBizVerdictRuleService {

    /**
     * 查询裁决表管理
     */
    BizVerdictRuleVo queryById(Long id);

    /**
     * 查询裁决表管理列表
     */
    TableDataInfo<BizVerdictRuleVo> queryPageList(BizVerdictRuleBo bo, PageQuery pageQuery);

    /**
     * 查询裁决表管理列表
     */
    List<BizVerdictRuleVo> queryList(BizVerdictRuleBo bo);

    /**
     * 新增裁决表管理
     */
    Boolean insertByBo(BizVerdictRuleBo bo);

    /**
     * 修改裁决表管理
     */
    Boolean updateByBo(BizVerdictRuleBo bo);

    /**
     * 校验并批量删除裁决表管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    List<BizVerdictRule> queryRecord(String selfWeaponName, String targetWeaponName, Integer distance);


    List<BizVerdictRuleVo> getRuleList(BizVerdictRuleBo dto);
}
