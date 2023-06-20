package model;

import java.sql.SQLException;
import org.joda.time.LocalDate;
import java.util.Collection;


public interface IBeanDAO<T> {

	public void doSave(T bean) throws SQLException;

	public boolean doDelete(String id) throws SQLException;

	public T doRetrieveByKey(String id) throws SQLException;

	public Collection<T> doRetrieveAll(String order) throws SQLException;
	
	public Collection<T> doRetrieveSinceDate(LocalDate date) throws SQLException;
}