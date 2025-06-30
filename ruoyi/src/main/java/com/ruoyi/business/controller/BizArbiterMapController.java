package com.ruoyi.business.controller;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.business.domain.dto.BatchSaveArbiterMapCoordinateDto;
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
import com.ruoyi.business.domain.vo.BizArbiterMapVo;
import com.ruoyi.business.domain.bo.BizArbiterMapBo;
import com.ruoyi.business.service.IBizArbiterMapService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 地图管理
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/arbiterMap")
public class BizArbiterMapController extends BaseController {

    private final IBizArbiterMapService iBizArbiterMapService;

    /**
     * 查询地图管理列表
     */
    @SaCheckPermission("business:arbiterMap:list")
    @GetMapping("/list")
    public TableDataInfo<BizArbiterMapVo> list(BizArbiterMapBo bo, PageQuery pageQuery) {
        return iBizArbiterMapService.queryPageList(bo, pageQuery);
    }


    @GetMapping("/queryArbiterMapList")
    public List<BizArbiterMapVo> queryArbiterMapList(BizArbiterMapBo bo) {
        return iBizArbiterMapService.queryList(bo);
    }
    /**
     * 导出地图管理列表
     */
    @SaCheckPermission("business:arbiterMap:export")
    @Log(title = "地图管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizArbiterMapBo bo, HttpServletResponse response) {
        List<BizArbiterMapVo> list = iBizArbiterMapService.queryList(bo);
        ExcelUtil.exportExcel(list, "地图管理", BizArbiterMapVo.class, response);
    }

    /**
     * 获取地图管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:arbiterMap:query")
    @GetMapping("/{id}")
    public R<BizArbiterMapVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizArbiterMapService.queryById(id));
    }

    /**
     * 新增地图管理
     */
    @SaCheckPermission("business:arbiterMap:add")
    @Log(title = "地图管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizArbiterMapBo bo) {
        return toAjax(iBizArbiterMapService.insertByBo(bo));
    }

    /**
     * 修改地图管理
     */
    @SaCheckPermission("business:arbiterMap:edit")
    @Log(title = "地图管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizArbiterMapBo bo) {
        return toAjax(iBizArbiterMapService.updateByBo(bo));
    }

    /**
     * 删除地图管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:arbiterMap:remove")
    @Log(title = "地图管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizArbiterMapService.deleteWithValidByIds(Arrays.asList(ids), true));
    }

    /**
     * 批量新增地图坐标
     * @param batchSaveArbiterMapCoordinateDto
     * @return
     */
    @Log(title ="批量新增地图坐标")
    @PostMapping("/batchSaveArbiterMapCoordinate")
    @RepeatSubmit
    public R<String> batchSaveArbiterMapCoordinate( @RequestBody BatchSaveArbiterMapCoordinateDto batchSaveArbiterMapCoordinateDto) {
        return R.ok(iBizArbiterMapService.batchSaveArbiterMapCoordinate(batchSaveArbiterMapCoordinateDto));
    }
}
