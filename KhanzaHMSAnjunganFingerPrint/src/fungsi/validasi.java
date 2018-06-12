/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi;


import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import uz.ncipro.calendar.JDateTimePicker;
/**
 *
 * @author Owner
 */
public final class validasi {
    private int a,j,i;
    private String s,s1,auto;
    private final Connection connect=koneksiDB.condb();
    private final sekuel sek=new sekuel();
    private final java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
    private final DecimalFormat df2 = new DecimalFormat("###,###,###,###,###,###,###");  
    private final DecimalFormat df4 = new DecimalFormat("###,###,###,###,###,###,###.###");  
    private final DecimalFormat df3 = new DecimalFormat("######"); 
    private PreparedStatement ps;
    private ResultSet rs;
    private final Calendar now = Calendar.getInstance();
    private final int year=now.get(Calendar.YEAR);
    
    public validasi(){super();};

    public void autoNomer(DefaultTableModel tabMode,String strAwal,Integer pnj,javax.swing.JTextField teks){        
        s=Integer.toString(tabMode.getRowCount()+1);
        j=s.length();
        s1="";
        for(i = 1;i<=pnj-j;i++){
            s1=s1+"0";
        }
        teks.setText(strAwal+s1+s);
    }

    public void autoNomer(String tabel,String strAwal,Integer pnj,javax.swing.JTextField teks){
        try{            
            ps=connect.prepareStatement("select * from "+tabel);
            rs=ps.executeQuery();
            rs.last();
            s=Integer.toString(rs.getRow()+1);
            j=s.length();
            s1="";
            for(i = 1;i<=pnj-j;i++){
                s1=s1+"0";
            }
            teks.setText(strAwal+s1+s);      
         }catch(Exception e){
            System.out.println("Notifikasi : "+e);
         }
        
    }
    
    public void autoNomer2(String sql,String strAwal,Integer pnj,javax.swing.JTextField teks){
        try{            
            ps=connect.prepareStatement(sql);
            rs=ps.executeQuery();
            rs.last();
            s=Integer.toString(rs.getRow()+1);
            j=s.length();
            s1="";
            for(i = 1;i<=pnj-j;i++){
                s1=s1+"0";
            }
            teks.setText(strAwal+s1+s);
         }catch(Exception e){
            System.out.println("Notifikasi : "+e);
         }
        
    }
    
    public void autoNomer3(String sql,String strAwal,Integer pnj,javax.swing.JTextField teks){
        try{   
            rs=connect.prepareStatement(sql).executeQuery();
            s="1";
            while(rs.next()){
                s=Integer.toString(Integer.parseInt(rs.getString(1))+1);
            }            
            
            j=s.length();
            s1="";
            for(i = 1;i<=pnj-j;i++){
                s1=s1+"0";
            }
            teks.setText(strAwal+s1+s);
         }catch(Exception e){
            System.out.println("Notifikasi : "+e);
            JOptionPane.showMessageDialog(null,"Maaf, Query tidak bisa dijalankan...!!!!");
         }
        
    }
    
    public void autoNomer4(String sql,String strAwal,Integer pnj,javax.swing.JTextField teks){
        try{   
            rs=connect.prepareStatement(sql).executeQuery();
            s="1";
            while(rs.next()){
                s=Integer.toString(Integer.parseInt(rs.getString(1))+1);
            }            
            
            j=s.length();
            s1="";
            for(i = 1;i<=pnj-j;i++){
                s1=s1+"0";
            }
            teks.setText((strAwal+s1+s).substring(4,6)+(strAwal+s1+s).substring(2,4)+(strAwal+s1+s).substring(0,2));
         }catch(Exception e){
            System.out.println("Notifikasi : "+e);
            JOptionPane.showMessageDialog(null,"Maaf, Query tidak bisa dijalankan...!!!!");
         }
        
    }
    
    public void autoNomer5(String sql,String strAwal,Integer pnj,javax.swing.JTextField teks){
        try{   
            rs=connect.prepareStatement(sql).executeQuery();
            s="1";
            while(rs.next()){
                s=Integer.toString(Integer.parseInt(rs.getString(1))+1);
            }            
            
            j=s.length();
            s1="";
            for(i = 1;i<=pnj-j;i++){
                s1=s1+"0";
            }
            teks.setText((strAwal+s1+s).substring(2,4)+(strAwal+s1+s).substring(0,2)+(strAwal+s1+s).substring(4,6));
         }catch(Exception e){
            System.out.println("Notifikasi : "+e);
            JOptionPane.showMessageDialog(null,"Maaf, Query tidak bisa dijalankan...!!!!");
         }
        
    }
    
