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
import com.ruoyi.business.domain.vo.BizArbiterMapRoadVo;
import com.ruoyi.business.domain.bo.BizArbiterMapRoadBo;
import com.ruoyi.business.service.IBizArbiterMapRoadService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 地图路
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/arbiterMapRoad")
public class BizArbiterMapRoadController extends BaseController {

    private final IBizArbiterMapRoadService iBizArbiterMapRoadService;

    /**
     * 查询地图路列表
     */
    @SaCheckPermission("business:arbiterMapRoad:list")
    @GetMapping("/list")
    public TableDataInfo<BizArbiterMapRoadVo> list(BizArbiterMapRoadBo bo, PageQuery pageQuery) {
        return iBizArbiterMapRoadService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出地图路列表
     */
    @SaCheckPermission("business:arbiterMapRoad:export")
    @Log(title = "地图路", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizArbiterMapRoadBo bo, HttpServletResponse response) {
        List<BizArbiterMapRoadVo> list = iBizArbiterMapRoadService.queryList(bo);
        ExcelUtil.exportExcel(list, "地图路", BizArbiterMapRoadVo.class, response);
    }

    /**
     * 获取地图路详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:arbiterMapRoad:query")
    @GetMapping("/{id}")
    public R<BizArbiterMapRoadVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizArbiterMapRoadService.queryById(id));
    }

    /**
     * 新增地图路
     */
    @SaCheckPermission("business:arbiterMapRoad:add")
    @Log(title = "地图路", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizArbiterMapRoadBo bo) {
        return toAjax(iBizArbiterMapRoadService.insertByBo(bo));
    }

    /**
     * 修改地图路
     */
    @SaCheckPermission("business:arbiterMapRoad:edit")
    @Log(title = "地图路", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizArbiterMapRoadBo bo) {
        return toAjax(iBizArbiterMapRoadService.updateByBo(bo));
    }

    /**
     * 删除地图路
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:arbiterMapRoad:remove")
    @Log(title = "地图路", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizArbiterMapRoadService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
