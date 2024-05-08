package com.wadhams.trip.planner.report

import java.time.YearMonth
import java.time.format.DateTimeFormatter
import com.wadhams.trip.planner.dto.LocationMonthsDTO
import com.wadhams.trip.planner.dto.LocationNightsDTO

class MonthlyTripPlannerReportService {
	def report(YearMonth startYearMonth, List<LocationMonthsDTO> lmList) {
		File f = new File("out/monthly-trip-planning-report.txt")
		
		f.withPrintWriter {pw ->
			pw.println 'MONTHLY TRIP PLANNING REPORT'
			pw.println '----------------------------'
	
			report(startYearMonth, lmList, pw)
		}
	}
	def report(YearMonth startYearMonth, List<LocationMonthsDTO> lmList, PrintWriter pw) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern('MMM yyyy')
		
		YearMonth reportMonth = startYearMonth
		lmList.each {lm ->
			//println lm
			boolean firstLine = true
			String monthText = (lm.months == 1) ? 'month' : 'months'
			lm.months.times {
				String firstText = "...: ${lm.months} $monthText"
				pw.println "${dtf.format(reportMonth)}\t${lm.location}${(firstLine) ? firstText : ''}"
				firstLine = false
				reportMonth = reportMonth.plusMonths(1L)
			}
			pw.println ''
		}
	}
}
