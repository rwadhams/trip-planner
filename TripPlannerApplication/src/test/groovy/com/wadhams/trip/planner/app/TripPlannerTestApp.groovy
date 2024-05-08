package com.wadhams.trip.planner.app

import com.wadhams.trip.planner.dto.LocationMonthsDTO
import com.wadhams.trip.planner.dto.LocationNightsDTO
import com.wadhams.trip.planner.report.DailyTripPlannerReportService
import com.wadhams.trip.planner.report.MonthlyTripPlannerReportService
import com.wadhams.trip.planner.service.DailyTripPlannerXMLService
import com.wadhams.trip.planner.service.MonthlyTripPlannerXMLService
import java.time.LocalDate
import java.time.YearMonth

class TripPlannerTestApp {
	static main(args) {
		println 'TripPlannerTestApp started...'
		println ''

		TripPlannerTestApp app = new TripPlannerTestApp()
		
		//Daily
		//app.testDaily()
		
		//Monthly
		app.testMonthly()
		
		println 'TripPlannerTestApp ended.'
	}
	
	def testDaily() {
		DailyTripPlannerXMLService xmlService = new DailyTripPlannerXMLService()
		LocalDate startDate = xmlService.getStartDate('DailyTripPlannerTest.xml')
		println "startDate...: $startDate"
		println ''
		
		List<LocationNightsDTO> lnList = xmlService.buildLocationNightsList('DailyTripPlannerTest.xml')
		println 'List of LocationNightsDTOs:'
		lnList.each {dto ->
			println "\t$dto"
		}
		println ''
		
		PrintWriter pw = new PrintWriter(System.out, true)
		DailyTripPlannerReportService reportService = new DailyTripPlannerReportService()
		reportService.report(startDate, lnList, pw)
		println ''
	}
	
	def testMonthly() {
		MonthlyTripPlannerXMLService xmlService = new MonthlyTripPlannerXMLService()
		YearMonth startYearMonth = xmlService.getStartYearMonth('MonthlyTripPlannerTest.xml')
		println "startYearMonth...: $startYearMonth"
		println ''
		
		List<LocationMonthsDTO> lmList = xmlService.buildLocationMonthsList('MonthlyTripPlannerTest.xml')
		println 'List of LocationMonthsDTOs:'
		lmList.each {dto ->
			println "\t$dto"
		}
		println ''
		
		PrintWriter pw = new PrintWriter(System.out, true)
		MonthlyTripPlannerReportService reportService = new MonthlyTripPlannerReportService()
		reportService.report(startYearMonth, lmList, pw)
		println ''
	}
}
