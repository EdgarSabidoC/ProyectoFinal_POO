package View;

import java.awt.Font;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Carlos Antonio Ruiz
 */
public class BookAdd extends javax.swing.JFrame {

    //Constructor del Frame BookAdd.
    public BookAdd() {
        //Se inician los componentes internos.
        initComponents();
        
        //Se establece la posición del frame al centro de la pantalla.
        setLocationRelativeTo(null);
        
        //Se carga el icono del programa.
        iconFrame();
        
        //Placeholders:
        TextPrompt txtAuthorPlaceholder = new TextPrompt("Nombre del autor", this.txtAutor);
        TextPrompt txtYearPlaceholder = new TextPrompt("Año de publicación", this.txtYear);
        TextPrompt txtTitlerPlaceholder = new TextPrompt("Titulo del libro", this.txtTitle);
        TextPrompt txtEditionPlaceholder = new TextPrompt("Edición", this.txtEdition);
        TextPrompt txtEditorialPlaceholder = new TextPrompt("Editorial", this.txtEditorial);
        TextPrompt txtPagesPlaceholder = new TextPrompt("Número de páginas", this.txtPages);
        TextPrompt txtISBNPlaceholder = new TextPrompt("ISBN", this.txtISBN);
        
        //Color de transparencia de los placeholders:
        txtAuthorPlaceholder.changeAlpha(0.75f);
        txtYearPlaceholder.changeAlpha(0.75f);
        txtTitlerPlaceholder.changeAlpha(0.75f);
        txtEditionPlaceholder.changeAlpha(0.75f);
        txtEditorialPlaceholder.changeAlpha(0.75f);
        txtPagesPlaceholder.changeAlpha(0.75f);
        txtISBNPlaceholder.changeAlpha(0.75f);
        
        //Estilo de letra de los placeholders:
        txtAuthorPlaceholder.changeStyle(Font.PLAIN);
        txtYearPlaceholder.changeStyle(Font.PLAIN);
        txtTitlerPlaceholder.changeStyle(Font.PLAIN);
        txtEditionPlaceholder.changeStyle(Font.PLAIN);
        txtEditorialPlaceholder.changeStyle(Font.PLAIN);
        txtPagesPlaceholder.changeStyle(Font.PLAIN);
        txtISBNPlaceholder.changeStyle(Font.PLAIN);
        
        
        //Errores
        jblAuthorError.setVisible(false);
        jblEditionError.setVisible(false);
        jblEditorialError.setVisible(false);
        jblISBNError.setVisible(false);
        jblPagesError.setVisible(false);
        jblTitleError.setVisible(false);
        jblYearError.setVisible(false);
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

        PanelBookAdd = new javax.swing.JPanel();
        jblISBNError = new javax.swing.JLabel();
        jblPagesError = new javax.swing.JLabel();
        jblEditorialError = new javax.swing.JLabel();
        jblEditionError = new javax.swing.JLabel();
        jblTitleError = new javax.swing.JLabel();
        jblYearError = new javax.swing.JLabel();
        jblAuthorError = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        txtYear = new javax.swing.JTextField();
        txtAutor = new javax.swing.JTextField();
        txtISBN = new javax.swing.JTextField();
        txtEditorial = new javax.swing.JTextField();
        txtEdition = new javax.swing.JTextField();
        txtPages = new javax.swing.JTextField();
        btnAddBook = new javax.swing.JButton();
        jlbDecorative = new javax.swing.JLabel();
        jlbAdd = new javax.swing.JLabel();
        jlbPanel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AÑADIR LIBRO");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelBookAdd.setBackground(new java.awt.Color(0, 38, 78));
        PanelBookAdd.setMinimumSize(new java.awt.Dimension(370, 630));
        PanelBookAdd.setPreferredSize(new java.awt.Dimension(370, 630));
        PanelBookAdd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jblISBNError.setForeground(new java.awt.Color(0, 0, 0));
        PanelBookAdd.add(jblISBNError, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 580, 290, 20));

