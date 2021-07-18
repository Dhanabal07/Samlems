package opensamlems;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.LogoutResponse;
import org.opensaml.saml2.core.Response;
import org.opensaml.xml.signature.Signature;


public class OpenSamlAuth 
{
	
	public String handleLoginResponce(String Samlresponse)
	{
		System.out.println("\n\n**********Login Response Parsing Starts************");
		try {
			String ans=SamlLogoutResponse.base64decodedInflated(Samlresponse);
			System.out.println(ans);
			Response response=new SAMLUtil().decodeSAMLLoginResponse(ans);
			//Response response=new SAMLUtil().decodeSAMLResponse(Samlresponse);
			Assertion assertion = response.getAssertions().get(0);
			String subject = assertion.getSubject().getNameID().getValue();
			String issuer = assertion.getIssuer().getValue();
			String audience = assertion.getConditions().getAudienceRestrictions().get(0).getAudiences().get(0).getAudienceURI();
			String statusCode = response.getStatus().getStatusCode().getValue();
			
			
			System.out.println("\nSubject :"+subject);
			System.out.println("\nIssuer :"+issuer);
			System.out.println("\nAudience :"+audience);
			System.out.println("\nStatusCode :"+statusCode);
			System.out.println("\n\n**********Login Response Parsing End ************");
			return subject;
		}catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
	public String handleLogoutResponce(String Samlresponse)
	{
		System.out.println("\n\n**********Logout Response Parsing Starts************");
		try {
			String ans=SamlLogoutResponse.base64decodedInflated(Samlresponse);
			
			System.out.println("\n\nLogout Response XML : "+ans);
			
			LogoutResponse response=new SAMLUtil().decodeSAMLLogoutResponse(ans);
			
			String statusCode = response.getStatus().getStatusCode().getValue();
			//System.out.print("Status Code : "+statusCode);
			System.out.println("\n\n**********Login Response Parsing End ************");
			return statusCode;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void login(HttpServletResponse response)
	{
		System.out.println("\n\n**********LOGIN STARTS***********");
		SAMLRequest authrequest=new SAMLRequest();
		String request=authrequest.createLoginRequest();
		System.out.println("\n\nRequest : "+request);
		String destination=Constant.idp_sso;
		try {
			String encryptedRequest=authrequest. deflatedBase64encoded(request);
			
			System.out.println("\n\nEncrpted message :-");
			System.out.println(encryptedRequest);
			
			String FinalRequest=destination+"?SAMLRequest="+authrequest.urlEncoder(encryptedRequest);
			System.out.println("\n\nFinal Login URL Request:-");
			System.out.println(FinalRequest);
			System.out.println("\n\n**********LOGIN ENDS***********");
			response.sendRedirect(FinalRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void logout(HttpServletResponse response,String nameId)
	{
		System.out.println("\n\n**********LOGOUT STARTS***********");
		SAMLRequest authrequest=new SAMLRequest();
		String request=authrequest.createLogoutRequest(nameId);
		System.out.println("\n\nMain class req xml : "+request);
		String destination=Constant.idp_slo;
		String relayState=Constant.sp_logout_relay;
		try {
			String encryptedRequest=authrequest. deflatedBase64encoded(request);
			
			System.out.println("\n\nEncryptedRequest: "+encryptedRequest);

			String sigAlg="http://www.w3.org/2001/04/xmldsig-more#rsa-sha256";
			String sigAlgUrl=authrequest.urlEncoder(sigAlg);
			
		    String signature= Signing.buildSignature(encryptedRequest, relayState, sigAlg, "SAMLRequest");
			
			String signatureUrl=authrequest.urlEncoder(signature);
			
			
			String requestUrl=authrequest.urlEncoder(encryptedRequest);
			
			String relayStateUrl=authrequest.urlEncoder(relayState);
			
			String FinalRequest=destination+"?SigAlg="+sigAlgUrl+"&SAMLRequest="+requestUrl+"&RelayState="+relayStateUrl+"&Signature="+signatureUrl;
			System.out.println("\n\nFinal request : \n"+FinalRequest);
			response.sendRedirect(FinalRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
