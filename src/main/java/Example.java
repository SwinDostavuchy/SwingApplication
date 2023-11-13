import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Example extends JFrame implements ActionListener {
    private Container container;
    public JTextField inputNumberEngine;
    private JTextField inputPointA;
    private JTextField inputPointB;
    private JTextField inputPointD;
    private JLabel labelTitle;
    private JLabel labelNumberEngine;
    public JLabel labelPointA;
    public JLabel labelPointB;
    public JLabel labelPointD;
    private JLabel labelOutData;
    public JButton selectFilesButton;
    public JTextArea textArea;

    public static String pointA = "";
    public static String pointB = "";
    public static String pointD = "";
    public static String numberEngine = "";


    public Example() {
        setTitle("Считывание данных из PDF");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        labelTitle = new JLabel("Форма получения данных из PDF");
        labelTitle.setFont(new Font("Arial", Font.PLAIN, 30));
        labelTitle.setSize(500, 30);
        labelTitle.setLocation(50, 30);
        container.add(labelTitle);

        labelNumberEngine = new JLabel("Номер двигателя");
        labelNumberEngine.setFont(new Font("Arial", Font.PLAIN, 20));
        labelNumberEngine.setSize(200, 20);
        labelNumberEngine.setLocation(30, 100);
        container.add(labelNumberEngine);

        inputNumberEngine = new JTextField();
        inputNumberEngine.setFont(new Font("Arial", Font.PLAIN, 15));
        inputNumberEngine.setSize(200, 20);
        inputNumberEngine.setLocation(210, 100);
        container.add(inputNumberEngine);

        labelPointA = new JLabel("№ замера точка А");
        labelPointA.setFont(new Font("Arial", Font.PLAIN, 20));
        labelPointA.setSize(200, 20);
        labelPointA.setLocation(30, 150);
        container.add(labelPointA);

        inputPointA = new JTextField();
        inputPointA.setFont(new Font("Arial", Font.PLAIN, 15));
        inputPointA.setSize(200, 20);
        inputPointA.setLocation(210, 150);
        container.add(inputPointA);

        labelPointB = new JLabel("№ замера точка Б");
        labelPointB.setFont(new Font("Arial", Font.PLAIN, 20));
        labelPointB.setSize(200, 20);
        labelPointB.setLocation(30, 200);
        container.add(labelPointB);

        inputPointB = new JTextField();
        inputPointB.setFont(new Font("Arial", Font.PLAIN, 15));
        inputPointB.setSize(200, 20);
        inputPointB.setLocation(210, 200);
        container.add(inputPointB);

        labelPointD = new JLabel("№ замера точка Д");
        labelPointD.setFont(new Font("Arial", Font.PLAIN, 20));
        labelPointD.setSize(200, 20);
        labelPointD.setLocation(30, 250);
        container.add(labelPointD);

        inputPointD = new JTextField();
        inputPointD.setFont(new Font("Arial", Font.PLAIN, 15));
        inputPointD.setSize(200, 20);
        inputPointD.setLocation(210, 250);
        container.add(inputPointD);

        selectFilesButton = new JButton("Выбрать PDF файлы");
        selectFilesButton.setFont(new Font("Arial", Font.PLAIN, 15));
        selectFilesButton.setSize(200, 20);
        selectFilesButton.setLocation(150, 450);
        selectFilesButton.addActionListener(this);
        container.add(selectFilesButton);

        labelOutData = new JLabel("Данные для эксплатуационных графиков АБД");
        labelOutData.setFont(new Font("Arial", Font.PLAIN, 20));
        labelOutData.setSize(300, 20);
        labelOutData.setLocation(500, 70);
        container.add(labelOutData);

        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 15));
        textArea.setSize(300, 400);
        textArea.setLocation(500, 100);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        container.add(textArea);


        setVisible(true);
    }

    public static void main(String[] args) {
        Example example = new Example();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        selectFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JFileChooser fileChooser = new JFileChooser();
//                int result = fileChooser.showOpenDialog(Example.this);
//                if (result == JFileChooser.APPROVE_OPTION) {
//                    File selectedFile = fileChooser.getSelectedFile();
//                    try {
//                        String text = ReadData.read(selectedFile);
//                        textArea.setText(text);
//                        String s = inputPointA.getText();
//                        System.out.println(s);
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                        JOptionPane.showMessageDialog(Example.this, "Error reading PDF file");
//                    }
//                }
                pointA = inputPointA.getText();
                pointB = inputPointB.getText();
                pointD = inputPointD.getText();
                numberEngine = inputNumberEngine.getText();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setMultiSelectionEnabled(true);
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int ret = fileChooser.showOpenDialog(Example.this);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File[] listFiles = fileChooser.getSelectedFiles();
                    try {
                        for (File l : listFiles) {
                            ReadData.readPDFPages(l.getAbsolutePath());
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });


    }

}
