package com.optimised_buildings_ltd.heartbeat;

import com.tridiumx.mqttClientDriver.proxyExt.publishers.BMqttStringObjectPublishExt;
import com.tridiumx.mqttClientDriver.proxyExt.subscribers.BMqttStringObjectSubscribeExt;

import javax.baja.control.BNumericWritable;
import javax.baja.control.BStringWritable;
import javax.baja.control.trigger.BIntervalTriggerMode;
import javax.baja.control.trigger.BTimeTrigger;
import javax.baja.driver.point.BPointDeviceExt;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.space.BISpaceNode;
import javax.baja.status.BStatus;
import javax.baja.sys.*;

@NiagaraType
@NiagaraProperty(
        name = "proxyExt",
        type = "BMqttStringObjectSubscribeExt",
        defaultValue = "new BMqttStringObjectSubscribeExt()"
)
@NiagaraProperty(
        name = "expectedRate",
        type = "BTimeInterval",
        defaultValue = "new BTimeInterval(BIntervalTriggerMode.make(BRelTime.makeMinutes(1)), \"check\")"
)
@NiagaraAction(
        name = "check"
)
@NiagaraProperty(
        name = "missedMessageLimit",
        type = "BInteger",
        defaultValue = "BInteger.make(3)"
)
@NiagaraProperty(
        name = "debug",
        type = "BString",
        defaultValue = "BString.make(\"\")",
        flags = Flags.HIDDEN
)
@NiagaraAction(
        name = "loadConfig",
        flags = Flags.ASYNC
)

public class BHeartbeatBeatReceiver extends BStringWritable {


/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimised_buildings_ltd.heartbeat.BHeartbeatBeatReceiver(1404815605)1.0$ @*/
/* Generated Fri Jan 17 14:45:17 GMT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

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
// Property "expectedRate"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code expectedRate} property.
   * @see #getExpectedRate
   * @see #setExpectedRate
   */
  public static final Property expectedRate = newProperty(0, new BTimeInterval(BIntervalTriggerMode.make(BRelTime.makeMinutes(1)), "check"), null);
  
  /**
   * Get the {@code expectedRate} property.
   * @see #expectedRate
   */
  public BTimeInterval getExpectedRate() { return (BTimeInterval)get(expectedRate); }
  
  /**
   * Set the {@code expectedRate} property.
   * @see #expectedRate
   */
  public void setExpectedRate(BTimeInterval v) { set(expectedRate, v, null); }

////////////////////////////////////////////////////////////////
// Property "missedMessageLimit"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code missedMessageLimit} property.
   * @see #getMissedMessageLimit
   * @see #setMissedMessageLimit
   */
  public static final Property missedMessageLimit = newProperty(0, ((BInteger)(BInteger.make(3))).getInt(), null);
  
  /**
   * Get the {@code missedMessageLimit} property.
   * @see #missedMessageLimit
   */
  public int getMissedMessageLimit() { return getInt(missedMessageLimit); }
  
  /**
   * Set the {@code missedMessageLimit} property.
   * @see #missedMessageLimit
   */
  public void setMissedMessageLimit(int v) { setInt(missedMessageLimit, v, null); }

////////////////////////////////////////////////////////////////
// Property "debug"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code debug} property.
   * @see #getDebug
   * @see #setDebug
   */
  public static final Property debug = newProperty(Flags.HIDDEN, BString.make(""), null);
  
  /**
   * Get the {@code debug} property.
   * @see #debug
   */
  public String getDebug() { return getString(debug); }
  
  /**
   * Set the {@code debug} property.
   * @see #debug
   */
  public void setDebug(String v) { setString(debug, v, null); }

////////////////////////////////////////////////////////////////
// Action "check"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code check} action.
   * @see #check()
   */
  public static final Action check = newAction(0, null);
  
  /**
   * Invoke the {@code check} action.
   * @see #check
   */
  public void check() { invoke(check, null, null); }

////////////////////////////////////////////////////////////////
// Action "loadConfig"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code loadConfig} action.
   * @see #loadConfig()
   */
  public static final Action loadConfig = newAction(Flags.ASYNC, null);
  
  /**
   * Invoke the {@code loadConfig} action.
   * @see #loadConfig
   */
  public void loadConfig() { invoke(loadConfig, null, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BHeartbeatBeatReceiver.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  public void doCheck() {


      BAbsTime lastBeat = BAbsTime.make(0);
      try {
        lastBeat = BAbsTime.make(this.getOut().getValue());
      } catch (Exception e) {
        System.out.println(e.toString());
      }
      BRelTime timeElapsed = lastBeat.delta(BAbsTime.now());
      boolean alarm = false;
      if (timeElapsed.compareTo(BRelTime.makeSeconds(((BIntervalTriggerMode) this.getExpectedRate().getTriggerMode()).getInterval().getSeconds() * this.getMissedMessageLimit())) >= 0) {
        this.getProxyExt().setStatus(BStatus.alarm);
        alarm = true;
      } else {
        this.getProxyExt().setStatus(BStatus.ok);
        alarm = false;

      }
      this.setDebug(BAbsTime.now() + " | alarmState : " + alarm + " | elapsedTime : " + timeElapsed + " | calculatedTime : " + BRelTime.makeSeconds(((BIntervalTriggerMode) this.getExpectedRate().getTriggerMode()).getInterval().getSeconds() * this.getMissedMessageLimit()));

  }

  public void doLoadConfig(){

  }
}
