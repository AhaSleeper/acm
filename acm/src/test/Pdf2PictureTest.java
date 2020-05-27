package test;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Magese
 * @description PDF转图片测试
 * @date 2019/3/29
 */
public class Pdf2PictureTest {
    private static final String FILE_DIR = "D:\\pdf2img\\";
    private static final String IMG_EXT_NAME = ".png";

    public static void pdf2Pic() throws IOException {
        // 创建PDF文件对象
        File pdfFile = new File( "C:\\Users\\Administrator\\Downloads\\搜料网-POLYLAC®ABS-D-1000-MSDS.pdf");
        // 获取文件名称
        String pdfName = pdfFile.getName().substring(0, pdfFile.getName().lastIndexOf("."));
        // 加载PDF
        PDDocument doc = PDDocument.load(pdfFile);
        // 创建PDF读取对象
        PDFRenderer pdfRenderer = new PDFRenderer(doc);
        // 获取PDF总页数
        int pageCount = doc.getNumberOfPages();
        // 遍历PDF
        for (int pageIndex = 0; pageIndex < pageCount; pageIndex++) {
            // 将PDF读取成图片流
            BufferedImage image = pdfRenderer.renderImageWithDPI(pageIndex, 300, ImageType.RGB);
            // 创建图片文件对象并将图片写出
            File imageFile = new File(FILE_DIR + pdfName + "-" + pageIndex + IMG_EXT_NAME);
            ImageIO.write(image, "png", imageFile);
        }
        doc.close();
    }

    public static void main(String[] args) throws IOException {
        pdf2Pic();
    }

}
