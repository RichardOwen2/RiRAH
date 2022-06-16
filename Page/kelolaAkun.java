/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Page;

import tubes.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Arwin Marinta
 */
public class kelolaAkun extends javax.swing.JFrame {

    /**
     * Creates new form kelolaAccount
     */
    public kelolaAkun() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new Page.bgKelolaAkun();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelAkun = new javax.swing.JTable();
        pilihDetailEdit = new javax.swing.JComboBox<>();
        dataBaru = new javax.swing.JTextField();
        simpanPerubahan = new javax.swing.JButton();
        pilihAkunEdit = new javax.swing.JTextField();
        kembali = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabelAkun.setModel(new javax.swing.table.DefaultTableModel(
            null, new String [] {"ID", "Username", "Password", "Role", "Nama", "Email", "Nomor", "Alamat", "Accout Creat", "RiRAHPay"}));
        jScrollPane1.setViewportView(tabelAkun);

        pilihDetailEdit.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        pilihDetailEdit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id", "username", "password", "Role", "nama", "email", "nomor", "alamat", "Account Created", "RiRAHPay" }));
        pilihDetailEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihDetailEditActionPerformed(evt);
            }
        });

        dataBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataBaruActionPerformed(evt);
            }
        });

        simpanPerubahan.setBackground(new java.awt.Color(0, 255, 0));
        simpanPerubahan.setText("Simpan Perubahan");
        simpanPerubahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanPerubahanActionPerformed(evt);
            }
        });

        pilihAkunEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihAkunEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(412, 412, 412)
                        .addComponent(pilihAkunEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pilihDetailEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dataBaru, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(99, 99, 99)
                .addComponent(simpanPerubahan, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(simpanPerubahan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pilihAkunEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pilihDetailEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataBaru, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();

        simpanPerubahan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                main.editDataAkun();
            }
        });

        kembali.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.backEdit(1);
            }
        });


    }// </editor-fold>//GEN-END:initComponents

    private void pilihDetailEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihDetailEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pilihDetailEditActionPerformed

    private void simpanPerubahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanPerubahanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_simpanPerubahanActionPerformed

    private void dataBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataBaruActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataBaruActionPerformed

    private void pilihAkunEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihAkunEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pilihAkunEditActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField dataBaru;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel kembali;
    public javax.swing.JTextField pilihAkunEdit;
    public javax.swing.JComboBox<String> pilihDetailEdit;
    public javax.swing.JButton simpanPerubahan;
    public javax.swing.JTable tabelAkun;
    // End of variables declaration//GEN-END:variables
}
