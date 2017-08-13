package components.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Report {
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;
	private String reportPath;
	private String completeReportPath;

	public Report(WebDriver driver) {
		this.driver = driver;
		reportPath = Reporter.createResultFolder();
	}

	public void updateReport(String log1, String Status) {

		if (Status.equalsIgnoreCase("FAIL")) {
			String imgFilePath = takeScreenShot();
			log1 = log1 + "<a href ='" + imgFilePath + "'/> click";
			logger.log(LogStatus.FAIL, log1);
		} else if (Status.equalsIgnoreCase("PASS")) {
			String imgFilePath = takeScreenShot();
			log1 = log1 + "<a href ='" + imgFilePath + "'/> click";
			logger.log(LogStatus.PASS, log1);
		}

	}

	public void wrapReport() {
		report.endTest(logger);
		report.flush();
		System.out.println(logger.getRunStatus().toString());
	}

	public void setTestSummary(String testSummary) {

		logger = report.startTest(testSummary);
	}

	public void setTestCaseName(String testCaseName) {
		completeReportPath = reportPath + testCaseName + ".html";
		report = new ExtentReports(reportPath + testCaseName + ".html", false);
		report.config().documentTitle("Automation Report").reportHeadline(testCaseName)
				.reportName("Regression Suite - >");
	}

	public String takeScreenShot() {
		String currentDate = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String imgFilePath = Reporter.createResultFolderImage(reportPath) + currentDate + ".png";
		try {
			FileUtils.copyFile(scrFile, new File(imgFilePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("***Placed screen shot in " + imgFilePath + " ***");

		return imgFilePath;
	}

	public void sendReportInMain() {
		try {
			// Create the email message
			MultiPartEmail email = new MultiPartEmail();
			//HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setSSLOnConnect(true);
			email.setAuthenticator(new DefaultAuthenticator("anjanmondal@gmail.com", "Dataone.in2432"));
			email.addTo("anjanmondal@gmail.com", "Anjan Mondal");
			email.setFrom("me@apache.org", "Me");
			email.addBcc("yxrkt512@gmail.com");
			email.setSubject("Test Result");

			// embed the image and get the content id
			/*File myFile = new File(completeReportPath);
			URL mailUrl = myFile.toURI().toURL();
			String completeHtml =readFile(mailUrl);
			// url = new URL("http://www.apache.org/images/asf_logo_wide.gif");

			// TODO Auto-generated catch block

			//String cid = email.embed(mailUrl, "Apache logo");

			// set the html message
			email.setHtmlMsg(completeHtml);
			// email.setContent(cid);
			// set the alternative message
			email.setTextMsg("Your email client does not support HTML messages");
			 */
			
			 EmailAttachment attachment = new EmailAttachment();
			 attachment.setPath(completeReportPath);
			 attachment.setDisposition(EmailAttachment.ATTACHMENT);
			 attachment.setDescription("Execution Result");
			 attachment.setName("Result.html");
			// add the attachment
			  email.attach(attachment);

			// send the email
			email.send();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String readFile(URL url){
		String text = null ;
		try {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null)
				text = text+inputLine;
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return text;
	}

}
