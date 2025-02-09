package in.nineteen96.aws_candolim.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPaginatedRequest {

    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
    private String keyword;
}
