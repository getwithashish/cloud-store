//package com.apiauthentication.utility;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//import javax.crypto.spec.SecretKeySpec;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import com.apiauthentication.entity.User;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//
//@Component
//public class JWTUtility {
//	
//	private static final long JWT_TOKEN_VALIDITY = 5*60*60;
//	
//	@Value("${jwt.secret}")
//	private String secretKey;
//	
//	private final Key signatureSecretKey = generateSecretKey();
//	
//	public String generateToken(User user) {
//		Map<String, Object> claims = new HashMap<>();
//		return doGenerateToken(claims, user.getEmail());
//	}
//	
//	private Key generateSecretKey() {
//		Key signatureSecretKey = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());
//		return signatureSecretKey;
//	}
//	
//	private String doGenerateToken(Map<String, Object> claims, String subject) {
//		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000))
//				.signWith(signatureSecretKey, SignatureAlgorithm.HS512).compact();
//	}
//	
//	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//		final Claims claims = getAllClaimsFromToken(token);
//		return claimsResolver.apply(claims);
//	}
//	
//	private Claims getAllClaimsFromToken(String token) {
//		return Jwts.parserBuilder().setSigningKey(signatureSecretKey).build().parseClaimsJws(token).getBody();
//	}
//
//	public String getUsernameFromToken(String token) {
//		return getClaimFromToken(token, Claims::getSubject);
//	}
//	
//	public Date getExpirationDateFromToken(String token) {
//		return getClaimFromToken(token, Claims::getExpiration);
//	}
//	
//	private Boolean isTokenExpired(String token) {
//		final Date expiration = getExpirationDateFromToken(token);
//		return expiration.before(new Date());
//	}
//	
//	public Boolean validateToken(String token, User user) {
//		final String username = getUsernameFromToken(token);
//		return (username.equals(user.getEmail()) && !isTokenExpired(token));
//	}
//
//}
