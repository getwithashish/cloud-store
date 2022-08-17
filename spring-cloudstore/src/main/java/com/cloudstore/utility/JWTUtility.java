package com.cloudstore.utility;


import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.cloudstore.entity.UserAuthenticationEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class JWTUtility implements Serializable {

	private static final long serialVersionUID = 55L;

	private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

//	@Value("${jwt.secret}")
//	private String secretKey = "secretKey123";
	private static String secretKey = "01100110011110010100011010001001011101001000000011100001110001111000011101011011001011010101101111100000111011000100001010100110010001101100010110101010111001101110010110111000001100010001010101001010010011011110101111110111101110001011000100110011011101010111001110001100000101100110111111111101111001100000000111111100000010101100001001010111010011110110111100010010000111010000000100110111111110011111110100110010010001000000110101001010001100101100001001011011110110101011000101011100111010111111001000101111";

	private final Key signatureSecretKey = generateSecretKey();

	public String generateToken(UserAuthenticationEntity user) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, user.getEmail());
	}

	private Key generateSecretKey() {
//		Key signatureSecretKey = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());
		SecretKey signatureSecretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
		return signatureSecretKey;
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(signatureSecretKey).compact();
//				.signWith(signatureSecretKey, SignatureAlgorithm.HS512).compact();
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(signatureSecretKey).build().parseClaimsJws(token).getBody();
	}

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public Boolean validateToken(String token, UserAuthenticationEntity user) {
		final String username = getUsernameFromToken(token);
		return (username.equals(user.getEmail()) && !isTokenExpired(token));
	}

}
