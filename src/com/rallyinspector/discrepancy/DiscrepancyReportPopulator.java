package com.rallyinspector.discrepancy;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class DiscrepancyReportPopulator {
	
	final static Logger logger = Logger.getLogger(DiscrepancyReportPopulator.class);

	public JSONArray createDiscrepancyTablePopulatorObject(JSONArray resultJsonArray) {	
		
		JSONArray discrepancyReports = new JSONArray();		
		try {
			JSONObject discrepancyType = new JSONObject();
			discrepancyType.put("id", 1);
			
			for (int i = 0; i < resultJsonArray.length(); i++) {

				JSONObject userStory = resultJsonArray.getJSONObject(i);

				JSONObject discrepancyReport = new JSONObject();
				discrepancyReport.put("formattedID", userStory.getString("FormattedID"));
				discrepancyReport.put("artifactName", userStory.getString("Name"));
				discrepancyReport.put("artifactRef", userStory.getString("_ref"));
				discrepancyReport.put("discType", discrepancyType);
				discrepancyReport.put("teamName", userStory.getJSONObject("Project").getString("_refObjectName"));
				
				discrepancyReports.put(discrepancyReport);
			}

		} catch (JSONException e) {
			logger.error("Problem", e);
		}
		return discrepancyReports;
	}
}