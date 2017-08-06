package components.common;

public class Result {
	
	private String result;
	private String resultText;
	private String resultLink;
	
	public Result(String result,String resultText,String resultLink) {
		this.result = result;
		this.resultText = resultText;
		this.resultLink =resultLink;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public void setResultLink(String resultLink) {
		this.resultLink = resultLink;
	}
	
	public String getResult() {
		return this.result;
	}
	
	public String getResultLink() {
		return this.resultLink;
	}
	public void setResultText(String resultText) {
		this.resultText = resultText;
	}
	
	public String getResultText() {
		return this.resultText;
	}	
}