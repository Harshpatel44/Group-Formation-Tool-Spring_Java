package CSCI5308.GroupFormationTool.UserAuthentication.Security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Login.AccessControl.ILoginRepository;
import CSCI5308.GroupFormationTool.Login.AccessControl.ILoginService;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomAuthentication implements AuthenticationManager
{
	private static final String ADMIN_BANNER_ID = "ADMIN";
	private Authentication checkAdmin(String password, User u, Authentication authentication) throws AuthenticationException
	{
		// The admin password is not encrypted because it is hardcoded in the DB.
		if (password.equals(u.getPassword()))
		{
			// Grant ADMIN rights system-wide, this is used to protect controller mappings.
			List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
			rights.add(new SimpleGrantedAuthority("ADMIN"));
			// Return valid authentication token.
			UsernamePasswordAuthenticationToken token;
			token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),authentication.getCredentials(),rights);
			return token;
		}
		else
		{
			throw new BadCredentialsException("1000");
		}
	}
	
	private Authentication checkNormal(Authentication authentication) throws AuthenticationException
	{
		String bannerID = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();
		ILoginService loginService = Injector.instance().getLoginService();
		if(loginService.checkLogin(bannerID, password))
		{
			List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
			rights.add(new SimpleGrantedAuthority("USER"));
			// Return valid authentication token.
			UsernamePasswordAuthenticationToken token;
			token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),authentication.getCredentials(),rights);
			return token;
		}
		else
		{
			throw new BadCredentialsException("1000");
		}
	}

	// Authenticate against our database using the input banner ID and password.
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		String bannerID = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();
		IUserRepository userRepository = Injector.instance().getUserRepository();
		ILoginRepository loginRepository = Injector.instance().getLoginRepository();
		Boolean validity = false;
		try
		{
			validity = loginRepository.isUser(bannerID);
		}
		catch (Exception e)
		{
			throw new AuthenticationServiceException("1000");
		}
		if (validity)
		{
			User user = userRepository.loadUserByID(bannerID);
			if (bannerID.toUpperCase().equals(ADMIN_BANNER_ID))
			{
				return checkAdmin(password, user, authentication);
			}
			else
			{
				return checkNormal(authentication);
			}
		}
		else
		{
			// No user with this banner id found.
			throw new BadCredentialsException("1001");
		}			
	}
}
