package com.esprit.ProtectHer.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.esprit.ProtectHer.Repository.TrainingRepository;
import com.esprit.ProtectHer.Service.ITrainingSercive;
import com.esprit.ProtectHer.Service.PDFservice;
import com.esprit.ProtectHer.entity.Training;
import com.itextpdf.text.pdf.qrcode.WriterException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(tags = "Training Menage")
@RequestMapping("/training")
public class TrainingController {
	
	@Autowired
	ITrainingSercive trainingService;
	
	@Autowired
	PDFservice pdfService;
	
	@Autowired
	TrainingRepository trainingRepository;
	
	// http://localhost:8081/SpringMVC/formation/assignApprenantToFormation
			@ApiOperation(value = "assigner un apprenant à une formation")
			@PutMapping("/addLearnerToTraining")
			@ResponseBody
			public void addLearnerToTraining(@RequestParam("id") long id, @RequestParam("idTraining") long idTraining) {
				trainingService.addLearnerToTraining(id, idTraining);
			}
// http://localhost:8081/SpringMVC/Training/cancel-Training/{idTraining}
			
			@ApiOperation(value = "Annuler une formation ")
			@PutMapping("/removeTraining/{idTraining}")
			@ResponseBody
			public void removeTraining(@PathVariable("idTraining") Long idTraining) {
				trainingService.cancelTraining(idTraining);
					}
	// http://localhost:8081/SpringMVC/Training/generatePdfReport
			@ApiOperation(value = "generer un pdf")
			@PostMapping("/generatePdfReport")
			@ResponseBody
			public void generatePdfReport() throws WriterException, IOException {
				pdfService.generatePdfReport();
			}
			
	// http://localhost:8081/SpringMVC/Training/modify-Training
			@ApiOperation(value = "Modifier une formation ")
			@PutMapping("/modify-Training")
			@ResponseBody
			public Training modifyTraining(@RequestBody Training t) {
				return trainingService.updateTraining(t);
			}
			
	// http://localhost:8081/SpringMVC/Training/retrieve-training/{idTraining}
			@ApiOperation(value = "Rechercher une formation")
			@GetMapping("/retrieve-training/{idTraining}")
			@ResponseBody
			public Training retrieveTraining(@PathVariable("idTraining") Long idTraining) {
				return trainingService.retrieveTraining(idTraining);
			}
			
			
			 @PutMapping("/PhotoAdvertisings")
			 @ResponseBody
			 public Training uploadphotoTraining(@RequestParam Long trainingId ,@RequestPart("file") MultipartFile file)
			  {
			 try {
			 Training training = trainingRepository.findById(trainingId).orElse(null);
			 if (training!= null) {
			 File directory = new File("upload//");
			 if (!directory.exists())
			 directory.mkdir();
			 byte[] bytes = new byte[0];
			 bytes = file.getBytes();
			 Files.write(Paths.get("upload//" + file.getOriginalFilename()), bytes);
			 training.setPhoto(Paths.get("upload//" + file.getOriginalFilename()).toString());
			 return trainingRepository.save(training);
			 }}
			 catch (Exception e) {
			 e.printStackTrace();
			 }
			 return null;
			
			
			  }
			 
			 // http://localhost:8081/SpringMVC/Training/retrieve-all-trainings
				@ApiOperation(value = "Récupérer la liste des formations")
				@GetMapping("/retrieve-all-trainings")
				@ResponseBody
				public List<Training> getTrainings() {
					return trainingService.retrieveAlltrainings();
				}

}
