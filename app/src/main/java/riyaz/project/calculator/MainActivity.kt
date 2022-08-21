package riyaz.project.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvIntput:TextView?=null
    var lastnumeric:Boolean=false
    var lastdot:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvIntput=findViewById(R.id.answer)
    }
    fun OnDigit(view : View){
       tvIntput?.append((view as Button).text)
        lastnumeric=true
        lastdot=false
    }
    fun onClear(view: View){
        tvIntput?.text=""
    }
    fun OnDecimalPoint(view: View){
        if (lastnumeric && !lastdot){
            tvIntput?.append(".")
            lastnumeric=false
            lastdot=true
        }

    }
    fun onOperator(view: View){
        tvIntput?.text?.let{

            if(lastnumeric && !isOperatorAdded(it.toString())){
                tvIntput?.append((view as Button).text)
                lastnumeric=false
                lastdot=false
            }
        }
    }

    fun OnEqual(view:View){
        if(lastnumeric){
            var tvValue=tvIntput?.text.toString()
            var prefix=""

            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one= splitValue[0]
                    var two=splitValue[1]
                    if (prefix.isNotEmpty()){
                        one=prefix + one
                    }
                    tvIntput?.text=removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }
                else if(tvValue.startsWith("+")) {
                    prefix = "+"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one= splitValue[0]
                    var two=splitValue[1]
                    if (prefix.isNotEmpty()){
                        one=prefix + one
                    }
                    tvIntput?.text=removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }
                else if(tvValue.startsWith("/")) {
                    prefix = "/"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var one= splitValue[0]
                    var two=splitValue[1]
                    if (prefix.isNotEmpty()){
                        one=prefix + one
                    }
                    tvIntput?.text=removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }
                else if(tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var one= splitValue[0]
                    var two=splitValue[1]
                    if (prefix.isNotEmpty()){
                        one=prefix + one
                    }
                    tvIntput?.text=removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }


            }catch (e:ArithmeticException){
                e.printStackTrace()

            }
        }
    }
private fun removeZeroAfterDot(result:String):String{
    var value = result
    if (result.contains(".0"))
        value= result.substring(0,result.length-2)
    return value

}


    private fun isOperatorAdded(value:String):Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/") || value.contains("+") || value.contains("-")
        }
    }
}