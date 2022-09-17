package kg.megacom.fileservice2client.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.megacom.fileservice2client.models.dto.StatusHistoryDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseByStatus {
    List<StatusHistoryDto> statusList;
    Long requestCurrentStamp;
}
