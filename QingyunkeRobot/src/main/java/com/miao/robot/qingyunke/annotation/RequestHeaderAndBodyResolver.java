package com.miao.robot.qingyunke.annotation;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Component
public class RequestHeaderAndBodyResolver extends RequestResponseBodyMethodProcessor {

    public static final String headerMapName = "headerMap";

    public RequestHeaderAndBodyResolver(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestHeaderAndBody.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object result = super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);

        HashMap<String, String> headerParams = new HashMap<>();
        Iterator<String> headerNames = webRequest.getHeaderNames();
        headerNames.forEachRemaining(headerName -> headerParams.put(headerName, webRequest.getHeader(headerName)));
        BeanUtils.setProperty(result, headerMapName, headerParams);
        BeanUtils.populate(result, headerParams);

        return result;
    }
}
