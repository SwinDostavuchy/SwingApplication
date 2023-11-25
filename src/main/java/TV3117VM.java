import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class TV3117VM {

    public static int countPointAVM;
    public static int countPointBVM;
    public static int countPointDVM;

    public static String resultAVM = "";
    public static String resultB = "";
    public static String resultD = "";
    public static String resultCVMNtk = "";

    public static String resPointADavlSBarVM = "";
    public static String resPointATvxBVM = "";
    public static String resPointANtkVM = "";
    public static String resPointBDavlSBarVM = "";
    public static String resPointBTvxBVM = "";
    public static String resPointBNtkVM = "";
    public static String resPointDTvxBVM = "";
    public static String resPointDDavlSBarVM = "";
    public static String resPointDNtkVM = "";

    public static String allDataTextVM = "";
    public static String allDataACTVM = "";

    public static void readActTV3(File file) {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            allDataACTVM = stripper.getText(document);

            String[] dataTextActVM = allDataACTVM.split("Инженер по испытаниям:");

            StringBuilder sb = new StringBuilder();
            Set<String> dataFactParamVM = new LinkedHashSet<>();
            String c = "";

            if (Arrays.toString(dataTextActVM).contains(Example.numberEngine) && Arrays.toString(dataTextActVM).contains("Характеристика")) {
                for (int i = 0; i < dataTextActVM.length; i++) {
                    String[] strArr = dataTextActVM[i].split("\n");
                    for (int j = 0; j < strArr.length; j++) {
                        if (strArr[j].startsWith("Номера замеров:")) {
                            sb.append(strArr[j].substring(16));
                        }
                        if (strArr[j].startsWith("Факт.")) {
                            dataFactParamVM.add(strArr[j].substring(6));
                        }
                    }
                }
            }
            System.out.println(sb);

            String strAllFactP = Arrays.toString(dataFactParamVM.toArray()).replaceAll("[\\[\\]\\s]", " ");
//            System.out.println("first " + strAllFactP);
//            String str2 = strAllFactP.replaceAll(",", "");
            String[] arr = strAllFactP.split(",");
//            for (String t : arr) {
//                System.out.print(t);
//            }
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(arr));
            String pointStr = arrayList.get(2).substring(11, 15);
            System.out.println(pointStr);

            resultCVMNtk += pointStr;

            System.out.println("poinc c: " + resultCVMNtk);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void extractDataTV3117Vm() {

        String[] dataTextAllVM = ReadData.allDataText.split("Инженер по испытаниям:");

        Set<String> dataNumZamVM = new LinkedHashSet<>();
        Set<String> dataDavlSBarVM = new LinkedHashSet<>();
        Set<String> dataTvxVM = new LinkedHashSet<>();
        Set<String> dataNtkVM = new LinkedHashSet<>();

        if (Arrays.toString(dataTextAllVM).contains(Example.numberEngine) && Arrays.toString(dataTextAllVM).contains("Энергетические параметры")) {
            for (int i = 0; i < dataTextAllVM.length; i++) {
                String[] strArr = dataTextAllVM[i].split("\n");
                for (int j = 0; j < strArr.length; j++) {
                    if (strArr[j].startsWith("№ замера")) {
                        dataNumZamVM.add(strArr[j].substring(9));
                    }
                    if (strArr[j].startsWith("Давл. с БРС")) {
                        dataDavlSBarVM.add(strArr[j].substring(12));
                    }
                    if (strArr[j].startsWith("tвоз_тп")) {
                        dataTvxVM.add(strArr[j].substring(8));
                    }
                    if (strArr[j].startsWith("nтк") && !strArr[j].startsWith("nтк пр")) {
                        dataNtkVM.add(strArr[j].substring(4));
                    }
                }
            }
        }

        String strNumZ = Arrays.toString(dataNumZamVM.toArray()).replaceAll("[\\[\\]\\s]", " ");
        System.out.println(strNumZ);
        String str2NumZ = strNumZ.replaceAll(" , ", " ");
        System.out.println(str2NumZ);
        String[] arrayNumZam = str2NumZ.split(" ");
        ArrayList<String> listNumZ = new ArrayList<>(Arrays.asList(arrayNumZam));

        for (int i = 0; i < listNumZ.size(); i++) {
            if (listNumZ.get(i).equals(Example.pointA)) {
                countPointAVM = i;
            }
        }

        for (int j = 0; j < listNumZ.size(); j++) {
            if (listNumZ.get(j).equals(Example.pointB)) {
                countPointBVM = j;
            }
        }

        for (int k = 0; k < listNumZ.size(); k++) {
            if (listNumZ.get(k).equals(Example.pointD)) {
                countPointDVM = k;
            }
        }

        String strADavlSBar = Arrays.toString(dataDavlSBarVM.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2DavlSBar = strADavlSBar.replaceAll(" , ", " ");
        String[] arrayDavlSBar = str2DavlSBar.split(" ");
        ArrayList<String> listDavlSBar = new ArrayList<>(Arrays.asList(arrayDavlSBar));
        if (countPointAVM != 0) {
//            resultAVM += "Давление с БРС точка А: " + listDavlSBar.get(countPointAVM) + " , " + ("Давление с БРС точка Б: " + listDavlSBar.get(countPointBVM) + " ,") +
//                    "Давление с БРС точка Д: " + listDavlSBar.get(countPointDVM) + " . ";
            resPointADavlSBarVM += listDavlSBar.get(countPointAVM);

            System.out.println("Давление с БРС точка А: " + listDavlSBar.get(countPointAVM));
        }
        if (countPointBVM != 0) {
            resPointBDavlSBarVM += listDavlSBar.get(countPointBVM);
            System.out.println("Давление с БРС точка Б: " + listDavlSBar.get(countPointBVM));
        }
        if (countPointDVM != 0) {
            resPointDDavlSBarVM += listDavlSBar.get(countPointDVM);
            System.out.println("Давление с БРС точка Д: " + listDavlSBar.get(countPointDVM));
        }

        String strTVx = Arrays.toString(dataTvxVM.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2TVx = strTVx.replaceAll(" , ", " ");
        String[] arrayTVx = str2TVx.split(" ");
        ArrayList<String> listTVx = new ArrayList<>(Arrays.asList(arrayTVx));
        if (countPointAVM != 0) {
            resPointATvxBVM += listTVx.get(countPointAVM);
            System.out.println("tвоз_тп точка А: " + listTVx.get(countPointAVM));
        }
        if (countPointBVM != 0) {
            resPointBTvxBVM += listTVx.get(countPointBVM);
            System.out.println("tвоз_тп точка Б: " + listTVx.get(countPointBVM));
        }
        if (countPointDVM != 0) {
            resPointDTvxBVM += listTVx.get(countPointDVM);
            System.out.println("tвоз_тп точка Д: " + listTVx.get(countPointDVM));
        }

        String strNtk = Arrays.toString(dataNtkVM.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2Ntk = strNtk.replaceAll(" , ", " ");
        String[] arrayNtk = str2Ntk.split(" ");
        ArrayList<String> listNtk = new ArrayList<>(Arrays.asList(arrayNtk));
        if (countPointAVM != 0) {
            resPointANtkVM += listNtk.get(countPointAVM);
            System.out.println("nтк точка А: " + listNtk.get(countPointAVM));
            countPointAVM = 0;
        }
        if (countPointBVM != 0) {
            resPointBNtkVM += listNtk.get(countPointBVM);
            System.out.println("nтк точка Б: " + listNtk.get(countPointBVM));
            countPointBVM = 0;
        }
        if (countPointDVM != 0) {
            resPointDNtkVM += listNtk.get(countPointDVM);
            System.out.println("nтк точка Д: " + listNtk.get(countPointDVM));
            countPointDVM = 0;
        }

    }
}
