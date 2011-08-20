import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: yuml
 * Date: 11-8-7
 * Time: 上午8:26
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String dir = "E:\\documents\\manual";
        File[] files = FileUtils.getFilesOrderByModified(dir, false);
        for (File file : files) {

            System.out.println("File Name: " + file.getName() + "\n Modified Time :" +
                    DateUtils.formatLong(new Date(file.lastModified())));
        }
    }
}
