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
import com.ruoyi.business.domain.vo.BizVerdictBlowEffectVo;
import com.ruoyi.business.domain.bo.BizVerdictBlowEffectBo;
import com.ruoyi.business.service.IBizVerdictBlowEffectService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 裁决打击效果
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/verdictBlowEffect")
public class BizVerdictBlowEffectController extends BaseController {

    private final IBizVerdictBlowEffectService iBizVerdictBlowEffectService;

    /**
     * 查询裁决打击效果列表
     */
    @SaCheckPermission("business:verdictBlowEffect:list")
    @GetMapping("/list")
    public TableDataInfo<BizVerdictBlowEffectVo> list(BizVerdictBlowEffectBo bo, PageQuery pageQuery) {
        return iBizVerdictBlowEffectService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出裁决打击效果列表
     */
    @SaCheckPermission("business:verdictBlowEffect:export")
    @Log(title = "裁决打击效果", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizVerdictBlowEffectBo bo, HttpServletResponse response) {
        List<BizVerdictBlowEffectVo> list = iBizVerdictBlowEffectService.queryList(bo);
        ExcelUtil.exportExcel(list, "裁决打击效果", BizVerdictBlowEffectVo.class, response);
    }

    /**
     * 获取裁决打击效果详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:verdictBlowEffect:query")
    @GetMapping("/{id}")
    public R<BizVerdictBlowEffectVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizVerdictBlowEffectService.queryById(id));
    }

    /**
     * 新增裁决打击效果
     */
    @SaCheckPermission("business:verdictBlowEffect:add")
    @Log(title = "裁决打击效果", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizVerdictBlowEffectBo bo) {
        return toAjax(iBizVerdictBlowEffectService.insertByBo(bo));
    }

    /**
     * 修改裁决打击效果
     */
    @SaCheckPermission("business:verdictBlowEffect:edit")
    @Log(title = "裁决打击效果", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizVerdictBlowEffectBo bo) {
        return toAjax(iBizVerdictBlowEffectService.updateByBo(bo));
    }

    /**
     * 删除裁决打击效果
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:verdictBlowEffect:remove")
    @Log(title = "裁决打击效果", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizVerdictBlowEffectService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
