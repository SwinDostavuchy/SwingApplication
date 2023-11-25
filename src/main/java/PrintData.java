import javax.swing.*;
import java.awt.*;

public class PrintData extends JFrame {

    public static void createTableTV3VM() {
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
                {"График Б", TV3117VM.resPointDNtkVM, TV3117VM.resPointBTvxBVM, TV3117VM.resPointBDavlSBarVM, Example.pointB},
                {"График С", TV3117VM.resultCVMNtk, "15", "760 ",""},
                {"График Д", TV3117VM.resPointDNtkVM, TV3117VM.resPointDTvxBVM, TV3117VM.resPointDDavlSBarVM, Example.pointD},
                {"График А", TV3117VM.resPointANtkVM, TV3117VM.resPointATvxBVM, TV3117VM.resPointADavlSBarVM, Example.pointA},
        };
        if (!TV3117VM.resPointANtkVM.isEmpty() && !TV3117VM.resPointDNtkVM.isEmpty() && !TV3117VM.resPointBDavlSBarVM.isEmpty()) {
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
                {"График Б", VK2500.resPointBNtkVK2500, VK2500.resPointBTvxBVK2500, VK2500.resPointBDavlSBarVK2500, Example.pointB},
                {"График С", VK2500.resultCVK2500Ntk, "15", "760",""},
                {"График Д", VK2500.resPointDNtkVK2500, VK2500.resPointDTvxBVK2500, VK2500.resPointDDavlSBarVK2500, Example.pointD},
                {"График А", VK2500.resPointANtkVK2500, VK2500.resPointATvxBVK2500, VK2500.resPointADavlSBarVK2500, Example.pointA},
        };
        if (!VK2500.resPointANtkVK2500.isEmpty() && !VK2500.resPointDNtkVK2500.isEmpty() && !VK2500.resPointBNtkVK2500.isEmpty() && !VK2500.resultCVK2500Ntk.isEmpty()) {
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
                {"График С", " ", " ", " ",""},
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
