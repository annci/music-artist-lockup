package mashup.wikipedia;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class Wikipedia {
	
    private static final Logger log = LoggerFactory.getLogger(Wikipedia.class);
    RestTemplate restTemplate = new RestTemplate();

    /**
     * Get a description of the artist with resource id from Wikipedia
     * 
     * @param id the resource id of the artist
     * @return a description of the artist
     */
	@SuppressWarnings("unchecked")
	public String getDescription(String id) {
        String extract = "";
        
		Map<String,Object> map = restTemplate.getForObject("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&exintro=true&redirects=true&titles=" + id, Map.class);
		log.info("Wikipedia map: " + map.toString());
		
        Map<String, Object> query = (Map<String, Object>)map.get("query");
        if (query == null) {
            log.error("Query doesn't exist");
        	return extract;
        }
        Map<String, Object> pages = (Map<String, Object>)query.get("pages");
        if (pages == null) {
            log.error("Pages doesn't exist");
        	return extract;
        }
        Map<String, Object> page = (Map<String, Object>)pages.entrySet().iterator().next().getValue();
        if (page == null) {
            log.error("Page doesn't exist");
        	return extract;
        }

        extract = (String)page.get("extract");

        log.info("extract: " + extract);

        return extract;
	}

}
