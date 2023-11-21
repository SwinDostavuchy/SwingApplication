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
    public static int countPointA;
    public static int countPointB;
    public static int countPointD;

    public static String resultA = "";
    public static String resultB = "";
    public static String resultD = "";

    public static String allDataText = "";
//    public static Set<String> dataNumZam = new LinkedHashSet<>();
//    public static Set<String> dataDavlSBar = new LinkedHashSet<>();
//    public static Set<String> dataTvx = new LinkedHashSet<>();
//    public static Set<String> dataNtk = new LinkedHashSet<>();


    public ReadData(String davlSBar, String tVx, String ntk) {
        this.davlSBar = davlSBar;
        this.tVx = tVx;
        this.ntk = ntk;
    }

    public static void read(File file) {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            allDataText = stripper.getText(document);
//            System.out.println(allDataText);
            extractDataVK2500();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public static void extractDataVK2500() {
        String[] dataTextAll = allDataText.split("Инженер по испытаниям:");

        Set<String> dataNumZam = new LinkedHashSet<>();
        Set<String> dataDavlSBar = new LinkedHashSet<>();
        Set<String> dataTvx = new LinkedHashSet<>();
        Set<String> dataNtk = new LinkedHashSet<>();

        if (Arrays.toString(dataTextAll).contains(Example.numberEngine) && Arrays.toString(dataTextAll).contains("Энергетические параметры")) {
            for (int i = 0; i < dataTextAll.length; i++) {
                String[] strArrAll = dataTextAll[i].split("\n");
                for (int j = 0; j < strArrAll.length; j++) {
                    if (strArrAll[j].startsWith("№ замера")) {
                        dataNumZam.add(strArrAll[j].substring(9));
                    }
                    if (strArrAll[j].startsWith("Давл. с БРС")) {
                        dataDavlSBar.add(strArrAll[j].substring(12));
                    }
                    if (strArrAll[j].startsWith("t*вх")) {
                        dataTvx.add(strArrAll[j].substring(5));
                    }
                    if (strArrAll[j].startsWith("nтк") && !strArrAll[j].startsWith("nтк пр")) {
                        dataNtk.add(strArrAll[j].substring(4));
                    }
                }
            }
        }

        String strNumZ = Arrays.toString(dataNumZam.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2NumZ = strNumZ.replaceAll(" , ", " ");
        System.out.println(str2NumZ);
        String[] arrayNumZam = str2NumZ.split(" ");
        ArrayList<String> listNumZ = new ArrayList<>(Arrays.asList(arrayNumZam));

        for (int i = 0; i < listNumZ.size(); i++) {
            if (listNumZ.get(i).equals(Example.pointA)) {
                countPointA = i;
            } if (listNumZ.get(i).equals(Example.pointB)) {
                countPointB = i;
            } if (listNumZ.get(i).equals(Example.pointD)) {
                countPointD = i;
            }
        }

//        for (int j = 0; j < listNumZ.size(); j++) {
//            if (listNumZ.get(j).equals(Example.pointB)) {
//                countPointB = j;
//            }
//        }
//
//        for (int k = 0; k < listNumZ.size(); k++) {
//            if (listNumZ.get(k).equals(Example.pointD)) {
//                countPointD = k;
//            }
//        }
//
//        String strADavlSBar = Arrays.toString(dataDavlSBar.toArray()).replaceAll("[\\[\\]\\s]", " ");
//        String str2DavlSBar = strADavlSBar.replaceAll(" , ", " ");
//        String[] arrayDavlSBar = str2DavlSBar.split(" ");
//        ArrayList<String> listDavlSBar = new ArrayList<>(Arrays.asList(arrayDavlSBar));
//        if (countPointA != 0) {
//            resultA += "Давление с БРС точка А: " + listDavlSBar.get(countPointA) + " , " + ("Давление с БРС точка Б: " + listDavlSBar.get(countPointB) + " ,") +
//                    "Давление с БРС точка Д: " + listDavlSBar.get(countPointD) + " . ";
//
//            System.out.println("Давление с БРС точка А: " + listDavlSBar.get(countPointA));
//        }
//        if (countPointB != 0) {
//            System.out.println("Давление с БРС точка Б: " + listDavlSBar.get(countPointB));
//        }
//        if (countPointD != 0) {
//            System.out.println("Давление с БРС точка Д: " + listDavlSBar.get(countPointD));
//        }
//
//        String strTVx = Arrays.toString(dataTvx.toArray()).replaceAll("[\\[\\]\\s]", " ");
//        String str2TVx = strTVx.replaceAll(" , ", " ");
//        String[] arrayTVx = str2TVx.split(" ");
//        ArrayList<String> listTVx = new ArrayList<>(Arrays.asList(arrayTVx));
//        if (countPointA != 0) {
//            System.out.println("t*вх точка А: " + listTVx.get(countPointA));
//        }
//        if (countPointB != 0) {
//            System.out.println("t*вх точка Б: " + listTVx.get(countPointB));
//        }
//        if (countPointD != 0) {
//            System.out.println("t*вх точка Д: " + listTVx.get(countPointD));
//        }
//
//        String strNtk = Arrays.toString(dataNtk.toArray()).replaceAll("[\\[\\]\\s]", " ");
//        String str2Ntk = strNtk.replaceAll(" , ", " ");
//        String[] arrayNtk = str2Ntk.split(" ");
//        ArrayList<String> listNtk = new ArrayList<>(Arrays.asList(arrayNtk));
//        if (countPointA != 0) {
//            System.out.println("nтк точка А: " + listNtk.get(countPointA));
//        }
//        if (countPointB != 0) {
//            System.out.println("nтк точка Б: " + listNtk.get(countPointB));
//        }
//        if (countPointD != 0) {
//            System.out.println("nтк точка Д: " + listNtk.get(countPointD));
//        }

    }

}