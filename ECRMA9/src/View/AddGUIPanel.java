/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.CandidateController;
import Controller.FormEvent;
import Controller.FormListener;
import Model.Candidate;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author Uriel Federez
 */
public class AddGUIPanel extends javax.swing.JPanel {

    private final SimpleDateFormat date_fmt = new SimpleDateFormat("dd-MM-yyyy");
    // TODO: because ElecPerController has some errors, using this for now
    //       change to use MainController later on
    private CandidateController candidateController = new CandidateController();
    private FormListener form_listener = null;
    private Candidate   candidate_info = null;
    private JFileChooser image_chooser = new JFileChooser();
    private String image_path = "";
    /**
     * Creates new form AddGUIPanel
     */
    public AddGUIPanel() {
        initComponents(); 
        
        birthDateSpinner.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        birthDateSpinner.setEditor(new JSpinner.DateEditor(birthDateSpinner,
                                                          date_fmt.toPattern()));     
        
        gradDateSpinner.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        gradDateSpinner.setEditor(new JSpinner.DateEditor(gradDateSpinner,
                                                          date_fmt.toPattern()));     
        
        midInitialTxt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(midInitialTxt.getText().length() == 1)
                {
                    midInitialTxt.setText(("" + e.getKeyChar()));
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}
            
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        setSize(1250, 810);
    }
    
    
    public void set_form_listener(FormListener form_listener)
    {
        this.form_listener = form_listener;
    }
    
    
     public void reset(){
         
         firstNameTxt.setText("First name");
         lastNameTxt.setText("Last name");
         midInitialTxt.setText("Middle initial");
         birthDateSpinner.setValue(new Date());
         gradDateSpinner.setValue(new Date());
         religionTxt.setText("Religion");
         universityTxt.setText("University");
         degreeTxt.setText("Degree");
     }

