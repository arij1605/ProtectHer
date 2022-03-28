package tn.esprit.protectHer.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.google.zxing.WriterException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import tn.esprit.protectHer.entity.Subscription;
import tn.esprit.protectHer.repository.SubscriptionRepository;

@Component("pdfGenerator")
public class PDFGenerator {

	@Value("C:/ProtectHer/")
	private String pdfDir;

	@Value("dd MMMM yyyy HH:mm:ss")
	private String localDateFormat;

	@Value("C:/ProtectHer/logo.png")
	private String logoImgPath;

	@Autowired
	SubscriptionRepository subscriptionRepository;

	private static Font COURIER = new Font(Font.FontFamily.COURIER, 18, Font.BOLD);
	private static Font COURIER_MEDIUM = new Font(Font.FontFamily.COURIER, 14, Font.BOLD);
	private static Font COURIER_SMALL = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);

	public void generatePdfReport() throws WriterException, IOException {
		Document document = new Document(PageSize.A4.rotate());
		List<Subscription> subscriptions = subscriptionRepository.findByConverted(false);
		try {
			for (Subscription subscription : subscriptions) {
				PdfWriter.getInstance(document, new FileOutputStream(pdfDir + (subscription.getNic().toString().length() == 8 ? subscription.getNic().toString() : "0" + subscription.getNic().toString()) + ".pdf"));
				document.open();
				addLogo(document);
				addDocTitle(document);
				createBadge(document, subscription);
				addQRCode(document, subscription);
				document.close();
				subscription.setConverted(true);
				subscriptionRepository.save(subscription);
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
		p1.add(new Paragraph("Member Subscription", COURIER));
		p1.setAlignment(Element.ALIGN_CENTER);
		leaveEmptyLine(p1, 1);
		p1.add(new Paragraph("Subscription generated on " + localDateString, COURIER_MEDIUM));
		document.add(p1);
	}

	private void createBadge(Document document, Subscription subscription) throws DocumentException {
		Paragraph paragraph = new Paragraph();
		leaveEmptyLine(paragraph, 2);
		paragraph.add(new Paragraph("First Name : " + subscription.getFirstName(), COURIER_SMALL));
		paragraph.add(new Paragraph("\nLast Name : " + subscription.getLastName(), COURIER_SMALL));
		paragraph.add(new Paragraph("\nNational Identity Card : " + (subscription.getNic().toString().length() == 8 ? subscription.getNic().toString() : "0" + subscription.getNic().toString()), COURIER_SMALL));
		paragraph.add(new Paragraph("\nValidation Period : from " + subscription.getStartDate() + " to " + subscription.getEndDate(), COURIER_SMALL));
		document.add(paragraph);
	}

	private void addQRCode(Document document, Subscription subscription) throws DocumentException, WriterException, IOException {
		QRCodeGenerator.generateQRCodeImage(subscription.getNic().toString(), 90, 90,"./src/main/resources/" + subscription.getEmail() + ".png");
		try {
			Paragraph p1 = new Paragraph();
			leaveEmptyLine(p1, 2);
			document.add(p1);
			Image img = Image.getInstance("./src/main/resources/" + subscription.getEmail() + ".png");
			img.setAlignment(Element.ALIGN_RIGHT);
			document.add(img);
			File file = new File("./src/main/resources/" + subscription.getEmail() + ".png");
			file.delete();
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
	}

	private static void leaveEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
}