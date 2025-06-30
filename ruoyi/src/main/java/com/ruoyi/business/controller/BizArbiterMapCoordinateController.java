package com.ruoyi.business.controller;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.business.domain.dto.MapImportDto;
import com.ruoyi.common.excel.ExcelResult;
import com.ruoyi.system.domain.vo.SysUserImportVo;
import com.ruoyi.system.listener.SysUserImportListener;
import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.http.MediaType;
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
import com.ruoyi.business.domain.vo.BizArbiterMapCoordinateVo;
import com.ruoyi.business.domain.bo.BizArbiterMapCoordinateBo;
import com.ruoyi.business.service.IBizArbiterMapCoordinateService;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 地图坐标
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/arbiterMapCoordinate")
public class BizArbiterMapCoordinateController extends BaseController {

    private final IBizArbiterMapCoordinateService iBizArbiterMapCoordinateService;

    /**
     * 查询地图坐标列表
     */
    @SaCheckPermission("business:arbiterMapCoordinate:list")
    @GetMapping("/list")
    public TableDataInfo<BizArbiterMapCoordinateVo> list(BizArbiterMapCoordinateBo bo, PageQuery pageQuery) {
        return iBizArbiterMapCoordinateService.queryPageList(bo, pageQuery);
    }

    @GetMapping("/queryArbiterMapCoordinateList")
    public List<BizArbiterMapCoordinateVo> queryArbiterMapCoordinateList(BizArbiterMapCoordinateBo bo) {
        return iBizArbiterMapCoordinateService.queryList(bo);
    }

    /**
     * 导出地图坐标列表
     */
    @SaCheckPermission("business:arbiterMapCoordinate:export")
    @Log(title = "地图坐标", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizArbiterMapCoordinateBo bo, HttpServletResponse response) {
        List<BizArbiterMapCoordinateVo> list = iBizArbiterMapCoordinateService.queryList(bo);
        ExcelUtil.exportExcel(list, "地图坐标", BizArbiterMapCoordinateVo.class, response);
    }

    /**
     * 获取地图坐标详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:arbiterMapCoordinate:query")
    @GetMapping("/{id}")
    public R<BizArbiterMapCoordinateVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizArbiterMapCoordinateService.queryById(id));
    }

    /**
     * 新增地图坐标
     */
    @SaCheckPermission("business:arbiterMapCoordinate:add")
    @Log(title = "地图坐标", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizArbiterMapCoordinateBo bo) {
        return toAjax(iBizArbiterMapCoordinateService.insertByBo(bo));
    }

    /**
     * 修改地图坐标
     */
    @SaCheckPermission("business:arbiterMapCoordinate:edit")
    @Log(title = "地图坐标", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizArbiterMapCoordinateBo bo) {
        return toAjax(iBizArbiterMapCoordinateService.updateByBo(bo));
    }

    /**
     * 删除地图坐标
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:arbiterMapCoordinate:remove")
    @Log(title = "地图坐标", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizArbiterMapCoordinateService.deleteWithValidByIds(Arrays.asList(ids), true));
    }


    @PostMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<Void> importData(@RequestPart("file") MultipartFile file,@RequestParam Long mapId) throws Exception {
        List<MapImportDto> result = ExcelUtil.importExcel(file.getInputStream(), MapImportDto.class);
        iBizArbiterMapCoordinateService.importData(result,mapId);
        return R.ok();
    }

}
