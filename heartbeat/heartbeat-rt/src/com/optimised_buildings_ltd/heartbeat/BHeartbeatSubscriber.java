package com.optimised_buildings_ltd.heartbeat;

import com.tridiumx.mqttClientDriver.point.BMqttClientDriverPointFolder;

import javax.baja.nre.annotations.NiagaraAction;
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
@NiagaraAction(
        name = "subscribe"
)
@NiagaraProperty(
        name = "deviceHealth",
        type = "BHealthData",
        defaultValue = "new BHealthData()"
)
@NiagaraProperty(
        name = "historyHealth",
        type = "BHealthData",
        defaultValue = "new BHealthData()"
)
@NiagaraAction(
        name = "update"
)
/*
@NiagaraProperty(
        name = "historyHealth",
        type = "BMqttStringSubscriber",
        defaultValue = "new BMqttStringSubscriber()"
)
@NiagaraProperty(
        name = "deviceHealth",
        type = "BMqttStringSubscriber",
        defaultValue = "new BMqttStringSubscriber()"
)
@NiagaraProperty(
        name = "deviceIds",
        type = "BMqttStringSubscriber",
        defaultValue = "new BMqttStringSubscriber()"
)*/

public class BHeartbeatSubscriber extends BMqttClientDriverPointFolder {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimised_buildings_ltd.heartbeat.BHeartbeatSubscriber(1494781829)1.0$ @*/
/* Generated Tue Jan 28 15:39:58 GMT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

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
// Property "deviceHealth"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code deviceHealth} property.
   * @see #getDeviceHealth
   * @see #setDeviceHealth
   */
  public static final Property deviceHealth = newProperty(0, new BHealthData(), null);
  
  /**
   * Get the {@code deviceHealth} property.
   * @see #deviceHealth
   */
  public BHealthData getDeviceHealth() { return (BHealthData)get(deviceHealth); }
  
  /**
   * Set the {@code deviceHealth} property.
   * @see #deviceHealth
   */
  public void setDeviceHealth(BHealthData v) { set(deviceHealth, v, null); }

////////////////////////////////////////////////////////////////
// Property "historyHealth"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code historyHealth} property.
   * @see #getHistoryHealth
   * @see #setHistoryHealth
   */
  public static final Property historyHealth = newProperty(0, new BHealthData(), null);
  
  /**
   * Get the {@code historyHealth} property.
   * @see #historyHealth
   */
  public BHealthData getHistoryHealth() { return (BHealthData)get(historyHealth); }
  
  /**
   * Set the {@code historyHealth} property.
   * @see #historyHealth
   */
  public void setHistoryHealth(BHealthData v) { set(historyHealth, v, null); }

////////////////////////////////////////////////////////////////
// Action "subscribe"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code subscribe} action.
   * @see #subscribe()
   */
  public static final Action subscribe = newAction(0, null);
  
  /**
   * Invoke the {@code subscribe} action.
   * @see #subscribe
   */
  public void subscribe() { invoke(subscribe, null, null); }

////////////////////////////////////////////////////////////////
// Action "update"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code update} action.
   * @see #update()
   */
  public static final Action update = newAction(0, null);
  
  /**
   * Invoke the {@code update} action.
   * @see #update
   */
  public void update() { invoke(update, null, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BHeartbeatSubscriber.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public void doSubscribe(){
        String topicBase = "optimised_heartbeat/" + this.getHostId() + " (" + this.getStationName() + ")/devices";

        BMqttStringSubscriber deviceHealthSubscriber = this.resolveSubscriber("deviceHealthSubscriber");
        BMqttStringSubscriber historyHealthSubscriber = this.resolveSubscriber("historyHealthSubscriber");
        BMqttStringSubscriber deviceIdsSubscriber = this.resolveSubscriber("deviceIdsSubscriber");

        deviceHealthSubscriber.getProxyExt().doUnsubscribe();
        deviceHealthSubscriber.getProxyExt().setTopic(topicBase + "/deviceHealth");
        deviceHealthSubscriber.getProxyExt().doSubscribe();

        historyHealthSubscriber.getProxyExt().doUnsubscribe();
        historyHealthSubscriber.getProxyExt().setTopic(topicBase + "/historyHealth");
        historyHealthSubscriber.getProxyExt().doSubscribe();

        deviceIdsSubscriber.getProxyExt().doUnsubscribe();
        deviceIdsSubscriber.getProxyExt().setTopic(topicBase + "/deviceIds");
        deviceIdsSubscriber.getProxyExt().doSubscribe();
    }

    public BMqttStringSubscriber resolveSubscriber(String name){
        if(null != this.get(name)){
            return (BMqttStringSubscriber) this.get(name);
        } else {
            this.add(name, new BMqttStringSubscriber());
            return  (BMqttStringSubscriber)this.get(name);
        }
    }

    public void doUpdate(){
        BMqttStringSubscriber deviceHealthSubscriber = this.resolveSubscriber("deviceHealthSubscriber");
        BMqttStringSubscriber historyHealthSubscriber = this.resolveSubscriber("historyHealthSubscriber");

        BHealthData data = new BHealthData();

        this.setDeviceHealth(data.decodeString(deviceHealthSubscriber.getOut().getValue()));
        this.setHistoryHealth(data.decodeString(historyHealthSubscriber.getOut().getValue()));
    }

    public void onChanged(Property prop, Context cx){
        if(prop.equals(hostId) || prop.equals(stationName)){
            this.doSubscribe();
        }
    }



}
