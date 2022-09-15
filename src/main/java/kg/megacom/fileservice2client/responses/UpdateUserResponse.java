package kg.megacom.fileservice2client.responses;

import kg.megacom.fileservice2client.models.enums.UserStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserResponse {
    Long userId;
    UserStatus currentUserStatus;
    UserStatus prevUserStatus;

    //Ответ сервера - уникальный ID пользователя, новый и предыдущий статус.
}
