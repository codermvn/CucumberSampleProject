
package com.lh.test.model.OBR;

import java.io.Serializable;
import java.util.HashMap;

import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.lh.test.model.Frame;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "tiger_record_id",
    "fare_class_code",
    "requested_relative_discount",
    "requested_first_travel",
    "requested_last_travel",
    "frame"
})
public class Obr implements Serializable {

    @JsonProperty("tiger_record_id")
    private Double tigerRecordId;
    @JsonProperty("fare_class_code")
    private String fareClassCode;
    @JsonProperty("requested_relative_discount")
    private Integer requestedRelativeDiscount;
    @JsonProperty("requested_first_travel")
    private String requestedFirstTravel;
    @JsonProperty("requested_last_travel")
    private String requestedLastTravel;
    @JsonProperty("frame")
    private Frame frame;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("tiger_record_id")
    public Double getTigerRecordId() {
        return tigerRecordId;
    }

    @JsonProperty("tiger_record_id")
    public void setTigerRecordId(Double tigerRecordId) {
        this.tigerRecordId = tigerRecordId;
    }

    @JsonProperty("fare_class_code")
    public String getFareClassCode() {
        return fareClassCode;
    }

    @JsonProperty("fare_class_code")
    public void setFareClassCode(String fareClassCode) {
        this.fareClassCode = fareClassCode;
    }

    @JsonProperty("requested_relative_discount")
    public Integer getRequestedRelativeDiscount() {
        return requestedRelativeDiscount;
    }

    @JsonProperty("requested_relative_discount")
    public void setRequestedRelativeDiscount(Integer requestedRelativeDiscount) {
        this.requestedRelativeDiscount = requestedRelativeDiscount;
    }

    @JsonProperty("requested_first_travel")
    public String getRequestedFirstTravel() {
        return requestedFirstTravel;
    }

    @JsonProperty("requested_first_travel")
    public void setRequestedFirstTravel(String requestedFirstTravel) {
        this.requestedFirstTravel = requestedFirstTravel;
    }

    @JsonProperty("requested_last_travel")
    public String getRequestedLastTravel() {
        return requestedLastTravel;
    }

    @JsonProperty("requested_last_travel")
    public void setRequestedLastTravel(String requestedLastTravel) {
        this.requestedLastTravel = requestedLastTravel;
    }

    @JsonProperty("frame")
    public Frame getFrame() {
        return frame;
    }

    @JsonProperty("frame")
    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("tigerRecordId", tigerRecordId).append("fareClassCode", fareClassCode).append("requestedRelativeDiscount", requestedRelativeDiscount).append("requestedFirstTravel", requestedFirstTravel).append("requestedLastTravel", requestedLastTravel).append("frame", frame).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(requestedLastTravel).append(frame).append(fareClassCode).append(additionalProperties).append(requestedRelativeDiscount).append(requestedFirstTravel).append(tigerRecordId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Obr) == false) {
            return false;
        }
        Obr rhs = ((Obr) other);
        return new EqualsBuilder().append(requestedLastTravel, rhs.requestedLastTravel).append(frame, rhs.frame).append(fareClassCode, rhs.fareClassCode).append(additionalProperties, rhs.additionalProperties).append(requestedRelativeDiscount, rhs.requestedRelativeDiscount).append(requestedFirstTravel, rhs.requestedFirstTravel).append(tigerRecordId, rhs.tigerRecordId).isEquals();
    }

}
