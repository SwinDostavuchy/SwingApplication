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
            extractData();
//            System.out.println(allDataText);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public static void extractData() {
        String[] dataTextAll = allDataText.split("Инженер по испытаниям:");

        Set<String> dataNumZam = new LinkedHashSet<>();
        Set<String> dataDavlSBar = new LinkedHashSet<>();
        Set<String> dataTvx = new LinkedHashSet<>();
        Set<String> dataNtk = new LinkedHashSet<>();

        if (Arrays.toString(dataTextAll).contains(Example.numberEngine) && Arrays.toString(dataTextAll).contains("Энергетические параметры")) {
            for (int i = 0; i < dataTextAll.length; i++) {
                String[] strArr = dataTextAll[i].split("\n");
                for (int j = 0; j < strArr.length; j++) {
                    if (strArr[j].startsWith("№ замера")) {
                        dataNumZam.add(strArr[j].substring(9));
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
        }

        String str = Arrays.toString(dataNumZam.toArray()).replaceAll("[\\[\\]\\s]", " ");
//        String[] arr = str.split(",");
//        String str2 = Arrays.toString(arr);

        System.out.println(str);
        String str2 = str.replaceAll(" , ", " ");
        System.out.println(str2);
        String[] array = str2.split(" ");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(array));

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(Example.pointA)) {
                countPointA = i;
                System.out.println(i);
            }
        }
        System.out.print("индекс " + countPointA);

//        for(String s : list) {
//            if (s.equals(Example.pointA)) {
//                countPointA = list.get(Integer.parseInt(s));
//                System.out.print(countPointA);
//            }
//        }

//        char[] characters = str.toCharArray();
//        String str1 = Arrays.toString(characters).replaceAll(",","");
//        String str2 = str1.replaceAll("  ", " ");
//        String[] array = str2.split("");
//        System.out.println(str2);
//        System.out.println(array.length);

//        System.out.println(str2);
//        System.out.println(Arrays.toString(characters));

//        for (String s : arr) {
//            System.out.print(s);
//        }

//        dataNumZam.forEach(System.out::println);

//        dataDavlSBar.forEach(System.out::println);
//        dataTvx.forEach(System.out::println);
//        dataNtk.forEach(System.out::println);
    }

}
