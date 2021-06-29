package View;

import java.awt.Font;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Carlos Antonio Ruiz
 */
public class LoginAdmin extends javax.swing.JFrame {
    
    //Constructor del Frame Login para administradores.
    public LoginAdmin() {
        //Se inician los componentes internos.
        initComponents();
        
        //Se establece la posición del frame al centro de la pantalla.
        setLocationRelativeTo(null);
        
        //Se carga el icono del programa.
        iconFrame();
        
        //Placeholders:
        TextPrompt txtUsernamePlaceholder = new TextPrompt("Nombre de administrador", this.txtAdminName);
        TextPrompt txtPasswordPlaceholder = new TextPrompt("Contraseña", this.txtPassword);
        
        //Color de transparencia de los placeholders:
        txtUsernamePlaceholder.changeAlpha(0.75f);
        txtPasswordPlaceholder.changeAlpha(0.75f);
        
        //Estilo de letra de los placeholders:
        txtUsernamePlaceholder.changeStyle(Font.PLAIN);
        txtPasswordPlaceholder.changeStyle(Font.PLAIN);
        
        //Errores
        jblAdminLoginError.setVisible(false);
        jblAdminPasswordError.setVisible(false);
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

        PanelLogin = new javax.swing.JPanel();
        jblAdminPasswordError = new javax.swing.JLabel();
        jblAdminLoginError = new javax.swing.JLabel();
        txtAdminName = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLoginAdmin = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        cbxShowPassword = new javax.swing.JCheckBox();
        jlbText = new javax.swing.JLabel();
        jlbDecorative = new javax.swing.JLabel();
        jlbLogin = new javax.swing.JLabel();
        jlbPanel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BIBLIOTECA K'ASTAKÁNO'OB");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelLogin.setBackground(new java.awt.Color(0, 38, 78));
        PanelLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jblAdminPasswordError.setForeground(new java.awt.Color(0, 0, 0));
        PanelLogin.add(jblAdminPasswordError, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 290, 20));

        jblAdminLoginError.setForeground(new java.awt.Color(0, 0, 0));
        PanelLogin.add(jblAdminLoginError, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 290, 20));

        txtAdminName.setBackground(new java.awt.Color(166, 184, 204));
        txtAdminName.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtAdminName.setForeground(new java.awt.Color(0, 0, 0));
        PanelLogin.add(txtAdminName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 290, 50));

        txtPassword.setBackground(new java.awt.Color(166, 184, 204));
        txtPassword.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(0, 0, 0));
        PanelLogin.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 290, 50));

        btnLoginAdmin.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnLoginAdmin.setForeground(new java.awt.Color(255, 255, 255));
        btnLoginAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn2.png"))); // NOI18N
        btnLoginAdmin.setText("Ingresar como usuario");
        btnLoginAdmin.setBorderPainted(false);
        btnLoginAdmin.setFocusPainted(false);
        btnLoginAdmin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLoginAdmin.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn2.png"))); // NOI18N
        btnLoginAdmin.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn2_Rollover.png"))); // NOI18N
        btnLoginAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginAdminActionPerformed(evt);
            }
        });
        PanelLogin.add(btnLoginAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 290, 40));

        btnLogin.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(213, 159, 0));
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnLogin.setText("Ingresar ahora");
        btnLogin.setBorderPainted(false);
        btnLogin.setFocusPainted(false);
        btnLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLogin.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnLogin.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1_Rollover.png"))); // NOI18N
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        PanelLogin.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 290, 40));

        cbxShowPassword.setBackground(new java.awt.Color(255, 255, 255));
        cbxShowPassword.setForeground(new java.awt.Color(0, 38, 78));
        cbxShowPassword.setText("Mostrar contraseña");
        cbxShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxShowPasswordActionPerformed(evt);
            }
        });
        PanelLogin.add(cbxShowPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        jlbText.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jlbText.setForeground(new java.awt.Color(213, 159, 0));
        jlbText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbText.setText("O");
        jlbText.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PanelLogin.add(jlbText, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 290, 30));

        jlbDecorative.setForeground(new java.awt.Color(213, 159, 0));
        jlbDecorative.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbDecorative.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Panel2.png"))); // NOI18N
        jlbDecorative.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PanelLogin.add(jlbDecorative, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 40, 50));

        jlbLogin.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jlbLogin.setForeground(new java.awt.Color(255, 255, 255));
        jlbLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Panel2.png"))); // NOI18N
        jlbLogin.setText("INICIAR SESIÓN");
        jlbLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PanelLogin.add(jlbLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 170, 50));

        jlbPanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Panel.png"))); // NOI18N
        PanelLogin.add(jlbPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 350, 500));

        getContentPane().add(PanelLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //Metodo que muestra u oculta el contendio del campo de la contraseña.
    private void cbxShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxShowPasswordActionPerformed
        if(cbxShowPassword.isSelected()) {
            this.txtPassword.setEchoChar((char)0);
        }
        else {
            this.txtPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_cbxShowPasswordActionPerformed
    
     //Metodo que cierra el login del administrador e inicia el login para usuarios.
    private void btnLoginAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginAdminActionPerformed
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLoginAdminActionPerformed
    
    //Metodo que ingresa al dashboard a traves de un administrador ingresado.
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        Dashboard dash = new Dashboard();
        dash.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLoginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelLogin;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLoginAdmin;
    private javax.swing.JCheckBox cbxShowPassword;
    private javax.swing.JLabel jblAdminLoginError;
    private javax.swing.JLabel jblAdminPasswordError;
    private javax.swing.JLabel jlbDecorative;
    private javax.swing.JLabel jlbLogin;
    private javax.swing.JLabel jlbPanel;
    private javax.swing.JLabel jlbText;
    private javax.swing.JTextField txtAdminName;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
