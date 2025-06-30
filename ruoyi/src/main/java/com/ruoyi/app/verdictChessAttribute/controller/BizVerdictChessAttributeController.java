package com.ruoyi.app.verdictChessAttribute.controller;

import java.util.List;
import java.util.Arrays;

import com.ruoyi.business.domain.dto.BatchSaveVerdictChessAttributeDto;
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
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.app.verdictChessAttribute.domain.vo.BizVerdictChessAttributeVo;
import com.ruoyi.app.verdictChessAttribute.domain.bo.BizVerdictChessAttributeBo;
import com.ruoyi.app.verdictChessAttribute.service.IBizVerdictChessAttributeService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 裁决棋子属性表
 *
 * @author ruoyi
 * @date 2024-08-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/verdictChessAttribute/verdictChessAttribute")
public class BizVerdictChessAttributeController extends BaseController {

    private final IBizVerdictChessAttributeService iBizVerdictChessAttributeService;

    /**
     * 查询裁决棋子属性表列表
     */
    @SaCheckPermission("verdictChessAttribute:verdictChessAttribute:list")
    @GetMapping("/list")
    public TableDataInfo<BizVerdictChessAttributeVo> list(BizVerdictChessAttributeBo bo, PageQuery pageQuery) {
        return iBizVerdictChessAttributeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出裁决棋子属性表列表
     */
    @SaCheckPermission("verdictChessAttribute:verdictChessAttribute:export")
    @Log(title = "裁决棋子属性表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizVerdictChessAttributeBo bo, HttpServletResponse response) {
        List<BizVerdictChessAttributeVo> list = iBizVerdictChessAttributeService.queryList(bo);
        ExcelUtil.exportExcel(list, "裁决棋子属性表", BizVerdictChessAttributeVo.class, response);
    }

    /**
     * 获取裁决棋子属性表详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("verdictChessAttribute:verdictChessAttribute:query")
    @GetMapping("/{id}")
    public R<BizVerdictChessAttributeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizVerdictChessAttributeService.queryById(id));
    }

    /**
     * 新增裁决棋子属性表
     */
    @SaCheckPermission("verdictChessAttribute:verdictChessAttribute:add")
    @Log(title = "裁决棋子属性表", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizVerdictChessAttributeBo bo) {
        return toAjax(iBizVerdictChessAttributeService.insertByBo(bo));
    }

    /**
     * 修改裁决棋子属性表
     */
    @SaCheckPermission("verdictChessAttribute:verdictChessAttribute:edit")
    @Log(title = "裁决棋子属性表", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizVerdictChessAttributeBo bo) {
        return toAjax(iBizVerdictChessAttributeService.updateByBo(bo));
    }

    /**
     * 删除裁决棋子属性表
     *
     * @param ids 主键串
     */
    @SaCheckPermission("verdictChessAttribute:verdictChessAttribute:remove")
    @Log(title = "裁决棋子属性表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizVerdictChessAttributeService.deleteWithValidByIds(Arrays.asList(ids), true));
    }

    /**
     * 批量选择棋子属性定义引用
     * @param batchSaveVerdictChessAttributeDto
     * @return
     */
    @Log(title ="批量新增地图坐标")
    @PostMapping("/batchSaveVerdictChessAttribute")
    @RepeatSubmit
    public R<String> batchSaveVerdictChessAttribute( @RequestBody BatchSaveVerdictChessAttributeDto batchSaveVerdictChessAttributeDto) {
        return R.ok(iBizVerdictChessAttributeService.batchSaveVerdictChessAttribute(batchSaveVerdictChessAttributeDto));
    }
}
