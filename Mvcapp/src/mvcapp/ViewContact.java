package mvcapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewContact extends JFrame {
    JLabel lNama = new JLabel("Nama");
    JLabel lNoHp = new JLabel("No Hp");
    JLabel lUmur = new JLabel("Umur");
    JLabel lEmail = new JLabel("E-mail");
    JLabel lCari = new JLabel("Cari Nama");

    JTextField tfNama = new JTextField();
    JTextField tfNoHp = new JTextField();
    JTextField tfUmur = new JTextField();
    JTextField tfEmail = new JTextField();
    JTextField tfCari = new JTextField();

    JButton btnTambah = new JButton("Tambah");
    JButton btnUpdate = new JButton("Update");
    JButton btnCari = new JButton("Cari");
    JButton btnReset = new JButton("Refresh");

    JTable tabel;
    DefaultTableModel dtm;
    JScrollPane scrollPane;
    Object namaKolom[] = {"Nama", "No Hp", "Umur", "E-mail"};

    public ViewContact() {
        dtm = new DefaultTableModel(namaKolom, 0);
        tabel = new JTable(dtm);
        scrollPane = new JScrollPane(tabel);

        setTitle("Data Kontak");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setSize(900, 400);

        add(scrollPane);
        scrollPane.setBounds(20, 20, 480, 300);

        add(lNama);
        lNama.setBounds(510, 20, 90, 20);
        add(tfNama);
        tfNama.setBounds(510, 40, 120, 20);

        add(lNoHp);
        lNoHp.setBounds(510, 70, 90, 20);
        add(tfNoHp);
        tfNoHp.setBounds(510, 90, 120, 20);

        add(lUmur);
        lUmur.setBounds(510, 120, 90, 20);
        add(tfUmur);
        tfUmur.setBounds(510, 140, 120, 20);

        add(lEmail);
        lEmail.setBounds(510, 170, 90, 20);
        add(tfEmail);
        tfEmail.setBounds(510, 190, 120, 20);

        add(btnTambah);
        btnTambah.setBounds(510, 220, 90, 20);
        add(btnUpdate);
        btnUpdate.setBounds(510, 250, 90, 20);

        add(btnCari);
        btnCari.setBounds(510, 280, 90, 20);
        add(btnReset);
        btnReset.setBounds(510, 310, 90, 20);
    }

    public String getNama() {
        return tfNama.getText();
    }

    public String getNoHp() {
        return tfNoHp.getText();
    }

    public String getUmur() {
        return tfUmur.getText();
    }

    public String getEmail() {
        return tfEmail.getText();
    }

    public String getCariNama() {
        return tfCari.getText();
    }
}
