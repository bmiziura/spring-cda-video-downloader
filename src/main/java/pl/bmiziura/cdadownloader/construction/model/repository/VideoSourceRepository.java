package pl.bmiziura.cdadownloader.construction.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bmiziura.cdadownloader.construction.model.entity.VideoSourceEntity;

@Repository
public interface VideoSourceRepository extends JpaRepository<VideoSourceEntity, Long> {
}
