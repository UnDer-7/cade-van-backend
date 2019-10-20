package com.cade.cadeonibus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverLocationDTO implements Serializable {
  private static final long serialVersionUID = -3570367130315425375L;

  private double latitude;
  private double longitude;
  private long driverId;
  private long itineraryId;
  private String driverName;
  private boolean isDriving;

}
