import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class VK2500 {
    public static int countPointA;
    public static int countPointB;
    public static int countPointD;

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
    public static String allDataACT = "";
    public static void extractDataVK2500P() {
        String[] dataTextAll = ReadData.allDataText.split("Инженер по испытаниям:");

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
            resPointADavlSBar += listDavlSBar.get(countPointA);


//            resultA += "Давление с БРС точка А: " + listDavlSBar.get(countPointA) + " , " + ("Давление с БРС точка Б: " + listDavlSBar.get(countPointB) + " ,") +
//                    "Давление с БРС точка Д: " + listDavlSBar.get(countPointD) + " . ";

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
}
