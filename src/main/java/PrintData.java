import javax.swing.*;
import java.awt.*;

public class PrintData extends JFrame {

    public static void createGUITable() {
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
                {"График Б", ReadData.resPointBNtk, ReadData.resPointBTvxB, ReadData.resPointBDavlSBar, Example.pointB},
                {"График С", ReadData.resultC, "15", "760",""},
                {"График Д", ReadData.resPointDNtk, ReadData.resPointDTvxB, ReadData.resPointDDavlSBar, Example.pointD},
                {"График А", ReadData.resPointANtk, ReadData.resPointATvxB, ReadData.resPointADavlSBar, Example.pointA},
        };
        if (!ReadData.resPointANtk.isEmpty() && !ReadData.resPointDNtk.isEmpty() && !ReadData.resPointBNtk.isEmpty() && !ReadData.resultC.isEmpty()) {
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
