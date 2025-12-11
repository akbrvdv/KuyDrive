package com.kelompok6.kuydrive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var orderAdapter: OrderAdapter
    private val listPesanan = ArrayList<Order>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.page_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvHistory)
        recyclerView.layoutManager = LinearLayoutManager(context)

        isiDataDumy()

        orderAdapter = OrderAdapter(listPesanan)
        recyclerView.adapter = orderAdapter
    }

    private fun isiDataDumy() {
        listPesanan.clear()
        listPesanan.add(Order("KuyRide", "12 Nov 2025", "Rp 12.000", "Mall Plaza", "Stasiun", "Selesai"))
        listPesanan.add(Order("KuyRide", "10 Nov 2025", "Rp 15.000", "Kampus UNIPMA", "Suncity", "Selesai"))
        listPesanan.add(Order("KuyRide", "09 Nov 2025", "Rp 10.000", "Stasiun Madiun", "Alun-alun", "Selesai"))
    }
}