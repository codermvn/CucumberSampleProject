
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
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "obr_request_id",
    "tiger_contract_number",
    "unique_customer_id",
    "customer_name",
    "user_name",
    "real_name",
    "user_email",
    "justification_date_time",
    "justification_text",
    "attachment_1",
    "attachment_1_name",
    "attachment_2",
    "attachment_2_name",
    "obrs"
})
public class OBRRequest implements Serializable{

    @JsonProperty("obr_request_id")
    private Double obrRequestId;
    @JsonProperty("tiger_contract_number")
    private String tigerContractNumber;
    @JsonProperty("unique_customer_id")
    private String uniqueCustomerId;
    @JsonProperty("customer_name")
    private String customerName;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("real_name")
    private String realName;
    @JsonProperty("user_email")
    private String userEmail;
    @JsonProperty("justification_date_time")
    private String justificationDateTime;
    @JsonProperty("justification_text")
    private String justificationText;
    @JsonProperty("attachment_1")
    private String attachment1;
    @JsonProperty("attachment_1_name")
    private String attachment1Name;
    @JsonProperty("attachment_2")
    private String attachment2;
    @JsonProperty("attachment_2_name")
    private String attachment2Name;
    @JsonProperty("obrs")
    private List<Obr> obrs = null;
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

    @JsonProperty("tiger_contract_number")
    public String getTigerContractNumber() {
        return tigerContractNumber;
    }

    @JsonProperty("tiger_contract_number")
    public void setTigerContractNumber(String tigerContractNumber) {
        this.tigerContractNumber = tigerContractNumber;
    }

    @JsonProperty("unique_customer_id")
    public String getUniqueCustomerId() {
        return uniqueCustomerId;
    }

    @JsonProperty("unique_customer_id")
    public void setUniqueCustomerId(String uniqueCustomerId) {
        this.uniqueCustomerId = uniqueCustomerId;
    }

    @JsonProperty("customer_name")
    public String getCustomerName() {
        return customerName;
    }

    @JsonProperty("customer_name")
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @JsonProperty("user_name")
    public String getUserName() {
        return userName;
    }

    @JsonProperty("user_name")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonProperty("real_name")
    public String getRealName() {
        return realName;
    }

    @JsonProperty("real_name")
    public void setRealName(String realName) {
        this.realName = realName;
    }

    @JsonProperty("user_email")
    public String getUserEmail() {
        return userEmail;
    }

    @JsonProperty("user_email")
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @JsonProperty("justification_date_time")
    public String getJustificationDateTime() {
        return justificationDateTime;
    }

    @JsonProperty("justification_date_time")
    public void setJustificationDateTime(String justificationDateTime) {
        this.justificationDateTime = justificationDateTime;
    }

    @JsonProperty("justification_text")
    public String getJustificationText() {
        return justificationText;
    }

    @JsonProperty("justification_text")
    public void setJustificationText(String justificationText) {
        this.justificationText = justificationText;
    }

    @JsonProperty("attachment_1")
    public String getAttachment1() {
        return attachment1;
    }

    @JsonProperty("attachment_1")
    public void setAttachment1(String attachment1) {
        this.attachment1 = attachment1;
    }

    @JsonProperty("attachment_1_name")
    public String getAttachment1Name() {
        return attachment1Name;
    }

    @JsonProperty("attachment_1_name")
    public void setAttachment1Name(String attachment1Name) {
        this.attachment1Name = attachment1Name;
    }

    @JsonProperty("attachment_2")
    public String getAttachment2() {
        return attachment2;
    }

    @JsonProperty("attachment_2")
    public void setAttachment2(String attachment2) {
        this.attachment2 = attachment2;
    }

    @JsonProperty("attachment_2_name")
    public String getAttachment2Name() {
        return attachment2Name;
    }

    @JsonProperty("attachment_2_name")
    public void setAttachment2Name(String attachment2Name) {
        this.attachment2Name = attachment2Name;
    }

    @JsonProperty("obrs")
    public List<Obr> getObrs() {
        return obrs;
    }

    @JsonProperty("obrs")
    public void setObrs(List<Obr> obrs) {
        this.obrs = obrs;
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
        return new ToStringBuilder(this).append("obrRequestId", obrRequestId).append("tigerContractNumber", tigerContractNumber).append("uniqueCustomerId", uniqueCustomerId).append("customerName", customerName).append("userName", userName).append("realName", realName).append("userEmail", userEmail).append("justificationDateTime", justificationDateTime).append("justificationText", justificationText).append("attachment1", attachment1).append("attachment1Name", attachment1Name).append("attachment2", attachment2).append("attachment2Name", attachment2Name).append("obrs", obrs).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(attachment1Name).append(customerName).append(justificationDateTime).append(attachment2).append(attachment1).append(userEmail).append(obrs).append(uniqueCustomerId).append(obrRequestId).append(justificationText).append(additionalProperties).append(attachment2Name).append(userName).append(realName).append(tigerContractNumber).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OBRRequest) == false) {
            return false;
        }
        OBRRequest rhs = ((OBRRequest) other);
        return new EqualsBuilder().append(attachment1Name, rhs.attachment1Name).append(customerName, rhs.customerName).append(justificationDateTime, rhs.justificationDateTime).append(attachment2, rhs.attachment2).append(attachment1, rhs.attachment1).append(userEmail, rhs.userEmail).append(obrs, rhs.obrs).append(uniqueCustomerId, rhs.uniqueCustomerId).append(obrRequestId, rhs.obrRequestId).append(justificationText, rhs.justificationText).append(additionalProperties, rhs.additionalProperties).append(attachment2Name, rhs.attachment2Name).append(userName, rhs.userName).append(realName, rhs.realName).append(tigerContractNumber, rhs.tigerContractNumber).isEquals();
    }

}
