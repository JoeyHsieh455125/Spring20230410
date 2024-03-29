package spring.mvc.session17.entity;

public class Division {
	private Integer x;
	private Integer y;
	private Integer result;
	
	{
		this.x = Integer.valueOf(0);
		this.y = 0;
		this.result = 0;
	}
	
	public Division() {
		super();
	}

	public Integer getX() {
		return x;
	}
	
	public void setX(Integer x) {
		this.x = x;
	}
	
	public Integer getY() {
		return y;
	}
	
	public void setY(Integer y) {
		this.y = y;
	}
	
	public Integer getResult() {
		return result;
	}
	
	public void setResult(Integer result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return "Division [x=" + x + ", y=" + y + ", result=" + result + "]";
	}
}
