import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public static String resultC = "";

    public static String resPointADavlSBar = "";
    public static String resPointATvxB = "";
    public static String resPointANtk = "";
    public static String resPointBDavlSBar = "";
    public static String resPointBTvxB = "";
    public static String resPointBNtk = "";
    public static String resPointDTvxB = "";
    public static String resPointDDavlSBar = "";
    public static String resPointDNtk = "";

    public static String allDataText = "";
    public static String allDataZamAct;
    public static String allDataACT = "";
    public static Set<String> dataNumZam = new LinkedHashSet<>();
    public static Set<String> dataDavlSBar = new LinkedHashSet<>();
    public static Set<String> dataTvx = new LinkedHashSet<>();
    public static Set<String> dataNtk = new LinkedHashSet<>();


    public ReadData(String davlSBar, String tVx, String ntk) {
        this.davlSBar = davlSBar;
        this.tVx = tVx;
        this.ntk = ntk;
    }

    public static void writeTxtDataFromExplG() {
        File theDir = new File("C:\\Users\\User\\Desktop\\txt_files");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(theDir.getAbsolutePath() + "\\" + Example.numberEngine)) {
//            writer.write(PrintData.createGUITable());
        } catch (IOException e) {
            // Handle the exception
        }
    }

//    public static void readActVK2500(File file) {
//        try (PDDocument document = PDDocument.load(file)) {
//            PDFTextStripper stripper = new PDFTextStripper();
//            allDataACT = stripper.getText(document);
//
//            String[] dataTextAct = allDataACT.split("Инженер по испытаниям:");
//
//            StringBuilder sb = new StringBuilder();
//            Set<String> dataFactParam = new LinkedHashSet<>();
//            String c = "";
//
//            if (Arrays.toString(dataTextAct).contains(Example.numberEngine) && Arrays.toString(dataTextAct).contains("Характеристика")) {
//                for (int i = 0; i < dataTextAct.length; i++) {
//                    String[] strArr = dataTextAct[i].split("\n");
//                    for (int j = 0; j < strArr.length; j++) {
//                        if (strArr[j].startsWith("Номера замеров:")) {
//                            sb.append(strArr[j].substring(16));
//                        }
//                        if (strArr[j].startsWith("Факт.")) {
//                            dataFactParam.add(strArr[j].substring(6));
//                        }
//                    }
//                }
//            }
//            System.out.println(sb);
//
//            String strAllFactP = Arrays.toString(dataFactParam.toArray()).replaceAll("[\\[\\]\\s]", " ");
////            System.out.println("first " + strAllFactP);
////            String str2 = strAllFactP.replaceAll(",", "");
//            String[] arr = strAllFactP.split(",");
////            for (String t : arr) {
////                System.out.print(t);
////            }
//            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(arr));
//            String pointStr = arrayList.get(3).substring(6, 10);
//
//            resultC += pointStr;
//
//            System.out.println("poinc c: " + resultC);
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void readTxt(File file) throws IOException {
//        Path actDb = Paths.get(file.getAbsolutePath());
//        Path actDbNew = Paths.get("C:\\Users\\User\\Desktop\\компоненты винды\\data\\actdb_220523_020312__new.txt");
//        try {
//            Files.copy(actDb, actDbNew, StandardCopyOption.REPLACE_EXISTING);
//            System.out.println("File is copied successfull!!!!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            String filePath = file.getPath();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder stringBuilder = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line + "\n");
                line = reader.readLine();
            }
            String fileContent = stringBuilder.toString();
            reader.close();
            String[] arr = fileContent.split("\n");
            for (String s : arr) {
                System.out.println(s);
            }
