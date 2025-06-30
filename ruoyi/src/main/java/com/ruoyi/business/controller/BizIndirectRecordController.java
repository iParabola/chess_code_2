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
import com.ruoyi.business.domain.vo.BizIndirectRecordVo;
import com.ruoyi.business.domain.bo.BizIndirectRecordBo;
import com.ruoyi.business.service.IBizIndirectRecordService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 间瞄记录
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/indirectRecord")
public class BizIndirectRecordController extends BaseController {

    private final IBizIndirectRecordService iBizIndirectRecordService;

    /**
     * 查询间瞄记录列表
     */
    @SaCheckPermission("business:indirectRecord:list")
    @GetMapping("/list")
    public TableDataInfo<BizIndirectRecordVo> list(BizIndirectRecordBo bo, PageQuery pageQuery) {
        return iBizIndirectRecordService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出间瞄记录列表
     */
    @SaCheckPermission("business:indirectRecord:export")
    @Log(title = "间瞄记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizIndirectRecordBo bo, HttpServletResponse response) {
        List<BizIndirectRecordVo> list = iBizIndirectRecordService.queryList(bo);
        ExcelUtil.exportExcel(list, "间瞄记录", BizIndirectRecordVo.class, response);
    }

    /**
     * 获取间瞄记录详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:indirectRecord:query")
    @GetMapping("/{id}")
    public R<BizIndirectRecordVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizIndirectRecordService.queryById(id));
    }

    /**
     * 新增间瞄记录
     */
    @SaCheckPermission("business:indirectRecord:add")
    @Log(title = "间瞄记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizIndirectRecordBo bo) {
        return toAjax(iBizIndirectRecordService.insertByBo(bo));
    }

    /**
     * 修改间瞄记录
     */
    @SaCheckPermission("business:indirectRecord:edit")
    @Log(title = "间瞄记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizIndirectRecordBo bo) {
        return toAjax(iBizIndirectRecordService.updateByBo(bo));
    }

    /**
     * 删除间瞄记录
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:indirectRecord:remove")
    @Log(title = "间瞄记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizIndirectRecordService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
