package com.ruoyi.business.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.websocket.dto.WebSocketMessageDTO;
import com.ruoyi.common.websocket.utils.WebSocketUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * WebSocket 演示案例
 *
 * @author zendwang
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/demo/websocket")
@Slf4j
public class WeSocketController {

    /**
     * 发布消息
     *
     * @param dto 发送内容
     */
    @SaIgnore
    @GetMapping("/send")
    public R<Void> send(WebSocketMessageDTO dto) throws InterruptedException {
//        WebSocketUtils.publishMessage(dto);
        WebSocketUtils.publishAll("测试");
        return R.ok("操作成功");
    }
}