//            System.out.println(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//        try (FileInputStream fis = new FileInputStream(String.valueOf(actDbNew));
//             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
//             BufferedReader reader = new BufferedReader(isr)
//        ) {
//
//            String str;
//            while ((str = reader.readLine()) != null) {
//                System.out.println(str);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    //    public static void readActVK2500P(File file) {
//        try (PDDocument document = PDDocument.load(file)) {
//            PDFTextStripper stripper = new PDFTextStripper();
//            allDataACT = stripper.getText(document);
//
//            String[] dataTextAct = allDataACT.split("Инженер по испытаниям:");
//
//            StringBuilder sb = new StringBuilder();
//            Set<String> dataFactParam = new LinkedHashSet<>();
//            String c = "";
//
//            if (Arrays.toString(dataTextAct).contains(Example.numberEngine) && Arrays.toString(dataTextAct).contains("Характеристика")) {
//                for (int i = 0; i < dataTextAct.length; i++) {
//                    String[] strArr = dataTextAct[i].split("\n");
//                    for (int j = 0; j < strArr.length; j++) {
//                        if (strArr[j].startsWith("Номера замеров:")) {
//                            sb.append(strArr[j].substring(16));
//                        }
//                        if (strArr[j].startsWith("Факт.")) {
//                            dataFactParam.add(strArr[j].substring(6));
//                        }
//                    }
//                }
//            }
//            System.out.println(sb);
//
//            String strAllFactP = Arrays.toString(dataFactParam.toArray()).replaceAll("[\\[\\]\\s]", " ");
////            System.out.println("first " + strAllFactP);
////            String str2 = strAllFactP.replaceAll(",", "");
//            String[] arr = strAllFactP.split(",");
////            for (String t : arr) {
////                System.out.print(t);
////            }
//            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(arr));
//            String pointStr = arrayList.get(4).substring(6, 10);
//
//            resultC += pointStr;
//
//            System.out.println("poinc c: " + pointStr);
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public static void readNumZamActVk(File file) {
        try {
            PDDocument document = PDDocument.load(file);
            PDFTextStripper stripper = new PDFTextStripper();
            allDataZamAct = stripper.getText(document);
            if (Example.comboBox.getSelectedItem().equals("ВК-2500")) {
                VK2500_15ST.extractDataFromActVK2500St15();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void read(File file) {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            allDataText = stripper.getText(document);

            if (Example.comboBox.getSelectedItem().equals("ВК-2500")) {
                VK2500_15ST.extractDataVK250015St();
            }
            if (Example.comboBox.getSelectedItem().equals("ТВ3-117ВМ")) {
                TV3117NEW.extractDataTV3117Vm();
            }
            if (Example.comboBox.getSelectedItem().equals("ВК-2500П")) {
                VK2500P.extractDataVK2500P();
            }
//            System.out.println(allDataText);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

//    public static void extractDataVK2500P() {
//        String[] dataTextAll = allDataText.split("Инженер по испытаниям:");
//
//        Set<String> dataNumZam = new LinkedHashSet<>();
//        Set<String> dataDavlSBar = new LinkedHashSet<>();
//        Set<String> dataTvx = new LinkedHashSet<>();
//        Set<String> dataNtk = new LinkedHashSet<>();
//
//        if (Arrays.toString(dataTextAll).contains(Example.numberEngine) && Arrays.toString(dataTextAll).contains("Энергетические параметры")) {
//            for (int i = 0; i < dataTextAll.length; i++) {
//                String[] strArr = dataTextAll[i].split("\n");
//                for (int j = 0; j < strArr.length; j++) {
//                    if (strArr[j].startsWith("№ замера")) {
//                        dataNumZam.add(strArr[j].substring(9));
//                    }
//                    if (strArr[j].startsWith("Давл. с БРС")) {
//                        dataDavlSBar.add(strArr[j].substring(12));
//                    }
//                    if (strArr[j].startsWith("Твх_б")) {
//                        dataTvx.add(strArr[j].substring(6));
//                    }
//                    if (strArr[j].startsWith("nтк") && !strArr[j].startsWith("nтк пр")) {
//                        dataNtk.add(strArr[j].substring(4));
//                    }
//                }
//            }
//        }
//
//        String strNumZ = Arrays.toString(dataNumZam.toArray()).replaceAll("[\\[\\]\\s]", " ");
//        System.out.println(strNumZ);
//        String str2NumZ = strNumZ.replaceAll(" , ", " ");
//        System.out.println(str2NumZ);
//        String[] arrayNumZam = str2NumZ.split(" ");
//        ArrayList<String> listNumZ = new ArrayList<>(Arrays.asList(arrayNumZam));
//
//        for (int i = 0; i < listNumZ.size(); i++) {
//            if (listNumZ.get(i).equals(Example.pointA)) {
//                countPointA = i;
//            }
//        }
//
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
//            resPointADavlSBar += listDavlSBar.get(countPointA);
//
//
////            resultA += "Давление с БРС точка А: " + listDavlSBar.get(countPointA) + " , " + ("Давление с БРС точка Б: " + listDavlSBar.get(countPointB) + " ,") +
////                    "Давление с БРС точка Д: " + listDavlSBar.get(countPointD) + " . ";
//
//            System.out.println("Давление с БРС точка А: " + listDavlSBar.get(countPointA));
//        }
//
//        String strTVx = Arrays.toString(dataTvx.toArray()).replaceAll("[\\[\\]\\s]", " ");
//        String str2TVx = strTVx.replaceAll(" , ", " ");
//        String[] arrayTVx = str2TVx.split(" ");
//        ArrayList<String> listTVx = new ArrayList<>(Arrays.asList(arrayTVx));
//        if (countPointA != 0) {
//            System.out.println("Твх_б точка А: " + listTVx.get(countPointA));
//        }
//
//        String strNtk = Arrays.toString(dataNtk.toArray()).replaceAll("[\\[\\]\\s]", " ");
//        String str2Ntk = strNtk.replaceAll(" , ", " ");
//        String[] arrayNtk = str2Ntk.split(" ");
//        ArrayList<String> listNtk = new ArrayList<>(Arrays.asList(arrayNtk));
//        if (countPointA != 0) {
//            System.out.println("nтк точка А: " + listNtk.get(countPointA));
//            countPointA = 0;
//        }
//
//    }

//    public static void extractDataTV3117Vm() {
//        String[] dataTextAll = allDataText.split("Инженер по испытаниям:");
//
//        Set<String> dataNumZam = new LinkedHashSet<>();
//        Set<String> dataDavlSBar = new LinkedHashSet<>();
//        Set<String> dataTvx = new LinkedHashSet<>();
//        Set<String> dataNtk = new LinkedHashSet<>();
//
//        if (Arrays.toString(dataTextAll).contains(Example.numberEngine) && Arrays.toString(dataTextAll).contains("Энергетические параметры")) {
//            for (int i = 0; i < dataTextAll.length; i++) {
//                String[] strArr = dataTextAll[i].split("\n");
//                for (int j = 0; j < strArr.length; j++) {
//                    if (strArr[j].startsWith("№ замера")) {
//                        dataNumZam.add(strArr[j].substring(9));
//                    }
//                    if (strArr[j].startsWith("Давл. с БРС")) {
//                        dataDavlSBar.add(strArr[j].substring(12));
//                    }
//                    if (strArr[j].startsWith("tвоз_тп")) {
//                        dataTvx.add(strArr[j].substring(8));
//                    }
//                    if (strArr[j].startsWith("nтк") && !strArr[j].startsWith("nтк пр")) {
//                        dataNtk.add(strArr[j].substring(4));
//                    }
//                }
//            }
//        }
//
//        String strNumZ = Arrays.toString(dataNumZam.toArray()).replaceAll("[\\[\\]\\s]", " ");
//        System.out.println(strNumZ);
//        String str2NumZ = strNumZ.replaceAll(" , ", " ");
//        System.out.println(str2NumZ);
//        String[] arrayNumZam = str2NumZ.split(" ");
//        ArrayList<String> listNumZ = new ArrayList<>(Arrays.asList(arrayNumZam));
//
//        for (int i = 0; i < listNumZ.size(); i++) {
//            if (listNumZ.get(i).equals(Example.pointA)) {
//                countPointA = i;
//            }
//        }
//
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
//            System.out.println("tвоз_тп точка А: " + listTVx.get(countPointA));
//        }
//        if (countPointB != 0) {
//            System.out.println("tвоз_тп точка Б: " + listTVx.get(countPointB));
//        }
//        if (countPointD != 0) {
//            System.out.println("tвоз_тп точка Д: " + listTVx.get(countPointD));
//        }
//
//        String strNtk = Arrays.toString(dataNtk.toArray()).replaceAll("[\\[\\]\\s]", " ");
//        String str2Ntk = strNtk.replaceAll(" , ", " ");
//        String[] arrayNtk = str2Ntk.split(" ");
//        ArrayList<String> listNtk = new ArrayList<>(Arrays.asList(arrayNtk));
//        if (countPointA != 0) {
//            System.out.println("nтк точка А: " + listNtk.get(countPointA));
//            countPointA = 0;
//        }
//        if (countPointB != 0) {
//            System.out.println("nтк точка Б: " + listNtk.get(countPointB));
//            countPointB = 0;
//        }
//        if (countPointD != 0) {
//            System.out.println("nтк точка Д: " + listNtk.get(countPointD));
//            countPointD = 0;
//        }
//
//    }

//    public static void extractDataVK2500() {
//        String[] dataTextAll = allDataText.split("Инженер по испытаниям:");
//
//        Set<String> dataNumZam = new LinkedHashSet<>();
//        Set<String> dataDavlSBar = new LinkedHashSet<>();
//        Set<String> dataTvx = new LinkedHashSet<>();
//        Set<String> dataNtk = new LinkedHashSet<>();
//
//        if (Arrays.toString(dataTextAll).contains(Example.numberEngine) && Arrays.toString(dataTextAll).contains("Энергетические параметры")) {
//            for (int i = 0; i < dataTextAll.length; i++) {
//                String[] strArr = dataTextAll[i].split("\n");
//                for (int j = 0; j < strArr.length; j++) {
//                    if (strArr[j].startsWith("№ замера")) {
//                        dataNumZam.add(strArr[j].substring(9));
//                    }
//                    if (strArr[j].startsWith("Давл. с БРС")) {
//                        dataDavlSBar.add(strArr[j].substring(12));
//                    }
//                    if (strArr[j].startsWith("Твх_б")) {
//                        dataTvx.add(strArr[j].substring(6));
//                    }
//                    if (strArr[j].startsWith("nтк") && !strArr[j].startsWith("nтк пр")) {
//                        dataNtk.add(strArr[j].substring(4));
//                    }
//                }
//            }
//        }
//
//        String strNumZ = Arrays.toString(dataNumZam.toArray()).replaceAll("[\\[\\]\\s]", " ");
//        System.out.println(strNumZ);
//        String str2NumZ = strNumZ.replaceAll(" , ", " ");
//        System.out.println(str2NumZ);
//        String[] arrayNumZam = str2NumZ.split(" ");
//        ArrayList<String> listNumZ = new ArrayList<>(Arrays.asList(arrayNumZam));
//
//        for (int i = 0; i < listNumZ.size(); i++) {
//            if (listNumZ.get(i).equals(Example.pointA)) {
//                countPointA = i;
//            }
//        }
//
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
////            resultA += "Давление с БРС точка А: " + listDavlSBar.get(countPointA) + " , " + ("Давление с БРС точка Б: " + listDavlSBar.get(countPointB) + " ,") +
////                    "Давление с БРС точка Д: " + listDavlSBar.get(countPointD) + " . ";
//            resPointADavlSBar += listDavlSBar.get(countPointA);
//
//            System.out.println("Давление с БРС точка А: " + listDavlSBar.get(countPointA));
//        }
//        if (countPointB != 0) {
//            resPointBDavlSBar += listDavlSBar.get(countPointB);
//            System.out.println("Давление с БРС точка Б: " + listDavlSBar.get(countPointB));
//        }
//        if (countPointD != 0) {
//            resPointDDavlSBar += listDavlSBar.get(countPointD);
//            System.out.println("Давление с БРС точка Д: " + listDavlSBar.get(countPointD));
//        }
//
//        String strTVx = Arrays.toString(dataTvx.toArray()).replaceAll("[\\[\\]\\s]", " ");
//        String str2TVx = strTVx.replaceAll(" , ", " ");
//        String[] arrayTVx = str2TVx.split(" ");
//        ArrayList<String> listTVx = new ArrayList<>(Arrays.asList(arrayTVx));
//        if (countPointA != 0) {
//            resPointATvxB += listTVx.get(countPointA);
//            System.out.println("Твх_б точка А: " + listTVx.get(countPointA));
//        }
//        if (countPointB != 0) {
//            resPointBTvxB += listTVx.get(countPointB);
//            System.out.println("Твх_б точка Б: " + listTVx.get(countPointB));
//        }
//        if (countPointD != 0) {
//            resPointDTvxB += listTVx.get(countPointD);
//            System.out.println("Твх_б точка Д: " + listTVx.get(countPointD));
//        }
//
//        String strNtk = Arrays.toString(dataNtk.toArray()).replaceAll("[\\[\\]\\s]", " ");
//        String str2Ntk = strNtk.replaceAll(" , ", " ");
//        String[] arrayNtk = str2Ntk.split(" ");
//        ArrayList<String> listNtk = new ArrayList<>(Arrays.asList(arrayNtk));
//        if (countPointA != 0) {
//            resPointANtk += listNtk.get(countPointA);
//            System.out.println("nтк точка А: " + listNtk.get(countPointA));
//            countPointA = 0;
//        }
//        if (countPointB != 0) {
//            resPointBNtk += listNtk.get(countPointB);
//            System.out.println("nтк точка Б: " + listNtk.get(countPointB));
//            countPointB = 0;
//        }
//        if (countPointD != 0) {
//            resPointDNtk += listNtk.get(countPointD);
//            System.out.println("nтк точка Д: " + listNtk.get(countPointD));
//            countPointD = 0;
//        }
//
//    }

}