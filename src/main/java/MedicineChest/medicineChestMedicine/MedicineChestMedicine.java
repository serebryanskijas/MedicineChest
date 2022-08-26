package MedicineChest.medicineChestMedicine;

import MedicineChest.medicine.Medicine;
import MedicineChest.medicineChest.MedicineChest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.Format;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicine_chest_medicine")
public class MedicineChestMedicine {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int count;

    @Column(name = "expiration_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private MedicineChest medicineChest;

    @ManyToOne(fetch = FetchType.LAZY)
    private Medicine medicine;

}

