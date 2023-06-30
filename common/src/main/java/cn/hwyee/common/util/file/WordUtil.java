package cn.hwyee.common.util.file;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.util.Matrix;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author hui
 * @version 1.0
 * @className WordUtil
 * @description
 * @date 2023/6/29
 * @since JDK 1.8
 */
@Slf4j
public class WordUtil {
    public static final String SIGN = "&签名&";
    public static final String DATE = "&日期&";
    public static String testpath = "./config/authfiletemplete/授权书.docx";
    public static String testoutpath = "./config/authfiletemplete/授权书temp.docx";
    public static String testimg = "./config/zhangsan.png";
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void insertImg()  {
        int width = 60;
        int height = 20;
        OPCPackage opcPackage = null;
        XWPFDocument document = null;
        FileInputStream fileInputStream = null;
        FileOutputStream out = null;
        //不能使用 try-resource-cache因为opcPackage的关闭需使用revert();
//        try (OPCPackage opcPackage=POIXMLDocument.openPackage(testpath);
//             XWPFDocument document = new XWPFDocument(opcPackage);
//             FileInputStream fileInputStream = new FileInputStream(testimg);
//             FileOutputStream out = new FileOutputStream(testoutpath);){
        try {
            //打开word文档
            opcPackage = POIXMLDocument.openPackage(testpath);
            document = new XWPFDocument(opcPackage);
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            XWPFRun finalRun = null;
            //寻找标记文本段落
            for (XWPFParagraph xwpfParagraph : paragraphs) {
                if (!"".equals(xwpfParagraph.getText()) && xwpfParagraph.getText().contains(SIGN)) {
                    List<XWPFRun> runs = xwpfParagraph.getRuns();
                    log.info("runs size {}",runs.size());
                    log.info("top:{}", xwpfParagraph.getBorderTop());
                    //每个段落由许多个XWPFRun组成,寻找标记文本的Run。
                    for (XWPFRun run : runs) {
                        String text = run.getText(0);
                        if (text == null){
                            continue;
                        }
                        if (text.contains(SIGN)){
                            //替换图片标记文本为空
                            run.setText("",0);
                            finalRun = run;
                        }else if (text.contains(DATE)){
                            //替换时间标记文本为当前时间
                            run.setText(dateTimeFormatter.format(LocalDate.now()),0);
                        }
                    }
                }
            }
            //插入图片
            if (finalRun != null) {
                fileInputStream = new FileInputStream(testimg);
                finalRun.addPicture(fileInputStream, Document.PICTURE_TYPE_PNG, "testimg",
                        Units.toEMU(width), Units.toEMU(height));
                CTDrawing drawingArray = finalRun.getCTR().getDrawingArray(0);
                CTGraphicalObject graphic = drawingArray.getInlineArray(0).getGraphic();
                //拿到新插入的图片替换添加CTAnchor 设置浮动属性 删除inline属性
                CTAnchor anchor = getAnchorWithGraphic(graphic, "testimg",
                        //图片大小
                        Units.toEMU(width), Units.toEMU(height),
                        //相对当前段落位置 需要计算段落已有内容的左偏移
                        Units.toEMU(30), Units.toEMU(0), false);
                //图片的定位属性就两种 行内和锚
                //添加浮动(锚)属性
                drawingArray.setAnchorArray(new CTAnchor[]{anchor});
                //删除行内属性,这个会占用文本位置,所以不用
                drawingArray.removeInline(0);
            }
            out = new FileOutputStream(testoutpath);
            document.write(out);

        } catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if (opcPackage != null){
                try {
                    //不能用opcPackage.close();因为如果源文档是可写的,则关闭时会将改动写到源文件(模板文件)。
                    opcPackage.revert();
                }catch (Exception e){
                    log.error("关闭word文档包失败:{}",e.getMessage(),e);
                }
            }
            if (document != null){
                try {
                    document.close();
                }catch (Exception e){
                    log.error("关闭word文档失败:{}",e.getMessage(),e);
                }
            }
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (Exception e){
                    log.error("关闭图片输入流失败:{}",e.getMessage(),e);
                }
            }
            if (out != null){
                try {
                    out.close();
                } catch (Exception e){
                    log.error("关闭word输出流失败:{}",e.getMessage(),e);
                }
            }
        }




    }

    /**
     * @param ctGraphicalObject 图片数据
     * @param deskFileName      图片描述
     * @param width             宽
     * @param height            高
     * @param leftOffset        水平偏移 left
     * @param topOffset         垂直偏移 top
     * @param behind            文字上方，文字下方
     * @return
     * @throws Exception
     */
    public static CTAnchor getAnchorWithGraphic(CTGraphicalObject ctGraphicalObject,
                                                String deskFileName, int width, int height,
                                                int leftOffset, int topOffset, boolean behind) {
        log.info(">>width>> {}  >>height>>>>{}" ,width, height);
        String anchorXML =
                "<wp:anchor xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" "
                        + "simplePos=\"0\" relativeHeight=\"0\" behindDoc=\"" + ((behind) ? 1 : 0) + "\" locked=\"0\" layoutInCell=\"1\" allowOverlap=\"1\">"
                        + "<wp:simplePos x=\"0\" y=\"0\"/>"
                        + "<wp:positionH relativeFrom=\"column\">"
                        + "<wp:posOffset>" + leftOffset + "</wp:posOffset>"
                        + "</wp:positionH>"
                        + "<wp:positionV relativeFrom=\"paragraph\">"
                        + "<wp:posOffset>" + topOffset + "</wp:posOffset>" +
                        "</wp:positionV>"
                        + "<wp:extent cx=\"" + width + "\" cy=\"" + height + "\"/>"
                        + "<wp:effectExtent l=\"0\" t=\"0\" r=\"0\" b=\"0\"/>"
                        + "<wp:wrapNone/>"
                        + "<wp:docPr id=\"1\" name=\"Drawing 0\" descr=\"" + deskFileName + "\"/><wp:cNvGraphicFramePr/>"
                        + "</wp:anchor>";

        CTDrawing drawing = null;
        try {
            drawing = CTDrawing.Factory.parse(anchorXML);
        } catch (XmlException e) {
            log.error("word插入图片失败:{}",e.getMessage(),e);
        }
        CTAnchor anchor = drawing.getAnchorArray(0);
        anchor.setGraphic(ctGraphicalObject);
        return anchor;
    }

    public static String pdf = "./config/word2pdf.pdf";
    public static void poiWord2PDF(){
        OPCPackage opcPackage = null;
        XWPFDocument document = null;
        FileInputStream fileInputStream = null;
        FileOutputStream out = null;
        try {
            //打开word文档
            opcPackage = POIXMLDocument.openPackage(testpath);
            document = new XWPFDocument(opcPackage);
            out = new FileOutputStream(pdf);
            //会报错,poi版本太高了
//            PdfConverter.getInstance().convert(document, out, null);
//            PDDocument pdf = new PDDocument();
//
//            PDPage page = new PDPage();
//
//            pdf.addPage(page);
//
//            PDPageContentStream contentStream = new PDPageContentStream(pdf, page);
//
//            PDFTextStripper pdfTextStripper = new PDFTextStripper();
//
//            pdfTextStripper.setSortByPosition(true);
//
//            LayoutResult layout = null;
//
//            float yMin = 0.0F;
//
//            try {
//
//                layout = new DocxToPDFConverter(document).layout();
//
//                yMin = layout.getPage().get(0).getyMin();
//
//                int pageNum = 1;
//
//                while(layout != null){
//
//                    PDPage pdfPage = new PDPage(PDRectangle.A4);
//
//                    pdf.addPage(pdfPage);
//
//                    contentStream = new PDPageContentStream(pdf, pdfPage);
//
//                    contentStream.beginText();
//
//                    float offsetX = 85.0F;
//
//                    for (PDFLine line : layout.getPage()) {
//
//                        contentStream.setTextMatrix(Matrix.getTranslateInstance(offsetX, 830 - line.getLineTop() - line.getLineHeight() - yMin));
//
//                        contentStream.showText(line.getLine());
//
//                        contentStream.setTextMatrix(Matrix.getTranslateInstance(0.0F, line.getLineSpacing()));
//
//                    }
//
//                    contentStream.endText();
//
//                    contentStream.close();
//
//                    ++pageNum;
//
//                    layout = layout.isLast() ? null : new DocxToPDFConverter(document, layout).layout();
//
//                }
//
//                pdf.save("word.pdf");
//
//            }finally{
//
//                pdf.close();
//
//            }
        }catch (Exception ee){
            if (opcPackage != null){
                try {
                    //不能用opcPackage.close();因为如果源文档是可写的,则关闭时会将改动写到源文件(模板文件)。
                    opcPackage.revert();
                }catch (Exception e){
                    log.error("关闭word文档包失败:{}",e.getMessage(),e);
                }
            }
            if (document != null){
                try {
                    document.close();
                }catch (Exception e){
                    log.error("关闭word文档失败:{}",e.getMessage(),e);
                }
            }
        }
    }




    public static void main(String[] args) {
//        insertImg();

    }
}
