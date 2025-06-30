package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizIndirectBlowEffect;
import com.ruoyi.business.domain.vo.BizIndirectBlowEffectVo;
import com.ruoyi.business.domain.bo.BizIndirectBlowEffectBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 间瞄打击效果Service接口
 *
 * @author ruoyi
 * @date 2024-02-20
 */
public interface IBizIndirectBlowEffectService {

    /**
     * 查询间瞄打击效果
     */
    BizIndirectBlowEffectVo queryById(Long id);

    /**
     * 查询间瞄打击效果列表
     */
    TableDataInfo<BizIndirectBlowEffectVo> queryPageList(BizIndirectBlowEffectBo bo, PageQuery pageQuery);

    /**
     * 查询间瞄打击效果列表
     */
    List<BizIndirectBlowEffectVo> queryList(BizIndirectBlowEffectBo bo);

    /**
     * 新增间瞄打击效果
     */
    Boolean insertByBo(BizIndirectBlowEffectBo bo);

    /**
     * 修改间瞄打击效果
     */
    Boolean updateByBo(BizIndirectBlowEffectBo bo);

    /**
     * 校验并批量删除间瞄打击效果信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    BizIndirectBlowEffect getByAttackScoreAndDiceScore(Long attackScore, Integer diceScore);
}
