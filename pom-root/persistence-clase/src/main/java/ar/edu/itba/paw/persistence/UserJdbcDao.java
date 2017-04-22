package ar.edu.itba.paw.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.User;
import ar.edu.itba.paw.persistence.UserDao;

@Repository
public class UserJdbcDao implements UserDao {	
	private final static RowMapper<User> ROW_MAPPER = new RowMapper<User>() {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new User(rs.getInt("userid"), rs.getString("username"), rs.getString("password"));
		}
		
	};
	
	private JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert jdbcInsert;

	@Autowired
	public UserJdbcDao(final DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
		jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
					.withTableName("users")
					.usingGeneratedKeyColumns("userid");		
	}
	
	public User findById(int id) {
		final List<User> list = jdbcTemplate.query("SELECT * FROM users WHERE userid = ?", ROW_MAPPER, id);
		
		if (list.isEmpty())
			return null;
		
		return list.get(0);
	}

	public User create(String username, String password) {
		final Map<String, Object> args = new HashMap<>();
		args.put("username", username);
		args.put("password", password);
		
		final Number userId = jdbcInsert.executeAndReturnKey(args);
		
		return new User(userId.intValue(), username, password);
	}

	public User findByName(String username) {
		final List<User> query = jdbcTemplate.query("SELECT * FROM users WHERE username = ?", ROW_MAPPER, username);
		
		if (query.isEmpty())
			return null;
		
		return query.get(0);
	}
	
}
