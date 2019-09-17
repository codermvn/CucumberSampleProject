
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
    "fd_point_of_sale_type",
    "fd_point_of_sale",
    "fd_sub_category",
    "fd_filing_method",
    "bd_carrier_code",
    "bd_source",
    "bd_compartment",
    "bd_wild_card_fare_class_code",
    "bd_oneway_roundtrip_indicator",
    "bd_tariff_code",
    "bd_fare_class_code",
    "bd_maximum_relative_corporate_discount",
    "rfd_carrier_code",
    "rfd_booking_class",
    "rfd_routing_code",
    "rfd_fare_amount",
    "rfd_rule_code",
    "tv_travel_from",
    "tv_travel_to",
    "fv_frame_valid_from",
    "fv_frame_valid_to",
    "location"
})
public class Frame implements Serializable{

    @JsonProperty("fd_point_of_sale_type")
    private String fdPointOfSaleType;
    @JsonProperty("fd_point_of_sale")
    private String fdPointOfSale;
    @JsonProperty("fd_sub_category")
    private String fdSubCategory;
    @JsonProperty("fd_filing_method")
    private String fdFilingMethod;
    @JsonProperty("bd_carrier_code")
    private String bdCarrierCode;
    @JsonProperty("bd_source")
    private String bdSource;
    @JsonProperty("bd_compartment")
    private String bdCompartment;
    @JsonProperty("bd_wild_card_fare_class_code")
    private String bdWildCardFareClassCode;
    @JsonProperty("bd_oneway_roundtrip_indicator")
    private String bdOnewayRoundtripIndicator;
    @JsonProperty("bd_tariff_code")
    private String bdTariffCode;
    @JsonProperty("bd_fare_class_code")
    private String bdFareClassCode;
    @JsonProperty("bd_maximum_relative_corporate_discount")
    private Integer bdMaximumRelativeCorporateDiscount;
    @JsonProperty("rfd_carrier_code")
    private String rfdCarrierCode;
    @JsonProperty("rfd_booking_class")
    private String rfdBookingClass;
    @JsonProperty("rfd_routing_code")
    private String rfdRoutingCode;
    @JsonProperty("rfd_fare_amount")
    private Integer rfdFareAmount;
    @JsonProperty("rfd_rule_code")
    private String rfdRuleCode;
    @JsonProperty("tv_travel_from")
    private String tvTravelFrom;
    @JsonProperty("tv_travel_to")
    private String tvTravelTo;
    @JsonProperty("fv_frame_valid_from")
    private String fvFrameValidFrom;
    @JsonProperty("fv_frame_valid_to")
    private String fvFrameValidTo;
    @JsonProperty("location")
    private Location location;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("fd_point_of_sale_type")
    public String getFdPointOfSaleType() {
        return fdPointOfSaleType;
    }

    @JsonProperty("fd_point_of_sale_type")
    public void setFdPointOfSaleType(String fdPointOfSaleType) {
        this.fdPointOfSaleType = fdPointOfSaleType;
    }

    @JsonProperty("fd_point_of_sale")
    public String getFdPointOfSale() {
        return fdPointOfSale;
    }

    @JsonProperty("fd_point_of_sale")
    public void setFdPointOfSale(String fdPointOfSale) {
        this.fdPointOfSale = fdPointOfSale;
    }

    @JsonProperty("fd_sub_category")
    public String getFdSubCategory() {
        return fdSubCategory;
    }

    @JsonProperty("fd_sub_category")
    public void setFdSubCategory(String fdSubCategory) {
        this.fdSubCategory = fdSubCategory;
    }

    @JsonProperty("fd_filing_method")
    public String getFdFilingMethod() {
        return fdFilingMethod;
    }

    @JsonProperty("fd_filing_method")
    public void setFdFilingMethod(String fdFilingMethod) {
        this.fdFilingMethod = fdFilingMethod;
    }

    @JsonProperty("bd_carrier_code")
    public String getBdCarrierCode() {
        return bdCarrierCode;
    }

    @JsonProperty("bd_carrier_code")
    public void setBdCarrierCode(String bdCarrierCode) {
        this.bdCarrierCode = bdCarrierCode;
    }

    @JsonProperty("bd_source")
    public String getBdSource() {
        return bdSource;
    }

