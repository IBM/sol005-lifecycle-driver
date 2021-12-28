package org.etsi.sol005.model;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * This operation supports the update of a NS instance,  It shall comply with the provisions defined in Table 6.5.2.12-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class UpdateNsRequest   {
  /**
   * The type of update. It determines also which one of the following parameters is present in the operation. Possible values include: * ADD_VNF: Adding existing VNF instance(s) * REMOVE_VNF: Removing VNF instance(s) * INSTANTIATE_VNF: Instantiating new VNF(s) * CHANGE_VNF_DF: Changing VNF DF * OPERATE_VNF: Changing VNF state, * MODIFY_VNF_INFORMATION: Modifying VNF information and/or the configurable properties of VNF instance(s) * CHANGE_EXTERNAL_VNF_CONNECTIVITY: Changing the external connectivity of VNF instance(s) * CHANGE_VNFPKG: Changing the VNF package(s) on which (a) VNF instance(s) is/are based * ADD_SAP: Adding SAP(s) * REMOVE_SAP: Removing SAP(s) * ADD_NESTED_NS: Adding existing NS instance(s) as nested NS(s) * REMOVE_NESTED_NS: Removing existing nested NS instance(s) * ASSOC_NEW_NSD_VERSION: Associating a new NSD version to the NS instance * MOVE_VNF: Moving VNF instance(s) from one origin NS instance to another target NS instance * ADD_VNFFG: Adding VNFFG(s) * REMOVE_VNFFG: Removing VNFFG(s) * UPDATE_VNFFG: Updating VNFFG(s) * CHANGE_NS_DF: Changing NS DF * ADD_PNF: Adding PNF * MODIFY_PNF: Modifying PNF * REMOVE_PNF: Removing PNF * CREATE_VNF_SNAPSHOT: Creating VNF Snapshots of VNF instances belonging to the NS instance.   It depends on the VNF capabilities, and is declared in the VNFD whether the operation is supported for a particular VNF. * REVERT_VNF_TO_SNAPSHOT: Reverting a VNF instance belonging to the NS instance to a VNF Snapshot.   It depends on the VNF capabilities, and is declared in the VNFD whether the operation is supported for a particular VNF.   The operation might be service-disruptive. * DELETE_VNF_SNAPSHOT_INFO: Deleting available VNF Snapshot information for a VNF instance belonging to the NS instance.   It depends on the VNF capabilities, and is declared in the VNFD whether the operation is supported for a particular VNF. * MODIFY_WAN_CONNECTION_INFO: Modify WAN related connectivity information. * CREATE_NS_VIRTUAL_LINK: Create an NsVirtualLink instance. * DELETE_NS_VIRTUAL_LINK: Delete an NsVirtualLink instance. 
   */
  public enum UpdateTypeEnum {
    ADD_VNF("ADD_VNF"),
    
    REMOVE_VNF("REMOVE_VNF"),
    
    INSTANTIATE_VNF("INSTANTIATE_VNF"),
    
    CHANGE_VNF_DF("CHANGE_VNF_DF"),
    
    OPERATE_VNF("OPERATE_VNF"),
    
    MODIFY_VNF_INFORMATION("MODIFY_VNF_INFORMATION"),
    
    CHANGE_EXTERNAL_VNF_CONNECTIVITY("CHANGE_EXTERNAL_VNF_CONNECTIVITY"),
    
    CHANGE_VNFPKG("CHANGE_VNFPKG"),
    
    ADD_SAP("ADD SAP"),
    
    REMOVE_SAP("REMOVE_SAP"),
    
    ADD_NESTED_NS("ADD_NESTED_NS"),
    
    REMOVE_NESTED_NS("REMOVE_NESTED_NS"),
    
    ASSOC_NEW_NSD_VERSION("ASSOC_NEW_NSD_VERSION"),
    
    MOVE_VNF("MOVE_VNF"),
    
    ADD_VNFFG("ADD_VNFFG"),
    
    REMOVE_VNFFG("REMOVE_VNFFG"),
    
    UPDATE_VNFFG("UPDATE_VNFFG"),
    
    CHANGE_NS_DF("CHANGE_NS_DF"),
    
    ADD_PNF("ADD_PNF"),
    
    MODIFY_PNF("MODIFY_PNF"),
    
    REMOVE_PNF("REMOVE_PNF"),
    
    CREATE_VNF_SNAPSHOT("CREATE_VNF_SNAPSHOT"),
    
    REVERT_VNF_TO_SNAPSHOT("REVERT_VNF_TO_SNAPSHOT"),
    
    DELETE_VNF_SNAPSHOT_INFO("DELETE_VNF_SNAPSHOT_INFO"),
    
    MODIFY_WAN_CONNECTION_INFO("MODIFY_WAN_CONNECTION_INFO"),
    
    CREATE_NS_VIRTUAL_LINK("CREATE_NS_VIRTUAL_LINK"),
    
    DELETE_NS_VIRTUAL_LINK("DELETE_NS_VIRTUAL_LINK");

    private String value;

    UpdateTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static UpdateTypeEnum fromValue(String value) {
      for (UpdateTypeEnum b : UpdateTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private UpdateTypeEnum updateType;

  private List<VnfInstanceData> addVnfIstance = null;

  private List<String> removeVnfInstanceId = null;

  private List<InstantiateVnfData> instantiateVnfData = null;

  private List<ChangeVnfFlavourData> changeVnfFlavourData = null;

  private List<OperateVnfData> operateVnfData = null;

  private List<ModifyVnfInfoData> modifyVnfInfoData = null;

  private List<ChangeExtVnfConnectivityData> changeExtVnfConnectivityData = null;

  private List<ChangeVnfPackageData> changeVnfPackageData = null;

  private List<SapData> addSap = null;

  private List<String> removeSapId = null;

  private List<NestedNsInstanceData> addNestedNsData = null;

  private List<String> removeNestedNsId = null;

  private AssocNewNsdVersionData assocNewNsdVersionData;

  private List<MoveVnfInstanceData> moveVnfInstanceData = null;

  private List<AddVnffgData> addVnffg = null;

  private List<String> removeVnffgId = null;

  private List<UpdateVnffgData> updateVnffg = null;

  private ChangeNsFlavourData changeNsFlavourData;

  private List<AddPnfData> addPnfData = null;

  private List<ModifyPnfData> modifyPnfData = null;

  private List<String> removePnfId = null;

  private List<ModifyWanConnectionInfoData> modifyWanConnectionInfoData = null;

  private OffsetDateTime updateTime = null;

  private CreateVnfSnapshotData createSnapshotData;

  private RevertVnfToSnapshotData revertVnfToSnapshotData;

  private DeleteVnfSnapshotData deleteVnfSnapshotData;

  private List<AddNsVirtualLinkData> addNsVirtualLinkData = null;

  private List<String> deleteNsVirtualLinkId = null;
}

