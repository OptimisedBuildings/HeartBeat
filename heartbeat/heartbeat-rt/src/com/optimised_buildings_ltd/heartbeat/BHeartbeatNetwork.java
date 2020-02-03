package com.optimised_buildings_ltd.heartbeat;

import com.tridium.json.JSONException;
import com.tridium.json.JSONObject;
import com.tridium.ndriver.BNDevice;
import com.tridiumx.mqttClientDriver.BAbstractMqttDriverDevice;
import com.tridiumx.mqttClientDriver.BAbstractMqttDriverNetwork;
import com.tridiumx.mqttClientDriver.point.BMqttClientDriverPointDeviceExt;
import com.tridiumx.mqttClientDriver.proxyExt.publishers.BMqttStringObjectPublishExt;

import javax.baja.alarm.ext.BAlarmSourceExt;
import javax.baja.alarm.ext.fault.BStatusFaultAlgorithm;
import javax.baja.alarm.ext.offnormal.BStatusAlgorithm;
import javax.baja.collection.BITable;
import javax.baja.control.trigger.BIntervalTriggerMode;
import javax.baja.control.trigger.BTimeTrigger;
import javax.baja.control.trigger.BTriggerMode;
import javax.baja.driver.BDevice;
import javax.baja.driver.history.BHistoryImport;
import javax.baja.history.ext.BHistoryExt;
import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.status.BStatus;
import javax.baja.sys.*;
import java.util.ArrayList;
import java.util.logging.Logger;

@NiagaraType
@NiagaraProperty(
        name = "device",
        type = "BAbstractMqttDriverDevice",
        defaultValue = "new BAbstractMqttDriverDevice()"
)
@NiagaraAction(
        name = "beat",
        flags = Flags.ASYNC
)
@NiagaraProperty(
        name = "beatInterval",
        type = "BTimeInterval",
        defaultValue = "new BTimeInterval(BIntervalTriggerMode.make(BRelTime.makeMinutes(1)), \"beat\")"
)
@NiagaraAction(
        name = "addReceiver",
        parameterType = "BStationInfo",
        defaultValue = "new BStationInfo()"
)

public class BHeartbeatNetwork extends BAbstractMqttDriverNetwork {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimised_buildings_ltd.heartbeat.BHeartbeatNetwork(3217898891)1.0$ @*/
/* Generated Tue Jan 28 10:07:51 GMT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "device"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code device} property.
   * @see #getDevice
   * @see #setDevice
   */
  public static final Property device = newProperty(0, new BAbstractMqttDriverDevice(), null);
  
  /**
   * Get the {@code device} property.
   * @see #device
   */
  public BAbstractMqttDriverDevice getDevice() { return (BAbstractMqttDriverDevice)get(device); }
  
  /**
   * Set the {@code device} property.
   * @see #device
   */
  public void setDevice(BAbstractMqttDriverDevice v) { set(device, v, null); }

////////////////////////////////////////////////////////////////
// Property "beatInterval"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code beatInterval} property.
   * @see #getBeatInterval
   * @see #setBeatInterval
   */
  public static final Property beatInterval = newProperty(0, new BTimeInterval(BIntervalTriggerMode.make(BRelTime.makeMinutes(1)), "beat"), null);
  
  /**
   * Get the {@code beatInterval} property.
   * @see #beatInterval
   */
  public BTimeInterval getBeatInterval() { return (BTimeInterval)get(beatInterval); }
  
  /**
   * Set the {@code beatInterval} property.
   * @see #beatInterval
   */
  public void setBeatInterval(BTimeInterval v) { set(beatInterval, v, null); }

////////////////////////////////////////////////////////////////
// Action "beat"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code beat} action.
   * @see #beat()
   */
  public static final Action beat = newAction(Flags.ASYNC, null);
  
  /**
   * Invoke the {@code beat} action.
   * @see #beat
   */
  public void beat() { invoke(beat, null, null); }

////////////////////////////////////////////////////////////////
// Action "addReceiver"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code addReceiver} action.
   * @see #addReceiver(BStationInfo parameter)
   */
  public static final Action addReceiver = newAction(0, new BStationInfo(), null);
  
