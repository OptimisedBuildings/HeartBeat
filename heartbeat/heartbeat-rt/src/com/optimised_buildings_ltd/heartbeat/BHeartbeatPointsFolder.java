package com.optimised_buildings_ltd.heartbeat;

import com.tridiumx.mqttClientDriver.point.BMqttClientDriverPointDeviceExt;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;

@NiagaraType
@NiagaraProperty(
        name = "beat",
        type = "BHeartbeatBeatPoint",
        defaultValue = "new BHeartbeatBeatPoint()"
)

public class BHeartbeatPointsFolder extends BMqttClientDriverPointDeviceExt {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimised_buildings_ltd.heartbeat.BHeartbeatPointsFolder(497665117)1.0$ @*/
/* Generated Mon Jan 13 11:55:25 GMT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "beat"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code beat} property.
   * @see #getBeat
   * @see #setBeat
   */
  public static final Property beat = newProperty(0, new BHeartbeatBeatPoint(), null);
  
  /**
   * Get the {@code beat} property.
   * @see #beat
   */
  public BHeartbeatBeatPoint getBeat() { return (BHeartbeatBeatPoint)get(beat); }
  
  /**
   * Set the {@code beat} property.
   * @see #beat
   */
  public void setBeat(BHeartbeatBeatPoint v) { set(beat, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BHeartbeatPointsFolder.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

}