    public String autoNomer(String tabel,String strAwal,Integer pnj){
        try{    
            auto="";
            ps=connect.prepareStatement("select * from "+tabel);
            rs=ps.executeQuery();
            rs.last();
            s=Integer.toString(rs.getRow()+1);
            j=s.length();
            s1="";
            for(i = 1;i<=pnj-j;i++){
                s1=s1+"0";
            }
            auto=strAwal+s1+s;             
         }catch(Exception e){
            System.out.println("Notifikasi : "+e);
            JOptionPane.showMessageDialog(null,"Maaf, Query tidak bisa dijalankan...!!!!");
         }
        return auto;        
    }
    
    public String autoNomer3(String sql,String strAwal,Integer pnj){
        try{   
            auto="";
            rs=connect.prepareStatement(sql).executeQuery();
            s="1";
            while(rs.next()){
                s=Integer.toString(Integer.parseInt(rs.getString(1))+1);
            }            
            
            j=s.length();
            s1="";
            for(i = 1;i<=pnj-j;i++){
                s1=s1+"0";
            }
            auto=strAwal+s1+s;
         }catch(Exception e){
            System.out.println("Notifikasi : "+e);
            JOptionPane.showMessageDialog(null,"Maaf, Query tidak bisa dijalankan...!!!!");
         }
        return auto;
    }

