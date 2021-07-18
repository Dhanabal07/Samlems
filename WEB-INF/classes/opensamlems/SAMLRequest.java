package opensamlems;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.util.Calendar;
import java.util.UUID;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;


public class SAMLRequest 
{
		private static final DateTimeFormatter DATE_TIME_FORMAT = ISODateTimeFormat.dateTimeNoMillis().withZoneUTC();
		private final Calendar issueInstant=Calendar.getInstance();
		public String createLoginRequest()
		{
			
			String id=generateUniqueID();
			id="\""+id+"\"";
			String str="<samlp:AuthnRequest xmlns:samlp=\"urn:oasis:names:tc:SAML:2.0:protocol\" "
					+ "xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\" "
					+ "ID="+id
					+ " Version=\"2.0\"";
			
			String issueInstantString = formatDateTime(issueInstant.getTimeInMillis());
			String Destination="\""+Constant.idp_sso+"\"";
			String ProtocolBinding="\"urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST\"";
	        String AssertionConsumerServiceURL="\""+Constant.sp_acs+"\"";
	        String issuer=Constant.sp_issuer;
			
	        
			str=str+" IssueInstant=\""+issueInstantString+"\"";
			str=str+" Destination="+Destination;
			str=str+" ProtocolBinding="+ProtocolBinding;
			str=str+" AssertionConsumerServiceURL="+AssertionConsumerServiceURL+">";
			
			str=str+" <saml:Issuer>"+issuer+"</saml:Issuer>";
			
			
			
			str=str+" <samlp:NameIDPolicy Format=\"urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified\" AllowCreate=\"true\"/></samlp:AuthnRequest>";
			return str;
	}
	
	public String createLogoutRequest(String nameId)
	{
		//String id=generateUniqueID();
		String id="ONELOGIN_f372878c-ac0e-4ede-82aa-2dd8913c883c";
		id="\""+id+"\"";
		String str="<samlp:LogoutRequest xmlns:samlp=\"urn:oasis:names:tc:SAML:2.0:protocol\" "
				+ "xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\" "
				+ "ID="+id
				+ " Version=\"2.0\"";
		
		String issueInstantString = formatDateTime(issueInstant.getTimeInMillis());
		String Destination="\""+Constant.idp_slo+"\"";
		String issuer=Constant.sp_issuer;
		
        
		str=str+" IssueInstant=\""+issueInstantString+"\"";
		str=str+" Destination="+Destination;
		
		str=str+" ><saml:Issuer>"+issuer+"</saml:Issuer>";
		
		str=str+" <saml:NameID>"+nameId+"</saml:NameID> </samlp:LogoutRequest>";
		
		return str;
		
	}
	
	public static String formatDateTime(long timeInMillis) {
		return DATE_TIME_FORMAT.print(timeInMillis);
	}
	
	public static String deflatedBase64encoded(String input) throws IOException {
		ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
		Deflater deflater = new Deflater(Deflater.DEFLATED, true);
		DeflaterOutputStream deflaterStream = new DeflaterOutputStream(bytesOut, deflater);
		deflaterStream.write(input.getBytes(Charset.forName("UTF-8")));
		deflaterStream.finish();
		return new String(Base64.encodeBase64(bytesOut.toByteArray()));
	}
	
	public static String base64encoder(byte [] input) {
		return toStringUtf8(Base64.encodeBase64(input));
	}
	
	public static String base64encoder(String input) {
		return base64encoder(toBytesUtf8(input));
	}
	
	private static String toStringUtf8(byte[] bytes) {
		try {
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}
	
	private static byte[] toBytesUtf8(String str) {
		try {
			return str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}
	public static String generateUniqueID() {
		
		return "ONELOGIN_" + UUID.randomUUID();
	}
	
	public static String urlEncoder(String input) {
		if (input != null) {
			try {
				return URLEncoder.encode(input, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new IllegalArgumentException();
			}
		} else {
			return null;
		}
	}
}