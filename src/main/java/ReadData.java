import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ReadData {
    private String davlSBar;
    private String tVx;
    private String ntk;

    public final String ENERG_PARAM = "Энергетические параметры";

    public static StringBuilder text = new StringBuilder();


    public ReadData(String davlSBar, String tVx, String ntk) {
        this.davlSBar = davlSBar;
        this.tVx = tVx;
        this.ntk = ntk;
    }

    public static String read(File file) {
        String sData = "";
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String allData = stripper.getText(document);
//            String[] strData = pageDivider(allData);
//            for (int i = 0; i < strData.length; i++) {
//                System.out.println(strData[i]);
//            }
//            sData = Arrays.toString(strData);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sData;
    }

    public static String readPDFPages(String s) throws IOException {
        PdfReader pdfReader;
        pdfReader = new PdfReader(s);

        String getNumZam = "";
        String getDavlSBar = "";
        String gettVx = "";
        String ntk = "";

        for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
            TextExtractionStrategy strategy = new SimpleTextExtractionStrategy();
            text.append(PdfTextExtractor.getTextFromPage(pdfReader, i, strategy));

//            if (text.toString().contains(Example.numberEngine) && text.toString().contains("Энергетические параметры")) {
//                String[] arr = delStringPoPeren(text.toString());
//                for (int j = 0; j < arr.length; j++) {
//                    if (arr[j].contains("№ замера") || arr[j].contains(Example.pointA) ||
//                    arr[j].contains(Example.pointB) || arr[j].contains(Example.pointD)) {
//                        getNumZam += arr[j];
//                    } else if (arr[j].contains("Давл. с БРС")) {
//                        getDavlSBar += arr[j];
//                    } else if (arr[j].contains("t*вх")) {
//                        gettVx += arr[j];
//                    }
//                }
//            }

        }
        extractDataFromStr(text.toString());

        pdfReader.close();
//        System.out.println(text.toString());
        System.out.println(getNumZam);
//        System.out.println(getDavlSBar);
//        System.out.println(gettVx);

        return text.toString();
    }

    public static String extractDataFromStr(String s) {
        String getNumZam = "";
        String getDavlSBar = "";
        String gettVx = "";
        String ntk = "";

        String[] arr = pageDivider(text);

        for (int i = 0; i < arr.length; i++) {
            if (checkPage(arr[i])) {
                String[] array2 = delStringPoPeren(s);
                for (int j = 0; j < array2.length; j++) {
                    if (array2[j].contains("№ замера " + Example.pointA) || array2[j]
                            .contains("№ замера " + Example.pointB) || array2[j].contains("№ замера " + Example.pointD)) {
                         getNumZam += array2[j];
                    }
                }
            }
        }
        System.out.println(getNumZam);
        return getNumZam;
    }

    public static String[] pageDivider(StringBuilder s) {
        return s.toString().split("Инженер по испытаниям:");
    }

    public static boolean checkPage(String s) {
        String[] arr = pageDivider(new StringBuilder(s));
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].contains(Example.numberEngine) && arr[i].contains("Энергетические параметры")) {
                return true;
            }
        }
        return false;
    }

    public static String[] delStringPoPeren(String st) {
        return st.split("\n");
    }

    public static String[] delStringPoProb(String s) {
        return s.split(" ");
    }

}
