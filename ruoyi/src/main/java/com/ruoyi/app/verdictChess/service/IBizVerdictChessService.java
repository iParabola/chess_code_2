package com.ruoyi.app.verdictChess.service;

import com.ruoyi.app.verdictChess.domain.BizVerdictChess;
import com.ruoyi.app.verdictChess.domain.vo.BizVerdictChessDto;
import com.ruoyi.app.verdictChess.domain.vo.BizVerdictChessVo;
import com.ruoyi.app.verdictChess.domain.bo.BizVerdictChessBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 裁决棋子表Service接口
 *
 * @author ruoyi
 * @date 2024-08-21
 */
public interface IBizVerdictChessService {

    /**
     * 查询裁决棋子表
     */
    BizVerdictChessVo queryById(Long id);

    /**
     * 查询裁决棋子表列表
     */
    TableDataInfo<BizVerdictChessVo> queryPageList(BizVerdictChessBo bo, PageQuery pageQuery);

    /**
     * 查询裁决棋子表列表
     */
    List<BizVerdictChessVo> queryList(BizVerdictChessBo bo);

    /**
     * 查询裁决棋子表列表
     */
    List<BizVerdictChessDto> queryDtoList(BizVerdictChessBo bo);

    /**
     * 新增裁决棋子表
     */
    Boolean insertByBo(BizVerdictChessBo bo);

    /**
     * 修改裁决棋子表
     */
    Boolean updateByBo(BizVerdictChessBo bo);

    /**
     * 校验并批量删除裁决棋子表信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
