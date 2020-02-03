package com.optimised_buildings_ltd.heartbeat;

import com.tridium.json.JSONObject;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.*;

@NiagaraType
@NiagaraProperty(
        name = "total",
        type = "BInteger",
        defaultValue = "BInteger.make(0)"
)
@NiagaraProperty(
        name = "good",
        type = "BInteger",
        defaultValue = "BInteger.make(0)"
)
@NiagaraProperty(
        name = "bad",
        type = "BInteger",
        defaultValue = "BInteger.make(0)"
)
@NiagaraProperty(
        name = "percentage",
        type = "BInteger",
        defaultValue = "BInteger.make(0)"
)

public class BHealthData extends BComponent {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimised_buildings_ltd.heartbeat.BHealthData(3926737848)1.0$ @*/
/* Generated Tue Jan 28 14:38:07 GMT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "total"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code total} property.
   * @see #getTotal
   * @see #setTotal
   */
  public static final Property total = newProperty(0, ((BInteger)(BInteger.make(0))).getInt(), null);
  
  /**
   * Get the {@code total} property.
   * @see #total
   */
  public int getTotal() { return getInt(total); }
  
  /**
   * Set the {@code total} property.
   * @see #total
   */
  public void setTotal(int v) { setInt(total, v, null); }

////////////////////////////////////////////////////////////////
// Property "good"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code good} property.
   * @see #getGood
   * @see #setGood
   */
  public static final Property good = newProperty(0, ((BInteger)(BInteger.make(0))).getInt(), null);
  
  /**
   * Get the {@code good} property.
   * @see #good
   */
  public int getGood() { return getInt(good); }
  
  /**
   * Set the {@code good} property.
   * @see #good
   */
  public void setGood(int v) { setInt(good, v, null); }

////////////////////////////////////////////////////////////////
// Property "bad"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code bad} property.
   * @see #getBad
   * @see #setBad
   */
  public static final Property bad = newProperty(0, ((BInteger)(BInteger.make(0))).getInt(), null);
  
  /**
   * Get the {@code bad} property.
   * @see #bad
   */
  public int getBad() { return getInt(bad); }
  
  /**
   * Set the {@code bad} property.
   * @see #bad
   */
  public void setBad(int v) { setInt(bad, v, null); }

////////////////////////////////////////////////////////////////
// Property "percentage"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code percentage} property.
   * @see #getPercentage
   * @see #setPercentage
   */
  public static final Property percentage = newProperty(0, ((BInteger)(BInteger.make(0))).getInt(), null);
  
  /**
   * Get the {@code percentage} property.
   * @see #percentage
   */
  public int getPercentage() { return getInt(percentage); }
  
  /**
   * Set the {@code percentage} property.
   * @see #percentage
   */
  public void setPercentage(int v) { setInt(percentage, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BHealthData.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public BHealthData decodeString(String input){
        BHealthData healthData = new BHealthData();
        healthData.setTotal(this.findValue("total", input));
        healthData.setPercentage(this.findValue("percentage", input));
        if(input.contains("up")){
            healthData.setGood(this.findValue("up", input));
            healthData.setBad(this.findValue("down", input));
        } else {
            healthData.setGood(this.findValue("successful", input));
            healthData.setBad(this.findValue("failed", input));
        }
        return healthData;
    }

    private int findValue(String key, String data){
        int index = data.indexOf(key) + key.length();
        String number = data.substring(data.indexOf("[",index) + 1, data.indexOf("]",index));
        return Integer.parseInt(number);
    }
}
