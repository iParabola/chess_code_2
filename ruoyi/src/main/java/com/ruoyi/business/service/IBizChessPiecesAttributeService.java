package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizChessPiecesAttribute;
import com.ruoyi.business.domain.dto.BizChessPiecesAttributeDto;
import com.ruoyi.business.domain.vo.BizChessPiecesAttributeVo;
import com.ruoyi.business.domain.bo.BizChessPiecesAttributeBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 棋子属性定义Service接口
 *
 * @author ruoyi
 * @date 2024-09-02
 */
public interface IBizChessPiecesAttributeService {

    /**
     * 查询棋子属性定义
     */
    BizChessPiecesAttributeVo queryById(Long id);

    /**
     * 查询棋子属性定义列表
     */
    TableDataInfo<BizChessPiecesAttributeVo> queryPageList(BizChessPiecesAttributeBo bo, PageQuery pageQuery);

    /**
     * 查询棋子属性定义列表
     */
    List<BizChessPiecesAttributeVo> queryList(BizChessPiecesAttributeBo bo);

    /**
     * 查询裁决棋子表列表
     */
    List<BizChessPiecesAttribute> queryDtoList(BizChessPiecesAttributeDto bo);

    /**
     * 新增棋子属性定义
     */
    Boolean insertByBo(BizChessPiecesAttributeBo bo);

    /**
     * 修改棋子属性定义
     */
    Boolean updateByBo(BizChessPiecesAttributeBo bo);

    /**
     * 校验并批量删除棋子属性定义信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
