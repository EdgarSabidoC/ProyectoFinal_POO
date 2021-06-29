package View;

import App.PruebaBiblioteca;
import Model.Admin;
import Model.Date;
import Model.ID;
import Model.Password;
import Model.SuperAdmin;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Edgar Sabido Cortés, Carlos Antonio Ruíz Domínguez, Luis Alfredo Cota Armenta.
 * 
 */
public class AdminAdd extends javax.swing.JFrame {
    
    //Constructor del Frame AdminAdd.
    public AdminAdd() {
        //Se inician los componentes internos.
        initComponents();
        
        //Se establece la posición del frame al centro de la pantalla.
        setLocationRelativeTo(null);
        
        //Se carga el icono del programa.
        iconFrame();
        
        //Placeholders:
        TextPrompt txtNamePlaceholder = new TextPrompt("Nombre", this.txtName);
        TextPrompt txtLastName1Placeholder = new TextPrompt("Apellido paterno", this.txtLastName1);
        TextPrompt txtLastName2Placeholder = new TextPrompt("Apellido materno", this.txtLastName2);
        
        //Color de transparencia de los placeholders:
        txtNamePlaceholder.changeAlpha(0.75f);
        txtLastName1Placeholder.changeAlpha(0.75f);
        txtLastName2Placeholder.changeAlpha(0.75f);
        
        //Estilo de letra de los placeholders:
        txtNamePlaceholder.changeStyle(Font.PLAIN);
        txtLastName1Placeholder.changeStyle(Font.PLAIN);
        txtLastName2Placeholder.changeStyle(Font.PLAIN);
        
        //Errores
        jblErrorLastName1.setVisible(false);
        jblErrorLastName2.setVisible(false);
        jblErrorName.setVisible(false);
    }
    
    //Metodo que carga una imagen y la establece como el icono del programa.
    public void iconFrame() {
        URL url = getClass().getResource("/Images/iconFrame.png");
        ImageIcon iconFrame = new ImageIcon(url);
        setIconImage(iconFrame.getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelAdminAdd = new javax.swing.JPanel();
        jblErrorName = new javax.swing.JLabel();
        jblErrorLastName2 = new javax.swing.JLabel();
        jblErrorLastName1 = new javax.swing.JLabel();
        txtLastName2 = new javax.swing.JTextField();
        txtLastName1 = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        btnAddAdmin = new javax.swing.JButton();
        jlbDecorative = new javax.swing.JLabel();
        jlbAdd = new javax.swing.JLabel();
        jlbPanel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AÑADIR ADMINISTRADOR");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelAdminAdd.setBackground(new java.awt.Color(0, 38, 78));
        PanelAdminAdd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jblErrorName.setForeground(new java.awt.Color(255, 0, 51));
        PanelAdminAdd.add(jblErrorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 290, 20));

        jblErrorLastName2.setForeground(new java.awt.Color(255, 0, 51));
        PanelAdminAdd.add(jblErrorLastName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 290, 20));

