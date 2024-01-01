package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * NsIdentifierCreationNotification
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-01-28T13:34:51.383Z[Europe/London]")
public class NsIdentifierCreationNotification implements NotificationRequestBody   {
    @JsonProperty("id")
    private String id;

    @JsonProperty("notificationType")
    private String notificationType;

    @JsonProperty("subscriptionId")
    private String subscriptionId;

    @JsonProperty("timestamp")
    private OffsetDateTime timestamp = null;

    @JsonProperty("nsInstanceId")
    private String nsInstanceId;

    @JsonProperty("_links")
    private LccnLinks links;

    public NsIdentifierCreationNotification id(String id) {
        this.id = id;
        return this;
    }

    /**
     * An identifier with the intention of being globally unique. 
     * @return id
     */
    @Schema(required = true, description = "An identifier with the intention of being globally unique. ")
    @NotNull


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NsIdentifierCreationNotification notificationType(String notificationType) {
        this.notificationType = notificationType;
        return this;
    }

    /**
     * Discriminator for the different notification types. Shall be set to \"NsIdentifierDeletionNotification\" for this notification type. 
     * @return notificationType
     */
    @Schema(required = true, description = "Discriminator for the different notification types. Shall be set to \"NsIdentifierDeletionNotification\" for this notification type. ")
    @NotNull


    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public NsIdentifierCreationNotification subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    /**
     * An identifier with the intention of being globally unique. 
     * @return subscriptionId
     */
    @Schema(required = true, description = "An identifier with the intention of being globally unique. ")
    @NotNull


    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public NsIdentifierCreationNotification timestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * Get timestamp
     * @return timestamp
     */
    @Schema(required = true, description = "")
    @NotNull

    @Valid

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public NsIdentifierCreationNotification nsInstanceId(String nsInstanceId) {
        this.nsInstanceId = nsInstanceId;
        return this;
    }

    /**
     * An identifier with the intention of being globally unique. 
     * @return nsInstanceId
     */
    @Schema(required = true, description = "An identifier with the intention of being globally unique. ")
    @NotNull


    public String getNsInstanceId() {
        return nsInstanceId;
    }

    public void setNsInstanceId(String nsInstanceId) {
        this.nsInstanceId = nsInstanceId;
    }

    public NsIdentifierCreationNotification links(LccnLinks links) {
        this.links = links;
        return this;
    }

    /**
     * Get links
     * @return links
     */
    @Schema(required = true, description = "")
    @NotNull

    @Valid

    public LccnLinks getLinks() {
        return links;
    }

    public void setLinks(LccnLinks links) {
        this.links = links;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NsIdentifierCreationNotification nsIdentifierCreationNotification = (NsIdentifierCreationNotification) o;
        return Objects.equals(this.id, nsIdentifierCreationNotification.id) &&
                Objects.equals(this.notificationType, nsIdentifierCreationNotification.notificationType) &&
                Objects.equals(this.subscriptionId, nsIdentifierCreationNotification.subscriptionId) &&
                Objects.equals(this.timestamp, nsIdentifierCreationNotification.timestamp) &&
                Objects.equals(this.nsInstanceId, nsIdentifierCreationNotification.nsInstanceId) &&
                Objects.equals(this.links, nsIdentifierCreationNotification.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notificationType, subscriptionId, timestamp, nsInstanceId, links);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NsIdentifierCreationNotification {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
        sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
        sb.append("    nsInstanceId: ").append(toIndentedString(nsInstanceId)).append("\n");
        sb.append("    links: ").append(toIndentedString(links)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}


