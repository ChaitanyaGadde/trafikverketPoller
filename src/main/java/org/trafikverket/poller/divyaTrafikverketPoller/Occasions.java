package org.trafikverket.poller.divyaTrafikverketPoller;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Occasions {

  DurationValue duration;
  String name;
  String date;
  String time;
  String locationName;


}
