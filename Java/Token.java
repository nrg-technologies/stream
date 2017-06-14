import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Token {

    private final String key;
    private Object[] data;

    public Token(String key) {
        this.key = key;
    }

    public void setData(Object... data) {
        this.data = data;
    }

    public String generate(
            long time
            )
            throws
            NoSuchAlgorithmException,
            InvalidKeyException,
            IOException
            {
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec(
                key.getBytes(), "RAW")
                );
        StringBuilder buffer = new StringBuilder();
        if(data != null) {
            for(Object object : data) {
                buffer.append(object);
            }
        }
        byte[] array = buffer.toString().getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(
                mac.doFinal(
                        ByteBuffer.allocate(array.length + 8)
                                  .put(array)
                                  .putLong(time)
                                  .array()
                        )
                );
        return new String(new sun.misc.BASE64Encoder().encode(digest));
    }
}