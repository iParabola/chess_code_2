package com.ruoyi.app.verdictChess.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ruoyi.app.verdictChess.domain.BizVerdictChess;
import com.ruoyi.app.verdictChess.domain.vo.BizVerdictChessDto;
import com.ruoyi.app.verdictChess.domain.vo.BizVerdictChessVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 裁决棋子表Mapper接口
 *
 * @author ruoyi
 * @date 2024-08-21
 */
public interface BizVerdictChessMapper extends BaseMapperPlus<BizVerdictChessMapper, BizVerdictChess, BizVerdictChessVo> {
    List<BizVerdictChessDto> queryList(@Param(Constants.WRAPPER) QueryWrapper<BizVerdictChess> queryWrapper);
}
