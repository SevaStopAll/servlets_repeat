package ru.sevastopall.http.dto;

import lombok.Builder;
import lombok.Value;
import ru.sevastopall.http.entity.Gender;
import ru.sevastopall.http.entity.Role;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    LocalDate birthday;
    String image;
    String email;
    Role role;
    Gender gender;

}
