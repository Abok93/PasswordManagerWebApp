//Aiden Bokrossy 
//ID: 991712275

package ca.sheridancollege.bokrossy.database;

import java.util.List;
import ca.sheridancollege.bokrossy.beans.PasswordRecord;

public interface DatabaseAccess {

	// Method to insert a PasswordRecod into a database
	void insertPasswordRecord(PasswordRecord passwordRecord);

	// Method to retrieve a list of all PasswordRecords
	List<PasswordRecord> getPasswordRecordList();

	// Method to retrieve a list of PasswordRecords by id
	List<PasswordRecord> getPasswordRecordListById(Long id);
}
