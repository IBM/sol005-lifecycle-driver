package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

    public class NsProductsFromProviders {
    public List<NsProducts> getNsProducts() {
        return nsProducts;
    }

    public void setNsProducts(List<NsProducts> nsProducts) {
        this.nsProducts = nsProducts;
    }

    @JsonProperty("nsProducts")
    private List<NsProducts> nsProducts;
    
    @JsonProperty("nsProvider")
    String nsProvider;

    public NsProductsFromProviders(String nsProvider, List<NsProducts> nsProducts) {
        this.nsProvider = nsProvider;
        this.nsProducts = nsProducts;
    }

    public NsProductsFromProviders(String nsProvider) {
        this.nsProvider = nsProvider;
    }

    public NsProductsFromProviders() {
    }

    public String getNsProvider() {
        return nsProvider;
    }

    public void setNsProvider(String nsProvider) {
        this.nsProvider = nsProvider;
    }

}
