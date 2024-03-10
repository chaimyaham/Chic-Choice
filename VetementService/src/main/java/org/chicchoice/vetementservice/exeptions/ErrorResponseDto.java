package org.chicchoice.vetementservice.exeptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private String path;
    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;
}
