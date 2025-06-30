package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizIndirectVerdictRule;
import com.ruoyi.business.domain.vo.BizIndirectVerdictRuleVo;
import com.ruoyi.business.domain.bo.BizIndirectVerdictRuleBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 间瞄裁决规则Service接口
 *
 * @author ruoyi
 * @date 2024-02-20
 */
public interface IBizIndirectVerdictRuleService {

    /**
     * 查询间瞄裁决规则
     */
    BizIndirectVerdictRuleVo queryById(Long id);

    /**
     * 查询间瞄裁决规则列表
     */
    TableDataInfo<BizIndirectVerdictRuleVo> queryPageList(BizIndirectVerdictRuleBo bo, PageQuery pageQuery);

    /**
     * 查询间瞄裁决规则列表
     */
    List<BizIndirectVerdictRuleVo> queryList(BizIndirectVerdictRuleBo bo);

    /**
     * 新增间瞄裁决规则
     */
    Boolean insertByBo(BizIndirectVerdictRuleBo bo);

    /**
     * 修改间瞄裁决规则
     */
    Boolean updateByBo(BizIndirectVerdictRuleBo bo);

    /**
     * 校验并批量删除间瞄裁决规则信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    int countByChessPiecesNumber(String chessPiecesNumber);

    /**
     * query fire type
     *
     * @param verdictRecordId
     * @param chessPiecesCampId
     * @param targetCoordinate
     * @param distance
     * @return
     */
    Integer queryFireType(Long verdictRecordId, Long chessPiecesCampId, String targetCoordinate, int distance);

    /**
     * 查询匹配间瞄规则
     *
     * @param chessPiecesNumber
     * @param fireType
     * @return
     */
    BizIndirectVerdictRule queryIndirectRuleRecord(String chessPiecesNumber, Integer fireType);

}
