package ir.interview.idmb.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.interview.idmb.databinding.ItemMovieBinding

class MovieAdapter :
    ListAdapter<Movie, MovieAdapter.AddressesViewHolder>(
        object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.imdbID == newItem.imdbID
            }
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    var onItemClick : ((movie:Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressesViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddressesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressesViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    inner class AddressesViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie, onItemClick: ((movie: Movie) -> Unit)?) {
            with(binding) {
                binding.txtTitle.text = item.title
                binding.txtYear.text = item.year
                binding.txtType.text = item.type

                root.setOnClickListener{
                    onItemClick?.invoke(item)
                }
            }
        }
    }
}