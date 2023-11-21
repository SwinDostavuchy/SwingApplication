import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
    public static String allDataACT = "";
//    public static Set<String> dataNumZam = new LinkedHashSet<>();
//    public static Set<String> dataDavlSBar = new LinkedHashSet<>();
//    public static Set<String> dataTvx = new LinkedHashSet<>();
//    public static Set<String> dataNtk = new LinkedHashSet<>();


    public ReadData(String davlSBar, String tVx, String ntk) {
        this.davlSBar = davlSBar;
        this.tVx = tVx;
        this.ntk = ntk;
    }

    public static void readAct(File file) {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            allDataACT = stripper.getText(document);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(allDataACT);
    }

    public static void readTxt(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)
        ) {

            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//        try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
//            StringBuilder sb = new StringBuilder();
//            String line = br.readLine();
//
//            while (line != null) {
//                sb.append(line);
//                sb.append(System.lineSeparator());
//                line = br.readLine();
//            }
//            String everything = sb.toString();
//            System.out.println(everything);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public static void read(File file) {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            allDataText = stripper.getText(document);

            String selectedBox = (String) Example.comboBox.getSelectedItem();
            assert selectedBox != null;
            switch (selectedBox) {
                case "ВК-2500":
                    extractDataVK2500();
                    break;
                case "ТВ3-117ВМ":
                    extractDataTV3117Vm();
                    break;
                case "ВК-2500П":
                    extractDataVK2500P();
                    break;
            }

//            System.out.println(allDataText);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public static void extractDataVK2500P() {
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
                        dataDavlSBar.add(strArr[j].substring(12));
                    }
                    if (strArr[j].startsWith("Твх_б")) {
                        dataTvx.add(strArr[j].substring(6));
                    }
                    if (strArr[j].startsWith("nтк") && !strArr[j].startsWith("nтк пр")) {
                        dataNtk.add(strArr[j].substring(4));
                    }
                }
            }
        }

        String strNumZ = Arrays.toString(dataNumZam.toArray()).replaceAll("[\\[\\]\\s]", " ");
        System.out.println(strNumZ);
        String str2NumZ = strNumZ.replaceAll(" , ", " ");
        System.out.println(str2NumZ);
        String[] arrayNumZam = str2NumZ.split(" ");
        ArrayList<String> listNumZ = new ArrayList<>(Arrays.asList(arrayNumZam));

        for (int i = 0; i < listNumZ.size(); i++) {
            if (listNumZ.get(i).equals(Example.pointA)) {
                countPointA = i;
            }
        }

        for (int j = 0; j < listNumZ.size(); j++) {
            if (listNumZ.get(j).equals(Example.pointB)) {
                countPointB = j;
            }
        }

        for (int k = 0; k < listNumZ.size(); k++) {
            if (listNumZ.get(k).equals(Example.pointD)) {
                countPointD = k;
            }
        }

        String strADavlSBar = Arrays.toString(dataDavlSBar.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2DavlSBar = strADavlSBar.replaceAll(" , ", " ");
        String[] arrayDavlSBar = str2DavlSBar.split(" ");
        ArrayList<String> listDavlSBar = new ArrayList<>(Arrays.asList(arrayDavlSBar));
        if (countPointA != 0) {
            resultA += "Давление с БРС точка А: " + listDavlSBar.get(countPointA) + " , " + ("Давление с БРС точка Б: " + listDavlSBar.get(countPointB) + " ,") +
                    "Давление с БРС точка Д: " + listDavlSBar.get(countPointD) + " . ";

            System.out.println("Давление с БРС точка А: " + listDavlSBar.get(countPointA));
        }

        String strTVx = Arrays.toString(dataTvx.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2TVx = strTVx.replaceAll(" , ", " ");
        String[] arrayTVx = str2TVx.split(" ");
        ArrayList<String> listTVx = new ArrayList<>(Arrays.asList(arrayTVx));
        if (countPointA != 0) {
            System.out.println("Твх_б точка А: " + listTVx.get(countPointA));
        }

        String strNtk = Arrays.toString(dataNtk.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2Ntk = strNtk.replaceAll(" , ", " ");
        String[] arrayNtk = str2Ntk.split(" ");
        ArrayList<String> listNtk = new ArrayList<>(Arrays.asList(arrayNtk));
        if (countPointA != 0) {
            System.out.println("nтк точка А: " + listNtk.get(countPointA));
            countPointA = 0;
        }

    }

    public static void extractDataTV3117Vm() {
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
                        dataDavlSBar.add(strArr[j].substring(12));
                    }
                    if (strArr[j].startsWith("tвоз_тп")) {
                        dataTvx.add(strArr[j].substring(8));
                    }
                    if (strArr[j].startsWith("nтк") && !strArr[j].startsWith("nтк пр")) {
                        dataNtk.add(strArr[j].substring(4));
                    }
                }
            }
        }

        String strNumZ = Arrays.toString(dataNumZam.toArray()).replaceAll("[\\[\\]\\s]", " ");
        System.out.println(strNumZ);
        String str2NumZ = strNumZ.replaceAll(" , ", " ");
        System.out.println(str2NumZ);
        String[] arrayNumZam = str2NumZ.split(" ");
        ArrayList<String> listNumZ = new ArrayList<>(Arrays.asList(arrayNumZam));

        for (int i = 0; i < listNumZ.size(); i++) {
            if (listNumZ.get(i).equals(Example.pointA)) {
                countPointA = i;
            }
        }

        for (int j = 0; j < listNumZ.size(); j++) {
            if (listNumZ.get(j).equals(Example.pointB)) {
                countPointB = j;
            }
        }

        for (int k = 0; k < listNumZ.size(); k++) {
            if (listNumZ.get(k).equals(Example.pointD)) {
                countPointD = k;
            }
        }

        String strADavlSBar = Arrays.toString(dataDavlSBar.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2DavlSBar = strADavlSBar.replaceAll(" , ", " ");
        String[] arrayDavlSBar = str2DavlSBar.split(" ");
        ArrayList<String> listDavlSBar = new ArrayList<>(Arrays.asList(arrayDavlSBar));
        if (countPointA != 0) {
            resultA += "Давление с БРС точка А: " + listDavlSBar.get(countPointA) + " , " + ("Давление с БРС точка Б: " + listDavlSBar.get(countPointB) + " ,") +
                    "Давление с БРС точка Д: " + listDavlSBar.get(countPointD) + " . ";

            System.out.println("Давление с БРС точка А: " + listDavlSBar.get(countPointA));
        }
        if (countPointB != 0) {
            System.out.println("Давление с БРС точка Б: " + listDavlSBar.get(countPointB));
        }
        if (countPointD != 0) {
            System.out.println("Давление с БРС точка Д: " + listDavlSBar.get(countPointD));
        }

        String strTVx = Arrays.toString(dataTvx.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2TVx = strTVx.replaceAll(" , ", " ");
        String[] arrayTVx = str2TVx.split(" ");
        ArrayList<String> listTVx = new ArrayList<>(Arrays.asList(arrayTVx));
        if (countPointA != 0) {
            System.out.println("tвоз_тп точка А: " + listTVx.get(countPointA));
        }
        if (countPointB != 0) {
            System.out.println("tвоз_тп точка Б: " + listTVx.get(countPointB));
        }
        if (countPointD != 0) {
            System.out.println("tвоз_тп точка Д: " + listTVx.get(countPointD));
        }

        String strNtk = Arrays.toString(dataNtk.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2Ntk = strNtk.replaceAll(" , ", " ");
        String[] arrayNtk = str2Ntk.split(" ");
        ArrayList<String> listNtk = new ArrayList<>(Arrays.asList(arrayNtk));
        if (countPointA != 0) {
            System.out.println("nтк точка А: " + listNtk.get(countPointA));
            countPointA = 0;
        }
        if (countPointB != 0) {
            System.out.println("nтк точка Б: " + listNtk.get(countPointB));
            countPointB = 0;
        }
        if (countPointD != 0) {
            System.out.println("nтк точка Д: " + listNtk.get(countPointD));
            countPointD = 0;
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
                String[] strArr = dataTextAll[i].split("\n");
                for (int j = 0; j < strArr.length; j++) {
                    if (strArr[j].startsWith("№ замера")) {
                        dataNumZam.add(strArr[j].substring(9));
                    }
                    if (strArr[j].startsWith("Давл. с БРС")) {
                        dataDavlSBar.add(strArr[j].substring(12));
                    }
                    if (strArr[j].startsWith("Твх_б")) {
                        dataTvx.add(strArr[j].substring(6));
                    }
                    if (strArr[j].startsWith("nтк") && !strArr[j].startsWith("nтк пр")) {
                        dataNtk.add(strArr[j].substring(4));
                    }
                }
            }
        }

        String strNumZ = Arrays.toString(dataNumZam.toArray()).replaceAll("[\\[\\]\\s]", " ");
        System.out.println(strNumZ);
        String str2NumZ = strNumZ.replaceAll(" , ", " ");
        System.out.println(str2NumZ);
        String[] arrayNumZam = str2NumZ.split(" ");
        ArrayList<String> listNumZ = new ArrayList<>(Arrays.asList(arrayNumZam));

        for (int i = 0; i < listNumZ.size(); i++) {
            if (listNumZ.get(i).equals(Example.pointA)) {
                countPointA = i;
            }
        }

        for (int j = 0; j < listNumZ.size(); j++) {
            if (listNumZ.get(j).equals(Example.pointB)) {
                countPointB = j;
            }
        }

        for (int k = 0; k < listNumZ.size(); k++) {
            if (listNumZ.get(k).equals(Example.pointD)) {
                countPointD = k;
            }
        }

        String strADavlSBar = Arrays.toString(dataDavlSBar.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2DavlSBar = strADavlSBar.replaceAll(" , ", " ");
        String[] arrayDavlSBar = str2DavlSBar.split(" ");
        ArrayList<String> listDavlSBar = new ArrayList<>(Arrays.asList(arrayDavlSBar));
        if (countPointA != 0) {
            resultA += "Давление с БРС точка А: " + listDavlSBar.get(countPointA) + " , " + ("Давление с БРС точка Б: " + listDavlSBar.get(countPointB) + " ,") +
                    "Давление с БРС точка Д: " + listDavlSBar.get(countPointD) + " . ";

            System.out.println("Давление с БРС точка А: " + listDavlSBar.get(countPointA));
        }
        if (countPointB != 0) {
            System.out.println("Давление с БРС точка Б: " + listDavlSBar.get(countPointB));
        }
        if (countPointD != 0) {
            System.out.println("Давление с БРС точка Д: " + listDavlSBar.get(countPointD));
        }

        String strTVx = Arrays.toString(dataTvx.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2TVx = strTVx.replaceAll(" , ", " ");
        String[] arrayTVx = str2TVx.split(" ");
        ArrayList<String> listTVx = new ArrayList<>(Arrays.asList(arrayTVx));
        if (countPointA != 0) {
            System.out.println("Твх_б точка А: " + listTVx.get(countPointA));
        }
        if (countPointB != 0) {
            System.out.println("Твх_б точка Б: " + listTVx.get(countPointB));
        }
        if (countPointD != 0) {
            System.out.println("Твх_б точка Д: " + listTVx.get(countPointD));
        }

        String strNtk = Arrays.toString(dataNtk.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2Ntk = strNtk.replaceAll(" , ", " ");
        String[] arrayNtk = str2Ntk.split(" ");
        ArrayList<String> listNtk = new ArrayList<>(Arrays.asList(arrayNtk));
        if (countPointA != 0) {
            System.out.println("nтк точка А: " + listNtk.get(countPointA));
            countPointA = 0;
        }
        if (countPointB != 0) {
            System.out.println("nтк точка Б: " + listNtk.get(countPointB));
            countPointB = 0;
        }
        if (countPointD != 0) {
            System.out.println("nтк точка Д: " + listNtk.get(countPointD));
            countPointD = 0;
        }

    }

}