import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class VK2500P {

    public static int countPointAVK2500P;

    public static String resPointADavlSBarVK2500P = "";
    public static String resPointATvxBVK2500P = "";
    public static String resPointANtkVK2500P = "";

    public static String allDataTextVK2500 = "";
    public static String allDataACTVK2500P = "";

    public static String resultA = "";
    public static String resultB = "";
    public static String resultD = "";
    public static String resultCVK2500PNtk = "";


    public static void readActVK2500P(File file) {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            allDataACTVK2500P = stripper.getText(document);

            String[] dataTextAct = allDataACTVK2500P.split("Инженер по испытаниям:");

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

            resultCVK2500PNtk += pointStr;

            System.out.println("poinc c: " + resultCVK2500PNtk);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void extractDataVK2500P() {
        String[] dataTextAllVK2500P = ReadData.allDataText.split("Инженер по испытаниям:");

        Set<String> dataNumZamVK2500P = new LinkedHashSet<>();
        Set<String> dataDavlSBarVK2500P = new LinkedHashSet<>();
        Set<String> dataTvxVK2500P = new LinkedHashSet<>();
        Set<String> dataNtkVK2500P = new LinkedHashSet<>();

        if (Arrays.toString(dataTextAllVK2500P).contains(Example.numberEngine) && Arrays.toString(dataTextAllVK2500P).contains("Энергетические параметры")) {
            for (int i = 0; i < dataTextAllVK2500P.length; i++) {
                String[] strArr = dataTextAllVK2500P[i].split("\n");
                for (int j = 0; j < strArr.length; j++) {
                    if (strArr[j].startsWith("№ замера")) {
                        dataNumZamVK2500P.add(strArr[j].substring(9));
                    }
                    if (strArr[j].startsWith("Давл. с БРС")) {
                        dataDavlSBarVK2500P.add(strArr[j].substring(12));
                    }
                    if (strArr[j].startsWith("Tвх_б")) {
                        dataTvxVK2500P.add(strArr[j].substring(6));
                    }
                    if (strArr[j].startsWith("nтк") && !strArr[j].startsWith("nтк пр")) {
                        dataNtkVK2500P.add(strArr[j].substring(4));
                    }
                }
            }
        }

        String strNumZ = Arrays.toString(dataNumZamVK2500P.toArray()).replaceAll("[\\[\\]\\s]", " ");
        System.out.println(strNumZ);
        String str2NumZ = strNumZ.replaceAll(" , ", " ");
        System.out.println(str2NumZ);
        String[] arrayNumZam = str2NumZ.split(" ");
        ArrayList<String> listNumZ = new ArrayList<>(Arrays.asList(arrayNumZam));

        for (int i = 0; i < listNumZ.size(); i++) {
            if (listNumZ.get(i).equals(Example.pointA)) {
                countPointAVK2500P = i;
            }
        }

        String strADavlSBar = Arrays.toString(dataDavlSBarVK2500P.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2DavlSBar = strADavlSBar.replaceAll(" , ", " ");
        String[] arrayDavlSBar = str2DavlSBar.split(" ");
        ArrayList<String> listDavlSBar = new ArrayList<>(Arrays.asList(arrayDavlSBar));
        if (countPointAVK2500P != 0) {
            resPointADavlSBarVK2500P += listDavlSBar.get(countPointAVK2500P);

            System.out.println("Давление с БРС точка А: " + listDavlSBar.get(countPointAVK2500P));
        }

        String strTVx = Arrays.toString(dataTvxVK2500P.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2TVx = strTVx.replaceAll(" , ", " ");
        String[] arrayTVx = str2TVx.split(" ");
        ArrayList<String> listTVx = new ArrayList<>(Arrays.asList(arrayTVx));
        if (countPointAVK2500P != 0) {
            resPointATvxBVK2500P += listTVx.get(countPointAVK2500P);

            System.out.println("Твх_б точка А: " + listTVx.get(countPointAVK2500P));
        }


        String strNtk = Arrays.toString(dataNtkVK2500P.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2Ntk = strNtk.replaceAll(" , ", " ");
        String[] arrayNtk = str2Ntk.split(" ");
        ArrayList<String> listNtk = new ArrayList<>(Arrays.asList(arrayNtk));
        if (countPointAVK2500P != 0) {
            resPointANtkVK2500P += listNtk.get(countPointAVK2500P);

            System.out.println("nтк точка А: " + listNtk.get(countPointAVK2500P));
//            countPointAVK2500P = 0;
        }
    }
}
