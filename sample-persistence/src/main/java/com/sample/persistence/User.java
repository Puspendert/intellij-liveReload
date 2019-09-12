package com.sample.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sample.common.ParentEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 *
 * @author puspender
 *
 */

@Setter @Getter
@NoArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "email", "unique_id" }) })
public class User implements ParentEntity<Integer> {

    private static final long serialVersionUID = -900576716780005963L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "user_id", unique = true, nullable = false)
    private Integer userId;

    @Column(name = "unique_id", unique = true, length = 45, updatable = false) @NotBlank
    private String uniqueId;

    @Column(name = "email", unique = true, length = 45, updatable = false) @NotBlank
    private String email;

    @Column(name = "password") @NotBlank @JsonIgnore
    private String password;

    @Column(name = "backup_email", length = 45)
    private String backupEmail;

    @Column(name = "first_name", length = 45)
    @NotBlank
    private String firstName;

    @Column(name = "last_name", length = 45)
    @NotBlank
    private String lastName;

    @Column(name = "phone", length = 45)
    private String phone;

    @Column(name = "bio", length = 1000)
    private String bio;

    @Column(name = "created_on", updatable = false)
    @CreationTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class) @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    @UpdateTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class) @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updatedOn;


    public User(Integer userId, @NotBlank String uniqueId, @NotBlank String email, @NotBlank String password) {
        this.userId = userId;
        this.uniqueId = uniqueId;
        this.email = email;
        this.password = password;
    }


    public User(@NotBlank String uniqueId, @NotBlank String firstName, @NotBlank String lastName) {
        this.uniqueId = uniqueId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * For NamedQuery User.getUserMinimal
     */




    @JsonIgnore
    @Override
    public Integer getId() {
        return getUserId();
    }
}
