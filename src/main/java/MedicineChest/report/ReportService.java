package MedicineChest.report;

import MedicineChest.medicineChestMedicine.MedicineChestMedicine;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public JasperPrint exportReport(List<MedicineChestMedicine> medicineChestMedicines, String template) throws FileNotFoundException, JRException {
        File file = ResourceUtils.getFile("classpath:templates/" + template);
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(medicineChestMedicines);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", "test");
         return JasperFillManager.fillReport(jasperReport, parameters, dataSource);

    }
}