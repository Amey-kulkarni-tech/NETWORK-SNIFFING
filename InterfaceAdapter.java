/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networksniffing;

import java.awt.Color;
import javax.swing.*;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpcap.JpcapCaptor;
import jpcap.*;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author AMEY
 */
public class InterfaceAdapter  extends JPanel{

    static NetworkInterface[] ni;
    public InterfaceAdapter(JFrame f)
    {
        this.setBackground(Color.lightGray);
        
        this.setLayout(null);
        
        JTabbedPane tb=new JTabbedPane();
        tb.setBounds(20,50,950,500);
        
           ni=JpcapCaptor.getDeviceList();
           
           for(int i=0;i<ni.length;i++)
           {
               NetworkPanel np=new NetworkPanel(f,ni[i]);
               
               tb.add(ni[i].description, np);
               
           }
           
           this.add(tb);
        
        
    }
    
    
}
