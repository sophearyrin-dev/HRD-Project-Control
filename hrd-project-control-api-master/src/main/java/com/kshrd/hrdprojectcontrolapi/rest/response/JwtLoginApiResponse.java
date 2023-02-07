package com.kshrd.hrdprojectcontrolapi.rest.response;

import java.io.Serializable;

public class JwtLoginApiResponse implements Serializable {

    private final String jwtToken;

    public JwtLoginApiResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    @Override
    public String toString() {
        return "JwtLoginApiResponse{" +
                "jwtToken='" + jwtToken + '\'' +
                '}';
    }
}
