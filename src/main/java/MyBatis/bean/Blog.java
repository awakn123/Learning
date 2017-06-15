package MyBatis.bean;

public class Blog {
	private int id;
	private Author author;
	private String title;
	private byte[] varBin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte[] getVarBin() {
		return varBin;
	}

	public void setVarBin(byte[] varBin) {
		this.varBin = varBin;
	}
}
