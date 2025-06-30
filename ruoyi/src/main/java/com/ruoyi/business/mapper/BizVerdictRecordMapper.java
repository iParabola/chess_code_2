package com.ruoyi.business.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.business.domain.BizVerdictRecord;
import com.ruoyi.business.domain.BizVerdictRecordRound;
import com.ruoyi.business.domain.dto.QueryVerdictRecordDetail;
import com.ruoyi.business.domain.dto.QueryVerdictResultVo;
import com.ruoyi.business.domain.vo.BizVerdictRecordVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

/**
 * 裁决记录Mapper接口
 *
 * @author ruoyi
 * @date 2024-02-21
 */
public interface BizVerdictRecordMapper extends BaseMapperPlus<BizVerdictRecordMapper, BizVerdictRecord, BizVerdictRecordVo> {
    Page<QueryVerdictRecordDetail> myQueryPageList(@Param("page") Page<BizVerdictRecord> page, @Param(Constants.WRAPPER) QueryWrapper<BizVerdictRecord> queryWrapper);

}
