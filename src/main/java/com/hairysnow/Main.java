package com.hairysnow;

import com.hairysnow.playintegrity.IntegrityResult;
import com.hairysnow.playintegrity.PlayIntegrityHelper;

public class Main {
    public static void main(String[] args) {
        String integrityToken = "<your integrity token from app>";
        String applicationName = "<your application name>";
        String packageName = "<your packageName>";
        String credentialJsonPath = "<your credential json from google cloud platform>";
        IntegrityResult integrityResult =
                PlayIntegrityHelper
                        .decodeIntegrityToken(
                                integrityToken,
                                applicationName,
                                packageName,
                                credentialJsonPath);
        System.out.println(integrityResult);

    }

}