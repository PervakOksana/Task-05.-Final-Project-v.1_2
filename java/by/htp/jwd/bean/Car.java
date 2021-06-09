package by.htp.jwd.bean;

import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Blob;
import java.util.Arrays;

public class Car {

	private int id;
	private String brand;
	private String describe_of_brand;
	private String bodywork;
	private String power;
	private String transmission;
	private int number_of_doors;
	private int year;
	private String photoPath;
	private Blob photoB;
	
	public Car() {
		super();
	}

	
	
	public Car(String brand, String describe_of_brand, String bodywork, String power, String transmission,
			int number_of_doors, int year, String photoPath) {
		super();
		this.brand = brand;
		this.describe_of_brand = describe_of_brand;
		this.bodywork = bodywork;
		this.power = power;
		this.transmission = transmission;
		this.number_of_doors = number_of_doors;
		this.year = year;
		this.photoPath = photoPath;
	}

	


	public Car(int id, String brand, String describe_of_brand, String bodywork, String power, String transmission,
			int number_of_doors, int year, String photoPath) {
		super();
		this.id = id;
		this.brand = brand;
		this.describe_of_brand = describe_of_brand;
		this.bodywork = bodywork;
		this.power = power;
		this.transmission = transmission;
		this.number_of_doors = number_of_doors;
		this.year = year;
		this.photoPath = photoPath;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescribe_of_brand() {
		return describe_of_brand;
	}

	public void setDescribe_of_brand(String describe_of_brand) {
		this.describe_of_brand = describe_of_brand;
	}

	public String getBodywork() {
		return bodywork;
	}

	public void setBodywork(String bodywork) {
		this.bodywork = bodywork;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public int getNumber_of_doors() {
		return number_of_doors;
	}

	public void setNumber_of_doors(int number_of_doors) {
		this.number_of_doors = number_of_doors;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Blob getPhotoB() {
		return photoB;
	}

	public void setPhotoB(Blob photoB) {
		this.photoB = photoB;
	}



	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", describe_of_brand=" + describe_of_brand + ", bodywork="
				+ bodywork + ", power=" + power + ", transmission=" + transmission + ", number_of_doors="
				+ number_of_doors + ", year=" + year + ", photoPath=" + photoPath + ", photoB=" + photoB + "]";
	}

	
	
}
