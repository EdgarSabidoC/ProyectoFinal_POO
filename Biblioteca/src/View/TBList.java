/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import Model.Admin;
import Model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/**
 *
 * @author Carlos Antonio Ruiz
 */
public class TBList {
    private Vector<String> columnNames = new Vector<>();
    private Vector<Vector<Object>> dataBooks = new Vector();
    
    private void disposeTable() {
        columnNames.clear();
        dataBooks.clear();
    }
    
    private void initColumnasLibros() {
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
    private void initFilasLibros() {
        
        for(int i = 0; i < Dashboard.biblioteca.getNumberOfBooks(); i++) {
            Vector<Object> row = new Vector<>();
            
            row.add(Dashboard.biblioteca.getBooksList().get(i).getAuthor());
            row.add(Integer.toString(Dashboard.biblioteca.getBooksList().get(i).getYear()));
            row.add(Dashboard.biblioteca.getBooksList().get(i).getTitle());
            row.add(Dashboard.biblioteca.getBooksList().get(i).getEdition());
            row.add(Dashboard.biblioteca.getBooksList().get(i).getEditorial());
            row.add(Integer.toString(Dashboard.biblioteca.getBooksList().get(i).getNumPages()));
            row.add(Dashboard.biblioteca.getBooksList().get(i).getISBN());
            row.add(Dashboard.biblioteca.getBooksList().get(i).getAvailability());
            row.add(String.valueOf(Dashboard.biblioteca.getBooksList().get(i).getID().getCharCode()));
            if(Dashboard.biblioteca.getBooksList().get(i).isBorrowed()) {
                row.add(Dashboard.biblioteca.getBooksList().get(i).getReturnDate().getDateS());
            } else {
                row.add("No hay fecha");
            }
            row.add("PEDIR");
            row.add("DEVOLVER");
            row.add("ELIMINAR");
            dataBooks.add(row);
        }
        
    }
    
    private void initFilasLibrosConLista(ArrayList<Book> lista) {
        
        for(int i = 0; i < lista.size(); i++) {
            Vector<Object> row = new Vector<>();
            
            row.add(lista.get(i).getAuthor());
            row.add(Integer.toString(lista.get(i).getYear()));
            row.add(lista.get(i).getTitle());
            row.add(lista.get(i).getEdition());
            row.add(lista.get(i).getEditorial());
            row.add(Integer.toString(lista.get(i).getNumPages()));
            row.add(lista.get(i).getISBN());
            row.add(lista.get(i).getAvailability());
            row.add(String.valueOf(lista.get(i).getID().getCharCode()));
            if(lista.get(i).isBorrowed()) {
                row.add(lista.get(i).getReturnDate().getDateS());
            } else {
                row.add("No hay fecha");
            }
            row.add("PEDIR");
            row.add("DEVOLVER");
            row.add("ELIMINAR");
            dataBooks.add(row);
        }
        
    }
    
    private DefaultTableModel initTablaLibros() {
        //Se limpia la tabla si es que tiene algo almacenado.
        disposeTable();
        
        //Se carga el titulo de las columnas y los elementos de la fila.
        initColumnasLibros();
        initFilasLibros();
        
        //Se carga el contenido a la tabla y se configura como no editable.
        DefaultTableModel model = new DefaultTableModel(dataBooks, columnNames);/*{
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        */
        
        return model;
    }
    
    private DefaultTableModel initTablaLibrosConLista(ArrayList<Book> lista) {
        //Se limpia la tabla si es que tiene algo almacenado.
        disposeTable();
        
        //Se carga el titulo de las columnas y los elementos de la fila.
        initColumnasLibros();
        initFilasLibrosConLista(lista);
        
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
    
    public void initTablaListaLibros(JTable tabla) {
        
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
        tabla.setModel(initTablaLibros());
        Remove(tabla,columnas);
        
    }
    public void initTablaListaLibrosDevolver(JTable tabla) {
        
        List<Integer> columnas = new ArrayList<>();
         
        columnas.add(7);
        columnas.add(10);
        columnas.add(12);
        tabla.setModel(initTablaLibros());
        Remove(tabla,columnas);
        
    }
    public void initTablaListaLibrosBuscar(JTable tabla, ArrayList<Book> lista) {
        
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
        tabla.setModel(initTablaLibrosConLista(lista));
        Remove(tabla,columnas);
        
    }
    
    public void initTablaListaLibrosPrestados(JTable tabla, ArrayList<Book> lista) {
        
        List<Integer> columnas = new ArrayList<>();
         
        columnas.add(10);
        columnas.add(11);
        columnas.add(12);
        
        tabla.setModel(initTablaLibrosConLista(lista));
        Remove(tabla,columnas);
        
    }
    public void initTablaListaLibrosPedir(JTable tabla, ArrayList<Book> lista) {
        
        List<Integer> columnas = new ArrayList<>();
         
        columnas.add(7);
        columnas.add(9);
        columnas.add(11);
        columnas.add(12);
        
        tabla.setModel(initTablaLibrosConLista(lista));
        Remove(tabla,columnas);
        
    }
}
