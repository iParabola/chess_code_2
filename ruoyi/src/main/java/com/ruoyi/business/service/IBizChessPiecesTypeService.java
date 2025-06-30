package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizChessPiecesType;
import com.ruoyi.business.domain.vo.BizChessPiecesTypeVo;
import com.ruoyi.business.domain.bo.BizChessPiecesTypeBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 棋子类型Service接口
 *
 * @author ruoyi
 * @date 2024-02-20
 */
public interface IBizChessPiecesTypeService {

    /**
     * 查询棋子类型
     */
    BizChessPiecesTypeVo queryById(Long id);

    /**
     * 查询棋子类型列表
     */
    TableDataInfo<BizChessPiecesTypeVo> queryPageList(BizChessPiecesTypeBo bo, PageQuery pageQuery);

    /**
     * 查询棋子类型列表
     */
    List<BizChessPiecesTypeVo> queryList(BizChessPiecesTypeBo bo);

    /**
     * 新增棋子类型
     */
    Boolean insertByBo(BizChessPiecesTypeBo bo);

    /**
     * 修改棋子类型
     */
    Boolean updateByBo(BizChessPiecesTypeBo bo);

    /**
     * 校验并批量删除棋子类型信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
