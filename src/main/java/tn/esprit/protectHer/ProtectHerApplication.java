package tn.esprit.protectHer;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.google.zxing.WriterException;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tn.esprit.protectHer.service.PDFGenerator;

@EnableSwagger2
@EnableScheduling
@SpringBootApplication
public class ProtectHerApplication {

	public static void main(String[] args) throws WriterException, IOException {
		ApplicationContext ac = SpringApplication.run(ProtectHerApplication.class, args);
		PDFGenerator pDFGenerator = ac.getBean("pdfGenerator",PDFGenerator.class);
		pDFGenerator.generatePdfReport();
	}

}
