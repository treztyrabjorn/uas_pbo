/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connectDatabase;

/**
 *
 * @author Zwesty Quatra
 */
import javax.swing.*;

public class MainFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DataMahasiswaFrame().setVisible(true);
                new DataMatakuliahFrame().setVisible(true);
                new DataKRSFrame().setVisible(true);
            }
        });
    }
}
