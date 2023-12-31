package ru.sevastopall.http.service;

import lombok.SneakyThrows;
import ru.sevastopall.http.dao.UserDao;
import ru.sevastopall.http.dto.CreateUserDto;
import ru.sevastopall.http.dto.UserDto;
import ru.sevastopall.http.exception.ValidationException;
import ru.sevastopall.http.mapper.CreateUserMapper;
import ru.sevastopall.http.mapper.UserMapper;
import ru.sevastopall.http.validator.CreateUserValidator;

import java.util.Optional;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();
    public Optional<UserDto> login(String email, String password) {
        return userDao.findByLoginAndPassword(email, password)
                .map(userMapper::mapFrom);
    }
    @SneakyThrows
    public Integer create(CreateUserDto userDto) {
        // validation
        // map
        //userDao.save
        //return id
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        imageService.upload(userEntity.getImage(), userDto.getImage().getInputStream());
        userDao.save(userEntity);
        return userEntity.getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
