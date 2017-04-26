package br.heitor.getninja.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Lead {
    private Date created_at;

    @SerializedName("_embedded")
    private LeadData data;

    @SerializedName("_links")
    private OfferLink links;

    public Date getCreated_at() {
        return created_at;
    }

    public String getTitle() {
        return data.getTitle();
    }

    public User getUser() {
        return data.getUser();
    }

    public Address getAddress() {
        return data.getAddress();
    }

    public String getLink() {
        return links.getSelfLink();
    }

    private class LeadData {
        private OfferRequest request;
        private Address address;
        private User user;

        String getTitle() {
            return request.getTitle();
        }

        Address getAddress() {
            return address;
        }

        User getUser() {
            return user;
        }
    }

    private class OfferRequest {
        private String title;

        String getTitle() {
            return title;
        }
    }

    private class OfferLink {
        private SelfLink self;

        String getSelfLink() {
            return self.getHref();
        }

        private class SelfLink {
            String href;

            String getHref() {
                return href;
            }
        }
    }
}
