/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networksniffing;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author AMEY
 */
public class Application extends JFrame {
    
    BottomText btxt;
    InterfaceAdapter ia;
    
    public Application()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.setTitle("NETWORK SNIFFING ");
        this.setResizable(false);
        this.setSize(1000,700);
        this.setLayout(new BorderLayout());
        
        //ImageIcon img=new ImageIcon("networkimg.jpg");
        //JLabel icon=new JLabel(img);
        
       // this.add(icon,BorderLayout.CENTER);
        
        
        
        //this.remove(icon);
        
        
        ia=new InterfaceAdapter(this);
        
        add(ia,BorderLayout.CENTER);
        
        btxt=new BottomText();        
        btxt.setMessage("Developed By :Amey Kulkarni ");
        this.add(btxt,BorderLayout.SOUTH);
        
    }
}
