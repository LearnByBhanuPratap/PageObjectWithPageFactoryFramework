package com.test.automation.uiAutomation.customListner;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;
/**
 * 
 * @author Bhanu Pratap
 *
 */
public class Retry implements IRetryAnalyzer {
	public static final Logger log = Logger.getLogger(Retry.class.getName());
	private int retryCount = 0;
	private int maxRetryCount = 3;
	
	public boolean retry(ITestResult result) {
		if (retryCount < maxRetryCount) {
			log("Retrying test " + result.getName() + " with status " + getResultStatusName(result.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
			retryCount++;
			return true;
		}
		return false;
	}
	public String getResultStatusName(int status) {
		String resultName = null;
		if (status == 1)
			resultName = "SUCCESS";
		if (status == 2)
			resultName = "FAILURE";
		if (status == 3)
			resultName = "SKIP";
		return resultName;
	}
	
	public void log(String data){
		log.info(data);
		Reporter.log(data);
	}
	
}
