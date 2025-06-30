package com.ruoyi.business.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ruoyi.business.domain.BizVerdictRecord;
import com.ruoyi.business.domain.BizVerdictRecordRound;
import com.ruoyi.business.domain.vo.BizVerdictRecordRoundVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 裁决记录回合管理Mapper接口
 *
 * @author ruoyi
 * @date 2024-03-07
 */
public interface BizVerdictRecordRoundMapper extends BaseMapperPlus<BizVerdictRecordRoundMapper, BizVerdictRecordRound, BizVerdictRecordRoundVo> {
  List<BizVerdictRecordRoundVo> getHistoryTreeByRound(@Param(Constants.WRAPPER) LambdaQueryWrapper<BizVerdictRecordRound> queryWrapper);
}
