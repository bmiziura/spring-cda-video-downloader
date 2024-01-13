package pl.bmiziura.cdadownloader.construction.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bmiziura.cdadownloader.construction.model.entity.VideoEntity;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
}
