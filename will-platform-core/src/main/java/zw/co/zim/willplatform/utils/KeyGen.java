package zw.co.zim.willplatform.utils;

import java.util.Random;

public class KeyGen {

    private KeyGen() {
        super();
    }

    public static String generateTicketNumber() {
        String CHARACTERS = "0123456789";
        int length = 6;

        Random random = new Random();

        StringBuilder ref = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            ref.append(CHARACTERS.charAt(randomIndex));
        }

        return "SMBZT-" + ref;
    }
}
