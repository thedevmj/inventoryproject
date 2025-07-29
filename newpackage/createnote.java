/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import services.delaytask;
import dao.connectionprovider;
import static java.lang.System.exit;
import java.sql.*;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.io.*;
import java.util.Vector;
import javax.swing.JFileChooser;

/**
 *
 * @author Junaid Mansuri
 */
public class createnote extends javax.swing.JFrame {

    private int noteid = 0;

    /**
     * Creates new form createnote
     */
    public createnote() {
        initComponents();
        setLocationRelativeTo(null);
        btnupdate.setEnabled(false);
        loadid();
        delaytask.delay(1500, () -> (JOptionPane.showMessageDialog(null, "Welcome!")));
        txtsearch.setVisible(true);
        combos.setVisible(true);

//        setupverification();
    }

//    private void setupverification() {
//
//        try {
//            int uid = AppSession.userid;
//            Connection con = connectionprovider.getCon();
//            PreparedStatement pst = con.prepareStatement("select privsetup from usertbl where userid=?");
//            pst.setInt(1, uid);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                boolean setup = rs.getBoolean("privsetup");
//
//                if (setup) {
//                   
//                    privspace.setVisible(true);
//
//                } else {
//                    privspace.setVisible(true);
//                   
//
//                }
//            }
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "An Error occured !" + e);
//
//        }
//
//    }
    private void loadid() {

        TableColumnModel tcm = tblnotes.getColumnModel();
        tcm.removeColumn(tcm.getColumn(0));

    }

    private boolean getContent() {

        TableModel model = tblnotes.getModel();

        int index = tblnotes.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(null, "Please select a note for update !");
            return false;
        }
        String originaltitle = model.getValueAt(index, 1).toString();
        String originalcontent = model.getValueAt(index, 2).toString();
        String currenttitle = txttitle.getText();
        String currentcontent = txtcontent.getText();

        return !originalcontent.equals(currentcontent) || !originaltitle.equals(currenttitle);

    }

    private void emptyfield() {
        txttitle.setText("");
        txtcontent.setText("");
        lbldt.setText("");
    }

    private void loadtable() {

        DefaultTableModel model = (DefaultTableModel) tblnotes.getModel();
        model.setRowCount(0);

        try {
            Connection con = connectionprovider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from tblnote");
            while (rs.next()) {

                model.addRow(new Object[]{rs.getInt("noteid"), rs.getString("notetitle"), rs.getString("notecontent"), rs.getString("notedatetime")});

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        tblnotes.repaint();

    }

    private void loadsearchtable(String name) {

        DefaultTableModel model = (DefaultTableModel) tblnotes.getModel();

        try {

            Connection con = connectionprovider.getCon();
            PreparedStatement pst = con.prepareStatement("select * from tblnote where notetitle = ?");
            pst.setString(1, name);

            ResultSet rs = pst.executeQuery();
            model.setRowCount(0);
            while (rs.next()) {

                model.addRow(new Object[]{rs.getInt("noteid"), rs.getString("notetitle"), rs.getString("notecontent"), rs.getString("notedatetime")});

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    private void loaddatetimetable(int date) {
        DefaultTableModel model = (DefaultTableModel) tblnotes.getModel();

        try {

            Connection con = connectionprovider.getCon();
            PreparedStatement pst = con.prepareStatement("select * from tblnote where day(notedatetime) < ?");
            pst.setInt(1, date);
            ResultSet rs = pst.executeQuery();
            model.setRowCount(0);
            while (rs.next()) {

                model.addRow(new Object[]{rs.getInt("noteid"), rs.getString("notetitle"), rs.getString("notecontent"), rs.getString("notedatetime")});

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }

    }

    private void loadolddates(int date) {

        DefaultTableModel model = (DefaultTableModel) tblnotes.getModel();

        try {

            Connection con = connectionprovider.getCon();
            PreparedStatement pst = con.prepareStatement("select * from tblnote where day(notedatetime) > ?");
            pst.setInt(1, date);
            ResultSet rs = pst.executeQuery();
            model.setRowCount(0);
            while (rs.next()) {

                model.addRow(new Object[]{rs.getInt("noteid"), rs.getString("notetitle"), rs.getString("notecontent"), rs.getString("notedatetime")});

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }

    }

    private void loadchartable(char name) {

        DefaultTableModel model = (DefaultTableModel) tblnotes.getModel();
        try {

            Connection con = connectionprovider.getCon();
            PreparedStatement pst = con.prepareStatement("select * from tblnote where LOWER(notetitle) like ?");
            pst.setString(1, Character.toLowerCase(name) + "%");
            ResultSet rs = pst.executeQuery();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("noteid"), rs.getString("notetitle"), rs.getString("notecontent"), rs.getString("notedatetime")});

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }

    }

    private boolean validateform(String formtype) {
        if (formtype.equals("new") && !txttitle.getText().equals("") && !txtcontent.getText().equals("")) {
            return true;
        } else if (formtype.equals("edit") && !txtcontent.getText().equals("")) {
            return true;

        } else {
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblnotes = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtcontent = new javax.swing.JTextArea();
        btnsave = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnclose = new javax.swing.JButton();
        txttitle = new javax.swing.JTextField();
        lbldt = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        combos = new javax.swing.JComboBox<>();
        btnsearch = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        privspace = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Handwriting", 1, 36)); // NOI18N
        jLabel1.setText("Notes");

        tblnotes.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        tblnotes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Title", "Content", "Date Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblnotes.getTableHeader().setReorderingAllowed(false);
        tblnotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnotesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblnotes);

        txtcontent.setColumns(20);
        txtcontent.setRows(5);
        jScrollPane2.setViewportView(txtcontent);

        btnsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_12.png"))); // NOI18N
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        btnupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/updated_12.png"))); // NOI18N
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btndelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_12.png"))); // NOI18N
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btnclose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_12.png"))); // NOI18N
        btnclose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncloseActionPerformed(evt);
            }
        });

        txttitle.setText("Add Title....");
        txttitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttitleMouseClicked(evt);
            }
        });
        txttitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttitleActionPerformed(evt);
            }
        });

        lbldt.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbldt.setText("Date And Time :");

        jButton1.setText("Create Category");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtsearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtsearchFocusGained(evt);
            }
        });

        combos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Search ->", "Alphabet", "Name", "Latest", "Oldest", " ", " " }));

        btnsearch.setText("Search");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        jMenu1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu1.setText("Open Text File      ");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        privspace.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        privspace.setText("Private Space   ");
        privspace.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                privspaceMouseClicked(evt);
            }
        });
        jMenuBar1.add(privspace);

        jMenu4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu4.setText(" Add Categories      ");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnclose, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
            .addGroup(layout.createSequentialGroup()
                .addGap(381, 381, 381)
                .addComponent(combos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbldt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(36, 36, 36))
                            .addComponent(txttitle, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE))
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsearch)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsearch))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbldt)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txttitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnclose, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 93, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
        String title = txttitle.getText().trim();
        String content = txtcontent.getText();
        if (!validateform("new")) {
            JOptionPane.showMessageDialog(null, "Please fill all fields");

        } else {
            try {
                if (txttitle.getText().equals("Add Title..."));
                {
                    txttitle.setText("");

                }
                LocalDateTime currenttime = LocalDateTime.now();
                String formattedtime = currenttime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                Connection con = connectionprovider.getCon();
                PreparedStatement pst = con.prepareStatement("insert into tblnote (notetitle,notecontent,notedatetime) values(?,?,?)");

                pst.setString(1, title);
                pst.setString(2, content);
                pst.setString(3, formattedtime);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Note Saved");
                txttitle.setText("Add Title...");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);

            }

            loadtable();
            emptyfield();
        }


    }//GEN-LAST:event_btnsaveActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:

        loadtable();


    }//GEN-LAST:event_formComponentShown

    private void txttitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttitleActionPerformed

    private void txttitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttitleMouseClicked
        // TODO add your handling code here:
        txttitle.selectAll();

    }//GEN-LAST:event_txttitleMouseClicked

    private void btncloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncloseActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(null, "Do you want to Exit the Application ?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            emptyfield();
            AppSession.userid = -1;
            exit(0);

        }

    }//GEN-LAST:event_btncloseActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:

        String title = txttitle.getText();
        String content = txtcontent.getText();

        if (!validateform("edit")) {
            JOptionPane.showMessageDialog(null, "Please fill all fields");

        } else {

            if (getContent()) {
                try {

                    Connection con = connectionprovider.getCon();
                    PreparedStatement pst = con.prepareStatement("update tblnote set notetitle=?,notecontent=? where noteid=?");
                    pst.setString(1, title);
                    pst.setString(2, content);
                    pst.setInt(3, noteid);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Note Updated");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);

                }
                loadtable();
                emptyfield();
            } else {
                JOptionPane.showMessageDialog(null, "Cannot update ,No changes detected ");

            }

        }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void tblnotesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnotesMouseClicked
        // TODO add your handling code here:
        int index = tblnotes.getSelectedRow();
        TableModel model = tblnotes.getModel();

        noteid = Integer.parseInt(model.getValueAt(index, 0).toString());

        String title = model.getValueAt(index, 1).toString();
        txttitle.setText("");
        txttitle.setText(title);

        String content = (String) model.getValueAt(index, 2);
        txtcontent.setText(content);

        String datetime = (String) model.getValueAt(index, 3);
        lbldt.setText(datetime);

        btnsave.setEnabled(false);
        btnupdate.setEnabled(true);
    }//GEN-LAST:event_tblnotesMouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:

        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text file", "txt"));
        int result = filechooser.showOpenDialog(this);
        File selectedfile = filechooser.getSelectedFile();
        String filename = selectedfile.getName();
        if (result == filechooser.APPROVE_OPTION) {
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedfile))) {

                txtcontent.read(reader, null);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Failed to open file ");
            }
            txttitle.setText(filename);
        }


    }//GEN-LAST:event_jMenu1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        new createcategories().setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        // TODO add your handling code here:

        new viewcategory().setVisible(true);
    }//GEN-LAST:event_jMenu4MouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:

        int row = tblnotes.getSelectedRow();
        if (row != -1) {
            tblnotes.clearSelection();
            txtcontent.setText("");
            lbldt.setText("");
            txttitle.setText("Title... ");
        } else {
            lbldt.setText("Date And Time :");
        }
        loadtable();
        txtsearch.setText("");
        btnsearch.setEnabled(false);

    }//GEN-LAST:event_formMouseClicked

    private void privspaceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_privspaceMouseClicked
        // TODO add your handling code here:
        try {
            Connection con = connectionprovider.getCon();
            PreparedStatement pst = con.prepareStatement("select privsetup from usertbl where userid=?");
            int uid = AppSession.userid;
            pst.setInt(1, uid);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                boolean setup = rs.getBoolean("privsetup");
                System.out.println(setup);
                if (setup) {
                    new privspacelogin().setVisible(true);
                } else {
                    int vid = rs.getInt("privsetup");
                    if (vid == 0) {

                        new setprivspacepass().setVisible(true);

                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }//GEN-LAST:event_privspaceMouseClicked

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        int notecount = tblnotes.getSelectedRow();
        if (notecount == -1) {
            JOptionPane.showMessageDialog(null, "No Note Selected To Delete ");

        } else {
            try {

                Connection con = connectionprovider.getCon();
                PreparedStatement pst = con.prepareStatement("delete from tblnote where noteid=?");
                pst.setInt(1, noteid);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Note Deleted Successfully !");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error deleting note !");
            }
            loadtable();
            emptyfield();
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // TODO add your handling code here:
        String selectedsearch = combos.getSelectedItem().toString();
        String notename = txtsearch.getText().trim();
        switch (selectedsearch) {

            case "Name":
                if (!notename.equals("")) {
                    loadsearchtable(notename);
                    selectedsearch = "";
                }
                break;
                
            case "Latest":
                int date = Integer.parseInt(notename);
                loaddatetimetable(date);
                selectedsearch = "";
                break;

            case "Oldest":
                int date_2 = Integer.parseInt(notename);
                loadolddates(date_2);
                selectedsearch = "";
            case "Alphabet":
                char note = notename.charAt(0);
                loadchartable(note);
                selectedsearch = "";

                break;

            case "Search ->":
                JOptionPane.showMessageDialog(null, "Please select How to search !");
                selectedsearch = "";
                break;
        }


    }//GEN-LAST:event_btnsearchActionPerformed

    private void txtsearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtsearchFocusGained
        // TODO add your handling code here:
        btnsearch.setEnabled(true);
    }//GEN-LAST:event_txtsearchFocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(createnote.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(createnote.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(createnote.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(createnote.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new createnote().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnclose;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> combos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbldt;
    private javax.swing.JMenu privspace;
    private javax.swing.JTable tblnotes;
    private javax.swing.JTextArea txtcontent;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txttitle;
    // End of variables declaration//GEN-END:variables
}
