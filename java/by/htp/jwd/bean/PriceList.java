package by.htp.jwd.bean;

public class PriceList {
	
	private int id;
	private double cost_hour;
	private double cost_dayr;
	private double sale;
	private Car car;
	

	public PriceList() {
		super();
	}

	public PriceList(double cost_hour, double cost_dayr, double sale) {
		super();
		this.cost_hour = cost_hour;
		this.cost_dayr = cost_dayr;
		this.sale = sale;
		
	}
	
	public PriceList(double cost_hour, double cost_dayr, double sale, Car car) {
		super();
		this.cost_hour = cost_hour;
		this.cost_dayr = cost_dayr;
		this.sale = sale;
		this.car = car;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCost_hour() {
		return cost_hour;
	}

	public void setCost_hour(double cost_hour) {
		this.cost_hour = cost_hour;
	}

	public double getCost_dayr() {
		return cost_dayr;
	}

	public void setCost_dayr(double cost_dayr) {
		this.cost_dayr = cost_dayr;
	}

	public double getSale() {
		return sale;
	}

	public void setSale(double sale) {
		this.sale = sale;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cost_dayr);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(cost_hour);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		temp = Double.doubleToLongBits(sale);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PriceList other = (PriceList) obj;
		if (car == null) {
			if (other.car != null)
				return false;
		} else if (!car.equals(other.car))
			return false;
		if (Double.doubleToLongBits(cost_dayr) != Double.doubleToLongBits(other.cost_dayr))
			return false;
		if (Double.doubleToLongBits(cost_hour) != Double.doubleToLongBits(other.cost_hour))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(sale) != Double.doubleToLongBits(other.sale))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PriceList [id=" + id + ", cost_hour=" + cost_hour + ", cost_dayr=" + cost_dayr + ", sale=" + sale
				+ ", car=" + car + "]";
	}
	
	

}
