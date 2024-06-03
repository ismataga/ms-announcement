package com.example.authentication.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@SQLRestriction("is_deleted <> true")
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  private String email;
  private String password;

  @Column(nullable = false)
  private boolean emailConfirmed = false;

  @ToString.Exclude
  @JoinColumn(name = "email_verify_code_id")
  @OneToOne(cascade = CascadeType.PERSIST)
  private EmailVerifyCode emailVerifyCode;

}