        jblErrorLastName1.setForeground(new java.awt.Color(255, 0, 51));
        PanelAdminAdd.add(jblErrorLastName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 290, 20));

        txtLastName2.setBackground(new java.awt.Color(166, 184, 204));
        txtLastName2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtLastName2.setForeground(new java.awt.Color(0, 0, 0));
        txtLastName2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLastName2KeyReleased(evt);
            }
        });
        PanelAdminAdd.add(txtLastName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 290, 50));

        txtLastName1.setBackground(new java.awt.Color(166, 184, 204));
        txtLastName1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtLastName1.setForeground(new java.awt.Color(0, 0, 0));
        txtLastName1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLastName1KeyReleased(evt);
            }
        });
        PanelAdminAdd.add(txtLastName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 290, 50));

        txtName.setBackground(new java.awt.Color(166, 184, 204));
        txtName.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtName.setForeground(new java.awt.Color(0, 0, 0));
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNameKeyReleased(evt);
            }
        });
        PanelAdminAdd.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 290, 50));

        btnAddAdmin.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnAddAdmin.setForeground(new java.awt.Color(213, 159, 0));
        btnAddAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnAddAdmin.setText("Añadir administrador");
        btnAddAdmin.setBorderPainted(false);
        btnAddAdmin.setFocusPainted(false);
        btnAddAdmin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddAdmin.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnAddAdmin.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1_Rollover.png"))); // NOI18N
        btnAddAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAdminActionPerformed(evt);
            }
        });
        PanelAdminAdd.add(btnAddAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 290, 40));

        jlbDecorative.setForeground(new java.awt.Color(213, 159, 0));
        jlbDecorative.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbDecorative.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Panel2.png"))); // NOI18N
        jlbDecorative.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PanelAdminAdd.add(jlbDecorative, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 40, 50));

        jlbAdd.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jlbAdd.setForeground(new java.awt.Color(255, 255, 255));
        jlbAdd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Panel2.png"))); // NOI18N
        jlbAdd.setText("AÑADIR ADMINISTRADOR");
        jlbAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PanelAdminAdd.add(jlbAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 270, 50));

        jlbPanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Panel.png"))); // NOI18N
        PanelAdminAdd.add(jlbPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 350, 500));

        getContentPane().add(PanelAdminAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAdminActionPerformed
        if(!(this.txtName.getText().isEmpty()) 
            && !(this.txtLastName1.getText().isEmpty())
            && !(this.txtLastName2.getText().isEmpty())) {
            // AQUÍ VA TODO LO QUE SE DEBE DE REALIZAR AL PRESIONAR EL BOTÓN.
            String name = this.txtName.getText();
            String lastName1 = this.txtLastName1.getText();
            String lastName2 = this.txtLastName2.getText();
            
            ((SuperAdmin)PruebaBiblioteca.user).addUserToList(PruebaBiblioteca.biblioteca.getAdminsList(), new Admin(name, lastName1, lastName2, new ID("_base64", 10), 
                                                       new Password(12), new Date()));
            PruebaBiblioteca.biblioteca.updateInfoInFiles();
        } 
        
        if(this.txtName.getText().isEmpty()) {
            // Si campo Name está vacío:
            this.txtName.setBackground(Color.PINK);
            this.jblErrorName.setText("*Campo obligatorio");
            this.jblErrorName.setVisible(true);
        }
        
        if(this.txtLastName1.getText().isEmpty()) {
            // Si campo LastName1 está vacío:
            this.txtLastName1.setBackground(Color.PINK);
            this.jblErrorLastName1.setText("*Campo obligatorio");
            this.jblErrorLastName1.setVisible(true);
        }
        
        if(this.txtLastName2.getText().isEmpty()) {
            // Si campo LastName2 está vacío:
            this.txtLastName2.setBackground(Color.PINK);
            this.jblErrorLastName2.setText("*Campo obligatorio");
            this.jblErrorLastName2.setVisible(true);
        } 
    }//GEN-LAST:event_btnAddAdminActionPerformed

    private void txtNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyReleased
        // Char leído al presionar la tecla:
        Character c = evt.getKeyChar();

        if (c >= KeyEvent.VK_0 && c <= KeyEvent.VK_9) {
            // Si se ingresan letras:
            new Validate().isValidStringWithoutDigits(this.txtName, this.jblErrorName);

        } else if (this.txtName.getText().isEmpty() || new Validate().isValidStringWithoutDigits(this.txtName, this.jblErrorName)) {
            // Si el campo de texto está vacío o puede ser convertido a número:
            this.jblErrorName.setVisible(false);
            this.txtName.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_txtNameKeyReleased

    private void txtLastName1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastName1KeyReleased
        // Char leído al presionar la tecla:
        Character c = evt.getKeyChar();

        if (c >= KeyEvent.VK_0 && c <= KeyEvent.VK_9) {
            // Si se ingresan letras:
            new Validate().isValidStringWithoutDigits(this.txtLastName1, this.jblErrorLastName1);

        } else if (this.txtName.getText().isEmpty() || new Validate().isValidStringWithoutDigits(this.txtLastName1, this.jblErrorLastName1)) {
            // Si el campo de texto está vacío o puede ser convertido a número:
            this.jblErrorLastName1.setVisible(false);
            this.txtLastName1.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_txtLastName1KeyReleased

    private void txtLastName2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastName2KeyReleased
        // Char leído al presionar la tecla:
        Character c = evt.getKeyChar();

        if (c >= KeyEvent.VK_0 && c <= KeyEvent.VK_9) {
            // Si se ingresan letras:
            new Validate().isValidStringWithoutDigits(this.txtLastName2, this.jblErrorLastName2);

        } else if (this.txtName.getText().isEmpty() || new Validate().isValidStringWithoutDigits(this.txtLastName2, this.jblErrorLastName2)) {
            // Si el campo de texto está vacío o puede ser convertido a número:
            this.jblErrorLastName2.setVisible(false);
            this.txtLastName2.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_txtLastName2KeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelAdminAdd;
    private javax.swing.JButton btnAddAdmin;
    private javax.swing.JLabel jblErrorLastName1;
    private javax.swing.JLabel jblErrorLastName2;
    private javax.swing.JLabel jblErrorName;
    private javax.swing.JLabel jlbAdd;
    private javax.swing.JLabel jlbDecorative;
    private javax.swing.JLabel jlbPanel;
    private javax.swing.JTextField txtLastName1;
    private javax.swing.JTextField txtLastName2;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
