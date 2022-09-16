package kg.megacom.fileservice2client.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.megacom.fileservice2client.models.enums.UserStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    Long id;
    String name;
    String imageUrl;
    String email;
    UserStatus status;
}
