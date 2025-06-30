package com.ruoyi.common.websocket.constant;

/**
 * websocket的常量配置
 *
 * @author zendwang
 */
public interface WebSocketConstants {
    /**
     * websocketSession中的参数的key
     */
    String LOGIN_USER_KEY = "loginUser";

    /**
     * 订阅的频道
     */
    String WEB_SOCKET_TOPIC = "global:websocket";

    /**
     * 前端心跳检查的命令
     */
    String PING = "ping";

    /**
     * 服务端心跳恢复的字符串
     */
    String PONG = "pong";

    /**
     * 加入房间
     */
    String JOIN_ROOM = "joinRoom";



    String WATCH_JOIN_ROOM = "watchJoinRoom";

    /**
     * 离开房间
     */
    String LEAVE_ROOM = "leaveRoom";

    /**
     * 房间已满
     */
    String ROOM_FULL = "roomFull";

    /**
     * 开始游戏
     */
    String START_GAME = "startGame";

    /**
     * 掷骰子
     */
    String SHAKE_DICE = "shakeDice";

    /**
     * 查表
     */
    String LOOK_UP_TABLE = "lookUpTable";

    /**
     * 关闭表
     */
    String CLOSE_TABLE = "colseTable";

    /**
     * 行动
     */
    String TAKE_ACTION = "takeAction";


    /**
     * 房间 key
     */
    String ROOM_KEY = "room:";

    /**
     * 房间 选手 key
     */
    String ROOM_USER_KEY = "room:user:";
}
