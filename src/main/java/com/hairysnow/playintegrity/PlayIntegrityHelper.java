package com.hairysnow.playintegrity;

import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.playintegrity.v1.PlayIntegrity;
import com.google.api.services.playintegrity.v1.PlayIntegrityRequestInitializer;
import com.google.api.services.playintegrity.v1.model.*;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.FileInputStream;
import java.util.Objects;

/**
 * @author Jam 2022/11/23
 * Description : Google Play Integrity API，用于安全认证
 * <a href="https://developer.android.com/google/play/integrity/overview">Google Play Integrity API</a>>
 */
public class PlayIntegrityHelper {

    /**
     * 请求Google解密设备传过来的integrityToken
     *
     * @param integrityToken  设备从谷歌获取的integrityToken
     * @param applicationName 应用名
     * @param packageName     应用包名
     * @param credentialJsonPath  Google credential json文件
     * @return {@link IntegrityResult}
     */
    public static IntegrityResult decodeIntegrityToken(String integrityToken,
                                                       String applicationName,
                                                       String packageName,
                                                       String credentialJsonPath
    ) {
        try {
            DecodeIntegrityTokenRequest requestObj = new DecodeIntegrityTokenRequest();
            requestObj.setIntegrityToken(integrityToken);
            //Configure downloaded Json file
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialJsonPath));
            HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);

            HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
            JsonFactory JSON_FACTORY = new GsonFactory();
            GoogleClientRequestInitializer playIntegrityRequestInitializer = new PlayIntegrityRequestInitializer();

            PlayIntegrity.Builder playIntegrity = new PlayIntegrity.Builder(HTTP_TRANSPORT, JSON_FACTORY, requestInitializer)
                    .setApplicationName(applicationName)
                    .setGoogleClientRequestInitializer(playIntegrityRequestInitializer);
            PlayIntegrity play = playIntegrity.build();

            DecodeIntegrityTokenResponse response = play.v1().decodeIntegrityToken(packageName, requestObj).execute();
            TokenPayloadExternal tokenPayloadExternal = response.getTokenPayloadExternal();
            System.out.println(tokenPayloadExternal);
            AppIntegrity appIntegrity = tokenPayloadExternal.getAppIntegrity();
            DeviceIntegrity deviceIntegrity = tokenPayloadExternal.getDeviceIntegrity();
            //应用和证书与 Google Play 分发的版本相符(是否为正品应用，是否被篡改过)
            boolean playRecognized = Objects.equals("PLAY_RECOGNIZED", appIntegrity.getAppRecognitionVerdict());
            //应用正在由 Google Play 服务提供支持的 Android 设备上运行
            boolean meetsDeviceIntegrity = deviceIntegrity.getDeviceRecognitionVerdict().contains("MEETS_DEVICE_INTEGRITY");
            //应用正在通过了基本系统完整性检查的设备上运行
            boolean meetsBasicIntegrity = deviceIntegrity.getDeviceRecognitionVerdict().contains("MEETS_BASIC_INTEGRITY");
            return new IntegrityResult(playRecognized, meetsDeviceIntegrity, meetsBasicIntegrity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return IntegrityResult.failed();
    }
}
