package com.esprit.ProtectHer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.esprit.ProtectHer.entity.Certification;
import com.esprit.ProtectHer.Repository.CertificationRepository;



@Service
@Slf4j
public class CertificationService implements ICertificationService {
	
	@Autowired
	CertificationRepository certificationRepository;
	
	@Override

	public void addCertification(Certification certification) {
		
		certificationRepository.save(certification);

		}


	public void cancelCertification(Long idCertification) {
		certificationRepository.deleteById(idCertification);
		
	}


	@Override
	public Certification updateCertification(Certification c) {
		Certification certification = retrieveCertification(c.getIdCertification());
		certification.setDate(c.getDate());
		certification.setDomaine(c.getDomaine());
		certification.setConversion(c.getConversion());
	
		certificationRepository.save(certification);
		return certification;
	}


	@Override
	public Certification retrieveCertification(Long idCertification) {
		Certification certification = certificationRepository.findById(idCertification).orElse(null);
		log.info("certification : " + certification);
		return certification;
	}

}

	


