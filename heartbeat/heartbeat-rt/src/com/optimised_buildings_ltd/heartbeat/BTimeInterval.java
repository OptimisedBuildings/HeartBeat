package com.optimised_buildings_ltd.heartbeat;

import javax.baja.control.trigger.BTimeTrigger;
import javax.baja.control.trigger.BTriggerMode;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;

@NiagaraType
@NiagaraProperty(
        name = "actionName",
        type = "BString",
        defaultValue = "BString.make(\"execute\")"
)

public class BTimeInterval extends BTimeTrigger {

    public BTimeInterval(BTriggerMode mode, String actionName) {
        this.setTriggerMode(mode);
        this.setActionName(actionName);
    }

    public BTimeInterval(){

    }
    /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
    /*@ $com.smartgreen.mqttSmartGreen.util.BTimeInterval(86757377)1.0$ @*/
    /* Generated Mon Dec 30 18:48:27 GMT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "actionName"
////////////////////////////////////////////////////////////////

    /**
     * Slot for the {@code actionName} property.
     * @see #getActionName
     * @see #setActionName
     */
    public static final Property actionName = newProperty(0, BString.make("execute"), null);

    /**
     * Get the {@code actionName} property.
     * @see #actionName
     */
    public String getActionName() { return getString(actionName); }

    /**
     * Set the {@code actionName} property.
     * @see #actionName
     */
    public void setActionName(String v) { setString(actionName, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

    @Override
    public Type getType() { return TYPE; }
    public static final Type TYPE = Sys.loadType(BTimeInterval.class);

    /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    @Override
    public void doFireTrigger(){
        this.getParent().asComponent().invoke(this.getParent().getAction(this.getActionName()), null);
    }

}
