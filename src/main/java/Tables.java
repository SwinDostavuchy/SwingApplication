import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Tables extends JFrame {
    public JTable table;

    Object row[][] = {{" ","n тк зам(%)","t vx(C)","Ph(mm.pt.st.)","№ замера"},
            {"График <Б>",ReadData.resPointBNtk,ReadData.resPointBTvxB,ReadData.resPointBDavlSBar,Example.pointB}};

    Object column[] = {"column 1","column 2","column 3","column 4","column 5"};

    public Tables() {
        table = new JTable(row, column);
        File theDir = new File("C:\\Users\\vorojeykin\\Desktop\\txt_files");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        try {
            FileWriter fw = new FileWriter(theDir.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < table.getColumnCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    bw.write(table.getModel().getValueAt(i,j) + " ");
                    bw.write("\n______________________________\n");
                    bw.close();
                    fw.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
