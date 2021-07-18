package opensamlems;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;



public class Signing
{
	static String  keys=Constant.private_key
	public static String buildSignature(String samlMessage, String relayState, String signAlgorithm, String type) throws  IllegalArgumentException
	{
		
		 String signature = "";
		 PrivateKey key =loadPrivateKeyFromProp(keys);
		 String msg = type + "=" + SAMLRequest.urlEncoder(samlMessage);
		 
		 if (StringUtils.isNotEmpty(relayState)) {
			 msg += "&RelayState=" + SAMLRequest.urlEncoder(relayState);
		 }
		 
		 msg += "&SigAlg=" +SAMLRequest.urlEncoder(signAlgorithm);

		 try {
			signature = SAMLRequest.base64encoder(sign(msg, key, signAlgorithm));
		} catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException e) {
			String errorMsg = "buildSignature error." + e.getMessage();
			
		}
		 if (signature.isEmpty()) {
			 String errorMsg = "There was a problem when calculating the Signature of the " + type;
			 throw new IllegalArgumentException(errorMsg);
		 }
		 return signature;
	}
	
	private static byte[] sign(String text, PrivateKey key, String signAlgorithm) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		System.out.println("signAlgorithm");
        Signature instance = Signature.getInstance("SHA256withRSA");
        instance.initSign(key);
        instance.update(text.getBytes());
        byte[] signature = instance.sign();

        return signature;
	}
	
	
	public static PrivateKey loadPrivateKey(String keyString) throws GeneralSecurityException {
		//System.out.println("\nkeyString : "+keyString);
		String extractedKey = chunkString(keyString, 64);
		KeyFactory kf = KeyFactory.getInstance("RSA");

		PrivateKey privKey;
		try {
			byte[] encoded = Base64.decodeBase64(extractedKey);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
			privKey = kf.generatePrivate(keySpec);
		}
		catch(IllegalArgumentException e) {
			privKey = null;
		}

		return privKey;
	}
	private static String chunkString(String str, int chunkSize) {
		String newStr = StringUtils.EMPTY;
		int stringLength = str.length();
		for (int i = 0; i < stringLength; i += chunkSize) {
			if (i + chunkSize > stringLength) {
				chunkSize = stringLength - i;
			}
			newStr += str.substring(i, chunkSize + i) + '\n';
		}
		return newStr;
	}

	public static PrivateKey loadPrivateKeyFromProp(String propertyKey) {
	
			try {
				return loadPrivateKey(propertyKey.trim());
			} catch (Exception e) {
				return null;
			}

	}
}
