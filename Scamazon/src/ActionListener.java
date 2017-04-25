
import java.awt.event.ActionEvent;
import java.util.EventListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kyle
 */

    public interface ActionListener extends EventListener {

    /**
     * Invoked when an action occurs.
     */
      
    public abstract void actionPerformed(ActionEvent e);

}

