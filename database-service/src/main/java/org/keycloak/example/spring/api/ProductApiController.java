package org.keycloak.example.spring.api;

import net.rossillo.spring.web.mvc.CacheControl;
import net.rossillo.spring.web.mvc.CachePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

/**
 * Product API controller.
 */
@RestController
@RequestMapping("/products")
@CacheControl(policy = CachePolicy.NO_CACHE)
public class ProductApiController {

    private static final Logger log = LoggerFactory.getLogger(ProductApiController.class);

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PRODUCT_VIEW')")
    public List<String> getProducts(Principal principal) {

        log.info("Returning product list.");

        return Arrays.asList(
                "iPhone",
                "iPad",
                "MacBook",
                "Apple Watch");
    }
}
