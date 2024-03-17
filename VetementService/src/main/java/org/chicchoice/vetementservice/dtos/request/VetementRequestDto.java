package org.chicchoice.vetementservice.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import org.chicchoice.vetementservice.enums.Category;

import java.io.Serializable;

/**
 * DTO for {@link org.chicchoice.vetementservice.entities.Vetement}
 */
@Getter
@Setter
@NoArgsConstructor
public class VetementRequestDto implements Serializable {
    @NotBlank
    String note;
    @NotNull
    Category category;
    @NotBlank
    String marque;
    @NotNull
    Long mediaId;
    @NotNull
    Long userId;
    @NotNull
    Boolean favoris;
}