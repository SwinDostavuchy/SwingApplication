import javax.swing.*;
import java.awt.*;

public class PrintData extends JFrame {

    public static void createTableTV3VM() {
        TV3117NEW.setValueData();

        JFrame frame = new JFrame("Исходные данные для построения экспл. графиков");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {
                " ",
                "n тк зам.(%)",
                "t вх",
                "P h(мм.рт.ст.)",
                "№ замера"
        };

        String[][] data = {
                {"График Б", TV3117NEW.resPointBNtkVM, TV3117NEW.resPointBTvxBVM, TV3117NEW.resPointBDavlSBarVM, Example.pointB},
                {"График С", TV3117NEW.resultCVMNtk, "15", "760 ", ""},
                {"График Д", TV3117NEW.resPointDNtkVM, TV3117NEW.resPointDTvxBVM, TV3117NEW.resPointDDavlSBarVM, Example.pointD},
                {"График А", TV3117NEW.resPointANtkVM, TV3117NEW.resPointATvxBTpVM, TV3117NEW.resPointADavlSBarVM, Example.pointA},
        };
        if (!TV3117NEW.resPointBNtkVM.isEmpty() && !TV3117NEW.resPointDNtkVM.isEmpty() && !TV3117NEW.resPointANtkVM.isEmpty()) {
            JTable table = new JTable(data, columnNames);

            JScrollPane scrollPane = new JScrollPane(table);

            frame.getContentPane().add(scrollPane);

            frame.setPreferredSize(new Dimension(450, 200));

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

    }

    public static void createTableVK2500() {
//        VK2500NEW.extractDataVK2500();
        VK2500_15ST.setValueData();

        JFrame frame = new JFrame("Исходные данные для построения экспл. графиков");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {
                " ",
                "n тк зам.(%)",
                "t вх",
                "P h(мм.рт.ст.)",
                "№ замера"
        };

        String[][] data = {
                {"График Д", VK2500_15ST.resPointDNtkVK2500, VK2500_15ST.resPointDTvxBVK2500, VK2500_15ST.resPointDDavlSBarVK2500, Example.pointD},
                {"График А", VK2500_15ST.resPointANtkVK2500, VK2500_15ST.resPointATvxBVK2500, VK2500_15ST.resPointADavlSBarVK2500, Example.pointA},
                {"График Б", VK2500_15ST.resPointBNtkVK2500, VK2500_15ST.resPointBTvxBVK2500, VK2500_15ST.resPointBDavlSBarVK2500, Example.pointB},
                {"График С", VK2500_15ST.resultCVK2500Ntk, "15", "760", ""},
        };
        if (!VK2500_15ST.resPointDNtkVK2500.isEmpty() && !VK2500_15ST.resPointANtkVK2500.isEmpty() && !VK2500_15ST.resPointBNtkVK2500.isEmpty() && !VK2500_15ST.resultCVK2500Ntk.isEmpty()) {
            JTable table = new JTable(data, columnNames);

            JScrollPane scrollPane = new JScrollPane(table);

            frame.getContentPane().add(scrollPane);

            frame.setPreferredSize(new Dimension(450, 200));

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

    }

    public static void createTableVK2500P() {
        JFrame frame = new JFrame("Исходные данные для построения экспл. графиков");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {
                " ",
                "n тк зам.(%)",
                "t вх",
                "P h(мм.рт.ст.)",
                "№ замера"
        };

        String[][] data = {
                {"График Б", " ", " ", " ", " "},
                {"График С", " ", " ", " ", ""},
                {"График Д", " ", " ", " ", " "},
                {"График А", VK2500P.resPointANtkVK2500P, VK2500P.resPointATvxBVK2500P, VK2500P.resPointADavlSBarVK2500P, Example.pointA},
        };
        if (!VK2500P.resPointANtkVK2500P.isEmpty() && !VK2500P.resPointADavlSBarVK2500P.isEmpty() && !VK2500P.resPointATvxBVK2500P.isEmpty()) {
            JTable table = new JTable(data, columnNames);

            JScrollPane scrollPane = new JScrollPane(table);

            frame.getContentPane().add(scrollPane);

            frame.setPreferredSize(new Dimension(450, 200));

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

    }
}
