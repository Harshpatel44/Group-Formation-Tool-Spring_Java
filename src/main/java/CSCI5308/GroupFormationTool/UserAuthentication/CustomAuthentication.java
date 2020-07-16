package CSCI5308.GroupFormationTool.UserAuthentication;

import static CSCI5308.GroupFormationTool.ApplicationConstants.admin;
import static CSCI5308.GroupFormationTool.ApplicationConstants.badCredentialsException;
import static CSCI5308.GroupFormationTool.ApplicationConstants.noUserFoundException;
import static CSCI5308.GroupFormationTool.ApplicationConstants.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import CSCI5308.GroupFormationTool.UserManager.IUserService;
import CSCI5308.GroupFormationTool.UserManager.UserManagerAbstractFactory;

public class CustomAuthentication implements AuthenticationManager
{
	private Authentication checkForAdmin(String password,Authentication authentication) throws AuthenticationException
	{
		// The admin password is not encrypted because it is hardcoded in the DB.
		if (password.equals(UserManagerAbstractFactory.instance().getUser().getPassword()))
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
		ILoginService loginService = UserAuthenticationAbstractFactory.instance().getLoginService();
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

		IUserService iUserService = UserManagerAbstractFactory.instance().getUserService();

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
			if (bannerID.equals(admin))
			{
				UserManagerAbstractFactory.instance().getUserService().setUserByBannerId(admin,UserManagerAbstractFactory.instance().getUser());
				return checkForAdmin(password, authentication);
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
