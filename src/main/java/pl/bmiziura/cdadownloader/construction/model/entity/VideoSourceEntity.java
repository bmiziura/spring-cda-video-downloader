package pl.bmiziura.cdadownloader.construction.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import pl.bmiziura.cdadownloader.construction.model.VideoSourceQuality;

@Entity
@Table(name = "video_source")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VideoSourceEntity {
    private static final String SEQUENCE_NAME = "video_source_id_seq";

    @Id
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "quality")
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private VideoSourceQuality quality;

    private String source;

    @ManyToOne
    @JoinColumn(name = "video_id")
    @JsonBackReference
    private VideoEntity video;

}
