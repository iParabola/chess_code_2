package com.ruoyi.common.websocket.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class RoomDTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 房间用户
     */
    private Map<String, Long> roomUserMap;

    /**
     * 当前行动用户id
     */
    private Long actionUserId;

    /**
     * 行动类型
     */
    private String action;


    /**
     * 消息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

}
