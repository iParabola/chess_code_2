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
import com.ruoyi.business.domain.vo.BizVerdictRecordVo;
import com.ruoyi.business.domain.bo.BizVerdictRecordBo;
import com.ruoyi.business.service.IBizVerdictRecordService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 裁决记录
 *
 * @author ruoyi
 * @date 2024-02-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/verdictRecord")
public class BizVerdictRecordController extends BaseController {

    private final IBizVerdictRecordService iBizVerdictRecordService;

    /**
     * 查询裁决记录列表
     */
    @SaCheckPermission("business:verdictRecord:list")
    @GetMapping("/list")
    public TableDataInfo<BizVerdictRecordVo> list(BizVerdictRecordBo bo, PageQuery pageQuery) {
        return iBizVerdictRecordService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出裁决记录列表
     */
    @SaCheckPermission("business:verdictRecord:export")
    @Log(title = "裁决记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizVerdictRecordBo bo, HttpServletResponse response) {
        List<BizVerdictRecordVo> list = iBizVerdictRecordService.queryList(bo);
        ExcelUtil.exportExcel(list, "裁决记录", BizVerdictRecordVo.class, response);
    }

    /**
     * 获取裁决记录详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:verdictRecord:query")
    @GetMapping("/{id}")
    public R<BizVerdictRecordVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizVerdictRecordService.queryById(id));
    }

    /**
     * 新增裁决记录
     */
    @SaCheckPermission("business:verdictRecord:add")
    @Log(title = "裁决记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizVerdictRecordBo bo) {
        return toAjax(iBizVerdictRecordService.insertByBo(bo));
    }

    /**
     * 修改裁决记录
     */
    @SaCheckPermission("business:verdictRecord:edit")
    @Log(title = "裁决记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizVerdictRecordBo bo) {
        return toAjax(iBizVerdictRecordService.updateByBo(bo));
    }

    /**
     * 删除裁决记录
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:verdictRecord:remove")
    @Log(title = "裁决记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizVerdictRecordService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
