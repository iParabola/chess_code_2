package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.BizChessPiecesAttribute;
import com.ruoyi.business.domain.vo.BizChessPiecesAttributeVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 棋子属性定义Mapper接口
 *
 * @author ruoyi
 * @date 2024-09-02
 */
public interface BizChessPiecesAttributeMapper extends BaseMapperPlus<BizChessPiecesAttributeMapper, BizChessPiecesAttribute, BizChessPiecesAttributeVo> {
    List<BizChessPiecesAttribute> queryList(@Param("productId") Long productId, @Param("chessTypeId") Long chessTypeId);
}
