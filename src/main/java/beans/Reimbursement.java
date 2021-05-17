package beans;

import java.time.LocalDateTime;



public class Reimbursement {
	
	private int reimbId;
	private Double amount;
	private LocalDateTime submitted;
	private LocalDateTime resolved;
	private String description;
	private User author;
	private User resolver;
	private int status;
	private ReimType type;
	
	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getAuthor() {
		return author;
	}
	
	public User getResolver() {
		return resolver;
	}
	
	public void setAuthor(User author) {
		this.author = author;
	}
	
	public void setResolver(User r) {
		
		this.resolver = r;
	}
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		if (amount <= 0) {
			throw new UnsupportedOperationException("Amount cannot be negative or 0");
		}
		this.amount = amount;
	}
	public ReimType getType() {
		return type;
	}
	
	public void setType(ReimType type) {
		this.type = type;
	}
	
	public LocalDateTime getSubmitted() {
		return submitted;
	}
	public void setSubmitted(LocalDateTime timestamp) {
		this.submitted = timestamp;
	}
	public void setSubmitted() {
		this.submitted = LocalDateTime.now();
	}
	
	public LocalDateTime getResolved() {
		return resolved;
	}
	public void setResolved(LocalDateTime timestamp) {
		this.resolved = timestamp;
	}
	public void setResolved() {
		this.resolved = LocalDateTime.now();
	}
	
	
		

}
