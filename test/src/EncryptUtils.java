import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by IntelliJ IDEA.
 * User: yuml
 * Date: 11-8-6
 * Time: 上午10:24
 * 文本加密解密
 */
public class EncryptUtils {
    /**
     * 加密
     *
     * @param text      加密文本
     * @param algorithm 算法
     * @return
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     */
    public static String encrypt(String text, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String eText = null;
        if (text != null && !"".equals(text)) {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(text.getBytes("UTF-8"));
            byte[] digest = md.digest();
            eText = StringUtils.byte2Hex(digest);
        }
        return eText;
    }

    /**
     * MD5摘要  默认生成32位
     *
     * @param text
     * @return
     */
    public static String md5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return encrypt(text, "MD5");
    }
    /**
         * MD5摘要 生成16位
         *
         * @param text
         * @return
         */
        public static String md5_16(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
            String s = encrypt(text, "MD5");
            if(s!=null && s.length()>0){
                s = s.substring(8,24);
            }
            return s;
        }

    /**
     * sha-1加密
     *
     * @param text
     * @return
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     */
    public static String sha(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return encrypt(text, "SHA-1");
    }
}
