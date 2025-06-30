package com.ruoyi.common.websocket.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.ruoyi.business.domain.bo.BizVerdictRecordBo;
import com.ruoyi.business.domain.vo.BizUserVo;
import com.ruoyi.business.domain.vo.BizVerdictRecordVo;
import com.ruoyi.business.service.IBizUserService;
import com.ruoyi.business.service.IBizVerdictRecordService;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.common.websocket.dto.RoomDTO;
import com.ruoyi.common.websocket.dto.WebSocketMessageDTO;
import com.ruoyi.common.websocket.utils.WebSocketUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ruoyi.common.websocket.constant.WebSocketConstants.*;

@RequiredArgsConstructor
@Service
public class WebSocketService {
    private final IBizUserService iBizUserService;
    private final IBizVerdictRecordService iBizVerdictRecordService;

    /**
     * 加入房间
     *
     * @param userId
     * @param verdictRecordId
     */
    public void joinRoom(Long userId, Long verdictRecordId) {
        // Redis保存用户所在房间
        RedisUtils.setCacheObject(ROOM_USER_KEY + userId, verdictRecordId, Duration.ofSeconds(86400));
        // 获取房间信息
        RoomDTO<Object> roomDto = RedisUtils.getCacheObject(ROOM_KEY + verdictRecordId);
        Map<String, String> userNameMap = new HashMap<>();
        if (ObjectUtil.isNotNull(roomDto)) {
            Map<String, Long> roomUserMap = roomDto.getRoomUserMap();
            if (roomUserMap.containsKey("firstUserId")) {
                BizUserVo firstUserVO = iBizUserService.queryById(roomUserMap.get("firstUserId"));
                userNameMap.put("firstUserName", firstUserVO.getUserName());
            }
            if (roomUserMap.containsKey("firstJudgeUserId")) {
                BizUserVo firstJudgeUserVO = iBizUserService.queryById(roomUserMap.get("firstJudgeUserId"));
                userNameMap.put("firstJudgeUserName", firstJudgeUserVO.getUserName());
            }
            if (roomUserMap.containsKey("firstCommanderUserId")) {
                BizUserVo firstCommanderUserVO = iBizUserService.queryById(roomUserMap.get("firstCommanderUserId"));
                userNameMap.put("firstCommanderUserName", firstCommanderUserVO.getUserName());
            }
            if (roomUserMap.containsKey("secondUserId")) {
                BizUserVo secondUserVO = iBizUserService.queryById(roomUserMap.get("secondUserId"));
                userNameMap.put("secondUserName", secondUserVO.getUserName());
            }
            if (roomUserMap.containsKey("secondJudgeUserId")) {
                BizUserVo secondJudgeUserVO = iBizUserService.queryById(roomUserMap.get("secondJudgeUserId"));
                userNameMap.put("secondJudgeUserName", secondJudgeUserVO.getUserName());
            }
            if (roomUserMap.containsKey("secondCommanderUserId")) {
                BizUserVo secondCommanderUserVO = iBizUserService.queryById(roomUserMap.get("secondCommanderUserId"));
                userNameMap.put("secondCommanderUserName", secondCommanderUserVO.getUserName());
            }
        } else {
            System.out.println("RoomDTO is null for verdictRecordId: "+ verdictRecordId);

        }
        // 发送信息
        sendMsg(verdictRecordId, JOIN_ROOM, userNameMap);
    }


    public void watchJoinRoom(Long userId, Long verdictRecordId) {
        // Redis保存用户所在房间
        RedisUtils.setCacheObject(ROOM_USER_KEY + userId, verdictRecordId, Duration.ofSeconds(86400));
        // 获取房间信息
        RoomDTO<Object> roomDto = RedisUtils.getCacheObject(ROOM_KEY + verdictRecordId);
        Map<String, Long> userNameMap = roomDto.getRoomUserMap();
        userNameMap.put(String.valueOf(userId),userId);
        roomDto.setRoomUserMap(userNameMap);
//        sendMsg(verdictRecordId, WATCH_JOIN_ROOM, userNameMap);
        RedisUtils.setCacheObject(ROOM_KEY + verdictRecordId, roomDto, Duration.ofSeconds(86400));
    }

