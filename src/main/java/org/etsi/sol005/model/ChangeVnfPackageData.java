package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type specifies the information needed to change the current VNF package for a VNF instance. Clause B.3 of the ETSI GS NFV-IFA 007 illustrates the variants of changes to the current VNF Package and information flow procedures. This operation encompasses the following scenarios: * Changes of the VNF virtualised resources, such as requirements, composition and structure between the VNF versions,   without changing the VNF software version. * Changes of both the VNF software version and the VNF virtualised resources. This case includes replacing the VNF   software version by means of virtualised resources management, such as terminating the current virtualized resource   instances running the current software version and instantiating new virtualized resource instances with the   destination VNF software version. The new virtualized resource instances may have the same characteristics as the   current virtualized resource instances. * Changes related to the VNFD, such as correction of bugs in the VNFD, changes in the naming scheme of VNFD components   (e.g. name of the VDU, vduId), and adding/removing descriptors of VNF Package changes (VnfPackageChangeInfo). NOTE: For software updates that are executed by functional entities outside NFV-MANO and that require synchronization       of the information held by the NFV-MANO entities with a new VNF package that reflects the same changes, an       alternative procedure using the PATCH method on the \&quot;Individual VNF instance\&quot; resource has been defined, as       illustrated in annex B.2 of ETSI GS NFV-IFA 007 [19]. This procedure assumes certain restrictions on the       characteristics of the new VNF package, as defined in note 1 in table 5.5.2.2-1 of ETSI GS NFV-SOL 003. This type shall comply with the provisions defined in Table 6.5.3.54-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ChangeVnfPackageData   {

  private String vnfInstanceId;

  private String vnfdId;

  private List<ExtVirtualLinkData> extVirtualLinks = null;

  private List<ExtManagedVirtualLinkData> extManagedVirtualLinks = null;

  private Object additionalParams;

  private Object extensions;

  private Object vnfConfigurableProperties;
}

