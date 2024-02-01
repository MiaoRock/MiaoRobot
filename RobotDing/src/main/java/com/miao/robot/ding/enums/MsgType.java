package com.miao.robot.ding.enums;

public enum MsgType {

    empty("empty", "", "不回复"),
    text("text", "text", "文本"),
    markdown("markdown", "markdown", "页签"),
    actionCard("actionCard", "actionCard", "跳转页签"),
    feedCard("feedCard", "feedCard", "条页签");

    MsgType(String type, String typeLag, String typeName) {
        this.type = type;
        this.typeLag = typeLag;
        this.typeName = typeName;
    }

    private String type;
    private String typeLag;
    private String typeName;

    public String getType() {
        return type;
    }

    public String getTypeLag() {
        return typeLag;
    }

    public String getTypeName() {
        return typeName;
    }
}
