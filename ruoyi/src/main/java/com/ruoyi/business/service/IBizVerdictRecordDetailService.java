package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizVerdictRecordDetail;
import com.ruoyi.business.domain.bo.BizChessPiecesBo;
import com.ruoyi.business.domain.bo.BizVerdictRecordBo;
import com.ruoyi.business.domain.vo.BizChessPiecesVo;
import com.ruoyi.business.domain.vo.BizVerdictRecordDetailVo;
import com.ruoyi.business.domain.bo.BizVerdictRecordDetailBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

/**
 * 裁决记录详情Service接口
 *
 * @author ruoyi
 * @date 2024-02-21
 */
public interface IBizVerdictRecordDetailService {

    /**
     * 查询裁决记录详情
     */
    BizVerdictRecordDetailVo queryById(Long id);

    /**
     * 查询裁决记录详情列表
     */
    TableDataInfo<BizVerdictRecordDetailVo> queryPageList(BizVerdictRecordDetailBo bo, PageQuery pageQuery);

    /**
     * 查询裁决记录详情列表
     */
    List<BizVerdictRecordDetailVo> queryList(BizVerdictRecordDetailBo bo);

    /**
     * 新增裁决记录详情
     */
    Boolean insertByBo(BizVerdictRecordDetailBo bo);

    /**
     * 修改裁决记录详情
     */
    Boolean updateByBo(BizVerdictRecordDetailBo bo);

    /**
     * 校验并批量删除裁决记录详情信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);


    List<BizVerdictRecordDetailVo> goVerdictRecordDetail(BizVerdictRecordDetailBo dto);

    List<String> targetChessPiecesNumberList(@Validated @RequestBody BizVerdictRecordBo dto);


    BizChessPiecesVo targetChessPiecesDetail(@RequestBody BizChessPiecesBo dto);


    BizVerdictRecordDetailVo verdict(@Validated @RequestBody BizVerdictRecordDetailBo dto);
}
