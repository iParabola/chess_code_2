package com.ruoyi.business.controller;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.core.validate.QueryGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.business.domain.vo.BizVerdictRecordChessPiecesVo;
import com.ruoyi.business.domain.bo.BizVerdictRecordChessPiecesBo;
import com.ruoyi.business.service.IBizVerdictRecordChessPiecesService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 裁决记录棋子
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/verdictRecordChessPieces")
public class BizVerdictRecordChessPiecesController extends BaseController {

    private final IBizVerdictRecordChessPiecesService iBizVerdictRecordChessPiecesService;

    /**
     * 查询裁决记录棋子列表
     */
    @SaCheckPermission("business:verdictRecordChessPieces:list")
    @GetMapping("/list")
    public TableDataInfo<BizVerdictRecordChessPiecesVo> list(BizVerdictRecordChessPiecesBo bo, PageQuery pageQuery) {
        return iBizVerdictRecordChessPiecesService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出裁决记录棋子列表
     */
    @SaCheckPermission("business:verdictRecordChessPieces:export")
    @Log(title = "裁决记录棋子", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizVerdictRecordChessPiecesBo bo, HttpServletResponse response) {
        List<BizVerdictRecordChessPiecesVo> list = iBizVerdictRecordChessPiecesService.queryList(bo);
        ExcelUtil.exportExcel(list, "裁决记录棋子", BizVerdictRecordChessPiecesVo.class, response);
    }

    /**
     * 获取裁决记录棋子详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:verdictRecordChessPieces:query")
    @GetMapping("/{id}")
    public R<BizVerdictRecordChessPiecesVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizVerdictRecordChessPiecesService.queryById(id));
    }

    /**
     * 新增裁决记录棋子
     */
    @SaCheckPermission("business:verdictRecordChessPieces:add")
    @Log(title = "裁决记录棋子", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizVerdictRecordChessPiecesBo bo) {
        return toAjax(iBizVerdictRecordChessPiecesService.insertByBo(bo));
    }

    /**
     * 修改裁决记录棋子
     */
    @SaCheckPermission("business:verdictRecordChessPieces:edit")
    @Log(title = "裁决记录棋子", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizVerdictRecordChessPiecesBo bo) {
        return toAjax(iBizVerdictRecordChessPiecesService.updateByBo(bo));
    }

    /**
     * 删除裁决记录棋子
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:verdictRecordChessPieces:remove")
    @Log(title = "裁决记录棋子", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizVerdictRecordChessPiecesService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
