package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizVerdictBlowEffect;
import com.ruoyi.business.domain.vo.BizVerdictBlowEffectVo;
import com.ruoyi.business.domain.bo.BizVerdictBlowEffectBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

/**
 * 裁决打击效果Service接口
 *
 * @author ruoyi
 * @date 2024-02-20
 */
public interface IBizVerdictBlowEffectService {

    /**
     * 查询裁决打击效果
     */
    BizVerdictBlowEffectVo queryById(Long id);

    /**
     * 查询裁决打击效果列表
     */
    TableDataInfo<BizVerdictBlowEffectVo> queryPageList(BizVerdictBlowEffectBo bo, PageQuery pageQuery);

    /**
     * 查询裁决打击效果列表
     */
    List<BizVerdictBlowEffectVo> queryList(BizVerdictBlowEffectBo bo);

    /**
     * 新增裁决打击效果
     */
    Boolean insertByBo(BizVerdictBlowEffectBo bo);

    /**
     * 修改裁决打击效果
     */
    Boolean updateByBo(BizVerdictBlowEffectBo bo);

    /**
     * 校验并批量删除裁决打击效果信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);


    BizVerdictBlowEffect queryRecord(Integer verdictType, Integer attackScore, Integer diceScore);


    List<BizVerdictBlowEffectVo> getBlowEffectList(@RequestBody BizVerdictBlowEffectBo dto);

}
