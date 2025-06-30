package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizChessPieces;
import com.ruoyi.business.domain.vo.BizChessPiecesVo;
import com.ruoyi.business.domain.bo.BizChessPiecesBo;
import com.ruoyi.business.domain.vo.BizProductVo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

/**
 * 棋子管理Service接口
 *
 * @author ruoyi
 * @date 2024-02-19
 */
public interface IBizChessPiecesService {

    /**
     * 查询棋子管理
     */
    BizChessPiecesVo queryById(Long id);

    /**
     * 查询棋子管理列表
     */
    TableDataInfo<BizChessPiecesVo> queryPageList(BizChessPiecesBo bo, PageQuery pageQuery);

    /**
     * 查询棋子管理列表
     */
    List<BizChessPiecesVo> queryList(BizChessPiecesBo bo);

    /**
     * 新增棋子管理
     */
    Boolean insertByBo(BizChessPiecesBo bo);

    /**
     * 修改棋子管理
     */
    Boolean updateByBo(BizChessPiecesBo bo);

    /**
     * 校验并批量删除棋子管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);


    List<BizChessPiecesVo> queryVerdictChessPiecesVos( @RequestBody BizProductVo queryArmoryDetailDto);

    BizChessPieces queryByChessPiecesNumber(String chessPiecesNumber);

    List<BizChessPieces> queryByProductId(Long productId);
}
