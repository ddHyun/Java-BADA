package dto;

public class StuffVO {//주문물품 관련 정보
	private int size, weight, box;
	private String code, note, stuff;	
	
	//싱글톤
	private static StuffVO instance = new StuffVO();
	
	private StuffVO(){};
	
	public static StuffVO getInstance() {
		return instance;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getBox() {
		return box;
	}
	public void setBox(int box) {
		this.box = box;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getStuff() {
		return stuff;
	}
	public void setStuff(String stuff) {
		this.stuff = stuff;
	}
	
	
}
