package ru.sevastopall.http.mapper;

import ru.sevastopall.http.dto.CreateUserDto;
import ru.sevastopall.http.entity.Gender;
import ru.sevastopall.http.entity.Role;
import ru.sevastopall.http.entity.User;
import ru.sevastopall.http.util.LocalDateFormatter;
import ru.sevastopall.http.validator.CreateUserValidator;

public class CreateUserMapper implements Mapper<CreateUserDto, User> {
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .name(object.getName())
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .email(object.getEmail())
                .password(object.getPassword())
                .gender(Gender.valueOf(object.getGender()))
                .role(Role.valueOf(object.getRole()))


                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
