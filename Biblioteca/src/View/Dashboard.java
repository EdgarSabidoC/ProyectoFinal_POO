package View;

import App.PruebaBiblioteca;
import java.net.URL;
import javax.swing.ImageIcon;
import Model.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Edgar Sabido Cortés, Carlos Antonio Ruíz Domínguez, Luis Alfredo Cota Armenta.
 *
 */
public final class Dashboard extends javax.swing.JFrame {
    public TBList bookTable = new TBList();
    public ArrayList<Book> bookArray;
    public ArrayList<User> userArray;

    public Dashboard() {
        //Se inician los componentes internos.
        initComponents();

        //Se establece la posición del frame al centro de la pantalla.
        setLocationRelativeTo(null);

        //Se carga el icono del programa.
        iconFrame();

        //Se carga en el menu de inicio el nombre del usuario.
        HomeName();

        //Se ocultan opciones del menu según el tipo de usuario que haya ingresado.
        if (PruebaBiblioteca.user instanceof Member) {
            //Se elimina la capacidad de ingresar a pestañas solo para adminsitradores y superadmin.
            PanelMenu.remove(btnSearchUsers);
            PanelMenu.remove(btnManageUsers);
            PanelMenu.remove(btnManageBooks);

            PanelFormMenu.remove(btnBookReturn);
        } else if (PruebaBiblioteca.user instanceof Admin && !(PruebaBiblioteca.user instanceof SuperAdmin)) {
            //Se elimina la capacidad de ingresar a pestañas solo para usuarios y superadmin.
            PanelFormMenu.remove(btnBookIssue);
            PanelFormMenu.remove(btnUserBook);

            UserManage.remove(btnAdminAdd);

            //Se modifica el texto de los menus.
            jblUserID.setText("ID DEL ADMINISTRADOR");
        } else {
            //Se elimina la capacidad de ingresar a pestañas solo para usuarios.
            PanelFormMenu.remove(btnBookIssue);
            PanelFormMenu.remove(btnUserBook);

            //Se modifica el texto de los menus.
            jblUserID.setText("ID DEL ADMINISTRADOR");
        }

        //Placeholders:
        TextPrompt txtSearchBookPlaceholder = new TextPrompt("Buscar Libro", this.txtBookSearch);
        TextPrompt txtUserSearchPlaceholder = new TextPrompt("Buscar usuario", this.txtUserSearch);

        //Color de transparencia de los placeholders:
        txtSearchBookPlaceholder.changeAlpha(0.75f);
        txtUserSearchPlaceholder.changeAlpha(0.75f);

        //Estilo de letra de los placeholders:
        txtSearchBookPlaceholder.changeStyle(Font.PLAIN);
        txtUserSearchPlaceholder.changeStyle(Font.PLAIN);

        //Errores
        jblRelastname1Error.setVisible(false);
        jblRelastname2Error.setVisible(false);
        jblRenameError1.setVisible(false);
        jblSearchError.setVisible(false);
        jblUserSearchError.setVisible(false);
    }

    //Metodo que carga una imagen y la establece como el icono del programa.
    public void iconFrame() {
        URL url = getClass().getResource("/Images/iconFrame.png");
        ImageIcon iconFrame = new ImageIcon(url);
        setIconImage(iconFrame.getImage());
    }

    public void initTableBookReturn(JTable table, ArrayList<Book> BookReturn) {
        bookArray = BookReturn;
        bookTable.initTableBookReturn(table, BookReturn);
        ButtonColumn buttonColumn = new ButtonColumn(table, returnbook, 9);
        buttonColumn.setMnemonic(KeyEvent.VK_D);
    }

    public void initTableBookList(JTable table) {
        bookTable.initTableBookList(table, PruebaBiblioteca.biblioteca.getBooksList());
    }

    public void initTableBookManage(JTable table) {
        bookTable.initTableBookManage(table, PruebaBiblioteca.biblioteca.getBooksList());
        ButtonColumn buttonColumn = new ButtonColumn(table, bookremove, 10);
        buttonColumn.setMnemonic(KeyEvent.VK_D);
    }

    public void initTableBookSearch(JTable table, ArrayList<Book> BookSearh) {
        bookTable.initTableBookSearch(table, BookSearh);
    }

    public void initTableUserBook(JTable table, ArrayList<Book> UserBook) {
        bookTable.initTableBookIssued(table, UserBook);
    }

    public void initTableBookIssue(JTable table, ArrayList<Book> BookIssue) {
        bookArray = BookIssue;
        bookTable.initTableBookIssue(table, BookIssue);
        ButtonColumn buttonColumn = new ButtonColumn(table, bookissue, 8);
        buttonColumn.setMnemonic(KeyEvent.VK_D);
    }

    public void initTableUserSearch(JTable table, ArrayList<User> UserSearch) {
        bookTable.initTableUserSearch(table, UserSearch);
    }

    public void initTableUserManage(JTable table, ArrayList<User> UserManage) {
        userArray = UserManage;
        if (PruebaBiblioteca.user instanceof Admin && !(PruebaBiblioteca.user instanceof SuperAdmin)) {
            bookTable.initTableUserManage(table, UserManage);
            ButtonColumn buttonColumn1 = new ButtonColumn(table, userremove, 5);
            buttonColumn1.setMnemonic(KeyEvent.VK_D);

        } else {
            bookTable.initTableUserManageRoot(table, UserManage);
            ButtonColumn buttonColumn1 = new ButtonColumn(table, userremoveroot, 5);
            buttonColumn1.setMnemonic(KeyEvent.VK_D);

            ButtonColumn buttonColumn2 = new ButtonColumn(table, userpassword, 6);
            buttonColumn2.setMnemonic(KeyEvent.VK_D);
        }
    }

    //Metodo que carga en el menu de inicio el nombre del usuario.
    public void HomeName() {
        jblWelcomeUser.setText(PruebaBiblioteca.user.getName().toUpperCase());
    }

    //Metodo que limpia el contenido del panel del contenido previamente cargado
    private void cleanPanel() {
        PanelForm.removeAll();
        PanelForm.repaint();
        PanelForm.revalidate();
    }

    //Metodo que imprime en el panel el contenido agregado.
    private void printPanel() {
        PanelForm.repaint();
        PanelForm.revalidate();
    }

    private ArrayList<User> userSearchListTable() {
        ArrayList<User> UserList = new ArrayList<>();
        if (PruebaBiblioteca.user instanceof Admin && !(PruebaBiblioteca.user instanceof SuperAdmin)) {
            for (int i = 0; i < PruebaBiblioteca.biblioteca.getNumberOfMembers(); i++) {
                UserList.add(PruebaBiblioteca.biblioteca.getMembersList().get(i));
            }
        } else {
            for (int i = 0; i < PruebaBiblioteca.biblioteca.getNumberOfMembers(); i++) {
                UserList.add(PruebaBiblioteca.biblioteca.getMembersList().get(i));
            }
            for (int i = 1; i < PruebaBiblioteca.biblioteca.getNumberOfAdmins(); i++) {
                UserList.add(PruebaBiblioteca.biblioteca.getAdminsList().get(i));
            }
        }
        return UserList;
    }

