package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.BizVerdictRecordScore;
import com.ruoyi.business.domain.vo.BizVerdictRecordScoreVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;

import java.util.List;

/**
 * 裁决记录分数管理Mapper接口
 *
 * @author ruoyi
 * @date 2024-03-04
 */
public interface BizVerdictRecordScoreMapper extends BaseMapperPlus<BizVerdictRecordScoreMapper, BizVerdictRecordScore, BizVerdictRecordScoreVo> {
    List<BizVerdictRecordScoreVo> getRealTimeScore(Long verdictRecordId);
    List<BizVerdictRecordScoreVo> getSummaryScore(Long verdictRecordId);
    List<BizVerdictRecordScoreVo> getSummaryScoreNew(Long verdictRecordId);
    List<BizVerdictRecordScoreVo> getSummaryTotal(Long verdictRecordId);

}
