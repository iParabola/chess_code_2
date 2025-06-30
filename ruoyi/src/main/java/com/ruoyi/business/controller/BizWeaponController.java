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
import com.ruoyi.business.domain.vo.BizWeaponVo;
import com.ruoyi.business.domain.bo.BizWeaponBo;
import com.ruoyi.business.service.IBizWeaponService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 武器管理
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/weapon")
public class BizWeaponController extends BaseController {

    private final IBizWeaponService iBizWeaponService;

    /**
     * 查询武器管理列表
     */
    @SaCheckPermission("business:weapon:list")
    @GetMapping("/list")
    public TableDataInfo<BizWeaponVo> list(BizWeaponBo bo, PageQuery pageQuery) {
        return iBizWeaponService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出武器管理列表
     */
    @SaCheckPermission("business:weapon:export")
    @Log(title = "武器管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizWeaponBo bo, HttpServletResponse response) {
        List<BizWeaponVo> list = iBizWeaponService.queryList(bo);
        ExcelUtil.exportExcel(list, "武器管理", BizWeaponVo.class, response);
    }

    /**
     * 获取武器管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:weapon:query")
    @GetMapping("/{id}")
    public R<BizWeaponVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizWeaponService.queryById(id));
    }

    /**
     * 新增武器管理
     */
    @SaCheckPermission("business:weapon:add")
    @Log(title = "武器管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizWeaponBo bo) {
        return toAjax(iBizWeaponService.insertByBo(bo));
    }

    /**
     * 修改武器管理
     */
    @SaCheckPermission("business:weapon:edit")
    @Log(title = "武器管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizWeaponBo bo) {
        return toAjax(iBizWeaponService.updateByBo(bo));
    }

    /**
     * 删除武器管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:weapon:remove")
    @Log(title = "武器管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizWeaponService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
