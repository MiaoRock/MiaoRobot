package com.miao.robot.Enum;

public enum RobotMode {
    normal("normal", "常规", ""),
    qingyunke("qingyunke", "青云客机器人", "QingyunkeClient"),
    tuling("tuling", "青云客机器人", "TulingClient");

    RobotMode(String mode, String modeName, String modeClassName) {
        this.mode = mode;
        this.modeName = modeName;
        this.modeClassName = modeClassName;
    }

    private String mode;
    private String modeName;
    private String modeClassName;

    public String getMode() {
        return mode;
    }

    public String getModeName() {
        return modeName;
    }

    public String getModeClassName() {
        return modeClassName;
    }
}
