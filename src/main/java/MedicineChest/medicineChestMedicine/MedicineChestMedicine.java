package MedicineChest.medicineChestMedicine;

import MedicineChest.medicine.Medicine;
import MedicineChest.medicineChest.MedicineChest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate expirationDate;

    @OneToOne
    private Medicine medicine;

    @OneToOne
    private MedicineChest medicineChest;

}

