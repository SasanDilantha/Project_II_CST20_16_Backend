package com.pms.user_server.clients;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record FarmClientResponse(
        @JsonProperty("farm_id") Integer farm_id
) {
    @JsonCreator
    public FarmClientResponse(@JsonProperty("farm_id") Integer farm_id) {
        this.farm_id = farm_id;
    }
}
