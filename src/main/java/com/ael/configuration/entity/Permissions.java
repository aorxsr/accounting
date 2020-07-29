package com.ael.configuration.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "pub_permissions")
@Data
public class Permissions {
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @Column
    private String permissionCode;
    @Column
    private String permissionName;
}
