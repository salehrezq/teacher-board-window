/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter;

import java.io.Serializable;

/**
 *
 * @author Saleh
 */
public class StateInfo implements Serializable {

    private static final long serialVersionUID = 83904320664393L;

    private String type;
    private boolean state;
    private String info;

    public String getType() {
        return this.type;
    }

    public boolean getState() {
        return this.state;
    }

    public String getInfo() {
        return this.info;
    }

}
