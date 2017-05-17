package mashup.musicbrainz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MusicBrainzResponse {
	
    private static final Logger log = LoggerFactory.getLogger(MusicBrainzResponse.class);
	
	private String id;
	private List<Relation> relations;
	@JsonProperty("release-groups")
	private List<ReleaseGroup> releaseGroups;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public List<Relation> getRelations() {
		return relations;
	}

	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

	public List<ReleaseGroup> getReleaseGroups() {
		return releaseGroups;
	}

	public void setReleaseGroups(List<ReleaseGroup> releaseGroups) {
		this.releaseGroups = releaseGroups;
	}
	
	/**
	 * Get the resource name needed to fetch artist description from Wikipedia
	 * 
	 * @return resource name
	 */
	public String getWikipediaResource() {
		String wikipediaResource = "";
	    for (Relation relation : relations) {
	        if (relation.getType().equals("wikipedia")) {
	        	wikipediaResource = relation.getResource();
				log.info("wikipediaResource " + wikipediaResource);
				break;
	        }
	    }
	    
	    return wikipediaResource.substring(wikipediaResource.lastIndexOf('/') + 1);
	}	
	
}
