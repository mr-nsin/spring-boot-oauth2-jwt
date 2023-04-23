package com.learning.oauth2.springbootoauth2jwt.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class ConfigOAuth2 extends AuthorizationServerConfigurerAdapter{

    private String clientId = "nitinsinghal";
    private String clientSecret = "nitinsinghal-secret-key";
    private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpQIBAAKCAQEA4hvHzIBERHTUO9okQRbQSsx9fOP43TPBtnwPbSvzeQ0UH47d\n" +
            "h+hdlfRMrFaf4WmxfDsa9owTPcQ5w2akpcDBSJWX5Tq7gwy4cFXZ7ULrv/WPnYFr\n" +
            "KP8dDz53nFdiD/t9x534wAN614CGLavd0hRdZz5AiCtaiSapjmmp11utC2g4Ao5b\n" +
            "GkWsFE5my8aULYEnvZementhFHdxWh9RyoUI/oZymm6LyVOPzMjy43CgwGXgs5M8\n" +
            "xjhoCJ+0Z20uVQaQE0lHZJS8GcJus0SiGTsGdTrVKt43FMsEvgjexsp2vta+Hc9m\n" +
            "UsALxr6GpIwy+YVdGSScM+kS4t/80gc9H4q6IwIDAQABAoIBAH3IioufR1r1yyNI\n" +
            "pSZ4dINH4v9ZKxaTNk44EbTf4GAVAH7V0TNL9tP2xbiRr/uL+qprjwJcxk90G9Yy\n" +
            "AaIb6upgHdHtrGao6unDOKqesdtl1fD/EAgMc7QJQiz9B5Zwh3ZG0RywUuQToZFC\n" +
            "rPGzVoqavPeDdqgZk/WfnlYpr8hJs+d0GBU3VILyBbhTY6vkLClpOLbMQTVDlSwh\n" +
            "S1AtMMdY7n9/bng4/fwJDVIWAqKQeq8BeZESSqnEbGaKdmo0tgkr0HIdjn+zluB+\n" +
            "Djbg2cTXz4Pzk7BMgG9D+uMKv+dm1rb216CbJ2eMJt04+/+okgCNeBRYk8QrMvMS\n" +
            "Z98bnbECgYEA/SJAQacVO610tbMxNFZ8YcPGILcm/bZTLUR6ep82KMCT8ddx7mCa\n" +
            "eSNFEZKJXQ+msUKHZKoNeNi6SFuPt0LBcf1DKbxYjOAIoQo55i4iyaJgjpC9Nn4R\n" +
            "fqMpI2qYq5adgta8EjQHRbFjm1WsrXRS9ftQLajoUALpzEnCYPDAYjcCgYEA5Ksx\n" +
            "Pj+5RHGIpEkuMkYMUHnxMJpNNjwxXZNbyjJXJBGOwK9XIDaTxfmHL1WDrS58KSO/\n" +
            "2bigwvpBlrfLV5vuWs+qrlIB9KB+9PCH+DNlwOQzCueSS1C+i9m1fN+IS5WahkWI\n" +
            "qIop3c/mGhsKF9JR8m9M2dKw0xNsrmcd3llRYXUCgYEA7hnikR6llU3SAvt7o/xZ\n" +
            "4OfynGFK1slCMclA38iUe/6w6oG50/ygMKPhWucYOCX5qLCohCKEq5BIoLknVL0B\n" +
            "EsSkAUT9kf1FgJ0K2x24cStAYbl5kSFCOXklv/+LfZMds61imEjCMmbqB3muBDXv\n" +
            "txreTySa6G/lFNiocuDiRT8CgYEA4NLcKJrYmc2SjgwRaoZqhxlNpP6gmYlW8+z1\n" +
            "G3ihKqpEk3gRjm96nPR4EHbgbv48PuqSLwsSrIEGyZvNRkbec9SnuJqSp6TBDiwL\n" +
            "U2GdUqhK8xa5RGmP3spfYPYZ+E6PziJBeYvWG+rTDqm1I7S6s0Tw51z6U51Ohq6F\n" +
            "tF5W4B0CgYEA2ucxLbE21DGPwniptB4/6DVMSUvMrmPL3rQZow4Jnd+ePQuX746Z\n" +
            "Yumcw7QMIhNjPFf40u/aKUSsB0wHEBjifQVONdmbiMXXNp+/PHVhB0FgaFIiqc4H\n" +
            "vnbaxpojjKQnhqmHn9EeAq+TZkKC6gLuSEzKbL+omZoG5ff3o2p9Sxc=\n" +
            "-----END RSA PRIVATE KEY-----\n";
    private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4hvHzIBERHTUO9okQRbQ\n" +
            "Ssx9fOP43TPBtnwPbSvzeQ0UH47dh+hdlfRMrFaf4WmxfDsa9owTPcQ5w2akpcDB\n" +
            "SJWX5Tq7gwy4cFXZ7ULrv/WPnYFrKP8dDz53nFdiD/t9x534wAN614CGLavd0hRd\n" +
            "Zz5AiCtaiSapjmmp11utC2g4Ao5bGkWsFE5my8aULYEnvZementhFHdxWh9RyoUI\n" +
            "/oZymm6LyVOPzMjy43CgwGXgs5M8xjhoCJ+0Z20uVQaQE0lHZJS8GcJus0SiGTsG\n" +
            "dTrVKt43FMsEvgjexsp2vta+Hc9mUsALxr6GpIwy+YVdGSScM+kS4t/80gc9H4q6\n" +
            "IwIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);

    }

}
