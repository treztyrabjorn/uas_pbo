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
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class DataMatakuliahFrame extends JFrame {
    private JTextField tfNamaMK;
    private JButton btnSave, btnUpdate, btnDelete, btnView;
    private JTable table;
    private DefaultTableModel model;

    public DataMatakuliahFrame() {
        setTitle("Data Mata Kuliah");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Input untuk Nama Mata Kuliah
        JPanel panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(3, 5, 8, 8)); // 2 baris, 5 kolom

        panelInput.add(new JLabel("Nama Mata Kuliah:"));
        tfNamaMK = new JTextField();
        panelInput.add(tfNamaMK);

        btnSave = new JButton("Simpan");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Hapus");
        btnView = new JButton("Lihat Data");

        // Menambahkan tombol ke grid layout
        panelInput.add(btnSave);
        panelInput.add(btnUpdate);
        panelInput.add(btnDelete);
        panelInput.add(btnView); // Tombol Lihat Data juga di sini

        add(panelInput, BorderLayout.NORTH);

        // Table untuk melihat data
        model = new DefaultTableModel(new String[] {"ID", "Nama Mata Kuliah"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Aksi tombol
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveMatakuliah();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateMatakuliah();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteMatakuliah();
            }
        });

        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewDataMatakuliah();
            }
        });
    }

    private void saveMatakuliah() {
        String namaMK = tfNamaMK.getText();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO matakuliah (nama) VALUES (?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, namaMK);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan.");
                viewDataMatakuliah();  // Refresh table
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void updateMatakuliah() {
        // Implementasi update mata kuliah
    }

    private void deleteMatakuliah() {
        // Implementasi hapus mata kuliah
    }

    private void viewDataMatakuliah() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM matakuliah";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                // Clear previous data
                model.setRowCount(0);

                // Menambahkan data ke tabel
                while (rs.next()) {
                    int idmk = rs.getInt("idmk");
                    String nama = rs.getString("nama");
                    model.addRow(new Object[]{idmk, nama});
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
