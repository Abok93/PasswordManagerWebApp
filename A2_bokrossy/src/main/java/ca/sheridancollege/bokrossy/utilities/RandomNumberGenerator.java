// Aiden Bokrossy
// ID: 991712275

package ca.sheridancollege.bokrossy.utilities;

import java.time.LocalTime;
import java.util.Random;

// A class for generating random numbers
public class RandomNumberGenerator {

	public static Long generateRandomId() {
		Random r = new Random(LocalTime.now().toNanoOfDay());
		return r.nextLong(111_111_111, 999_999_999);
	}
}
