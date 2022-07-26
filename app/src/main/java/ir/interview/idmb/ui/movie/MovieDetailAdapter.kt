package ir.interview.idmb.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.interview.idmb.databinding.ItemMovieDetailBinding

class MovieDetailAdapter :
    ListAdapter<MovieDetailItem, MovieDetailAdapter.AddressesViewHolder>(
        object : DiffUtil.ItemCallback<MovieDetailItem>() {
            override fun areItemsTheSame(oldItem: MovieDetailItem, newItem: MovieDetailItem): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: MovieDetailItem, newItem: MovieDetailItem): Boolean {
                return oldItem == newItem
            }
        }
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressesViewHolder {
        val binding =
            ItemMovieDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddressesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AddressesViewHolder(private val binding: ItemMovieDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieDetailItem) {
            with(binding) {
                binding.txtTitle.text = item.title
                binding.txtDetail.text = item.detail
            }
        }
    }
}