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
import com.ruoyi.business.domain.vo.BizArbiterMapLegendVo;
import com.ruoyi.business.domain.bo.BizArbiterMapLegendBo;
import com.ruoyi.business.service.IBizArbiterMapLegendService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 地图图例
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/arbiterMapLegend")
public class BizArbiterMapLegendController extends BaseController {

    private final IBizArbiterMapLegendService iBizArbiterMapLegendService;

    /**
     * 查询地图图例列表
     */
    @SaCheckPermission("business:arbiterMapLegend:list")
    @GetMapping("/list")
    public TableDataInfo<BizArbiterMapLegendVo> list(BizArbiterMapLegendBo bo, PageQuery pageQuery) {
        return iBizArbiterMapLegendService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出地图图例列表
     */
    @SaCheckPermission("business:arbiterMapLegend:export")
    @Log(title = "地图图例", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizArbiterMapLegendBo bo, HttpServletResponse response) {
        List<BizArbiterMapLegendVo> list = iBizArbiterMapLegendService.queryList(bo);
        ExcelUtil.exportExcel(list, "地图图例", BizArbiterMapLegendVo.class, response);
    }

    /**
     * 获取地图图例详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:arbiterMapLegend:query")
    @GetMapping("/{id}")
    public R<BizArbiterMapLegendVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizArbiterMapLegendService.queryById(id));
    }

    /**
     * 新增地图图例
     */
    @SaCheckPermission("business:arbiterMapLegend:add")
    @Log(title = "地图图例", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizArbiterMapLegendBo bo) {
        return toAjax(iBizArbiterMapLegendService.insertByBo(bo));
    }

    /**
     * 修改地图图例
     */
    @SaCheckPermission("business:arbiterMapLegend:edit")
    @Log(title = "地图图例", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizArbiterMapLegendBo bo) {
        return toAjax(iBizArbiterMapLegendService.updateByBo(bo));
    }

    /**
     * 删除地图图例
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:arbiterMapLegend:remove")
    @Log(title = "地图图例", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizArbiterMapLegendService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