    public void editTable(DefaultTableModel tabMode,String table,String field_acuan,JTextField nilai_field,String update) {
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            nilai_field.requestFocus();
        }else if(nilai_field.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Gagal mengedit. Pilih dulu data yang mau diedit.\nKlik data pada table untuk memilih...!!!!");
        }else if(! nilai_field.getText().trim().equals("")){            
            sek.mengedit(table,field_acuan+"='"+nilai_field.getText()+"'", update);                 
        }
    }
    
    public void editTable(DefaultTableModel tabMode,String table,String field_acuan,String nilai_field,String update,int i, String[] a) {
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
        }else if(nilai_field.trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Gagal mengedit. Pilih dulu data yang mau diedit.\nKlik data pada table untuk memilih...!!!!");
        }else if(! nilai_field.trim().equals("")){            
            sek.mengedit(table,field_acuan+"="+nilai_field, update,i,a);                 
        }
    }

    public void editTable(DefaultTableModel tabMode,String table,String field_acuan,JComboBox nilai_field,String update) {
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            nilai_field.requestFocus();
        }else if(nilai_field.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Gagal mengedit. Pilih dulu data yang mau diedit.\nKlik data pada table untuk memilih...!!!!");
        }else if(nilai_field.getSelectedItem()!=""){            
            sek.mengedit(table,field_acuan+"='"+nilai_field.getSelectedItem()+"'", update);            
            
        }
    }

    public void editTable(DefaultTableModel tabMode,String table,String field_acuan,JLabel nilai_field,String update) {
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            nilai_field.requestFocus();
        }else if(nilai_field.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Gagal mengedit. Pilih dulu data yang mau diedit.\nKlik data pada table untuk memilih...!!!!");
        }else if(! nilai_field.getText().trim().equals("")){            
            sek.mengedit(table,field_acuan+"='"+nilai_field.getText()+"'", update);            
            
        }
    }
    
    public void fillData(DefaultTableModel model,JTable table, File file) {
        try {
            WritableWorkbook workbook1 = Workbook.createWorkbook(file);
            WritableSheet sheet1 = workbook1.createSheet("First Sheet", 0); 
            model = (DefaultTableModel) table.getModel();

            for (i = 0; i < model.getColumnCount(); i++) {
                Label column = new Label(i, 0, model.getColumnName(i));
                sheet1.addCell(column);
            }
            for (i = 0; i < model.getRowCount(); i++) {
                for (j = 0; j < model.getColumnCount(); j++) {
                    Label row = new Label(j, i + 1, 
                            model.getValueAt(i, j).toString());
                    sheet1.addCell(row);
                }
            }
            workbook1.write();
            workbook1.close();
        } catch (IOException | WriteException ex) {
            System.out.println("Notifikasi : "+ex);
        }
    }

    public void hapusTable(DefaultTableModel tabMode,JTextField nilai_field,String table,String field) {
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            nilai_field.requestFocus();
        }else if(nilai_field.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus.\nKlik data pada table untuk memilih...!!!!");
        }else if(! nilai_field.getText().trim().equals("")){            
            sek.meghapus(table,field,nilai_field.getText());            
            
        }
    }

    public void hapusTable(DefaultTableModel tabMode,JComboBox nilai_field,String table,String field) {
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            nilai_field.requestFocus();
        }else if(nilai_field.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus.\nKlik data pada table untuk memilih...!!!!");
        }else if(nilai_field.getSelectedItem()!=""){            
            String data=nilai_field.getSelectedItem().toString();
            sek.meghapus(table,field,data);     
        }
    }

    public void loadCombo(JComboBox cmb,String field,String table){
        cmb.removeAllItems();
        try{            
            ps=connect.prepareStatement("select "+field+" from "+table+" order by "+field);
            rs=ps.executeQuery();
            while(rs.next()){
                String item=rs.getString(1);
                cmb.addItem(item);
                a++;
            }          
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }

    public void LoadTahun(JComboBox cmb){        
        cmb.removeAllItems();
        for(i =year;i>=1980;i--){
            cmb.addItem(i);
        }
        cmb.setSelectedItem(year);
    }

    public void LoadTahunAkd(JComboBox cmb){
        cmb.removeAllItems();
        for(i = 1950;i<=year;i++){
            cmb.addItem(i+"1");
            cmb.addItem(i+"2");
        }
        cmb.setSelectedItem(year+"1");
    }

    @SuppressWarnings("empty-statement")
    public void MyReport(String reportName,String reportDirName,String judul,String qry){
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jrxml ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        // Ambil Direktori tempat file RptMaster.jrxml berada
        String[] subRptDir = fullPath.split(reportName);
        String reportDir = subRptDir[0];


        try {
            try (Statement stm = connect.createStatement()) {
                Map<String, Object> parameters = new HashMap<>();
                
                try {
                    
                    String namafile="./"+reportDirName+"/"+reportName;
                    File reportfile=new File(namafile);
                    
                    JRDesignQuery newQuery = new JRDesignQuery();
                    newQuery.setText(qry);
                    JasperDesign jasperDesign = JRXmlLoader.load(reportfile);
                    jasperDesign.setQuery(newQuery);
                    
                    JasperReport JRpt = JasperCompileManager.compileReport(jasperDesign);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(JRpt, parameters, connect);
                    
                    JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                    jasperViewer.setTitle(judul);
                    Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
                    jasperViewer.setSize(screen.width-50,screen.height-50);
                    jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                    jasperViewer.setLocationRelativeTo(null);
                    jasperViewer.setVisible(true);
                } catch (Exception rptexcpt) {
                    System.out.println("Report Can't view because : " + rptexcpt);
                    JOptionPane.showMessageDialog(null,"Report Can't view because : "+ rptexcpt);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @SuppressWarnings("empty-statement")
    public void MyReport2(String reportName,String reportDirName,String judul,String qry){
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jrxml ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        // Ambil Direktori tempat file RptMaster.jrxml berada
        String[] subRptDir = fullPath.split(reportName);
        String reportDir = subRptDir[0];


        try {
            try (Statement stm = connect.createStatement()) {
                Map<String, Object> parameters = new HashMap<>();
                
                try {
                    
                    String namafile="./"+reportDirName+"/"+reportName;
                    File reportfile=new File(namafile);
                    
                    JRDesignQuery newQuery = new JRDesignQuery();
                    newQuery.setText(qry);
                    JasperDesign jasperDesign = JRXmlLoader.load(reportfile);
                    jasperDesign.setQuery(newQuery);
                    
                    JasperReport JRpt = JasperCompileManager.compileReport(jasperDesign);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(JRpt, parameters, connect);
                    
                    JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                    jasperViewer.setTitle(judul);
                    Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
                    jasperViewer.setSize(screen.width-50,screen.height-50);
                    jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                    jasperViewer.setLocationRelativeTo(null);
                    jasperViewer.setAlwaysOnTop(true);
                    jasperViewer.setVisible(true);
                } catch (Exception rptexcpt) {
                    System.out.println("Report Can't view because : " + rptexcpt);
                    JOptionPane.showMessageDialog(null,"Report Can't view because : "+ rptexcpt);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void MyReport(String reportName,String reportDirName,String judul,String qry,Map parameters){
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jrxml ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        // Ambil Direktori tempat file RptMaster.jrxml berada
        String[] subRptDir = fullPath.split(reportName);
        String reportDir = subRptDir[0];


        try {
            try {
                String namafile="./"+reportDirName+"/"+reportName;
                File reportfile=new File(namafile);

                JRDesignQuery newQuery = new JRDesignQuery();
                newQuery.setText(qry);
                JasperDesign jasperDesign = JRXmlLoader.load(reportfile);
                jasperDesign.setQuery(newQuery);

                JasperReport JRpt = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(JRpt, parameters, connect);

                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle(judul);
                Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
                jasperViewer.setSize(screen.width-50,screen.height-50);
                jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                jasperViewer.setLocationRelativeTo(null);
                jasperViewer.setVisible(true);
            } catch (Exception rptexcpt) {
                System.out.println("Report Can't view because : " + rptexcpt);
                JOptionPane.showMessageDialog(null,"Report Can't view because : "+ rptexcpt);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void MyReport2(String reportName,String reportDirName,String judul,String qry,Map parameters){
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jrxml ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        // Ambil Direktori tempat file RptMaster.jrxml berada
        String[] subRptDir = fullPath.split(reportName);
        String reportDir = subRptDir[0];


        try {
            try {
                String namafile="./"+reportDirName+"/"+reportName;
                File reportfile=new File(namafile);

                JRDesignQuery newQuery = new JRDesignQuery();
                newQuery.setText(qry);
                JasperDesign jasperDesign = JRXmlLoader.load(reportfile);
                jasperDesign.setQuery(newQuery);

                JasperReport JRpt = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(JRpt, parameters, connect);

                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle(judul);
                Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
                jasperViewer.setSize(screen.width-50,screen.height-50);
                jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                jasperViewer.setLocationRelativeTo(null);
                jasperViewer.setAlwaysOnTop(true);
                jasperViewer.setVisible(true);
            } catch (Exception rptexcpt) {
                System.out.println("Report Can't view because : " + rptexcpt);
                JOptionPane.showMessageDialog(null,"Report Can't view because : "+ rptexcpt);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void MyReport(String reportName,Map parameters,String title){
        try {
                JasperViewer jasperViewer =new JasperViewer(JasperFillManager.fillReport(JasperCompileManager.compileReport("./report/"+reportName),parameters,connect), false);
                jasperViewer.setTitle(title);
                jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                jasperViewer.setLocationRelativeTo(null);
                jasperViewer.setVisible(true);
                //JasperViewer.viewReport(JasperFillManager.fillReport(JasperCompileManager.compileReport("./report/"+reportName),parameters,connect),false);
        } catch (Exception ex) {
           System.out.println("Notifikasi : "+ex);
        } 
    }


    public void pindah(java.awt.event.KeyEvent evt,JTextField kiri,JTextField kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah(java.awt.event.KeyEvent evt,JTextField kiri,JTextField kanan,JTextField bawah){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            bawah.requestFocus();
        }
    }
    
    public void pindah2(java.awt.event.KeyEvent evt,JTextField kiri,JTextField kanan){
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JTextField kiri,JTextArea kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(KeyEvent evt, JComboBox kiri, JTextArea kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JTextField kiri,JButton kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah(java.awt.event.KeyEvent evt,JTextField kiri,JButton kanan,JTextField bawah){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            bawah.requestFocus();
        }
    }
    
    public void pindah2(java.awt.event.KeyEvent evt,JTextField kiri,JButton kanan){
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JButton kiri,JTextField kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JButton kiri,JButton kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JTextField kiri,JComboBox kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JTextArea kiri,JComboBox kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JComboBox kiri,JTextField kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JComboBox kiri,JDateTimePicker kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JDateTimePicker kiri,JTextField kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JComboBox kiri,JComboBox kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JComboBox kiri,JButton kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(KeyEvent evt, JButton kiri, JComboBox kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(KeyEvent evt, JTextArea kiri, JButton kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(KeyEvent evt, JTextArea kiri, JTextField kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void panggilUrl(String url){
        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();                                
        try{ 
            Properties prop = new Properties();
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            if(os.contains("win")) {
                rt.exec( "rundll32 url.dll,FileProtocolHandler " + "http://"+koneksiDB.HOST2()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+url);
            }else if (os.contains("mac")) {
                rt.exec( "open " + "http://"+koneksiDB.HOST2()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+url);
            }else if (os.contains("nix") || os.contains("nux")) {
                String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror","chrome","chromium","netscape","opera","links","lynx","midori"};
                // Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
                StringBuilder cmd = new StringBuilder();
                for(i=0; i<browsers.length; i++) cmd.append(i==0  ? "" : " || ").append(browsers[i]).append(" \"").append("http://").append(koneksiDB.HOST()+":"+prop.getProperty("PORTWEB")).append("/").append(prop.getProperty("HYBRIDWEB")).append("/").append(url).append( "\" ");
                rt.exec(new String[] { "sh", "-c", cmd.toString() });
            } else {
                return;
            }
        }catch (Exception e){
            return;
        } 
    }
    
    public void printUrl(String url) throws URISyntaxException{
        try{
           Properties prop = new Properties();
           prop.loadFromXML(new FileInputStream("setting/database.xml"));            
           desktop.print(new File(new java.net.URI("http://"+koneksiDB.HOST()+":"+prop.getProperty("PORTWEB")+"/"+url)));  
        }catch (Exception e) {
           System.out.println(e);
        }
    }

    public void SetTgl(DefaultTableModel tabMode,JTable table,JDateTimePicker dtp,int i){
        j=table.getSelectedRow();
        try {
           Date dtpa = new SimpleDateFormat("yyyy-MM-dd").parse(tabMode.getValueAt(j,i).toString());
           dtp.setDate(dtpa);
        } catch (ParseException ex) {
           dtp.setDate(new Date());
        }
    }
    
    public String SetTgl(String original){
        s = "";
        try {
            s=original.substring(6,10)+"-"+original.substring(3,5)+"-"+original.substring(0,2);
        }catch (Exception e) {
        }   
        return s;
    }
    
    public void SetTgl(JDateTimePicker dtp,String tgl){            
        try {
           Date dtpa = new SimpleDateFormat("yyyy-MM-dd").parse(tgl);
           dtp.setDate(dtpa);
        } catch (ParseException ex) {
           dtp.setDate(new Date());
        }
    }
    
    public Date SetTgl2(String tgl){
        try {
           Date dtpa = new SimpleDateFormat("yyyy-MM-dd").parse(tgl);
           return dtpa;
        } catch (ParseException ex) {
           return new Date();
        }
    }
    
    public void textKosong(JTextField teks,String pesan){
        JOptionPane.showMessageDialog(null,"Maaf, "+pesan+" tidak boleh kosong...!!!");
        teks.requestFocus();
    }

    public void textKosong(JTextArea teks,String pesan){
        JOptionPane.showMessageDialog(null,"Maaf, "+pesan+" tidak boleh kosong...!!!");
        teks.requestFocus();
    }


    public void tabelKosong(DefaultTableModel tabMode) {
        j=tabMode.getRowCount();
        for(i=0;i<j;i++){
            tabMode.removeRow(0);
        }
    }

    public void textKosong(JComboBox teks, String pesan) {
        JOptionPane.showMessageDialog(null,"Maaf, "+pesan+" tidak boleh kosong...!!!");
        teks.requestFocus();
    }
    
    public String SetAngka(double nilai){        
       return df2.format(nilai);
    }
    
    public String SetAngka3(double nilai){        
       return df4.format(nilai);
    }
    
    public String SetAngka2(double nilai){        
       return df3.format(nilai);
    }
    
    public double SetAngka(String txt){
        double x;         
            if(txt.equals("")){
                x=0;
            }else{
                x=Double.parseDouble(txt);
            }
            return x;
    }

       
}
