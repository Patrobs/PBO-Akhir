package home;
import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.JOptionPane;
import pengunjung.PengunjungView;

public class HomeController {
      public void setFullScreen (HomeView hv){
        try {
            hv.setExtendedState(MAXIMIZED_BOTH);
        } catch (Exception e) {
             System.err.println("Error at HomeController-setFullScreen , details : "+ e.toString());
             JOptionPane.showMessageDialog(hv, "Error at HomeController-refresh , details : "+ e.toString());
        }
    }
    
    public void menuItemKeluarAction (HomeView hv){
        try {
            int konfirmasi = JOptionPane.showConfirmDialog(hv, "Apakah anda yakin ingin keluar?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if(konfirmasi == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(hv, "Anda Telah Keluar!");
                System.exit(0);
            }
        } catch (Exception error) {
            System.err.println("Error at HomeController-menuItemKeluarAction , details : "+ error.toString());
            JOptionPane.showMessageDialog(hv, "Error at HomeController-menuItemKeluarAction , details : "+ error.toString());
        
        }
    }
    
    public void menuItemPengunjungAction (HomeView hv){
        try {
            HomeView.menuItemPengunjung.setEnabled(false);
            PengunjungView pv = new PengunjungView();
            HomeView.panelHome.add(pv);
            pv.setVisible(true);
        } catch (Exception e) {
            System.err.println("Error at HomeController-menuItemSiswaAction , details : "+ e.toString());
            JOptionPane.showMessageDialog(hv, "Error at HomeController-menuItemSiswaAction , details : "+ e.toString());
        
        }
    }
}
