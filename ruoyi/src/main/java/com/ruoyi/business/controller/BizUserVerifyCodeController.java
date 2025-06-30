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
import com.ruoyi.business.domain.vo.BizUserVerifyCodeVo;
import com.ruoyi.business.domain.bo.BizUserVerifyCodeBo;
import com.ruoyi.business.service.IBizUserVerifyCodeService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户验证码
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/userVerifyCode")
public class BizUserVerifyCodeController extends BaseController {

    private final IBizUserVerifyCodeService iBizUserVerifyCodeService;

    /**
     * 查询用户验证码列表
     */
    @SaCheckPermission("business:userVerifyCode:list")
    @GetMapping("/list")
    public TableDataInfo<BizUserVerifyCodeVo> list(BizUserVerifyCodeBo bo, PageQuery pageQuery) {
        return iBizUserVerifyCodeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出用户验证码列表
     */
    @SaCheckPermission("business:userVerifyCode:export")
    @Log(title = "用户验证码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizUserVerifyCodeBo bo, HttpServletResponse response) {
        List<BizUserVerifyCodeVo> list = iBizUserVerifyCodeService.queryList(bo);
        ExcelUtil.exportExcel(list, "用户验证码", BizUserVerifyCodeVo.class, response);
    }

    /**
     * 获取用户验证码详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:userVerifyCode:query")
    @GetMapping("/{id}")
    public R<BizUserVerifyCodeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizUserVerifyCodeService.queryById(id));
    }

    /**
     * 新增用户验证码
     */
    @SaCheckPermission("business:userVerifyCode:add")
    @Log(title = "用户验证码", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizUserVerifyCodeBo bo) {
        return toAjax(iBizUserVerifyCodeService.insertByBo(bo));
    }

    /**
     * 修改用户验证码
     */
    @SaCheckPermission("business:userVerifyCode:edit")
    @Log(title = "用户验证码", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizUserVerifyCodeBo bo) {
        return toAjax(iBizUserVerifyCodeService.updateByBo(bo));
    }

    /**
     * 删除用户验证码
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:userVerifyCode:remove")
    @Log(title = "用户验证码", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizUserVerifyCodeService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
