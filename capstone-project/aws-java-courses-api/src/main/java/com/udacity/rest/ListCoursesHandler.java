package com.udacity.rest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.udacity.ApiGatewayResponse;
import com.udacity.Response;
import com.udacity.data.Course;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ListCoursesHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
    try {
        // get all courses
        List<Course> courses = new Course().list();

        // send the response back
        return ApiGatewayResponse.builder()
    				.setStatusCode(200)
    				.setObjectBody(courses)
    				.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless - Udacity"))
    				.build();
    } catch (Exception ex) {
        logger.error("Error in listing courses: " + ex);

        // send the error response back
  			Response responseBody = new Response("Error in listing courses: ", input);
  			return ApiGatewayResponse.builder()
  					.setStatusCode(500)
  					.setObjectBody(responseBody)
  					.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless - Udacity"))
  					.build();
    }
	}
}
