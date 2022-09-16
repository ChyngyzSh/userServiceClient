package kg.megacom.fileservice2client.services.impl;

import kg.megacom.fileservice2client.dao.StatusHistoryRepo;
import kg.megacom.fileservice2client.dao.UserRepo;
import kg.megacom.fileservice2client.exceptions.UserException;
import kg.megacom.fileservice2client.mappers.UserMapper;
import kg.megacom.fileservice2client.microservices.FileServiceFeign;
import kg.megacom.fileservice2client.microservices.json.ResponseFile;
import kg.megacom.fileservice2client.models.StatusHistory;
import kg.megacom.fileservice2client.models.User;
import kg.megacom.fileservice2client.models.dto.UserDto;
import kg.megacom.fileservice2client.models.enums.UserStatus;
import kg.megacom.fileservice2client.responses.UpdateUserResponse;
import kg.megacom.fileservice2client.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.Date;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final StatusHistoryRepo statusHistoryRepo;
    private final UserMapper userMapper;
    private final FileServiceFeign fileServiceFeign;


    public UserServiceImpl(UserRepo userRepo, StatusHistoryRepo statusHistoryRepo,
                           FileServiceFeign fileServiceFeign) {
        this.userRepo = userRepo;
        this.statusHistoryRepo = statusHistoryRepo;
        this.fileServiceFeign = fileServiceFeign;
        this.userMapper = UserMapper.INSTANCE;
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user = userRepo.save(user);

        StatusHistory history = new StatusHistory();
        history.setId(history.getId());
        history.setUser(user);
        history.setCurrentStatus(userDto.getStatus());
        history.setStartDate(new Date());
        System.out.println(history);
        statusHistoryRepo.save(history);
        return userMapper.toDto(user);
    }

    //2 пункт
    @Override
    public UserDto addUser(User user, MultipartFile file) {
        ResponseFile responseFile = fileServiceFeign.upload(file);
        user.setImageUrl(responseFile.getDownloadUri());
        user=userRepo.save(user);
        UserDto userDto =userMapper.toDto(user);
        return userDto;
    }

    //3
    @Override
    public User getById(Long id) {
        return userRepo.findById(id).orElseThrow(()-> new RuntimeException("Пользователь не найден!"));
    }

    //4 - спец объект, где указываю только userId, предыдущий статус и текущий
    @Override
    public UpdateUserResponse update(Long userId, UserStatus userStatus) {

        if(getById(userId).equals(false)){
            new UserException("Пользователь не найден!");
        }
        User user = getById(userId);
        user.setStatus(userStatus);
        user = userRepo.save(user);
        //при каждом обновлении статуса обновляю также Статус в таблице Users

        Calendar calendar = Calendar.getInstance();
        //перед сохранением нахожу последнюю запись, и сохраняю статус в переменной
        StatusHistory statusHistoryOld = statusHistoryRepo.findByUserIdPrevStatus(user.getId());
        calendar.add(Calendar.MINUTE, -1);
        //отнимаю 1 минуту, чтобы в базе указать дату завершения прошлого сеанса
        if(userStatus == statusHistoryOld.getCurrentStatus()){
            throw new UserException("Статус прежний, выберите другой");
        }else {
            statusHistoryOld.setEndDate(calendar.getTime());// указываем в пред.записи дату закрытия
            statusHistoryRepo.save(statusHistoryOld);
            calendar.add(Calendar.MINUTE, 1);//добавляю обратно минуту, и записываю новые данные
            StatusHistory statusHistory = new StatusHistory();
            statusHistory.setCurrentStatus(userStatus);
            statusHistory.setStartDate(calendar.getTime());
            statusHistory.setUser(user);
            statusHistoryRepo.save(statusHistory);
        }
        UpdateUserResponse updateUserResponse = new UpdateUserResponse();
        updateUserResponse.setUserId(userId);
        updateUserResponse.setCurrentUserStatus(userStatus);
        updateUserResponse.setPrevUserStatus(statusHistoryOld.getCurrentStatus());
        return updateUserResponse;
/*
Передаем на сервер уникальный ID пользователя и новый статус (Online, Offline).
Изменяем статус пользователя.
Ответ сервера - уникальный ID пользователя, новый и предыдущий статус.
*/
    }



}
