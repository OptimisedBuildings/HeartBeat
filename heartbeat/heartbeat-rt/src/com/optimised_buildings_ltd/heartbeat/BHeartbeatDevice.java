package com.optimised_buildings_ltd.heartbeat;

import com.tridiumx.mqttClientDriver.BAbstractMqttDriverDevice;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;

@NiagaraType
@NiagaraProperty(
        name = "points",
        type = "BHeartbeatPointsFolder",
        defaultValue = "new BHeartbeatPointsFolder()"
)

public class BHeartbeatDevice extends BAbstractMqttDriverDevice {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimised_buildings_ltd.heartbeat.BHeartbeatDevice(2172576421)1.0$ @*/
/* Generated Mon Jan 13 11:53:08 GMT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "points"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code points} property.
   * @see #getPoints
   * @see #setPoints
   */
  public static final Property points = newProperty(0, new BHeartbeatPointsFolder(), null);
  
  /**
   * Get the {@code points} property.
   * @see #points
   */
  public BHeartbeatPointsFolder getPoints() { return (BHeartbeatPointsFolder)get(points); }
  
  /**
   * Set the {@code points} property.
   * @see #points
   */
  public void setPoints(BHeartbeatPointsFolder v) { set(points, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BHeartbeatDevice.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
}
