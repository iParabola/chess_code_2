package com.ruoyi.app.verdictRuleValue.controller;

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
import com.ruoyi.app.verdictRuleValue.domain.vo.BizVerdictRuleValueVo;
import com.ruoyi.app.verdictRuleValue.domain.bo.BizVerdictRuleValueBo;
import com.ruoyi.app.verdictRuleValue.service.IBizVerdictRuleValueService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 裁决规则标准值表
 *
 * @author ccc
 * @date 2024-09-13
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/verdictRuleValue")
public class BizVerdictRuleValueController extends BaseController {

    private final IBizVerdictRuleValueService iBizVerdictRuleValueService;

    /**
     * 查询裁决规则标准值表列表
     */
    @SaCheckPermission("app:verdictRuleValue:list")
    @GetMapping("/list")
    public TableDataInfo<BizVerdictRuleValueVo> list(BizVerdictRuleValueBo bo, PageQuery pageQuery) {
        return iBizVerdictRuleValueService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出裁决规则标准值表列表
     */
    @SaCheckPermission("app:verdictRuleValue:export")
    @Log(title = "裁决规则标准值表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizVerdictRuleValueBo bo, HttpServletResponse response) {
        List<BizVerdictRuleValueVo> list = iBizVerdictRuleValueService.queryList(bo);
        ExcelUtil.exportExcel(list, "裁决规则标准值表", BizVerdictRuleValueVo.class, response);
    }

    /**
     * 获取裁决规则标准值表详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("app:verdictRuleValue:query")
    @GetMapping("/{id}")
    public R<BizVerdictRuleValueVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizVerdictRuleValueService.queryById(id));
    }

    /**
     * 新增裁决规则标准值表
     */
    @SaCheckPermission("app:verdictRuleValue:add")
    @Log(title = "裁决规则标准值表", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizVerdictRuleValueBo bo) {
        return toAjax(iBizVerdictRuleValueService.insertByBo(bo));
    }

    /**
     * 修改裁决规则标准值表
     */
    @SaCheckPermission("app:verdictRuleValue:edit")
    @Log(title = "裁决规则标准值表", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizVerdictRuleValueBo bo) {
        return toAjax(iBizVerdictRuleValueService.updateByBo(bo));
    }

    /**
     * 删除裁决规则标准值表
     *
     * @param ids 主键串
     */
    @SaCheckPermission("app:verdictRuleValue:remove")
    @Log(title = "裁决规则标准值表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizVerdictRuleValueService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