        jblPagesError.setForeground(new java.awt.Color(0, 0, 0));
        PanelBookAdd.add(jblPagesError, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, 290, 20));

        jblEditorialError.setForeground(new java.awt.Color(0, 0, 0));
        PanelBookAdd.add(jblEditorialError, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 290, 20));

        jblEditionError.setForeground(new java.awt.Color(0, 0, 0));
        PanelBookAdd.add(jblEditionError, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 290, 20));

        jblTitleError.setForeground(new java.awt.Color(0, 0, 0));
        PanelBookAdd.add(jblTitleError, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 290, 20));

        jblYearError.setForeground(new java.awt.Color(0, 0, 0));
        PanelBookAdd.add(jblYearError, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 290, 20));

        jblAuthorError.setForeground(new java.awt.Color(0, 0, 0));
        PanelBookAdd.add(jblAuthorError, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 290, 20));

        txtTitle.setBackground(new java.awt.Color(166, 184, 204));
        txtTitle.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtTitle.setForeground(new java.awt.Color(0, 0, 0));
        PanelBookAdd.add(txtTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 290, 50));

        txtYear.setBackground(new java.awt.Color(166, 184, 204));
        txtYear.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtYear.setForeground(new java.awt.Color(0, 0, 0));
        PanelBookAdd.add(txtYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 290, 50));

        txtAutor.setBackground(new java.awt.Color(166, 184, 204));
        txtAutor.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtAutor.setForeground(new java.awt.Color(0, 0, 0));
        PanelBookAdd.add(txtAutor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 290, 50));

        txtISBN.setBackground(new java.awt.Color(166, 184, 204));
        txtISBN.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtISBN.setForeground(new java.awt.Color(0, 0, 0));
        PanelBookAdd.add(txtISBN, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, 290, 50));

        txtEditorial.setBackground(new java.awt.Color(166, 184, 204));
        txtEditorial.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtEditorial.setForeground(new java.awt.Color(0, 0, 0));
        PanelBookAdd.add(txtEditorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 290, 50));

        txtEdition.setBackground(new java.awt.Color(166, 184, 204));
        txtEdition.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtEdition.setForeground(new java.awt.Color(0, 0, 0));
        PanelBookAdd.add(txtEdition, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 290, 50));

        txtPages.setBackground(new java.awt.Color(166, 184, 204));
        txtPages.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtPages.setForeground(new java.awt.Color(0, 0, 0));
        PanelBookAdd.add(txtPages, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 290, 50));

        btnAddBook.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnAddBook.setForeground(new java.awt.Color(213, 159, 0));
        btnAddBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnAddBook.setText("Añadir libro");
        btnAddBook.setBorderPainted(false);
        btnAddBook.setFocusPainted(false);
        btnAddBook.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddBook.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnAddBook.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1_Rollover.png"))); // NOI18N
        btnAddBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBookActionPerformed(evt);
            }
        });
        PanelBookAdd.add(btnAddBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, 290, 40));

        jlbDecorative.setForeground(new java.awt.Color(213, 159, 0));
        jlbDecorative.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbDecorative.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Panel1.png"))); // NOI18N
        jlbDecorative.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PanelBookAdd.add(jlbDecorative, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 40, 50));

        jlbAdd.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jlbAdd.setForeground(new java.awt.Color(255, 255, 255));
        jlbAdd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Panel1.png"))); // NOI18N
        jlbAdd.setText("AÑADIR LIBRO");
        jlbAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PanelBookAdd.add(jlbAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 210, 50));

        jlbPanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Panel3.png"))); // NOI18N
        PanelBookAdd.add(jlbPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 350, -1));

        getContentPane().add(PanelBookAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBookActionPerformed

    }//GEN-LAST:event_btnAddBookActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelBookAdd;
    private javax.swing.JButton btnAddBook;
    private javax.swing.JLabel jblAuthorError;
    private javax.swing.JLabel jblEditionError;
    private javax.swing.JLabel jblEditorialError;
    private javax.swing.JLabel jblISBNError;
    private javax.swing.JLabel jblPagesError;
    private javax.swing.JLabel jblTitleError;
    private javax.swing.JLabel jblYearError;
    private javax.swing.JLabel jlbAdd;
    private javax.swing.JLabel jlbDecorative;
    private javax.swing.JLabel jlbPanel;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtEdition;
    private javax.swing.JTextField txtEditorial;
    private javax.swing.JTextField txtISBN;
    private javax.swing.JTextField txtPages;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables
}
