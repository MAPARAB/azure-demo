package com.example.azure.filter;

import com.example.azure.controller.GreetingController;
import com.example.azure.pojo.ApiKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.List;

@Component
public class AuthFilter implements GlobalFilter
{
    private static final Logger logger
            = LoggerFactory.getLogger(AuthFilter.class);


    @Autowired
    private List<ApiKeys> apikeys;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        logger.info("Entering the Filter  $$$$$$$$$$$$$$$$$$$$");

        List<String> apikeyheader = exchange.getRequest().getHeaders().get("gateway");
        logger.info("The apikeyheader inside filter :::: " + apikeyheader.get(0));

        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        String route_id = route!=null? route.getId() : null ;
        logger.info("The route id inside filter:::: " + route_id);

        if(route_id==null || CollectionUtils.isEmpty(apikeyheader) || !isAuthorised(apikeyheader.get(0), route_id))
        {
            logger.info("Unthorized to access");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You cannot consume this service, Please validate your api key");
        }

        return chain.filter(exchange);
    }


    private boolean isAuthorised(String apikeyheader, String routeid)
    {
        boolean flag = false;

        logger.info("API Key Object :::: " + apikeys);

        for(int i = 0 ; i< apikeys.size() ; i++)
        {
            ApiKeys keys = apikeys.get(i);
            logger.info("API Information :::: " + keys.getServiceId());
            if(keys.getServiceId().equals(apikeyheader))
            {
                logger.info("The apikeyheader :::: " + apikeyheader);
                if(keys.getRouteId().contains(routeid))
                {
                    logger.info("The route id is :::: " + routeid);
                    flag = true;
                }
            }
        }

        return flag;
    }


}
