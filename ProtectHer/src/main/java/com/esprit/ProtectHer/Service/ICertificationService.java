package com.esprit.ProtectHer.Service;

import com.esprit.ProtectHer.entity.Certification;


public interface ICertificationService {

	void addCertification(Certification certification);
	
	void cancelCertification(Long idCertification);

	Certification updateCertification(Certification c);

	Certification retrieveCertification(Long idCertification);

}
