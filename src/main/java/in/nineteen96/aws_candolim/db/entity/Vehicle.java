package in.nineteen96.aws_candolim.db.entity;

import in.nineteen96.aws_candolim.util.enums.VehicleType;
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
@Table(name = "vehicles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private String id;

    @Column(name = "number_suffix", nullable = false, length = 4)
    private String numberSuffix;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private VehicleType type;

    @Column(nullable = false)
    private Boolean commissioned;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
