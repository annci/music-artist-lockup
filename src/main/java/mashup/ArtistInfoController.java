package mashup;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import mashup.coverart.CoverImage;
import mashup.musicbrainz.MusicBrainzResponse;
import mashup.musicbrainz.ReleaseGroup;
import mashup.wikipedia.Wikipedia;

@RestController
public class ArtistInfoController {
	
    private static final Logger log = LoggerFactory.getLogger(ArtistInfoController.class);
	
	@RequestMapping("/artist")
	public ArtistInfo artist(@RequestParam(value="mbid", defaultValue="5b11f4ce-a62d-471e-81fc-a69a8278c7da") String mbid) {
        log.info("mbid = " + mbid);
        RestTemplate restTemplate = new RestTemplate();
        MusicBrainzResponse musicBrainzResponse = restTemplate.getForObject("http://musicbrainz.org/ws/2/artist/" + mbid + "?&fmt=json&inc=url-rels+release-groups", MusicBrainzResponse.class);
        
        List<Album> albums = getAlbumList(musicBrainzResponse.getReleaseGroups());
        Wikipedia wikipedia = new Wikipedia();
        String wikiResource = musicBrainzResponse.getWikipediaResource();
        
		return new ArtistInfo(mbid, wikipedia.getDescription(wikiResource), albums);
	}
	
	/**
	 * Get an list of all album covers for the artist
	 * 
	 * @return list of album cover image urls
	 */
	public List<Album> getAlbumList(List<ReleaseGroup> releaseGroups) {
		List<Album> albumList = new ArrayList<Album>();
        RestTemplate restTemplate = new RestTemplate();
        CoverImage coverImage;
        String image = "";
        for (ReleaseGroup releaseGroup : releaseGroups) {
        	
        	try {
        		coverImage = restTemplate.getForObject("http://coverartarchive.org/release-group/" + releaseGroup.getId(), CoverImage.class);
        		image = coverImage.getImages().get(0).getImage();
        	}
        	catch (HttpClientErrorException he) {
        		image = "";
        	}
			albumList.add(new Album(releaseGroup.getTitle(), releaseGroup.getId(), image));
		}
		return albumList;
	}

}
