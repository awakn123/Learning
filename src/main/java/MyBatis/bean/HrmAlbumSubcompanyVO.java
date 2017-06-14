package MyBatis.bean;

public class HrmAlbumSubcompanyVO {
	private int id;

	private double toTALsize;
	private int[] arr = new int[1];

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getToTALsize() {
		return toTALsize;
	}

	public void setToTALsize(double toTALsize) {
		this.toTALsize = toTALsize;
	}

	public int[] getArr() {
		return arr;
	}

	public void setArr(int[] arr) {
		this.arr = arr;
	}
}
