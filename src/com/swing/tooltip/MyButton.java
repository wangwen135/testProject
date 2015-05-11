package com.swing.tooltip;

import java.awt.event.ActionListener;  

import javax.swing.JButton;  
import javax.swing.JToolTip;  
  
public class MyButton extends JButton {  
  
     private static final long serialVersionUID = -4212536258012660909L;  
      
     private MyToolTip toolTip;  
     private ActionListener toolTipBtnListener;  
      
     public MyButton() {  
          super();  
     }  
  
     public JButton getToolTipButton() {  
          if (toolTip != null) {  
               return toolTip.getButton();  
          } else {  
               return null;  
          }  
     }  
      
     public void addToolTipBtnListener(ActionListener l) {  
          toolTipBtnListener = l;  
     }  
      
     public JToolTip createToolTip() {  
          if (toolTip == null) {  
               toolTip = new MyToolTip();  
               toolTip.getButton().addActionListener(toolTipBtnListener);  
          }  
           
          return toolTip;  
     }  
}  