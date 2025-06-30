package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.BizVerdictBlowEffect;
import com.ruoyi.business.domain.vo.BizVerdictBlowEffectVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;

import java.util.List;

/**
 * 裁决打击效果Mapper接口
 *
 * @author ruoyi
 * @date 2024-02-20
 */
public interface BizVerdictBlowEffectMapper extends BaseMapperPlus<BizVerdictBlowEffectMapper, BizVerdictBlowEffect, BizVerdictBlowEffectVo> {
        List<BizVerdictBlowEffectVo> getBlowEffectList(Long type);
}
