package com.udacity.rest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.ApiGatewayResponse;
import com.udacity.Response;
import com.udacity.data.ToDo;
import org.apache.log4j.Logger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CreateToDoHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		Map<String, String> m = new HashMap<String, String>();
		m.put("Access-Control-Allow-Origin", "*");
		m.put("Access-Control-Allow-Credentials", "true");

      try {
          // get the 'body' from input
          JsonNode body = new ObjectMapper().readTree((String) input.get("body"));

          // create the To Do object for post
          ToDo toDo = new ToDo();
          toDo.setName(body.get("name").asText());
          toDo.setDueDate(body.get("dueDate").asText());
          toDo.save(toDo);

          // send the response back
      		return ApiGatewayResponse.builder()
      				.setStatusCode(200)
      				.setObjectBody(toDo)
      				.setHeaders(m)
      				.build();

      } catch (Exception ex) {

          logger.error("Error in saving To Do: " + ex);
		  logger.error("Error in saving To Do: " + ex.fillInStackTrace());

          // send the error response back

		  Response responseBody = new Response("Error in saving To Do: ", input);
    			return ApiGatewayResponse.builder()
    					.setStatusCode(500)
    					.setObjectBody(responseBody)
    					.setHeaders(m)
    					.build();
      }
	}
}
