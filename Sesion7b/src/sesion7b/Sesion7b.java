
package sesion7b;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class Sesion7b {
 

    public static void main(String[] args) {
        List<Alumno> listaAlumnos= new ArrayList<Alumno>();
        for(int cont=1;cont<=5;cont++){
            Alumno alumno = new Alumno(cont,"0000"+cont, "Alumno "+cont);
            listaAlumnos.add(alumno);
            for(int i=1;i<=3;i++){
                alumno.addMateria(new Materia(i,"Materia "+i));
            }
        }
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("report7b.jasper");
            JasperPrint jasperPrint= JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(listaAlumnos));
            
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File ("Reporte7bPDF.pdf"));
            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(Sesion7b.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
