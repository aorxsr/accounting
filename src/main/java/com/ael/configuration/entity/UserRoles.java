package com.ael.configuration.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "pub_user_roles")
@Data
public class UserRoles {
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @Column
    private String userId;
    @Column
    private String roleId;
}
