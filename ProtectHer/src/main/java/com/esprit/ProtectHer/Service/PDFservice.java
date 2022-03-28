package com.esprit.ProtectHer.Service;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.esprit.ProtectHer.Repository.TrainingRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;




import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.WriterException;
import com.esprit.ProtectHer.entity.Training;



@Component("pdfService")
public class PDFservice {
	
	@Value("C:/Formation")
	private String pdfDir;

	@Value("dd MMMM yyyy HH:mm:ss")
	private String localDateFormat;

	@Value("C:/Formation/protectLogo.png")
	private String logoImgPath;

	@Autowired
	
	TrainingRepository trainingRepository;

	private static Font COURIER = new Font(Font.FontFamily.COURIER, 18, Font.BOLD);
	private static Font COURIER_MEDIUM = new Font(Font.FontFamily.COURIER, 14, Font.BOLD);
	private static Font COURIER_SMALL = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);
	
	public void generatePdfReport() throws WriterException, IOException {
		Document document = new Document(PageSize.A4.rotate());
		List<Training> trainings = trainingRepository.findByEtat(false) ;
		try {
			for (Training training : trainings) {
				PdfWriter.getInstance(document, new FileOutputStream(pdfDir + ""+ ".pdf"));
				document.open();
				addLogo(document);
				addDocTitle(document);
				createBadge(document, training);
				document.close();
				training.setEtat(true);
			}
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
	}

	private void addLogo(Document document) {
		try {
			Image img = Image.getInstance(logoImgPath);
			img.scalePercent(25, 25);
			img.setAlignment(Element.ALIGN_RIGHT);
			document.add(img);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
	}

	private void addDocTitle(Document document) throws DocumentException {
		String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern(localDateFormat));
		Paragraph p1 = new Paragraph();
		leaveEmptyLine(p1, 1);
		p1.add(new Paragraph("new training", COURIER));
		p1.setAlignment(Element.ALIGN_CENTER);
		leaveEmptyLine(p1, 1);
		p1.add(new Paragraph("training generated on " + localDateString, COURIER_MEDIUM));
		document.add(p1);
	}

	private void createBadge(Document document, Training training) throws DocumentException {
		Paragraph paragraph = new Paragraph();
		leaveEmptyLine(paragraph, 3);
		paragraph.add((new Paragraph("\nDate Debut : " + training.getDateDebut(), COURIER_SMALL)));
		paragraph.add((new Paragraph("\nDtae Fin : " + training.getDateFin(), COURIER_SMALL)));
		paragraph.add((new Paragraph("\nDomaine : " + training.getDomaine(), COURIER_SMALL)));
		paragraph.add((new Paragraph("\nLevel :" + training.getLevel(), COURIER_SMALL)));
		document.add(paragraph);
	}
	private static void leaveEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}


}
