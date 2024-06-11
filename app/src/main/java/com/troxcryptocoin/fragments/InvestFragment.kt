import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.troxcryptocoin.adapter.InvestAdapter
import com.troxcryptocoin.databinding.FragmentInvestBinding
import com.troxcryptocoin.modal.HomeModal

class InvestFragment : Fragment() {
    private var binding: FragmentInvestBinding? = null
    private var quizQuestions1: MutableList<HomeModal> = mutableListOf()
    private lateinit var adapter: InvestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInvestBinding.inflate(inflater, container, false)
        fetchData() // Call fetchData here after inflating the layout
        return binding?.root
    }

    private fun fetchData() {
        val url1 = "https://taajakhaber.com/quiz_sathi/get_mock.php"
        val requestQueue1: RequestQueue = Volley.newRequestQueue(requireContext())

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            url1,
            null,
            { response ->
                val transarray = ArrayList<HomeModal>()
                for (i in 0 until response.length()) {
                    val dataObject = response.getJSONObject(i)
                    val question = dataObject.getString("question")
                    val op1 = dataObject.getString("op1")
                    val op2 = dataObject.getString("op2")
                    val op3 = dataObject.getString("op3")
                    val op4 = dataObject.getString("op4")
                    val ans = dataObject.getString("ans")
                    val transObj = HomeModal(question, op1, op2, op3, op4, ans)
                    transarray.add(transObj)
                }
                quizQuestions1.addAll(transarray)
                adapter.notifyDataSetChanged()
            },
            { error ->
                Toast.makeText(requireContext(), "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            })

        requestQueue1.add(jsonArrayRequest)

        adapter = InvestAdapter(quizQuestions1)
        binding?.rvInvestrecycler?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvInvestrecycler?.adapter = adapter
    }
}
