import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.text.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;


public class PdfReaderApp extends JFrame {
    private JTextArea textArea;
    private JButton openButton;


    public PdfReaderApp() {
        super("Swing Application");
//        JFrame frame = new JFrame("Swing Application");
//        frame.setSize(800,600);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

//        JPanel panel = new JPanel();

        textArea = new JTextArea();


        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        openButton = new JButton("Open PDF File");
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(PdfReaderApp.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        String text = ReadData.read(selectedFile);
                        textArea.setText(text);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(PdfReaderApp.this, "Error reading PDF file");
                    }
                }
            }
        });

        add(openButton, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.EAST);


//        frame.add(panel);
//        add(openButton, BorderLayout.SOUTH);
//
//        add(readInputButton, BorderLayout.NORTH);
//
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private String getTextFromPdf(File file) throws IOException {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PdfReaderApp().setVisible(true);
            }
        });
    }
}
