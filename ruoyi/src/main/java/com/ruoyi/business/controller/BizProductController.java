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
import com.ruoyi.business.domain.vo.BizProductVo;
import com.ruoyi.business.domain.bo.BizProductBo;
import com.ruoyi.business.service.IBizProductService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品管理
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/product")
public class BizProductController extends BaseController {

    private final IBizProductService iBizProductService;

    /**
     * 查询产品管理列表
     */
    @SaCheckPermission("business:product:list")
    @GetMapping("/list")
    public TableDataInfo<BizProductVo> list(BizProductBo bo, PageQuery pageQuery) {
        return iBizProductService.queryPageList(bo, pageQuery);
    }
    @GetMapping("/queryProductList")
    public List<BizProductVo> queryProductList(BizProductBo bo) {
        return iBizProductService.queryList(bo);
    }


    /**
     * 导出产品管理列表
     */
    @SaCheckPermission("business:product:export")
    @Log(title = "产品管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizProductBo bo, HttpServletResponse response) {
        List<BizProductVo> list = iBizProductService.queryList(bo);
        ExcelUtil.exportExcel(list, "产品管理", BizProductVo.class, response);
    }

    /**
     * 获取产品管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:product:query")
    @GetMapping("/{id}")
    public R<BizProductVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizProductService.queryById(id));
    }

    /**
     * 新增产品管理
     */
    @SaCheckPermission("business:product:add")
    @Log(title = "产品管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizProductBo bo) {
        return toAjax(iBizProductService.insertByBo(bo));
    }

    /**
     * 修改产品管理
     */
    @SaCheckPermission("business:product:edit")
    @Log(title = "产品管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizProductBo bo) {
        return toAjax(iBizProductService.updateByBo(bo));
    }

    /**
     * 删除产品管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:product:remove")
    @Log(title = "产品管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizProductService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
