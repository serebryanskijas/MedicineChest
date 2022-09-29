package MedicineChest.medicine;

import MedicineChest.medicine.vo.SearchVo;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private SessionFactory sessionFactory;

    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    public Medicine save(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    public Medicine findById(Long id) {
        return medicineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find Medicine by id: " + id));
    }

    public void deleteById(Long id) {
        medicineRepository.deleteById(id);
    }

    public Medicine getMedicineByName(String name) {
        return medicineRepository.getMedicineByName(name);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Medicine> findByCategory(SearchVo searchVo) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Medicine.class);
        if (!StringUtils.isEmpty(searchVo.getName())) {
            criteria.add(Restrictions.like("name", searchVo.getName(), MatchMode.ANYWHERE).ignoreCase());
        }
        if (searchVo.getCategory() != null) {
            criteria.createAlias("category", "cat");
            criteria.add(Restrictions.eq("cat.id", searchVo.getCategory().getId()));
        }
        return criteria.list();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Medicine> findByCategoryId(Long id) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Medicine.class);
        criteria.createAlias("category", "cat");
        criteria.add(Restrictions.eq("cat.id", id));
        return criteria.list();
    }

}
