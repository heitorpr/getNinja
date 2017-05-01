package br.heitor.getninja.views.adapter_delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.heitor.getninja.Events.ItemClickEvent;
import br.heitor.getninja.R;
import br.heitor.getninja.collections.Collection;
import br.heitor.getninja.models.Lead;
import br.heitor.getninja.utils.DateUtils;
import br.heitor.getninja.views.adapter.AbstractAdapterDelegate;
import br.heitor.getninja.views.adapter.CustomAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class LeadAdapterDelegate extends AbstractAdapterDelegate<Collection<Lead>> {
    public LeadAdapterDelegate(Context ctx, CustomAdapter.ViewType viewType) {
        super(ctx, viewType);
    }

    @Override
    public boolean isForViewType(@NonNull Collection items, int position) {
        return items.get(position) instanceof Lead;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new LeadViewHolder(inflater.inflate(R.layout.row_lead_or_offer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Collection<Lead> items, int position, @NonNull RecyclerView.ViewHolder viewHolder) {
        Lead lead = items.get(position);

        LeadViewHolder holder = (LeadViewHolder) viewHolder;

        holder.txtTitle.setText(lead.getTitle());
        holder.txtPersonName.setText(lead.getUser().getName());
        holder.txtPersonDate.setText(DateUtils.format(lead.getCreated_at()));
        holder.txtPersonLocation.setText(lead.getAddress().getNeighborhood());

        setEvents(holder);
        setIcons(lead, holder);
    }

    private void setIcons(Lead lead, LeadViewHolder holder) {
        setIconColors(holder, R.drawable.ic_person_green_24dp, R.drawable.ic_place_green_24dp);
    }

    private void setIconColors(LeadViewHolder holder, int resPerson, int resPlace) {
        holder.imgPersonDate.setImageResource(resPerson);
        holder.imgPersonName.setImageResource(resPerson);
        holder.imgPersonLocation.setImageResource(resPlace);
    }

    private void setEvents(final LeadViewHolder holder) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new ItemClickEvent(holder.getAdapterPosition(), viewType));
            }
        });
    }

    class LeadViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.view_separator)
        AppCompatImageView viewSeparator;
        @BindView(R.id.img_person_name)
        AppCompatImageView imgPersonName;
        @BindView(R.id.txt_person_name)
        TextView txtPersonName;
        @BindView(R.id.linear_person_name)
        LinearLayout linearPersonName;
        @BindView(R.id.img_person_date)
        AppCompatImageView imgPersonDate;
        @BindView(R.id.txt_person_date)
        TextView txtPersonDate;
        @BindView(R.id.linear_person_date)
        LinearLayout linearPersonDate;
        @BindView(R.id.img_person_location)
        AppCompatImageView imgPersonLocation;
        @BindView(R.id.txt_person_location)
        TextView txtPersonLocation;

        LeadViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
