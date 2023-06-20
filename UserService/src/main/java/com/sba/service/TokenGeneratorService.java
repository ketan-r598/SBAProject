package com.sba.service;

import java.util.Map;

import com.sba.model.UserCredential;

public interface TokenGeneratorService {
	Map<String,String> generateToken(UserCredential user);
}
