package ru.sevastopall.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import ru.sevastopall.http.service.ImageService;
import ru.sevastopall.http.util.UrlPath;

import java.io.IOException;
import java.io.InputStream;

@WebServlet(UrlPath.IMAGES + "/*")
public class ImageServlet extends HttpServlet {
    private final ImageService imageService = ImageService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var requestURI = req.getRequestURI();
        String imagePath = requestURI.replace("/images", "");
        imageService.get(imagePath)
                .ifPresentOrElse(image ->
                {
                    resp.setContentType("application/octet-stream");
                    writeImage(resp, image);
                }, () -> resp.setStatus(404));

    }
    @SneakyThrows
    private void writeImage(HttpServletResponse resp, InputStream image) {
        try(image; var outputStream = resp.getOutputStream()) {
            int currentByte;
            while((currentByte = image.read()) != -1) {
                outputStream.write(currentByte);
            }
        }
    }
}
