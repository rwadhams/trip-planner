package com.wadhams.trip.planner.app

import java.time.LocalDate

import com.wadhams.trip.planner.dto.LocationNightsDTO
import com.wadhams.trip.planner.report.TripPlannerReportService
import com.wadhams.trip.planner.service.TripPlannerXMLService

class TripPlannerApp {
	static main(args) {
		println 'TripPlannerApp started...'
		println ''
		
		if (args.size() == 1) {
			String filename = "TripPlanner${args[0]}.xml"
			println "\tFilename: $filename"
			println ''

			TripPlannerApp app = new TripPlannerApp()
			app.execute(filename)
		}
		else {
			println "Invalid number of arguments. args.size(): ${args.size()}"
			println ''
			println 'Usage: TripPlannerApp <file suffix number>'
			println '<file suffix number> = must be a number (nn = 01, 02, 03...99).'
			println 'Filename pattern will be TripPlanner<nn>.xml'
			println ''
		}
		
		println 'TripPlannerApp ended.'
	}
	
	def execute(String filename) {
		TripPlannerXMLService xmlService = new TripPlannerXMLService()
		LocalDate startDate = xmlService.getStartDate(filename)
		List<LocationNightsDTO> lnList = xmlService.buildLocationNightsList(filename)

		TripPlannerReportService reportService = new TripPlannerReportService()
		reportService.report(startDate, lnList)
		
		println '\tTrip Planning report can be found in the \'out\' folder.'
		println ''
	}
	
}
