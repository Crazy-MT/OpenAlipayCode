package com.maotong.openalipaycode

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.maotong.openalipaycode.ui.theme.OpenAlipayCodeTheme
import com.maotong.openalipaycode.ui.theme.OpenAlipayCodeTheme

class CodeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenAlipayCodeTheme {

                openAlipay()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    private fun openAlipay() {
        try {
            // 构造 intent 打开支付宝
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("alipayqr://platformapi/startapp?saId=20000056")
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
            System.exit(0)
        } catch (e: ActivityNotFoundException) {
            // 如果没有安装支付宝应用
            e.printStackTrace()
            Toast.makeText(this, "未检测到支付宝，请安装后重试", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OpenAlipayCodeTheme {
        Greeting("Android")
    }
}