package org.chicchoice.mediaservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence_media")
    @SequenceGenerator(name = "sequence_media",sequenceName = "sequence_media",allocationSize = 1)
    private Long id;
    private String imageUrl;
}
