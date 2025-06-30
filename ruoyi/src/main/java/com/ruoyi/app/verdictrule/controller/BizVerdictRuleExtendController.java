package com.ruoyi.app.verdictrule.controller;

import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.lang.tree.Tree;
import com.ruoyi.app.verdictrule.domain.bo.BizVerdictRuleExtendBo;
import com.ruoyi.app.verdictrule.domain.vo.BizVerdictRuleExtendVo;
import com.ruoyi.app.verdictrule.service.IBizVerdictRuleExtendService;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
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

/**
 * 裁决表管理
 *
 * @author ruoyi
 * @date 2024-08-18
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/verdictRuleExtend")
public class BizVerdictRuleExtendController extends BaseController {

    private final IBizVerdictRuleExtendService iBizVerdictRuleExtendService;

    /**
     * 查询裁决表管理列表
     */
    @SaCheckPermission("business:verdictRule:list")
    @GetMapping("/list")
    public R<List<BizVerdictRuleExtendVo>> list(BizVerdictRuleExtendBo bo) {
        List<BizVerdictRuleExtendVo> list = iBizVerdictRuleExtendService.queryList(bo);
        return R.ok(list);
    }
    /**
         * 查询裁决表管理列表
         */
    @GetMapping("/listAll")
    public R<List<BizVerdictRuleExtendVo>> listAll(BizVerdictRuleExtendBo bo) {
        return R.ok(iBizVerdictRuleExtendService.queryAllList(bo));
    }

    /**
     * 导出裁决表管理列表
     */
    @SaCheckPermission("business:verdictRule:export")
    @Log(title = "裁决表管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizVerdictRuleExtendBo bo, HttpServletResponse response) {
        List<BizVerdictRuleExtendVo> list = iBizVerdictRuleExtendService.queryList(bo);
        ExcelUtil.exportExcel(list, "裁决表管理", BizVerdictRuleExtendVo.class, response);
    }

    /**
     * 获取裁决表管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:verdictRule:query")
    @GetMapping("/{id}")
    public R<BizVerdictRuleExtendVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizVerdictRuleExtendService.queryById(id));
    }

    /**
     * 新增裁决表管理
     */
    @SaCheckPermission("business:verdictRule:add")
    @Log(title = "裁决表管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizVerdictRuleExtendBo bo) {
        return toAjax(iBizVerdictRuleExtendService.insertByBo(bo));
    }

    /**
     * 修改裁决表管理
     */
    @SaCheckPermission("business:verdictRule:edit")
    @Log(title = "裁决表管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizVerdictRuleExtendBo bo) {
        return toAjax(iBizVerdictRuleExtendService.updateByBo(bo));
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
        return toAjax(iBizVerdictRuleExtendService.deleteWithValidByIds(Arrays.asList(ids), true));
    }

    @GetMapping("/dimension/{verdictRuleId}")
    public R<Map<String,Object>> getDimensionWithValueByVerdictRuleId(@NotNull(message = "主键不能为空")
                                         @PathVariable Long verdictRuleId) {
         return R.ok(iBizVerdictRuleExtendService.getDimensionWithValueByVerdictRuleId(verdictRuleId));
    }
}
