package org.trafikverket.poller.divyaTrafikverketPoller;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DurationValue {

  ZonedDateTime start;
  ZonedDateTime end;
}
