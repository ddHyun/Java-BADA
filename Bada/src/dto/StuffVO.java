package dto;

public class StuffVO {//�ֹ���ǰ ���� ����
	private int box;
	private String code, note, stuff;
	private float size, weight;
	
	//�̱���
	private static StuffVO instance = new StuffVO();
	
	private StuffVO(){};
	
	public static StuffVO getInstance() {
		return instance;
	}
	
	public float getSize() {
		return size;
	}
	public void setSize(float size) {
		this.size = size;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
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
