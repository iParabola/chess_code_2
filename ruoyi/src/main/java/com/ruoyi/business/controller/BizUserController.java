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
import com.ruoyi.business.domain.vo.BizUserVo;
import com.ruoyi.business.domain.bo.BizUserBo;
import com.ruoyi.business.service.IBizUserService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户管理
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/user")
public class BizUserController extends BaseController {

    private final IBizUserService iBizUserService;

    /**
     * 查询用户管理列表
     */
    @SaCheckPermission("business:user:list")
    @GetMapping("/list")
    public TableDataInfo<BizUserVo> list(BizUserBo bo, PageQuery pageQuery) {
        return iBizUserService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出用户管理列表
     */
    @SaCheckPermission("business:user:export")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizUserBo bo, HttpServletResponse response) {
        List<BizUserVo> list = iBizUserService.queryList(bo);
        ExcelUtil.exportExcel(list, "用户管理", BizUserVo.class, response);
    }

    /**
     * 获取用户管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:user:query")
    @GetMapping("/{id}")
    public R<BizUserVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizUserService.queryById(id));
    }

    /**
     * 新增用户管理
     */
    @SaCheckPermission("business:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizUserBo bo) {
        return toAjax(iBizUserService.insertByBo(bo));
    }

    /**
     * 修改用户管理
     */
    @SaCheckPermission("business:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizUserBo bo) {
        return toAjax(iBizUserService.updateByBo(bo));
    }

    /**
     * 删除用户管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizUserService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
