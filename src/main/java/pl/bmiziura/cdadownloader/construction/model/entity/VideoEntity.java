package pl.bmiziura.cdadownloader.construction.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "video")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VideoEntity {

    @Id
    private Long id;

    private String title;
    private long duration;

    @JsonManagedReference
    @OneToMany(mappedBy = "video", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<VideoSourceEntity> sources;
}
