package com.liutao62.util;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * @author liutao
 * @date Created in 2021/10/15 20:48
 * @description
 */
public final class RestServiceHelper {

    /**
     * 日志对象
     */
    private final static Logger logger = LoggerFactory.getLogger(RestServiceHelper.class);

    /**
     * rest调用模板
     */
    private static RestTemplate template = null;


    /**
     * 带有请求头信息进行调用
     *
     * @param url 服务地址
     * @param method 请求方法
     * @param params 参数
     * @param headerMap 头信息
     * @return 结果串
     */
    public static String execute(String url, HttpMethod method, Map<String, ?> params, Map<String, String> headerMap) {
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        if (headerMap != null)
            headers.setAll(headerMap);
        HttpEntity<MultiValueMap<String, ?>> requestEntity;
        if ((method == HttpMethod.POST || method == HttpMethod.PUT) && params != null && !params.isEmpty()) {
            requestEntity = new HttpEntity<MultiValueMap<String, ?>>(buildRequestEntity(params), headers);
        } else {
            url = buildUrl(url, params);
            requestEntity = new HttpEntity<>(null, headers);
        }
        HttpEntityRequestCallback callback = new HttpEntityRequestCallback(requestEntity, String.class);
        RestTemplate template = getTemplate();
        HttpMessageConverterExtractor<String> responseExtractor = new HttpMessageConverterExtractor<>(String.class, template.getMessageConverters());
        return getTemplate().execute(url, method, callback, responseExtractor);

    }

    /**
     * 构建参数
     *
     * @param params 参数map
     * @return HttpClient需要的参数对象
     */
    public static MultiValueMap<String, Object> buildRequestEntity(Map<String, ?> params) {

        MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
        for (Map.Entry<String, ?> entry : params.entrySet()) {
            Object v = entry.getValue();
            String key = entry.getKey();
            if (entry.getValue() == null)
                continue;
            if (v instanceof Collection) {
                Collection collection = (Collection) v;
                if (collection.isEmpty())
                    continue;
                for (Object o : collection) {
                    if (o == null)
                        continue;
                    if (o.toString().isEmpty())
                        continue;
                    requestEntity.add(key, o.toString());
                }
            } else if (v instanceof Resource) {
                requestEntity.add(key, v);
            } else if (v.toString().length() > 0) {
                requestEntity.add(key, v.toString());
            }
        }
        return requestEntity;
    }

    /**
     * 构建get请求时的参数
     *
     * @param url 地址
     * @param params 参数
     * @return url串
     */
    public static String buildUrl(String url, Map<String, ?> params) {
        if (params == null || params.isEmpty())
            return url;
        String split;
        if (url.contains("?")) {
            split = "&";
        } else {
            split = "?";
        }
        for (Map.Entry<String, ?> e : params.entrySet()) {
            url = url + split + e.getKey() + "=" + e.getValue().toString();
            if (split.equals("?")) {
                split = "&";
            }
        }
        return url;
    }

    /**
     * 取得rest访问模板
     *
     * @return rest访问模板对象
     */
    public static RestTemplate getTemplate() {
        if (template == null) {
            template = new RestTemplate();
            List<HttpMessageConverter<?>> converters = template.getMessageConverters();
            for (int i = 0; i < converters.size(); i++) {
                if (converters.get(i) instanceof StringHttpMessageConverter) {
                    converters.set(i, new StringHttpMessageConverter(Charset.forName("UTF-8")));
                }
                if (converters.get(i) instanceof FormHttpMessageConverter) {
                    ((FormHttpMessageConverter) converters.get(i)).setMultipartCharset(Charset.forName("UTF-8"));
                }
            }

        }
        return template;
    }

    /**
     * Request callback implementation that writes the given object to the request stream.
     */
    private static class HttpEntityRequestCallback implements RequestCallback {

        private final HttpEntity<?> requestEntity;
        private final Type responseType;

        private HttpEntityRequestCallback(Object requestBody) {
            this(requestBody, null);
        }

