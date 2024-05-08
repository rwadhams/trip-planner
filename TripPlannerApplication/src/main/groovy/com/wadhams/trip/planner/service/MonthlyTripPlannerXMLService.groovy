package com.wadhams.trip.planner.service

import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import com.wadhams.trip.planner.dto.LocationMonthsDTO

class MonthlyTripPlannerXMLService {
	
	YearMonth getStartYearMonth(String filename) {
		def tripPlanner = slurpXMLFile("data/$filename")
		
		YearMonth startYearMonth = YearMonth.parse(tripPlanner.startYearMonth.text())
		//println startYearMonth
		
		return startYearMonth
	}
	
	List<LocationMonthsDTO> buildLocationMonthsList(String filename) {
		List<LocationMonthsDTO> locationMonthsList = []
		
		def tripPlanner = slurpXMLFile("data/$filename")
		def lmList = tripPlanner.locationMonths

		lmList.each {lm ->
			//println lm
			locationMonthsList << build(lm)
		}

		return locationMonthsList
	}
	
	LocationMonthsDTO build(lm) {
			String location = lm.location
			String months = lm.months
			
			return new LocationMonthsDTO(location : "$location", months : Integer.parseInt(months))
	}
	
	def slurpXMLFile(String filename) {
		File tripPlannerFile
		URL resource = getClass().getClassLoader().getResource(filename)
		if (resource == null) {
			throw new IllegalArgumentException("file not found!")
		}
		else {
			tripPlannerFile = new File(resource.toURI())
		}
		
		return new XmlSlurper().parse(tripPlannerFile)
	}
	
}
