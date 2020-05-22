package com.shigaga.cmoneydemo.ui.first_page

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.transition.MaterialSharedAxis
import com.shigaga.cmoneydemo.R
import com.shigaga.cmoneydemo.ui.second_page.SecondFragment
import kotlinx.android.synthetic.main.first_fragment.*

class FirstFragment : Fragment() {

    companion object {
        fun newInstance() = FirstFragment()
    }

    private lateinit var viewModel: FirstViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        val forward = MaterialSharedAxis.create(MaterialSharedAxis.Z, true)
        enterTransition = forward

        val backward = MaterialSharedAxis.create(MaterialSharedAxis.Z, false)
        returnTransition = backward
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        nextButton.setOnClickListener {
            goToSecondFragment(it)
        }
    }

    private fun goToSecondFragment(v: View) {
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        Log.d(TAG, "v.transitionName:${v.transitionName}");
        fragmentTransaction.addSharedElement(v, v.transitionName)

        fragmentTransaction.replace(R.id.container, SecondFragment.newInstance())
                .addToBackStack(SecondFragment::class.java.simpleName)
                .commit()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FirstViewModel::class.java)
    }

}
