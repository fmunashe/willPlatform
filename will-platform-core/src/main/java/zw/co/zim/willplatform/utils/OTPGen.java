package zw.co.zim.willplatform.utils;

import java.util.Random;

public class OTPGen {
    public static String generateOTP() {
        String CHARACTERS = "123456";
        int length = 6;

        Random random = new Random();

        StringBuilder otp = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            otp.append(CHARACTERS.charAt(randomIndex));
        }

        return otp.toString();
    }
}
