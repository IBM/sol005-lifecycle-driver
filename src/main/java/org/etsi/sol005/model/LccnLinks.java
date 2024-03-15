package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
// import jakarta.annotation.*;
/**
 * LccnLinks
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-01-28T13:34:51.383Z[Europe/London]")
public class LccnLinks {
    @JsonProperty("nsInstance")
    private NotificationLink nsInstance;

    @JsonProperty("subscription")
    private NotificationLink subscription;

    @JsonProperty("nslcmOpOcc")
    private NotificationLink nslcmOpOcc;

    public LccnLinks nsInstance(NotificationLink nsInstance) {
        this.nsInstance = nsInstance;
        return this;
    }

    /**
     * Get nsInstance
     * @return nsInstance
     */
    @Schema(required = true, description = "")
    @NotNull

    @Valid

    public NotificationLink getNsInstance() {
        return nsInstance;
    }

    public void setNsInstance(NotificationLink nsInstance) {
        this.nsInstance = nsInstance;
    }

    public LccnLinks subscription(NotificationLink subscription) {
        this.subscription = subscription;
        return this;
    }

    /**
     * Get subscription
     * @return subscription
     */
    @Schema(description = "")

    @Valid

    public NotificationLink getSubscription() {
        return subscription;
    }

    public void setSubscription(NotificationLink subscription) {
        this.subscription = subscription;
    }

    public LccnLinks nslcmOpOcc(NotificationLink nslcmOpOcc) {
        this.nslcmOpOcc = nslcmOpOcc;
        return this;
    }

    /**
     * Get nslcmOpOcc
     * @return nslcmOpOcc
     */
    @Schema(description = "")

    @Valid

    public NotificationLink getNslcmOpOcc() {
        return nslcmOpOcc;
    }

    public void setNslcmOpOcc(NotificationLink nslcmOpOcc) {
        this.nslcmOpOcc = nslcmOpOcc;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LccnLinks lccnLinks = (LccnLinks) o;
        return Objects.equals(this.nsInstance, lccnLinks.nsInstance) &&
                Objects.equals(this.subscription, lccnLinks.subscription) &&
                Objects.equals(this.nslcmOpOcc, lccnLinks.nslcmOpOcc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nsInstance, subscription, nslcmOpOcc);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LccnLinks {\n");

        sb.append("    nsInstance: ").append(toIndentedString(nsInstance)).append("\n");
        sb.append("    subscription: ").append(toIndentedString(subscription)).append("\n");
        sb.append("    nslcmOpOcc: ").append(toIndentedString(nslcmOpOcc)).append("\n");
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

