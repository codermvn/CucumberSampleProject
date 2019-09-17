
package com.lh.test.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "origin_code",
    "origin_type",
    "destination_code",
    "destination_type"
})
public class Location implements Serializable {

    @JsonProperty("origin_code")
    private String originCode;
    @JsonProperty("origin_type")
    private String originType;
    @JsonProperty("destination_code")
    private String destinationCode;
    @JsonProperty("destination_type")
    private String destinationType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("origin_code")
    public String getOriginCode() {
        return originCode;
    }

    @JsonProperty("origin_code")
    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    @JsonProperty("origin_type")
    public String getOriginType() {
        return originType;
    }

    @JsonProperty("origin_type")
    public void setOriginType(String originType) {
        this.originType = originType;
    }

    @JsonProperty("destination_code")
    public String getDestinationCode() {
        return destinationCode;
    }

    @JsonProperty("destination_code")
    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    @JsonProperty("destination_type")
    public String getDestinationType() {
        return destinationType;
    }

    @JsonProperty("destination_type")
    public void setDestinationType(String destinationType) {
        this.destinationType = destinationType;
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
        return new ToStringBuilder(this).append("originCode", originCode).append("originType", originType).append("destinationCode", destinationCode).append("destinationType", destinationType).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(destinationCode).append(destinationType).append(additionalProperties).append(originCode).append(originType).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Location) == false) {
            return false;
        }
        Location rhs = ((Location) other);
        return new EqualsBuilder().append(destinationCode, rhs.destinationCode).append(destinationType, rhs.destinationType).append(additionalProperties, rhs.additionalProperties).append(originCode, rhs.originCode).append(originType, rhs.originType).isEquals();
    }

}
