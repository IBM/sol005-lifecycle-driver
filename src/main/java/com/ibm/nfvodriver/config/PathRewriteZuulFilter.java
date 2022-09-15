package com.ibm.nfvodriver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * Filter that rewrites the proxy url, prefixing it with the contents of zuulRoute.url
 */
@Component("PathRewriteZuulFilter")
public class PathRewriteZuulFilter extends ZuulFilter {

    @Autowired
    private ZuulProperties zuulProperties;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().getFilterExecutionSummary().toString()
                             .contains("PreDecorationFilter[SUCCESS]");
    }

    @Override
    public Object run() {
        final RequestContext requestContext = RequestContext.getCurrentContext();
        if (requestContext.get(FilterConstants.SERVICE_ID_KEY) == null || requestContext.getRouteHost() != null) {
            return null;
        }

        final String proxy = (String) requestContext.get(FilterConstants.PROXY_KEY);
        final ZuulProperties.ZuulRoute zuulRoute = this.zuulProperties.getRoutes().get(proxy);

        final String requestUri = requestContext.get(FilterConstants.REQUEST_URI_KEY).toString();
        final String rewrittenRequestUri = zuulRoute.getUrl() + requestUri;
        requestContext.put(FilterConstants.REQUEST_URI_KEY, rewrittenRequestUri);

        requestContext.set(FilterConstants.SERVICE_ID_KEY, zuulRoute.getServiceId());

        return null;
    }
}