package mashup;

public class Album {
	private final String title;
	private final String id;
	private final String image;
	
	public Album(String title, String id, String image) {
		this.title = title;
		this.id = id;
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public String getId() {
		return id;
	}

	public String getImage() {
		return image;
	}
	
}
