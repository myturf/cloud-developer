package com.udacity.rest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.ApiGatewayResponse;
import com.udacity.Response;
import org.apache.log4j.Logger;
import java.util.Collections;
import java.util.Map;

import com.udacity.data.Course;

public class CreateCourseHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

      try {
          // get the 'body' from input
          JsonNode body = new ObjectMapper().readTree((String) input.get("body"));

          // create the Course object for post
          Course course = new Course();

          course.setName(body.get("name").asText());
          course.setRatings((float) body.get("ratings").asDouble());
          course.save(course);

          // send the response back
      		return ApiGatewayResponse.builder()
      				.setStatusCode(200)
      				.setObjectBody(course)
      				.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless - Udacity"))
      				.build();

      } catch (Exception ex) {
          logger.error("Error in saving product: " + ex);

          // send the error response back
    			Response responseBody = new Response("Error in saving Course: ", input);
    			return ApiGatewayResponse.builder()
    					.setStatusCode(500)
    					.setObjectBody(responseBody)
    					.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless - Udacity"))
    					.build();
      }
	}
}
