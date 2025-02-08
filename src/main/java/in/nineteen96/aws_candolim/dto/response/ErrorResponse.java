package in.nineteen96.aws_candolim.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ErrorResponse extends BasicResponseOutput {

    private String errorMessage;
    private String exception;
}
