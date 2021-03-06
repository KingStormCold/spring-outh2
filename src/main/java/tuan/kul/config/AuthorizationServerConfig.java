package tuan.kul.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	static final String CLIEN_ID = "devglan-client";
	static final String CLIENT_SECRET = "devglan-secret";
	static final String GRANT_TYPE = "password";
	static final String AUTHORIZATION_CODE = "authorization_code";
	static final String REFRESH_TOKEN = "refresh_token";
	static final String IMPLICIT = "implicit";
	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
	static final String TRUST = "trust";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60;
	static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier("dataSource")
	DataSource dataSource;

	@Autowired
	@Resource(name = "userService")
	private UserDetailsService userDetailsService;

	//7
	@Override
	public void configure(ClientDetailsServiceConfigurer client) throws Exception {
		client.jdbc(dataSource);
//		client.jdbc(dataSource).inMemory().withClient("client_api").secret(passwordEncoder.encode("123456"))
//				.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
//				.authorizedGrantTypes(GRANT_TYPE, REFRESH_TOKEN).resourceIds("resource");
//        configurer.inMemory()
//                .withClient(CLIEN_ID)
//                .secret(passwordEncoder.encode(CLIENT_SECRET))
//                .authorizedGrantTypes(GRANT_TYPE, REFRESH_TOKEN)
//                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
//                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
//                .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
	}

	//6
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
		configurer.tokenStore(tokenStore).authenticationManager(authenticationManager);
	}
	
	//oauth check token
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.checkTokenAccess("permitAll()");
	}
}