    /**
     * 离开房间
     *
     * @param session
     */
    public void leaveRoom(WebSocketSession session) {
        // TODO: 2024/3/3 开始游戏了就不能离开房间 掉线只能重连
        LoginUser loginUser = (LoginUser) session.getAttributes().get(LOGIN_USER_KEY);
        Long userId = loginUser.getUserId();
        Long verdictRecordId = RedisUtils.getCacheObject(ROOM_USER_KEY + userId);
        BizVerdictRecordVo bizVerdictRecordVo = iBizVerdictRecordService.queryById(verdictRecordId);

        // 更新房间用户信息
        if (userId.equals(bizVerdictRecordVo.getFirstUserId())) {
            bizVerdictRecordVo.setFirstUserId(null);
            if (ObjectUtil.isNull(bizVerdictRecordVo.getFirstJudgeUserId())&&ObjectUtil.isNull(bizVerdictRecordVo.getFirstCommanderUserId())) {
                bizVerdictRecordVo.setFirstCampId(null);
            }
        } else if (userId.equals(bizVerdictRecordVo.getFirstJudgeUserId())) {
            bizVerdictRecordVo.setFirstJudgeUserId(null);
            if (ObjectUtil.isNull(bizVerdictRecordVo.getFirstUserId())&&ObjectUtil.isNull(bizVerdictRecordVo.getFirstCommanderUserId())) {
                bizVerdictRecordVo.setFirstCampId(null);
            }
        } else if (userId.equals(bizVerdictRecordVo.getSecondUserId())) {
            bizVerdictRecordVo.setSecondUserId(null);
            if (ObjectUtil.isNull(bizVerdictRecordVo.getSecondJudgeUserId())&&ObjectUtil.isNull(bizVerdictRecordVo.getSecondCommanderUserId())) {
                bizVerdictRecordVo.setSecondCampId(null);
            }
        } else if (userId.equals(bizVerdictRecordVo.getSecondJudgeUserId())) {
            bizVerdictRecordVo.setSecondJudgeUserId(null);
            if (ObjectUtil.isNull(bizVerdictRecordVo.getSecondUserId())&&ObjectUtil.isNull(bizVerdictRecordVo.getSecondCommanderUserId())) {
                bizVerdictRecordVo.setSecondCampId(null);
            }
        } else if (userId.equals(bizVerdictRecordVo.getFirstCommanderUserId())) {
            bizVerdictRecordVo.setSecondJudgeUserId(null);
            if (ObjectUtil.isNull(bizVerdictRecordVo.getFirstUserId())&&ObjectUtil.isNull(bizVerdictRecordVo.getFirstJudgeUserId())) {
                bizVerdictRecordVo.setSecondCampId(null);
            }
        } else if (userId.equals(bizVerdictRecordVo.getSecondCommanderUserId())) {
            bizVerdictRecordVo.setSecondCommanderUserId(null);
            if (ObjectUtil.isNull(bizVerdictRecordVo.getSecondUserId())&&ObjectUtil.isNull(bizVerdictRecordVo.getSecondJudgeUserId())) {
                bizVerdictRecordVo.setSecondCampId(null);
            }
        }

        // 保存房间信息到缓存
        iBizVerdictRecordService.updateByBo(BeanUtil.toBean(bizVerdictRecordVo, BizVerdictRecordBo.class));
        iBizVerdictRecordService.saveToCache(verdictRecordId);

        // 更新 Redis 房间用户映射
        RedisUtils.deleteObject(ROOM_USER_KEY + userId);
        RoomDTO<Object> roomDto = RedisUtils.getCacheObject(ROOM_KEY + verdictRecordId);
        if (ObjectUtil.isNotNull(roomDto)) {
            Map<String, Long> roomUserMap = roomDto.getRoomUserMap();
            roomUserMap.remove(String.valueOf(userId));
            roomDto.setRoomUserMap(roomUserMap);
            RedisUtils.setCacheObject(ROOM_KEY + verdictRecordId, roomDto, Duration.ofSeconds(86400));
        }

        // 发送信息
        sendMsg(verdictRecordId, LEAVE_ROOM);
    }

    /**
     * @param verdictRecordId
     * @param action
     * @param <T>
     */
    public <T> void sendMsg(Long verdictRecordId, String action) {
        sendMsg(verdictRecordId, action, null, "");
    }

    /**
     * @param verdictRecordId
     * @param action
     * @param data
     * @param <T>
     */
    public <T> void sendMsg(Long verdictRecordId, String action, T data) {
        sendMsg(verdictRecordId, action, data, "");
    }

    /**
     * @param verdictRecordId
     * @param action
     * @param data
     * @param msg
     * @param <T>
     */
    public <T> void sendMsg(Long verdictRecordId, String action, T data, String msg) {
        RoomDTO<Object> roomDto = RedisUtils.getCacheObject(ROOM_KEY + verdictRecordId);
        if (ObjectUtil.isEmpty(roomDto)) {
            // 读到缓存
            iBizVerdictRecordService.saveToCache(verdictRecordId);
            roomDto = RedisUtils.getCacheObject(ROOM_KEY + verdictRecordId);
            if (ObjectUtil.isEmpty(roomDto)) {
                System.out.println("RoomDTO is null after attempting to save to cache for verdictRecordId: "+ verdictRecordId);
                return;
            }
        }
        roomDto.setAction(action);
        roomDto.setMsg(msg);
        roomDto.setData(data);
        // 房间内用户id集合
        List<Long> userIds = new ArrayList<>(roomDto.getRoomUserMap().values());
        final SerializeConfig config = new SerializeConfig();
        config.put(Long.class, ToStringSerializer.instance);
        String wsMsg = JSON.toJSONString(roomDto, config);
        sendMsg(userIds, wsMsg);
    }

    /**
     * 发布消息
     *
     * @param userIds
     * @param msg
     */
    public void sendMsg(List<Long> userIds, String msg) {
        WebSocketMessageDTO webSocketMessageDto = new WebSocketMessageDTO();
        webSocketMessageDto.setSessionKeys(userIds);
        webSocketMessageDto.setMessage(msg);
        WebSocketUtils.publishMessage(webSocketMessageDto);
    }
}
