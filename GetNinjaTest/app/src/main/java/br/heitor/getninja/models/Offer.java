package br.heitor.getninja.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Offer {
    private String state;

    @SerializedName("_embedded")
    private OfferData data;

    @SerializedName("_links")
    private OfferLink links;

    public Date getCreatedAt() {
        return data.getCreated_at();
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

    private class OfferData {
        private OfferRequest request;

        Date getCreated_at() {
            return request.getCreated_at();
        }

        String getTitle() {
            return request.getTitle();
        }

        Address getAddress() {
            return request.getAddress();
        }

        User getUser() {
            return request.getUser();
        }
    }

    private class OfferRequest {
        private Date created_at;
        private String title;

        @SerializedName("_embedded")
        private OfferRequestEmbedded data;

        Date getCreated_at() {
            return created_at;
        }

        String getTitle() {
            return title;
        }

        Address getAddress() {
            return data.getAddress();
        }

        User getUser() {
            return data.getUser();
        }

        private class OfferRequestEmbedded {
            private User user;
            private Address address;

            Address getAddress() {
                return address;
            }

            User getUser() {
                return user;
            }
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
