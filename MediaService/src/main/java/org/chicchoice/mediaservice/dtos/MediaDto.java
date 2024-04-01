package org.chicchoice.mediaservice.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MediaDto implements Serializable {
    @NotNull
    @Positive
    Long id;
    @NotNull
    String imageUrl;
}