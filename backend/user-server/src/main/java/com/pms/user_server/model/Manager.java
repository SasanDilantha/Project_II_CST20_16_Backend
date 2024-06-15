package com.pms.user_server.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Manager {
    @Id
    private Integer user_id;
    private Integer farm_id;
    // setup relationship
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
