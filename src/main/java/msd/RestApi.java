package msd;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RestApi {
	
	@Autowired
	GameSummaryDao gameSummaryDAO;
	
	@GetMapping("/gamesummary")
	public Collection<GameSummary> getGameSummarys() {
		return gameSummaryDAO.getAllGameSummaries();
	}
	
}
