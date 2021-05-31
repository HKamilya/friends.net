package ru.itis.javalab.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.model.Image;
import ru.itis.javalab.services.ImagesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Controller
public class UploadImageController {
    @Autowired
    private ImagesService imagesService;
    @Value("${UPLOAD_DIR}")
    public String UPLOAD_DIR;


    @GetMapping(value = "/img")
    public @ResponseBody
    byte[] getImage(@RequestParam(value = "id") Long id) throws IOException {
        Optional<Image> image = imagesService.findById(id);
        InputStream in = getClass()
                .getResourceAsStream(UPLOAD_DIR + File.separator + image.get().getAddress());
        return IOUtils.toByteArray(in);
    }
}
