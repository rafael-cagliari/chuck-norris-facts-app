package com.rafael.chuck_norris

interface FactItemListener {
   fun deleteFact(id:String)
   fun updateDataBase()
   fun shareFact(fact:String)
}