package com.ruoyi.business.api;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.domain.vo.SysOssVo;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysDictTypeService;
import com.ruoyi.system.service.ISysOssService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统配置
 *
 * @author ruoyi
 * @date 2024-01-05
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/system")
@Tag(name = "API系统配置")
public class ApiSystemController extends BaseController {

    private final ISysConfigService configService;

    private final ISysDictTypeService dictTypeService;

    private final ISysOssService iSysOssService;

    /**
     * 根据参数键名查询参数值
     *
     * @param configKey 参数Key
     */
    @GetMapping(value = "/getConfigKey/{configKey}")
    public R<Void> getConfigKey(@PathVariable String configKey) {
        return R.ok(configService.selectConfigByKey(configKey));
    }

    /**
     * 根据ossid获取文件
     *
     * @param ossId
     * @return
     */
    @GetMapping("/getOssById/{ossId}")
    public R<SysOssVo> getOssById(@PathVariable Long ossId) {
        SysOssVo sysOssVo = iSysOssService.getById(ossId);
        return R.ok(sysOssVo);
    }

    /**
     * 获取数据字典
     *
     * @param dictType
     * @return
     */
    @GetMapping(value = "/getDataByDictType/{dictType}")
    public R<List<SysDictData>> getDataByDictType(@PathVariable String dictType) {
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (ObjectUtil.isNull(data)) {
            data = new ArrayList<>();
        }
        return R.ok(data);
    }

    /**
     * 文件上传接口
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<Map<String, String>> upload(@RequestPart("file") MultipartFile file) {
        if (ObjectUtil.isNull(file)) {
            throw new ServiceException("上传文件不能为空");
        }
        SysOssVo oss = iSysOssService.upload(file);
        Map<String, String> map = new HashMap<>(2);
        map.put("url", oss.getUrl());
        map.put("fileName", oss.getOriginalName());
        map.put("ossId", oss.getOssId().toString());
        return R.ok(map);
    }
}
