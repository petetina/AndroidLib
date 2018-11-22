package antoinepetetin.fr.customrecyclerview


import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

class RecyclerViewAdapter{/*: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {*/

    /*
    var dataSource: List<FakeDataModel>
    var layoutResId:Int = 0

    constructor(items: List<FakeDataModel>, layoutResId: Int){
        dataSource = items
        this.layoutResId = layoutResId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), layoutResId, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSource[position])
    }

    open class ViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: FakeDataModel) = {
            binding.setVariable(BR.item, item)
        }
    }*/
}