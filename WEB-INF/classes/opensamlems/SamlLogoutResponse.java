package opensamlems;

import java.util.zip.Inflater;

import org.apache.commons.codec.binary.Base64;

public class SamlLogoutResponse {

	public static String base64decodedInflated(String input) {
		if (input.isEmpty()) {
			return input;
		}
		byte[] decoded = Base64.decodeBase64(input);
		try {
			Inflater decompresser = new Inflater(true);
		    decompresser.setInput(decoded);
		    byte[] result = new byte[1024];
		    String inflated = "";
		    long limit = 0;
		    while(!decompresser.finished() && limit < 150) {
		    	int resultLength = decompresser.inflate(result);
		    	limit += 1;
		    	inflated += new String(result, 0, resultLength, "UTF-8");
		    }
		    decompresser.end();
		    return inflated;
		} catch (Exception e) {
			return new String(decoded);
		}
	}
}
