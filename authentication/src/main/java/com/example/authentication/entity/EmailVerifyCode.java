package com.example.authentication.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

import java.time.Instant;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "email_verify_code")
@SQLRestriction("is_deleted <> true")
public class EmailVerifyCode extends BaseEntity {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @Column(unique = true)
  private String verificationCode;
  private Instant expiredAt;
  @OneToOne(mappedBy = "emailVerifyCode", cascade = CascadeType.PERSIST)
  private User user;
}
