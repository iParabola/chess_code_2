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
import com.ruoyi.business.domain.vo.BizVerdictRuleVo;
import com.ruoyi.business.domain.bo.BizVerdictRuleBo;
import com.ruoyi.business.service.IBizVerdictRuleService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 裁决表管理
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/verdictRule")
public class BizVerdictRuleController extends BaseController {

    private final IBizVerdictRuleService iBizVerdictRuleService;

    /**
     * 查询裁决表管理列表
     */
    @SaCheckPermission("business:verdictRule:list")
    @GetMapping("/list")
    public TableDataInfo<BizVerdictRuleVo> list(BizVerdictRuleBo bo, PageQuery pageQuery) {
        return iBizVerdictRuleService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出裁决表管理列表
     */
    @SaCheckPermission("business:verdictRule:export")
    @Log(title = "裁决表管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizVerdictRuleBo bo, HttpServletResponse response) {
        List<BizVerdictRuleVo> list = iBizVerdictRuleService.queryList(bo);
        ExcelUtil.exportExcel(list, "裁决表管理", BizVerdictRuleVo.class, response);
    }

    /**
     * 获取裁决表管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:verdictRule:query")
    @GetMapping("/{id}")
    public R<BizVerdictRuleVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizVerdictRuleService.queryById(id));
    }

    /**
     * 新增裁决表管理
     */
    @SaCheckPermission("business:verdictRule:add")
    @Log(title = "裁决表管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizVerdictRuleBo bo) {
        return toAjax(iBizVerdictRuleService.insertByBo(bo));
    }

    /**
     * 修改裁决表管理
     */
    @SaCheckPermission("business:verdictRule:edit")
    @Log(title = "裁决表管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizVerdictRuleBo bo) {
        return toAjax(iBizVerdictRuleService.updateByBo(bo));
    }

    /**
     * 删除裁决表管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:verdictRule:remove")
    @Log(title = "裁决表管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizVerdictRuleService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
