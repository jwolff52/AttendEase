/************************************************************************
    AttendEase - A simple, point-and-click attendance program.
    Copyright (C) 2013-2014  James Wolff, Timothy Chandler, Sterling Long, Cole Howe

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*************************************************************************/

package attendease.util;

import attendease.gui.MeetingPanel;
import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author james.wolff
 */
public class ColorRenderer extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int row, int col) {
        if(col==MeetingPanel.NAME_COLUMN){
            return getNameCellRendererComponent(table, o, isSelected, hasFocus, row, col);
        }else if(col==MeetingPanel.TIME_COLUMN){
            return getTimeCellRendererComponent(table, o, isSelected, hasFocus, row, col);
        }else{
            return getPointsCellRendererComponent(table, o, isSelected, hasFocus, row, col);
        }
    }
    
    public Component getTimeCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int row, int col) {
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(true);
        Component comp=super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, col);
        if(MiscUtils.isLate(MiscUtils.timeToInt(FrameController.getGroup(FrameController.getSmgp().getCurrentGroupName()).getMeeting(FrameController.getSmgp().getCurrentMeetingName()).getStartTime()), MiscUtils.timeToInt((String)table.getValueAt(table.getRowCount()-1, 1)), FrameController.getInv().getGroup(FrameController.getSmgp().getCurrentGroupName()).getMeeting(FrameController.getSmgp().getCurrentMeetingName()).getDate(), new SimpleDateFormat("MMMM/dd/yyyy").format(Calendar.getInstance().getTime()))){
            setBackground(Color.RED);
        }else{
            setBackground(Color.GREEN);
        }
        table.setCellSelectionEnabled(false);
        return comp;
    }
    
    public Component getNameCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int row, int col) {
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(true);
        Color tC=table.getCellRenderer(row, MeetingPanel.TIME_COLUMN).getTableCellRendererComponent(table, table.getValueAt(row, MeetingPanel.TIME_COLUMN), hasFocus, hasFocus, row, MeetingPanel.TIME_COLUMN).getBackground();
        Color pC=table.getCellRenderer(row, MeetingPanel.POINTS_COLUMN).getTableCellRendererComponent(table, table.getValueAt(row, MeetingPanel.POINTS_COLUMN), hasFocus, hasFocus, row, MeetingPanel.POINTS_COLUMN).getBackground();
        Component comp=super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, col);
        if(tC.equals(Color.RED)||pC.equals(Color.RED)){
            setBackground(Color.RED);
        }else{
            setBackground(Color.WHITE);
        }
        table.setCellSelectionEnabled(false);
        return comp;
    }
    
    public Component getPointsCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int row, int col) {
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(true);
        Component comp=super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, col);
        if((Integer)o<0){
            setBackground(Color.RED);
        }else{
            setBackground(Color.WHITE);
        }
        table.setCellSelectionEnabled(false);
        return comp;
    }
    
}
