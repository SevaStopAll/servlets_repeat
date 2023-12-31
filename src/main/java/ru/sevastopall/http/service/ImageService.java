package ru.sevastopall.http.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import ru.sevastopall.http.util.PropertiesUtil;


import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.*;
import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public class ImageService {
    private static final ImageService INSTANCE = new ImageService();
    private final String basePath = "C:\\Users\\School_Laptop  3\\IdeaProjects\\servlets_repeat\\src\\main\\resources\\static";

    @SneakyThrows
    public void upload(String imagePath, InputStream imageContent) {
        var imageFullPath = Path.of(basePath, imagePath);
        try(imageContent) {
            Files.createDirectories(imageFullPath.getParent());
            Files.write(imageFullPath, imageContent.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }
    public static ImageService getInstance() {
        return INSTANCE;
    }
    @SneakyThrows
    public Optional<InputStream> get(String imagePath) {
        var imageFullPath = Path.of(basePath, imagePath);
        return Files.exists(imageFullPath)
                ? Optional.of(Files.newInputStream(imageFullPath)) : Optional.empty();
    }
}