  /**
   * Invoke the {@code addReceiver} action.
   * @see #addReceiver
   */
  public void addReceiver(BStationInfo parameter) { invoke(addReceiver, parameter, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BHeartbeatNetwork.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  public BIcon getIcon() {
    BValue dynamic = this.get("icon");
    return dynamic instanceof BIcon ? (BIcon)dynamic : icon;
  }

  private static final BIcon icon = BIcon.make(BOrd.make("module://heartbeat/res/heartbeat.png"));



  private void runSetTopicAndPublish(String topic, String output){
      //This method is the cannon. Load it with a topic and output and it fires!
      //Includes a check to ensure barrel is empty before loading the next bullet.

      BHeartbeatBeatPoint beatPoint = (BHeartbeatBeatPoint)this.getDevice().getPoints().get("beat");
      beatPoint.getProxyExt().setTopic(topic);
      beatPoint.doSet(BString.make(output));

    BRelTime timeout = BRelTime.makeSeconds(5);
    BAbsTime expired = BAbsTime.now().add(timeout);
    //System.out.println(beatPoint.getOut().toString() + " | " + output);
    //System.out.println(BAbsTime.now() + " | " + expired);
    //System.out.println(beatPoint.getProxyExt().getTopic() + " | " + topic);
    while((!beatPoint.getOut().getValue().contains(output) || !beatPoint.getProxyExt().getTopic().contains(topic)) && BAbsTime.now().isBefore(expired)){
      logger.fine("Waiting (out) | " + beatPoint.getOut() + " | " + output);
      logger.fine("Waiting (topic) | " + beatPoint.getProxyExt().getTopic() + " | " + topic);
    }
    beatPoint.getProxyExt().doPublish();
  }

  public void doBeat(){
    BMqttClientDriverPointDeviceExt points = this.getDevice().getPoints();
    BComponent[] list = points.getChildComponents();
    boolean contains = false;
    for(BComponent item : list){
      if(item.getName().contains("beat")){
        contains = true;
      }
    }
    if(!contains){
      points.add("beat",new BHeartbeatBeatPoint());
    }

    this.runSetTopicAndPublish("optimised_heartbeat/" + Sys.getHostId() + " (" + Sys.getStation().getStationName() + ")/system_heartbeat",
            BAbsTime.now().encodeToString());
    this.runSetTopicAndPublish("optimised_heartbeat/" + Sys.getHostId() + " (" + Sys.getStation().getStationName() + ")/config",
            this.getBeatInterval().toString());
    this.broadcastDevices();

    BComponent[] subscribers = this.getDevice().getPoints().getChildComponents();
    for(BComponent subscriber : subscribers){
      if(subscriber.getType().toString().contains("HeartbeatSubscriber")){
        ((BHeartbeatSubscriber)subscriber).doUpdate();
      }
    }
  }


  private void broadcastDevices(){
    String bql = "station:|slot:/Drivers|neql:n:device|bql:select *";
    BITable result = (BITable)BOrd.make(bql).resolve().get();
    Cursor c = result.cursor();

    String topicBase = "optimised_heartbeat/" + Sys.getHostId() + " (" + Sys.getStation().getStationName() + ")/devices";
    int total = 0; int failed = 0; int successful = 0;
    int deviceTotal = 0; int down = 0; int up = 0;
    ArrayList<String> deviceIds = new ArrayList<>();
    while(c.next()){
      BDevice device = (BDevice)c.get();

      JSONObject transmission = new JSONObject();
      if(device.getHealth().getDown()) {
          down++;
        transmission.append("lastOkTime", device.getHealth().getLastOkTime());
      } else {
          up++;
        transmission.append("lastFailTime", device.getHealth().getLastFailTime());
      }
      deviceTotal++;
      transmission.append("health", device.getHealth());

      transmission.append("deviceName", device.getDisplayName(null));

      transmission.append("network", device.getNetwork().getDisplayName(null));
      JSONObject histories =  this.scanHistories(device);
      transmission.append("historyHealth", histories);
      try {
      total += histories.getJSONArray("total").getInt(0);
      failed += histories.getJSONArray("failed").getInt(0);
      successful += histories.getJSONArray("successful").getInt(0);
      } catch (JSONException e){
        logger.finest("object empty");
      }

      String topicDeviceName = device.getDisplayName(null);
      if(device.getType().toString().contains("Trend")) {
        topicDeviceName = device.getParent().getParent().getDisplayName(null) + "/" + device.getParent().getDisplayName(null) + "/" + topicDeviceName;
      }
      deviceIds.add(device.getNetwork() + "/" + topicDeviceName);
      this.runSetTopicAndPublish(topicBase + "/" + device.getNetwork() + "/" + topicDeviceName, transmission.toString());

    }

    this.runSetTopicAndPublish(topicBase + "/deviceIds", deviceIds.toString());

    JSONObject historyHealth = new JSONObject();

      historyHealth.append("failed", failed);
      historyHealth.append("successful", successful);
      historyHealth.append("total", total);
      historyHealth.append("percentage", total < 1 ? 0 : (100 * failed) / total);

    this.runSetTopicAndPublish(topicBase + "/historyHealth" , historyHealth.toString());

    JSONObject deviceHealth = new JSONObject();

      deviceHealth.append("down", down);
      deviceHealth.append("up", up);
      deviceHealth.append("total", deviceTotal);
      deviceHealth.append("percentage", deviceTotal < 1 ? 0 : (100 * down) / deviceTotal);

    this.runSetTopicAndPublish(topicBase + "/deviceHealth", deviceHealth.toString());

    BComplex haystack = (BComplex)BOrd.make("station:|slot:/Services/NHaystackService").resolve().get();
    if(!haystack.isNull()){
        JSONObject skysparkHealth = new JSONObject();
        BComplex stats = (BComplex)haystack.get("stats");
        skysparkHealth.append("points", stats.get("numPoints"));
        skysparkHealth.append("status", haystack.get("status"));
        skysparkHealth.append("enabled", haystack.get("enabled"));
        this.runSetTopicAndPublish(topicBase + "/nHaystackHealth", skysparkHealth.toString());
    }
  }

  private JSONObject scanHistories(BDevice device){
    String neql = "station:|" + device.getSlotPath() + "|neql:n:parent->n:displayName = 'Histories' and n:parent->n:parent->n:device";
    logger.info(neql);
    BITable result = (BITable)BOrd.make(neql).resolve().get();
    Cursor c = result.cursor();
    int total = 0; int failed = 0; int successful = 0;
    while(c.next()){
        try{
          BHistoryImport ext = (BHistoryImport)c.get();
          if(ext.isNull()){
            continue;
          }
          total++;
          if(ext.getStatus().toString().contains("fault")){
            failed++;
          } else if(ext.getStatus().toString().contains("ok")){
            successful++;
          }
        } catch (Exception e){
          logger.fine(e.toString());
        }
    }
    JSONObject historyData = new JSONObject();
    if(total <= 0){return historyData;}
    historyData.append("failed", failed);
    historyData.append("successful", successful);
    historyData.append("total", total);
    historyData.append("percentage", (100 * failed)/total);
    return historyData;
  }

  public void doAddReceiver(BStationInfo info){
    //TODO FIND A WAY OF DETECTING THE CONFIG AND USE IT FOR SETUP
    BMqttClientDriverPointDeviceExt points = this.getDevice().getPoints();
    String subName = info.getHostId().replaceAll("-","\\$2d") + "$20$28" + info.getStationName() +
            "$29";
    points.add(subName, new BHeartbeatSubscriber());
    BHeartbeatSubscriber sub = (BHeartbeatSubscriber) points.get(subName);
    sub.setHostId(info.getHostId());
    sub.setStationName(info.getStationName());
    sub.doSubscribe();
    /*
        OUTDATED METHOD


      points.add(hostId.getString().replaceAll("-","\\$2d"),new BHeartbeatBeatReceiver());
      BHeartbeatBeatReceiver newPoint = (BHeartbeatBeatReceiver) points.get(hostId.getString().replaceAll("-","\\$2d"));
      newPoint.getProxyExt().setTopic("optimised_heartbeat/" + hostId.getString() + "/system_heartbeat");
      newPoint.getProxyExt().doSubscribe();
      newPoint.add("StatusAlarmExt", new BAlarmSourceExt());
      BAlarmSourceExt alarmExt = (BAlarmSourceExt) newPoint.get("StatusAlarmExt");
      alarmExt.setFaultAlgorithm(new BStatusFaultAlgorithm());
      alarmExt.setOffnormalAlgorithm(new BStatusAlgorithm());
    ((BStatusAlgorithm)alarmExt.getOffnormalAlgorithm()).setAlarmValues(BStatus.make(136)); //Unacked Alarm and Alarm

     */
  }

  private Logger logger = Logger.getLogger("ob.heartbeat");

}
