package com.optimised_buildings_ltd.heartbeat;

import com.tridiumx.mqttClientDriver.proxyExt.subscribers.BMqttStringObjectSubscribeExt;

import javax.baja.control.BStringPoint;
import javax.baja.control.BStringWritable;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;

@NiagaraType
@NiagaraProperty(
        name = "proxyExt",
        type = "BMqttStringObjectSubscribeExt",
        defaultValue = "new BMqttStringObjectSubscribeExt()"
)

public class BMqttStringSubscriber extends BStringPoint {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimised_buildings_ltd.heartbeat.BMqttStringSubscriber(1009629523)1.0$ @*/
/* Generated Tue Jan 28 09:50:25 GMT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "proxyExt"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code proxyExt} property.
   * @see #getProxyExt
   * @see #setProxyExt
   */
  public static final Property proxyExt = newProperty(0, new BMqttStringObjectSubscribeExt(), null);
  
  /**
   * Get the {@code proxyExt} property.
   * @see #proxyExt
   */
  public BMqttStringObjectSubscribeExt getProxyExt() { return (BMqttStringObjectSubscribeExt)get(proxyExt); }
  
  /**
   * Set the {@code proxyExt} property.
   * @see #proxyExt
   */
  public void setProxyExt(BMqttStringObjectSubscribeExt v) { set(proxyExt, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BMqttStringSubscriber.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/


}
