package org.trafikverket.poller.divyaTrafikverketPoller;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DataList {

  List<Occasions> occasions;
  String cost;
}
