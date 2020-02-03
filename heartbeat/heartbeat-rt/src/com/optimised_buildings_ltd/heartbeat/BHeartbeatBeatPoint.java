package com.optimised_buildings_ltd.heartbeat;

import com.tridiumx.mqttClientDriver.proxyExt.publishers.BMqttStringObjectPublishExt;

import javax.baja.control.BStringWritable;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;

@NiagaraType
@NiagaraProperty(
        name = "proxyExt",
        type = "BMqttStringObjectPublishExt",
        defaultValue = "new BMqttStringObjectPublishExt()"
)

public class BHeartbeatBeatPoint extends BStringWritable {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimised_buildings_ltd.heartbeat.BHeartbeatBeatPoint(1741780524)1.0$ @*/
/* Generated Mon Jan 13 11:57:03 GMT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "proxyExt"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code proxyExt} property.
   * @see #getProxyExt
   * @see #setProxyExt
   */
  public static final Property proxyExt = newProperty(0, new BMqttStringObjectPublishExt(), null);
  
  /**
   * Get the {@code proxyExt} property.
   * @see #proxyExt
   */
  public BMqttStringObjectPublishExt getProxyExt() { return (BMqttStringObjectPublishExt)get(proxyExt); }
  
  /**
   * Set the {@code proxyExt} property.
   * @see #proxyExt
   */
  public void setProxyExt(BMqttStringObjectPublishExt v) { set(proxyExt, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BHeartbeatBeatPoint.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
}
