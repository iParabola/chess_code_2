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
import com.ruoyi.business.domain.vo.BizIndirectBlowEffectVo;
import com.ruoyi.business.domain.bo.BizIndirectBlowEffectBo;
import com.ruoyi.business.service.IBizIndirectBlowEffectService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 间瞄打击效果
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/indirectBlowEffect")
public class BizIndirectBlowEffectController extends BaseController {

    private final IBizIndirectBlowEffectService iBizIndirectBlowEffectService;

    /**
     * 查询间瞄打击效果列表
     */
    @SaCheckPermission("business:indirectBlowEffect:list")
    @GetMapping("/list")
    public TableDataInfo<BizIndirectBlowEffectVo> list(BizIndirectBlowEffectBo bo, PageQuery pageQuery) {
        return iBizIndirectBlowEffectService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出间瞄打击效果列表
     */
    @SaCheckPermission("business:indirectBlowEffect:export")
    @Log(title = "间瞄打击效果", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizIndirectBlowEffectBo bo, HttpServletResponse response) {
        List<BizIndirectBlowEffectVo> list = iBizIndirectBlowEffectService.queryList(bo);
        ExcelUtil.exportExcel(list, "间瞄打击效果", BizIndirectBlowEffectVo.class, response);
    }

    /**
     * 获取间瞄打击效果详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:indirectBlowEffect:query")
    @GetMapping("/{id}")
    public R<BizIndirectBlowEffectVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizIndirectBlowEffectService.queryById(id));
    }

    /**
     * 新增间瞄打击效果
     */
    @SaCheckPermission("business:indirectBlowEffect:add")
    @Log(title = "间瞄打击效果", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizIndirectBlowEffectBo bo) {
        return toAjax(iBizIndirectBlowEffectService.insertByBo(bo));
    }

    /**
     * 修改间瞄打击效果
     */
    @SaCheckPermission("business:indirectBlowEffect:edit")
    @Log(title = "间瞄打击效果", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizIndirectBlowEffectBo bo) {
        return toAjax(iBizIndirectBlowEffectService.updateByBo(bo));
    }

    /**
     * 删除间瞄打击效果
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:indirectBlowEffect:remove")
    @Log(title = "间瞄打击效果", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizIndirectBlowEffectService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
