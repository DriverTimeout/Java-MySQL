
package javaing;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Javaing {

    static JFrame f;
    static JTable table;
    static ResultSet rs;
    static String[][] rowData;
    static int count = 0;

    public static void main(String[] args) {
        String url ="jdbc:mysql://localhost:3306/WrightDB2022";
        String login = "root";
        String password="";
        
        Connection conn = null;
        Statement stmt;
        try
        {
          conn = DriverManager.getConnection(url, login, password);
          stmt = conn.createStatement();
          rs = stmt.executeQuery("select * from employees;");
          while(rs.next())
          {
            count++;
          }
             rowData = new String[count][4];
             int index = 0;
             rs = stmt.executeQuery("select * from employees;");
             while(rs.next())
             {
              rowData[index][0] = rs.getString(1);
              rowData[index][0] = rs.getString(2);
              rowData[index][0] = rs.getString(3);
              rowData[index][0] = rs.getString(4);
              index++;
             }
          conn.close();
        }
        catch(SQLException ex)
        {
         ex.printStackTrace();
        }
        String[] columnNames={"ID", "List Name", "First Name", "Age"};
        table = new JTable(rowData, columnNames);
        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        table.getColumnModel().getColumn(3).setPreferredWidth(5);
        table.setRowSelectionAllowed(true);
        ListSelectionModel gust = table.getSelectionModel();
        gust.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        gust.addListSelectionListener(new selection());
        f = new JFrame("DB to SWING List");
        f.add(new JScrollPane(table));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300, 200);
        f.setResizable(false);
        f.setVisible(true);
       
    }
    
     public static class selection implements ListSelectionListener
   {
        @Override 
        public void valueChanged(ListSelectionEvent e){
        String selectedID = null;
        String selectedLname = null;
        String selectedFname = null;
        String selectedAge = null;
        int[] selectedRow = table.getSelectedRows();
        for(int i = 0; i<selectedRow.length; i++)
        {
         selectedID = (String) table.getValueAt(selectedRow[i], 0);
         selectedLname = (String) table.getValueAt(selectedRow[i], 1);
         selectedFname = (String) table.getValueAt(selectedRow[i], 2);
         selectedAge = (String) table.getValueAt(selectedRow[i], 3);
        }
         StringBuilder sb = new StringBuilder();
         sb.append("EMPLOYEE INFO\n");
         sb.append("=============\n");
         sb.append("Employee ID: "+selectedID+"\n");
         sb.append("Employee Last Name: "+selectedLname+"\n");
         sb.append("Employee First Name: "+selectedFname+"\n");
         sb.append("Employee Age: "+selectedAge+"\n");
         JOptionPane.showMessageDialog(f, sb.toString(), "Employee Selected", JOptionPane.INFORMATION_MESSAGE);
}
   }  
}
