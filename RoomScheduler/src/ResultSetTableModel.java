
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author guthr
 */
public class ResultSetTableModel extends DefaultTableModel {
    public ResultSetTableModel(String[] col, Object[][] queriedList) {
        setColumnIdentifiers(col);
        addRows(queriedList);
    }
    
    public void addRows(Object[][] queriedList) {
       for (Object[] i: queriedList) {
           addRow(i);
           System.out.println("Added row");
       }
    }
}
