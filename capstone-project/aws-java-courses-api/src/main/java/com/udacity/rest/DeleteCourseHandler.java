package com.udacity.rest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.udacity.ApiGatewayResponse;
import com.udacity.Response;
import org.apache.log4j.Logger;
import java.util.Collections;
import java.util.Map;

import com.udacity.data.Course;

public class DeleteCourseHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

    try {
        // get the 'pathParameters' from input
        Map<String,String> pathParameters =  (Map<String,String>)input.get("pathParameters");
        String id = pathParameters.get("id");

        // get the course by id
        Boolean success = new Course().delete(id);

        // send the response back
        if (success) {
          return ApiGatewayResponse.builder()
      				.setStatusCode(204)
      				.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless - Udacity"))
      				.build();
        } else {
          return ApiGatewayResponse.builder()
      				.setStatusCode(404)
      				.setObjectBody("Course with id: '" + id + "' not found.")
      				.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless - Udacity"))
      				.build();
        }
    } catch (Exception ex) {
        logger.error("Error in deleting Course: " + ex);

        // send the error response back
  			Response responseBody = new Response("Error in deleting Course: ", input);
  			return ApiGatewayResponse.builder()
  					.setStatusCode(500)
  					.setObjectBody(responseBody)
  					.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless - Udacity"))
  					.build();
    }
	}
}
