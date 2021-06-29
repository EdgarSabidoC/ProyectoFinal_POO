package View;


import Model.Admin;
import Model.Book;
import Model.Member;
import Model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/**
 *
 * @author Edgar Sabido Cortés, Carlos Antonio Ruíz Domínguez, Luis Alfredo Cota Armenta.
 * 
 */
public class TBList {
    private Vector<String> columnNames = new Vector<>();
    private Vector<Vector<Object>> dataBooks = new Vector();
    
    private void disposeTable() {
        columnNames.clear();
        dataBooks.clear();
    }
    
    private void initColumnsUser() {
        columnNames.add("TIPO");
        columnNames.add("ID");
        columnNames.add("NOMBRE");
        columnNames.add("APELLIDOS");
        columnNames.add("ÚLTIMO ACCESO");
        columnNames.add("ELIMINAR");
        columnNames.add("CONTRASEÑA");
    }
    
    public void initRowsUser(ArrayList<User> list) {
        for(int i = 0; i < list.size(); i++) {
            Vector<Object> row = new Vector<>();
            
            if(list.get(i) instanceof Member) {
                row.add("CLNT");
            } else {
                row.add("ADMN");
            }
            row.add(String.valueOf(list.get(i).getUserID().getCharCode()));
            row.add(list.get(i).getName());
            row.add(list.get(i).getFirstLastName() + " " + list.get(i).getSecondLastName());
            row.add(list.get(i).getLastLogin().getDateS());
            row.add("ELIMINAR");
            row.add("CONTRASEÑA");
            dataBooks.add(row);
        }
    }
    
    private DefaultTableModel initTableUser(ArrayList<User> list) {
        //Se limpia la tabla si es que tiene algo almacenado.
        disposeTable();
        
        //Se carga el titulo de las columnas y los elementos de la fila.
        initColumnsUser();
        initRowsUser(list);
        
        //Se carga el contenido a la tabla y se configura como no editable.
        DefaultTableModel model = new DefaultTableModel(dataBooks, columnNames);
        
        return model;
    }
    
    private void initColumnsBook() {
        columnNames.add("AUTOR");
        columnNames.add("AÑO");
        columnNames.add("TITULO");
        columnNames.add("EDICIÓN");
        columnNames.add("EDITORIAL");
        columnNames.add("PÁGINAS");
        columnNames.add("ISBN");
        columnNames.add("DISPONIBILIDAD");
        columnNames.add("ID");
        columnNames.add("FECHA DE RETORNO");
        columnNames.add("PEDIR");
        columnNames.add("DEVOLVER");
        columnNames.add("ELIMINAR");
    }
    
    
    
    private void initRowsBook(ArrayList<Book> list) {
        
        for(int i = 0; i < list.size(); i++) {
            Vector<Object> row = new Vector<>();
            
            row.add(list.get(i).getAuthor());
            row.add(Integer.toString(list.get(i).getYear()));
            row.add(list.get(i).getTitle());
            row.add(list.get(i).getEdition());
            row.add(list.get(i).getEditorial());
            row.add(Integer.toString(list.get(i).getNumPages()));
            row.add(list.get(i).getISBN());
            row.add(list.get(i).getAvailability());
            row.add(String.valueOf(list.get(i).getID().getCharCode()));
            if(list.get(i).isBorrowed()) {
                row.add(list.get(i).getReturnDate().getDateS());
            } else {
                row.add("No hay fecha");
            }
            row.add("PEDIR");
            row.add("DEVOLVER");
            row.add("ELIMINAR");
            dataBooks.add(row);
        }
        
    }
    
    
    private DefaultTableModel initTableBook(ArrayList<Book> list) {
        //Se limpia la tabla si es que tiene algo almacenado.
        disposeTable();
        
        //Se carga el titulo de las columnas y los elementos de la fila.
        initColumnsBook();
        initRowsBook(list);
        
        //Se carga el contenido a la tabla y se configura como no editable.
        DefaultTableModel model = new DefaultTableModel(dataBooks, columnNames);
        
        return model;
    }
    
    private void Remove(JTable table, List col_index){
        List<TableColumn> tcol = new ArrayList<>();
        for(int i = 0; i < col_index.size(); i++) {
            tcol.add(table.getColumnModel().getColumn((Integer)col_index.get(i)));
        }
        for(int i = 0; i < tcol.size(); i++) {
            table.removeColumn(tcol.get(i));
        }
    }
    
    public void initTableUserSearch(JTable table, ArrayList<User> list) {
        
        List<Integer> columnas = new ArrayList<>();
        columnas.add(5);
        columnas.add(6);
        
        table.setModel(initTableUser(list));
        Remove(table,columnas);
        
    }
    
    public void initTableUserManage(JTable table, ArrayList<User> list) {
        
        List<Integer> columnas = new ArrayList<>();
        columnas.add(6);
        
        table.setModel(initTableUser(list));
        Remove(table,columnas);
        
    }
    public void initTableUserManageRoot(JTable table, ArrayList<User> list) {
        
        List<Integer> columnas = new ArrayList<>();
        
        table.setModel(initTableUser(list));
        Remove(table,columnas);
        
    }
    
    
    public void initTableBookList(JTable table, ArrayList<Book> list) {
        
        List<Integer> columnas = new ArrayList<>();
         
        if(Dashboard.usuario instanceof Admin) {
            columnas.add(10);
            columnas.add(11);
            columnas.add(12);
        } else {
            columnas.add(9);
            columnas.add(10);
            columnas.add(11);
            columnas.add(12);
        }
        table.setModel(initTableBook(list));
        Remove(table,columnas);
        
    }
    
    public void initTableBookManage(JTable table, ArrayList<Book> list) {
        
        List<Integer> columnas = new ArrayList<>();
         
        columnas.add(10);
        columnas.add(11);
        
        
        table.setModel(initTableBook(list));
        Remove(table,columnas);
        
    }
    public void initTableBookReturn(JTable table, ArrayList<Book> list) {
        
        List<Integer> columnas = new ArrayList<>();
        
        columnas.add(7);
        columnas.add(10);
        columnas.add(12);
        table.setModel(initTableBook(list));
        Remove(table,columnas);
        
    }
    public void initTableBookSearch(JTable table, ArrayList<Book> list) {
        
        List<Integer> columnas = new ArrayList<>();
         
        if(Dashboard.usuario instanceof Admin) {
            columnas.add(10);
            columnas.add(11);
            columnas.add(12);
        } else {
            columnas.add(9);
            columnas.add(10);
            columnas.add(11);
            columnas.add(12);
        }
        table.setModel(initTableBook(list));
        Remove(table,columnas);
        
    }
    
    public void initTableBookIssued(JTable table, ArrayList<Book> list) {
        
        List<Integer> columnas = new ArrayList<>();
         
        columnas.add(10);
        columnas.add(11);
        columnas.add(12);
        
        table.setModel(initTableBook(list));
        Remove(table,columnas);
        
    }
    public void initTableBookIssue(JTable table, ArrayList<Book> list) {
        
        List<Integer> columnas = new ArrayList<>();
         
        columnas.add(7);
        columnas.add(9);
        columnas.add(11);
        columnas.add(12);
        
        table.setModel(initTableBook(list));
        Remove(table,columnas);
        
    }
}
