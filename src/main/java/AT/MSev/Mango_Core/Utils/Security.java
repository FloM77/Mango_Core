package AT.MSev.Mango_Core.Utils;

import java.security.MessageDigest;

public class Security {
    public static String SHAString(String input)
    {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(input.getBytes());
            String encryptedString = new String(messageDigest.digest());
            return encryptedString;
        } catch(Exception ex) { MangoUtils.Log(ex.getMessage()); }
        return null;
    }
}
