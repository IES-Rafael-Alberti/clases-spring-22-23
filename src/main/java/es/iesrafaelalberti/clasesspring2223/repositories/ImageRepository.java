package es.iesrafaelalberti.clasesspring2223.repositories;

import es.iesrafaelalberti.clasesspring2223.models.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String> {
}
