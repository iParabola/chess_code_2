package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizChessPiecesCamp;
import com.ruoyi.business.domain.vo.BizChessPiecesCampVo;
import com.ruoyi.business.domain.bo.BizChessPiecesCampBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 棋子阵营Service接口
 *
 * @author ruoyi
 * @date 2024-02-21
 */
public interface IBizChessPiecesCampService {

    /**
     * 查询棋子阵营
     */
    BizChessPiecesCampVo queryById(Long id);

    /**
     * 查询棋子阵营列表
     */
    TableDataInfo<BizChessPiecesCampVo> queryPageList(BizChessPiecesCampBo bo, PageQuery pageQuery);

    /**
     * 查询棋子阵营列表
     */
    List<BizChessPiecesCampVo> queryList(BizChessPiecesCampBo bo);

    /**
     * 新增棋子阵营
     */
    Boolean insertByBo(BizChessPiecesCampBo bo);

    /**
     * 修改棋子阵营
     */
    Boolean updateByBo(BizChessPiecesCampBo bo);

    /**
     * 校验并批量删除棋子阵营信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
