package com.ruoyi.app.verdicttype.controller;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.lang.tree.Tree;
import com.ruoyi.app.verdicttype.domain.BizVerdictType;
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
import com.ruoyi.app.verdicttype.domain.vo.BizVerdictTypeVo;
import com.ruoyi.app.verdicttype.domain.bo.BizVerdictTypeBo;
import com.ruoyi.app.verdicttype.service.IBizVerdictTypeService;

/**
 * 裁决类型
 *
 * @author ruoyi
 * @date 2024-08-10
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/verdictType")
public class BizVerdictTypeController extends BaseController {

    private final IBizVerdictTypeService iBizVerdictTypeService;

    /**
     * 查询裁决类型列表
     */
    @SaCheckPermission("app:verdictType:list")
    @GetMapping("/list")
    public R<List<BizVerdictTypeVo>> list(BizVerdictTypeBo bo) {
        List<BizVerdictTypeVo> list = iBizVerdictTypeService.queryList(bo);
        return R.ok(list);
    }

    /**
     * 导出裁决类型列表
     */
    @SaCheckPermission("app:verdictType:export")
    @Log(title = "裁决类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizVerdictTypeBo bo, HttpServletResponse response) {
        List<BizVerdictTypeVo> list = iBizVerdictTypeService.queryList(bo);
        ExcelUtil.exportExcel(list, "裁决类型", BizVerdictTypeVo.class, response);
    }

    /**
     * 获取裁决类型详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("app:verdictType:query")
    @GetMapping("/{id}")
    public R<BizVerdictTypeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizVerdictTypeService.queryById(id));
    }

    /**
     * 新增裁决类型
     */
    @SaCheckPermission("app:verdictType:add")
    @Log(title = "裁决类型", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizVerdictTypeBo bo) {
        return toAjax(iBizVerdictTypeService.insertByBo(bo));
    }

    /**
     * 修改裁决类型
     */
    @SaCheckPermission("app:verdictType:edit")
    @Log(title = "裁决类型", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizVerdictTypeBo bo) {
        return toAjax(iBizVerdictTypeService.updateByBo(bo));
    }

    /**
     * 删除裁决类型
     *
     * @param ids 主键串
     */
    @SaCheckPermission("app:verdictType:remove")
    @Log(title = "裁决类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizVerdictTypeService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
    /**
     *获取裁决分类树
     */
    @GetMapping("/bizVerdictTypeTree")
    public R<List<Tree<Long>>> bizVerdictTypeTree(BizVerdictType bizVerdictType) {
            return R.ok(iBizVerdictTypeService.selectBizVerdictTreeList(bizVerdictType));
        }
}
