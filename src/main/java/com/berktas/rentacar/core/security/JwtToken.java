package com.berktas.rentacar.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtToken {
    public static final String SIGNING_KEY = "aselsis";
    public static final String ISSUER = "http://rentacar.com";

    private final String token;
    private final Claims claims;

    private JwtToken(String token) {
        this.token = token;
        this.claims = Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public static JwtToken parse(String token) {
        return new JwtToken(token);
    }

    public Claims getClaims() {
        return claims;
    }

    public boolean isNoneExpired() {
        return claims.getExpiration().after(new Date());
    }

    public static class Builder {

        private final Claims claims;

        public Builder() {
            claims = Jwts.claims();
            claims.setIssuedAt(new Date());
        }

        public Builder put(String key, Object obj) {
            claims.put(key, obj);
            return this;
        }

        public Builder subject(String subject) {
            claims.setSubject(subject);
            return this;
        }

        public Builder expiration(Date date) {
            claims.setExpiration(date);
            return this;
        }

        public String build() {

            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuer(ISSUER)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                    .compact();
        }
    }

}