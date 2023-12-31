import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class VK2500 {
    public static int countPointAVK2500;
    public static int countPointBVK2500;
    public static int countPointDVK2500;

    public static String resPointADavlSBarVK2500 = "";
    public static String resPointATvxBVK2500 = "";
    public static String resPointANtkVK2500 = "";
    public static String resPointBDavlSBarVK2500 = "";
    public static String resPointBTvxBVK2500 = "";
    public static String resPointBNtkVK2500 = "";
    public static String resPointDTvxBVK2500 = "";
    public static String resPointDDavlSBarVK2500 = "";
    public static String resPointDNtkVK2500 = "";

    public static String allDataTextVK2500 = "";
    public static String allDataACTVK2500 = "";

    public static String resultA = "";
    public static String resultB = "";
    public static String resultD = "";
    public static String resultCVK2500Ntk = "";

    public static void readActVK2500(File file) {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            allDataACTVK2500 = stripper.getText(document);

            String[] dataTextAct = allDataACTVK2500.split("Инженер по испытаниям:");

            StringBuilder sb = new StringBuilder();
            Set<String> dataFactParam = new LinkedHashSet<>();
            String c = "";

            if (Arrays.toString(dataTextAct).contains(Example.numberEngine) && Arrays.toString(dataTextAct).contains("Характеристика")) {
                for (int i = 0; i < dataTextAct.length; i++) {
                    String[] strArr = dataTextAct[i].split("\n");
                    for (int j = 0; j < strArr.length; j++) {
                        if (strArr[j].startsWith("Номера замеров:")) {
                            sb.append(strArr[j].substring(16));
                        }
                        if (strArr[j].startsWith("Факт.")) {
                            dataFactParam.add(strArr[j].substring(6));
                        }
                    }
                }
            }
            System.out.println(sb);

            String strAllFactP = Arrays.toString(dataFactParam.toArray()).replaceAll("[\\[\\]\\s]", " ");
//            System.out.println("first " + strAllFactP);
//            String str2 = strAllFactP.replaceAll(",", "");
            String[] arr = strAllFactP.split(",");
//            for (String t : arr) {
//                System.out.print(t);
//            }
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(arr));
            String pointStr = arrayList.get(3).substring(6, 10);

            resultCVK2500Ntk += pointStr;

            System.out.println("poinc c: " + resultCVK2500Ntk);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void extractDataVK2500() {

        String[] dataTextAllVK2500 = ReadData.allDataText.split("Инженер по испытаниям:");

        Set<String> dataNumZamVK2500 = new LinkedHashSet<>();
        Set<String> dataDavlSBarVK2500 = new LinkedHashSet<>();
        Set<String> dataTvxVK2500 = new LinkedHashSet<>();
        Set<String> dataNtkVK2500 = new LinkedHashSet<>();

        Set<String> resSetPointA = new LinkedHashSet<>();
        Set<String> resSetPointB = new LinkedHashSet<>();
        Set<String> resSetPointD = new LinkedHashSet<>();

        if (Arrays.toString(dataTextAllVK2500).contains(Example.numberEngine) && Arrays.toString(dataTextAllVK2500).contains("Энергетические параметры")) {
            for (int i = 0; i < dataTextAllVK2500.length; i++) {
                String[] strArr = dataTextAllVK2500[i].split("\n");
                for (int j = 0; j < strArr.length; j++) {
                    if (strArr[j].startsWith("№ замера")) {
                        dataNumZamVK2500.add(strArr[j].substring(9));
                    }
                    if (strArr[j].startsWith("Давл. с БРС")) {
                        dataDavlSBarVK2500.add(strArr[j].substring(12));
                    }
                    if (strArr[j].startsWith("nтк") && !strArr[j].startsWith("nтк пр")) {
                        dataNtkVK2500.add(strArr[j].substring(4));
                    }
                    if (strArr[j].startsWith("Твх_б")) {
                        dataTvxVK2500.add(strArr[j].substring(6));
                    }

                }
            }
        }

        String strNumZ = Arrays.toString(dataNumZamVK2500.toArray()).replaceAll("[\\[\\]\\s]", " ");
        System.out.println(strNumZ);
        String str2NumZ = strNumZ.replaceAll(" , ", " ");
        System.out.println(str2NumZ);
        String[] arrayNumZam = str2NumZ.split(" ");
        ArrayList<String> listNumZ = new ArrayList<>(Arrays.asList(arrayNumZam));

        for (int i = 0; i < listNumZ.size(); i++) {
            if (listNumZ.get(i).equals(Example.pointA)) {
                countPointAVK2500 = i;
            }
        }

        for (int j = 0; j < listNumZ.size(); j++) {
            if (listNumZ.get(j).equals(Example.pointB)) {
                countPointBVK2500 = j;
            }
        }

        for (int k = 0; k < listNumZ.size(); k++) {
            if (listNumZ.get(k).equals(Example.pointD)) {
                countPointDVK2500 = k;
            }
        }

        String strADavlSBar = Arrays.toString(dataDavlSBarVK2500.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2DavlSBar = strADavlSBar.replaceAll(" , ", " ");
        String[] arrayDavlSBar = str2DavlSBar.split(" ");
        ArrayList<String> listDavlSBar = new ArrayList<>(Arrays.asList(arrayDavlSBar));
        if (countPointAVK2500 != 0) {
//            resultA += "Давление с БРС точка А: " + listDavlSBar.get(countPointA) + " , " + ("Давление с БРС точка Б: " + listDavlSBar.get(countPointB) + " ,") +
//                    "Давление с БРС точка Д: " + listDavlSBar.get(countPointD) + " . ";
//            resPointADavlSBarVK2500 += listDavlSBar.get(countPointAVK2500);
            resSetPointA.add(listDavlSBar.get(countPointAVK2500));

            System.out.println("Давление с БРС точка А: " + resPointADavlSBarVK2500);
        }
        if (countPointBVK2500 != 0) {
            resPointBDavlSBarVK2500 += listDavlSBar.get(countPointBVK2500);
//            resSetPointB.add(listDavlSBar.get(countPointBVK2500));
            System.out.println("length: " + resPointBDavlSBarVK2500.length());
            System.out.println("Давление с БРС точка Б: " + resPointBDavlSBarVK2500);
        }
        if (countPointDVK2500 != 0) {
//            resSetPointD.add(listDavlSBar.get(countPointDVK2500));
            resPointDDavlSBarVK2500 += listDavlSBar.get(countPointDVK2500);
            System.out.println("Давление с БРС точка Д: " + resPointDDavlSBarVK2500);
        }

        String strTVx = Arrays.toString(dataTvxVK2500.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2TVx = strTVx.replaceAll(" , ", " ");
        String[] arrayTVx = str2TVx.split(" ");
        ArrayList<String> listTVx = new ArrayList<>(Arrays.asList(arrayTVx));
        if (countPointAVK2500 != 0) {
//            resSetPointA.add(listTVx.get(countPointAVK2500));
            resPointATvxBVK2500 += listTVx.get(countPointAVK2500);
            System.out.println("Твх_б точка А: " + resPointATvxBVK2500);
        }
        if (countPointBVK2500 != 0) {
//            resSetPointB.add(listTVx.get(countPointBVK2500));
            resPointBTvxBVK2500 += listTVx.get(countPointBVK2500);
            System.out.println("length: " + resPointBTvxBVK2500.length());
            System.out.println("Твх_б точка Б: " + resPointBTvxBVK2500);
        }
        if (countPointDVK2500 != 0) {
//            resSetPointD.add(listTVx.get(countPointDVK2500));
            resPointDTvxBVK2500 += listTVx.get(countPointDVK2500);
            System.out.println("Твх_б точка Д: " + resPointDTvxBVK2500);
        }

        String strNtk = Arrays.toString(dataNtkVK2500.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2Ntk = strNtk.replaceAll(" , ", " ");
        String[] arrayNtk = str2Ntk.split(" ");
        ArrayList<String> listNtk = new ArrayList<>(Arrays.asList(arrayNtk));
        if (countPointAVK2500 != 0) {
//            resSetPointA.add(listNtk.get(countPointAVK2500));
            resPointANtkVK2500 += listNtk.get(countPointAVK2500);
            System.out.println("nтк точка А: " + resPointANtkVK2500);
            countPointAVK2500 = 0;
        }
        if (countPointBVK2500 != 0) {
//            resSetPointB.add(listNtk.get(countPointBVK2500));
            resPointBNtkVK2500 += listNtk.get(countPointBVK2500);
            System.out.println("length: " + resPointBNtkVK2500.length());
            System.out.println("nтк точка Б: " + resPointBNtkVK2500);
            countPointBVK2500 = 0;
        }
        if (countPointDVK2500 != 0) {
//            resSetPointD.add(listNtk.get(countPointDVK2500));
            resPointDNtkVK2500 += listNtk.get(countPointDVK2500);
            System.out.println("nтк точка Д: " + resPointDNtkVK2500);
            countPointDVK2500 = 0;
        }

//        for (String a : resSetPointA) {
//            System.out.println(a);
//        }
//        for (String b : resSetPointB) {
//            System.out.println(b);
//        }
//        for (String d : resSetPointD) {
//            System.out.print(d);
//        }
//        try {
//            List<String> listPA = new ArrayList<>(resSetPointA);
//            List<String> listPB = new ArrayList<>(resSetPointB);
//            List<String> listPD = new ArrayList<>(resSetPointD);
//
//            resPointADavlSBarVK2500 += listPA.get(0);
//            resPointBDavlSBarVK2500 += listPB.get(0);
//            resPointDDavlSBarVK2500 += listPD.get(0);
//
//            resPointATvxBVK2500 += listPA.get(1);
//            resPointBTvxBVK2500 += listPB.get(1);
//            resPointDTvxBVK2500 += listPD.get(1);
//
//            resPointANtkVK2500 += listPA.get(2);
//            resPointBNtkVK2500 += listPB.get(2);
//            resPointDNtkVK2500 += listPD.get(2);
//        } catch (Exception ignored) {
//
//        }
    }
}
