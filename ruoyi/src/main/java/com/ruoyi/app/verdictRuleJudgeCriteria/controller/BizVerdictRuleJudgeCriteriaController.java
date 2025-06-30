package com.ruoyi.app.verdictRuleJudgeCriteria.controller;

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
import com.ruoyi.app.verdictRuleJudgeCriteria.domain.vo.BizVerdictRuleJudgeCriteriaVo;
import com.ruoyi.app.verdictRuleJudgeCriteria.domain.bo.BizVerdictRuleJudgeCriteriaBo;
import com.ruoyi.app.verdictRuleJudgeCriteria.service.IBizVerdictRuleJudgeCriteriaService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 裁决判断前置条件
 *
 * @author ccc
 * @date 2024-09-18
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/verdictRuleJudgeCriteria")
public class BizVerdictRuleJudgeCriteriaController extends BaseController {

    private final IBizVerdictRuleJudgeCriteriaService iBizVerdictRuleJudgeCriteriaService;

    /**
     * 查询裁决判断前置条件列表
     */
    @SaCheckPermission("app:verdictRuleJudgeCriteria:list")
    @GetMapping("/list")
    public TableDataInfo<BizVerdictRuleJudgeCriteriaVo> list(BizVerdictRuleJudgeCriteriaBo bo, PageQuery pageQuery) {
        return iBizVerdictRuleJudgeCriteriaService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出裁决判断前置条件列表
     */
    @SaCheckPermission("app:verdictRuleJudgeCriteria:export")
    @Log(title = "裁决判断前置条件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizVerdictRuleJudgeCriteriaBo bo, HttpServletResponse response) {
        List<BizVerdictRuleJudgeCriteriaVo> list = iBizVerdictRuleJudgeCriteriaService.queryList(bo);
        ExcelUtil.exportExcel(list, "裁决判断前置条件", BizVerdictRuleJudgeCriteriaVo.class, response);
    }

    /**
     * 获取裁决判断前置条件详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("app:verdictRuleJudgeCriteria:query")
    @GetMapping("/{id}")
    public R<BizVerdictRuleJudgeCriteriaVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizVerdictRuleJudgeCriteriaService.queryById(id));
    }
       @GetMapping("/detail/{verdictTypeId}")
    public R<BizVerdictRuleJudgeCriteriaVo> queryByVerdictTypeId(@NotNull(message = "裁决类型ID")
                                        @PathVariable Long verdictTypeId) {
        return R.ok(iBizVerdictRuleJudgeCriteriaService.queryByVerdictTypeId(verdictTypeId));
    }

    /**
     * 新增裁决判断前置条件
     */
    @SaCheckPermission("app:verdictRuleJudgeCriteria:add")
    @Log(title = "裁决判断前置条件", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizVerdictRuleJudgeCriteriaBo bo) {
        return toAjax(iBizVerdictRuleJudgeCriteriaService.insertByBo(bo));
    }

    /**
     * 修改裁决判断前置条件
     */
    @SaCheckPermission("app:verdictRuleJudgeCriteria:edit")
    @Log(title = "裁决判断前置条件", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizVerdictRuleJudgeCriteriaBo bo) {
        return toAjax(iBizVerdictRuleJudgeCriteriaService.updateByBo(bo));
    }

    /**
     * 删除裁决判断前置条件
     *
     * @param ids 主键串
     */
    @SaCheckPermission("app:verdictRuleJudgeCriteria:remove")
    @Log(title = "裁决判断前置条件", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizVerdictRuleJudgeCriteriaService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
