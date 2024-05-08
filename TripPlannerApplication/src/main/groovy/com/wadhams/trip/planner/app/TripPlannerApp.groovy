package com.wadhams.trip.planner.app

import com.wadhams.trip.planner.dto.LocationMonthsDTO
import com.wadhams.trip.planner.dto.LocationNightsDTO
import com.wadhams.trip.planner.report.DailyTripPlannerReportService
import com.wadhams.trip.planner.report.MonthlyTripPlannerReportService
import com.wadhams.trip.planner.service.DailyTripPlannerXMLService
import com.wadhams.trip.planner.service.MonthlyTripPlannerXMLService
import com.wadhams.trip.planner.type.Mode
import java.time.LocalDate
import java.time.YearMonth

class TripPlannerApp {
	static main(args) {
		println 'TripPlannerApp started...'
		println ''

		if (args.size() == 2) {
			TripPlannerApp app = new TripPlannerApp()
			
			Mode mode = Mode.findByName("${args[0]}")
			
			if (mode == Mode.Unknown) {
				println "Unknown \'mode\' parameter: ${args[0]}"
				println ''
				println 'See \'Usage\' above. Application did not run.'
				println ''
			}
			else if (mode == Mode.Daily) {
				String filename = "DailyTripPlanner${args[1]}.xml"
				println "\tFilename: $filename"
				println ''
				app.executeDaily(filename)
			}
			else if (mode == Mode.Monthly) {
				String filename = "MonthlyTripPlanner${args[1]}.xml"
				println "\tFilename: $filename"
				println ''
				app.executeMonthly(filename)
			}
			else {
				println "ERROR: Should never happen."
				println ''
				println 'See \'Usage\' above. Application did not run.'
				println ''
				throw new RuntimeException()
			}
		}
		else {
			println "Invalid number of arguments. args.size(): ${args.size()}"
			println ''
			println 'Usage: TripPlannerApp <mode> <file suffix number>'
			println '<mode> = Daily | Monthly'
			println '<file suffix number> = must be a number (nn = 01, 02, 03...99).'
			println 'Filename pattern will be either DailyTripPlanner<nn>.xml or MonthlyTripPlanner<nn>.xml'
			println ''
		}
		
		println 'TripPlannerApp ended.'
	}
	
	def executeDaily(String filename) {
		DailyTripPlannerXMLService xmlService = new DailyTripPlannerXMLService()
		LocalDate startDate = xmlService.getStartDate(filename)
		List<LocationNightsDTO> lnList = xmlService.buildLocationNightsList(filename)

		DailyTripPlannerReportService reportService = new DailyTripPlannerReportService()
		reportService.report(startDate, lnList)
		
		println '\tDaily Trip Planning report can be found in the \'out\' folder.'
		println ''
	}
	
	def executeMonthly(String filename) {
		MonthlyTripPlannerXMLService xmlService = new MonthlyTripPlannerXMLService()
		YearMonth startYearMonth = xmlService.getStartYearMonth(filename)
		List<LocationMonthsDTO> lmList = xmlService.buildLocationMonthsList(filename)
		
		MonthlyTripPlannerReportService reportService = new MonthlyTripPlannerReportService()
		reportService.report(startYearMonth, lmList)
		
		println '\tMonthly Trip Planning report can be found in the \'out\' folder.'
		println ''
	}
	
}
