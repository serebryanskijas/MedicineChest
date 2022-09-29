package MedicineChest.medicineChestMedicine;

import MedicineChest.medicine.Medicine;
import MedicineChest.medicineChest.MedicineChest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public String getDate() {
        return expirationDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
    }
    @Transient
    private String date;

    public LocalDate getNowDate() {
        return LocalDate.now() ;
    }

    @Transient
    private LocalDate nowDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private MedicineChest medicineChest;

    @ManyToOne(fetch = FetchType.LAZY)
    private Medicine medicine;

    @Transient
    public Boolean isDateValid (){
    return expirationDate.isAfter(LocalDate.now());
    }
}

