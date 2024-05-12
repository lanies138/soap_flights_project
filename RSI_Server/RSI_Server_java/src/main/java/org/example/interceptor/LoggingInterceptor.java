package org.example.interceptor;

import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.MethodEndpoint;

public class LoggingInterceptor implements EndpointInterceptor {
    
    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) {
        System.out.println("Request Received: " + createMethodName(endpoint));
        return true;
    }
    
    @Override
    public boolean handleResponse(MessageContext messageContext, Object endpoint) {
        System.out.println("Response Processed: " + createMethodName(endpoint));
        return true;
    }
    
    @Override
    public boolean handleFault(MessageContext messageContext, Object endpoint) {
        System.out.println("Fault Occurred: " + createMethodName(endpoint));
        return true;
    }
    
    @Override
    public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) {
        if (ex != null) {
            System.out.println("Exception occurred: " + createMethodName(endpoint));
            System.out.println(ex.getMessage());
        }
    }
    
    private String createMethodName(Object endpoint) {
        return ((MethodEndpoint) endpoint).getMethod().getName();
    }
}
