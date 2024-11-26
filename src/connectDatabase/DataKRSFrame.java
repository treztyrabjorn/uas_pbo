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

public class DataKRSFrame extends JFrame {
    private JTextField tfIdmhs, tfIdmk, tfSemester, tfTahunAjaran;
    private JButton btnSave, btnUpdate, btnDelete, btnView;
    private JTable table;
    private DefaultTableModel model;

    public DataKRSFrame() {
        setTitle("Data KRS");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Input untuk KRS
        JPanel panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(3, 2, 10, 10));

        panelInput.add(new JLabel("ID Mahasiswa:"));
        tfIdmhs = new JTextField();
        panelInput.add(tfIdmhs);

        panelInput.add(new JLabel("ID Mata Kuliah:"));
        tfIdmk = new JTextField();
        panelInput.add(tfIdmk);

        panelInput.add(new JLabel("Semester:"));
        tfSemester = new JTextField();
        panelInput.add(tfSemester);

        panelInput.add(new JLabel("Tahun Ajaran:"));
        tfTahunAjaran = new JTextField();
        panelInput.add(tfTahunAjaran);

        btnSave = new JButton("Simpan");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Hapus");

        panelInput.add(btnSave);
        panelInput.add(btnUpdate);
        panelInput.add(btnDelete);

        add(panelInput, BorderLayout.NORTH);

        // Table untuk melihat data
        model = new DefaultTableModel(new String[] {"ID KRS", "ID Mahasiswa", "ID Mata Kuliah", "Semester", "Tahun Ajaran"}, 0);
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
                saveKRS();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateKRS();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteKRS();
            }
        });

        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewDataKRS();
            }
        });
    }

    private void saveKRS() {
        String idmhs = tfIdmhs.getText();
        String idmk = tfIdmk.getText();
        String semester = tfSemester.getText();
        String tahunAjaran = tfTahunAjaran.getText();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO krs (idmhs, idmk, semester, tahunajaran) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, idmhs);
                stmt.setString(2, idmk);
                stmt.setString(3, semester);
                stmt.setString(4, tahunAjaran);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan.");
                viewDataKRS();  // Refresh table
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void updateKRS() {
        // Implementasi update KRS
    }

    private void deleteKRS() {
        // Implementasi hapus KRS
    }

    private void viewDataKRS() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM krs";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                // Clear previous data
                model.setRowCount(0);

                // Menambahkan data ke tabel
                while (rs.next()) {
                    int idkrs = rs.getInt("idkrs");
                    String idmhs = rs.getString("idmhs");
                    String idmk = rs.getString("idmk");
                    String semester = rs.getString("semester");
                    String tahunajaran = rs.getString("tahunajaran");
                    model.addRow(new Object[]{idkrs, idmhs, idmk, semester, tahunajaran});
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
