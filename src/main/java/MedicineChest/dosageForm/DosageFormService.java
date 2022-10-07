package MedicineChest.dosageForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DosageFormService {
    @Autowired
    private DosageFormRepository dosageFormRepository;

    public List<DosageForm> findAll() {
        return dosageFormRepository.findAll();
    }

    public DosageForm save(DosageForm dosageForm) {
        return dosageFormRepository.save(dosageForm);
    }

    public DosageForm findById(Long id) {
        return dosageFormRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find DosageForm by id: " + id));
    }

    public void deleteById(Long id) {
        dosageFormRepository.deleteById(id);
    }
}
