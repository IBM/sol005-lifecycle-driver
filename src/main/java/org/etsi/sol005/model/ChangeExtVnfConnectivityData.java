package org.etsi.sol005.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type describes the information invoked by the NFVO to change the external VNF connectivity information maintained by the VNFM. The types of changes that this operation supports are: 1) Disconnect the external CPs that are connected to a particular external VL, and connect them to a different    external VL. 2) Disconnect and delete external CPs that are connected to a particular external VL and that represent subports    in a trunk, i.e. CP instances that are created from external CPDs that have trunk mode configured according to    clause 7.1.6.3 in ETSI GS NFV-IFA 011. If the parent port is exposed as an \&quot;extCp\&quot;, the VNFM shall ensure that    the parent port is not deleted. If the parent port is exposed as an \&quot;extCp\&quot; and there are other subports connected,    the VNFM shall ensure that the parent port is not disconnected, unless it is reconnected to a different external    VL in the same operation. 3) Change the connectivity parameters of the existing external CPs, including changing addresses. NOTE: Depending on the capabilities of the underlying VIM resources, certain changes (e.g. modifying the IP address       assignment) might not be supported without deleting the resource and creating another one with the modified       configuration.  4) Create new CPs that represent supports in a trunk, i.e. CP instances that are created from external CPDs that    have trunk mode configured according to clause 7.1.6.3 in ETSI GS NFV-IFA 011, and connect them to a particular    external VL. Creation of the parent port with this operation is not supported. This type shall comply with the    provisions defined in Table 6.5.3.33-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ChangeExtVnfConnectivityData   {

  private String vnfInstanceId;

  private List<ExtVirtualLinkData> extVirtualLinks = new ArrayList<>();

  private Object additionalParams;

}

