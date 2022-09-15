package kg.megacom.fileservice2client.models.dto;

import kg.megacom.fileservice2client.models.User;
import kg.megacom.fileservice2client.models.enums.UserStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class StatusHistoryDto {
    Long id;
    UserDto user;
    UserStatus status;
    Date startDate;
    Date endDate;
}
