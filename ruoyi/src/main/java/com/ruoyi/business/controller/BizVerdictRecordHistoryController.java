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
import com.ruoyi.business.domain.vo.BizVerdictRecordHistoryVo;
import com.ruoyi.business.domain.bo.BizVerdictRecordHistoryBo;
import com.ruoyi.business.service.IBizVerdictRecordHistoryService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 裁决记录历史复盘
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/verdictRecordHistory")
public class BizVerdictRecordHistoryController extends BaseController {

    private final IBizVerdictRecordHistoryService iBizVerdictRecordHistoryService;

    /**
     * 查询裁决记录历史复盘列表
     */
    @SaCheckPermission("business:verdictRecordHistory:list")
    @GetMapping("/list")
    public TableDataInfo<BizVerdictRecordHistoryVo> list(BizVerdictRecordHistoryBo bo, PageQuery pageQuery) {
        return iBizVerdictRecordHistoryService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出裁决记录历史复盘列表
     */
    @SaCheckPermission("business:verdictRecordHistory:export")
    @Log(title = "裁决记录历史复盘", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizVerdictRecordHistoryBo bo, HttpServletResponse response) {
        List<BizVerdictRecordHistoryVo> list = iBizVerdictRecordHistoryService.queryList(bo);
        ExcelUtil.exportExcel(list, "裁决记录历史复盘", BizVerdictRecordHistoryVo.class, response);
    }

    /**
     * 获取裁决记录历史复盘详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:verdictRecordHistory:query")
    @GetMapping("/{id}")
    public R<BizVerdictRecordHistoryVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizVerdictRecordHistoryService.queryById(id));
    }

    /**
     * 新增裁决记录历史复盘
     */
    @SaCheckPermission("business:verdictRecordHistory:add")
    @Log(title = "裁决记录历史复盘", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizVerdictRecordHistoryBo bo) {
        return toAjax(iBizVerdictRecordHistoryService.insertByBo(bo));
    }

    /**
     * 修改裁决记录历史复盘
     */
    @SaCheckPermission("business:verdictRecordHistory:edit")
    @Log(title = "裁决记录历史复盘", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizVerdictRecordHistoryBo bo) {
        return toAjax(iBizVerdictRecordHistoryService.updateByBo(bo));
    }

    /**
     * 删除裁决记录历史复盘
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:verdictRecordHistory:remove")
    @Log(title = "裁决记录历史复盘", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizVerdictRecordHistoryService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
