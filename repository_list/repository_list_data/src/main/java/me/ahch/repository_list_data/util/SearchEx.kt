package me.ahch.repository_list_data.util

fun List<String>.toLinedString():String{
   var result=""
   this.forEach {
      result += "$it \r\n"
   }
   return result
}