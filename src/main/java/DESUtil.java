import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

public class DESUtil {
    // 算法名称
    public static final String KEY_ALGORITHM = "DES";
    // 算法名称/加密模式/填充方式
    // DES共有四种工作模式-->>ECB：电子密码本模式、CBC：加密分组链接模式、CFB：加密反馈模式、OFB：输出反馈模式
    public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

    public static final String LOG_PATH = "/Users/liuyingwen/Downloads/1620957262O711.yyimlog";

    /**
     * 生成密钥key对象
     *
     * @param keyStr 密钥字符串
     * @return 密钥对象
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws Exception
     */
    private static SecretKey keyGenerator1(String keyStr) throws Exception {
        byte input[] = HexString2Bytes(keyStr);
        DESKeySpec desKey = new DESKeySpec(input);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        return securekey;
    }

    private static int parse(char c) {
        if (c >= 'a')
            return (c - 'a' + 10) & 0x0f;
        if (c >= 'A')
            return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    // 从十六进制字符串到字节数组转换
    public static byte[] HexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    /**
     * 加密数据
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return 加密后的数据
     */
    public static String encrypt(String data, String key) throws Exception {
        Key deskey = keyGenerator(key);
        // 实例化Cipher对象，它用于完成实际的加密操作
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        SecureRandom random = new SecureRandom();
        // 初始化Cipher对象，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, deskey, random);
        byte[] results = cipher.doFinal(data.getBytes());
        // 该部分是为了与加解密在线测试网站（http://tripledes.online-domain-tools.com/）的十六进制结果进行核对

        // 执行加密操作。加密后的结果通常都会用Base64编码进行传输
        return Base64.encode(results);
    }

    /**
     * 加密数据
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return 加密后的数据
     */
    public static String encrypt1(String data, String key) throws Exception {
        Key deskey = keyGenerator1(key);
        // 实例化Cipher对象，它用于完成实际的加密操作
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        SecureRandom random = new SecureRandom();
        // 初始化Cipher对象，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, deskey, random);
        byte[] results = cipher.doFinal(data.getBytes());
        // 该部分是为了与加解密在线测试网站（http://tripledes.online-domain-tools.com/）的十六进制结果进行核对

        // 执行加密操作。加密后的结果通常都会用Base64编码进行传输
        return Base64.encode(results);
    }

    /**
     * 解密数据
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return 解密后的数据
     */
    public static String decrypt(String data, String key) throws Exception {
        Key deskey = keyGenerator(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        // 初始化Cipher对象，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, deskey);
        // 执行解密操作
        return new String(cipher.doFinal(Base64.decode(data)));
    }

    /**
     * 解密数据
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return 解密后的数据
     */
    public static String decrypt1(String data, String key) throws Exception {
        Key deskey = keyGenerator1(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        // 初始化Cipher对象，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, deskey);
        // 执行解密操作
        return new String(cipher.doFinal(Base64.decode(data)));
    }

    public static void main(String[] args) throws Exception {
        try {
//            decryptLog("/Users/kong/Downloads/wjg图片发送按钮有时不能点/1607062921f7vA.log.txt", "/Users/kong/Downloads/wjg图片发送按钮有时不能点/1607062919dmiM.log2.txt");
            decryptLog(LOG_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String enCode(String key, String content) {
        String encryptData = "";
        try {
            key = MD5Util.getMD5Str(key);
            encryptData = UUID.randomUUID().toString().substring(0, 5)
                    + encrypt1(content, key)
                    + UUID.randomUUID().toString().substring(0, 5);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (encryptData == null || encryptData.equals("")) {
            encryptData = content;
        }
        return encryptData;
    }

    private static SecretKey keyGenerator(String keyStr) throws Exception {
//        byte input[] = HexString2Bytes(keyStr);
//        DESKeySpec desKey = new DESKeySpec(input);
        DESKeySpec desKey = new DESKeySpec(keyStr.getBytes("UTF-8"));
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        return securekey;
    }

    public static String deCode(String key, String encryptData) {
        String decryptData = "";
        if (encryptData != null && !encryptData.trim().equals("")) {
            try {
                decryptData = decrypt1(encryptData.substring(5, encryptData.length() - 5),
                        MD5Util.getMD5Str(key));
            } catch (Exception e) {
            }
            if (decryptData.equals("")) {
                decryptData = encryptData;//TODO:不该赋值
            }
        }
        return decryptData;
    }

    public static String getFileExtension(File file) {
        if (file == null) return null;

        return getFileExtension(file.getPath());
    }

    public static String getFileExtension(String filePath) {
        int lastPoi = filePath.lastIndexOf('.');
        int lastSep = filePath.lastIndexOf(File.separator);
        if (lastPoi == -1 || lastSep >= lastPoi) return "";
        return filePath.substring(lastPoi + 1);
    }

    /**
     * 解密指定路径的日志文件
     *
     * @param filePath 加密日志文件绝对路径，可以是文件也可以是文件夹
     */
    public static void decryptLog(String filePath) throws Exception {
        File logFile = new File(filePath);
        if (!logFile.exists()) {
            return;
        }

        if (logFile.isFile()) {
            String ext = getFileExtension(logFile);
            decryptLog(logFile.getAbsolutePath(), String.format("%s2.%s", logFile.getAbsolutePath(), ext));
            return;
        }

        if (!logFile.isDirectory()) {
            return;
        }
        //路径参数是文件夹
        File[] files = logFile.listFiles();
        if (files == null || files.length <= 0) {
            return;
        }
        for (File file : files) {
            if (file == null || !file.exists() || !file.isFile()) {
                continue;
            }
            String ext = getFileExtension(file);
            decryptLog(file.getAbsolutePath(), String.format("%s2.%s", file.getAbsolutePath(), ext));
        }
    }

    /**
     * 解密日志
     *
     * @param sourceFileName     pc端源日志绝对路径
     * @param diminationFileName pc端新日志绝对路径
     */
    public static void decryptLog(String sourceFileName, String diminationFileName) throws Exception {
        BufferedWriter bw = null;
        OutputStreamWriter osw = null;
        BufferedReader buffreader = null;
        InputStream is = null;
        try {
            is = new FileInputStream(sourceFileName);
            OutputStream out = new FileOutputStream(diminationFileName, true);
            InputStreamReader inputreader = new InputStreamReader(is);
            buffreader = new BufferedReader(inputreader);
            osw = new OutputStreamWriter(out);
            bw = new BufferedWriter(osw);
            String line;
            //分行读取
            while ((line = buffreader.readLine()) != null) {
                String a = DESUtil.deCode("youzone_android*2020", line);
                bw.write(a + "\n");
                bw.flush();
            }
            System.out.println(diminationFileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                bw.close();
            }
            if (osw != null) {
                osw.close();
            }
            if (buffreader != null) {
                buffreader.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }
}