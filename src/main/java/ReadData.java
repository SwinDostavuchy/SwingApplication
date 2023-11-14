import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ReadData {
    private String davlSBar;
    private String tVx;
    private String ntk;

    public static String allDataText = "";
    public static String numZamers = "";

    public final String ENERG_PARAM = "Энергетические параметры";

    public static StringBuilder text = new StringBuilder();

    public static Set<String> dataNumZam = new LinkedHashSet<>();
    public static Set<String> dataDavlSBar = new LinkedHashSet<>();
    public static Set<String> dataTvx = new LinkedHashSet<>();
    public static Set<String> dataNtk = new LinkedHashSet<>();


    public ReadData(String davlSBar, String tVx, String ntk) {
        this.davlSBar = davlSBar;
        this.tVx = tVx;
        this.ntk = ntk;
    }

    public static String read(File file) {
        String sData = "";
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            allDataText = stripper.getText(document);

//            System.out.println(allDataText);
            extractData();
            dataNumZam.forEach(System.out::println);
            dataDavlSBar.forEach(System.out::println);
            dataTvx.forEach(System.out::println);
            dataNtk.forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return sData;
    }

    public static void extractData() {
        String[] dataTextAll = allDataText.split("Инженер по испытаниям:");

        if (Arrays.toString(dataTextAll).contains(Example.numberEngine) && Arrays.toString(dataTextAll).contains("Энергетические параметры")) {
            for (int i = 0; i < dataTextAll.length; i++) {
                String[] strArr = dataTextAll[i].split("\n");
                for (int j = 0; j < strArr.length; j++) {
                    if (strArr[j].startsWith("№")) {
                        dataNumZam.add(strArr[j]);
                    }
                    if (strArr[j].startsWith("Давл. с БРС")) {
                        dataDavlSBar.add(strArr[j]);
                    }
                    if (strArr[j].startsWith("t*вх")) {
                        dataTvx.add(strArr[j]);
                    }
                    if (strArr[j].startsWith("nтк") && !strArr[j].startsWith("nтк пр")) {
                        dataNtk.add(strArr[j]);
                    }
                }
            }


//            for (String s : dataNumZam) {
//                System.out.println(s);
//            }
//            for (String s : dataDavlSBar) {
//                System.out.println(s);
//            }
//            for (String s : dataTvx) {
//                System.out.println(s);
//            }
//            for (String s : dataNtk) {
//                System.out.println(s);
//            }

        }
    }

    public static boolean checkNumberEngine(String s) {
        if (s.endsWith(Example.numberEngine)) {
            return true;
        }
        return false;
    }

    public static String getNumZamer(String s) {
        String nZam = "";
        if (s.startsWith("№")) {
            nZam += s;
        }
        return nZam;
    }

    public static String getDavlSBarometra(String s) {
        String davl = "";
        if (s.startsWith("Давл.")) {
            davl += s;
        }
        return davl;
    }

    public static String getTvxB(String s) {
        String tvx = "";
        if (s.startsWith("t*вх")) {
            tvx += s;
        }
        return tvx;
    }

    public static String getNtk(String s) {
        String ntk = "";
        if (s.startsWith("nтк") && !s.startsWith("nтк пр")) {
            ntk += s;
        }
        return ntk;
    }

//    public static String extractDataFromStr(String s) {
//        String getNumZam = "";
//        String getDavlSBar = "";
//        String gettVx = "";
//        String ntk = "";
//
//        String[] arr = pageDivider(text);
//
//        for (int i = 0; i < arr.length; i++) {
//            if (checkPage(arr[i])) {
//                String[] array2 = delStringPoPeren(s);
//                for (int j = 0; j < array2.length; j++) {
//                    if (array2[j].contains("№ замера " + Example.pointA) || array2[j]
//                            .contains("№ замера " + Example.pointB) || array2[j].contains("№ замера " + Example.pointD)) {
//                         getNumZam += array2[j];
//                    }
//                }
//            }
//        }
//        System.out.println(getNumZam);
//        return getNumZam;
//    }

    public static String[] pageDivider(String s) {
        return s.split("Инженер по испытаниям:");
    }

//    public static boolean checkPage(String s) {
//        String[] arr = pageDivider(new StringBuilder(s));
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i].contains(Example.numberEngine) && arr[i].contains("Энергетические параметры")) {
//                return true;
//            }
//        }
//        return false;
//    }

    public static String[] delStringPoPeren(String st) {
        return st.split("\n");
    }

    public static String[] delStringPoProb(String s) {
        return s.split(" ");
    }

}
