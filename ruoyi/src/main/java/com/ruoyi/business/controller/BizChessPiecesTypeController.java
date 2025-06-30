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
import com.ruoyi.business.domain.vo.BizChessPiecesTypeVo;
import com.ruoyi.business.domain.bo.BizChessPiecesTypeBo;
import com.ruoyi.business.service.IBizChessPiecesTypeService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 棋子类型
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/chessPiecesType")
public class BizChessPiecesTypeController extends BaseController {

    private final IBizChessPiecesTypeService iBizChessPiecesTypeService;

    /**
     * 查询棋子类型列表
     */
    @SaCheckPermission("business:chessPiecesType:list")
    @GetMapping("/list")
    public TableDataInfo<BizChessPiecesTypeVo> list(BizChessPiecesTypeBo bo, PageQuery pageQuery) {
        return iBizChessPiecesTypeService.queryPageList(bo, pageQuery);
    }
    @GetMapping("/queryChessPiecesTypeList")
    public List<BizChessPiecesTypeVo> queryChessPiecesTypeList(BizChessPiecesTypeBo bo) {
        return iBizChessPiecesTypeService.queryList(bo);
    }


    /**
     * 导出棋子类型列表
     */
    @SaCheckPermission("business:chessPiecesType:export")
    @Log(title = "棋子类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizChessPiecesTypeBo bo, HttpServletResponse response) {
        List<BizChessPiecesTypeVo> list = iBizChessPiecesTypeService.queryList(bo);
        ExcelUtil.exportExcel(list, "棋子类型", BizChessPiecesTypeVo.class, response);
    }

    /**
     * 获取棋子类型详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:chessPiecesType:query")
    @GetMapping("/{id}")
    public R<BizChessPiecesTypeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizChessPiecesTypeService.queryById(id));
    }

    /**
     * 新增棋子类型
     */
    @SaCheckPermission("business:chessPiecesType:add")
    @Log(title = "棋子类型", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizChessPiecesTypeBo bo) {
        return toAjax(iBizChessPiecesTypeService.insertByBo(bo));
    }
    @PostMapping("/saveChessPiecesType")
    public R<Void> saveChessPiecesType( @RequestBody BizChessPiecesTypeBo bo) {
        return toAjax(iBizChessPiecesTypeService.insertByBo(bo));
    }

    /**
     * 修改棋子类型
     */
    @SaCheckPermission("business:chessPiecesType:edit")
    @Log(title = "棋子类型", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizChessPiecesTypeBo bo) {
        return toAjax(iBizChessPiecesTypeService.updateByBo(bo));
    }

    /**
     * 删除棋子类型
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:chessPiecesType:remove")
    @Log(title = "棋子类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizChessPiecesTypeService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
