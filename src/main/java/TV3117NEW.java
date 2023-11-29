import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TV3117NEW {
    public static int countPointAVM;
    public static int countPointATVozVM;
    public static int countPointBVM;
    public static int countPointDVM;

    public static String resultAVM = "";
    public static String resultB = "";
    public static String resultD = "";
    public static String resultCVMNtk = "";

    public static String resPointADavlSBarVM = "";
    public static String resPointATvxBVM = "";
    public static String resPointATvxBTpVM = "";
    public static String resPointANtkVM = "";
    public static String resPointBDavlSBarVM = "";
    public static String resPointBTvxBVM = "";
    public static String resPointBNtkVM = "";
    public static String resPointDTvxBVM = "";
    public static String resPointDDavlSBarVM = "";
    public static String resPointDNtkVM = "";

    public static String allDataTextVM = "";
    public static String allDataACTVM = "";

    public static Set<String> resSetPointATV3117 = new LinkedHashSet<>();
    public static Set<String> resSetPointATmTV3117 = new LinkedHashSet<>();
    public static Set<String> resSetPointBTV3117 = new LinkedHashSet<>();
    public static Set<String> resSetPointDTV3117 = new LinkedHashSet<>();

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
        Set<String> dataTvozTpVM = new LinkedHashSet<>();
        Set<String> dataNtkVM = new LinkedHashSet<>();
        Set<String> dataTvxVM = new LinkedHashSet<>();

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
                    if (strArr[j].startsWith("t*вх")) {
                        dataTvxVM.add(strArr[j].substring(5));
                    }
                    if (strArr[j].startsWith("nтк") && !strArr[j].startsWith("nтк пр")) {
                        dataNtkVM.add(strArr[j].substring(4));
                    }
                    if (strArr[j].startsWith("tвоз_тп")) {
                        dataTvozTpVM.add(strArr[j].substring(8));
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

        for (int i = 0; i < listNumZ.size(); i++) {
            if (listNumZ.get(i).equals(Example.pointA)) {
                countPointAVM = i;
            }
        }

        for (int t = 0; t < listNumZ.size(); t++) {
            if (listNumZ.get(t).equals(Example.pointA)) {
                countPointATVozVM = t;
            }
        }

        String strADavlSBar = Arrays.toString(dataDavlSBarVM.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2DavlSBar = strADavlSBar.replaceAll(" , ", " ");
        String[] arrayDavlSBar = str2DavlSBar.split(" ");
        ArrayList<String> listDavlSBar = new ArrayList<>(Arrays.asList(arrayDavlSBar));
        if (countPointBVM != 0) {
//            resultAVM += "Давление с БРС точка А: " + listDavlSBar.get(countPointAVM) + " , " + ("Давление с БРС точка Б: " + listDavlSBar.get(countPointBVM) + " ,") +
//                    "Давление с БРС точка Д: " + listDavlSBar.get(countPointDVM) + " . ";
//            resPointADavlSBarVM += listDavlSBar.get(countPointAVM);
            System.out.println("Davl s bar point B: " + resSetPointBTV3117.add(listDavlSBar.get(countPointBVM)));
            System.out.println("Давление с БРС точка Б: " + listDavlSBar.get(countPointBVM));
        }
        if (countPointDVM != 0) {
//            resPointBDavlSBarVM += listDavlSBar.get(countPointBVM);
            System.out.println("Davl s bar point D: " + resSetPointDTV3117.add(listDavlSBar.get(countPointDVM)));
            System.out.println("Давление с БРС точка D: " + listDavlSBar.get(countPointDVM));

        }
        if (countPointAVM != 0) {
//            resPointDDavlSBarVM += listDavlSBar.get(countPointDVM);
            System.out.println("Davl s bar point A: " + resSetPointATV3117.add(listDavlSBar.get(countPointAVM)));
            System.out.println("Давление с БРС точка A: " + listDavlSBar.get(countPointAVM));
        }

        // упорядочить
        String strTVOZ = Arrays.toString(dataTvozTpVM.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2TVOZ = strTVOZ.replaceAll(" , ", " ");
        String[] arrayTVx = str2TVOZ.split(" ");
        ArrayList<String> listTVOZ = new ArrayList<>(Arrays.asList(arrayTVx));
        if (countPointATVozVM != 0) {
//            resPointATvxBVM += listTVx.get(countPointAVM);
            System.out.println("tвоз_тп точка А: " + listTVOZ.get(countPointATVozVM));
            System.out.println("tвоз_тп точка А: " + resSetPointATmTV3117.add(listTVOZ.get(countPointATVozVM)));
            countPointATVozVM = 0;
        }
//        if (countPointBVM != 0) {
//            resPointBTvxBVM += listTVx.get(countPointBVM);
//            System.out.println("tвоз_тп точка Б: " + listTVx.get(countPointBVM));
//        }
//        if (countPointDVM != 0) {
//            resPointDTvxBVM += listTVx.get(countPointDVM);
//            System.out.println("tвоз_тп точка Д: " + listTVx.get(countPointDVM));
//        }
        String strTVxTm = Arrays.toString(dataTvxVM.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2TVxVm = strTVxTm.replaceAll(" , ", " ");
        String[] arrayTVxVm = str2TVxVm.split(" ");
        ArrayList<String> listTVxVm = new ArrayList<>(Arrays.asList(arrayTVxVm));
        if (countPointBVM != 0) {
            System.out.println("t_vx point B: " + resSetPointBTV3117.add(listTVxVm.get(countPointBVM)));
            System.out.println("t_vx point B: " + listTVxVm.get(countPointBVM));
        }
        if (countPointDVM != 0) {
            System.out.println("t_vx point D: " + resSetPointDTV3117.add(listTVxVm.get(countPointDVM)));
            System.out.println("t_vx point D: " + listTVxVm.get(countPointDVM));
        }


        String strNtk = Arrays.toString(dataNtkVM.toArray()).replaceAll("[\\[\\]\\s]", " ");
        String str2Ntk = strNtk.replaceAll(" , ", " ");
        String[] arrayNtk = str2Ntk.split(" ");
        ArrayList<String> listNtk = new ArrayList<>(Arrays.asList(arrayNtk));
        if (countPointBVM != 0) {
//            resPointANtkVM += listNtk.get(countPointAVM);
            System.out.println("ntk point B: " + resSetPointBTV3117.add(listNtk.get(countPointBVM)));
            System.out.println("nтк точка B: " + listNtk.get(countPointBVM));
            countPointBVM = 0;
        }
        if (countPointDVM != 0) {
//            resPointBNtkVM += listNtk.get(countPointBVM);
            System.out.println("ntk point D: " + resSetPointDTV3117.add(listNtk.get(countPointDVM)));
            System.out.println("nтк точка D: " + listNtk.get(countPointDVM));

            countPointDVM = 0;
        }
        if (countPointAVM != 0) {
//            resPointDNtkVM += listNtk.get(countPointDVM);
            System.out.println("ntk point A: " + resSetPointATV3117.add(listNtk.get(countPointAVM)));
            System.out.println("nтк точка A: " + listNtk.get(countPointAVM));
            countPointAVM = 0;
        }

    }

    public static void setValueData() {
        try {
            List<String> listPB = new ArrayList<>(resSetPointBTV3117);
            List<String> listPD = new ArrayList<>(resSetPointDTV3117);
            List<String> listPA = new ArrayList<>(resSetPointATV3117);
            List<String> listPATm = new ArrayList<>(resSetPointATmTV3117);

            for (String b : listPB) {
                System.out.print("list PB: " + b + "\n");
            }
            for (String d : listPD) {
                System.out.print("list PD: " + d + "\n");
            }
            for (String a : listPA) {
                System.out.print("listPA: " + a + "\n");
            }
            for (String pa : listPATm) {
                System.out.print("list PAtm: " + pa + "\n");
            }
            resPointBDavlSBarVM += listPB.get(0);
            resPointDDavlSBarVM += listPD.get(0);
            resPointADavlSBarVM += listPA.get(0);

            resPointBNtkVM += listPB.get(2);
            resPointDNtkVM += listPD.get(2);
            resPointANtkVM += listPA.get(1);

            resPointBTvxBVM += listPB.get(1);
            resPointDTvxBVM += listPD.get(1);
            resPointATvxBTpVM += listPATm.get(0);


            System.out.println(resPointBDavlSBarVM);
            System.out.println(resPointDDavlSBarVM);
            System.out.println(resPointADavlSBarVM);

            System.out.println(resPointBTvxBVM);
            System.out.println(resPointDTvxBVM);
            System.out.println(resPointATvxBTpVM);

            System.out.println(resPointBNtkVM);
            System.out.println(resPointDNtkVM);
            System.out.println(resPointANtkVM);
        } catch (Exception ignored) {

        }
    }

    public static void readActTV3New(File file) {
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
}
