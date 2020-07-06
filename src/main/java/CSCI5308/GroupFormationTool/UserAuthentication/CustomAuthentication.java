package CSCI5308.GroupFormationTool.UserAuthentication;

import java.util.ArrayList;
import java.util.List;
import CSCI5308.GroupFormationTool.UserManager.IUser;
import CSCI5308.GroupFormationTool.UserManager.IUserService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import CSCI5308.GroupFormationTool.Injector;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static CSCI5308.GroupFormationTool.ApplicationConstants.*;

public class CustomAuthentication implements AuthenticationManager
{
	private Authentication checkForAdmin(String password, IUser u, Authentication authentication) throws AuthenticationException
	{
		// The admin password is not encrypted because it is hardcoded in the DB.
		if (password.equals(u.getPassword()))
		{
			// Grant ADMIN rights system-wide, this is used to protect controller mappings.
			List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
			rights.add(new SimpleGrantedAuthority(admin));
			// Return valid authentication token.
			UsernamePasswordAuthenticationToken token;
			token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),authentication.getCredentials(),rights);
			return token;
		}
		else
		{
			throw new BadCredentialsException(badCredentialsException);
		}
	}
	
	private Authentication checkForNormalUser(Authentication authentication) throws Exception
	{
		String bannerID = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();
		ILoginService loginService = Injector.instance().getLoginService();
		if(loginService.checkIfUserIsAuthenticated(bannerID, password))
		{
			List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
			rights.add(new SimpleGrantedAuthority(user));
			// Return valid authentication token.
			UsernamePasswordAuthenticationToken token;
			token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),authentication.getCredentials(),rights);
			return token;
		}
		else
		{
			throw new BadCredentialsException(badCredentialsException);
		}
	}

	// Authenticate against our database using the input banner ID and password.
	public Authentication authenticate(Authentication authentication) {
		String bannerID = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();

		IUserService iUserService = null;
		try {
			iUserService = Injector.instance().getUserService();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Boolean validity;
		try
		{
			validity = iUserService.checkIfUserExists(bannerID);
		}
		catch (Exception e)
		{
			throw new AuthenticationServiceException(badCredentialsException);
		}

		if (validity)
		{
//			IUser iUser =  Injector.instance().getUser();
//			iUserService.setUserByBannerId(bannerID,iUser);
			if (bannerID.toUpperCase().equals(admin))
			{
				return checkForAdmin(password, Injector.instance().getUser(), authentication);
			}
			else
			{
				try {
					return checkForNormalUser(authentication);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else
		{
			// No user with this banner id found.
			throw new BadCredentialsException(noUserFoundException);
		}
	return null;
	}
}
