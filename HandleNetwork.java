/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networksniffing;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpcap.JpcapCaptor;
import jpcap.*;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;
import java.util.*;

/**
 *
 * @author AMEY
 */
public class HandleNetwork {
    
    static NetworkInterface[] networkcard;
    
    public HandleNetwork()
    {
            networkcard=JpcapCaptor.getDeviceList();
           
            
    }
    
    
}
