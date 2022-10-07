package MedicineChest.medicineChestMedicine;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MedicineChestMedicineService {

    @Autowired
    private MedicineChestMedicineRepository medicineChestMedicineRepository;

    @Autowired
    private SessionFactory sessionFactory;

    public List<MedicineChestMedicine> findAll() {
        return medicineChestMedicineRepository.findAll();
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<MedicineChestMedicine> findByCategory(Long medicineChestId, Long categoryId) {
        Session session = sessionFactory.openSession();
        return session.createCriteria(MedicineChestMedicine.class)
                .createAlias("medicineChest", "medicineChest")
                .createAlias("medicine", "med")
                .createAlias("med.category", "category")
                .add(Restrictions.eq("medicineChest.id", medicineChestId))
                .add(Restrictions.eq("category.id", categoryId)).list();
    }

    public MedicineChestMedicine save(MedicineChestMedicine medicineChestMedicine) {
        return medicineChestMedicineRepository.save(medicineChestMedicine);
    }

    public MedicineChestMedicine findById(Long id) {
        return medicineChestMedicineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find MedicineChestMedicine by id: " + id));
    }

    public List<MedicineChestMedicine> findByMedicineChestId(Long id) {
        return medicineChestMedicineRepository.getByMedicineChestId(id);
    }

    public void deleteById(Long id) {
        medicineChestMedicineRepository.deleteById(id);
    }

}
