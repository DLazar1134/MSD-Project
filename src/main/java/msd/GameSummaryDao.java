package msd;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GameSummaryDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Collection<GameSummary> getAllGameSummaries() {		
		return jdbcTemplate.query("Select * from GAMESUMMARY", new
				BeanPropertyRowMapper<GameSummary>(GameSummary.class));
	}
}