    @JsonProperty("bd_source")
    public void setBdSource(String bdSource) {
        this.bdSource = bdSource;
    }

    @JsonProperty("bd_compartment")
    public String getBdCompartment() {
        return bdCompartment;
    }

    @JsonProperty("bd_compartment")
    public void setBdCompartment(String bdCompartment) {
        this.bdCompartment = bdCompartment;
    }

    @JsonProperty("bd_wild_card_fare_class_code")
    public String getBdWildCardFareClassCode() {
        return bdWildCardFareClassCode;
    }

    @JsonProperty("bd_wild_card_fare_class_code")
    public void setBdWildCardFareClassCode(String bdWildCardFareClassCode) {
        this.bdWildCardFareClassCode = bdWildCardFareClassCode;
    }

    @JsonProperty("bd_oneway_roundtrip_indicator")
    public String getBdOnewayRoundtripIndicator() {
        return bdOnewayRoundtripIndicator;
    }

    @JsonProperty("bd_oneway_roundtrip_indicator")
    public void setBdOnewayRoundtripIndicator(String bdOnewayRoundtripIndicator) {
        this.bdOnewayRoundtripIndicator = bdOnewayRoundtripIndicator;
    }

    @JsonProperty("bd_tariff_code")
    public String getBdTariffCode() {
        return bdTariffCode;
    }

    @JsonProperty("bd_tariff_code")
    public void setBdTariffCode(String bdTariffCode) {
        this.bdTariffCode = bdTariffCode;
    }

    @JsonProperty("bd_fare_class_code")
    public String getBdFareClassCode() {
        return bdFareClassCode;
    }

    @JsonProperty("bd_fare_class_code")
    public void setBdFareClassCode(String bdFareClassCode) {
        this.bdFareClassCode = bdFareClassCode;
    }

    @JsonProperty("bd_maximum_relative_corporate_discount")
    public Integer getBdMaximumRelativeCorporateDiscount() {
        return bdMaximumRelativeCorporateDiscount;
    }

    @JsonProperty("bd_maximum_relative_corporate_discount")
    public void setBdMaximumRelativeCorporateDiscount(Integer bdMaximumRelativeCorporateDiscount) {
        this.bdMaximumRelativeCorporateDiscount = bdMaximumRelativeCorporateDiscount;
    }

    @JsonProperty("rfd_carrier_code")
    public String getRfdCarrierCode() {
        return rfdCarrierCode;
    }

    @JsonProperty("rfd_carrier_code")
    public void setRfdCarrierCode(String rfdCarrierCode) {
        this.rfdCarrierCode = rfdCarrierCode;
    }

    @JsonProperty("rfd_booking_class")
    public String getRfdBookingClass() {
        return rfdBookingClass;
    }

    @JsonProperty("rfd_booking_class")
    public void setRfdBookingClass(String rfdBookingClass) {
        this.rfdBookingClass = rfdBookingClass;
    }

    @JsonProperty("rfd_routing_code")
    public String getRfdRoutingCode() {
        return rfdRoutingCode;
    }

    @JsonProperty("rfd_routing_code")
    public void setRfdRoutingCode(String rfdRoutingCode) {
        this.rfdRoutingCode = rfdRoutingCode;
    }

    @JsonProperty("rfd_fare_amount")
    public Integer getRfdFareAmount() {
        return rfdFareAmount;
    }

    @JsonProperty("rfd_fare_amount")
    public void setRfdFareAmount(Integer rfdFareAmount) {
        this.rfdFareAmount = rfdFareAmount;
    }

    @JsonProperty("rfd_rule_code")
    public String getRfdRuleCode() {
        return rfdRuleCode;
    }

    @JsonProperty("rfd_rule_code")
    public void setRfdRuleCode(String rfdRuleCode) {
        this.rfdRuleCode = rfdRuleCode;
    }

    @JsonProperty("tv_travel_from")
    public String getTvTravelFrom() {
        return tvTravelFrom;
    }

    @JsonProperty("tv_travel_from")
    public void setTvTravelFrom(String tvTravelFrom) {
        this.tvTravelFrom = tvTravelFrom;
    }

    @JsonProperty("tv_travel_to")
    public String getTvTravelTo() {
        return tvTravelTo;
    }

