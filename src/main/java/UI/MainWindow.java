package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MainWindow {
    private JLabel InfoLabel;
    private JLabel KeyValueLabel;
    private JTextField keyTextField;
    private JLabel stringLabel;
    private JTextArea stringtextArea;
    private JButton submitButton;
    private JPanel mainPanel;

    public MainWindow() {

        submitButton.addActionListener(click -> {

            String key = keyTextField.getText();

            File inputFile = new File("document.txt");

            String text = stringtextArea.getText();
            try {
                FileOutputStream outputStream = new FileOutputStream(inputFile);
                outputStream.write(text.getBytes());
            }
            catch (IOException e){
                JOptionPane.showMessageDialog(null, "Unable to write in the file.");
            }

            File encryptedFile = new File("document.encrypted");

            try {
                CryptoUtils.encrypt(key, inputFile, encryptedFile);
            } catch (CryptoException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                ex.printStackTrace();
            }
            File file = new File("document.encrypted");
            StringBuffer stringBuffer = new StringBuffer();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st;
                while ((st = br.readLine()) != null)
                    stringBuffer.append(st);

            }
            catch (IOException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

            JOptionPane.showMessageDialog(null, "The Encrypted text is: "+ stringBuffer.toString());
        });
    }

    public static void main(String[] args) throws IOException {

        JFrame jFrame = new JFrame("MainWindow");
        jFrame.setContentPane(new MainWindow().mainPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

    }



}
