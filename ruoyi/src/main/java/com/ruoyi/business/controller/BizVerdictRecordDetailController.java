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
import com.ruoyi.business.domain.vo.BizVerdictRecordDetailVo;
import com.ruoyi.business.domain.bo.BizVerdictRecordDetailBo;
import com.ruoyi.business.service.IBizVerdictRecordDetailService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 裁决记录详情
 *
 * @author ruoyi
 * @date 2024-02-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/verdictRecordDetail")
public class BizVerdictRecordDetailController extends BaseController {

    private final IBizVerdictRecordDetailService iBizVerdictRecordDetailService;

    /**
     * 查询裁决记录详情列表
     */
    @SaCheckPermission("business:verdictRecordDetail:list")
    @GetMapping("/list")
    public TableDataInfo<BizVerdictRecordDetailVo> list(BizVerdictRecordDetailBo bo, PageQuery pageQuery) {
        return iBizVerdictRecordDetailService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出裁决记录详情列表
     */
    @SaCheckPermission("business:verdictRecordDetail:export")
    @Log(title = "裁决记录详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizVerdictRecordDetailBo bo, HttpServletResponse response) {
        List<BizVerdictRecordDetailVo> list = iBizVerdictRecordDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "裁决记录详情", BizVerdictRecordDetailVo.class, response);
    }

    /**
     * 获取裁决记录详情详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:verdictRecordDetail:query")
    @GetMapping("/{id}")
    public R<BizVerdictRecordDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizVerdictRecordDetailService.queryById(id));
    }

    /**
     * 新增裁决记录详情
     */
    @SaCheckPermission("business:verdictRecordDetail:add")
    @Log(title = "裁决记录详情", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizVerdictRecordDetailBo bo) {
        return toAjax(iBizVerdictRecordDetailService.insertByBo(bo));
    }

    /**
     * 修改裁决记录详情
     */
    @SaCheckPermission("business:verdictRecordDetail:edit")
    @Log(title = "裁决记录详情", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizVerdictRecordDetailBo bo) {
        return toAjax(iBizVerdictRecordDetailService.updateByBo(bo));
    }

    /**
     * 删除裁决记录详情
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:verdictRecordDetail:remove")
    @Log(title = "裁决记录详情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizVerdictRecordDetailService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