    Action userremoveroot = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable table = (JTable) e.getSource();
            int modelRow = Integer.valueOf(e.getActionCommand());

            SuperAdmin admin = (SuperAdmin) PruebaBiblioteca.user;

            User user = null;
            User usertemp = userArray.get(modelRow);

            if (usertemp instanceof Member) {
                for (int i = 0; i < PruebaBiblioteca.biblioteca.getMembersList().size(); i++) {
                    if (usertemp.getUserID().compareID(PruebaBiblioteca.biblioteca.getMembersList().get(i).getUserID().getCharCode())) {
                        user = PruebaBiblioteca.biblioteca.getMembersList().get(i);
                    }
                }

                if (((Member) user).getBookList().isEmpty()) {
                    admin.deleteUserInList(PruebaBiblioteca.biblioteca.getMembersList(), user);
                    userArray.remove(modelRow);
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede borrar el usuario ya que aún tiene libros prestados");
                    return;
                }

            } else if (usertemp instanceof Admin) {
                for (int i = 0; i < PruebaBiblioteca.biblioteca.getAdminsList().size(); i++) {
                    if (usertemp.getUserID().compareID(PruebaBiblioteca.biblioteca.getAdminsList().get(i).getUserID().getCharCode())) {
                        user = PruebaBiblioteca.biblioteca.getAdminsList().get(i);
                    }
                }

                admin.deleteUserInList(PruebaBiblioteca.biblioteca.getAdminsList(), user);
                userArray.remove(modelRow);
            }

