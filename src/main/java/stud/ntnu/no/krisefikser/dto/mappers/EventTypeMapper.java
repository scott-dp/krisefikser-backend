package stud.ntnu.no.krisefikser.dto.mappers;

import lombok.experimental.UtilityClass;
import stud.ntnu.no.krisefikser.dto.EventTypeRequest;
import stud.ntnu.no.krisefikser.dto.EventTypeResponse;
import stud.ntnu.no.krisefikser.entities.EventType;

@UtilityClass
public class EventTypeMapper {

  public static EventTypeResponse toDto(EventType eventType) {
    return new EventTypeResponse()
        .setId(eventType.getId())
        .setName(eventType.getName())
        .setDescription(eventType.getDescription());
  }

  public static EventType toEntity(EventTypeRequest eventTypeRequest) {
    return new EventType()
        .setName(eventTypeRequest.getName())
        .setDescription(eventTypeRequest.getDescription());
  }
}
