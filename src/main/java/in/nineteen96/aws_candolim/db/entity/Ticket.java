package in.nineteen96.aws_candolim.db.entity;

import in.nineteen96.aws_candolim.util.enums.PaymentMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private String id;

    @Column(name = "serial_number", nullable = false, unique = true, length = 15)
    private String serialNumber;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Integer passenger;

    @Column(name = "payment_mode", nullable = false)
    private PaymentMode paymentMode;

    @Column(name = "gstn", length = 45)
    private String gstNumber;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime deletedOn;

    @Column(nullable = false)
    private Boolean deleted;

}
