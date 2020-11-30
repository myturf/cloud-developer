package com.udacity.rest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.udacity.ApiGatewayResponse;
import com.udacity.Response;
import org.apache.log4j.Logger;
import java.util.Collections;
import java.util.Map;

import com.udacity.data.Course;

public class GetCourseHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

    try {
        // get the 'pathParameters' from input
        Map<String,String> pathParameters =  (Map<String,String>)input.get("pathParameters");
        String id = pathParameters.get("id");

        // get the Course by id
        Course course = new Course().get(id);

        // send the response back
        if (course != null) {
          return ApiGatewayResponse.builder()
      				.setStatusCode(200)
      				.setObjectBody(course)
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
        logger.error("Error in retrieving course: " + ex);

        // send the error response back
  			Response responseBody = new Response("Error in retrieving course: ", input);
  			return ApiGatewayResponse.builder()
  					.setStatusCode(500)
  					.setObjectBody(responseBody)
  					.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless - Udacity"))
  					.build();
    }
	}
}
