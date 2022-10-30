package com.example.proiectdac
import java.io.File
import java.io.InputStream
class SecurityPage {
    fun main()
    {
        println("suntem in functia main din security page")
        val fileName = "Demo.txt"
        //Create file object
        val file = File(fileName)
        //Create file
        var isCreated = file.createNewFile()
        if(isCreated)
        {
            println("File is created")
        }
        else
        {
            println("File is not created")
        }
        //Again create file
        isCreated = file.createNewFile()
        if(isCreated)
        {
            println("File is created")
        }
        else
        {
            println("File is not created")
        }
    }
}