     private String validate_input()
    {
        String error = "";
        boolean is_valid_fname = (!firstNameTxt.getText().isEmpty() &&
                                  !firstNameTxt.getText().equals("First name"));
        boolean is_valid_lname = (!lastNameTxt.getText().isEmpty() &&
                                  !lastNameTxt.getText().equals("Last name"));
        boolean is_valid_midI  = (midInitialTxt.getText().length() == 1 &&
                                  !midInitialTxt.getText().equals("Middle initial"));
        boolean is_valid_uni   = (!universityTxt.getText().equals("University"));
        boolean is_valid_deg   = (!degreeTxt.getText().equals("Degree"));

        Date birth_date   = (Date) birthDateSpinner.getValue();
        Date grad_date    = (Date) gradDateSpinner.getValue();
        Date current_date = new Date();

        boolean is_valid_bdate =  (get_date_difference(current_date, grad_date) >= (365))
                                  && (birth_date.getTime() < grad_date.getTime());
        boolean is_valid_gdate =  (get_date_difference(current_date, grad_date) >= (365));

        if(!is_valid_fname || !is_valid_lname || !is_valid_midI)
            error += "Invalid name entered!\n";
        if(!is_valid_bdate)
            error += "Invalid birth date entered!\n";
        if(!is_valid_uni || !is_valid_deg || !is_valid_gdate)
            error += "Invalid educational background entered!\n";
        return error;
    }
    
    
    private long get_date_difference(Date d1, Date d2) {
        long time_diff = d1.getTime() - d2.getTime();
        return TimeUnit.DAYS.convert(time_diff, TimeUnit.MILLISECONDS);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        imageLabel = new javax.swing.JLabel();
        firstNameTxt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        religionTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        birthDateSpinner = new javax.swing.JSpinner();
        sexComboBox = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        degreeTxt = new javax.swing.JTextField();
        universityTxt = new javax.swing.JTextField();
        gradDateSpinner = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        cancelBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        confirmBtn = new javax.swing.JButton();
        lastNameTxt = new javax.swing.JTextField();
        midInitialTxt = new javax.swing.JTextField();
        browseImageBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 97, 140)));

        firstNameTxt.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        firstNameTxt.setForeground(new java.awt.Color(33, 97, 140));
        firstNameTxt.setText("First name");
        firstNameTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        firstNameTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                firstNameTxtMouseClicked(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(33, 97, 140));
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(33, 97, 140));
        jLabel17.setText("Personal Information");

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(33, 97, 140));
        jLabel7.setText("Sex");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jPanel5.add(jLabel7, gridBagConstraints);

        religionTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        religionTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        religionTxt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel5.add(religionTxt, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(33, 97, 140));
        jLabel8.setText("Religion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.33;
        jPanel5.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(33, 97, 140));
        jLabel9.setText("Date of Birth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 30);
        jPanel5.add(jLabel9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 5, 30);
        jPanel5.add(birthDateSpinner, gridBagConstraints);

        sexComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 30);
        jPanel5.add(sexComboBox, gridBagConstraints);

        jLabel18.setBackground(new java.awt.Color(33, 97, 140));
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(33, 97, 140));
        jLabel18.setText("Educational Background");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        degreeTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        degreeTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        degreeTxt.setText("Degree");
        degreeTxt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        degreeTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                degreeTxtMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 15, 20);
        jPanel7.add(degreeTxt, gridBagConstraints);

        universityTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        universityTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        universityTxt.setText("University");
        universityTxt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        universityTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                universityTxtMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 15, 20);
        jPanel7.add(universityTxt, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jPanel7.add(gradDateSpinner, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(33, 97, 140));
        jLabel10.setText("Graduation Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        jPanel7.add(jLabel10, gridBagConstraints);

        cancelBtn.setBackground(new java.awt.Color(33, 97, 140));
        cancelBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cancelBtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelBtn.setText("Cancel");
        cancelBtn.setContentAreaFilled(false);
        cancelBtn.setOpaque(true);
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        resetBtn.setBackground(new java.awt.Color(33, 97, 140));
        resetBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        resetBtn.setForeground(new java.awt.Color(255, 255, 255));
        resetBtn.setText("Reset");
        resetBtn.setContentAreaFilled(false);
        resetBtn.setOpaque(true);
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        confirmBtn.setBackground(new java.awt.Color(33, 97, 140));
        confirmBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        confirmBtn.setForeground(new java.awt.Color(255, 255, 255));
        confirmBtn.setText("Confirm");
        confirmBtn.setContentAreaFilled(false);
        confirmBtn.setOpaque(true);
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBtnActionPerformed(evt);
            }
        });

        lastNameTxt.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lastNameTxt.setForeground(new java.awt.Color(33, 97, 140));
        lastNameTxt.setText("Last name");
        lastNameTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        lastNameTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lastNameTxtMouseClicked(evt);
            }
        });

        midInitialTxt.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        midInitialTxt.setForeground(new java.awt.Color(33, 97, 140));
        midInitialTxt.setText("Middle initial");
        midInitialTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        midInitialTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                midInitialTxtMouseClicked(evt);
            }
        });

        browseImageBtn.setText("Browse Image");
        browseImageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseImageBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(browseImageBtn)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(firstNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lastNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(midInitialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1156, Short.MAX_VALUE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(160, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resetBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(confirmBtn)
                .addGap(84, 84, 84))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(firstNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(midInitialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(browseImageBtn))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addGap(15, 15, 15)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmBtn)
                    .addComponent(resetBtn)
                    .addComponent(cancelBtn))
                .addContainerGap(188, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_resetBtnActionPerformed

    private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed
        String error = validate_input();
        System.out.println("Error: " + error);
        if(!error.isEmpty())
        {
            // TODO: possibly change message if there is a better message/design
            JOptionPane.showMessageDialog(null,
                                          error,
                                          "Add Candidate failed!",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }

        String first_name  = firstNameTxt.getText();
        String last_name   = lastNameTxt.getText();
        char mid_initial   = Character.toUpperCase(midInitialTxt.getText().charAt(0));
        String religion    = religionTxt.getText();
        Date birth_date    = (Date) birthDateSpinner.getValue();

        String degree      = degreeTxt.getText();
        String univeristy  = universityTxt.getText();
        Date grad_date     = (Date) gradDateSpinner.getValue();
        
        boolean is_existing = true;
        if(candidate_info == null)
        {
            candidate_info = new Candidate();
            is_existing    = false;
        }
        
        candidate_info.set_birth_date(birth_date);
        candidate_info.set_first_name(first_name);
        candidate_info.set_last_name(last_name);
        candidate_info.set_middle_initial(mid_initial);
        candidate_info.set_religion(religion);
        candidate_info.set_degree(degree);
        candidate_info.set_university(univeristy);
        candidate_info.set_grad_date(grad_date);
        candidate_info.set_sex((String) sexComboBox.getSelectedItem());
        candidate_info.set_image_path(image_path);

        if(form_listener != null)
        {
            FormEvent form_event = new FormEvent(this);
            form_event.setCandidate(candidate_info);
            form_event.setPurpose(is_existing ? "candidate_add" : "candidate_update");
            form_listener.formEventOccurred(form_event);
        }
        System.out.println("added candidate");
    }//GEN-LAST:event_confirmBtnActionPerformed

    private void browseImageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseImageBtnActionPerformed
        int return_val = image_chooser.showDialog(null, "Select");

        if(return_val == JFileChooser.APPROVE_OPTION)
        {
            File selected_file = image_chooser.getSelectedFile();
            try {
                image_path = "img/" + selected_file.getName();

                Files.copy(selected_file.toPath(),
                    new File(image_path).toPath(),
                        StandardCopyOption.REPLACE_EXISTING);

                set_image_label(image_path);

            } catch (IOException ex) {
                Logger.getLogger(AddGUI.class.getName()).log(Level.ALL.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_browseImageBtnActionPerformed

    private void firstNameTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_firstNameTxtMouseClicked
        // TODO add your handling code here:
        firstNameTxt.setText("");
    }//GEN-LAST:event_firstNameTxtMouseClicked

    private void lastNameTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameTxtMouseClicked
        // TODO add your handling code here:
        lastNameTxt.setText("");
    }//GEN-LAST:event_lastNameTxtMouseClicked

    private void midInitialTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_midInitialTxtMouseClicked
        // TODO add your handling code here:
        midInitialTxt.setText("");
    }//GEN-LAST:event_midInitialTxtMouseClicked

    private void degreeTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_degreeTxtMouseClicked
        // TODO add your handling code here:
        degreeTxt.setText("");
    }//GEN-LAST:event_degreeTxtMouseClicked

    private void universityTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_universityTxtMouseClicked
        // TODO add your handling code here:
        universityTxt.setText("");
    }//GEN-LAST:event_universityTxtMouseClicked

    private void set_image_label(String path)
    {
        ImageIcon img_icon  = new ImageIcon(path);
        Image img_base      = img_icon.getImage();
        Image img_resized   = img_base.getScaledInstance(imageLabel.getWidth(), 
                                                         imageLabel.getHeight(),
                                                         java.awt.Image.SCALE_SMOOTH);                
        imageLabel.setIcon(new ImageIcon(img_resized));
    }
    
    public void set_candidate_details(Candidate candidate_info)
    {
        this.candidate_info = candidate_info;
        this.image_path     = candidate_info.get_image_path();
        
        firstNameTxt.setText(candidate_info.get_first_name());
        lastNameTxt.setText(candidate_info.get_last_name());
        midInitialTxt.setText("" + candidate_info.get_mid_initial());
        birthDateSpinner.setValue(candidate_info.get_birth_date());
        religionTxt.setText(candidate_info.get_religion());
        degreeTxt.setText(candidate_info.get_degree());
        universityTxt.setText(candidate_info.get_university());
        gradDateSpinner.setValue(candidate_info.get_grad_date());
        sexComboBox.setSelectedItem((String) candidate_info.get_sex());
        birthDateSpinner.setValue(candidate_info.get_birth_date());
        
        if(!candidate_info.get_image_path().isEmpty())
            set_image_label(candidate_info.get_image_path());
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner birthDateSpinner;
    private javax.swing.JButton browseImageBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton confirmBtn;
    private javax.swing.JTextField degreeTxt;
    private javax.swing.JTextField firstNameTxt;
    private javax.swing.JSpinner gradDateSpinner;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField lastNameTxt;
    private javax.swing.JTextField midInitialTxt;
    private javax.swing.JTextField religionTxt;
    private javax.swing.JButton resetBtn;
    private javax.swing.JComboBox<String> sexComboBox;
    private javax.swing.JTextField universityTxt;
    // End of variables declaration//GEN-END:variables
}
