package MedicineChest.medicine;

import MedicineChest.category.Category;
import MedicineChest.dosageForm.DosageForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "medicine")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String manufacturer;

    private Integer dosage;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "medicine_dosage_form",
            joinColumns = @JoinColumn(name = "medicine_id"),
            inverseJoinColumns = @JoinColumn(name = "dosage_form_id"))
    private Set<DosageForm> dosageForms = new HashSet<>();

}