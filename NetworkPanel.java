/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networksniffing;

import javax.swing.*;
import jpcap.NetworkInterface;
import java.awt.*;
import java.awt.event.*;
import jpcap.NetworkInterfaceAddress;
/**
 *
 * @author AMEY
 */
public class NetworkPanel extends JPanel {
    
    private JFrame application;
    private NetworkInterface nf;
    
   Label iname;
   
   JButton idetails;
    
    public NetworkPanel(JFrame f,NetworkInterface n)
    {
            this.nf=n;
            this.application=f;
            this.setLayout(null);
            
            this.setBackground(Color.white);
            
            iname=new Label();
            iname.setAlignment(Label.CENTER);
            
           iname.setBounds(10, 10, 950, 100);
           iname.setFont(new Font("arial",Font.BOLD,24));
            iname.setText(""+nf.description);
          
            
            Font myfont=new Font("arial",Font.BOLD,18);
            
            Label name=new Label("Name :\t "+nf.name);
            Label description=new Label("Description :\t "+nf.description);
            Label datalink=new Label("Data Link Name :\t "+nf.datalink_name);
            Label datalinkdesc=new Label("Data Link Description :\t  "+nf.datalink_description);
            JButton go=new JButton("Track Packet");
            String mac="";
          
            for(byte b : nf.mac_address)
           {
                mac=mac+b+":";
           }
            mac=mac.substring(0, mac.length());
            Label macaddress=new Label("Mac Address :\t "+mac);
           
            
            name.setBounds(10,100,950,50);
            description.setBounds(10,150,950,50);
            datalink.setBounds(10,200,950,50);
            datalinkdesc.setBounds(10,250,950,50);
            macaddress.setBounds(10,300,950,50);
            go.setBounds(10,450,200,20);
            name.setFont(myfont);
            description.setFont(myfont);
            datalink.setFont(myfont);
            datalinkdesc.setFont(myfont);
            macaddress.setFont(myfont);
          
            
            this.add(iname);
            this.add(name);
            this.add(description);
            this.add(datalink);
            this.add(datalinkdesc);
            this.add(macaddress);
            this.add(go);
            this.setVisible(true);
            
            go.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent ae)
            {
                    TrackingFrame tr=new TrackingFrame(f,n);
            
            }
            
            });
    
    }
    
}
