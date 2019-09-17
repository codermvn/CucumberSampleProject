package com.lh.test.model.OBR;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "obr_request_id",
    "obr_ids"
})
public class OBRResponse implements Serializable {

    @JsonProperty("obr_request_id")
    private Double obrRequestId;
    @JsonProperty("obr_ids")
    private List<Integer> obrIds = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("obr_request_id")
    public Double getObrRequestId() {
        return obrRequestId;
    }

    @JsonProperty("obr_request_id")
    public void setObrRequestId(Double obrRequestId) {
        this.obrRequestId = obrRequestId;
    }

    @JsonProperty("obr_ids")
    public List<Integer> getObrIds() {
        return obrIds;
    }

    @JsonProperty("obr_ids")
    public void setObrIds(List<Integer> obrIds) {
        this.obrIds = obrIds;
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
        return new ToStringBuilder(this).append("obrRequestId", obrRequestId).append("obrIds", obrIds).append("additionalProperties", additionalProperties).toString();
    }

}