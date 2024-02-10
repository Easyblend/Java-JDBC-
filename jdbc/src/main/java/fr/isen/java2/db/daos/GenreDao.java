package fr.isen.java2.db.daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.isen.java2.db.entities.Genre;

import javax.xml.crypto.Data;

public class GenreDao {

	public List<Genre> listGenres() {
		List<Genre> listOfGeneres = new ArrayList<>();
		try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
			try (Statement statement = connection.createStatement()) {
				String sqlQuery = "SELECT * FROM genre";
				try (ResultSet results = statement.executeQuery(sqlQuery)) {
					while (results.next()) {
						Genre genre = new Genre(
								results.getInt("idgenre"),
								results.getString("name"));
						listOfGeneres.add(genre);
					}
				}
			}
		} catch (SQLException e) {
			return new ArrayList<>();
		}
		return listOfGeneres;
	}

	public Genre getGenre(String name) {
		try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM genre WHERE name=?")) {
				statement.setString(1, name);
				try (ResultSet result = statement.executeQuery()) {
					if (result.next()) {
						Genre genre = new Genre();
						genre.setId(result.getInt("idgenre"));
						genre.setName(result.getString("name"));
						return genre;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Genre getGenreById(Integer id) {
		try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM genre WHERE idgenre=?")) {
				statement.setInt(1, id);
				try (ResultSet result = statement.executeQuery()) {
					if (result.next()) {
						Genre genre = new Genre(
								result.getInt("idgenre"),
								result.getString("name"));
						return genre;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addGenre(String name) {
		try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
			String sqlQuery = "INSERT INTO genre(name) " + "VALUES(?)";
			try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
				statement.setString(1, name);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
