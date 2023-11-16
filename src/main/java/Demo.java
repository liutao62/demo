import java.io.*;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @author liutao
 * @date Created in 2021/5/13 20:38
 * @description
 */
public class Demo {
    private static final int GIFT_SIZE = 10;

    public static void main(String[] args) throws Exception {
        // 解压后的最新 esn 文件（不带后缀）放在此目录下即可
        String outputPath = "/Users/liutao/Downloads/esnlog";
        File outZipPath = new File(outputPath);
//        zipContraMultiFile("/Users/liutao/Downloads/esnlog (1).zip", outputPath);
        for (String f : Objects.requireNonNull(outZipPath.list())) {
            File logFile = new File(outZipPath, f);
            DESUtil.decryptLog(logFile.getAbsolutePath(), "/Users/liutao/IdeaProjects/self/demo/src/main/resources/log.txt");
        }
    }

    public static void zipContraMultiFile(String zippath, String outzippath) {
        try {
            File file = new File(zippath);
            File outFile = null;
            ZipFile zipFile = new ZipFile(file);
            ZipInputStream zipInput = new ZipInputStream(new FileInputStream(file));
            ZipEntry entry = null;
            InputStream input = null;
            OutputStream output = null;
            while ((entry = zipInput.getNextEntry()) != null) {
                System.out.println("解压缩" + entry.getName() + "文件");
                outFile = new File(outzippath + File.separator + entry.getName());
                if (!outFile.getParentFile().exists()) {
                    outFile.getParentFile().mkdir();
                }
                if (!outFile.exists()) {
                    outFile.createNewFile();
                }
                input = zipFile.getInputStream(entry);
                output = new FileOutputStream(outFile);
                int temp = 0;
                while ((temp = input.read()) != -1) {
                    output.write(temp);
                }
                input.close();
                output.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

