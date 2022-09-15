package kg.megacom.fileservice2client.services;

import kg.megacom.fileservice2client.models.User;
import kg.megacom.fileservice2client.models.dto.UserDto;
import kg.megacom.fileservice2client.models.enums.UserStatus;
import kg.megacom.fileservice2client.responses.UpdateUserResponse;
import kg.megacom.fileservice2client.responses.ResponseByStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface UserService {
    UserDto save(UserDto userDto);

    UpdateUserResponse update(Long userId, UserStatus userStatus);

    User getById(Long id);

    UserDto addUser(User user, MultipartFile file);


}
