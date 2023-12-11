import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class VK2500_15ST {
    public static int countPointAVK250015St;
    public static int countPointBVK250015St;
    public static int countPointDVK250015St;

    public static String resPointADavlSBarVK250015St = "";
    public static String resPointATvxBVK250015St = "";
    public static String resPointANtkVK250015St = "";
    public static String resPointBDavlSBarVK250015St = "";
    public static String resPointBTvxBVK250015St = "";
    public static String resPointBNtkVK250015St = "";
    public static String resPointDTvxBVK250015St = "";
    public static String resPointDDavlSBarVK250015St = "";
    public static String resPointDNtkVK250015St = "";

//    public static String allDataTextVK2500 = "";
    public static String allDataACTVK250015St = "";
    public static String resultCVK2500Ntk15St = "";
    public static String numberZamAct = "";

    public static Set<String> resSetPointAVK250015St = new LinkedHashSet<>();
    public static Set<String> resSetPointBVK250015St = new LinkedHashSet<>();
    public static Set<String> resSetPointDVK250015St = new LinkedHashSet<>();

    public static List<String> listNumberZamACT15St = new ArrayList<>();

    public static String setResultRejimVk2500_15St = "";
    public static String setResultPmvxVk2500_15St = "";
    public static String setResultPt1Vk2500_15St = "";
    public static String setResultTmvxVk2500_15St = "";


    public static void extractDataFromActVK2500St15() {
        String[] dataTextAllFromActVk2500st15 = ReadData.allDataText.split("Инженер по испытаниям:");
        for (String s : dataTextAllFromActVk2500st15) {
            System.out.println(s);
        }
    }

    public static void readActVK250015St(File file) {
//        List<String> listNumberZamACT15St = new ArrayList<String>();
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            allDataACTVK250015St = stripper.getText(document);

            String[] dataTextAct = allDataACTVK250015St.split("Инженер по испытаниям:");

//            StringBuilder sb = new StringBuilder();
            Set<String> set = new LinkedHashSet<>();
            Set<String> dataFactParam = new LinkedHashSet<>();
            String c = "";

            if (Arrays.toString(dataTextAct).contains(Example.numberEngine) && Arrays.toString(dataTextAct).contains("Характеристика")) {
                for (int i = 0; i < dataTextAct.length; i++) {
                    String[] strArr = dataTextAct[i].split("\n");
                    for (int j = 0; j < strArr.length; j++) {
                        if (strArr[j].startsWith("Номера замеров:")) {
                            set.add(strArr[j].substring(16));
                        }
//                        if (strArr[j].startsWith("Номера замеров:")) {
//                            sb.append(strArr[j].substring(16));
//                        }
                        if (strArr[j].startsWith("Факт.")) {
                            dataFactParam.add(strArr[j].substring(6));
                        }
                    }
                }
            }

            String strAllFactP = Arrays.toString(dataFactParam.toArray()).replaceAll("[\\[\\]\\s]", " ");
//            System.out.println("first " + strAllFactP);
//            String str2 = strAllFactP.replaceAll(",", "");
            String[] arr = strAllFactP.split(",");
//            for (String t : arr) {
//                System.out.print(t);
//            }
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(arr));
            String pointStr = arrayList.get(3).substring(6, 10);

            resultCVK2500Ntk15St += pointStr;

            System.out.println("poinc c: " + resultCVK2500Ntk15St);

            String strNumZam = Arrays.toString(set.toArray()).replaceAll("[\\[\\]\\s]", " ").trim();
            String[] arrNumZam = strNumZam.split(", ");

            listNumberZamACT15St.addAll(Arrays.asList(arrNumZam));

            for (String d : listNumberZamACT15St) {
                numberZamAct += d + " ";
                System.out.println(d);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] convertSetToStringArray(List<String> list) {
        StringBuilder str = new StringBuilder();
        for (String s : list) {
            str.append(s);
        }
        return str.toString().split(" ");
    }


    public static void extractDataVK250015St() {

        String[] dataTextAllVK2500 = ReadData.allDataText.split("Инженер по испытаниям:");

        Set<String> dataNumZamVK2500 = new LinkedHashSet<>();
        Set<String> dataDavlSBarVK2500 = new LinkedHashSet<>();
        Set<String> dataTvxVK2500 = new LinkedHashSet<>();
        Set<String> dataNtkVK2500 = new LinkedHashSet<>();

        Set<String> tmp = new LinkedHashSet<>();
        Set<String> Rejim = new LinkedHashSet<>();

        Set<String> Bx1TK = new LinkedHashSet<>();
        Set<String> By1TK = new LinkedHashSet<>();
        Set<String> Bz1TK = new LinkedHashSet<>();

        Set<String> Bx1CT = new LinkedHashSet<>();
        Set<String> By1CT = new LinkedHashSet<>();
        Set<String> Bz1CT = new LinkedHashSet<>();

        Set<String> Bx4TK = new LinkedHashSet<>();
        Set<String> By4TK = new LinkedHashSet<>();
        Set<String> Bz4TK = new LinkedHashSet<>();

        Set<String> Bx4CT = new LinkedHashSet<>();
        Set<String> By4CT = new LinkedHashSet<>();
        Set<String> Bz4CT = new LinkedHashSet<>();

        Set<String> Pmvx = new LinkedHashSet<>();
        Set<String> Pt1 = new LinkedHashSet<>();
        Set<String> Tmvx = new LinkedHashSet<>();


        if (Arrays.toString(dataTextAllVK2500).contains(Example.numberEngine) && Arrays.toString(dataTextAllVK2500).contains("Энергетические параметры")
                && Arrays.toString(dataTextAllVK2500).contains("№ замера " + listNumberZamACT15St.get(0))) {
            for (int i = 0; i < dataTextAllVK2500.length; i++) {
                String[] strArr = dataTextAllVK2500[i].split("\n");
                for (int j = 0; j < strArr.length; j++) {
                    if (strArr[j].startsWith("№ замера")) {
                        tmp.add(strArr[j].substring(9));
                    }
                    if (strArr[j].startsWith("Режим")) {
                        Rejim.add(strArr[j].substring(6));
                    }
                    if (strArr[j].startsWith("Bx1 ТК")) {
                        Bx1TK.add(strArr[j].substring(7));
                    }
                    if (strArr[j].startsWith("By1 ТК")) {
                        By1TK.add(strArr[j].substring(7));
                    }
                    if (strArr[j].startsWith("Bz1 ТК")) {
                        Bz1TK.add(strArr[j].substring(7));
                    }
                    if (strArr[j].startsWith("Bx1 СТ")) {
                        Bx1CT.add(strArr[j].substring(7));
                    }
                    if (strArr[j].startsWith("By1 СТ")) {
                        By1CT.add(strArr[j].substring(7));
                    }
                    if (strArr[j].startsWith("Bz1 СТ")) {
                        Bz1CT.add(strArr[j].substring(7));
                    }
                    if (strArr[j].startsWith("Bx4 ТК")) {
                        Bx4TK.add(strArr[j].substring(7));
                    }
                    if (strArr[j].startsWith("By4 ТК")) {
                        By4TK.add(strArr[j].substring(7));
                    }
                    if (strArr[j].startsWith("Bz4 ТК")) {
                        Bz4TK.add(strArr[j].substring(7));
                    }
                    if (strArr[j].startsWith("Bx4 СТ")) {
                        Bx4CT.add(strArr[j].substring(7));
                    }
                    if (strArr[j].startsWith("By4 СТ")) {
                        By4CT.add(strArr[j].substring(7));
                    }
                    if (strArr[j].startsWith("Bz4 СТ")) {
                        Bz4CT.add(strArr[j].substring(7));
                    }
                    if (strArr[j].startsWith("Pм вх")) {
                        Pmvx.add(strArr[j].substring(6));
                    }
                    if (strArr[j].startsWith("Pт1")) {
                        Pt1.add(strArr[j].substring(4));
                    }
                    if (strArr[j].startsWith("tм вых")) {
                        Tmvx.add(strArr[j].substring(7));
                    }
                }
            }
        }
        String resultSetRejim = Rejim.toString();
        resultSetRejim = resultSetRejim.replaceAll("\\,|\\[|\\]|\\s", " ").replaceAll("  ","").trim();
        setResultRejimVk2500_15St += resultSetRejim;
        System.out.println("Строка получнная из сета омеров замеров: " + resultSetRejim);

        String resultSetPmvx = Pmvx.toString();
        resultSetPmvx = resultSetPmvx.replaceAll("\\,|\\[|\\]|\\s", " ").replaceAll("  ","").trim();
        setResultPmvxVk2500_15St += resultSetPmvx;
        System.out.println("Строка полученная из сета п масла входа: " + resultSetPmvx);

        String resultSetPt1 = Pt1.toString();
        resultSetPt1 = resultSetPt1.replaceAll("\\,|\\[|\\]|\\s", " ").replaceAll("  ","").trim();
        setResultPt1Vk2500_15St += resultSetPt1;
        System.out.println("Строка полученная из сета Pt1: " + resultSetPt1);

        String resultSetTmvx = Tmvx.toString();
        resultSetTmvx = resultSetTmvx.replaceAll("\\,|\\[|\\]|\\s", " ").replaceAll("  ","").trim();
        setResultTmvxVk2500_15St += resultSetTmvx;
        System.out.println("Строка полученная из сета Tmvx: " + resultSetTmvx);



        if (Arrays.toString(dataTextAllVK2500).contains(Example.numberEngine) && Arrays.toString(dataTextAllVK2500)
                .contains("Энергетические параметры")) {
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
        //        System.out.println(strNumZ);
        String str2NumZ = strNumZ.replaceAll(" , ", " ");
        System.out.println(str2NumZ);
        String[] arrayNumZam = str2NumZ.split(" ");
        ArrayList<String> listNumZ = new ArrayList<>(Arrays.asList(arrayNumZam));

        for (int i = 0; i < listNumZ.size(); i++) {
            if (listNumZ.get(i).equals(Example.pointB)) {
                countPointBVK250015St = i;
            }
        }

        for (int j = 0; j < listNumZ.size(); j++) {
            if (listNumZ.get(j).equals(Example.pointD)) {
                countPointDVK250015St = j;
            }
        }

        for (int k = 0; k < listNumZ.size(); k++) {
            if (listNumZ.get(k).equals(Example.pointA)) {
                countPointAVK250015St = k;
            }
        }

        String strADavlSBar = Arrays.toString(dataDavlSBarVK2500.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2DavlSBar = strADavlSBar.replaceAll(" , ", " ");
        String[] arrayDavlSBar = str2DavlSBar.split(" ");
        ArrayList<String> listDavlSBar = new ArrayList<>(Arrays.asList(arrayDavlSBar));

        if (countPointDVK250015St != 0) {
//            resPointBDavlSBarVK2500 += listDavlSBar.get(countPointBVK2500);
            System.out.println(countPointDVK250015St + " - countD");
            System.out.println("Davl s bar point D: " + resSetPointDVK250015St.add(listDavlSBar.get(countPointDVK250015St)));
//            System.out.println("length: " + resPointBDavlSBarVK2500.length());
//            System.out.println("Давление с БРС точка Б: " + resPointBDavlSBarVK2500);
        }
        if (countPointAVK250015St != 0) {
            System.out.println(countPointAVK250015St + " - countA");
            System.out.println("Davl s bar point A: " + resSetPointAVK250015St.add(listDavlSBar.get(countPointAVK250015St)));
//            resPointDDavlSBarVK2500 += listDavlSBar.get(countPointDVK2500);
//            System.out.println("Давление с БРС точка Д: " + resPointDDavlSBarVK2500);
        }
        if (countPointBVK250015St != 0) {
//            resultA += "Давление с БРС точка А: " + listDavlSBar.get(countPointA) + " , " + ("Давление с БРС точка Б: " + listDavlSBar.get(countPointB) + " ,") +
//                    "Давление с БРС точка Д: " + listDavlSBar.get(countPointD) + " . ";
//            resPointADavlSBarVK2500 += listDavlSBar.get(countPointAVK2500);
            System.out.println(countPointBVK250015St + " - countB");
            System.out.println("Davl s bar point B: " + resSetPointBVK250015St.add(listDavlSBar.get(countPointBVK250015St)));

//            System.out.println("Давление с БРС точка А: " + resPointADavlSBarVK2500);
        }

        String strNtk = Arrays.toString(dataNtkVK2500.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2Ntk = strNtk.replaceAll(" , ", " ");
        String[] arrayNtk = str2Ntk.split(" ");
        ArrayList<String> listNtk = new ArrayList<>(Arrays.asList(arrayNtk));

        if (countPointDVK250015St != 0) {
            System.out.println("Ntk point D: " + resSetPointDVK250015St.add(listNtk.get(countPointDVK250015St)));
//            resPointBNtkVK2500 += listNtk.get(countPointBVK2500);
//            System.out.println("length: " + resPointBNtkVK2500.length());
//            System.out.println("nтк точка Б: " + resPointBNtkVK2500);
        }
        if (countPointAVK250015St != 0) {
            System.out.println("Ntk point A: " + resSetPointAVK250015St.add(listNtk.get(countPointAVK250015St)));
//            resPointDNtkVK2500 += listNtk.get(countPointDVK2500);
//            System.out.println("nтк точка Д: " + resPointDNtkVK2500);
        }
        if (countPointBVK250015St != 0) {
            System.out.println("Ntk point B: " + resSetPointBVK250015St.add(listNtk.get(countPointBVK250015St)));
//            resPointANtkVK2500 += listNtk.get(countPointAVK2500);
//            System.out.println("nтк точка А: " + resPointANtkVK2500);
        }

        //
        String strTVx = Arrays.toString(dataTvxVK2500.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2TVx = strTVx.replaceAll(" , ", " ");
        String[] arrayTVx = str2TVx.split(" ");
        ArrayList<String> listTVx = new ArrayList<>(Arrays.asList(arrayTVx));

        if (countPointDVK250015St != 0) {
            System.out.println("Tvxb point D: " + resSetPointDVK250015St.add(listTVx.get(countPointDVK250015St)));
            countPointDVK250015St = 0;
            //coutn=0

//            resPointBTvxBVK2500 += listTVx.get(countPointBVK2500);
//            System.out.println("length: " + resPointBTvxBVK2500.length());
//            System.out.println("Твх_б точка Б: " + resPointBTvxBVK2500);
        }
        if (countPointAVK250015St != 0) {
            System.out.println("Tvx point A: " + resSetPointAVK250015St.add(listTVx.get(countPointAVK250015St)));
            countPointAVK250015St = 0;
//            resPointDTvxBVK2500 += listTVx.get(countPointDVK2500);
//            System.out.println("Твх_б точка Д: " + resPointDTvxBVK2500);
        }
        if (countPointBVK250015St != 0) {
            System.out.println("Tvxb point B: " + resSetPointBVK250015St.add(listTVx.get(countPointBVK250015St)));
            countPointBVK250015St = 0;

//            resPointATvxBVK2500 += listTVx.get(countPointAVK2500);
//            System.out.println("Твх_б точка А: " + resPointATvxBVK2500);
        }


        for (String d : resSetPointDVK250015St) {
            System.out.println("d+" + d);
        }
        for (String a : resSetPointAVK250015St) {
            System.out.println("a+" + a);
        }
        for (String b : resSetPointBVK250015St) {
            System.out.println("b+" + b);
        }


    }

    public static void setValueDataVK250015St() {
        try {
            List<String> listPD = new ArrayList<>(resSetPointDVK250015St);
            List<String> listPA = new ArrayList<>(resSetPointAVK250015St);
            List<String> listPB = new ArrayList<>(resSetPointBVK250015St);

            resPointDDavlSBarVK250015St += listPD.get(0);
            resPointADavlSBarVK250015St += listPA.get(0);
            resPointBDavlSBarVK250015St += listPB.get(0);

            resPointDNtkVK250015St += listPD.get(1);
            resPointANtkVK250015St += listPA.get(1);
            resPointBNtkVK250015St += listPB.get(1);

            resPointDTvxBVK250015St += listPD.get(2);
            resPointATvxBVK250015St += listPA.get(2);
            resPointBTvxBVK250015St += listPB.get(2);

            System.out.println(resPointDDavlSBarVK250015St);
            System.out.println(resPointADavlSBarVK250015St);
            System.out.println(resPointBDavlSBarVK250015St);

            System.out.println(resPointDTvxBVK250015St);
            System.out.println(resPointATvxBVK250015St);
            System.out.println(resPointBTvxBVK250015St);

            System.out.println(resPointDNtkVK250015St);
            System.out.println(resPointANtkVK250015St);
            System.out.println(resPointBNtkVK250015St);

        } catch (Exception ignored) {

        }
    }
}