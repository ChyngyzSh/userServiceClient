package kg.megacom.fileservice2client.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.megacom.fileservice2client.models.User;
import kg.megacom.fileservice2client.models.dto.StatusHistoryDto;
import kg.megacom.fileservice2client.models.dto.UserDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseByStatus {
    List<StatusHistoryDto> statusList;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date requestDate;
}
