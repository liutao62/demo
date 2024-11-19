import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
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
        ArrayList<String> old = Lists.newArrayList("jnchshwa", "hz915d6f", "e11659vt", "qyic8c7o", "dgc8j3e7", "ayqj73nm", "20o0smha", "q453tfhw", "x82dca80", "mzsr2uqu", "vwsgqatm", "iucw6z3a", "ts8jqrax", "ec3g1klg", "x1550egu", "sz0p5gyz", "qbg71rsv", "uaxctkh0", "pntgxj1s", "vfx2hv8l", "t5d0h9l7", "bcclvrgu", "cor4y2bq", "viy9dfme", "kytq233a", "ttyadp8g", "jip70wey", "rrh3bwfc", "aj5e0gsx", "icyewhwv", "insfr8ck", "ndcq6zi3", "isk9zbu7", "b616r7im", "jtqcod3k", "hj6ro5by", "sabqqlq1", "e6jd3fck", "uckcazto", "qbgv94ai"
        );

        ArrayList<String> target = Lists.newArrayList("y6bzmfrx", "qyic8c7o", "trr51z1u", "vwsgqatm", "w34o47cq", "wwrtk9r8", "mbe008kc", "ikkb9gi2", "ftzokcrn", "glroeeo3", "kjevosbe", "j9b55izl", "dpxhi49c", "ogz9urwc", "vkx4mgh7", "t70rxy8a", "nim5zstd", "qmh8bs6c", "r47tbpsv", "ij44ambo", "ckg75soz", "u60uwe55", "x0y0m8rn", "eb8b58c3", "p8s8sqd6", "aae28shl", "hqp3mg1j", "rx69kmql", "ton8l99g", "cqmr5njx", "lmcwzu3e", "xn9o50gt", "o4p6voe0", "vyos9rjy", "dmpltzkc", "lc6fpdmh", "vz69bh15", "fxrzzens", "h27tpclq", "b9wuchko", "qbg71rsv", "it1c7eoe", "cwuhswon", "pntgxj1s", "bolmz333", "tfuqrfic", "itvqa0x6", "sxljek2f", "b616r7im", "ryievz1l", "sq4iimfp", "n22w18oi", "hvgvzpx0", "bnipy2cj", "dsbuej9w", "da3mojo8", "nbe2y23x", "ppy6c63r", "fe376zmc", "dlqorvak", "qhwm54dz", "fkdtrqg8", "ipd1qn5s", "obu25s4b", "rkafzcia", "ttyadp8g", "ygzf9svt", "da4g5qhf", "u8jdybet", "gfcx92ph", "hdywrzfg", "lmmf8i8y", "rdy91zio", "pxodp3oi", "fo02vw19", "diyt1hqk", "xgjh8jtw", "hj0j4fxt", "izx2uug0", "s4rl0np9", "h2tsp9zl", "ebkitg4w", "gxm3arh0", "b96sc95e", "srrh3hgp", "g7i9zxb7", "rnqsuipc", "s1mqekvo", "aa7i8snb", "nakpg97z", "w8k9ito4", "v1bxz4av"
        );
        Collection<String> intersection = CollectionUtils.intersection(old, target);
        System.out.println(intersection);
        // 解压后的最新 esn 文件（不带后缀）放在此目录下即可
        String outputPath = "/Users/liutao/Downloads/yysignlog";
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

