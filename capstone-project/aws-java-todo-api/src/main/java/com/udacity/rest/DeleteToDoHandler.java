package com.udacity.rest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.udacity.ApiGatewayResponse;
import com.udacity.Response;
import com.udacity.data.ToDo;
import org.apache.log4j.Logger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DeleteToDoHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

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

        // get the to do by id
        Boolean success = new ToDo().delete(id);

        // send the response back
        if (success) {
          return ApiGatewayResponse.builder()
      				.setStatusCode(204)
      				.setHeaders(Collections.singletonMap("Access-Control-Allow-Origin", "*"))
      				.build();
        } else {
          return ApiGatewayResponse.builder()
      				.setStatusCode(404)
      				.setObjectBody("To Do with id: '" + id + "' not found.")
      				.setHeaders(m)
      				.build();
        }
    } catch (Exception ex) {
        logger.error("Error in deleting To Do: " + ex);

        // send the error response back
  			Response responseBody = new Response("Error in deleting To Do: ", input);
  			return ApiGatewayResponse.builder()
  					.setStatusCode(500)
  					.setObjectBody(responseBody)
  					.setHeaders(m)
  					.build();
    }
	}
}
