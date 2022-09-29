package MedicineChest.report;

import MedicineChest.medicineChestMedicine.MedicineChestMedicine;
import MedicineChest.medicineChestMedicine.MedicineChestMedicineService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private MedicineChestMedicineService medicineChestMedicineService;

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/report")
    public void generateReport(HttpServletResponse response, @RequestParam Long id) throws IOException, JRException {
        List<MedicineChestMedicine> medicineChestMedicines = medicineChestMedicineService.findByMedicineChestId(id);

        JasperPrint jasperPrint = null;
        try {
            jasperPrint = reportService.exportReport(medicineChestMedicines, "report.jrxml");

        } catch (FileNotFoundException | JRException e) {
            e.printStackTrace();
        }
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline;filename=medicineList.pdf");

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.exportReport();
    }

}
