/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package attendease.util;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author james.wolff
 */
public class ATableModel extends DefaultTableModel{
    
    public ATableModel(){
        super();
    }
    
    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }
}
