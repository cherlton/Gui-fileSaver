/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package guilibrary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Student
 */
public class GuiLibrary extends JFrame {
private JPanel namePnl;
private JPanel surnamePnl;
private JPanel agePnl;
private JPanel detailsPnl;
private JPanel commentPnl;
private JPanel okBtnPnl;
private JPanel saveBtnPnl;
private JPanel okBtnAndCommentPnl;
private JPanel mainPnl;
private JPanel bodyPnl;

private JLabel nameLb;
private JLabel surnameLb;
private JLabel ageLb;

private JTextField nameTxf;
private JTextField surnameTxf;
private JTextField ageTxf;

private JTextArea commentTxtArea;
private JScrollPane scrollablePane;
private JButton okBtn;
private JButton saveBtn;
    public GuiLibrary() {
        setSize(250,200);
        setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(true);
        detailsPnl=new JPanel(new GridLayout(3,1,1,1));
        
        namePnl=new JPanel(new FlowLayout(FlowLayout.LEFT));
        surnamePnl=new JPanel(new FlowLayout(FlowLayout.LEFT));
        agePnl=new JPanel(new FlowLayout(FlowLayout.LEFT));
        okBtnAndCommentPnl=new JPanel(new GridLayout(1,2,1,1));
        commentPnl=new JPanel(new FlowLayout(FlowLayout.CENTER));
        okBtnPnl=new JPanel(new FlowLayout(FlowLayout.CENTER));
        saveBtnPnl=new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPnl=new JPanel(new BorderLayout());
        bodyPnl=new JPanel(new BorderLayout());
        
        nameLb=new JLabel("Enter Name:");
        surnameLb=new JLabel("Enter Surname:");
        ageLb=new JLabel("Enter Age:");
        
        nameTxf =new JTextField(10);
        surnameTxf =new JTextField(10);
        ageTxf =new JTextField(10);
        
        commentTxtArea=new JTextArea(10,15);
        commentTxtArea.setEditable(false);
        scrollablePane=new JScrollPane(commentTxtArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        okBtn=new JButton("Display");
        okBtn.addActionListener(new DisplayBtn());
        saveBtn=new JButton("SAVE");
        saveBtn.addActionListener(new SaveBtn());
         namePnl.add(nameLb);
         namePnl.add(nameTxf);
         
         surnamePnl.add(surnameLb);
         surnamePnl.add(surnameTxf);
         
         agePnl.add(ageLb);
         agePnl.add(ageTxf);
         
         
         detailsPnl.add(namePnl);
         detailsPnl.add(surnamePnl);
         detailsPnl.add(agePnl);
         
         commentPnl.add(scrollablePane);
         okBtnPnl.add(okBtn);
         okBtnAndCommentPnl.add(commentPnl);
         okBtnAndCommentPnl.add(okBtnPnl);
         
         saveBtnPnl.add(saveBtn);
         bodyPnl.add(detailsPnl,BorderLayout.NORTH);
         bodyPnl.add(okBtnAndCommentPnl,BorderLayout.CENTER);
         bodyPnl.add(saveBtnPnl,BorderLayout.SOUTH);
         mainPnl.add(bodyPnl);
         add(mainPnl);
         setResizable(false);
         pack();
        
         setVisible(true);
    }
    private class DisplayBtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
       
        
       
        JFileChooser fc=new JFileChooser();
        int val=fc.showOpenDialog(GuiLibrary.this);
        if(val==JFileChooser.APPROVE_OPTION){
        
            File file = fc.getSelectedFile();
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(file));
                String data="",record="";
                while((data=br.readLine())!=null){
                    record+=data+"\n";
            }
               br.close();
                commentTxtArea.setText(record);
                JOptionPane.showMessageDialog(GuiLibrary.this, "All data is displayed");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GuiLibrary.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GuiLibrary.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        }
        
    
    }

    private class SaveBtn implements ActionListener{
      

        @Override
        public void actionPerformed(ActionEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         String name=nameTxf.getText();
        String surname=surnameTxf.getText();
      
        int age=Integer.parseInt(ageTxf.getText());
        
        User us=new User(name, surname, age);
        JFileChooser fc=new JFileChooser();
        int val=fc.showSaveDialog(GuiLibrary.this);
        if(val==JFileChooser.APPROVE_OPTION){
        
            File file = fc.getSelectedFile();
            BufferedReader br;
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
                bw.write(us.toString());
                bw.newLine();
                bw.close();
                nameTxf.setText(" ");
                surnameTxf.setText(" ");
                ageTxf.setText(" ");
                JOptionPane.showMessageDialog(GuiLibrary.this, "All data is been added to a file");
            } catch (IOException ex) {
                 Logger.getLogger(GuiLibrary.class.getName()).log(Level.SEVERE, null, ex);
             }
               
            
            
        }
        }
        
    }
    
}
