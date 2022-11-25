package com.hairysnow.playintegrity;

/**
 * @author Jam 2022/11/23
 * Description : Google Play Integrity结果
 */
public class IntegrityResult {
    /*
    * 应用和签名与 Google Play 分发的版本相符(是否为正品应用，是否被篡改过)
    * */
    private boolean isPlayRecognized;
    /*
    * 应用正在由 Google Play 服务提供支持的 Android 设备上运行
    * */
    private boolean isMeetsDeviceIntegrity;
    /*
    * 应用正在通过了基本系统完整性检查的设备上运行
    * */
    private boolean isMeetsBasicIntegrity;

    public IntegrityResult() {
    }

    public IntegrityResult(boolean isPlayRecognized, boolean isMeetsDeviceIntegrity, boolean isMeetsBasicIntegrity) {
        this.isPlayRecognized = isPlayRecognized;
        this.isMeetsDeviceIntegrity = isMeetsDeviceIntegrity;
        this.isMeetsBasicIntegrity = isMeetsBasicIntegrity;
    }

    public boolean isPlayRecognized() {
        return isPlayRecognized;
    }

    public void setPlayRecognized(boolean playRecognized) {
        this.isPlayRecognized = playRecognized;
    }

    public boolean isMeetsDeviceIntegrity() {
        return isMeetsDeviceIntegrity;
    }

    public void setMeetsDeviceIntegrity(boolean meetsDeviceIntegrity) {
        this.isMeetsDeviceIntegrity = meetsDeviceIntegrity;
    }

    public boolean isMeetsBasicIntegrity() {
        return isMeetsBasicIntegrity;
    }

    public void setMeetsBasicIntegrity(boolean meetsBasicIntegrity) {
        this.isMeetsBasicIntegrity = meetsBasicIntegrity;
    }

    public static IntegrityResult failure() {
        return new IntegrityResult();
    }

    @Override
    public String toString() {
        return "IntegrityResult{" +
                "isPlayRecognized=" + isPlayRecognized +
                ", isMeetsDeviceIntegrity=" + isMeetsDeviceIntegrity +
                ", isMeetsBasicIntegrity=" + isMeetsBasicIntegrity +
                '}';
    }
}
