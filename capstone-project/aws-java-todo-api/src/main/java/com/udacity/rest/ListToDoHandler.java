package com.udacity.rest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.udacity.ApiGatewayResponse;
import com.udacity.Response;
import com.udacity.data.ToDo;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListToDoHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private final Logger logger = Logger.getLogger(this.getClass());


	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		Map<String, String> m = new HashMap<String, String>();
		m.put("Access-Control-Allow-Origin", "*");
		m.put("Access-Control-Allow-Credentials", "true");
    try {
        // get all to do
        List<ToDo> toDos = new ToDo().list();

        // send the response back


        return ApiGatewayResponse.builder()
    				.setStatusCode(200)
    				.setObjectBody(toDos)
    				.setHeaders(m)
    				.build();
    } catch (Exception ex) {
        logger.error("Error in listing To Dos: " + ex);

        // send the error response back
  			Response responseBody = new Response("Error in listing To Dos: ", input);
  			return ApiGatewayResponse.builder()
  					.setStatusCode(500)
  					.setObjectBody(responseBody)
  					.setHeaders(m)
  					.build();
    }
	}
}
