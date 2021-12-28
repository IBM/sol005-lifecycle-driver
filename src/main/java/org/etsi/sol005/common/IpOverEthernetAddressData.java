package org.etsi.sol005.common;

import java.util.List;

import org.etsi.sol005.lifecyclemanagement.SubnetIpRange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents network address data for IP over Ethernet.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents network address data for IP over Ethernet.")
public class IpOverEthernetAddressData {

    @ApiModelProperty(name = "MAC Address", notes = "MAC address. If this attribute is not present, it shall be chosen by the VIM.")
    private String macAddress;
    @ApiModelProperty(name = "IP Addresses", required = true, notes = "List of IP addresses to assign to the CP instance. If this attribute is not present, no IP address shall be assigned.")
    private IpAddress ipAddresses;

    /**
     * Represents IP address data for fixed or dynamic IP address assignment per subnet.
     */
    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModel(description = "Represents IP address data for fixed or dynamic IP address assignment per subnet.")
    public static class IpAddress {

        @ApiModelProperty(name = "Type", required = true, notes = "The type of the IP addresses.")
        private IpAddressType type;
        @ApiModelProperty(name = "Fixed Addresses", notes = "Fixed addresses to assign (from the subnet defined by \"subnetId\" if provided).")
        private List<String> fixedAddresses;
        @ApiModelProperty(name = "Number of Dynamic Addresses", notes = "Number of dynamic addresses to assign (from the subnet defined by \"subnetId\" if provided).")
        private Integer numDynamicAddresses;
        @ApiModelProperty(name = "IP Address Range", notes = "An IP address range to be used, e.g. in case of egress connections. In case this attribute is present, IP addresses from the range will be used.")
        private SubnetIpRange addressRange;
        @ApiModelProperty(name = "Subnet Id", notes = "Subnet defined by the identifier of the subnet resource in the VIM. In case this attribute is present, IP addresses from that subnet will be assigned; otherwise, IP addresses not bound to a subnet will be assigned.")
        private String subnetId;

        public enum IpAddressType {
            IPV4, IPV6;
        }

    }

}
