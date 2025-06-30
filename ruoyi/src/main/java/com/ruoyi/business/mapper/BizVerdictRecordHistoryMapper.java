package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.BizVerdictRecordHistory;
import com.ruoyi.business.domain.bo.BizVerdictRecordHistoryBo;
import com.ruoyi.business.domain.vo.BizVerdictRecordHistoryVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 裁决记录历史复盘Mapper接口
 *
 * @author ruoyi
 * @date 2024-02-20
 */
public interface BizVerdictRecordHistoryMapper extends BaseMapperPlus<BizVerdictRecordHistoryMapper, BizVerdictRecordHistory, BizVerdictRecordHistoryVo> {
    int deleteByRecord(@Param("bizVerdictRecordHistory") BizVerdictRecordHistory bizVerdictRecordHistory);
    List<BizVerdictRecordHistoryBo>  selectChessPieceByVerdictRecordId(@Param("bizVerdictRecordHistory") BizVerdictRecordHistoryBo dto);

    List<BizVerdictRecordHistoryBo>  selectByChessRoundAndVerdictRecordId(@Param("bizVerdictRecordHistory") BizVerdictRecordHistoryBo dto);
}
