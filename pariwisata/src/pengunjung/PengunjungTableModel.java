package pengunjung;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PengunjungTableModel extends AbstractTableModel{
    private List<Pengunjung> list = new ArrayList<>();
    
    public void insert (Pengunjung p){
        list.add(p);
        fireTableDataChanged();
    }
    
    public void update (int row, Pengunjung p){
        list.set(row, p);
        fireTableDataChanged();
    }
    
    public void delete (int row){
        list.remove(row);
        fireTableDataChanged();
    }
    
    public Pengunjung get (int row){
        return list.get(row);
    }
    
    public void setList (List<Pengunjung> list){
        this.list = list;
        fireTableDataChanged();
    }
    

    public int getRowCount(){
        return list.size();
    }
    

    public int getColumnCount(){
        return 7;
    }
    

    public Object getValueAt(int rowIndex, int columnIndex){
        switch (columnIndex){
            case 0 :
                return rowIndex + 1;
                
            case 1 :
                return list.get(rowIndex).getID();
                
            case 2 :
                return list.get(rowIndex).getNama();
                
            case 3 :
                return list.get(rowIndex).getEmail();
                
            case 4 :
                return list.get(rowIndex).getTelp();

            case 5 :
                return list.get(rowIndex).getAlamat();
                
            
            default :
                return null;
        }
    }
    

    public String getColumnName (int column){
        switch (column){
            case 0 :
                return "ID";
                
            case 1 :
                return "Nama";
                
            case 2 :
                return "Email";
                
            case 3 :
                return "Telpon";
                
            case 4 :
                return "Alamat";
                     
            default :
                return null;
        }
    }

    
}
