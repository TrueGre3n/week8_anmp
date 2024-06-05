package com.ubaya.todoapp.view

import android.view.View
import android.widget.CompoundButton
import com.ubaya.todoapp.model.Todo

interface TodoCheckedChangeListener{
    fun onTodoCheckedChange(cb:CompoundButton,isChecked:Boolean,todo: Todo)

}

interface TodoEditClickListener{
    fun onTodoEditClick(c: View)
}

//interface RadioClickListener {
//    fun onRadioClick(v:View, priority:Int, obj:Todo)
//}

interface RadioClickListener {
    fun onRadioClick(v:View )
}