    @JsonProperty("tv_travel_to")
    public void setTvTravelTo(String tvTravelTo) {
        this.tvTravelTo = tvTravelTo;
    }

    @JsonProperty("fv_frame_valid_from")
    public String getFvFrameValidFrom() {
        return fvFrameValidFrom;
    }

    @JsonProperty("fv_frame_valid_from")
    public void setFvFrameValidFrom(String fvFrameValidFrom) {
        this.fvFrameValidFrom = fvFrameValidFrom;
    }

    @JsonProperty("fv_frame_valid_to")
    public String getFvFrameValidTo() {
        return fvFrameValidTo;
    }

    @JsonProperty("fv_frame_valid_to")
    public void setFvFrameValidTo(String fvFrameValidTo) {
        this.fvFrameValidTo = fvFrameValidTo;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
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
        return new ToStringBuilder(this).append("fdPointOfSaleType", fdPointOfSaleType).append("fdPointOfSale", fdPointOfSale).append("fdSubCategory", fdSubCategory).append("fdFilingMethod", fdFilingMethod).append("bdCarrierCode", bdCarrierCode).append("bdSource", bdSource).append("bdCompartment", bdCompartment).append("bdWildCardFareClassCode", bdWildCardFareClassCode).append("bdOnewayRoundtripIndicator", bdOnewayRoundtripIndicator).append("bdTariffCode", bdTariffCode).append("bdFareClassCode", bdFareClassCode).append("bdMaximumRelativeCorporateDiscount", bdMaximumRelativeCorporateDiscount).append("rfdCarrierCode", rfdCarrierCode).append("rfdBookingClass", rfdBookingClass).append("rfdRoutingCode", rfdRoutingCode).append("rfdFareAmount", rfdFareAmount).append("rfdRuleCode", rfdRuleCode).append("tvTravelFrom", tvTravelFrom).append("tvTravelTo", tvTravelTo).append("fvFrameValidFrom", fvFrameValidFrom).append("fvFrameValidTo", fvFrameValidTo).append("location", location).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(bdCarrierCode).append(fdPointOfSale).append(bdFareClassCode).append(bdWildCardFareClassCode).append(tvTravelTo).append(location).append(fvFrameValidTo).append(fdPointOfSaleType).append(fvFrameValidFrom).append(bdTariffCode).append(rfdRoutingCode).append(fdFilingMethod).append(bdMaximumRelativeCorporateDiscount).append(bdOnewayRoundtripIndicator).append(bdSource).append(bdCompartment).append(tvTravelFrom).append(additionalProperties).append(rfdFareAmount).append(rfdRuleCode).append(rfdBookingClass).append(rfdCarrierCode).append(fdSubCategory).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Frame) == false) {
            return false;
        }
        Frame rhs = ((Frame) other);
        return new EqualsBuilder().append(bdCarrierCode, rhs.bdCarrierCode).append(fdPointOfSale, rhs.fdPointOfSale).append(bdFareClassCode, rhs.bdFareClassCode).append(bdWildCardFareClassCode, rhs.bdWildCardFareClassCode).append(tvTravelTo, rhs.tvTravelTo).append(location, rhs.location).append(fvFrameValidTo, rhs.fvFrameValidTo).append(fdPointOfSaleType, rhs.fdPointOfSaleType).append(fvFrameValidFrom, rhs.fvFrameValidFrom).append(bdTariffCode, rhs.bdTariffCode).append(rfdRoutingCode, rhs.rfdRoutingCode).append(fdFilingMethod, rhs.fdFilingMethod).append(bdMaximumRelativeCorporateDiscount, rhs.bdMaximumRelativeCorporateDiscount).append(bdOnewayRoundtripIndicator, rhs.bdOnewayRoundtripIndicator).append(bdSource, rhs.bdSource).append(bdCompartment, rhs.bdCompartment).append(tvTravelFrom, rhs.tvTravelFrom).append(additionalProperties, rhs.additionalProperties).append(rfdFareAmount, rhs.rfdFareAmount).append(rfdRuleCode, rhs.rfdRuleCode).append(rfdBookingClass, rhs.rfdBookingClass).append(rfdCarrierCode, rhs.rfdCarrierCode).append(fdSubCategory, rhs.fdSubCategory).isEquals();
    }

}
