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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.nio.cs.ext.MacCroatian;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Controller
public class ReportController {
    @Autowired
    MedicineChestMedicineService medicineChestMedicineService;

    @Autowired
    ReportService reportService;

    @RequestMapping(value = "/report1", params = {})
    public void  generateReport(HttpServletResponse response)  throws IOException, JRException{
        List<MedicineChestMedicine> medicineChestMedicines = medicineChestMedicineService.findAll();
        JasperPrint jasperPrint = null;
       try {
          jasperPrint = reportService.exportReport(medicineChestMedicines, "report.jrxml");

        } catch (FileNotFoundException | JRException e) {
            e.printStackTrace();
        }
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline;filename=diskLargeLabel.pdf");

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.exportReport();
    }
  /*  @GetMapping(value = "/report1", params = {"sort"})
    public String generateReportSort(@RequestParam("sort") String sort){
        List<Customer> customers = customerService.findAllSorted(sort);
        try {
            reportService.exportReport(customers, "report.jrxml", "report2.html", "html");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
        return "redirect:/report2.html";
    }

    @GetMapping(value = "/report2", params = {})
    public String generateReportSort(){
        List<Customer> customers = customerService.findAllSorted("company");
        try {
            reportService.exportReport(customers, "report.jrxml", "report3.html", "html");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
        return "redirect:/report3.html";
    }*/
}
