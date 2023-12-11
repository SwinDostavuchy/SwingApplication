public class DataForTxtFile {
    public static String mGVK250015St = "";
    public static String kr2_250015St = "";
    public static String kr_250015St = "";
    public static String mP_250015St = "";
    public static String vzl_250015St = "";
    public static String chr_250015St = "";

    public static void txtVk2500_15St() {
        String[] arrRejim = VK2500_15ST.setResultRejimVk2500_15St.split(" ");
        String[] arrPmvx = VK2500_15ST.setResultPmvxVk2500_15St.split(" ");
        String[] arrPt1 = VK2500_15ST.setResultPt1Vk2500_15St.split(" ");
        String[] arrTmvx = VK2500_15ST.setResultTmvxVk2500_15St.split(" ");
//        for (String s : arrRejim) {
//            System.out.println("Массив состоящий из режимов: " + s);
//        }
        double mgPmvx = 0;
        double mgPt1 = 0;
        double mgTmvx = 0;

        double kr2Pmvx = 0;
        double kr2Pt1 = 0;
        double kr2Tmvx = 0;

        double kr1Pmvx = 0;
        double kr1Pt1 = 0;
        double kr1Tmvx = 0;

        double mpPmvx = 0;
        double mpPt1 = 0;
        double mpTmvx = 0;

        double vzlPmvx = 0;
        double vzlPt1 = 0;
        double vzlTmvx = 0;

        double chrPmvx = 0;
        double chrPt1 = 0;
        double chrTmvx = 0;

        for (int i = 0; i < arrRejim.length; i++) {
            if (arrRejim[i].equals("МГ")) {
                mgPmvx = Double.parseDouble(arrPmvx[i]);
                mgPt1 = Double.parseDouble(arrPt1[i]);
                mgTmvx = Double.parseDouble(arrTmvx[i]);
            }
            if (arrRejim[i].equals("2КР")) {
                kr2Pmvx += Double.parseDouble(arrPmvx[i]);
                kr2Pt1 += Double.parseDouble(arrPt1[i]);
                kr2Tmvx += Double.parseDouble(arrTmvx[i]);
            }
            if (arrRejim[i].equals("КР")) {
                kr1Pmvx += Double.parseDouble(arrPmvx[i]);
                kr1Pt1 += Double.parseDouble(arrPt1[i]);
                kr1Tmvx += Double.parseDouble(arrTmvx[i]);
            }
            if (arrRejim[i].equals("МП")) {
                mpPmvx += Double.parseDouble(arrPmvx[i]);
                mpPt1 += Double.parseDouble(arrPt1[i]);
                mpTmvx += Double.parseDouble(arrTmvx[i]);
            }
            if (arrRejim[i].equals("ВЗЛ")) {
                vzlPmvx += Double.parseDouble(arrPmvx[i]);
                vzlPt1 += Double.parseDouble(arrPt1[i]);
                vzlTmvx += Double.parseDouble(arrTmvx[i]);
            }
            if (arrRejim[i].equals("ЧР")) {
                chrPmvx += Double.parseDouble(arrPmvx[i]);
                chrPt1 += Double.parseDouble(arrPt1[i]);
                chrTmvx += Double.parseDouble(arrTmvx[i]);
            }
        }

        System.out.println("Данные для редактирования txt ВЗЛ: " + String.format("%.0f", vzlTmvx / 3) + " " +
                String.format("%.1f",vzlPmvx / 3).replace(",",".") + " " + String.format("%.0f",vzlPt1 / 3));

        System.out.println("Данные для редактирования txt МП: " + String.format("%.0f", mpTmvx / 3) + " " +
                String.format("%.1f",mpPmvx / 3).replace(",",".") + " " + String.format("%.0f",mpPt1 / 3));

        System.out.println("Данные для редактирования txt 1КР: " + String.format("%.0f", kr1Tmvx / 3) + " " +
                String.format("%.1f",kr1Pmvx / 3).replace(",",".") + " " + String.format("%.0f",kr1Pt1 / 3));

        System.out.println("Данные для редактирования txt 2КР: " + String.format("%.0f", kr2Tmvx / 3) + " " +
                String.format("%.1f", kr2Pmvx / 3).replace(",", ".") + " " + String.format("%.0f", kr2Pt1 / 3));

        System.out.println("Данные для редактирования txt ЧР: " + String.format("%.0f", chrTmvx / 2) + " " +
                String.format("%.1f",chrPmvx / 2).replace(",",".") + " " + String.format("%.0f",chrPt1 / 2));

        System.out.println("Данные для редактирования txt МГ: " + String.format("%.0f", mgTmvx) + " " +
                String.format("%.1f", mgPmvx).replace(",", ".") + " " + String.format("%.0f", mgPt1));

    }
}
