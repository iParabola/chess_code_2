package com.ruoyi.business.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class QueryVerdictRecordDetail {
    //@ApiModelProperty(value = "裁决记录表id", required = true)
    private Long verdictRecordId;

    //@ApiModelProperty(value = "导演用户id")
    private String inviterUserId;

    //@ApiModelProperty(value = "导演名称")
    private String inviterUserName;

    //@ApiModelProperty(value = "甲方用户id")
    private String firstUserId;

    //@ApiModelProperty(value = "甲方用户姓名")
    private String firstUserName;

    //@ApiModelProperty(value = "甲方裁决用户id")
//    private String firstJudgeUserId;

    //@ApiModelProperty(value = "甲方指挥用户id")
//    private String firstCommanderUserId;

    //@ApiModelProperty(value = "甲方指挥用户名")
//    private String firstCommanderUserName;

    //@ApiModelProperty(value = "甲方裁决用户姓名")
//    private String firstJudgeUserName;

    //@ApiModelProperty(value = "甲方棋子阵营id")
    private String firstCampId;

    //@ApiModelProperty(value = "甲方棋子阵营名称")
    private String firstCampName;

    //@ApiModelProperty(value = "乙方用户id")
    private String secondUserId;

    //@ApiModelProperty(value = "乙方用户姓名")
    private String secondUserName;

    //@ApiModelProperty(value = "乙方裁决用户id")
//    private String secondJudgeUserId;

    //@ApiModelProperty(value = "乙方裁决用户姓名")
//    private String secondJudgeUserName;

    //@ApiModelProperty(value = "乙方指挥用户id")
//    private String secondCommanderUserId;

    //@ApiModelProperty(value = "乙方指挥用户名")
//    private String secondCommanderUserName;

    //@ApiModelProperty(value = "乙方棋子阵营id")
    private String secondCampId;

    //@ApiModelProperty(value = "乙方棋子阵营名称")
    private String secondCampName;

    //@ApiModelProperty(value = "想定id")
    private String scenarioId;

    //@ApiModelProperty(value = "想定名称")
    private String scenarioName;

    //@ApiModelProperty(value = "邀请码")
    private String inviteCode;

    //@ApiModelProperty(value = "观战模式,10:观战 20:不观战")
    private Integer watchMode;

    //@ApiModelProperty(value = "比赛状态,10:初始化 20:部署中 25:一方部署完成 30:双方部署完成 40:比赛进行中 50:比赛完成")
    private Integer status;

    //@ApiModelProperty(value = "回合数")
    private Integer chessRound;

    //@ApiModelProperty(value = "比赛回合数限制")
    private Integer chessRoundLimit;


    private String cover;

    private Date createTime;

}
