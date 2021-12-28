package org.etsi.sol005.lifecyclemanagement;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents information about a network address that has been assigned.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents information about a network address that has been assigned.")
public class IpOverEthernetAddressInfo {

    @ApiModelProperty(name = "MAC Address", required = true, notes = "Assigned MAC address.")
    private String macAddress;
    @ApiModelProperty(name = "IP Addresses", notes = "Addresses assigned to the CP instance. Each entry represents IP addresses assigned by fixed or dynamic IP address assignment per subnet.")
    private List<IpAddress> ipAddresses;

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
        @ApiModelProperty(name = "Addresses", notes = "Fixed addresses assigned (from the subnet defined by \"subnetId\" if provided).")
        private List<String> addresses;
        @JsonProperty("isDynamic")
        @ApiModelProperty(name = "Number of Dynamic Addresses", notes = "Indicates whether this set of addresses was assigned dynamically (true) or based on address information provided as input from the API consumer (false). Shall be present if \"addresses\" is present and shall be absent otherwise.")
        private Boolean dynamic;
        @ApiModelProperty(name = "Address Range", notes = "An IP address range used, e.g. in case of egress connections.")
        private SubnetIpRange addressRange;
        @ApiModelProperty(name = "Subnet Id", notes = "Subnet defined by the identifier of the subnet resource in the VIM. In case this attribute is present, IP addresses are bound to that subnet.")
        private String subnetId;

        public enum IpAddressType {
            IPV4, IPV6;
        }

    }

}
