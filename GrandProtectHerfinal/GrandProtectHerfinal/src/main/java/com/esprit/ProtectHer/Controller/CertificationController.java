package com.esprit.ProtectHer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.ProtectHer.Service.ICertificationService;
import com.esprit.ProtectHer.entity.Certification;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(tags = "Certification Menage")
@RequestMapping("/certification")
public class CertificationController {
	
	@Autowired
	ICertificationService certificationService;
	
	// http://localhost:8081/SpringMVC/Certification/addCertification
			@ApiOperation(value = "Ajouter une certification")
			@PostMapping("/addCertification")	
			@ResponseBody
			public void addCertification(@RequestBody Certification certification) {
				certificationService.addCertification(certification);
			}
			
// http://localhost:8081/SpringMVC/Certification/cancel-Certification/{idCertification}
			
						@ApiOperation(value = "Annuler une certification ")
						@PutMapping("/removeCertification/{idCertification}")
						@ResponseBody
						public void removeCertification(@PathVariable("idCertification") Long idCertification) {
							certificationService.cancelCertification(idCertification);
								}
						
						
			// http://localhost:8081/SpringMVC/Certification/modify-Certification
						@ApiOperation(value = "Modifier une certification ")
						@PutMapping("/modify-Certification")
						@ResponseBody
						public Certification modifyCertification(@RequestBody Certification c) {
							return certificationService.updateCertification(c);
						}
						
		// http://localhost:8081/SpringMVC/Certification/retrieve-certification/{idCertification}
						@ApiOperation(value = "Rechercher une certification")
						@GetMapping("/retrieve-certification/{idCertification}")
						@ResponseBody
						public Certification retrieveCertification(@PathVariable("idCertification") Long idCertification) {
							return certificationService.retrieveCertification(idCertification);
						}
			

}
