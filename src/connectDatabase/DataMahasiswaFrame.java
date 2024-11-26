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

public class DataMahasiswaFrame extends JFrame {
    private JTextField tfNama, tfNim;
    private JButton btnSave, btnUpdate, btnDelete, btnView;
    private JTable table;
    private DefaultTableModel model;

    public DataMahasiswaFrame() {
        setTitle("Data Mahasiswa");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Input untuk Nama dan NIM
        JPanel panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(2, 3, 10, 10));

        panelInput.add(new JLabel("Nama:"));
        tfNama = new JTextField();
        panelInput.add(tfNama);

        panelInput.add(new JLabel("NIM:"));
        tfNim = new JTextField();
        panelInput.add(tfNim);

        btnSave = new JButton("Simpan");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Hapus");

        panelInput.add(btnSave);
        panelInput.add(btnUpdate);
        panelInput.add(btnDelete);

        add(panelInput, BorderLayout.NORTH);

        // Table untuk melihat data
        model = new DefaultTableModel(new String[] {"ID", "Nama", "NIM"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Tombol Lihat Data
        btnView = new JButton("Lihat Data");
        JPanel panelView = new JPanel();
        panelView.add(btnView);
        add(panelView, BorderLayout.SOUTH);

        // Aksi tombol
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveMahasiswa();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateMahasiswa();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteMahasiswa();
            }
        });

        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewDataMahasiswa();
            }
        });
    }

    private void saveMahasiswa() {
        String nama = tfNama.getText();
        String nim = tfNim.getText();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO mahasiswa (nama, nim) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nama);
                stmt.setString(2, nim);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan.");
                viewDataMahasiswa();  // Refresh table
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void updateMahasiswa() {
        // Implementasi update mahasiswa
    }

    private void deleteMahasiswa() {
        // Implementasi hapus mahasiswa
    }

    private void viewDataMahasiswa() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM mahasiswa";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                // Clear previous data
                model.setRowCount(0);

                // Menambahkan data ke tabel
                while (rs.next()) {
                    int idmhs = rs.getInt("idmhs");
                    String nama = rs.getString("nama");
                    String nim = rs.getString("nim");
                    model.addRow(new Object[]{idmhs, nama, nim});
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
