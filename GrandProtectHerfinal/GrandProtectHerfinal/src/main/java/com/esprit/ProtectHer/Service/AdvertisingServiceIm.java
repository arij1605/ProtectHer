package com.esprit.ProtectHer.Service;


import com.esprit.ProtectHer.entity.Advertising;



public interface AdvertisingServiceIm {

	public Advertising addAdvertising(Advertising advertising);

	Advertising updateAdvertising(Long advertisingtId, Advertising a);

	void deleteAdvertising(Long id);

	Advertising retrieveAdvertising(Long id);

	void assignUserToAdvertising(Long idAdvertising, Long id);
	
	
	
}
