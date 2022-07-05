/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networksniffing;

import javax.swing.*;

/**
 *
 * @author AMEY
 */
public class BottomText extends JPanel {
   
    JLabel txt;
    public BottomText()
    {
          txt=new JLabel();
          txt.setAlignmentX(TOP_ALIGNMENT);
          
          this.add(txt);
          this.setVisible(true);
    }
    public void setMessage(String msg)
    {
            txt.setText(msg);
    }
    public String getMessage()
    {
        return txt.getText();
    }
}
