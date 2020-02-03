package com.optimised_buildings_ltd.heartbeat;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;

@NiagaraType
@NiagaraProperty(
        name = "hostId",
        type = "BString",
        defaultValue = "BString.make(\"\")"
)
@NiagaraProperty(
        name = "stationName",
        type = "BString",
        defaultValue = "BString.make(\"\")"
)

public class BStationInfo extends BComponent{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimised_buildings_ltd.heartbeat.BStationInfo(3526451222)1.0$ @*/
/* Generated Tue Jan 28 10:01:07 GMT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "hostId"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code hostId} property.
   * @see #getHostId
   * @see #setHostId
   */
  public static final Property hostId = newProperty(0, BString.make(""), null);
  
  /**
   * Get the {@code hostId} property.
   * @see #hostId
   */
  public String getHostId() { return getString(hostId); }
  
  /**
   * Set the {@code hostId} property.
   * @see #hostId
   */
  public void setHostId(String v) { setString(hostId, v, null); }

////////////////////////////////////////////////////////////////
// Property "stationName"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code stationName} property.
   * @see #getStationName
   * @see #setStationName
   */
  public static final Property stationName = newProperty(0, BString.make(""), null);
  
  /**
   * Get the {@code stationName} property.
   * @see #stationName
   */
  public String getStationName() { return getString(stationName); }
  
  /**
   * Set the {@code stationName} property.
   * @see #stationName
   */
  public void setStationName(String v) { setString(stationName, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BStationInfo.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
}
