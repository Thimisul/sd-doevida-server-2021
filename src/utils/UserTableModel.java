/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.List;
import entidades.User;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lsilva
 */
public class UserTableModel extends AbstractTableModel {

    private List<User> userData = new ArrayList<User>();
    private String[] columnNames = {"NomeB", "CidadeB", "EstadoB"};

    public UserTableModel() {}
    
    public UserTableModel(List<User> userData) {
        this.userData = userData;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return userData.size();
    }

    @Override
    public Object getValueAt(int row, int column) {
        Object userAttribute = null;
        User userObject = userData.get(row);
        switch (column) {
            case 0:
                userAttribute = userObject.getName();
                break;
            case 1:
                userAttribute = userObject.getCity();
                break;
            case 2:
                userAttribute = userObject.getFederativeUnit();
            default:
                break;
        }
        return userAttribute;
    }

    public void addUser(User user) {
        userData.add(user);
        fireTableDataChanged();
    }

}
