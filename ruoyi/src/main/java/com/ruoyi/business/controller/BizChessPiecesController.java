package com.ruoyi.business.controller;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import com.ruoyi.business.domain.vo.BizChessPiecesVo;
import com.ruoyi.business.domain.bo.BizChessPiecesBo;
import com.ruoyi.business.service.IBizChessPiecesService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 棋子管理
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/chessPieces")
public class BizChessPiecesController extends BaseController {

    private final IBizChessPiecesService iBizChessPiecesService;

    /**
     * 查询棋子管理列表
     */
    @SaCheckPermission("business:chessPieces:list")
    @GetMapping("/list")
    public TableDataInfo<BizChessPiecesVo> list(BizChessPiecesBo bo, PageQuery pageQuery) {
        return iBizChessPiecesService.queryPageList(bo, pageQuery);
    }

    @GetMapping("/queryChessPiecesList")
    public List<BizChessPiecesVo> queryChessPiecesList(BizChessPiecesBo bo) {
        return iBizChessPiecesService.queryList(bo);
    }

    /**
     * 导出棋子管理列表
     */
    @SaCheckPermission("business:chessPieces:export")
    @Log(title = "棋子管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizChessPiecesBo bo, HttpServletResponse response) {
        List<BizChessPiecesVo> list = iBizChessPiecesService.queryList(bo);
        ExcelUtil.exportExcel(list, "棋子管理", BizChessPiecesVo.class, response);
    }

    /**
     * 获取棋子管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:chessPieces:query")
    @GetMapping("/{id}")
    public R<BizChessPiecesVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizChessPiecesService.queryById(id));
    }

    /**
     * 新增棋子管理
     */
    @SaCheckPermission("business:chessPieces:add")
    @Log(title = "棋子管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizChessPiecesBo bo) {
        return toAjax(iBizChessPiecesService.insertByBo(bo));
    }

    /**
     * 修改棋子管理
     */
    @SaCheckPermission("business:chessPieces:edit")
    @Log(title = "棋子管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizChessPiecesBo bo) {
        return toAjax(iBizChessPiecesService.updateByBo(bo));
    }

    /**
     * 删除棋子管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:chessPieces:remove")
    @Log(title = "棋子管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizChessPiecesService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
