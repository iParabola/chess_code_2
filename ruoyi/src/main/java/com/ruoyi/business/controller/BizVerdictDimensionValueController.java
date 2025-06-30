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
import com.ruoyi.business.domain.vo.BizVerdictDimensionValueVo;
import com.ruoyi.business.domain.bo.BizVerdictDimensionValueBo;
import com.ruoyi.business.service.IBizVerdictDimensionValueService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 裁决维度定义值域
 *
 * @author ruoyi
 * @date 2024-09-09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/verdictDimensionValue")
public class BizVerdictDimensionValueController extends BaseController {

    private final IBizVerdictDimensionValueService iBizVerdictDimensionValueService;

    /**
     * 查询裁决维度定义值域列表
     */
    @SaCheckPermission("business:verdictDimensionValue:list")
    @GetMapping("/list")
    public TableDataInfo<BizVerdictDimensionValueVo> list(BizVerdictDimensionValueBo bo, PageQuery pageQuery) {
        return iBizVerdictDimensionValueService.queryPageList(bo, pageQuery);
    }
    @GetMapping("/listByDimensionId")
    public R<List<BizVerdictDimensionValueVo>> list(BizVerdictDimensionValueBo bo) {
           return R.ok(iBizVerdictDimensionValueService.queryList(bo));
    }

    /**
     * 导出裁决维度定义值域列表
     */
    @SaCheckPermission("business:verdictDimensionValue:export")
    @Log(title = "裁决维度定义值域", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizVerdictDimensionValueBo bo, HttpServletResponse response) {
        List<BizVerdictDimensionValueVo> list = iBizVerdictDimensionValueService.queryList(bo);
        ExcelUtil.exportExcel(list, "裁决维度定义值域", BizVerdictDimensionValueVo.class, response);
    }

    /**
     * 获取裁决维度定义值域详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:verdictDimensionValue:query")
    @GetMapping("/{id}")
    public R<BizVerdictDimensionValueVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizVerdictDimensionValueService.queryById(id));
    }

    /**
     * 新增裁决维度定义值域
     */
    @SaCheckPermission("business:verdictDimensionValue:add")
    @Log(title = "裁决维度定义值域", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizVerdictDimensionValueBo bo) {
        return toAjax(iBizVerdictDimensionValueService.insertByBo(bo));
    }

    /**
     * 修改裁决维度定义值域
     */
    @SaCheckPermission("business:verdictDimensionValue:edit")
    @Log(title = "裁决维度定义值域", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizVerdictDimensionValueBo bo) {
        return toAjax(iBizVerdictDimensionValueService.updateByBo(bo));
    }

    /**
     * 删除裁决维度定义值域
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:verdictDimensionValue:remove")
    @Log(title = "裁决维度定义值域", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizVerdictDimensionValueService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
