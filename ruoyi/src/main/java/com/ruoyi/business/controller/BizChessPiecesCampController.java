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
import com.ruoyi.business.domain.vo.BizChessPiecesCampVo;
import com.ruoyi.business.domain.bo.BizChessPiecesCampBo;
import com.ruoyi.business.service.IBizChessPiecesCampService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 棋子阵营
 *
 * @author ruoyi
 * @date 2024-02-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/chessPiecesCamp")
public class BizChessPiecesCampController extends BaseController {

    private final IBizChessPiecesCampService iBizChessPiecesCampService;

    /**
     * 查询棋子阵营列表
     */
    @SaCheckPermission("business:chessPiecesCamp:list")
    @GetMapping("/list")
    public TableDataInfo<BizChessPiecesCampVo> list(BizChessPiecesCampBo bo, PageQuery pageQuery) {
        return iBizChessPiecesCampService.queryPageList(bo, pageQuery);
    }

    @GetMapping("/queryChessPiecesCampList")
    public List<BizChessPiecesCampVo> queryChessPiecesCampList(BizChessPiecesCampBo bo) {
        return iBizChessPiecesCampService.queryList(bo);
    }

    /**
     * 导出棋子阵营列表
     */
    @SaCheckPermission("business:chessPiecesCamp:export")
    @Log(title = "棋子阵营", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizChessPiecesCampBo bo, HttpServletResponse response) {
        List<BizChessPiecesCampVo> list = iBizChessPiecesCampService.queryList(bo);
        ExcelUtil.exportExcel(list, "棋子阵营", BizChessPiecesCampVo.class, response);
    }

    /**
     * 获取棋子阵营详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:chessPiecesCamp:query")
    @GetMapping("/{id}")
    public R<BizChessPiecesCampVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizChessPiecesCampService.queryById(id));
    }

    /**
     * 新增棋子阵营
     */
    @SaCheckPermission("business:chessPiecesCamp:add")
    @Log(title = "棋子阵营", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizChessPiecesCampBo bo) {
        return toAjax(iBizChessPiecesCampService.insertByBo(bo));
    }

    @PostMapping("/saveChessPiecesCamp")
    public R<Void> saveChessPiecesCamp(@RequestBody BizChessPiecesCampBo bo) {
        return toAjax(iBizChessPiecesCampService.insertByBo(bo));
    }


    /**
     * 修改棋子阵营
     */
    @SaCheckPermission("business:chessPiecesCamp:edit")
    @Log(title = "棋子阵营", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizChessPiecesCampBo bo) {
        return toAjax(iBizChessPiecesCampService.updateByBo(bo));
    }

    /**
     * 删除棋子阵营
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:chessPiecesCamp:remove")
    @Log(title = "棋子阵营", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizChessPiecesCampService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
