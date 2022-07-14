package dto;

public class AddressVO {
	private String zipcode;
	private String si;
	private String gu;
	private String ro;
	private String bun1;
	private String bun2;
	
	public AddressVO() {}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getSi() {
		return si;
	}

	public void setSi(String si) {
		this.si = si;
	}

	public String getGu() {
		return gu;
	}

	public void setGu(String gu) {
		this.gu = gu;
	}

	public String getRo() {
		return ro;
	}

	public void setRo(String ro) {
		this.ro = ro;
	}

	public String getBun1() {
		return bun1;
	}

	public void setBun1(String bun1) {
		this.bun1 = bun1;
	}

	public String getBun2() {
		return bun2;
	}

	public void setBun2(String bun2) {
		this.bun2 = bun2;
	}
	
}
