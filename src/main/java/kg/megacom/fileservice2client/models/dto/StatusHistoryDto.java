package kg.megacom.fileservice2client.models.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class StatusHistoryDto {
    Long id;
    UserDto user;
    Date startDate;
    Date endDate;
}
