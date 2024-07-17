package com.wadhams.trip.planner.report

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.wadhams.trip.planner.dto.LocationNightsDTO

class DailyTripPlannerReportService {
	def report(LocalDate startingDate, List<LocationNightsDTO> lnList) {
		File f = new File("out/daily-trip-planning-report.txt")
		
		f.withPrintWriter {pw ->
			pw.println 'DAILY TRIP PLANNING REPORT'
			pw.println '--------------------------'
	
			report(startingDate, lnList, pw)
		}
	}
	def report(LocalDate startingDate, List<LocationNightsDTO> lnList, PrintWriter pw) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern('EEE, MMM d, yyyy')
		
		LocalDate reportDate = startingDate
		lnList.each {ln ->
			//println ln
			boolean firstLine = true
			String nightText = (ln.nights == 1) ? 'night' : 'nights'
			ln.nights.times {
				String firstText = "...: ${ln.nights} $nightText${(ln.campsite) ? ', Staying at: ' + ln.campsite : ''}${(ln.thingsToDo) ? ', ThingsToDo: ' + ln.thingsToDo : ''}"
				pw.println "${dtf.format(reportDate)}\t${ln.location}${(firstLine) ? firstText : ''}"
				firstLine = false
				reportDate = reportDate.next()
			}
			if (ln.nextDistance > 0) {
				pw.println ''
				pw.println "${ln.nextDistance} Kms"
			}
			pw.println ''
		}
	}
}
