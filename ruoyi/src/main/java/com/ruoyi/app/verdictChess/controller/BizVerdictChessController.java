package com.ruoyi.app.verdictChess.controller;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.app.verdictChess.domain.vo.BizVerdictChessDto;
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
import com.ruoyi.app.verdictChess.domain.vo.BizVerdictChessVo;
import com.ruoyi.app.verdictChess.domain.bo.BizVerdictChessBo;
import com.ruoyi.app.verdictChess.service.IBizVerdictChessService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 裁决棋子表
 *
 * @author ruoyi
 * @date 2024-08-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/verdictChess/verdictChess")
public class BizVerdictChessController extends BaseController {

    private final IBizVerdictChessService iBizVerdictChessService;

    /**
     * 查询裁决棋子表列表
     */
    @SaCheckPermission("verdictChess:verdictChess:list")
    @GetMapping("/list")
    public TableDataInfo<BizVerdictChessVo> list(BizVerdictChessBo bo, PageQuery pageQuery) {
        return iBizVerdictChessService.queryPageList(bo, pageQuery);
    }

    /**
     * 查询裁决棋子表列表
     */
    @SaCheckPermission("verdictChess:verdictChess:list")
    @GetMapping("/queryList")
    public TableDataInfo<BizVerdictChessDto> queryList(BizVerdictChessBo bo) {
        return TableDataInfo.build(iBizVerdictChessService.queryDtoList(bo));
    }

    /**
     * 导出裁决棋子表列表
     */
    @SaCheckPermission("verdictChess:verdictChess:export")
    @Log(title = "裁决棋子表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizVerdictChessBo bo, HttpServletResponse response) {
        List<BizVerdictChessVo> list = iBizVerdictChessService.queryList(bo);
        ExcelUtil.exportExcel(list, "裁决棋子表", BizVerdictChessVo.class, response);
    }

    /**
     * 获取裁决棋子表详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("verdictChess:verdictChess:query")
    @GetMapping("/{id}")
    public R<BizVerdictChessVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizVerdictChessService.queryById(id));
    }

    /**
     * 新增裁决棋子表
     */
    @SaCheckPermission("verdictChess:verdictChess:add")
    @Log(title = "裁决棋子表", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizVerdictChessBo bo) {
        return toAjax(iBizVerdictChessService.insertByBo(bo));
    }

    /**
     * 修改裁决棋子表
     */
    @SaCheckPermission("verdictChess:verdictChess:edit")
    @Log(title = "裁决棋子表", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizVerdictChessBo bo) {
        return toAjax(iBizVerdictChessService.updateByBo(bo));
    }

    /**
     * 删除裁决棋子表
     *
     * @param ids 主键串
     */
    @SaCheckPermission("verdictChess:verdictChess:remove")
    @Log(title = "裁决棋子表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizVerdictChessService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
