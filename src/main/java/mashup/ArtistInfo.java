package mashup;

import java.util.List;

public class ArtistInfo {
	private final String mbid;
	private final String description;
	private final List<Album> albums;
	
	public ArtistInfo(String mbid, String description, List<Album> albums) {
		this.mbid = mbid;
		this.description = description;
		this.albums = albums;
	}
	
	public String getMbid() {
		return mbid;
	}
	
	public String getDescription() {
		return description;
	}

	public List<Album> getAlbums() {
		return albums;
	}
}
