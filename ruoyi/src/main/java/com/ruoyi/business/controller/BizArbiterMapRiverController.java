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
import com.ruoyi.business.domain.vo.BizArbiterMapRiverVo;
import com.ruoyi.business.domain.bo.BizArbiterMapRiverBo;
import com.ruoyi.business.service.IBizArbiterMapRiverService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 地图河
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/arbiterMapRiver")
public class BizArbiterMapRiverController extends BaseController {

    private final IBizArbiterMapRiverService iBizArbiterMapRiverService;

    /**
     * 查询地图河列表
     */
    @SaCheckPermission("business:arbiterMapRiver:list")
    @GetMapping("/list")
    public TableDataInfo<BizArbiterMapRiverVo> list(BizArbiterMapRiverBo bo, PageQuery pageQuery) {
        return iBizArbiterMapRiverService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出地图河列表
     */
    @SaCheckPermission("business:arbiterMapRiver:export")
    @Log(title = "地图河", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizArbiterMapRiverBo bo, HttpServletResponse response) {
        List<BizArbiterMapRiverVo> list = iBizArbiterMapRiverService.queryList(bo);
        ExcelUtil.exportExcel(list, "地图河", BizArbiterMapRiverVo.class, response);
    }

    /**
     * 获取地图河详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:arbiterMapRiver:query")
    @GetMapping("/{id}")
    public R<BizArbiterMapRiverVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizArbiterMapRiverService.queryById(id));
    }

    /**
     * 新增地图河
     */
    @SaCheckPermission("business:arbiterMapRiver:add")
    @Log(title = "地图河", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizArbiterMapRiverBo bo) {
        return toAjax(iBizArbiterMapRiverService.insertByBo(bo));
    }

    /**
     * 修改地图河
     */
    @SaCheckPermission("business:arbiterMapRiver:edit")
    @Log(title = "地图河", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizArbiterMapRiverBo bo) {
        return toAjax(iBizArbiterMapRiverService.updateByBo(bo));
    }

    /**
     * 删除地图河
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:arbiterMapRiver:remove")
    @Log(title = "地图河", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizArbiterMapRiverService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
