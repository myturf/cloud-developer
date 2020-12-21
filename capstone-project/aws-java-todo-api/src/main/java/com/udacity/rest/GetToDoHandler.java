package com.udacity.rest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.udacity.ApiGatewayResponse;
import com.udacity.Response;
import org.apache.log4j.Logger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.udacity.data.ToDo;

public class GetToDoHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		Map<String, String> m = new HashMap<String, String>();
		m.put("Access-Control-Allow-Origin", "*");
		m.put("Access-Control-Allow-Credentials", "true");

    try {
        // get the 'pathParameters' from input
        Map<String,String> pathParameters =  (Map<String,String>)input.get("pathParameters");
        String id = pathParameters.get("id");

        // get the To Do by id
        ToDo toDo = new ToDo().get(id);

        // send the response back
        if (toDo != null) {
          return ApiGatewayResponse.builder()
      				.setStatusCode(200)
      				.setObjectBody(toDo)
      				.setHeaders(m)
      				.build();
        } else {
          return ApiGatewayResponse.builder()
      				.setStatusCode(404)
              .setObjectBody("To Do with id: '" + id + "' not found.")
      				.setHeaders(m)
      				.build();
        }
    } catch (Exception ex) {
        logger.error("Error in retrieving To Do: " + ex);

        // send the error response back
  			Response responseBody = new Response("Error in retrieving to do: ", input);
  			return ApiGatewayResponse.builder()
  					.setStatusCode(500)
  					.setObjectBody(responseBody)
  					.setHeaders(m)
  					.build();
    }
	}
}
