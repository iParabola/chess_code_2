package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.BizArbiterMapCoordinate;
import com.ruoyi.business.domain.vo.BizArbiterMapCoordinateVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;

import java.util.List;

/**
 * 地图坐标Mapper接口
 *
 * @author ruoyi
 * @date 2024-02-20
 */
public interface BizArbiterMapCoordinateMapper extends BaseMapperPlus<BizArbiterMapCoordinateMapper, BizArbiterMapCoordinate, BizArbiterMapCoordinateVo> {
        List<BizArbiterMapCoordinateVo> getMapCoordinate(Long arbiterMapId);
}
