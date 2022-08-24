package MedicineChest.medicineChestMedicine;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicineChestMedicineService {

    @Autowired
    private MedicineChestMedicineRepository medicineChestMedicineRepository;

    public List<MedicineChestMedicine> findAll() {
        return medicineChestMedicineRepository.findAll();
    }

    @Autowired
    SessionFactory sessionFactory;

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
        return medicineChestMedicineRepository.findById(id).get();
    }

    public List<MedicineChestMedicine> findByMedicineChestId(Long id) {
        return medicineChestMedicineRepository.getByMedicineChestId(id);
    }

    public void deleteById(Long id) {
        medicineChestMedicineRepository.deleteById(id);
    }

}
