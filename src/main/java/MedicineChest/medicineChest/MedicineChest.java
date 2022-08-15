package MedicineChest.medicineChest;

import MedicineChest.category.Category;
import MedicineChest.medicine.Medicine;
import MedicineChest.medicineChestMedicine.MedicineChestMedicine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="medicine_chest")
public class MedicineChest {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;



   /* @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "medicine_chest_medicine",
            joinColumns = @JoinColumn(name="medicine_chest_id"),
            inverseJoinColumns = @JoinColumn(name="medicine_id"))
    private Set<Medicine> medicine = new HashSet<>();*/
}