        private HttpEntityRequestCallback(Object requestBody, Type responseType) {
            this.responseType = responseType;
            if (requestBody instanceof HttpEntity) {
                this.requestEntity = (HttpEntity<?>) requestBody;
            } else if (requestBody != null) {
                this.requestEntity = new HttpEntity<Object>(requestBody);
            } else {
                this.requestEntity = HttpEntity.EMPTY;
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public void doWithRequest(ClientHttpRequest httpRequest) throws IOException {
            doWithResponseType(httpRequest);
            doWithRequestEntity(httpRequest);
        }

        protected void doWithRequestEntity(ClientHttpRequest request) throws IOException {
            if (!requestEntity.hasBody()) {
                HttpHeaders httpHeaders = request.getHeaders();
                HttpHeaders requestHeaders = requestEntity.getHeaders();
                if (!requestHeaders.isEmpty()) {
                    httpHeaders.putAll(requestHeaders);
                }
                if (httpHeaders.getContentLength() == -1) {
                    httpHeaders.setContentLength(0L);
                }
            } else {
                Object requestBody = requestEntity.getBody();
                Class<?> requestType = requestBody.getClass();
                HttpHeaders requestHeaders = requestEntity.getHeaders();
                MediaType requestContentType = requestHeaders.getContentType();
                for (HttpMessageConverter<?> messageConverter : getTemplate().getMessageConverters()) {
                    if (!messageConverter.canWrite(requestType, requestContentType))
                        continue;
                    if (!requestHeaders.isEmpty()) {
                        request.getHeaders().putAll(requestHeaders);
                    }
                    if (logger.isDebugEnabled()) {
                        if (requestContentType != null) {
                            logger.debug("Writing [" + requestBody + "] as \"" + requestContentType + "\" using [" + messageConverter + "]");
                        } else {
                            logger.debug("Writing [" + requestBody + "] using [" + messageConverter + "]");
                        }

                    }
                    ((HttpMessageConverter<Object>) messageConverter).write(requestBody, requestContentType, request);
                    return;
                }

                String message = "Could not write request: no suitable HttpMessageConverter found for request type [" + requestType.getName() + "]";
                if (requestContentType != null) {
                    message += " and content type [" + requestContentType + "]";
                }
                throw new RestClientException(message);
            }
        }

        protected void doWithResponseType(ClientHttpRequest request) {
            if (responseType == null)
                return;
            Class<?> responseClass = null;
            if (responseType instanceof Class) {
                responseClass = (Class<?>) responseType;
            }

            List<MediaType> allSupportedMediaTypes = new ArrayList<MediaType>();
            for (HttpMessageConverter<?> converter : getTemplate().getMessageConverters()) {
                if (responseClass != null) {
                    if (converter.canRead(responseClass, null)) {
                        allSupportedMediaTypes.addAll(getSupportedMediaTypes(converter));
                    }
                } else if (converter instanceof GenericHttpMessageConverter) {

                    GenericHttpMessageConverter<?> genericConverter = (GenericHttpMessageConverter<?>) converter;
                    if (genericConverter.canRead(responseType, null, null)) {
                        allSupportedMediaTypes.addAll(getSupportedMediaTypes(converter));
                    }
                }

            }
            if (!allSupportedMediaTypes.isEmpty()) {
                MediaType.sortBySpecificity(allSupportedMediaTypes);
                if (logger.isDebugEnabled()) {
                    logger.debug("Setting request Accept header to " + allSupportedMediaTypes);
                }
                request.getHeaders().setAccept(allSupportedMediaTypes);
            }
        }

        private List<MediaType> getSupportedMediaTypes(HttpMessageConverter<?> messageConverter) {
            List<MediaType> supportedMediaTypes = messageConverter.getSupportedMediaTypes();
            List<MediaType> result = new ArrayList<MediaType>(supportedMediaTypes.size());
            for (MediaType supportedMediaType : supportedMediaTypes) {
                if (supportedMediaType.getCharset() != null) {
                    supportedMediaType = new MediaType(supportedMediaType.getType(), supportedMediaType.getSubtype());
                }
                result.add(supportedMediaType);
            }
            return result;
        }

    }
}
