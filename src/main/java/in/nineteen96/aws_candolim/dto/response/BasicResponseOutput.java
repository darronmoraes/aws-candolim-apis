package in.nineteen96.aws_candolim.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class BasicResponseOutput {

    private HttpStatus status;
    private Boolean success;
    private String message;
    private LocalDateTime timestamp;

}
