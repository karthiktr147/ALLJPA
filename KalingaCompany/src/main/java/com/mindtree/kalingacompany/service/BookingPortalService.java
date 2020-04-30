package com.mindtree.kalingacompany.service;

import java.util.List;

import com.mindtree.kalingacompany.entity.BookingPortal;
import com.mindtree.kalingacompany.exception.service.KalingaCompanyAppServiceException;

public interface BookingPortalService {

	public BookingPortal addBookingPortalData(BookingPortal bookingPortal) throws KalingaCompanyAppServiceException;

	public List<BookingPortal> getBookingPortalData() throws KalingaCompanyAppServiceException;

	public void deleteBookingPorttalData(String portalName) throws KalingaCompanyAppServiceException;

}
