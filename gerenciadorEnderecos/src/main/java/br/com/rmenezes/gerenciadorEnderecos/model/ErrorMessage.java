package br.com.rmenezes.gerenciadorEnderecos.model;

public class ErrorMessage {

	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String toJson(){
		
		return "{\"Error\":\"" + this.message + "\"}";
	}
	
}
