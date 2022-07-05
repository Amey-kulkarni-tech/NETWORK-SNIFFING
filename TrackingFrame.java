/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networksniffing;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.PacketReceiver;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;
import java.util.Vector;
/**
 *
 * @author AMEY
 */
public class TrackingFrame  extends JFrame{
    
    private JFrame baseframe,current;
    private NetworkInterface nf;
    private Vector<String> packetstore;
    private  JpcapCaptor captor;
    private JTable datatable; 
    private static DefaultTableModel dtm;
    private static int counter;
    public TrackingFrame(JFrame ref1,NetworkInterface ref2)
    {
        packetstore=new Vector();
            baseframe=ref1;
            current=this;
            nf=ref2;
            counter=1;
            baseframe.setVisible(false);
            this.setResizable(false);
            this.setSize(1000,700);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setTitle(ref2.description.toUpperCase()+" DATA PACKET TRACKING");
            this.setVisible(true);
            
            this.setLayout(new BorderLayout());
            
            this.datatable=new JTable();
            dtm=new DefaultTableModel(0,0);
            dtm.setColumnIdentifiers(new String[]{"NO","LENGTH","SOURCE IP","SOURCE PORT","DESTINATION IP","DESTINATION PORT","PROTOCOL"});
            datatable.setModel(dtm);
            
            JScrollPane scroll=new JScrollPane(datatable);
            scroll.setVisible(true);
            this.add(scroll,BorderLayout.CENTER);
            
            datatable.setEnabled(false);
            
            datatable.getColumnModel().getColumn(0).setPreferredWidth(5);
            datatable.getColumnModel().getColumn(1).setPreferredWidth(10);
            datatable.getColumnModel().getColumn(2).setPreferredWidth(150);
            datatable.getColumnModel().getColumn(3).setPreferredWidth(10);
            datatable.getColumnModel().getColumn(4).setPreferredWidth(150);
            datatable.getColumnModel().getColumn(5).setPreferredWidth(10);
            datatable.getColumnModel().getColumn(6).setPreferredWidth(50);
            
            
            BottomText btxt=new BottomText();        
             btxt.setMessage("Developed By : Gauri Mulik (197036) and Amey Kulkarni (197030) ");
            this.add(btxt,BorderLayout.SOUTH);
           
            
           JMenuBar mb=new JMenuBar();
           JMenu start=new JMenu("Start");
           JMenu stop=new JMenu("Stop");
           JMenu back=new JMenu("Back");
           JMenu savefile=new JMenu("Save packet");
          start.addMouseListener(new MouseAdapter(){
          
          public void mouseClicked(MouseEvent me)
          {
              try{
                captor=JpcapCaptor.openDevice(nf, 65535, true, 20);
                Runnable r=new Runnable()
                {
                    public void run()
                    {   
                        counter=1;
                        captor.loopPacket(Integer.MAX_VALUE, new PacketPrinter());
                        
                    }
                };
                Thread th=new Thread(r);
                th.start();
                JOptionPane.showMessageDialog(current, "Packet tracking started" );
                
              }catch(Exception ex)
              {
                  //exception
                  System.out.print(ex);
              }
         }
          
          });
          
          stop.addMouseListener(new MouseAdapter(){
          
          public void mouseClicked(MouseEvent me)
          {
              try{
                captor.close();
                JOptionPane.showMessageDialog(current,"STOPED Tracking the Packet");
              }catch(Exception ex)
              {
                  //exception
                  System.out.print(ex);
              }
         }
          
          });
          
          back.addMouseListener(new MouseAdapter(){
          
          public void mouseClicked(MouseEvent me)
          {
              try{
                    current.dispose();
                    baseframe.setVisible(true);
              
              }catch(Exception ex)
              {
                  
                  //exception
                  System.out.print(ex);
              }
         }
          
          });
           
          savefile.addMouseListener(new MouseAdapter(){
          
          public void mouseClicked(MouseEvent me)
          {
                JFileChooser jfc=new JFileChooser();
                int option=jfc.showSaveDialog(current);
                if(option==JFileChooser.APPROVE_OPTION)
                {
                    try{
                        File file=jfc.getSelectedFile();
                        FileOutputStream fout=new FileOutputStream(file);
                        PrintWriter pw=new PrintWriter(fout);
                        for(int i=0;i<packetstore.size();i++)
                        {
                            pw.println(packetstore.get(i));
                        }
                        pw.close();
                        fout.close();
                        packetstore.clear();
                        JOptionPane.showMessageDialog(current, "File Saved Successfully");
                    }catch(Exception ex)
                    {
                            JOptionPane.showMessageDialog(current,"Exception Occured ... please try again");
                    }
             
                }
                else
                {
                    JOptionPane.showMessageDialog(current, "Not able to save the file");
                }
          
          }
      
          });
          
            mb.add(back);
            mb.add(start);
            mb.add(stop);
            mb.add(savefile);
          this.setJMenuBar(mb);
            
            
            
    
    }



    class PacketPrinter implements PacketReceiver {

    @Override
    public void receivePacket(Packet packet) {
       
            if(packet instanceof TCPPacket)
            {
                TCPPacket tcp=(TCPPacket)packet;
                String str=""+counter+"\t"+tcp.len+"\t"+tcp.src_ip+"\t"+tcp.src_port+"\t"+tcp.dst_ip+"\t"+tcp.dst_port+"\tTCP";
                dtm.addRow(new String[]{""+counter,""+tcp.len,""+tcp.src_ip,""+tcp.src_port,""+tcp.dst_ip,""+tcp.dst_port,"TCP"});
                packetstore.add(str);
                counter++;
            }
            else if(packet instanceof UDPPacket)
            {
                    UDPPacket udp=(UDPPacket)packet;
                    String str=""+counter+"\t"+udp.len+"\t"+udp.src_ip+"\t"+udp.src_port+"\t"+udp.dst_ip+"\t"+udp.dst_port+"\tUDP";
                dtm.addRow(new String[]{""+counter,""+udp.len,""+udp.src_ip,""+udp.src_port,""+udp.dst_ip,""+udp.dst_port,"UDP"});
                counter++;    
                packetstore.add(str);
                
            }
            
           
    }       
    
    
}




}
