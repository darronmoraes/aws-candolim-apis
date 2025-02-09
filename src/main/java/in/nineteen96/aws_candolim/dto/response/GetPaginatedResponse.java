package in.nineteen96.aws_candolim.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPaginatedResponse<T> extends BasicResponseOutput {

    private List<T> results;
    private Long totalElements;
    private Integer totalPages;
    private Integer currentPage;

}
