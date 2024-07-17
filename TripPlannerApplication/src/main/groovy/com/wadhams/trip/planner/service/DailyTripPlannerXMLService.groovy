package com.wadhams.trip.planner.service

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.wadhams.trip.planner.dto.LocationNightsDTO

class DailyTripPlannerXMLService {
	
	LocalDate getStartDate(String filename) {
		def tripPlanner = slurpXMLFile("data/$filename")
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern('dd/MM/yyyy')
		LocalDate startDate = LocalDate.parse(tripPlanner.startDate.text(), dtf)
		//println startDate
		return startDate
	}
	
	List<LocationNightsDTO> buildLocationNightsList(String filename) {
		List<LocationNightsDTO> locationNightsList = []
		
		def tripPlanner = slurpXMLFile("data/$filename")
		def lnList = tripPlanner.locationNights

		lnList.each {ln ->
			//println ln
			locationNightsList << build(ln)
		}

		return locationNightsList
	}
	
	LocationNightsDTO build(ln) {
			String location = ln.location
			String nights = ln.nights
			String campsite = ln.campsite
			String thingsToDo = ln.thingsToDo
			
			//nextDistance is an optional integer
			String nextDistanceInput = ln.nextDistance
			int nextDistance = 0	//default
			if (nextDistanceInput) {
				nextDistance = Integer.parseInt(nextDistanceInput)
			} 
			
			return new LocationNightsDTO(location : "$location", nights : Integer.parseInt(nights), campsite : "$campsite", thingsToDo : "$thingsToDo", nextDistance : nextDistance)
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
