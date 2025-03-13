package com.quantridulieu.hotelManagement.entities;

public class StatisticsExport {
	private int month;
	private Double revenue;
	private Double expense;
	private Double profit;
	
	public StatisticsExport() {}
	
	public StatisticsExport(int month, Double revenue, Double expense, Double profit) {
		super();
		this.month = month;
		this.revenue = revenue;
		this.expense = expense;
		this.profit = profit;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Double getRevenue() {
		return revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	public Double getExpense() {
		return expense;
	}

	public void setExpense(Double expense) {
		this.expense = expense;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}
	
	
}
