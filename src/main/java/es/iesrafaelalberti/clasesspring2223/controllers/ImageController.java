package es.iesrafaelalberti.clasesspring2223.controllers;

import es.iesrafaelalberti.clasesspring2223.models.Image;
import es.iesrafaelalberti.clasesspring2223.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
public class ImageController {
    @Autowired
    ImageService imageService;

    @PostMapping("/images/add")
    public String addPhoto(@RequestParam("title") String title,
                           @RequestParam("image") MultipartFile image)
            throws IOException {
        return imageService.addImage(title, image);
    }

    @GetMapping("/images/{id}")
    public String getPhoto(@PathVariable String id) {
        Image image = imageService.getImage(id);
        return "<img src=\"data:image/jpeg;base64, " + Base64.getEncoder().encodeToString(image.getImage().getData()) + "\">";
    }
}
