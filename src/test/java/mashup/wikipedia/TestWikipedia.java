package mashup.wikipedia;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class TestWikipedia {
	@Mock
	RestTemplate restTemplate;
	
	@InjectMocks
	private Wikipedia wikipedia;
	
	//private final Map<String,Object> map  = new HashMap<>();
	
	@Test
	public void test() {
		Wikipedia wikipedia = new Wikipedia();
		String desc = wikipedia.getDescription("");
		assertEquals(desc, "");
	}

	@Test 
	public void shouldGetDescription() {
		// Given
		String d = "Artisten";
		Map<String,Object> map  = new HashMap<>();
		Map<String,Object> page  = new HashMap<>();
		page.put("extract", d);
		map.put("99", page);
		Mockito.when(restTemplate.getForObject(
				Mockito.anyString(),
				Mockito.any()))
			.thenReturn(map);
		
		// When
		String desc = wikipedia.getDescription("99");
		
		// Then
		assertEquals(d, desc);
	}
}
