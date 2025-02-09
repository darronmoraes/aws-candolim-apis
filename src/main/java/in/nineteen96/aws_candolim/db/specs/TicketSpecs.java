package in.nineteen96.aws_candolim.db.specs;

import in.nineteen96.aws_candolim.db.entity.Ticket;
import in.nineteen96.aws_candolim.dto.request.GetPaginatedRequest;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TicketSpecs {

    public static Specification<Ticket> getTicketSpecs(GetPaginatedRequest request) {
        log.info("setting tickets specs");

        return ((root, query, criteriaBuilder) -> {
            List<Predicate> ticketPredicates = new ArrayList<>();

            if (request.getKeyword() != null && !request.getKeyword().isEmpty()) {
                log.info("keyword : {}", request.getKeyword());

                String partialKeyword = "%" + request.getKeyword() + "%";

                Predicate ticketSerialNumberPredicate = criteriaBuilder.like(root.get("serialNumber"), partialKeyword);

                ticketPredicates.add(ticketSerialNumberPredicate);
            }

            log.info("default behaviour, avoiding search for deleted tickets");
            Predicate deletedFalseTicketsPredicate = criteriaBuilder.equal(root.get("deleted"), false);
            ticketPredicates.add(deletedFalseTicketsPredicate);

            log.info("ticket specs criteria added");
            return criteriaBuilder.and(ticketPredicates.toArray(new Predicate[0]));
        });
    }

}
