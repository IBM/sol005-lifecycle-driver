package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.util.Objects;

/**
 * The definition of the general \&quot;ProblemDetails\&quot; data structure from IETF RFC 7807 [19] is reproduced in this structure. Compared to the general framework defined in IETF RFC 7807 [19], the \&quot;status\&quot; and \&quot;detail\&quot; attributes are mandated to be included by the present document, to ensure that the response contains additional textual information about an error. IETF RFC 7807 [19] foresees extensibility of the \&quot;ProblemDetails\&quot; type. It is possible that particular APIs in the present document, or particular implementations, define extensions to define additional attributes that provide more information about the error. The description column only provides some explanation of the meaning to Facilitate understanding of the design. For a full description, see IETF RFC 7807 [19].
 */
@Schema(description = "The definition of the general \"ProblemDetails\" data structure from IETF RFC 7807 [19] is reproduced in this structure. Compared to the general framework defined in IETF RFC 7807 [19], the \"status\" and \"detail\" attributes are mandated to be included by the present document, to ensure that the response contains additional textual information about an error. IETF RFC 7807 [19] foresees extensibility of the \"ProblemDetails\" type. It is possible that particular APIs in the present document, or particular implementations, define extensions to define additional attributes that provide more information about the error. The description column only provides some explanation of the meaning to Facilitate understanding of the design. For a full description, see IETF RFC 7807 [19]. ")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-11-20T15:53:58.333Z[Europe/London]")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ProblemDetails {
    @JsonProperty("type")
    private URI type;

    @JsonProperty("title")
    private String title;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("detail")
    private String detail;

    @JsonProperty("instance")
    private URI instance;

    public ProblemDetails type(URI type) {
        this.type = type;
        return this;
    }

    /**
     * A URI reference according to IETF RFC 3986 [5] that identifies the problem type. It is encouraged that the URI provides human-readable documentation for the problem (e.g. using HTML) when dereferenced. When this member is not present, its value is assumed to be \"about:blank\".
     * @return type
     */
    @Schema(description = "A URI reference according to IETF RFC 3986 [5] that identifies the problem type. It is encouraged that the URI provides human-readable documentation for the problem (e.g. using HTML) when dereferenced. When this member is not present, its value is assumed to be \"about:blank\". ")

    @Valid

    public URI getType() {
        return type;
    }

    public void setType(URI type) {
        this.type = type;
    }

    public ProblemDetails title(String title) {
        this.title = title;
        return this;
    }

    /**
     * A short, human-readable summary of the problem type. It should not change from occurrence to occurrence of the problem, except for purposes of localization. If type is given and other than \"about:blank\", this attribute shall also be provided. A short, human-readable summary of the problem type.  It SHOULD NOT change from occurrence to occurrence of the problem, except for purposes of localization (e.g., using proactive content negotiation; see [RFC7231], Section 3.4).
     * @return title
     */
    @Schema(description = "A short, human-readable summary of the problem type. It should not change from occurrence to occurrence of the problem, except for purposes of localization. If type is given and other than \"about:blank\", this attribute shall also be provided. A short, human-readable summary of the problem type.  It SHOULD NOT change from occurrence to occurrence of the problem, except for purposes of localization (e.g., using proactive content negotiation; see [RFC7231], Section 3.4). ")


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ProblemDetails status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * The HTTP status code for this occurrence of the problem. The HTTP status code ([RFC7231], Section 6) generated by the origin server for this occurrence of the problem.
     * @return status
     */
    @Schema(required = true, description = "The HTTP status code for this occurrence of the problem. The HTTP status code ([RFC7231], Section 6) generated by the origin server for this occurrence of the problem. ")
    @NotNull


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ProblemDetails detail(String detail) {
        this.detail = detail;
        return this;
    }

    /**
     * A human-readable explanation specific to this occurrence of the problem.
     * @return detail
     */
    @Schema(required = true, description = "A human-readable explanation specific to this occurrence of the problem. ")
    @NotNull


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ProblemDetails instance(URI instance) {
        this.instance = instance;
        return this;
    }

    /**
     * A URI reference that identifies the specific occurrence of the problem. It may yield further information if dereferenced.
     * @return instance
     */
    @Schema(description = "A URI reference that identifies the specific occurrence of the problem. It may yield further information if dereferenced. ")

    @Valid

    public URI getInstance() {
        return instance;
    }

    public void setInstance(URI instance) {
        this.instance = instance;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProblemDetails problemDetails = (ProblemDetails) o;
        return Objects.equals(this.type, problemDetails.type) &&
                Objects.equals(this.title, problemDetails.title) &&
                Objects.equals(this.status, problemDetails.status) &&
                Objects.equals(this.detail, problemDetails.detail) &&
                Objects.equals(this.instance, problemDetails.instance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, title, status, detail, instance);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProblemDetails {\n");

        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
        sb.append("    instance: ").append(toIndentedString(instance)).append("\n");
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


