/*
 * MIT License
 *
 * Copyright (c) 2016 BotMill.io
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package co.aurasphere.botmill.fb.test.upload;

import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.aurasphere.botmill.core.internal.util.ConfigurationUtils;
import co.aurasphere.botmill.fb.api.MessengerCodeApi;
import co.aurasphere.botmill.fb.model.api.MessengerCode;
import co.aurasphere.botmill.fb.model.api.MessengerCodeRequest;

/**
 * Test for the {@link MessengerCodeApi} class.
 * 
 * @author Donato Rimenti
 * @since 2.0.0
 */
public class MessengerCodeApiTest {
	
	private final static Logger logger = LoggerFactory.getLogger(MessengerCodeApiTest.class);
	
	@Before
	public void setup() {
		ConfigurationUtils.loadEncryptedConfigurationProperties(); // loads the annotated encryption class.
		ConfigurationUtils.loadBotDefinitions(); // loads the annotated bot.
	}

	public void test() {
		MessengerCode response = MessengerCodeApi.getMessengerCode();
		checkResponse(response);
		
		// Tests different cases.
		response = MessengerCodeApi.getMessengerCode(100);
		checkResponse(response);
		
		response = MessengerCodeApi.getMessengerCode(1000);
		checkResponse(response);
		
		response = MessengerCodeApi.getMessengerCode(new MessengerCodeRequest(2000));
		checkResponse(response);
	
	}

	/**
	 * Checks if the response is valid.
	 * 
	 * @param response
	 *            the MessengerCode to check.
	 */
	private void checkResponse(MessengerCode response) {
		Assert.assertNotNull(response);
		String uri = response.getUri();
		Assert.assertNotNull(uri);
		Assert.assertNotEquals("", uri);
		logger.info(
				"Succesfully got a Messenger code from Facebook (uri: [{}])",
				uri);
	}
}
