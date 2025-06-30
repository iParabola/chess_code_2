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
import com.ruoyi.business.domain.vo.BizVerdictDimensionVo;
import com.ruoyi.business.domain.bo.BizVerdictDimensionBo;
import com.ruoyi.business.service.IBizVerdictDimensionService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 裁决维度定义
 *
 * @author ruoyi
 * @date 2024-09-09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/verdictDimension")
public class BizVerdictDimensionController extends BaseController {

    private final IBizVerdictDimensionService iBizVerdictDimensionService;

    /**
     * 查询裁决维度定义列表
     */
    @SaCheckPermission("business:verdictDimension:list")
    @GetMapping("/list")
    public TableDataInfo<BizVerdictDimensionVo> list(BizVerdictDimensionBo bo, PageQuery pageQuery) {
        return iBizVerdictDimensionService.queryPageList(bo, pageQuery);
    }

    @GetMapping("/listAll")
    public R<List<BizVerdictDimensionVo>> listAll(BizVerdictDimensionBo bo) {
           return  R.ok(iBizVerdictDimensionService.queryList(bo));
    }

    /**
     * 导出裁决维度定义列表
     */
    @SaCheckPermission("business:verdictDimension:export")
    @Log(title = "裁决维度定义", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizVerdictDimensionBo bo, HttpServletResponse response) {
        List<BizVerdictDimensionVo> list = iBizVerdictDimensionService.queryList(bo);
        ExcelUtil.exportExcel(list, "裁决维度定义", BizVerdictDimensionVo.class, response);
    }

    /**
     * 获取裁决维度定义详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:verdictDimension:query")
    @GetMapping("/{id}")
    public R<BizVerdictDimensionVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizVerdictDimensionService.queryById(id));
    }

    /**
     * 新增裁决维度定义
     */
    @SaCheckPermission("business:verdictDimension:add")
    @Log(title = "裁决维度定义", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizVerdictDimensionBo bo) {
        return toAjax(iBizVerdictDimensionService.insertByBo(bo));
    }

    /**
     * 修改裁决维度定义
     */
    @SaCheckPermission("business:verdictDimension:edit")
    @Log(title = "裁决维度定义", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizVerdictDimensionBo bo) {
        return toAjax(iBizVerdictDimensionService.updateByBo(bo));
    }

    /**
     * 删除裁决维度定义
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:verdictDimension:remove")
    @Log(title = "裁决维度定义", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizVerdictDimensionService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
