package dev.intermediatebox.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable

@NoArgsConstructor
@AllArgsConstructor
public class Address {
  private String line1;
  private String line2;
  private String city;
}
