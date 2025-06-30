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
import com.ruoyi.business.domain.vo.BizIndirectVerdictRuleVo;
import com.ruoyi.business.domain.bo.BizIndirectVerdictRuleBo;
import com.ruoyi.business.service.IBizIndirectVerdictRuleService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 间瞄裁决规则
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/indirectVerdictRule")
public class BizIndirectVerdictRuleController extends BaseController {

    private final IBizIndirectVerdictRuleService iBizIndirectVerdictRuleService;

    /**
     * 查询间瞄裁决规则列表
     */
    @SaCheckPermission("business:indirectVerdictRule:list")
    @GetMapping("/list")
    public TableDataInfo<BizIndirectVerdictRuleVo> list(BizIndirectVerdictRuleBo bo, PageQuery pageQuery) {
        return iBizIndirectVerdictRuleService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出间瞄裁决规则列表
     */
    @SaCheckPermission("business:indirectVerdictRule:export")
    @Log(title = "间瞄裁决规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizIndirectVerdictRuleBo bo, HttpServletResponse response) {
        List<BizIndirectVerdictRuleVo> list = iBizIndirectVerdictRuleService.queryList(bo);
        ExcelUtil.exportExcel(list, "间瞄裁决规则", BizIndirectVerdictRuleVo.class, response);
    }

    /**
     * 获取间瞄裁决规则详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:indirectVerdictRule:query")
    @GetMapping("/{id}")
    public R<BizIndirectVerdictRuleVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizIndirectVerdictRuleService.queryById(id));
    }

    /**
     * 新增间瞄裁决规则
     */
    @SaCheckPermission("business:indirectVerdictRule:add")
    @Log(title = "间瞄裁决规则", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizIndirectVerdictRuleBo bo) {
        return toAjax(iBizIndirectVerdictRuleService.insertByBo(bo));
    }

    /**
     * 修改间瞄裁决规则
     */
    @SaCheckPermission("business:indirectVerdictRule:edit")
    @Log(title = "间瞄裁决规则", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizIndirectVerdictRuleBo bo) {
        return toAjax(iBizIndirectVerdictRuleService.updateByBo(bo));
    }

    /**
     * 删除间瞄裁决规则
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:indirectVerdictRule:remove")
    @Log(title = "间瞄裁决规则", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizIndirectVerdictRuleService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
