package MedicineChest;

import MedicineChest.medicineChest.TestService;
import MedicineChest.medicineChestMedicine.MedicineChestMedicine;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedicineChestApplication {

    private final TestService testService;

    public MedicineChestApplication(TestService testService) {
        this.testService = testService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MedicineChestApplication.class, args);
    }



}
