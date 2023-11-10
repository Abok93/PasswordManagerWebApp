//Aiden Bokrossy 
//ID: 991712275

package ca.sheridancollege.bokrossy.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor

// A class representing a password record
public class PasswordRecord {
	@NonNull
	private Long id;

	private String title;
	private String username;
	private String password;
	private String url;
	private String email;
	private String notes;
}
