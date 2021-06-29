package View;

import java.awt.Font;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Carlos Antonio Ruiz
 */
public class UserAdd extends javax.swing.JFrame {

    /**
     * Creates new form UserAdd
     */
    public UserAdd() {
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

        PanelUserAdd = new javax.swing.JPanel();
        jblErrorName = new javax.swing.JLabel();
        jblErrorLastName1 = new javax.swing.JLabel();
        jblErrorLastName2 = new javax.swing.JLabel();
        txtLastName2 = new javax.swing.JTextField();
        txtLastName1 = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        btnAddUser = new javax.swing.JButton();
        jlbDecorative = new javax.swing.JLabel();
        jlbAdd = new javax.swing.JLabel();
        jlbPanel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AÑADIR USUARIO");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelUserAdd.setBackground(new java.awt.Color(0, 38, 78));
        PanelUserAdd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jblErrorName.setForeground(new java.awt.Color(0, 0, 0));
        PanelUserAdd.add(jblErrorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 290, 20));

        jblErrorLastName1.setForeground(new java.awt.Color(0, 0, 0));
        PanelUserAdd.add(jblErrorLastName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 290, 20));

        jblErrorLastName2.setForeground(new java.awt.Color(0, 0, 0));
        PanelUserAdd.add(jblErrorLastName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 290, 20));

        txtLastName2.setBackground(new java.awt.Color(166, 184, 204));
        txtLastName2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtLastName2.setForeground(new java.awt.Color(0, 0, 0));
        PanelUserAdd.add(txtLastName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 290, 50));

        txtLastName1.setBackground(new java.awt.Color(166, 184, 204));
        txtLastName1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtLastName1.setForeground(new java.awt.Color(0, 0, 0));
        PanelUserAdd.add(txtLastName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 290, 50));

        txtName.setBackground(new java.awt.Color(166, 184, 204));
        txtName.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtName.setForeground(new java.awt.Color(0, 0, 0));
        PanelUserAdd.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 290, 50));

        btnAddUser.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnAddUser.setForeground(new java.awt.Color(213, 159, 0));
        btnAddUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnAddUser.setText("Añadir usuario");
        btnAddUser.setBorderPainted(false);
        btnAddUser.setFocusPainted(false);
        btnAddUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddUser.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnAddUser.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1_Rollover.png"))); // NOI18N
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });
        PanelUserAdd.add(btnAddUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 290, 40));

        jlbDecorative.setForeground(new java.awt.Color(213, 159, 0));
        jlbDecorative.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbDecorative.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Panel1.png"))); // NOI18N
        jlbDecorative.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PanelUserAdd.add(jlbDecorative, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 40, 50));

        jlbAdd.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jlbAdd.setForeground(new java.awt.Color(255, 255, 255));
        jlbAdd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Panel1.png"))); // NOI18N
        jlbAdd.setText("AÑADIR USUARIO");
        jlbAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PanelUserAdd.add(jlbAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 210, 50));

        jlbPanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Panel.png"))); // NOI18N
        PanelUserAdd.add(jlbPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 350, 500));

        getContentPane().add(PanelUserAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        
    }//GEN-LAST:event_btnAddUserActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelUserAdd;
    private javax.swing.JButton btnAddUser;
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
