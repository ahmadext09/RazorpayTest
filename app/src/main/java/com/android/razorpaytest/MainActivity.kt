package com.android.razorpaytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(), PaymentResultListener {
    lateinit var amtEdt: EditText
    lateinit var payBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        amtEdt = findViewById(R.id.idEdtAmt)
        payBtn = findViewById(R.id.idBtnMakePaymanet)
        Checkout.preload(applicationContext)

        payBtn.setOnClickListener {


            val amt = amtEdt.text.toString()
            if (amt.isEmpty())
                return@setOnClickListener
            val amount = Math.round(amt.toFloat() * 100).toInt()
            val checkout = Checkout()
            //  checkout.setKeyID("rzp_test_BkEQB5Vvjqfd0c")
            // checkout.setImage(R.drawable.profile_img)
            //   rzp_live_0yoXZPeTpoL5hv
            checkout.setKeyID("rzp_live_0yoXZPeTpoL5hv")

            val obj = JSONObject()
            try {
                obj.put("name", "Ahmad Saquib")
                obj.put("description", "Test payment")
                obj.put("theme.color", "")
                obj.put("currency", "INR")
                obj.put("amount", amount)
                obj.put("prefill.contact", "9616987031")
                obj.put("prefill.email", "ahmethan0912@gmail.com")

                checkout.open(this@MainActivity, obj)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }

    override fun onPaymentSuccess(s: String?) {

        Toast.makeText(this, "Payment is successful : " + s, Toast.LENGTH_SHORT).show();
    }

    override fun onPaymentError(p0: Int, s: String?) {

        Toast.makeText(this, "Payment Failed due to error : " + s, Toast.LENGTH_SHORT).show();
    }
}
