package com.ruoyi.common.websocket.handler;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.websocket.holder.WebSocketSessionHolder;
import com.ruoyi.common.websocket.service.WebSocketService;
import com.ruoyi.common.websocket.utils.WebSocketUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import static com.ruoyi.common.websocket.constant.WebSocketConstants.*;

/**
 * WebSocketHandler 实现类
 *
 * @author zendwang
 */
@Slf4j
public class PlusWebSocketHandler extends AbstractWebSocketHandler {

    @Autowired
    private WebSocketService webSocketService;

    /**
     * 连接成功后
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        LoginUser loginUser = (LoginUser) session.getAttributes().get(LOGIN_USER_KEY);
        WebSocketSessionHolder.addSession(loginUser.getUserId(), session);
        log.info("[connect] sessionId: {},userId:{},userType:{}", session.getId(), loginUser.getUserId(), loginUser.getUserType());
    }

    /**
     * 处理发送来的文本消息
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        LoginUser loginUser;
        String msg = message.getPayload();
        JSONObject jsonObject = JSON.parseObject(msg);
        String action = jsonObject.getString("action");
        Long verdictRecordId = jsonObject.getLong("verdictRecordId");
        Object data = jsonObject.get("data");
        if (StringUtils.isNotBlank(action)) {
            switch (action) {
                case JOIN_ROOM:
                    // 加入房间
                    loginUser = (LoginUser) session.getAttributes().get(LOGIN_USER_KEY);
                    webSocketService.joinRoom(loginUser.getUserId(), verdictRecordId);
                    break;
                case WATCH_JOIN_ROOM:
                    // 加入房间
                    loginUser = (LoginUser) session.getAttributes().get(LOGIN_USER_KEY);
                    webSocketService.watchJoinRoom(loginUser.getUserId(), verdictRecordId);
                    break;
                case LEAVE_ROOM:
                    // 加入房间
                    loginUser = (LoginUser) session.getAttributes().get(LOGIN_USER_KEY);
                    webSocketService.leaveRoom(session);
                    break;
                case PING:
                    // 心跳
                    WebSocketUtils.sendMessage(session, "pong," + data);
                    break;
                default:
                    if (StringUtils.isNotBlank(action)) {
                        // 转发消息
                        webSocketService.sendMsg(verdictRecordId, action, data);
                    }
            }
        }

    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        super.handleBinaryMessage(session, message);
    }

    /**
     * 心跳监测的回复
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        WebSocketUtils.sendPongMessage(session);
    }

    /**
     * 连接出错时
     *
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("[transport error] sessionId: {} , exception:{}", session.getId(), exception.getMessage());
    }

    /**
     * 连接关闭后
     *
     * @param session
     * @param status
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        LoginUser loginUser = (LoginUser) session.getAttributes().get(LOGIN_USER_KEY);
        WebSocketSessionHolder.removeSession(loginUser.getUserId());
//        webSocketService.leaveRoom(session);
        log.info("[disconnect] sessionId: {},userId:{},userType:{}", session.getId(), loginUser.getUserId(), loginUser.getUserType());
    }

    /**
     * 是否支持分片消息
     *
     * @return
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
