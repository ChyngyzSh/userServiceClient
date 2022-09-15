package kg.megacom.fileservice2client.controllers;

import kg.megacom.fileservice2client.models.User;
import kg.megacom.fileservice2client.models.dto.UserDto;
import kg.megacom.fileservice2client.models.enums.UserStatus;
import kg.megacom.fileservice2client.responses.ResponseByStatus;
import kg.megacom.fileservice2client.responses.UpdateUserResponse;
import kg.megacom.fileservice2client.services.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //2
    @PostMapping("/add")
    public UserDto addUser(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }
    //1
//    @PostMapping("/upload/image")
//    public UserDto uploadImage(@RequestPart MultipartFile file){
//
//        return userService.uploadImage(file);
//        //Ответ сервера - внутренний URI картинки.
//    }

    @PostMapping("/add/image")
    public UserDto addImageToUser(@RequestParam Long userId, @RequestPart MultipartFile file){
        User user = userService.getById(userId);
        return userService.addUser(user, file);
    }

//4-пункт
    @PutMapping("/update")
    public UpdateUserResponse update(@RequestParam long userId, @RequestParam UserStatus status ){
        return userService.update(userId, status);
    }

    //3-пункт
    @GetMapping("/get/{id}")
    public User getById(@PathVariable Long id){
        return userService.getById(id);
    }


}