            ((DefaultTableModel) table.getModel()).removeRow(modelRow);
            PruebaBiblioteca.biblioteca.updateInfoInFiles();
        }

    };

    Action userremove = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable table = (JTable) e.getSource();
            int modelRow = Integer.valueOf(e.getActionCommand());

            Admin admin = (Admin) PruebaBiblioteca.user;

            Member user = null;
            User usertemp = userArray.get(modelRow);

            for (int i = 0; i < PruebaBiblioteca.biblioteca.getMembersList().size(); i++) {
                if (usertemp.getUserID().compareID(PruebaBiblioteca.biblioteca.getMembersList().get(i).getUserID().getCharCode())) {
                    user = (Member) PruebaBiblioteca.biblioteca.getMembersList().get(i);
                }
            }

            if (((Member) user).getBookList().isEmpty()) {
                admin.deleteMemberInList(PruebaBiblioteca.biblioteca.getMembersList(), user);
                userArray.remove(modelRow);
            } else {
                JOptionPane.showMessageDialog(null, "No se puede borrar el usuario ya que aún tiene libros prestados");
                return;
            }

            ((DefaultTableModel) table.getModel()).removeRow(modelRow);
            PruebaBiblioteca.biblioteca.updateInfoInFiles();
        }

    };

    Action userpassword = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable table = (JTable) e.getSource();
            int modelRow = Integer.valueOf(e.getActionCommand());
            User usertemp = userArray.get(modelRow);
            JOptionPane.showMessageDialog(null, String.valueOf(usertemp.getUserPassword().getPasswordCode()));

        }

    };

    Action bookremove = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable table = (JTable) e.getSource();
            int modelRow = Integer.valueOf(e.getActionCommand());

            Admin admin = (Admin) PruebaBiblioteca.user;

            Book book = PruebaBiblioteca.biblioteca.getBooksList().get(modelRow);

            if (!book.isBorrowed()) {
                admin.deleteBookInList(PruebaBiblioteca.biblioteca.getBooksList(), book);
            } else {
                JOptionPane.showMessageDialog(null, "No se puede borrar el libro ya que esta prestado");
                return;
            }

            ((DefaultTableModel) table.getModel()).removeRow(modelRow);
            PruebaBiblioteca.biblioteca.updateInfoInFiles();
        }
    };
    //Accion que permite devolver un libro.
    Action returnbook = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable table = (JTable) e.getSource();
            int modelRow = Integer.valueOf(e.getActionCommand());
            ((DefaultTableModel) table.getModel()).removeRow(modelRow);

            Admin AdminReturn = (Admin) PruebaBiblioteca.user;

            Book book = null;
            for (int i = 0; i < PruebaBiblioteca.biblioteca.getBooksList().size(); i++) {
                if (bookArray.get(modelRow).getID().compareID(PruebaBiblioteca.biblioteca.getBooksList().get(i).getID().getCharCode())) {
                    book = PruebaBiblioteca.biblioteca.getBooksList().get(i);
                }
            }

            //Encontrar miembro
            Member membertemp = null;
            Member member = null;
            for (int i = 0; i < PruebaBiblioteca.biblioteca.getMembersList().size(); i++) {
                membertemp = (Member) PruebaBiblioteca.biblioteca.getMembersList().get(i);
                for (int j = 0; j < membertemp.getBookList().size(); j++) {
                    if (book.getID().compareID(membertemp.getBookList().get(j).getID().getCharCode())) {
                        member = (Member) PruebaBiblioteca.biblioteca.getMembersList().get(i);
                    }
                }
            }

            AdminReturn.returnABook(PruebaBiblioteca.biblioteca.getMembersList(), PruebaBiblioteca.biblioteca.getBooksList(), member, book);

            bookArray.remove(modelRow);
            PruebaBiblioteca.biblioteca.updateInfoInFiles();
        }

    };

    Action bookissue = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable table = (JTable) e.getSource();
            int modelRow = Integer.valueOf(e.getActionCommand());
            ((DefaultTableModel) table.getModel()).removeRow(modelRow);

            Member UserIssue = (Member) PruebaBiblioteca.user;
            UserIssue.bookABook(PruebaBiblioteca.biblioteca.getBooksList(), bookArray.get(modelRow));
            bookArray.remove(modelRow);

            PruebaBiblioteca.biblioteca.updateInfoInFiles();
        }

    };

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelBase = new javax.swing.JPanel();
        PanelMenu = new javax.swing.JPanel();
        btnLogOut = new javax.swing.JButton();
        btnSearchUsers = new javax.swing.JButton();
        btnManageUsers = new javax.swing.JButton();
        btnManageBooks = new javax.swing.JButton();
        PanelFormMenu = new javax.swing.JPanel();
        btnUserBook = new javax.swing.JButton();
        btnBookReturn = new javax.swing.JButton();
        btnBookIssue = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnBookSearch = new javax.swing.JButton();
        btnBookList = new javax.swing.JButton();
        btnUser = new javax.swing.JButton();
        btnConfig = new javax.swing.JButton();
        PanelForm = new javax.swing.JPanel();
        Home = new javax.swing.JPanel();
        jblWelcomeUser = new javax.swing.JLabel();
        jblWelcome = new javax.swing.JLabel();
        jblHomeBackground = new javax.swing.JLabel();
        User = new javax.swing.JPanel();
        txtName = new javax.swing.JTextField();
        jblUser = new javax.swing.JLabel();
        cbxShowPassword = new javax.swing.JCheckBox();
        txtLastNames = new javax.swing.JTextField();
        jblLastNames = new javax.swing.JLabel();
        txtUserID = new javax.swing.JTextField();
        jblUserID = new javax.swing.JLabel();
        jblPassword = new javax.swing.JLabel();
        txtLastLogin = new javax.swing.JTextField();
        jblLastLogin = new javax.swing.JLabel();
        jblName = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        BookList = new javax.swing.JPanel();
        jblBookList = new javax.swing.JLabel();
        scrollBookList = new javax.swing.JScrollPane();
        tableBookList = new javax.swing.JTable();
        BookSearch = new javax.swing.JPanel();
        btnSearch = new javax.swing.JToggleButton();
        txtBookSearch = new javax.swing.JTextField();
        scrollBookSearch = new javax.swing.JScrollPane();
        tableBookSearch = new javax.swing.JTable();
        jblBookSearch = new javax.swing.JLabel();
        jblSearchError = new javax.swing.JLabel();
        BookIssue = new javax.swing.JPanel();
        jblBookIssue = new javax.swing.JLabel();
        scrollBookIssue = new javax.swing.JScrollPane();
        tableBookIssue = new javax.swing.JTable();
        BookReturn = new javax.swing.JPanel();
        jblBookReturn = new javax.swing.JLabel();
        scrollBookReturn = new javax.swing.JScrollPane();
        tableBookReturn = new javax.swing.JTable();
        UserBook = new javax.swing.JPanel();
        jblUserBook = new javax.swing.JLabel();
        scrollUserBook = new javax.swing.JScrollPane();
        tableUserBook = new javax.swing.JTable();
        Configuration = new javax.swing.JPanel();
        jblRelastname2Error = new javax.swing.JLabel();
        jblRenameError1 = new javax.swing.JLabel();
        jblRelastname1Error = new javax.swing.JLabel();
        btnRename = new javax.swing.JToggleButton();
        jblLastName1 = new javax.swing.JLabel();
        txtLastName1 = new javax.swing.JTextField();
        jblUserConfig = new javax.swing.JLabel();
        txtNameChange = new javax.swing.JTextField();
        jblNameChange = new javax.swing.JLabel();
        txtLastName2 = new javax.swing.JTextField();
        jblLastName2 = new javax.swing.JLabel();
        UserSearch = new javax.swing.JPanel();
        jblUserSearch = new javax.swing.JLabel();
        scrollUserSearch = new javax.swing.JScrollPane();
        tableUserSearch = new javax.swing.JTable();
        txtUserSearch = new javax.swing.JTextField();
        btnUserSearch = new javax.swing.JToggleButton();
        jblUserSearchError = new javax.swing.JLabel();
        UserManage = new javax.swing.JPanel();
        btnAdminAdd = new javax.swing.JToggleButton();
        btnUserAdd = new javax.swing.JToggleButton();
        scrollUserManage = new javax.swing.JScrollPane();
        tableUserManage = new javax.swing.JTable();
        jblUserManage = new javax.swing.JLabel();
        BookManage = new javax.swing.JPanel();
        scrollBookSearch1 = new javax.swing.JScrollPane();
        tableBookManage = new javax.swing.JTable();
        btnBookAdd = new javax.swing.JToggleButton();
        jblBookManage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BIBLIOTECA K'ASTAKÁNO'OB");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelBase.setBackground(new java.awt.Color(0, 38, 78));
        PanelBase.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelMenu.setBackground(new java.awt.Color(213, 159, 0));
        PanelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogOut.setForeground(new java.awt.Color(255, 255, 255));
        btnLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn2.png"))); // NOI18N
        btnLogOut.setText("CERRAR SESIÓN");
        btnLogOut.setBorderPainted(false);
        btnLogOut.setFocusPainted(false);
        btnLogOut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLogOut.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn2.png"))); // NOI18N
        btnLogOut.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn2_Rollover.png"))); // NOI18N
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        PanelMenu.add(btnLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 140, 40));

        btnSearchUsers.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn2.png"))); // NOI18N
        btnSearchUsers.setText("BUSCAR USUARIOS");
        btnSearchUsers.setBorderPainted(false);
        btnSearchUsers.setFocusPainted(false);
        btnSearchUsers.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSearchUsers.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn2.png"))); // NOI18N
        btnSearchUsers.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn2_Rollover.png"))); // NOI18N
        btnSearchUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchUsersActionPerformed(evt);
            }
        });
        PanelMenu.add(btnSearchUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 180, 40));

        btnManageUsers.setForeground(new java.awt.Color(255, 255, 255));
        btnManageUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn2.png"))); // NOI18N
        btnManageUsers.setText("ADMINISTRAR USUARIOS");
        btnManageUsers.setBorderPainted(false);
        btnManageUsers.setFocusPainted(false);
        btnManageUsers.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnManageUsers.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn2.png"))); // NOI18N
        btnManageUsers.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn2_Rollover.png"))); // NOI18N
        btnManageUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageUsersActionPerformed(evt);
            }
        });
        PanelMenu.add(btnManageUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 210, 40));

        btnManageBooks.setForeground(new java.awt.Color(255, 255, 255));
        btnManageBooks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn2.png"))); // NOI18N
        btnManageBooks.setText("ADMINISTRAR LIBROS");
        btnManageBooks.setBorderPainted(false);
        btnManageBooks.setFocusPainted(false);
        btnManageBooks.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnManageBooks.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn2.png"))); // NOI18N
        btnManageBooks.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn2_Rollover.png"))); // NOI18N
        btnManageBooks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageBooksActionPerformed(evt);
            }
        });
        PanelMenu.add(btnManageBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 180, 40));

        PanelBase.add(PanelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 40));

        PanelFormMenu.setBackground(new java.awt.Color(0, 38, 78));
        PanelFormMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnUserBook.setForeground(new java.awt.Color(255, 255, 255));
        btnUserBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnUserBook.setText("PRESTADOS");
        btnUserBook.setBorderPainted(false);
        btnUserBook.setFocusPainted(false);
        btnUserBook.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUserBook.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnUserBook.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1_Rollover.png"))); // NOI18N
        btnUserBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserBookActionPerformed(evt);
            }
        });
        PanelFormMenu.add(btnUserBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 140, 40));

        btnBookReturn.setForeground(new java.awt.Color(255, 255, 255));
        btnBookReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnBookReturn.setText("DEVOLVER LIBRO");
        btnBookReturn.setBorderPainted(false);
        btnBookReturn.setFocusPainted(false);
        btnBookReturn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBookReturn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnBookReturn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1_Rollover.png"))); // NOI18N
        btnBookReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookReturnActionPerformed(evt);
            }
        });
        PanelFormMenu.add(btnBookReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 140, 40));

        btnBookIssue.setForeground(new java.awt.Color(255, 255, 255));
        btnBookIssue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnBookIssue.setText("PEDIR LIBRO");
        btnBookIssue.setBorderPainted(false);
        btnBookIssue.setFocusPainted(false);
        btnBookIssue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBookIssue.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnBookIssue.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1_Rollover.png"))); // NOI18N
        btnBookIssue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookIssueActionPerformed(evt);
            }
        });
        PanelFormMenu.add(btnBookIssue, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 140, 40));

        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnHome.setText("INICIO");
        btnHome.setBorderPainted(false);
        btnHome.setFocusPainted(false);
        btnHome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHome.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnHome.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1_Rollover.png"))); // NOI18N
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        PanelFormMenu.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 140, 40));

        btnBookSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnBookSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnBookSearch.setText("BUSCAR LIBROS");
        btnBookSearch.setBorderPainted(false);
        btnBookSearch.setFocusPainted(false);
        btnBookSearch.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBookSearch.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnBookSearch.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1_Rollover.png"))); // NOI18N
        btnBookSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookSearchActionPerformed(evt);
            }
        });
        PanelFormMenu.add(btnBookSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 140, 40));

        btnBookList.setForeground(new java.awt.Color(255, 255, 255));
        btnBookList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnBookList.setText("LISTA DE LIBROS");
        btnBookList.setBorderPainted(false);
        btnBookList.setFocusPainted(false);
        btnBookList.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBookList.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnBookList.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1_Rollover.png"))); // NOI18N
        btnBookList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookListActionPerformed(evt);
            }
        });
        PanelFormMenu.add(btnBookList, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 140, 40));

        btnUser.setForeground(new java.awt.Color(255, 255, 255));
        btnUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnUser.setText("USUARIO");
        btnUser.setBorderPainted(false);
        btnUser.setFocusPainted(false);
        btnUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUser.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnUser.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1_Rollover.png"))); // NOI18N
        btnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserActionPerformed(evt);
            }
        });
        PanelFormMenu.add(btnUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 140, 40));

        btnConfig.setForeground(new java.awt.Color(255, 255, 255));
        btnConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn2.png"))); // NOI18N
        btnConfig.setText("CONFIGURACIÓN");
        btnConfig.setBorderPainted(false);
        btnConfig.setFocusPainted(false);
        btnConfig.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConfig.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn2.png"))); // NOI18N
        btnConfig.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn2_Rollover.png"))); // NOI18N
        btnConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigActionPerformed(evt);
            }
        });
        PanelFormMenu.add(btnConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 140, 40));

        PanelBase.add(PanelFormMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 140, 500));

        PanelForm.setBackground(new java.awt.Color(255, 255, 255));
        PanelForm.setLayout(new java.awt.CardLayout());

        Home.setBackground(new java.awt.Color(255, 255, 255));
        Home.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jblWelcomeUser.setBackground(new java.awt.Color(213, 159, 0));
        jblWelcomeUser.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jblWelcomeUser.setForeground(new java.awt.Color(255, 255, 255));
        jblWelcomeUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblWelcomeUser.setText("NOMBRE USUARIO");
        jblWelcomeUser.setOpaque(true);
        Home.add(jblWelcomeUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 590, 40));

        jblWelcome.setBackground(new java.awt.Color(0, 38, 78));
        jblWelcome.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jblWelcome.setForeground(new java.awt.Color(255, 255, 255));
        jblWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblWelcome.setText("BIENVENIDO");
        jblWelcome.setOpaque(true);
        Home.add(jblWelcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 590, 60));

        jblHomeBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblHomeBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/imgBook.png"))); // NOI18N
        jblHomeBackground.setDoubleBuffered(true);
        Home.add(jblHomeBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 500));

        PanelForm.add(Home, "card2");

        User.setBackground(new java.awt.Color(255, 255, 255));
        User.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtName.setEditable(false);
        txtName.setBackground(new java.awt.Color(166, 184, 204));
        txtName.setForeground(new java.awt.Color(0, 0, 0));
        User.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 510, 30));

        jblUser.setBackground(new java.awt.Color(0, 38, 78));
        jblUser.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jblUser.setForeground(new java.awt.Color(255, 255, 255));
        jblUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblUser.setText("INFORMACIÓN DEL USUARIO");
        jblUser.setOpaque(true);
        User.add(jblUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 270, 30));

        cbxShowPassword.setBackground(new java.awt.Color(255, 255, 255));
        cbxShowPassword.setForeground(new java.awt.Color(0, 38, 78));
        cbxShowPassword.setText("Mostrar contraseña");
        cbxShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxShowPasswordActionPerformed(evt);
            }
        });
        User.add(cbxShowPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        txtLastNames.setEditable(false);
        txtLastNames.setBackground(new java.awt.Color(166, 184, 204));
        txtLastNames.setForeground(new java.awt.Color(0, 0, 0));
        User.add(txtLastNames, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 510, 30));

        jblLastNames.setForeground(new java.awt.Color(0, 0, 0));
        jblLastNames.setText("APELLIDOS:");
        User.add(jblLastNames, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 510, 30));

        txtUserID.setEditable(false);
        txtUserID.setBackground(new java.awt.Color(166, 184, 204));
        txtUserID.setForeground(new java.awt.Color(0, 0, 0));
        User.add(txtUserID, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 510, 30));

        jblUserID.setForeground(new java.awt.Color(0, 0, 0));
        jblUserID.setText("ID DEL USUARIO:");
        User.add(jblUserID, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 510, 30));

        jblPassword.setForeground(new java.awt.Color(0, 0, 0));
        jblPassword.setText("CONTRASEÑA");
        User.add(jblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 510, 30));

        txtLastLogin.setEditable(false);
        txtLastLogin.setBackground(new java.awt.Color(166, 184, 204));
        txtLastLogin.setForeground(new java.awt.Color(0, 0, 0));
        User.add(txtLastLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 510, 30));

        jblLastLogin.setForeground(new java.awt.Color(0, 0, 0));
        jblLastLogin.setText("ULTIMO INICIO DE SESIÓN:");
        User.add(jblLastLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 510, 30));

        jblName.setForeground(new java.awt.Color(0, 0, 0));
        jblName.setText("NOMBRE:");
        User.add(jblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 510, 30));

        txtPassword.setEditable(false);
        txtPassword.setBackground(new java.awt.Color(166, 184, 204));
        txtPassword.setForeground(new java.awt.Color(0, 0, 0));
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        User.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 510, 30));

        PanelForm.add(User, "card3");

        BookList.setBackground(new java.awt.Color(255, 255, 255));
        BookList.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jblBookList.setBackground(new java.awt.Color(0, 38, 78));
        jblBookList.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jblBookList.setForeground(new java.awt.Color(255, 255, 255));
        jblBookList.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblBookList.setText("LISTA DE LIBROS");
        jblBookList.setOpaque(true);
        BookList.add(jblBookList, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 270, 30));

        tableBookList.setBackground(new java.awt.Color(166, 184, 204));
        tableBookList.setForeground(new java.awt.Color(0, 0, 0));
        tableBookList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "AUTOR", "AÑO", "TITULO", "EDICIÓN", "EDITORIAL", "PÁGINAS", "ISBN", "DISPONIBILIDAD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollBookList.setViewportView(tableBookList);

        BookList.add(scrollBookList, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 510, 400));

        PanelForm.add(BookList, "card7");

        BookSearch.setBackground(new java.awt.Color(255, 255, 255));
        BookSearch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnSearch.setText("BUSCAR");
        btnSearch.setBorderPainted(false);
        btnSearch.setFocusPainted(false);
        btnSearch.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSearch.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnSearch.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1_Rollover.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        BookSearch.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 110, 40));

        txtBookSearch.setBackground(new java.awt.Color(166, 184, 204));
        txtBookSearch.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtBookSearch.setForeground(new java.awt.Color(0, 0, 0));
        txtBookSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBookSearchKeyReleased(evt);
            }
        });
        BookSearch.add(txtBookSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 400, 40));

        tableBookSearch.setBackground(new java.awt.Color(166, 184, 204));
        tableBookSearch.setForeground(new java.awt.Color(0, 0, 0));
        tableBookSearch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "AUTOR", "AÑO", "TITULO", "EDICIÓN", "EDITORIAL", "PÁGINAS", "ISBN", "DISPONIBILIDAD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollBookSearch.setViewportView(tableBookSearch);
        if (tableBookSearch.getColumnModel().getColumnCount() > 0) {
            tableBookSearch.getColumnModel().getColumn(8).setResizable(false);
            tableBookSearch.getColumnModel().getColumn(8).setHeaderValue("DISPONIBILIDAD");
        }

        BookSearch.add(scrollBookSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 510, 180));

        jblBookSearch.setBackground(new java.awt.Color(0, 38, 78));
        jblBookSearch.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jblBookSearch.setForeground(new java.awt.Color(255, 255, 255));
        jblBookSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblBookSearch.setText("BUSCAR LIBROS");
        jblBookSearch.setOpaque(true);
        BookSearch.add(jblBookSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 270, 30));

        jblSearchError.setForeground(new java.awt.Color(255, 0, 51));
        BookSearch.add(jblSearchError, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 400, 20));

        PanelForm.add(BookSearch, "card6");

        BookIssue.setBackground(new java.awt.Color(255, 255, 255));
        BookIssue.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jblBookIssue.setBackground(new java.awt.Color(0, 38, 78));
        jblBookIssue.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jblBookIssue.setForeground(new java.awt.Color(255, 255, 255));
        jblBookIssue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblBookIssue.setText("PEDIR UN LIBRO");
        jblBookIssue.setOpaque(true);
        BookIssue.add(jblBookIssue, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 270, 30));

        tableBookIssue.setBackground(new java.awt.Color(166, 184, 204));
        tableBookIssue.setForeground(new java.awt.Color(0, 0, 0));
        tableBookIssue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "AUTOR", "AÑO", "TITULO", "EDICIÓN", "EDITORIAL", "PÁGINAS", "ISBN", "PEDIR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableBookIssue.getTableHeader().setReorderingAllowed(false);
        scrollBookIssue.setViewportView(tableBookIssue);
        if (tableBookIssue.getColumnModel().getColumnCount() > 0) {
            tableBookIssue.getColumnModel().getColumn(8).setResizable(false);
        }

        BookIssue.add(scrollBookIssue, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 510, 400));

        PanelForm.add(BookIssue, "card7");

        BookReturn.setBackground(new java.awt.Color(255, 255, 255));
        BookReturn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jblBookReturn.setBackground(new java.awt.Color(0, 38, 78));
        jblBookReturn.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jblBookReturn.setForeground(new java.awt.Color(255, 255, 255));
        jblBookReturn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblBookReturn.setText("DEVOLVER UN LIBRO");
        jblBookReturn.setOpaque(true);
        BookReturn.add(jblBookReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 270, 30));

        tableBookReturn.setBackground(new java.awt.Color(166, 184, 204));
        tableBookReturn.setForeground(new java.awt.Color(0, 0, 0));
        tableBookReturn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "AUTOR", "AÑO", "TITULO", "EDICIÓN", "EDITORIAL", "PÁGINAS", "ISBN", "DEVOLVER"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableBookReturn.getTableHeader().setReorderingAllowed(false);
        scrollBookReturn.setViewportView(tableBookReturn);

        BookReturn.add(scrollBookReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 510, 400));

        PanelForm.add(BookReturn, "card7");

        UserBook.setBackground(new java.awt.Color(255, 255, 255));
        UserBook.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jblUserBook.setBackground(new java.awt.Color(0, 38, 78));
        jblUserBook.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jblUserBook.setForeground(new java.awt.Color(255, 255, 255));
        jblUserBook.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblUserBook.setText("LIBROS PRESTADOS");
        jblUserBook.setOpaque(true);
        UserBook.add(jblUserBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 270, 30));

        tableUserBook.setBackground(new java.awt.Color(166, 184, 204));
        tableUserBook.setForeground(new java.awt.Color(0, 0, 0));
        tableUserBook.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "AUTOR", "AÑO", "TITULO", "EDICIÓN", "EDITORIAL", "PÁGINAS", "ISBN", "REGRESAR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableUserBook.getTableHeader().setReorderingAllowed(false);
        scrollUserBook.setViewportView(tableUserBook);

        UserBook.add(scrollUserBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 510, 400));

        PanelForm.add(UserBook, "card12");

        Configuration.setBackground(new java.awt.Color(255, 255, 255));
        Configuration.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jblRelastname2Error.setForeground(new java.awt.Color(255, 0, 51));
        Configuration.add(jblRelastname2Error, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 510, 20));

        jblRenameError1.setForeground(new java.awt.Color(255, 0, 51));
        Configuration.add(jblRenameError1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 510, 20));

        jblRelastname1Error.setForeground(new java.awt.Color(255, 0, 51));
        Configuration.add(jblRelastname1Error, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 510, 20));

        btnRename.setForeground(new java.awt.Color(255, 255, 255));
        btnRename.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnRename.setText("CAMBIAR NOMBRE");
        btnRename.setBorderPainted(false);
        btnRename.setFocusPainted(false);
        btnRename.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRename.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnRename.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1_Rollover.png"))); // NOI18N
        btnRename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenameActionPerformed(evt);
            }
        });
        Configuration.add(btnRename, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, 280, 40));

        jblLastName1.setForeground(new java.awt.Color(0, 0, 0));
        jblLastName1.setText("APELLIDO PATERNO:");
        Configuration.add(jblLastName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 510, 30));

        txtLastName1.setBackground(new java.awt.Color(166, 184, 204));
        txtLastName1.setForeground(new java.awt.Color(0, 0, 0));
        txtLastName1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLastName1KeyReleased(evt);
            }
        });
        Configuration.add(txtLastName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 510, 30));

        jblUserConfig.setBackground(new java.awt.Color(0, 38, 78));
        jblUserConfig.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jblUserConfig.setForeground(new java.awt.Color(255, 255, 255));
        jblUserConfig.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblUserConfig.setText("CAMBIAR NOMBRE DEL USUARIO");
        jblUserConfig.setOpaque(true);
        Configuration.add(jblUserConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 340, 30));

        txtNameChange.setBackground(new java.awt.Color(166, 184, 204));
        txtNameChange.setForeground(new java.awt.Color(0, 0, 0));
        txtNameChange.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNameChangeKeyReleased(evt);
            }
        });
        Configuration.add(txtNameChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 510, 30));

        jblNameChange.setForeground(new java.awt.Color(0, 0, 0));
        jblNameChange.setText("NOMBRE:");
        Configuration.add(jblNameChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 510, 30));

        txtLastName2.setBackground(new java.awt.Color(166, 184, 204));
        txtLastName2.setForeground(new java.awt.Color(0, 0, 0));
        txtLastName2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLastName2KeyReleased(evt);
            }
        });
        Configuration.add(txtLastName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 510, 30));

        jblLastName2.setForeground(new java.awt.Color(0, 0, 0));
        jblLastName2.setText("APELLIDO MATERNO:");
        Configuration.add(jblLastName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 510, 30));

        PanelForm.add(Configuration, "card8");

        UserSearch.setBackground(new java.awt.Color(255, 255, 255));
        UserSearch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jblUserSearch.setBackground(new java.awt.Color(0, 38, 78));
        jblUserSearch.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jblUserSearch.setForeground(new java.awt.Color(255, 255, 255));
        jblUserSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblUserSearch.setText("BUSCAR USUARIOS");
        jblUserSearch.setOpaque(true);
        UserSearch.add(jblUserSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 270, 30));

        tableUserSearch.setBackground(new java.awt.Color(166, 184, 204));
        tableUserSearch.setForeground(new java.awt.Color(0, 0, 0));
        tableUserSearch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "TIPO", "ID", "NOMBRE", "APELLIDOS", "ULTIMO ACCESO", "LISTA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollUserSearch.setViewportView(tableUserSearch);
        if (tableUserSearch.getColumnModel().getColumnCount() > 0) {
            tableUserSearch.getColumnModel().getColumn(5).setHeaderValue("LISTA");
        }

        UserSearch.add(scrollUserSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 510, 180));

        txtUserSearch.setBackground(new java.awt.Color(166, 184, 204));
        txtUserSearch.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtUserSearch.setForeground(new java.awt.Color(0, 0, 0));
        txtUserSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUserSearchKeyReleased(evt);
            }
        });
        UserSearch.add(txtUserSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 400, 40));

        btnUserSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnUserSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnUserSearch.setText("BUSCAR");
        btnUserSearch.setBorderPainted(false);
        btnUserSearch.setFocusPainted(false);
        btnUserSearch.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUserSearch.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnUserSearch.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1_Rollover.png"))); // NOI18N
        btnUserSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserSearchActionPerformed(evt);
            }
        });
        UserSearch.add(btnUserSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 110, 40));

        jblUserSearchError.setForeground(new java.awt.Color(255, 0, 51));
        UserSearch.add(jblUserSearchError, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 400, 20));

        PanelForm.add(UserSearch, "card9");

        UserManage.setBackground(new java.awt.Color(255, 255, 255));
        UserManage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAdminAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdminAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnAdminAdd.setText("AÑADIR ADMINISTRADOR");
        btnAdminAdd.setBorderPainted(false);
        btnAdminAdd.setFocusPainted(false);
        btnAdminAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdminAdd.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnAdminAdd.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1_Rollover.png"))); // NOI18N
        btnAdminAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminAddActionPerformed(evt);
            }
        });
        UserManage.add(btnAdminAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 240, 40));

        btnUserAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnUserAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnUserAdd.setText("AÑADIR USUARIO");
        btnUserAdd.setBorderPainted(false);
        btnUserAdd.setFocusPainted(false);
        btnUserAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUserAdd.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnUserAdd.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1_Rollover.png"))); // NOI18N
        btnUserAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserAddActionPerformed(evt);
            }
        });
        UserManage.add(btnUserAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 240, 40));

        tableUserManage.setBackground(new java.awt.Color(166, 184, 204));
        tableUserManage.setForeground(new java.awt.Color(0, 0, 0));
        tableUserManage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "TIPO", "ID", "NOMBRE", "APELLIDOS", "ULTIMO ACCESO", "ELIMINAR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableUserManage.getTableHeader().setReorderingAllowed(false);
        scrollUserManage.setViewportView(tableUserManage);
        if (tableUserManage.getColumnModel().getColumnCount() > 0) {
            tableUserManage.getColumnModel().getColumn(5).setResizable(false);
        }

        UserManage.add(scrollUserManage, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 510, 290));

        jblUserManage.setBackground(new java.awt.Color(0, 38, 78));
        jblUserManage.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jblUserManage.setForeground(new java.awt.Color(255, 255, 255));
        jblUserManage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblUserManage.setText("ADMINISTRAR USUARIOS");
        jblUserManage.setOpaque(true);
        UserManage.add(jblUserManage, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 300, 30));

        PanelForm.add(UserManage, "card10");

        BookManage.setBackground(new java.awt.Color(255, 255, 255));
        BookManage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableBookManage.setBackground(new java.awt.Color(166, 184, 204));
        tableBookManage.setForeground(new java.awt.Color(0, 0, 0));
        tableBookManage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "AUTOR", "AÑO", "TITULO", "EDICIÓN", "EDITORIAL", "PÁGINAS", "ISBN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollBookSearch1.setViewportView(tableBookManage);

        BookManage.add(scrollBookSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 510, 290));

        btnBookAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnBookAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnBookAdd.setText("AÑADIR LIBRO");
        btnBookAdd.setBorderPainted(false);
        btnBookAdd.setFocusPainted(false);
        btnBookAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBookAdd.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1.png"))); // NOI18N
        btnBookAdd.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn1_Rollover.png"))); // NOI18N
        btnBookAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookAddActionPerformed(evt);
            }
        });
        BookManage.add(btnBookAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 240, 40));

        jblBookManage.setBackground(new java.awt.Color(0, 38, 78));
        jblBookManage.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jblBookManage.setForeground(new java.awt.Color(255, 255, 255));
        jblBookManage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblBookManage.setText("ADMINISTRAR LIBROS");
        jblBookManage.setOpaque(true);
        BookManage.add(jblBookManage, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 270, 30));

        PanelForm.add(BookManage, "card11");

        PanelBase.add(PanelForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 580, 500));

        getContentPane().add(PanelBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed

        //Se carga en el menu de inicio el nombre del usuario.
        HomeName();

        //Se remueven la información cargada del panel.
        cleanPanel();

        //Se imprime en el panel el menu Home.
        PanelForm.add(Home);
        printPanel();

    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserActionPerformed
        //Se carga la información del usuario
        txtName.setText(PruebaBiblioteca.user.getName());
        txtLastNames.setText(PruebaBiblioteca.user.getFirstLastName() + " " + PruebaBiblioteca.user.getSecondLastName());
        txtUserID.setText(String.valueOf(PruebaBiblioteca.user.getUserID().getCharCode()));
        txtLastLogin.setText("Fecha: " + PruebaBiblioteca.user.getLastLogin().getDateS() + " Hora: " + PruebaBiblioteca.user.getLastLogin().getTimeS());
        txtPassword.setText(String.valueOf(PruebaBiblioteca.user.getUserPassword().getPasswordCode()));
        //Se remueven la información cargada del panel.
        cleanPanel();

        //Se imprime en el panel el menu User.
        PanelForm.add(User);
        printPanel();
    }//GEN-LAST:event_btnUserActionPerformed

    private void btnBookListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookListActionPerformed
        //Se cargan los datos de la tabla.
        initTableBookList(tableBookList);

        //Se remueven la información cargada del panel.
        cleanPanel();

        //Se imprime en el panel el menu BookList.
        PanelForm.add(BookList);
        printPanel();
    }//GEN-LAST:event_btnBookListActionPerformed

    private void btnBookIssueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookIssueActionPerformed
        ArrayList<Book> BookIssueUser = new ArrayList<>();
        for (int i = 0; i < PruebaBiblioteca.biblioteca.getBooksList().size(); i++) {
            BookIssueUser.add(PruebaBiblioteca.biblioteca.getBooksList().get(i));
        }

        for (int i = (BookIssueUser.size() - 1); i >= 0; i--) {
            if (BookIssueUser.get(i).isBorrowed()) {
                BookIssueUser.remove(i);
            }
        }
        initTableBookIssue(tableBookIssue, BookIssueUser);

        //Se remueven la información cargada del panel.
        cleanPanel();

        //Se imprime en el panel el menu BookIssue.
        PanelForm.add(BookIssue);
        printPanel();
    }//GEN-LAST:event_btnBookIssueActionPerformed

    private void btnBookReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookReturnActionPerformed
        ArrayList<Book> BookReturnAdmin = new ArrayList<>();
        for (int i = 0; i < PruebaBiblioteca.biblioteca.getBooksList().size(); i++) {
            BookReturnAdmin.add(PruebaBiblioteca.biblioteca.getBooksList().get(i));
        }

        for (int i = (BookReturnAdmin.size() - 1); i >= 0; i--) {
            if (!BookReturnAdmin.get(i).isBorrowed()) {
                BookReturnAdmin.remove(i);
            }
        }

        initTableBookReturn(tableBookReturn, BookReturnAdmin);

        //Se remueven la información cargada del panel.
        cleanPanel();
        //Se imprime en el panel el menu BookReturn.
        PanelForm.add(BookReturn);
        printPanel();
    }//GEN-LAST:event_btnBookReturnActionPerformed

    private void btnConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigActionPerformed
        //Se remueven la información cargada del panel.
        cleanPanel();

        //Se imprime en el panel el menu Configuration.
        PanelForm.add(Configuration);
        printPanel();
    }//GEN-LAST:event_btnConfigActionPerformed

    private void btnManageBooksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageBooksActionPerformed
        //Se cargan los datos de la tabla.
        initTableBookManage(tableBookManage);

        //Se remueven la información cargada del panel.
        cleanPanel();

        //Se imprime en el panel el menu BookManage.
        PanelForm.add(BookManage);
        printPanel();
    }//GEN-LAST:event_btnManageBooksActionPerformed

    private void btnManageUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageUsersActionPerformed
        initTableUserManage(tableUserManage, userSearchListTable());

        //Se remueven la información cargada del panel.
        cleanPanel();

        //Se imprime en el panel el menu UserManage.
        PanelForm.add(UserManage);
        printPanel();
    }//GEN-LAST:event_btnManageUsersActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        PruebaBiblioteca.user = null;
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnBookSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookSearchActionPerformed
        //Se cargan los datos de la tabla.
        initTableBookList(tableBookSearch);

        //Se remueven la información cargada del panel.
        cleanPanel();

        //Se imprime en el panel el menu BookSearch.
        PanelForm.add(BookSearch);
        printPanel();
    }//GEN-LAST:event_btnBookSearchActionPerformed

    private void btnSearchUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchUsersActionPerformed
        //Se cargan los datos de la tabla.
        initTableUserSearch(tableUserSearch, userSearchListTable());
        //Se remueven la información cargada del panel.
        cleanPanel();

        //Se imprime en el panel el menu BookList.
        PanelForm.add(UserSearch);
        printPanel();
    }//GEN-LAST:event_btnSearchUsersActionPerformed

    private void btnUserAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserAddActionPerformed
        new UserAdd().setVisible(true);
    }//GEN-LAST:event_btnUserAddActionPerformed

    private void btnAdminAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminAddActionPerformed
        new AdminAdd().setVisible(true);
    }//GEN-LAST:event_btnAdminAddActionPerformed

    private void btnBookAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookAddActionPerformed
        new BookAdd().setVisible(true);
    }//GEN-LAST:event_btnBookAddActionPerformed

    private void btnRenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenameActionPerformed
        if (!(this.txtNameChange.getText().isEmpty())
            && !(this.txtLastName1.getText().isEmpty())
            && !(this.txtLastName2.getText().isEmpty())) {

            String name;
            String firstLastName;
            String secondLastName;

            name = txtNameChange.getText();
            firstLastName = txtLastName1.getText();
            secondLastName = txtLastName2.getText();

            PruebaBiblioteca.user.rename(name, firstLastName, secondLastName);
            PruebaBiblioteca.biblioteca.updateInfoInFiles();
        }
        
        if(this.txtNameChange.getText().isEmpty()) {
            this.txtNameChange.setBackground(Color.PINK);
            this.jblRenameError1.setText("*Campo obligatorio");
            this.jblRenameError1.setVisible(true);
        }
        
        if(this.txtLastName1.getText().isEmpty()) {
            this.txtLastName1.setBackground(Color.PINK);
            this.jblRelastname1Error.setText("*Campo obligatorio");
            this.jblRelastname1Error.setVisible(true);
        }
        
        if(this.txtLastName2.getText().isEmpty()) {
            this.txtLastName2.setBackground(Color.PINK);
            this.jblRelastname2Error.setText("*Campo obligatorio");
            this.jblRelastname2Error.setVisible(true);
        }
    }//GEN-LAST:event_btnRenameActionPerformed

    private void btnUserBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserBookActionPerformed
        Member UserArray = (Member) PruebaBiblioteca.user;
        ArrayList<Book> UserBookArray = UserArray.getBookList();
        initTableUserBook(tableUserBook, UserBookArray);

        //Se remueven la información cargada del panel.
        cleanPanel();

        //Se imprime en el panel el menu BookList.
        PanelForm.add(UserBook);
        printPanel();
    }//GEN-LAST:event_btnUserBookActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        if(!(this.txtBookSearch.getText().isEmpty())) {
            // Si el campo no está vacío:
            ArrayList<Book> BookSearh = PruebaBiblioteca.biblioteca.searchBook(txtBookSearch.getText());
            initTableBookSearch(tableBookSearch, BookSearh);
        } 
        
        if(this.txtBookSearch.getText().isEmpty()) {
            // Si el campo está vacío:
            this.jblSearchError.setText("*Campo obligatorio");
            this.txtBookSearch.setBackground(Color.PINK);
            this.jblSearchError.setVisible(true);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnUserSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserSearchActionPerformed
         if(!(this.txtUserSearch.getText().isEmpty())) {
            // Si el campo no está vacío:
            ArrayList<User> UserSearh = ((Admin) PruebaBiblioteca.user).searchUserInList(userSearchListTable(), txtUserSearch.getText());
            initTableUserSearch(tableUserSearch, UserSearh);
        }
         
        if(this.txtUserSearch.getText().isEmpty()) {
            // Si el campo está vacío:
            this.jblUserSearchError.setText("*Campo obligatorio");
            this.txtUserSearch.setBackground(Color.PINK);
            this.jblUserSearchError.setVisible(true);
        }
    }//GEN-LAST:event_btnUserSearchActionPerformed

    private void cbxShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxShowPasswordActionPerformed
        if (cbxShowPassword.isSelected()) {
            this.txtPassword.setEchoChar((char) 0);
        } else {
            this.txtPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_cbxShowPasswordActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void txtNameChangeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameChangeKeyReleased
        // Char leído al presionar la tecla:
        Character c = evt.getKeyChar();

        if (c >= KeyEvent.VK_0 && c <= KeyEvent.VK_9) {
            // Si se ingresan letras:
            new Validate().isValidStringWithoutDigits(this.txtNameChange, this.jblRenameError1);

        } else if (this.txtNameChange.getText().isEmpty() || new Validate().isValidStringWithoutDigits(this.txtNameChange, this.jblRenameError1)) {
            // Si el campo de texto está vacío o puede ser convertido a número:
            this.jblRenameError1.setVisible(false);
            this.txtNameChange.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_txtNameChangeKeyReleased

    private void txtLastName1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastName1KeyReleased
        // Char leído al presionar la tecla:
        Character c = evt.getKeyChar();

        if (c >= KeyEvent.VK_0 && c <= KeyEvent.VK_9) {
            // Si se ingresan letras:
            new Validate().isValidStringWithoutDigits(this.txtLastName1, this.jblRelastname1Error);

        } else if (this.txtLastName1.getText().isEmpty() || new Validate().isValidStringWithoutDigits(this.txtLastName1, this.jblRelastname1Error)) {
            // Si el campo de texto está vacío o puede ser convertido a número:
            this.jblRelastname1Error.setVisible(false);
            this.txtLastName1.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_txtLastName1KeyReleased

    private void txtLastName2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastName2KeyReleased
        // Char leído al presionar la tecla:
        Character c = evt.getKeyChar();

        if (c >= KeyEvent.VK_0 && c <= KeyEvent.VK_9) {
            // Si se ingresan letras:
            new Validate().isValidStringWithoutDigits(this.txtLastName2, this.jblRelastname2Error);

        } else if (this.txtLastName2.getText().isEmpty() || new Validate().isValidStringWithoutDigits(this.txtLastName2, this.jblRelastname2Error)) {
            // Si el campo de texto está vacío o puede ser convertido a número:
            this.jblRelastname2Error.setVisible(false);
            this.txtLastName2.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_txtLastName2KeyReleased

    private void txtBookSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBookSearchKeyReleased
        if(this.jblSearchError.isVisible()) {
            this.jblSearchError.setVisible(false);
            this.txtBookSearch.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_txtBookSearchKeyReleased

    private void txtUserSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserSearchKeyReleased
        if(this.jblUserSearchError.isVisible()) {
            this.jblUserSearchError.setVisible(false);
            this.txtUserSearch.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_txtUserSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BookIssue;
    private javax.swing.JPanel BookList;
    private javax.swing.JPanel BookManage;
    private javax.swing.JPanel BookReturn;
    private javax.swing.JPanel BookSearch;
    private javax.swing.JPanel Configuration;
    private javax.swing.JPanel Home;
    private javax.swing.JPanel PanelBase;
    private javax.swing.JPanel PanelForm;
    private javax.swing.JPanel PanelFormMenu;
    private javax.swing.JPanel PanelMenu;
    private javax.swing.JPanel User;
    private javax.swing.JPanel UserBook;
    private javax.swing.JPanel UserManage;
    private javax.swing.JPanel UserSearch;
    private javax.swing.JToggleButton btnAdminAdd;
    private javax.swing.JToggleButton btnBookAdd;
    private javax.swing.JButton btnBookIssue;
    private javax.swing.JButton btnBookList;
    private javax.swing.JButton btnBookReturn;
    private javax.swing.JButton btnBookSearch;
    private javax.swing.JButton btnConfig;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnManageBooks;
    private javax.swing.JButton btnManageUsers;
    private javax.swing.JToggleButton btnRename;
    private javax.swing.JToggleButton btnSearch;
    private javax.swing.JButton btnSearchUsers;
    private javax.swing.JButton btnUser;
    private javax.swing.JToggleButton btnUserAdd;
    private javax.swing.JButton btnUserBook;
    private javax.swing.JToggleButton btnUserSearch;
    private javax.swing.JCheckBox cbxShowPassword;
    private javax.swing.JLabel jblBookIssue;
    private javax.swing.JLabel jblBookList;
    private javax.swing.JLabel jblBookManage;
    private javax.swing.JLabel jblBookReturn;
    private javax.swing.JLabel jblBookSearch;
    private javax.swing.JLabel jblHomeBackground;
    private javax.swing.JLabel jblLastLogin;
    private javax.swing.JLabel jblLastName1;
    private javax.swing.JLabel jblLastName2;
    private javax.swing.JLabel jblLastNames;
    private javax.swing.JLabel jblName;
    private javax.swing.JLabel jblNameChange;
    private javax.swing.JLabel jblPassword;
    private javax.swing.JLabel jblRelastname1Error;
    private javax.swing.JLabel jblRelastname2Error;
    private javax.swing.JLabel jblRenameError1;
    private javax.swing.JLabel jblSearchError;
    private javax.swing.JLabel jblUser;
    private javax.swing.JLabel jblUserBook;
    private javax.swing.JLabel jblUserConfig;
    private javax.swing.JLabel jblUserID;
    private javax.swing.JLabel jblUserManage;
    private javax.swing.JLabel jblUserSearch;
    private javax.swing.JLabel jblUserSearchError;
    private javax.swing.JLabel jblWelcome;
    private javax.swing.JLabel jblWelcomeUser;
    private javax.swing.JScrollPane scrollBookIssue;
    private javax.swing.JScrollPane scrollBookList;
    private javax.swing.JScrollPane scrollBookReturn;
    private javax.swing.JScrollPane scrollBookSearch;
    private javax.swing.JScrollPane scrollBookSearch1;
    private javax.swing.JScrollPane scrollUserBook;
    private javax.swing.JScrollPane scrollUserManage;
    private javax.swing.JScrollPane scrollUserSearch;
    private javax.swing.JTable tableBookIssue;
    private javax.swing.JTable tableBookList;
    private javax.swing.JTable tableBookManage;
    private javax.swing.JTable tableBookReturn;
    private javax.swing.JTable tableBookSearch;
    private javax.swing.JTable tableUserBook;
    private javax.swing.JTable tableUserManage;
    private javax.swing.JTable tableUserSearch;
    private javax.swing.JTextField txtBookSearch;
    private javax.swing.JTextField txtLastLogin;
    private javax.swing.JTextField txtLastName1;
    private javax.swing.JTextField txtLastName2;
    private javax.swing.JTextField txtLastNames;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNameChange;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserID;
    private javax.swing.JTextField txtUserSearch;
    // End of variables declaration//GEN-END:variables
}
