package com.ruoyi.app.verdictRuleDimension.controller;

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
import com.ruoyi.app.verdictRuleDimension.domain.vo.BizVerdictRuleDimensionVo;
import com.ruoyi.app.verdictRuleDimension.domain.bo.BizVerdictRuleDimensionBo;
import com.ruoyi.app.verdictRuleDimension.service.IBizVerdictRuleDimensionService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 裁决规则维度定义表
 *
 * @author ccc
 * @date 2024-09-13
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/verdictRuleDimension")
public class BizVerdictRuleDimensionController extends BaseController {

    private final IBizVerdictRuleDimensionService iBizVerdictRuleDimensionService;

    /**
     * 查询裁决规则维度定义表列表
     */
    @SaCheckPermission("app:verdictRuleDimension:list")
    @GetMapping("/list")
    public TableDataInfo<BizVerdictRuleDimensionVo> list(BizVerdictRuleDimensionBo bo, PageQuery pageQuery) {
        return iBizVerdictRuleDimensionService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出裁决规则维度定义表列表
     */
    @SaCheckPermission("app:verdictRuleDimension:export")
    @Log(title = "裁决规则维度定义表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizVerdictRuleDimensionBo bo, HttpServletResponse response) {
        List<BizVerdictRuleDimensionVo> list = iBizVerdictRuleDimensionService.queryList(bo);
        ExcelUtil.exportExcel(list, "裁决规则维度定义表", BizVerdictRuleDimensionVo.class, response);
    }

    /**
     * 获取裁决规则维度定义表详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("app:verdictRuleDimension:query")
    @GetMapping("/{id}")
    public R<BizVerdictRuleDimensionVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizVerdictRuleDimensionService.queryById(id));
    }

    /**
     * 新增裁决规则维度定义表
     */
    @SaCheckPermission("app:verdictRuleDimension:add")
    @Log(title = "裁决规则维度定义表", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizVerdictRuleDimensionBo bo) {
        return toAjax(iBizVerdictRuleDimensionService.insertByBo(bo));
    }

    /**
     * 修改裁决规则维度定义表
     */
    @SaCheckPermission("app:verdictRuleDimension:edit")
    @Log(title = "裁决规则维度定义表", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizVerdictRuleDimensionBo bo) {
        return toAjax(iBizVerdictRuleDimensionService.updateByBo(bo));
    }

    /**
     * 删除裁决规则维度定义表
     *
     * @param ids 主键串
     */
    @SaCheckPermission("app:verdictRuleDimension:remove")
    @Log(title = "裁决规则维度定义表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizVerdictRuleDimensionService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
