package pengunjung;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import home.HomeView;
import javax.swing.table.TableModel;
import utilities.config;

public class PengunjungController {
    private final PengunjungTableModel ptm = new PengunjungTableModel();
    
     public void setMaximumFrame (PengunjungView sv)
    {
        try{
            sv.setMaximum(true);
        }
        catch (Exception error){
            System.err.println("Error at SiswaController-setMaximumFrame , details : "+ error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-setMaximumFrame , details : "+ error.toString());
        }
    }
    
    public void setUndecoroted (PengunjungView pv)
    {
        try {
            pv.putClientProperty("JInternalFrame.isPallete", Boolean.TRUE);
            BasicInternalFrameUI basicInternalFrameUI = (BasicInternalFrameUI) pv.getUI();
            basicInternalFrameUI.setNorthPane(null);
        } catch (Exception error) {
            System.err.println("Error at SiswaController-setUndecoreted , details : "+ error.toString());
            JOptionPane.showMessageDialog(pv, "Error at SiswaController-setUndecoreted , details : "+ error.toString());
        }
    }
    
    public void setTableModel (PengunjungView pv){
        try {
            PengunjungView.tablePengunjung.setModel((TableModel) ptm);
        } catch (Exception error) {
            System.err.println("Error at SiswaController-setTableModel , details : "+ error.toString());
            JOptionPane.showMessageDialog(pv, "Error at SiswaController-setTableModel , details : "+ error.toString());
        }
    }
    public void loadData (PengunjungView sv){
        try {
                Statement statement;
            ResultSet rs;
            String sqlSelect = "SELECT * FROM user ORDER by id_user ASC";
            statement = config.getConnection().createStatement();
            rs = statement.executeQuery(sqlSelect);

            List<Pengunjung> list = new ArrayList<>();
            while (rs.next()){
                
                

            Pengunjung p = new Pengunjung();
            p.setID(rs.getString("id_user"));
            p.setNama(rs.getString("nama"));
            p.setEmail(rs.getString("email"));
            p.setTelp(rs.getString("telp"));
            p.setAlamat(rs.getString("alamat"));
            list.add(p);
            }ptm.setList(list);
        }
        catch(Exception error){
            System.err.println("Error at SiswaController-loadData , details : "+ error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-loadData , details : "+ error.toString());
        
        }
    }
    
     /*Method searchData digunakan untuk mengambil data yang ada pada database dan disajikan pada tablePengunjung
    *akan tetapi data yang diambil itu berdasarkan keyword pencarian
    */
    
    public void searchData (PengunjungView pv){
        try {
            String parameter = "";
            if (PengunjungView.comboCari.getSelectedIndex() == 0) {
                parameter = "id_user";
            }
            else if(PengunjungView.comboCari.getSelectedIndex() == 1){
                parameter ="nama";
            }
            else if(PengunjungView.comboCari.getSelectedIndex() == 2){
                parameter ="email";
            }
            else if(PengunjungView.comboCari.getSelectedIndex() == 3){
                parameter ="telp";
            }
            
            String keyword = PengunjungView.textCari.getText();
            Statement statement;
            ResultSet rs;
            
            String sqlSelect = "SELECT * FROM user WHERE " + parameter + " LIKE '%" + keyword + "%' ORDER BY id_user ASC";
            statement = config.getConnection().createStatement();
            rs = statement.executeQuery(sqlSelect);
            
        } catch (Exception error) {
            System.err.println("Error at SiswaController-searchData , details : "+ error.toString());
            JOptionPane.showMessageDialog(pv, "Error at SiswaController-searchData , details : "+ error.toString());
        
        }
    }
    /*Method Refresh method ini digunakan untuk membuat keadaan view PengunjungView menjadi Default
    */
    
    public void refresh ( PengunjungView pv){
        try {
             PengunjungView.labelStatus.setText("");
             PengunjungView.textID.setText("");
             PengunjungView.textNama.setText("");
             PengunjungView.textAreaAlamat.setText("");
             PengunjungView.textCari.setText("");
             PengunjungView.textEmail.setText("");
             PengunjungView.textTelpon.setText("");
             PengunjungView.comboCari.setSelectedIndex(0);
             PengunjungView.tablePengunjung.clearSelection();
            
            
             //PengunjungView.labelStatus.setEnabled(false);
             PengunjungView.textID.setEnabled(false);
             PengunjungView.textNama.setEnabled(false);
             PengunjungView.textAreaAlamat.setEnabled(false);
             PengunjungView.textCari.setEnabled(false);
             PengunjungView.textEmail.setEnabled(false);
             PengunjungView.textTelpon.setEnabled(false);
             PengunjungView.comboCari.setEnabled(false);
           
            
            
             PengunjungView.comboCari.setEnabled(true);
             PengunjungView.textCari.setEnabled(true);
             PengunjungView.tablePengunjung.setEnabled(true);
             PengunjungView.buttonTambah.setEnabled(true);
             PengunjungView.buttonUbah.setEnabled(true);
             PengunjungView.buttonHapus.setEnabled(true);
             PengunjungView.buttonSegarkan.setEnabled(true);
             PengunjungView.buttonKeluar.setEnabled(true);
            
          loadData(pv);
        } catch (Exception error) {
             System.err.println("Error at SiswaController-refresh , details : "+ error.toString());
             JOptionPane.showMessageDialog(pv, "Error at SiswaController-refresh , details : "+ error.toString());
        }
    }
     /*Method tableSiswaAction digunakan untuk perilaku table yang mana
    *jika kita mengklik salah satu data maka data tersebut akan di set ke masing masing itemnya
    */
    
    public void tablePengunjungAction (final PengunjungView pv){
        PengunjungView.tablePengunjung.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = PengunjungView.tablePengunjung.getSelectedRow();
                if (row != -1){
                    Pengunjung p = ptm.get(row);
                    PengunjungView.textID.setText(p.getID());
                    PengunjungView.textNama.setText(p.getNama());
                    PengunjungView.textEmail.setText(p.getEmail());
                    PengunjungView.textTelpon.setText(p.getTelp());
                    PengunjungView.textAreaAlamat.setText(p.getAlamat());
                }
            }
        });
    }
      /*Function getIDOtomatis
    * Digunakan gsn meulah suatu ID menjadi otomatis dengan suatu format tertentu dengan format
    *ID-XXXX
    */
    
    private String getIDOtomatis (PengunjungView pv){
        String id = "";
        try {
            Statement statement;
            ResultSet rs;
            String sqlSelect = "SELECT id_user FROM user ORDER BY id_user ASC";
            
            statement = config.getConnection().createStatement();
            rs = statement.executeQuery(sqlSelect);
            
        } catch (Exception error) {
             System.err.println("Error at SiswaController-getNisOtomatis , details : "+ error.toString());
             JOptionPane.showMessageDialog(pv, "Error at SiswaController-getNisOtomatis , details : "+ error.toString());
        }
        return id;
    }
    
    public void buttonTambahAction (PengunjungView pv){
        try {
            PengunjungView.labelStatus.setText("INSERT");
            PengunjungView.textID.setText(getIDOtomatis(pv));
            PengunjungView.textNama.setText("");
            PengunjungView.textEmail.setText("");
            PengunjungView.textTelpon.setText("");
            PengunjungView.textAreaAlamat.setText("");
            PengunjungView.comboCari.setSelectedIndex(0);
            PengunjungView.textCari.setText("");
            PengunjungView.tablePengunjung.clearSelection();
           
            PengunjungView.labelStatus.setEnabled(true);
            PengunjungView.textID.setEnabled(true);
            PengunjungView.textNama.setEnabled(true);
            PengunjungView.textEmail.setEnabled(true);
             PengunjungView.textTelpon.setEnabled(true);
            PengunjungView.textAreaAlamat.setEnabled(true);
            PengunjungView.buttonSimpan.setEnabled(true);
            PengunjungView.buttonBatal.setEnabled(true);
            
            PengunjungView.comboCari.setEnabled(false);
            PengunjungView.textCari.setEnabled(false);
            PengunjungView.tablePengunjung.setEnabled(false);
            PengunjungView.buttonTambah.setEnabled(false);
            PengunjungView.buttonUbah.setEnabled(false);
            PengunjungView.buttonHapus.setEnabled(false);
            PengunjungView.buttonSegarkan.setEnabled(false);
            PengunjungView.buttonKeluar.setEnabled(false);
            
            PengunjungView.textNama.requestFocus();
        } catch (Exception error) {
         System.err.println("Error at SiswaController-buttonTambahAction , details : "+ error.toString());
         JOptionPane.showMessageDialog(pv, "Error at SiswaController-buttonTambahAction , details : "+ error.toString());
        }
    }
     public void buttonUbahAction(PengunjungView pv){
        try {
            int row = PengunjungView.tablePengunjung.getSelectedRow();
            
            if (row == -1) {
                JOptionPane.showMessageDialog(pv, "Silahkan klik data yang ingin diubah");
            }
            else {
                
            PengunjungView.labelStatus.setText("UPDATE");
            
            PengunjungView.textID.setEnabled(true);
            PengunjungView.textNama.setEnabled(true);
            PengunjungView.textEmail.setEnabled(true);
            PengunjungView.textTelpon.setEnabled(true);
            PengunjungView.textAreaAlamat.setEnabled(true);
            PengunjungView.buttonSimpan.setEnabled(true);
            PengunjungView.buttonBatal.setEnabled(true);
            
            PengunjungView.comboCari.setEnabled(false);
            PengunjungView.textCari.setEnabled(false);
            PengunjungView.tablePengunjung.setEnabled(false);
            PengunjungView.buttonTambah.setEnabled(false);
            PengunjungView.buttonUbah.setEnabled(false);
            PengunjungView.buttonHapus.setEnabled(false);
            PengunjungView.buttonSegarkan.setEnabled(false);
            PengunjungView.buttonKeluar.setEnabled(false);
            
            PengunjungView.textNama.requestFocus();
            }
        } catch (Exception error) {
             System.err.println("Error at SiswaController-buttonUbahAction , details : "+ error.toString());
             JOptionPane.showMessageDialog(pv, "Error at SiswaController-buttonUbahAction , details : "+ error.toString());
        }
    }
     public void buttonHapusAction (PengunjungView pv){
        try {
            int row = PengunjungView.tablePengunjung.getSelectedRow();
            
            if (row == -1) {
                JOptionPane.showMessageDialog(pv, "Silahkan klik data yang ingin dihapus");
            }
            else{
                String id = ptm.get(row).getID();
                int konfirmasi = JOptionPane.showConfirmDialog(pv, "Apakah anda yakin ingin menghapus data ID" +id+"?", "Konfrimasi", JOptionPane.YES_NO_OPTION);
                
                if (konfirmasi == JOptionPane.YES_NO_OPTION) {
                    PreparedStatement ps;
                    String sqlDelete = "DELETE FROM user WHERE id_user = ?";
                    
                    ps = config.getConnection().prepareStatement(sqlDelete);
                    ps.setString(1, id);
                    int isSuccess = ps.executeUpdate();
                    if (isSuccess == 0) {
                        JOptionPane.showMessageDialog(pv, "Data gagal dihapus", "Pesan", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(pv, "Data berhasil dihapus", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                refresh(pv);
            }
        } catch (HeadlessException | SQLException error) {
            System.err.println("Error at SiswaController-buttonHapusAction, details : " + error.toString());
            JOptionPane.showMessageDialog(pv, "Error at SiswaController-buttonHapusAction, details : " +error.toString());
        }
    }
     public void buttonSegarkanAction (PengunjungView pv){
        try {
            refresh(pv);
        } catch (Exception error) {
             System.err.println("Error at SiswaController-buttonSegarkanAction , details : "+ error.toString());
             JOptionPane.showMessageDialog(pv, "Error at SiswaController-buttonSegarkanAction , details : "+ error.toString());
        }
    }
    
    public void buttonKeluarAction (PengunjungView pv){
        try {
            pv.dispose();
            HomeView.menuItemPengunjung.setEnabled(true);
        } catch (Exception error) {
             System.err.println("Error at SiswaController-buttonTutupAction , details : "+ error.toString());
             JOptionPane.showMessageDialog(pv, "Error at SiswaController-buttonTutupAction , details : "+ error.toString());
        }
    }
    public boolean validasiData (PengunjungView pv){
        boolean b = true;
        
        try {
            if (PengunjungView.textID.getText().equals("")) {
                JOptionPane.showMessageDialog(pv, "ID Tidak boleh kosong");
            }
            else if (PengunjungView.textNama.getText().equals("")) {
                JOptionPane.showMessageDialog(pv, "Nama Tidak boleh kosong");
            }
            else if (PengunjungView.textEmail.getText().equals("")) {
                JOptionPane.showMessageDialog(pv, "Email Tidak boleh kosong");
            }
            else if (PengunjungView.textTelpon.getText().equals("")) {
                JOptionPane.showMessageDialog(pv, "Nomor Telepon Tidak boleh kosong");
            }
            else if (PengunjungView.textAreaAlamat.getText().equals("")) {
                JOptionPane.showMessageDialog(pv, "Alamat Tidak boleh kosong");
            }
            else{
                b = false;
            }
        } catch (Exception error) {
             System.err.println("Error at SiswaController-validasiData , details : "+ error.toString());
             JOptionPane.showMessageDialog(pv, "Error at SiswaController-validasidata , details : "+ error.toString());
        }
        return b;
    }
    
    public void buttonSimpanAction (PengunjungView pv) throws SQLException{
        try {
            boolean b = validasiData(pv);
           
                
                Pengunjung p = new Pengunjung();
                p.setID(PengunjungView.textID.getText());
                p.setNama(PengunjungView.textNama.getText());
                p.setEmail(PengunjungView.textEmail.getText());
                p.setTelp(PengunjungView.textTelpon.getText());
                p.setAlamat(PengunjungView.textAreaAlamat.getText());
                
                PreparedStatement ps;
                if (PengunjungView.labelStatus.getText().equals("INSERT")) {
                    String sqlInsert = "INSERT INTO user (id_user, nama, email, telp, alamat) VALUES (?, ?, ?, ?, ?)";
                    ps = config.getConnection().prepareStatement(sqlInsert);
                    ps.setString(1, p.getID());
                    ps.setString(2, p.getNama());
                    ps.setString(3, p.getEmail());
                    ps.setString(4, p.getTelp());
                    ps.setString(5, p.getAlamat());
                    int isSuccess = ps.executeUpdate();
                    
                    if (isSuccess != 1) {
                        JOptionPane.showMessageDialog(pv, "Data gagal disimpan", "Pesan", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(pv, "Data berhasil disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                    }
                    refresh(pv);
                }
                else if (PengunjungView.labelStatus.getText().equals("UPDATE")) {
                    String sqlUpdate = "UPDATE user SET nama = ?, email = ?, telp = ?, alamat = ? WHERE id_user = ?";
                    ps = config.getConnection().prepareStatement(sqlUpdate);
                    ps.setString(1, p.getNama());
                    ps.setString(2, p.getEmail());
                    ps.setString(3, p.getTelp());
                    ps.setString(4, p.getAlamat());
                    ps.setString(5, p.getID());
                     
                   
                    int isSuccess = ps.executeUpdate();
                    if (isSuccess != 1) {
                        JOptionPane.showMessageDialog(pv, "Data gagal diubah", "Pesan", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(pv, "Data berhasil diubah", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                    }
                    refresh(pv);
                }
                
            
        } catch (SQLException | HeadlessException error) {
             System.err.println("Error at SiswaController-buttonSimpanAction , details : "+ error.toString());
             JOptionPane.showMessageDialog(pv, "Error at SiswaController-buttonSimpanAction , details : "+ error.toString());
        }
    }
    
    public void buttonBatalAction (PengunjungView pv){
        try {
            refresh(pv);
        } catch (Exception error) {
             System.err.println("Error at SiswaController-buttonBatalAction , details : "+ error.toString());
             JOptionPane.showMessageDialog(pv, "Error at SiswaController-buttonBatalAction , details : "+ error.toString());
        }
    }
}
