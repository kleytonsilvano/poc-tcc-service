package tcc.poc.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserPrincipal implements Principal {

    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("expiration")
    private Date expiration;
    @JsonProperty("scope")
    private List<String> scope;

    public UserPrincipal() {
    }

    public UserPrincipal(Date expiration, String clientId, List<String> scope) {
        this.expiration = expiration;
        this.clientId = clientId;
        this.scope = scope;
    }

    public List<String> getCredentials() {
        List<String> ret = new ArrayList<>();
        ret.addAll(this.scope);
        return ret;
    }

    public void setCredentials(List<String> scope) {
        this.scope = scope;
    }

    public Date getExpiration() {
        return this.expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @JsonIgnore
    public String getName() {
        return clientId;
    }
}