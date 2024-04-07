package com.wadhams.trip.planner.app

import com.wadhams.trip.planner.dto.LocationNightsDTO
import com.wadhams.trip.planner.report.TripPlannerReportService
import com.wadhams.trip.planner.service.TripPlannerXMLService
import java.time.LocalDate

class TripPlannerTestApp {
	static main(args) {
		println 'TripPlannerTestApp started...'
		println ''

		TripPlannerXMLService xmlService = new TripPlannerXMLService()
		LocalDate startDate = xmlService.getStartDate('TripPlannerTest.xml')
		println "startDate...: $startDate"
		println ''
		
		List<LocationNightsDTO> lnList = xmlService.buildLocationNightsList('TripPlannerTest.xml')
		println 'List of LocationNightsDTOs:'
		lnList.each {dto ->
			println "\t$dto"
		}
		println ''
		
//		PrintWriter pw = new PrintWriter(System.out, true)
//		TripPlannerReportService reportService = new TripPlannerReportService()
//		reportService.report(startDate, lnList, pw)
//		println ''
		
		println 'TripPlannerTestApp ended.'
	}
}
