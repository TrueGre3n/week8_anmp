package com.ubaya.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.todoapp.R
import com.ubaya.todoapp.databinding.FragmentCreateTodoBinding
import com.ubaya.todoapp.databinding.FragmentEditTodoBinding
import com.ubaya.todoapp.model.Todo
import com.ubaya.todoapp.viewmodel.DetailTodoViewModel


class EditTodoFragment : Fragment(), RadioClickListener ,TodoEditClickListener {
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var binding: FragmentEditTodoBinding
    private lateinit var todo: Todo
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditTodoBinding.inflate(inflater,container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        binding.txtTitleTodo.text = "Edit Todo"
        binding.btnSubmit.text = "Save Changes"

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        binding.radioListener = this
        binding.saveListener = this
        observeViewModel()

//        binding.btnSubmit.setOnClickListener {
//            todo.title=binding.txtTitle.text.toString()
//            todo.notes=binding.txtNotes.text.toString()
//            val radio =
//                view.findViewById<RadioButton>(binding.radioGroupPriority.checkedRadioButtonId)
//            todo.priority = radio.tag.toString().toInt()
//            viewModel.update(todo)
//            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
//            Navigation.findNavController(it).popBackStack()
//        }




    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            /*todo=it
            binding.txtTitle.setText(it.title)
            binding.txtNotes.setText(it.notes)
            when (it.priority) {
                1 -> binding.radioLow.isChecked = true
                2 -> binding.radioMedium.isChecked = true
                else -> binding.radioHigh.isChecked = true
            }*/
            binding.todo = it
        })
    }

//    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
//        obj.priority = priority
//    }

    override fun onRadioClick(v: View) {
//        binding.todo.let{
//            binding.todo.priority = ...
//        }
        binding.todo!!.priority = v.tag.toString().toInt()
    }

    override fun onTodoEditClick(c: View) {
        viewModel.update(binding.todo!!)
        Toast.makeText(context,"Todo Updated",Toast.LENGTH_SHORT).show()
        Navigation.findNavController(c).popBackStack()
    }


}