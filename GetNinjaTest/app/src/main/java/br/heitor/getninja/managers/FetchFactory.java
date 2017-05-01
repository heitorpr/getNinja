package br.heitor.getninja.managers;

import br.heitor.getninja.Events.CollectionEvent;
import br.heitor.getninja.collections.Collection;
import br.heitor.getninja.collections.LeadCollection;
import br.heitor.getninja.collections.OfferCollection;
import br.heitor.getninja.interfaces.InterfaceLead;
import br.heitor.getninja.interfaces.InterfaceOffer;
import br.heitor.getninja.managers.parser.LeadParser;
import br.heitor.getninja.managers.parser.OfferParser;
import de.greenrobot.event.EventBus;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("unchecked")
public class FetchFactory {
    public static <T> void fetch(Collection<T> collection) {
        if (collection instanceof OfferCollection) {
            fetchOffers(collection);
        }

        if (collection instanceof LeadCollection) {
            fetchLeads(collection);
        }
    }

    private static void fetchOffers(final Collection collection) {
        InterfaceOffer service = ServiceGenerator.createService(InterfaceOffer.class);
        service.fetchList().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                collection.setList(OfferParser.parseList(response.body()));
                EventBus.getDefault().post(new CollectionEvent(collection.getClass()));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                EventBus.getDefault().post(new CollectionEvent(collection.getClass()));
            }
        });
    }

    private static void fetchLeads(final Collection collection) {
        InterfaceLead service = ServiceGenerator.createService(InterfaceLead.class);
        service.fetchList().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                collection.setList(LeadParser.parseList(response.body()));
                EventBus.getDefault().post(new CollectionEvent(collection.getClass()));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                EventBus.getDefault().post(new CollectionEvent(collection.getClass()));
            }
        });
    }
}
