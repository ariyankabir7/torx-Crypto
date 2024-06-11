import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.troxcryptocoin.adapter.HomeAdapter
import com.troxcryptocoin.databinding.FragmentHomeBinding
import com.troxcryptocoin.modal.HomeModal

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private var quizQuestions: MutableList<HomeModal> = mutableListOf()
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()
    }

    private fun fetchData() {
        val url = "https://taajakhaber.com/quiz_sathi/get_mock.php"
        val requestQueue: RequestQueue = Volley.newRequestQueue(requireContext())

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
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
                quizQuestions.addAll(transarray)
                adapter.notifyDataSetChanged()
            },
            { error ->
                Toast.makeText(requireContext(), "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            })

        requestQueue.add(jsonArrayRequest)

        adapter = HomeAdapter(quizQuestions)
        binding?.rvHomerecycler?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvHomerecycler?.adapter = adapter
    }
}
